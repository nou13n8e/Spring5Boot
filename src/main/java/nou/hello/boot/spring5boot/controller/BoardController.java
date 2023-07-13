package nou.hello.boot.spring5boot.controller;

import lombok.RequiredArgsConstructor;
import nou.hello.boot.spring5boot.model.Board;
import nou.hello.boot.spring5boot.service.BoardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    final BoardService bsrv;

    Logger logger = LogManager.getLogger(IndexController.class);

    @GetMapping("/list/{cpg}")
    public String list(Model m, @PathVariable Integer cpg) {
        logger.info("board list 호출!");
        m.addAttribute("board", bsrv.readBoard(cpg));
        m.addAttribute("cpg", cpg);
        m.addAttribute("cntpg", bsrv.countAllBoard());
        m.addAttribute("stpg", ((cpg-1)/10)*10+1);
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

    @PostMapping("/write")
    public String writeok(Board b) {
        logger.info("board write 호출!");
        String viewPage = "redirect:/board/fail";

        if (bsrv.saveBoard(b)) {
            viewPage = "redirect:/board/list/1";
        }
        return viewPage;
    }

    @GetMapping("/delete/{bno}")
    public String remove(@PathVariable String bno) {
        logger.info("board remove 호출!");
        String viewPage = "redirect:/board/fail";
        if (bsrv.removeOneBoard(bno)) {
            viewPage = "redirect:/board/list/1";
        }
        return viewPage;
    }
}