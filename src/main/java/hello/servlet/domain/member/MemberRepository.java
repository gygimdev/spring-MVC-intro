package hello.servlet.domain.member;

import java.util.*;

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }

    // 생성자
    private MemberRepository() {
    }

    // 맴버저장(회원가입)
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    // 맴버 찾기(id)
    public Member findById(Long id){
        return store.get(id);
    }

    // 모든 맴버 조회
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    // 테스트용 코드(디비 날리기)
    public void clearStore(){
       store.clear();
    }
}
