package nou.hello.boot.spring5boot.mybatis;

import nou.hello.boot.spring5boot.model.Pds;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PdsMapper {
    int insertPds(Pds p);

    int lastPdsPno();


//    List<Pds> selectPds(int stnum);
//    Pds selectOnePds(String bno);
//    int deleteOnePds(String bno);
//    int viewsUp(String bno);
//    int countAllPds();
//    List<Pds> findPds(Map<String, Object> params);
//    int countFindPds(Map<String, Object> params);
}
