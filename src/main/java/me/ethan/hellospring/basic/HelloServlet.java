package me.ethan.hellospring.basic;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {


    // servlet 이 호출될 때, 호출되는 메서드
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("HelloServlet.service");
        log.info("request = {}", request);
        log.info("response = {}", response);

        String userName = request.getParameter("userName");
        log.info("user name is {}", userName);


        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + userName);

    }
}
