package nou.hello.boot.spring5boot.pds;

import nou.hello.boot.spring5boot.dao.BoardDAO;
import nou.hello.boot.spring5boot.dao.BoardDAOImpl;
import nou.hello.boot.spring5boot.dao.PdsDAO;
import nou.hello.boot.spring5boot.dao.PdsDAOImpl;
import nou.hello.boot.spring5boot.model.Board;
import nou.hello.boot.spring5boot.model.Pds;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PdsDAOImpl.class)
public class PdsDAOUnitTest {
    @Autowired
    private PdsDAO pdao;

    @Test
    @DisplayName("PdsDAO select Test")
    void selectPds() {
        int cpg=1;
        int stnum=(cpg-1)*25;
        List<Pds> results=pdao.selectPds(stnum);
        assertNotNull(results);
    }

    @Test
    @DisplayName("PdsDAO select Test")
    void selectOnePds() {
        String pno="8";
        Pds results=pdao.selectOnePds(pno);
        assertNotNull(results);
    }

    @Test
    @DisplayName("PdsDAO countAllPds Test")
    void countAllPds() {
        int result=pdao.countAllPds();
        assertNotNull(result);
    }

}
