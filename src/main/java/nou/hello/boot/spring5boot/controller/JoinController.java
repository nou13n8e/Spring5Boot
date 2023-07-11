package nou.hello.boot.spring5boot.controller;

import nou.hello.boot.spring5boot.model.Check;
import nou.hello.boot.spring5boot.model.Member;
import nou.hello.boot.spring5boot.service.MemberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/join")
public class JoinController {

    @Autowired
    MemberService msrv;

    Logger logger= LogManager.getLogger(IndexController.class);
    @GetMapping("/agree")
    public String agree() {
        logger.info("join agree 호출!");
        return "/join/agree";
    }
    @GetMapping("/check")
    public String check() {
        logger.info("join check 호출!");
        return "/join/check";
    }
    @PostMapping("/check")
    public String checkok(Check check, HttpSession sess) {
        logger.info("join checkok 호출!");
        // check에서 보낸 개인정보를 세션에 저장하고 join으로 이동
        String viewPage="redirect:/join/check";

        if(check.getName2() != null) {
            sess.setAttribute("check", check);
            viewPage="redirect:/join/join";
        }
        return viewPage;
    }

    @GetMapping("/join")
    public String join(HttpSession sess) {
        logger.info("join join 호출!");
        
        // 세션변수가 없다면 agree로 이동
        if(sess.getAttribute("check")==null) return "redirect:/join/agree";
        
        return "/join/join";
    }
    @PostMapping("/join")
    public String joinme(Member m) {
        logger.info("join joinme 호출!");

        String viewPage="redirect:/join/joinfail";

        if(msrv.saveMember(m)) {
            viewPage="redirect:/join/joinok";
        }
        return viewPage;
    }

    @GetMapping("/joinok")
    public String joinok() {
        logger.info("join joinok 호출!");
        return "/join/joinok";
    }

    @GetMapping("/zipcode")
    @ResponseBody
    // ResponseBody는 클라이언트에 뷰 없이 응답 가능
    public void findzip(String dong, HttpServletResponse res) throws IOException {
        res.setContentType("application/json; charset=utf-8");
        res.getWriter().print(msrv.findzip(dong));
        // 우편번호 조회 결과를 json 형식으로 보내고, 따라서 응답유형을 json으로 지정
        // 검색된 결과를 뷰 없이 바로 응답(Response)으로 출력 가능

        // 우편번호 검색
        // /join/zipcode?dong=동이름
        // 검색결과는 뷰페이지 없이 바로 응답으로 출력 : RESTful 방식
        // 서블릿에서 제공하는 HttpServletResponse 객체를 이용하면
        // 스프링의 뷰리졸버 없이 바로 응답으로 출력할 수 있음
        // 단, 응답유형은 JSON 형식으로 함
    }


    // 아이디 중복 확인
    // join/checkuid?uid=아이디
    // join/checkuid/아이디
    @GetMapping("/checkuid/{uid}")
    @ResponseBody
    // ResponseBody는 클라이언트에 뷰 없이 응답 가능
    public void checkuid(@PathVariable String uid, HttpServletResponse res) throws IOException {
        res.setContentType("application/json; charset=utf-8");
        res.getWriter().print(msrv.checkuid(uid));
    }

}
