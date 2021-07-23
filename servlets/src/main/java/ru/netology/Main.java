package ru.netology;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netology.service.PostService;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String [] args) throws ClassNotFoundException, NoSuchMethodException, IllegalStateException,
            InvocationTargetException, IllegalAccessException, InstantiationException, ServletException, IOException {
        Class<?> aClass = Class.forName("ru.netology.servlet.MainServlet");
        Servlet servlet = (Servlet) aClass.getConstructor().newInstance();
        servlet.init(null);
        //servlet.service(null, null);
        //servlet.destroy();
    }

//    public static void main(String[] args) {
//        // отдаём список пакетов, в которых нужно искать аннотированные классы
//        final var context = new AnnotationConfigApplicationContext("ru.netology");
//
//        // получаем по имени бина
//        final var controller = context.getBean("postController");
//
//        // получаем по классу бина
//        final var service = context.getBean(PostService.class);
//
//        // по умолчанию создаётся лишь один объект на BeanDefinition
//        final var isSame = service == context.getBean("postService");
//    }
}
