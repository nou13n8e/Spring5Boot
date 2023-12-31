package nou.hello.boot.spring5boot.service;

import lombok.RequiredArgsConstructor;
import nou.hello.boot.spring5boot.dao.PdsDAO;
import nou.hello.boot.spring5boot.model.Pds;
import nou.hello.boot.spring5boot.model.PdsAttach;
import nou.hello.boot.spring5boot.model.PdsComment;
import nou.hello.boot.spring5boot.utils.PdsUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("psrv")
@RequiredArgsConstructor
public class PdsServiceImpl implements PdsService {

    final PdsDAO pdao;
    final PdsUtils pdsUtils;

    @Override
    public int newPds(Pds p) {
        return pdao.insertPds(p);
    }
    @Override
    public boolean newPdsAttach(MultipartFile attach, int pno) {
        // 1 첨부파일을 지정한 위치에 저장 후 정보를 알아내기
        PdsAttach pa=pdsUtils.processUpload(attach);

        // pa.setPno(String.valueOf(pno)) 위와 같이 작성해도 되지만 단순하게 작성
        pa.setPno(pno + "");

        // 2 첨부파일에 대한 정보를 DB에 저장
        int pacnt=pdao.insertPdsAttach(pa);

        return (pacnt > 0) ? true : false;
    }
    @Override
    public List<Pds> readPds(Integer cpg) {
        int stnum=(cpg-1)*25;
        return pdao.selectPds(stnum);
    }
    @Override
    public int countAllPds() {
        return pdao.countAllPds();
    }
    @Override
    public Pds readOnePds(String pno) {
        return pdao.selectOnePds(pno);
    }
    @Override
    public String readOnePdsAttach(String pno) {

        PdsAttach pa=pdao.selectOnePdsAttach(pno);

        return pa.getFname();
    }
    @Override
    public Map<String, Object> getHeaderResource(String fname) {
        Map<String, Object> objs = new HashMap<>();
        // 다운로드할 파일의 헤더와 리소스를 알아오기
        objs.put("header", pdsUtils.getHeader(fname));
        objs.put("resource", pdsUtils.getResource(fname));
        return objs;
    }

    @Override
    public boolean newPdsComment(PdsComment pc) {
        return (pdao.insertPdsComment(pc) > 0) ? true : false;
    }

    @Override
    public List<PdsComment> readPdsComment(String pno) {
        return pdao.selectPdsComment(pno);
    }

    @Override
    public boolean newPdsReply(PdsComment pc) {
        return (pdao.insertPdsReply(pc) > 0) ? true : false;
    }
}
