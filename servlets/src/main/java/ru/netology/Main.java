package ru.netology;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void Main(String [] args) throws ClassNotFoundException, NoSuchMethodException, IllegalStateException,
            InvocationTargetException, IllegalAccessException, InstantiationException, ServletException, IOException {
        Class<?> aClass = Class.forName("ru.netology.servlet.MainServlet");
        Servlet servlet = (Servlet) aClass.getConstructor().newInstance();
        servlet.init(null);
        servlet.service(null, null);
        servlet.destroy();
    }
}
