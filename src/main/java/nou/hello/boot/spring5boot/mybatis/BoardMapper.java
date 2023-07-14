package nou.hello.boot.spring5boot.mybatis;

import nou.hello.boot.spring5boot.model.Board;
import nou.hello.boot.spring5boot.model.Member;
import nou.hello.boot.spring5boot.model.Zipcode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface BoardMapper {
    int insertBoard(Board b);
    List<Board> selectBoard(int stnum);
    Board selectOneBoard(String bno);
    int deleteOneBoard(String bno);
    int viewsUp(String bno);
    int countAllBoard();
    List<Board> findBoard(Map<String, Object> params);
    int countFindBoard(Map<String, Object> params);
}
