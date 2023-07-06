package nou.hello.boot.spring5boot.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join")
public class JoinController {

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
    @GetMapping("/join")
    public String join() {
        logger.info("join join 호출!");
        return "/join/join";
    }
    @GetMapping("/joinok")
    public String joinok() {
        logger.info("join joinok 호출!");
        return "/join/joinok";
    }
}
