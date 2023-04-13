package hi.hbv601g.kritikin.services;

import hi.hbv601g.kritikin.entities.User;

public interface UserService {
    public User signUp(String username, String password);
    public void deleteUser(User user);
    public User findByUsername(String username);
    public User login(String username, String password);
    public void logOut();
}
