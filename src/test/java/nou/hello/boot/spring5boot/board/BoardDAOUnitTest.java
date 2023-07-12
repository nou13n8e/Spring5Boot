package nou.hello.boot.spring5boot.board;

import nou.hello.boot.spring5boot.dao.BoardDAO;
import nou.hello.boot.spring5boot.dao.BoardDAOImpl;
import nou.hello.boot.spring5boot.model.Board;
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
@Import(BoardDAOImpl.class)
public class BoardDAOUnitTest {
    @Autowired
    private BoardDAO bdao;

    @Test
    @DisplayName("BoardDAO insert Test")
    void insertBoard() {
        Board b=new Board(null, "", "abc123", null, "" , "", "", "");
        int result=bdao.insertBoard(b);
        System.out.println(result);
        assertEquals(result, 1);
    }

    @Test
    @DisplayName("BoardDAO select Test")
    void selectBoard() {
        int stnum=1;
        List<Board> results=bdao.selectBoard(stnum);
        System.out.println(results);
        assertNotNull(results);
    }

    @Test
    @DisplayName("BoardDAO selectOneBoard Test")
    void selectOneBoard() {
        String bno="300";
        Board result=bdao.selectOneBoard(bno);
        System.out.println(result);
        assertNotNull(result);
    }
}