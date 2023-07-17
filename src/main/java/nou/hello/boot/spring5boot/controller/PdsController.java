package nou.hello.boot.spring5boot.controller;

import lombok.RequiredArgsConstructor;
import nou.hello.boot.spring5boot.model.Board;
import nou.hello.boot.spring5boot.model.Pds;
import nou.hello.boot.spring5boot.model.PdsAttach;
import nou.hello.boot.spring5boot.service.PdsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequestMapping("/pds")
@RequiredArgsConstructor
public class PdsController {
    Logger logger= LogManager.getLogger(IndexController.class);

    final PdsService psrv;

    @GetMapping("/list/{cpg}")
    public String list(Model m, @PathVariable Integer cpg) {
        logger.info("pds list 호출!");
        m.addAttribute("pds", psrv.readPds(cpg));
        m.addAttribute("cpg", cpg);
        m.addAttribute("cntpg", psrv.countAllPds());
        m.addAttribute("stpg", ((cpg-1)/10)*10+1);

        if(cpg > (int)m.getAttribute("cntpg")) {
            return "redirect:/pds/list/1";
        }
        return "/pds/list";
    }

    @GetMapping("/view/{pno}")
    public String view(Model m, @PathVariable String pno) {
        logger.info("pds view 호출!");
        m.addAttribute("view", psrv.readOnePds(pno));
        return "/pds/view";
    }

    @GetMapping("/write")
    public String write() {
        logger.info("pds write 호출!");
        return "pds/write";
    }

    @PostMapping("/write")
    public String writeok(Pds p, MultipartFile attach) {
        logger.info("pds write 호출!");
        String viewPage = "redirect:/pds/fail";

        // 1 게시물을 먼저 DB에 저장한 뒤 번호(pno)를 알아내기
        int pno=psrv.newPds(p);

        // 2 번호(pno)를 이용해서 첨부파일을 DB에 저장 및 업로드
        if(!attach.isEmpty()) {
            psrv.newPdsAttach(attach, pno);
            viewPage="redirect:/pds/list/1";
        }

        return viewPage;
    }


}
