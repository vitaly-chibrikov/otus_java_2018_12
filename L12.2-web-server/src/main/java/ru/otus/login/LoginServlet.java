package ru.otus.login;

import ru.otus.user.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private static final int EXPIRE_INTERVAL = 20; // seconds
    private final UserService userService;

    public LoginServlet(UserService userService) {
        this.userService = userService;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if (userService.authenticate(name, password)) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(30);
        } else {
            response.setStatus(403);
        }

    }

}