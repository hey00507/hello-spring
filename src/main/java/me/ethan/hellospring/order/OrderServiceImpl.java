package me.ethan.hellospring.order;

import lombok.RequiredArgsConstructor;
import me.ethan.hellospring.discount.DiscountPolicy;
import me.ethan.hellospring.member.Member;
import me.ethan.hellospring.member.MemberRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    /**
     * 싱글턴 테스트용 코드
     */
    public MemberRepository getMemberRepository(){
        return this.memberRepository;
    }
}
