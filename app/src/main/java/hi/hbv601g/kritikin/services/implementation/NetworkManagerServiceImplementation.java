package hi.hbv601g.kritikin.services.implementation;

import java.io.IOException;

import hi.hbv601g.kritikin.services.NetworkManagerService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkManagerServiceImplementation implements NetworkManagerService {
    public static final String SERVER_URL = "https://hbv501g-t18-production.up.railway.app/api/";
    private OkHttpClient client;
    public NetworkManagerServiceImplementation() {
        client = new OkHttpClient();
    }
    @Override
    public String doGET(String url, Object[] args) throws IOException {
        Request request = new Request.Builder().url(SERVER_URL + url).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Error e){
            throw new IOException(e.getMessage());
        }
    }

    @Override
    public String doPOST(String url, Object[] args) throws IOException {
        //TODO: implement doPOST
        return null;
    }

    @Override
    public String doDELETE(String url, Object[] args) throws IOException {
        //TODO: implement doDELETE
        return null;
    }

    @Override
    public String doPATCH(String url, Object[] args) throws IOException {
        //TODO: implement doPATCH
        return null;
    }
}
