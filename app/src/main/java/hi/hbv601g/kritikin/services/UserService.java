package hi.hbv601g.kritikin.services;

import hi.hbv601g.kritikin.entities.User;

public interface UserService {
    User signUp(String username, String password);
    void deleteUser(User user);
    User findByUsername(String username);
    User login(String username, String password);
    void logOut();
}
