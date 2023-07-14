package nou.hello.boot.spring5boot.utils;

import nou.hello.boot.spring5boot.controller.IndexController;
import nou.hello.boot.spring5boot.model.PdsAttach;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.util.resources.LocaleData;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

@Component
public class PdsUtils {

    Logger logger= LogManager.getLogger(PdsUtils.class);

    // 첨부파일 저장 위치 설정
    @Value("${saveDir}") private String saveDir;

    public PdsAttach processUpload(MultipartFile attach) {
        PdsAttach pa=new PdsAttach();

        // 업로드 할 파일에 대한 정보 알아내기 (파일명)
        pa.setFname(makeUUId() + attach.getOriginalFilename());

        // 업로드 할 파일에 대한 정보 알아내기 (확장자)
        // 방법 1 : 파일명.확장자 -> 파일명.split(".")[1]
        // 방법 2 : 파일명.파일명.확장자 -> 파일명.subString(lastIndexOf(".") + 1)
        int pos=pa.getFname().lastIndexOf(".") + 1;
        pa.setFtype(pa.getFname().substring(pos));

        // 업로드 할 파일에 대한 정보 알아내기 (크기)
        pa.setFsize(attach.getSize()/1024 + "");

        // 첨부파일 지정한 위치에 저장
        String savepath = saveDir + pa.getFname();
        try {
            attach.transferTo(new File(savepath));
        } catch (IOException e) {
            logger.error("첨부파일을 처리하는 중 오류 발생!");
            e.printStackTrace();
        }

        return pa;
    }

    private String makeUUId() {
        String uuid= LocalDate.now() + "" + LocalTime.now();
        uuid=uuid.replace("-","")
                .replace(":","")
                .replace(".","");
        return uuid;
    }
}
