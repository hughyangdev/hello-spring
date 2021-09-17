package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;    // = new MemberService();
    // clear를 해줘야하는데 service만 있는 상태 -> MemoryMemberRepository를 생성하여 clearStroe 사용
    MemoryMemberRepository memberRepository;    // = new MemoryMemberRepository();
    // 테스트에서 memberRepository는 service의 memberRepository와 다른 객체
    // -> 현재는 MemoryMemberRepository의 store가 static이라 괜찮지만 static이 아닌 경우 문제 발생 가능
    // service에 생성자를 만들어서 외부에서 MemberRepository를 넣어주게 해서 동일한 객체를 사용하게 한다. @BeforeEach로 객체 생성 및 주입
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    // 각 test가 끝난 후 저장소를 비워주기 위해 작성
    // MemoryMemberRepository 에 clearStore() 작성
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        /*try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then
    }

    @Test
    void findMembers() {
        Member member1 = new Member();
        member1.setName("spring1");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberService.join(member2);

        List<Member> result = memberService.findMembers();

        assertThat(result.size()).isEqualTo(2);

    }

    @Test
    void findOne() {
    }
}