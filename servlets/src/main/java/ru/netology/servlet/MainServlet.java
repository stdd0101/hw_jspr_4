package ru.netology.servlet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netology.config.JavaConfig;
import ru.netology.controller.PostController;
import ru.netology.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private PostController controller;

    @Override
    public void init() {
        // отдаём класс конфигурации
        final var context = new AnnotationConfigApplicationContext(JavaConfig.class);

        // получаем по имени бина
        final var controller = context.getBean("postController");

        // получаем по классу бина
        //final var service = context.getBean(PostService.class);

        // по умолчанию создаётся лишь один объект на BeanDefinition
        //final var isSame = service == context.getBean("postService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var path = req.getRequestURI();
        if (path.equals("/api/posts")) {
            controller.save(req.getReader(), resp);
            return;
        } else if (path.matches("/api/posts/\\d")) {
            final var id = Long.parseLong(path.substring(path.lastIndexOf("/")));
            final var content = req.getParameter("string");
            controller.update(id, content, resp);
            return;
        } else if (path.matches("/api/posts/\\d/delete")) {
            final var id = Long.parseLong(req.getParameter("id"));
            controller.removeById(id, resp);
            return;
        }
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var path = req.getRequestURI();
        if (path.equals("/api/posts")) {
            controller.all(resp);
            return;
        } else if (path.matches("/api/posts/\\d")) {
            final var id = Long.parseLong(path.substring(path.lastIndexOf("/")));
            controller.getById(id, resp);
            return;
        }
        super.doGet(req, resp);
    }

//  @Override
//  protected void service(HttpServletRequest req, HttpServletResponse resp) {
//    // если деплоились в root context, то достаточно этого
//    try {
//      final var path = req.getRequestURI();
//      final var method = req.getMethod();
//      // primitive routing
//      if (method.equals("GET") && path.equals("/api/posts")) {
//        controller.all(resp);
//        return;
//      }
//      if (method.equals("GET") && path.matches("/api/posts/\\d+")) {
//        // easy way
//        final var id = Long.parseLong(path.substring(path.lastIndexOf("/")));
//        controller.getById(id, resp);
//        return;
//      }
//      if (method.equals("POST") && path.equals("/api/posts")) {
//        controller.save(req.getReader(), resp);
//        return;
//      }
//      if (method.equals("DELETE") && path.matches("/api/posts/\\d+")) {
//        // easy way
//        final var id = Long.parseLong(path.substring(path.lastIndexOf("/")));
//        controller.removeById(id, resp);
//        return;
//      }
//      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//    } catch (Exception e) {
//      e.printStackTrace();
//      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//    }
//  }
}

