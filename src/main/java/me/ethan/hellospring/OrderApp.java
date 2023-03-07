package me.ethan.hellospring;

import me.ethan.hellospring.config.CoreApplicationConfig;
import me.ethan.hellospring.member.Grade;
import me.ethan.hellospring.member.Member;
import me.ethan.hellospring.member.MemberService;
import me.ethan.hellospring.order.Order;
import me.ethan.hellospring.order.OrderService;

public class OrderApp {
    public static void main(String[] args) {
        CoreApplicationConfig coreApplicationConfig =new CoreApplicationConfig();
        MemberService memberService = coreApplicationConfig.memberService();
        OrderService orderService = coreApplicationConfig.orderService();

        Long memberId = 1L;
        Member memberA = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(memberA);

        Order order = orderService.createOrder(memberId, "itemA", 100000);

        System.out.println("order = " + order);
        System.out.println("order.calculatePrice() = " + order.calculatePrice());
    }
}
