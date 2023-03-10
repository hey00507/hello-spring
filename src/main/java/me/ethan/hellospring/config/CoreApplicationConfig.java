package me.ethan.hellospring.config;

import me.ethan.hellospring.discount.DiscountPolicy;
import me.ethan.hellospring.discount.RateDiscountPolicy;
import me.ethan.hellospring.member.MemberRepository;
import me.ethan.hellospring.member.MemberService;
import me.ethan.hellospring.member.MemberServiceImpl;
import me.ethan.hellospring.member.MemoryMemberRepository;
import me.ethan.hellospring.order.OrderService;
import me.ethan.hellospring.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreApplicationConfig {

    // 역할 클래스
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }



    // 구현 클래스
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
