package apiServices;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.RestInvoke;
import static io.restassured.RestAssured.given;

public class RestCaller {

    /**
     * Can use this method to send REST requests and can extend
     * the switch cases as per the needs
     * @param invoke http request and response pojo
     * @return Response object which returns as the response
     */
    public Response apiRequestSender(RestInvoke invoke){
        Response response = null;
        switch (invoke.getType()){
            case "GET":
                response= given()
                        .headers(invoke.getRequestHeaderMap())
                        .get(invoke.getUrlInvoke())
                        .then().using().extract().response();
                break;
            case "POST":
                response= given().contentType(ContentType.valueOf(invoke.getContentType())).body(invoke.getRequestBody())
                        .headers(invoke.getRequestHeaderMap())
                        .post(invoke.getUrlInvoke())
                        .then().using().extract().response();
                break;
            case "PUT":
                response= given().contentType(ContentType.valueOf(invoke.getContentType())).body(invoke.getRequestBody())
                        .headers(invoke.getRequestHeaderMap())
                        .put(invoke.getUrlInvoke())
                        .then().using().extract().response();
                break;
        }
        return response;
    }
}
