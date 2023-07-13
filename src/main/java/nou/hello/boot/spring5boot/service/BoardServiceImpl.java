package nou.hello.boot.spring5boot.service;

import nou.hello.boot.spring5boot.dao.BoardDAO;
import nou.hello.boot.spring5boot.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Board> readBoard(Integer cpg) {
        int stnum=(cpg-1)*25;
        return bdao.selectBoard(stnum);
    }

    @Override
    public Board readOneBoard(String bno) {
        return bdao.selectOneBoard(bno);
    }

    @Override
    public boolean removeOneBoard(String bno) {
        boolean isRemoved=false;
        if(bdao.deleteOneBoard(bno) > 0) isRemoved=true;
        return isRemoved;
    }

    @Override
    public int countAllBoard() {
        return bdao.countAllBoard();
    }

    @Override
    public List<Board> findBoard(Integer cpg, String findtype, String findkey) {
        Map<String, Object> params = new HashMap<>();
        params.put("findtype", findtype);
        params.put("findkey", findkey);
        params.put("stnum", (cpg-1) * 25);
        return bdao.findBoard(params);
    }
}
