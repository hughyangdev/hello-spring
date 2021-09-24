package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; // jpa가 EntityManager를 생성해준다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);  // find(조회타입, 식별자 PK)
        return Optional.ofNullable(member);
    }

    // PK 기반 검색이 아니면 jpql 기반으로 쿼리 작성 필요
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        // jpql 쿼리 언어 -> select m from Member m,
        // 객체(엔티티)를 대상으로 쿼리를 보내면 sql로 번역이 된다. (보통은 테이블 대상으로 쿼리를 보냄)
    }
}
