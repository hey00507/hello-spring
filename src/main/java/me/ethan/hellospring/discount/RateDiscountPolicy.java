package me.ethan.hellospring.discount;

import me.ethan.hellospring.member.Grade;
import me.ethan.hellospring.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {

    private static final int DISCOUNT_PERCENT = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price* DISCOUNT_PERCENT / 100;
        }
        return 0;
    }
}
