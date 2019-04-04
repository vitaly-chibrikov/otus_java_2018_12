package ru.otus.timer;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/*
 * 1) Написать сервлет, который на GET запрос возвращает сущность User в формате JSON.
 * Имя пользователя передается в параметре name. Если параметр не передан - возвращать всех пользователей
 *
 * 2)* Написать сервлет, который на PUT запрос создает User.
 * Данные приходят в формате JSON.
 *
 * 3)* Написать сервлет, который на POST запрос изменяет User. Ключ - name.
 *
 * 4)* Написать сервлет, который на DELETE запрос удаляет User. Ключ - name.
 *
 * */


/*
 *
 * 1) Реализовать сервлет, который на /login будет проводить аутентификацию пользователя
 *
 * */

/*
 *
 * 1) Написать Filter для авторизации на /user.
 * Редактировать и удалять существующего пользователя могут только авторизованные пользователи
 *
 * 2)* Максимальное время жизни неактивной сессии 30 сек
 *
 * 3)* Хранить в cookie имя пользователя. Cookie живут столько же, сколько сессия.
 *
 * 4)* Пользователь может менять только свой пароль
 *
 * */


public class TimerMain {
    private final static int PORT = 8080;
    private final static String STATIC = "/static";

    public static void main(String[] args) throws Exception {
        new TimerMain().start();
    }

    private void start() throws Exception {
        resourcesExample();

        ResourceHandler resourceHandler = new ResourceHandler();
        Resource resource = Resource.newClassPathResource(STATIC);
        resourceHandler.setBaseResource(resource);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new TimerServlet()), "/timer");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }

    private void resourcesExample() {
        URL url = TimerMain.class.getResource(STATIC + "/index.html"); //local path starts with '/'
        System.out.println(url);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            System.out.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
