package me.ethan.hellospring.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
// 가짜 proxyCode 를 만들어서, 주입함 (provider 든 proxy 든 진짜 조회가 필요한 시점까지 지연한다는 점에서 같다.)
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
