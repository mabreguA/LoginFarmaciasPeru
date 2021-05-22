package com.iteraprocess.test.steps;

import com.iteraprocess.framework.base.Base;
import com.iteraprocess.framework.base.DriverContext;
import com.iteraprocess.framework.config.Settings;
import com.iteraprocess.test.pages.InkaFarmaPage;
import com.iteraprocess.test.pages.LoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import java.util.concurrent.TimeUnit;

public class LoginFarmaciaSteps extends Base {

    @Given("^Ingresamos a InkaFarma$")
    public void ingresamosAInkaFarma() {
        DriverContext.Browser.GoToUrl(Settings.URLInkafarma);
        DriverContext.Driver.manage().window().maximize();
        Settings.Logs.Write("Navigated to URL " + Settings.URLInkafarma);
        CurrentPage = GetInstance(LoginPage.class);
        DriverContext.Driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        System.out.println("=================== Runned on PC mode ===================");
        CurrentPage.As(LoginPage.class).Message();
    }

    @And("^Ingreso los datos como el Correo \"([^\"]*)\" y Contraseña \"([^\"]*)\"$")
    public void ingresoDatos(String correo, String contrasena)  {
        CurrentPage.As(LoginPage.class).LoginF1(correo,contrasena);
    }

    @And("^Ingreso el producto \"([^\"]*)\"$")
    public void ingresoElProducto(String producto)  {
        CurrentPage = GetInstance(InkaFarmaPage.class);
        CurrentPage.As(InkaFarmaPage.class).busca(producto);

    }

    @And("^Cierro sesion$")
    public void cierroSesion() {
        CurrentPage=GetInstance(LoginPage.class);
        CurrentPage.As(LoginPage.class).cierro();
    }
}
