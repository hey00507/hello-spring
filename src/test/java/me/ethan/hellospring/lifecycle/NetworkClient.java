package me.ethan.hellospring.lifecycle;


import lombok.ToString;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@ToString
public class NetworkClient{
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지 - 생성자 호출시엔 url 이 없음");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    private void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }


    /**
     * InitializingBean 의 afterPropertiesSet 은, 의존관계 주입이 끝나면 호출된다.
     * afterPropertiesSet 과 destroy 는, 스프링 빈이라면, 무조건 호출된다.( 스프링 의존적이다.)
     * 인터페이스를 구현하는 방법도 있지만, @PostConstruct, @PreDestroy 를 사용하는 방법도 있다. (인터페이스 구현은 거의 사용되지 않음)
     */
    @PostConstruct
    public void init(){
        System.out.println("NetworkClient.init() - @PostConstruct");
        System.out.println("return 되는 시점에, propertiesSet 이 호출되며, url 등록됨" + url);
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close(){
        System.out.println("NetworkClient.close() - @PreDestroy");
        disconnect();
    }

}
