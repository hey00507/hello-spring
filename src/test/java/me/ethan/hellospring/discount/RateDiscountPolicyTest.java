package me.ethan.hellospring.discount;

import me.ethan.hellospring.member.Grade;
import me.ethan.hellospring.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 는 10% 의 할인이 적용되어야 한다.")
    void vipDiscount10Percent(){

        Member member = new Member(1L, "memberVIP", Grade.VIP);

        int discount = discountPolicy.discount(member, 10000);

        Assertions.assertThat(discount).isEqualTo(1000);
    }


    @Test
    @DisplayName("VIP 가 아닐 경우, 할인 적용이 되지 않는다.")
    void noDiscountWithBasic(){
        Member member = new Member(1L, "memberBASIC", Grade.BASIC);

        int discount = discountPolicy.discount(member, 10000);

        Assertions.assertThat(discount).isEqualTo(0);

    }
}