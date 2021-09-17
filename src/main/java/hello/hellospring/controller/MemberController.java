package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;  // = new MemberService();

    // MemberService를 생성하지 않고 생성자에 @Autowired로 스프링 컨테이너에 등록해서 사용한다.
    // @Autowired는 컨테이너에 등록된 MemberService를 가져온다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
