package nou.hello.boot.spring5boot.dao;

import lombok.RequiredArgsConstructor;
import nou.hello.boot.spring5boot.model.Member;
import nou.hello.boot.spring5boot.mybatis.MemberMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberDAOImpl implements MemberDAO {

    // mybatis를 사용하기 위해 필요한 객체 생성
    // 단 생성자 주입 방식 사용
    @Autowired
    final MemberMapper memberMapper;

    @Autowired
    private SqlSession sqlSession;

    @Override
    public int insertMember(Member m) {
        // sqlSession.insert(insert 관련 mapping, 매개변수)
        // memberMapper.insertMember(매개변수)로 변경
        //return sqlSession.insert("MemberMapper.insertMember", m);
        return memberMapper.insertMember(m);
    }

    @Override
    public List<Member> selectMember() {
        return memberMapper.selectMember();
    }
}
