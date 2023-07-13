package nou.hello.boot.spring5boot.dao;

import nou.hello.boot.spring5boot.model.Board;

import java.util.List;
import java.util.Map;

public interface BoardDAO {
    int insertBoard(Board b);
    List<Board> selectBoard(int stnum);
    Board selectOneBoard(String bno);
    int deleteOneBoard(String bno);
    int countAllBoard();
    List<Board> findBoard(Map<String, Object> params);
}
