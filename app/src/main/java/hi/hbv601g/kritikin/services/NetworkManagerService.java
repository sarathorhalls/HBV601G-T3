package hi.hbv601g.kritikin.services;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.Response;
public interface NetworkManagerService {
    String SERVER_URL = "https://hbv501g-t18-production.up.railway.app/api";
    String doGET(String url) throws IOException;
    Response doGETResponse(String url) throws IOException;
    String doPOST(String url, LinkedHashMap<String, String> body) throws IOException;
    String doPOST(String url, LinkedHashMap<String, String> body, LinkedHashMap<String, String> headers) throws IOException;

    // Just for testing
    Response doPOSTResponse(String url, LinkedHashMap<String, String> body, LinkedHashMap<String, String> headers) throws IOException;
    String doDELETE(String url, Object[] args) throws IOException;
    String doPATCH(String url, Object[] args) throws IOException;
}
