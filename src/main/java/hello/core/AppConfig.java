package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;


//AppConfig의 등장으로 사용영역과 구성영역을 나눔
// 사용영역은 수정필요 없고 구성영역의 수정만으로 가능
public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
        //생성자에 들어감 -> 생성자 주입
        //memberRepository() 중복 제거함

    }

    private static MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
        //구성영역의 수정만으로 가능

    }

    //나중에 수정에 용이
    //구성이 어떻게 되어있나 한 눈에 보기 편함
}
