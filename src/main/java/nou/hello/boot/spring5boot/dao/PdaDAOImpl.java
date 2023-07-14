package nou.hello.boot.spring5boot.dao;

import nou.hello.boot.spring5boot.model.Pds;
import nou.hello.boot.spring5boot.mybatis.PdsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("pdao")
public class PdaDAOImpl implements PdsDAO {

    @Autowired
    private PdsMapper pdsMapper;

    @Override
    public int insertPds(Pds p) {
        // Pds 테이블에 저장하는 기능
        int cnt=pdsMapper.insertPds(p);
        if(cnt > 0) {
            cnt=pdsMapper.lastPdsPno();
        }
        return cnt;
    }
}
