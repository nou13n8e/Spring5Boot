package nou.hello.boot.spring5boot;

import nou.hello.boot.spring5boot.dao.MemberDAO;
import nou.hello.boot.spring5boot.dao.MemberDAOImpl;
import nou.hello.boot.spring5boot.model.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(MemberDAOImpl.class)
public class MemberDAOUnitTest {
    @Autowired
    private MemberDAO mdao;

    @Test
    @DisplayName("MemberDAO insert Test")
    void insertMember() {
        Member m=new Member(null, "", "", "", "",
                "", "", "", "", null);
        int result=mdao.insertMember(m);
        System.out.println(result);
        assertEquals(result, 1);
    }
}
