package me.ethan.hellospring.order;

import me.ethan.hellospring.config.CoreApplicationConfig;
import me.ethan.hellospring.member.Grade;
import me.ethan.hellospring.member.Member;
import me.ethan.hellospring.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderServiceTest {
    MemberService memberService;

    OrderService orderService;

    @BeforeEach
    public void dependencyInjection(){
        CoreApplicationConfig coreApplicationConfig = new CoreApplicationConfig();
        memberService=coreApplicationConfig.memberService();
        orderService=coreApplicationConfig.orderService();
    }

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member =new Member( memberId,"memberA", Grade.VIP);
        memberService.join(member);


        Order order = orderService.createOrder(memberId, "itemA",10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}