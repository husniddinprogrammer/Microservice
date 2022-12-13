package com.microservice.member.service;

import com.microservice.member.entity.Member;
import com.microservice.member.response.UserVM;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {
    public Page<Member> getAll(Pageable pageable);
    public Member getById(Long id);
    public Member create(UserVM data);
    public Member update(UserVM data);
    public void delete(Member data);
    public void deleteById(Long id);
}
