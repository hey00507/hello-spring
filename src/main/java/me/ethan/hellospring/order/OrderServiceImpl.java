package me.ethan.hellospring.order;

import me.ethan.hellospring.discount.DiscountPolicy;
import me.ethan.hellospring.discount.FixDiscountPolicy;
import me.ethan.hellospring.discount.RateDiscountPolicy;
import me.ethan.hellospring.member.Member;
import me.ethan.hellospring.member.MemberRepository;
import me.ethan.hellospring.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
