package hi.hbv601g.kritikin.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.LinkedHashMap;

import hi.hbv601g.kritikin.services.implementation.NetworkManagerServiceImplementation;
import okhttp3.Response;

public class NetworkManagerServiceTest {
    NetworkManagerService networkManagerService;

    @Before
    public void createNetworkManagerService() {
        networkManagerService = new NetworkManagerServiceImplementation();
    }
    @Test
    public void getCompaniesFromServer() throws IOException {
        try (Response companiesGetRequest = networkManagerService.doGETResponse("companies")) {

            assertEquals("Companies response code not 200", companiesGetRequest.code(), 200);
            assertNotEquals("Companies Body null", null, companiesGetRequest.body());
            assertNotEquals("Companies body empty", "", companiesGetRequest.body().string());
        }
    }

    @Test
    public void postAuthenticationFromServer() throws IOException {
        LinkedHashMap<String, String> responseBody = new LinkedHashMap<>();
        responseBody.put("username", "test");
        responseBody.put("password", "test");
        try (Response companiesPostRequest = networkManagerService.doPOSTResponse("auth/signin", responseBody)) {
            assertEquals("Sign in response code not 200", companiesPostRequest.code(), 200);
        }
    }
}
