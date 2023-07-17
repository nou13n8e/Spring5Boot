package nou.hello.boot.spring5boot.pds;

import nou.hello.boot.spring5boot.dao.BoardDAOImpl;
import nou.hello.boot.spring5boot.dao.PdsDAOImpl;
import nou.hello.boot.spring5boot.model.Board;
import nou.hello.boot.spring5boot.model.Pds;
import nou.hello.boot.spring5boot.service.BoardService;
import nou.hello.boot.spring5boot.service.BoardServiceImpl;
import nou.hello.boot.spring5boot.service.PdsService;
import nou.hello.boot.spring5boot.service.PdsServiceImpl;
import nou.hello.boot.spring5boot.utils.PdsUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({PdsServiceImpl.class, PdsDAOImpl.class, PdsUtils.class})
public class PdsServiceUnitTest {
    @Autowired
    private PdsService psrv;


    @Test
    @DisplayName("PdsService readPds Test")
    void readPds() {
        int cpg=1;
        List<Pds> results=psrv.readPds(cpg);
        assertNotNull(results);
    }

    @Test
    @DisplayName("PdsService readOnePds Test")
    void readOnePds() {
        String pno="8";
        Pds result=psrv.readOnePds(pno);
        assertNotNull(result);
    }

    @Test
    @DisplayName("PdsService countAllPds Test")
    void countAllPds() {
        int result=psrv.countAllPds();
        assertNotNull(result);
    }

}
