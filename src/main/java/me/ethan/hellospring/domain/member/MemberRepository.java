package me.ethan.hellospring.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRepository {

    private Map<Long,Member> store = new HashMap<>();
    private static long sequence =0L;

    private static final MemberRepository instance =new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    public Member save(Member member){
        member.setId(sequence++);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(long memberId){
        return store.get(memberId);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clear(){
        store.clear();
    }
}
