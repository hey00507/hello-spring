package me.ethan.hellospring.order;

import me.ethan.hellospring.member.Grade;
import me.ethan.hellospring.member.Member;
import me.ethan.hellospring.member.MemberService;
import me.ethan.hellospring.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService =new OrderServiceImpl();

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member =new Member( memberId,"memberA", Grade.VIP);
        memberService.join(member);


        Order order = orderService.createOrder(memberId, "itemA",10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}