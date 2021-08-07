package ru.netology.servlet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netology.controller.PostController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private PostController controller;

    @Override
    public void init() {
        final var context = new AnnotationConfigApplicationContext("ru.netology");

        controller = context.getBean(PostController.class);
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
}

