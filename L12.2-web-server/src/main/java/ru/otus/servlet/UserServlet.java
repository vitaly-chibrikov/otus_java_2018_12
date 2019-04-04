package ru.otus.servlet;

import com.google.gson.Gson;
import ru.otus.user.User;
import ru.otus.user.UserDao;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserServlet extends HttpServlet {

    private static final String APPLICATION_JSON = "application/json;charset=UTF-8";
    private final UserDao userDao;
    private final Gson gson;

    public UserServlet(UserDao userDao, Gson gson) {
        this.userDao = userDao;
        this.gson = gson;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameterValues("name")[0];
        User user = userDao.findByName(name);

        resp.setContentType(APPLICATION_JSON);

        ServletOutputStream out = resp.getOutputStream();
        out.print(gson.toJson(user));
    }

}
