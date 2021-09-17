package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 허용 안함
        // null 가능성이 있으면 Optional로 감싸서 반환해주며 이로 인핸 ifPresent 사용 가능
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> { // ifPresent: 값이 있다면, m: member, optional이라서 null 확인 안함
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        // Optional<Member> 반환을 result로 받지 않고 바로 메서드 실행으로 코드 정리
        // 메서드로 별도 분리 refatoring 진행
        //  -> 영역 선택 후 ctrl+alt+shift+T 에서 extract method || ctrl+alt+m 으로 메서드 분리 생성  
        validateDuplicateMember(member);    // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
