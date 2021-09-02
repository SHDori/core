package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired //생성자가 하나면 @Autowired를 생략할 수 있다.
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);            // 회원정보 조회를 먼저 하고
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인정책에 그냥 넘기고
        // -> OrderServic입장에서는 할인에대해선 모르겠다 너가 알아서 할인된금액을 넘겨라 라는 방식
        // 이게 바로 단일 책임 원칙을 잘 지킨것이다.
        return new Order(memberId, itemName, itemPrice,discountPrice);


    }

    // 싱글톤 테스트용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
