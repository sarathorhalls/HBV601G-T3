package hi.hbv601g.kritikin.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import hi.hbv601g.kritikin.entities.User;
import hi.hbv601g.kritikin.services.implementation.UserServiceImplementation;

public class UserServiceTest {
    UserService userService;

    @Before
    public void createUserService() {
        userService = new UserServiceImplementation();
    }

    @Test
    public void testSignUpFunction() {
        String username = "Sara53";
        User testUser = userService.signUp(username, "password");

        assertNotNull("user object null", testUser);
        assertEquals("mismatched names", username, testUser.getUsername());
    }

    @Test
    public void testLoginFunction() {
        User testUser = userService.login("test", "test");

        assertNotNull("user null", testUser);
        assertEquals("username doesn't match", "test", testUser.getUsername());
        assertNotEquals("access_token empty", "", testUser.getAccess_token());
    }
}
