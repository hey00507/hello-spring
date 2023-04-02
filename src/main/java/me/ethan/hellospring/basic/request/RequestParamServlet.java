package me.ethan.hellospring.basic.request;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        log.info("[전체 파라미터 조회 - start]");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> log.info(paramName + " = " + request.getParameter(paramName)));
        log.info("[전체 파라미터 조회 - end]");
        log.info("");
        log.info("[단일 파라미터 조회 - start]");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        log.info("username = " + username);
        log.info("age = " + age);
        log.info("[단일 파라미터 조회 - end]");
        log.info("");

        log.info("[이름이 같은 복수 파라미터 조회 - start]");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            log.info("username = " + name);
        }
        log.info("[이름이 같은 복수 파라미터 조회 - end]");
        response.getWriter().write("ok");
    }
}
