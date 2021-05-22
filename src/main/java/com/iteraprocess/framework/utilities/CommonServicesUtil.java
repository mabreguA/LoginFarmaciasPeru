package com.iteraprocess.framework.utilities;

import org.json.JSONObject;
import org.openqa.selenium.json.Json;

import java.util.HashMap;
import java.util.Map;

public class CommonServicesUtil {

    public static String postRequestToken(String clientId,String clientSecret,String resource) {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("clientId", clientId);
        jsonParams.put("clientSecret", clientSecret);
        jsonParams.put("resource", resource);
        return jsonParams.toString();
    }

    public static String postRequestMarca() {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("monitor", postMapMonitor());
        return jsonParams.toString();
    }

    public static String postMonitor() {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("codUsr", "");
        jsonParams.put("codModulo", "");
        jsonParams.put("codAplic", "");
        return jsonParams.toString();
    }

    public static Map<String, String> postMapMonitor() {
        Map<String, String> mapMonitor = new HashMap<String, String>();

        mapMonitor.put("codUsr", "");
        mapMonitor.put("codModulo", "");
        mapMonitor.put("codAplic", "");

        return mapMonitor;
    }
}
