package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    /*private DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

/*//    @PersistenceContext -> 스펙에서는 생성자 Autowired 안하고 PersistanceContext 사용
    private EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository); // spring data jpa로 db 저장소 연결 사용
    }

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();  // db 없는 상태에서 memory로 저장소 사용
//        return new JdbcMemberRepository(dataSource);  // JDBC로 db 저장소 연결 사용
//        return new JdbcTemplateMemberRepository(dataSource);  // JdbcTemplate으로 db 저장소 연결 사용
//        return new JpaMemberRepository(em);   // JPA로 db 저장소 연결 사용
//    }

}
