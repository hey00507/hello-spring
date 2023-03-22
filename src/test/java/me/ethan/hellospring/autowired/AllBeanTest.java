package me.ethan.hellospring.autowired;

import me.ethan.hellospring.AutoCoreApplicationConfig;
import me.ethan.hellospring.discount.DiscountPolicy;
import me.ethan.hellospring.member.Grade;
import me.ethan.hellospring.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoCoreApplicationConfig.class ,DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice= discountService.discount(member,10000, "fixDiscountPolicy");


        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member,20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);

        int discountPriceWithPolicyList = discountService.discountWithPolicyList(member,10000, "FixDiscountPolicy");
        assertThat(discountPriceWithPolicyList).isEqualTo(1000);

        int rateDiscountPriceWithPolicyList = discountService.discountWithPolicyList(member,20000, "RateDiscountPolicy");
        assertThat(rateDiscountPriceWithPolicyList).isEqualTo(2000);

    }

    static class DiscountService{
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member,price);
        }

        public int discountWithPolicyList(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policies.stream()
                    .filter(policy -> policy.getClass().getSimpleName().equals(discountCode))
                    .findFirst()
                    .orElse(null);
            return discountPolicy.discount(member,price);
        }
    }

}
