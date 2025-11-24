package Library.service;

import Library.model.Member;
import Library.storage.FileStorage;

import java.util.*;

public class MemberService {
    private final FileStorage storage;
    private final List<Member> members;

    public MemberService(FileStorage storage) {
        this.storage = storage;
        this.members = new ArrayList<>(storage.readAllMembers());
    }

    public Member addMember(String name, String email) {
        int id = storage.nextMemberId();
        Member m = new Member(id, name.trim(), email.trim());
        members.add(m);
        storage.writeAllMembers(members);
        return m;
    }

    public List<Member> listAll() { return Collections.unmodifiableList(members); }

    public Optional<Member> findById(int id) {
        return members.stream().filter(m -> m.getId() == id).findFirst();
    }

    public boolean remove(int id) {
        Optional<Member> om = findById(id);
        if (om.isPresent()) {
            members.remove(om.get());
            storage.writeAllMembers(members);
            return true;
        }
        return false;
    }

    public boolean update(int id, String newName, String newEmail) {
        Optional<Member> om = findById(id);
        if (om.isPresent()) {
            Member m = om.get();
            m.setName(newName.trim());
            m.setEmail(newEmail.trim());
            storage.writeAllMembers(members);
            return true;
        }
        return false;
    }
}
