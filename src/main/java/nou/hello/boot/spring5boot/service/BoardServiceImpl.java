package nou.hello.boot.spring5boot.service;

import nou.hello.boot.spring5boot.dao.BoardDAO;
import nou.hello.boot.spring5boot.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bsrv")
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDAO bdao;

    @Override
    public boolean saveBoard(Board b) {
        boolean isSaved=false;
        if(bdao.insertBoard(b) > 0) isSaved=true;
        return isSaved;
    }

    @Override
    public List<Board> readBoard(int cpg) {
        int stnum=(cpg-1)*25;
        return bdao.selectBoard(stnum);
    }

    @Override
    public Board readOneBoard(String bno) {
        return bdao.selectOneBoard(bno);
    }
}