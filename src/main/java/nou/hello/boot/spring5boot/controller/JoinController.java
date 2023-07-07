package nou.hello.boot.spring5boot.controller;

import nou.hello.boot.spring5boot.model.Check;
import nou.hello.boot.spring5boot.model.Member;
import nou.hello.boot.spring5boot.service.MemberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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
    public String join() {
        logger.info("join join 호출!");
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
}
