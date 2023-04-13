package hi.hbv601g.kritikin.services;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.Response;
public interface NetworkManagerService {
    public static final String SERVER_URL = "https://hbv501g-t18-production.up.railway.app/api";
    public String doGET(String url) throws IOException;
    public Response doGETResponse(String url) throws IOException;
    public String doPOST(String url, LinkedHashMap<String, String> body) throws IOException;
    public String doPOST(String url, LinkedHashMap<String, String> body, LinkedHashMap<String, String> headers) throws IOException;
    //Just for testing
    public Response doPOSTResponse(String url, LinkedHashMap<String, String> body, LinkedHashMap<String, String> headers) throws IOException;
    public String doDELETE(String url, Object[] args) throws IOException;
    public String doPATCH(String url, Object[] args) throws IOException;
}
