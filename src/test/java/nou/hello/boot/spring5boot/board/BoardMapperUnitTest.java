package nou.hello.boot.spring5boot.board;

import nou.hello.boot.spring5boot.model.Board;
import nou.hello.boot.spring5boot.mybatis.BoardMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BoardMapperUnitTest {
    @Autowired
    private BoardMapper boardMapper;

    @Test
    @DisplayName("BoardMapper insert Test")
    @Transactional
    void insertBoard() {
        Board b=new Board(null, "", "abc123", null, "", "", "", "");
        int result=boardMapper.insertBoard(b);
        System.out.println(result);
        assertEquals(result,1);
    }

    @Test
    @DisplayName("BoardMapper selectBoard Test")
    void selectBoard() {
        int cpg=1;
        int stnum=(cpg-1)*25;
        List<Board> results=boardMapper.selectBoard(stnum);
        System.out.println(results);
        assertNotNull(results);
    }

    @Test
    @DisplayName("BoardMapper selectOneBoard Test")
    void selectOneBoard() {
        String bno="300";
        Board result=boardMapper.selectOneBoard(bno);
        System.out.println(result);
        assertNotNull(result);
    }

    @Test
    @DisplayName("BoardMapper deleteOneBoard Test")
    @Transactional
    void deleteOneBoard() {
        String bno="1088";
        int result=boardMapper.deleteOneBoard(bno);
        System.out.println(result);
        assertEquals(result, 1);
    }

    @Test
    @DisplayName("BoardMapper viewsUp Test")
    @Transactional
    void viewsUp() {
        String bno="1088";
        int result=boardMapper.viewsUp(bno);
        assertEquals(result, 1);
    }
}
