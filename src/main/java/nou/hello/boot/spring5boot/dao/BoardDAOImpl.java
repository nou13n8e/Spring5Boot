package nou.hello.boot.spring5boot.dao;

import lombok.RequiredArgsConstructor;
import nou.hello.boot.spring5boot.model.Board;
import nou.hello.boot.spring5boot.mybatis.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BoardDAOImpl implements BoardDAO {

    @Autowired
    final BoardMapper boardMapper;

    @Override
    public int insertBoard(Board b) {
        return boardMapper.insertBoard(b);
    }

    @Override
    public List<Board> selectBoard(int stnum) {
        return boardMapper.selectBoard(stnum);
    }

    @Override
    public Board selectOneBoard(String bno) {
        boardMapper.viewsUp(bno);
        return boardMapper.selectOneBoard(bno);
    }

    @Override
    public int deleteOneBoard(String bno) {
        return boardMapper.deleteOneBoard(bno);
    }

    @Override
    public int countAllBoard() {
        return boardMapper.countAllBoard();
    }

    @Override
    public List<Board> findBoard(Map<String, Object> params) {
        return boardMapper.findBoard(params);
    }

}
