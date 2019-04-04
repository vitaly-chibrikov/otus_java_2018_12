package ru.otus;

import com.google.gson.Gson;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.otus.auth.AuthorizationFilter;
import ru.otus.login.LoginServlet;
import ru.otus.user.InMemoryUserDao;
import ru.otus.user.UserService;
import ru.otus.servlet.UserServlet;

public class Main {
    private final static int PORT = 8080;

    public static void main(String[] args) throws Exception {
        new Main().start();
    }

    private void start() throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new UserServlet(userDao(), gson())), "/user");
        context.addServlet(new ServletHolder(new LoginServlet(userService())), "/login");

        context.addFilter(new FilterHolder(new AuthorizationFilter()), "/user", null);

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(context));

        server.start();
        server.join();
    }

    private UserService userService() {
        return new UserService(userDao());
    }


    private InMemoryUserDao userDao() {
        return new InMemoryUserDao();
    }

    private Gson gson() {
        return new Gson();
    }

}
