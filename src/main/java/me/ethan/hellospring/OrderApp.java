package me.ethan.hellospring;

import me.ethan.hellospring.member.Grade;
import me.ethan.hellospring.member.Member;
import me.ethan.hellospring.member.MemberService;
import me.ethan.hellospring.member.MemberServiceImpl;
import me.ethan.hellospring.order.Order;
import me.ethan.hellospring.order.OrderService;
import me.ethan.hellospring.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        CoreApplicationConfig coreApplicationConfig =new CoreApplicationConfig();
        MemberService memberService = coreApplicationConfig.memberService();
        OrderService orderService = coreApplicationConfig.orderService();

        Long memberId = 1L;
        Member memberA = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(memberA);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
        System.out.println("order.calculatePrice() = " + order.calculatePrice());
    }
}
