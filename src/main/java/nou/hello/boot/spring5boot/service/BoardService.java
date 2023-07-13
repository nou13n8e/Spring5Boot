package nou.hello.boot.spring5boot.service;

import nou.hello.boot.spring5boot.model.Board;

import java.util.List;
import java.util.Map;

public interface BoardService {
    boolean saveBoard(Board b);
    List<Board> readBoard(Integer cpg);
    Board readOneBoard(String bno);
    boolean removeOneBoard(String bno);
    int countAllBoard();
    List<Board> findBoard(Integer cpg, String findtype, String findkey);
}
