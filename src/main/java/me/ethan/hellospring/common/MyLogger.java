package me.ethan.hellospring.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request")
@Slf4j
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void makeUrl(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        log.info("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init(){
        this.uuid = UUID.randomUUID().toString().substring(0, 8);
        log.info("[{}] request scope bean create: {} " ,uuid,this);
    }

    @PreDestroy
    public void close(){
        log.info("close " + uuid);
    }
}
