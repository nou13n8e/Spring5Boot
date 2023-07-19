package nou.hello.boot.spring5boot.mybatis;

import nou.hello.boot.spring5boot.model.Pds;
import nou.hello.boot.spring5boot.model.PdsAttach;
import nou.hello.boot.spring5boot.model.PdsComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PdsMapper {
    int insertPds(Pds p);
    int lastPdsPno();
    int insertPdsAttach(PdsAttach pa);
    List<Pds> selectPds(int stnum);
    Pds selectOnePds(String pno);
    int countAllPds();
    int viewsUp(String pno);
    PdsAttach selectOnePdsAttach(String pno);
    int insertPdsComment(PdsComment pc);
    List<PdsComment> selectPdsComment(String pno);

    int insertPdsReply(PdsComment pc);


//    int deleteOnePds(String bno);


//    List<Pds> findPds(Map<String, Object> params);
//    int countFindPds(Map<String, Object> params);
}
