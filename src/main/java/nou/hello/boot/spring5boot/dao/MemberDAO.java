package nou.hello.boot.spring5boot.dao;

import nou.hello.boot.spring5boot.model.Member;
import nou.hello.boot.spring5boot.model.Zipcode;

import java.util.List;

public interface MemberDAO {
    int insertMember(Member m);
    List<Member> selectMember();

    List<Zipcode> selectzip(String dong);

    int selectOneUserid(String uid);
}
