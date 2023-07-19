package nou.hello.boot.spring5boot.controller;

import lombok.RequiredArgsConstructor;
import nou.hello.boot.spring5boot.model.Board;
import nou.hello.boot.spring5boot.model.Pds;
import nou.hello.boot.spring5boot.model.PdsAttach;
import nou.hello.boot.spring5boot.model.PdsComment;
import nou.hello.boot.spring5boot.service.PdsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
        m.addAttribute("view", psrv.readOnePds(pno)); //본문
        m.addAttribute("pcs", psrv.readPdsComment(pno)); //댓글+대댓글
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
    @GetMapping("/down/{pno}")
    public ResponseEntity<Resource> down(@PathVariable String pno) {
        logger.info("pds down 호출!");

        // 1 업로드한 파일에 대한 파일명 알아내기
        String fname=psrv.readOnePdsAttach(pno);

        // 2 알아낸 파일명을 이용해 헤더와 리소스 객체 생성
        Map<String, Object> obj=psrv.getHeaderResource(fname);

        return ResponseEntity.ok()
                .headers((HttpHeaders)obj.get("header"))
                .body((UrlResource)obj.get("resource"));
    }

    @PostMapping("/cmt/write")
    public String cmtwrtok(PdsComment pc) {
        logger.info("pds cmt wrt 호출!");
        String viewPage = "redirect:/pds/fail";

        // 1 댓글을 먼저 DB에 저장
        if(psrv.newPdsComment(pc)){
            // 작성한 댓글을 확인하기 위해 바로 본문 출력
            viewPage = "redirect:/pds/view/" + pc.getPno();
        }
        return viewPage;
    }

    @PostMapping("/reply/write")
    public String rpywrtok(PdsComment pc) {
        logger.info("pds rpy wrt 호출!");
        String viewPage = "redirect:/pds/fail";

        // 1 대댓글을 먼저 DB에 저장
        if(psrv.newPdsReply(pc)){
            // 작성한 대댓글을 확인하기 위해 바로 본문 출력
            viewPage = "redirect:/pds/view/" + pc.getPno();
        }
        return viewPage;
    }
}
