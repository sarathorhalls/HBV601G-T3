package hi.hbv601g.kritikin.services.implementation;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import hi.hbv601g.kritikin.services.NetworkManagerService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Headers;

public class NetworkManagerServiceImplementation implements NetworkManagerService {
    private OkHttpClient client;
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
        /*final Response[] returnedResponse = new Response[1];

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                //implement failure
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                returnedResponse[0] = response;
            }
        });
        return returnedResponse[0];*/
    }

    @Override
    public String doPOST(String url, LinkedHashMap<String, String> keyValuePairs) throws IOException {
        return doPOSTResponse(url, keyValuePairs).body().string();
    }

    @Override
    public Response doPOSTResponse(String url, LinkedHashMap<String, String> body) throws IOException {
        FormBody.Builder formBuilder = new FormBody.Builder();

        for (Map.Entry<String, String> keyValuePair: body.entrySet()) {
            formBuilder.add(keyValuePair.getKey(), keyValuePair.getValue());
        }

        RequestBody formBody = formBuilder.build();

        Request request = new Request.Builder().url(SERVER_URL + url).post(formBody).build();

        Call call = client.newCall(request);
        return call.execute();
    }

    @Override
    public String doPOST(String url, LinkedHashMap<String, String> body, LinkedHashMap<String, String> headers) throws IOException {
        return doPOSTResponse(url, body, headers).body().string();
    }

    @Override
    public Response doPOSTResponse(String url, LinkedHashMap<String, String> body, LinkedHashMap<String, String> headers) throws IOException {
        FormBody.Builder formBuilder = new FormBody.Builder();
        Headers.Builder headerBuilder = new Headers.Builder();

        for (Map.Entry<String, String> keyValuePair: body.entrySet()) {
            formBuilder.add(keyValuePair.getKey(), keyValuePair.getValue());
        }

        for (Map.Entry<String, String> keyValuePair: headers.entrySet()) {
            headerBuilder.add(keyValuePair.getKey(), keyValuePair.getValue());
        }

        RequestBody formBody = formBuilder.build();
        Headers formHeaders = headerBuilder.build();

        Request request = new Request.Builder().url(SERVER_URL + url).post(formBody).headers(formHeaders).build();

        Call call = client.newCall(request);
        return call.execute();
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
