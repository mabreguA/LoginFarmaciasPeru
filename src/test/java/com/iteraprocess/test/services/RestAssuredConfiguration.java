package com.iteraprocess.test.services;

import com.iteraprocess.framework.config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class RestAssuredConfiguration {

    @BeforeSuite(alwaysRun = true)
    public void configure() throws IOException {
        ConfigReader.PopulateSettings();
    }

    public RequestSpecification getRequestSpecification(String baseUri, String basePAth, int basePort) {

        RestAssured.baseURI = baseUri;
        if (basePort != 0) {
            RestAssured.port = basePort;
        }
        RestAssured.basePath = basePAth;

        return RestAssured.given().contentType(ContentType.JSON);
    }

    public RequestSpecification getRequestSpecificationFile(String baseUri, String basePAth, int basePort) {

        RestAssured.baseURI = baseUri;
        if (basePort != 0) {
            RestAssured.port = basePort;
        }
        RestAssured.basePath = basePAth;

        return RestAssured.given().contentType("multipart/form-data");
    }

    public Response getResponseGet(RequestSpecification requestSpecification, String endpoint, int status) {
        requestSpecification.log().all();
        Response response = requestSpecification.get(endpoint);
        if (response.getStatusCode() != status) {
            if (response.contentType().equals("text/html")) {
                Assert.assertEquals(response.getStatusCode(), status, " Error [" + response.htmlPath().get().toString() + "]");
            } else {
                System.out.println("=================>>>>>>>>><<<" + response.contentType());
                Assert.assertEquals(response.getStatusCode(), status, " Error [" + response.jsonPath().get("error") + "] - Message [" + response.jsonPath().get("message") + "]");
            }
        }
        response.then().log().all();
        return response;
    }

    public Response getResponsePost(RequestSpecification requestSpecification, String endpoint, int status) {
        requestSpecification.log().all();
        Response response = requestSpecification.post(endpoint);
        if (response.getStatusCode() != status) {
            if (response.contentType().equals("text/html")) {
                Assert.assertEquals(response.getStatusCode(), status, " Error [" + response.htmlPath().get().toString() + "]");
            } else {
                System.out.println("=================>>>>>>>>><<<" + response.contentType());
                Assert.assertEquals(response.getStatusCode(), status, " Error [" + response.jsonPath().get("error") + "] - Message [" + response.jsonPath().get("message") + "]");
            }
        }
        response.then().log().all();
        return response;
    }

    public Response getResponsePut(RequestSpecification requestSpecification, String endpoint, int status) {
        requestSpecification.log().all();
        Response response = requestSpecification.put(endpoint);
        if (response.getStatusCode() != status) {
            if (response.contentType().equals("text/html")) {
                Assert.assertEquals(response.getStatusCode(), status, " Error [" + response.htmlPath().get().toString() + "]");
            } else {
                System.out.println("=================>>>>>>>>><<<" + response.contentType());
                Assert.assertEquals(response.getStatusCode(), status, " Error [" + response.jsonPath().get("error") + "] - Message [" + response.jsonPath().get("message") + "]");
            }
        }
        response.then().log().all();
        return response;
    }

    public Response getResponsePatch(RequestSpecification requestSpecification, String endpoint, int status) {
        requestSpecification.log().all();
        Response response = requestSpecification.patch(endpoint);
        if (response.getStatusCode() != status) {
            if (response.contentType().equals("text/html")) {
                Assert.assertEquals(response.getStatusCode(), status, " Error [" + response.htmlPath().get().toString() + "]");
            } else {
                System.out.println("=================>>>>>>>>><<<" + response.contentType());
                Assert.assertEquals(response.getStatusCode(), status, " Error [" + response.jsonPath().get("error") + "] - Message [" + response.jsonPath().get("message") + "]");
            }
        }
        response.then().log().all();
        return response;
    }

    public Response getResponsePost(RequestSpecification requestSpecification, String endpoint, int status, String data) {
        requestSpecification.log().all();
        Response response = requestSpecification.post(endpoint);
        if (response.getStatusCode() != status) {
            if (response.contentType().equals("text/html")) {
                Assert.assertEquals(response.getStatusCode(), status, " Error [" + response.htmlPath().get().toString() + "]");
            } else {
                System.out.println("=================>>>>>>>>><<<" + response.contentType());
                Assert.assertEquals(response.getStatusCode(), status, " Error [" + response.jsonPath().get("error") + "] - Message [" + response.jsonPath().get("message") + "]");
            }
        }
        response.then().log().all();
        return response;
    }

    public Response getResponsePostTotal(RequestSpecification requestSpecification, String endpoint) {
        requestSpecification.log().all();
        Response response = requestSpecification.post(endpoint);
        response.then().log().all();
        return response;
    }

    //=============================================================================================

    public Response getResponseTotalGet(RequestSpecification requestSpecification, String endpoint) {
        requestSpecification.log().all();
        Response response = requestSpecification.get(endpoint);
        response.then().log().all();
        return response;
    }

    public Response getResponseTotalPost(RequestSpecification requestSpecification, String endpoint) {
        requestSpecification.log().all();
        Response response = requestSpecification.post(endpoint);
        response.then().log().all();
        return response;
    }

    public Response getResponseTotalPut(RequestSpecification requestSpecification, String endpoint) {
        requestSpecification.log().all();
        Response response = requestSpecification.put(endpoint);
        response.then().log().all();
        return response;
    }

    public Response getResponseTotalDelete(RequestSpecification requestSpecification, String endpoint) {
        requestSpecification.log().all();
        Response response = requestSpecification.delete(endpoint);
        response.then().log().all();
        return response;
    }

    public Response getResponseTotalPatch(RequestSpecification requestSpecification, String endpoint) {
        requestSpecification.log().all();
        Response response = requestSpecification.patch(endpoint);
        response.then().log().all();
        return response;
    }
}
