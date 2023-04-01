package me.ethan.hellospring.basic.request;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
    }

    private void printHeaderUtils(HttpServletRequest request) {
        log.info("----- HeaderUtils - start -----");
        log.info("request.getHeaderNames() = {}", request.getHeaderNames());
        log.info("request.getHeader(\"host\") = {}", request.getHeader("host"));
        log.info("request.getHeader(\"content-type\") = {}", request.getHeader("content-type"));
        log.info("request.getHeader(\"accept\") = {}", request.getHeader("accept"));
        log.info("request.getHeader(\"cookie\") = {}", request.getHeader("cookie"));
        log.info("request.getHeader(\"user-agent\") = {}", request.getHeader("user-agent"));
        log.info("----- HeaderUtils - end -----");
    }

    private void printHeaders(HttpServletRequest request) {
        log.info("----- Headers - start -----");
        request.getHeaderNames()
                .asIterator()
                .forEachRemaining(headerName -> log.info("{} : {}", headerName, request.getHeader(headerName)));
        log.info("----- Headers - end -----");
    }

    private void printStartLine(HttpServletRequest request) {
        log.info("----- REQUEST-LINE - start -----");
        log.info("request.getMethod() = {}", request.getMethod());
        log.info("request.getProtocal() = {}", request.getProtocol());
        log.info("request.getScheme() = {}", request.getScheme());
        log.info("request.getRequestURL() = {}", request.getRequestURL());
        log.info("request.getRequestURI() = {}", request.getRequestURI());
        log.info("request.getQueryString() = {}", request.getQueryString());
        log.info("request.isSecure() = {}", request.isSecure());
        log.info("----- REQUEST-LINE - end -----");
    }


}
