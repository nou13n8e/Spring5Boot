package nou.hello.boot.spring5boot.dao;

import nou.hello.boot.spring5boot.model.Pds;
import nou.hello.boot.spring5boot.model.PdsAttach;
import nou.hello.boot.spring5boot.mybatis.PdsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("pdao")
public class PdsDAOImpl implements PdsDAO {

    @Autowired
    private PdsMapper pdsMapper;

    @Override
    public int insertPds(Pds p) {
        // 1 pds 테이블에 저장하는 기능
        int cnt=pdsMapper.insertPds(p);
        // 2 저장된 것 중 pno를 추출하는 기능
        if(cnt > 0) {
            cnt=pdsMapper.lastPdsPno();
        }
        return cnt;
    }

    @Override
    public int insertPdsAttach(PdsAttach pa) {
        return pdsMapper.insertPdsAttach(pa);
    }

    @Override
    public List<Pds> selectPds(int stnum) {
        return pdsMapper.selectPds(stnum);
    }

    @Override
    public int countAllPds() {
        return pdsMapper.countAllPds();
    }

    @Override
    public Pds selectOnePds(String pno) {
        pdsMapper.viewsUp(pno);
        return pdsMapper.selectOnePds(pno);
    }

    @Override
    public PdsAttach selectOnePdsAttach(String pno) {
        return pdsMapper.selectOnePdsAttach(pno);
    }
}
