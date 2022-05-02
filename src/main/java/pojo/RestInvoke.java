package pojo;

import lombok.Data;

import java.util.Map;
import java.util.TreeMap;

@Data
public class RestInvoke {
    private String urlInvoke;
    private String contentType;
    private String type;
    private String requestBody;
    private Map<String, String> requestHeaderMap = new TreeMap<>();
    private Map<String, String> pathParam = new TreeMap<>();
    private int code;
    private String responseBody;
    private String expectedResponse;
    private int expectedCode;
}
