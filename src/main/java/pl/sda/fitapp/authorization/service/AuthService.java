package pl.sda.fitapp.authorization.service;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitapp.domains.UserType;
import pl.sda.fitapp.domains.dto.LoggedUserDto;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Optional;


@Service
public class AuthService {

    @Autowired
    private UserService userService;

    private static final String COOKIE_NAME = "remember-me";
    public static final String SESSION_USERNAME = "username";

    public boolean isAuthenticated() {
        return VaadinSession.getCurrent().getAttribute(SESSION_USERNAME) != null || loginRememberedUser();
    }

    public Long login(String username, String password, boolean rememberMe, UserType userType) {
        if (userService.isAuthenticUser(username, password, userType) > 0) {
            LoggedUserDto loggedUserDto = new LoggedUserDto(username, userType);
            VaadinSession.getCurrent().setAttribute(SESSION_USERNAME, loggedUserDto);
            if (rememberMe) {
                rememberUser(loggedUserDto);
            }
            return 1L;
        }

        return 0L;
    }

    public static void logOut() {
        Optional<Cookie> cookie = getRememberMeCookie();
        if (cookie.isPresent()) {
            String id = cookie.get().getValue();
            UserService.removeRememberedUser(id);
            deleteRememberMeCookie();
        }

        VaadinSession.getCurrent().close();
        Page.getCurrent().setLocation("");
    }

    private static Optional<Cookie> getRememberMeCookie() {
        Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
        return Arrays.stream(cookies).filter(c -> c.getName().equals(COOKIE_NAME)).findFirst();
    }

    private static boolean loginRememberedUser() {
        Optional<Cookie> rememberMeCookie = getRememberMeCookie();

        if (rememberMeCookie.isPresent()) {
            String id = rememberMeCookie.get().getValue();
            LoggedUserDto username = UserService.getRememberedUser(id);

            if (username != null) {
                VaadinSession.getCurrent().setAttribute(SESSION_USERNAME, username.getUsername() + " " + username.getUserType());
                return true;
            }
        }

        return false;
    }

    private static void rememberUser(LoggedUserDto loggedUserDto) {
        String id = UserService.rememberUser(loggedUserDto);

        Cookie cookie = new Cookie(COOKIE_NAME, id);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 30);
        VaadinService.getCurrentResponse().addCookie(cookie);
    }

    private static void deleteRememberMeCookie() {
        Cookie cookie = new Cookie(COOKIE_NAME, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        VaadinService.getCurrentResponse().addCookie(cookie);
    }

}
