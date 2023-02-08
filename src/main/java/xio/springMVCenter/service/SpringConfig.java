package xio.springMVCenter.service;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import xio.springMVCenter.aop.TimeTraceAop;
import xio.springMVCenter.repository.JpaMemberRepository;
import xio.springMVCenter.repository.MemberRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }


}