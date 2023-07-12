package nou.hello.boot.spring5boot.controller;

import lombok.RequiredArgsConstructor;
import nou.hello.boot.spring5boot.service.BoardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    final BoardService bsrv;

    Logger logger= LogManager.getLogger(IndexController.class);
    @GetMapping("/list")
    public String list(Model m, int cpg) {
        logger.info("board list 호출!");
        m.addAttribute("board", bsrv.readBoard(cpg));
        return "/board/list";
    }
}
