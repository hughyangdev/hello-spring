package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // JpaRepository를 상속받으면 스프링 데이터 jpa가 구현체를 만들어서 스프링빈을 등록해준다.

    // JPQL -> select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
