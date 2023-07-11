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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(MemberDAOImpl.class)
public class MemberDAOUnitTest {
    @Autowired
    private MemberDAO mdao;

    @Test
    @DisplayName("MemberDAO insert Test")
    void insertMember() {
        Member m=new Member(null, "", "", "", "", "" ,"", "", "", "", null);
        int result=mdao.insertMember(m);
        System.out.println(result);
        assertEquals(result, 1);
    }
    @Test
    @DisplayName("MemberDAO select Test")
    void selectMember() {
        List<Member> result=mdao.selectMember();
    }
    @Test
    @DisplayName("MemberDAO checkUserid Test")
    void checkUserid() {
        String uid="abc123";
        int result=mdao.selectOneUserid(uid);

        System.out.println(result);
        assertEquals(result, 1);
    }
    @Test
    @DisplayName("MemberDAO selectOneMember Test")
    void selectOneMember() {
        Member m=new Member();
        m.setUserid("abc123");
        m.setPasswd("987xyz");
        Member result=mdao.selectOneMember(m);
        System.out.println(result);
        assertNotNull(result);
    }
}
