package hi.hbv601g.kritikin.services.implementation;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.LinkedHashMap;

import hi.hbv601g.kritikin.entities.User;
import hi.hbv601g.kritikin.services.NetworkManagerService;
import hi.hbv601g.kritikin.services.UserService;

public class UserServiceImplementation implements UserService {
    NetworkManagerService networkManagerService;

    public UserServiceImplementation() {
        networkManagerService = new NetworkManagerServiceImplementation();
    }
    @Override
    public User signUp(String username, String password) {
        LinkedHashMap<String, String> authBody = new LinkedHashMap<>();
        authBody.put("username", username);
        authBody.put("password", password);

        String jsonUser;

        try {
            jsonUser = networkManagerService.doPOST("/auth/signup", authBody);
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }

        Gson g = new Gson();
        return g.fromJson(jsonUser, User.class);
    }

    @Override
    public void deleteUser(User user) {
        //TODO: implement deleteUser
    }

    @Override
    public User findByUsername(String username) {
        //TODO: implement in backend or scrap function
        return null;
    }

    @Override
    public User login(String username, String password) {
        LinkedHashMap<String, String> authBody = new LinkedHashMap<>();
        authBody.put("username", username);
        authBody.put("password", password);
        User user;
        try {
            String loginBody = networkManagerService.doPOST("/auth/signin", authBody);
            Gson g = new Gson();
            user = g.fromJson(loginBody, User.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    @Override
    public void logOut() {
        //TODO: Implement logOut
    }
}
