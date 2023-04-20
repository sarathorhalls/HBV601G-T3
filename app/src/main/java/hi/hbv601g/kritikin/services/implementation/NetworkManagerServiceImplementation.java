package hi.hbv601g.kritikin.services.implementation;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import hi.hbv601g.kritikin.services.NetworkManagerService;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Headers;

public class NetworkManagerServiceImplementation implements NetworkManagerService {
    private final OkHttpClient client;
    public NetworkManagerServiceImplementation() {
        client = new OkHttpClient();
    }

    @Override
    public String doGET(String url) throws IOException {
        return doGETResponse(url).body().string();
    }
    @Override
    public Response doGETResponse(String url) throws IOException {
        Request request = new Request.Builder().url(SERVER_URL + url).build();
        Call call = client.newCall(request);

        return call.execute();
    }

    @Override
    public String doPOST(String url, LinkedHashMap<String, String> body) throws IOException {
        return doPOSTResponse(url, body, null).body().string();
    }

    @Override
    public String doPOST(String url, LinkedHashMap<String, String> body, LinkedHashMap<String, String> headers) throws IOException {
        return doPOSTResponse(url, body, headers).body().string();
    }
    @Override
    public Response doPOSTResponse(String url, LinkedHashMap<String, String> body, LinkedHashMap<String, String> headers) throws IOException {
        Request.Builder requestBuilder = new Request.Builder().url(SERVER_URL + url);
        FormBody.Builder formBuilder = new FormBody.Builder();

        if (body != null) {
            for (Map.Entry<String, String> keyValuePair: body.entrySet()) {
                formBuilder.add(keyValuePair.getKey(), keyValuePair.getValue());
            }
        }
        RequestBody formBody = formBuilder.build();
        requestBuilder.post(formBody);


        if (headers != null) {
            Headers.Builder headerBuilder = new Headers.Builder();

            for (Map.Entry<String, String> keyValuePair: headers.entrySet()) {
                headerBuilder.add(keyValuePair.getKey(), keyValuePair.getValue());
            }

            Headers formHeaders = headerBuilder.build();
            requestBuilder.headers(formHeaders);
        }

        Request request = requestBuilder.build();

        Call call = client.newCall(request);
        return call.execute();
    }


    @Override
    public String doDELETE(String url, Object[] args) throws IOException {
        // TODO: implement doDELETE
        return null;
    }

    @Override
    public String doPATCH(String url, Object[] args) throws IOException {
        // TODO: implement doPATCH
        return null;
    }
}
