package nou.hello.boot.spring5boot.service;

import nou.hello.boot.spring5boot.model.Board;

import java.util.List;

public interface BoardService {
    boolean saveBoard(Board b);
    List<Board> readBoard(int cpg);
    Board readOneBoard(String bno);

}
