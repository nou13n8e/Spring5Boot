package nou.hello.boot.spring5boot.controller;

import lombok.RequiredArgsConstructor;
import nou.hello.boot.spring5boot.service.BoardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    final BoardService bsrv;

    Logger logger= LogManager.getLogger(IndexController.class);
    @GetMapping("/list/{cpg}")
    public String list(Model m, @PathVariable Integer cpg) {
        logger.info("board list 호출!");
        m.addAttribute("board", bsrv.readBoard(cpg));
        m.addAttribute("cpg", cpg);
        return "/board/list";
    }

    @GetMapping("/view/{bno}")
    public String view(Model m, @PathVariable String bno) {
        logger.info("board view 호출!");
        m.addAttribute("view", bsrv.readOneBoard(bno));
        return "/board/view";
    }

    @GetMapping("/write")
    public String write() {
        logger.info("board write 호출!");
        return "/board/write";
    }
}
