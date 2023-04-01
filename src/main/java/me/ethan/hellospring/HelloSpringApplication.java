package me.ethan.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // 모든 패키지를 탐색해서 @WebServlet, @WebFilter, @WebListener 가 붙은 클래스를 찾아서 서블릿으로 등록 (서블릿을 찾아서 등록)
public class HelloSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloSpringApplication.class, args);
    }
}

