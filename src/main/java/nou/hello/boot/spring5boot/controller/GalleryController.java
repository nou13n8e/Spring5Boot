package nou.hello.boot.spring5boot.controller;

import lombok.RequiredArgsConstructor;
import nou.hello.boot.spring5boot.service.GalleryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gallery")
@RequiredArgsConstructor
public class GalleryController {

    final GalleryService gsrv;

    Logger logger= LogManager.getLogger(IndexController.class);
    @GetMapping("/list/{cpg}")
    public String list(Model m, @PathVariable Integer cpg) {
        logger.info("gallery list 호출!");
        m.addAttribute("gal", gsrv.readGallery(cpg));
        m.addAttribute("cpg", cpg);
        m.addAttribute("cntpg", gsrv.countGallery());
        m.addAttribute("stpg", ((cpg-1)/10)*10+1);

        if(cpg > (int)m.getAttribute("cntpg")) {
            return "redirect:/gallery/list/1";
        }
        return "/gallery/list";
    }
}
