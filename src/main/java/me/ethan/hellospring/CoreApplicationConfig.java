package me.ethan.hellospring;

import me.ethan.hellospring.discount.DiscountPolicy;
import me.ethan.hellospring.discount.FixDiscountPolicy;
import me.ethan.hellospring.member.MemberRepository;
import me.ethan.hellospring.member.MemberService;
import me.ethan.hellospring.member.MemberServiceImpl;
import me.ethan.hellospring.member.MemoryMemberRepository;
import me.ethan.hellospring.order.OrderService;
import me.ethan.hellospring.order.OrderServiceImpl;

public class CoreApplicationConfig {

    // 역할 클래스
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }



    // 구현 클래스
    private DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
