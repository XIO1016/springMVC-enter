package xio.springMVCenter.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xio.springMVCenter.domain.Member;
import xio.springMVCenter.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService= new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member member1 = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(member1.getName());
    }

    @Test
    public void duplicateJoin() {
        //given
        Member member2 = new Member();
        member2.setName("spring");

        Member member3 = new Member();
        member3.setName("spring");

        //when
        memberService.join(member2);
        assertThrows(IllegalStateException.class,() -> memberService.join(member3));

//        try {
//            memberService.join(member3);
//            fail("예외가 발생해야 합니다.");
//        }catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {
    }
}