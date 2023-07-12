package nou.hello.boot.spring5boot.board;

import nou.hello.boot.spring5boot.dao.BoardDAO;
import nou.hello.boot.spring5boot.dao.BoardDAOImpl;
import nou.hello.boot.spring5boot.model.Board;
import nou.hello.boot.spring5boot.service.BoardService;
import nou.hello.boot.spring5boot.service.BoardServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({BoardServiceImpl.class, BoardDAOImpl.class})
public class BoardServiceUnitTest {
    @Autowired
    private BoardService bsrv;

    @Test
    @DisplayName("BoardService save Test")
    void saveBoard() {
        Board b=new Board(null, "", "abc123", null, "" , "", "", "");
        boolean result=bsrv.saveBoard(b);
        System.out.println(result);
        assertEquals(result, true);
    }

    @Test
    @DisplayName("BoardService read Test")
    void readBoard() {
        int cpg=1;
        List<Board> results=bsrv.readBoard(cpg);
        System.out.println(results);
        assertNotNull(results);
    }

    @Test
    @DisplayName("BoardService readOneBoard Test")
    void readOneBoard() {
        String bno="300";
        Board result=bsrv.readOneBoard(bno);
        System.out.println(result);
        assertNotNull(result);
    }
}
