package com.iteraprocess.framework.utilities;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Calendar;

public class ConnectionBdUtil {
    //    private static final String url = "jdbc:oracle:thin:@psbdouat0100.ppstst.com.pe:1521:igwuat";
    private static final String url = "jdbc:oracle:thin:@172.30.10.154:1522:epps";
    //    private static final String user = "USPCED01";
//    private static final String pass = "USPCED01";
    private static final String user = "consulta";
    private static final String pass = "consulta1";
    private static Connection ConexionSQL;
    private static ResultSet ResulSetSQL;
    private static Statement stmt;

    public static Connection GetConexion() {
        ConexionSQL = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getException() + ". Driver no encontrado");
        }

        try {
            ConexionSQL = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ConexionSQL;
    }

    public static String getFechaSistema(String strFecha) {

        String fechaBD=null;
        java.sql.Date fechaObtenida=null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        //OBTENER LA FECHA DESDE LA BD
        try {
            Connection con = GetConexion();
            stmt = con.createStatement();
            ResulSetSQL = stmt.executeQuery("select sysdate from dual");
            while (ResulSetSQL.next()) {
                fechaBD = ResulSetSQL.getString("sysdate").split(" ")[0];
            }
            fechaObtenida = new java.sql.Date(sdf.parse(fechaBD).getTime());
            calendar.setTime(fechaObtenida);

            ResulSetSQL.close();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        //SUMA O RESTA DE DIAS
        if(!strFecha.toUpperCase().equals("FECHASISTEMA")){
            String signal = strFecha.substring(12,13);
            int days = Integer.parseInt(strFecha.substring(13));
            if (signal.equals("+")) {
                calendar.add(Calendar.DAY_OF_YEAR, days);
            } else {
                calendar.add(Calendar.DAY_OF_YEAR,-days);
            }
        }
        sdf.applyPattern("dd/MM/yyyy");
        return sdf.format(calendar.getTime());
    }
}
