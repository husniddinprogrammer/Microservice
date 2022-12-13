package com.microservice.member.service.impl;

import com.microservice.member.entity.Member;
import com.microservice.member.repository.MemberRepository;
import com.microservice.member.response.UserVM;
import com.microservice.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Page<Member> getAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    @Override
    public Member getById(Long id){
        return memberRepository.findById(id).get();
    }

    @Override
    public Member create(UserVM data){
        Member member = new Member();
        member.setFirstName(data.getFirstName());
        member.setName(data.getName());
        member.setRegisterTime(LocalDateTime.now());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserVM> request = new HttpEntity<>(data,headers);

        restTemplate.postForObject(
                "http://localhost:8081/api/account/register",
                request,
                Object.class
        );
        return memberRepository.save(member);
    }

    @Override
    public Member update(UserVM data){
        Member member = memberRepository.findById(data.getId()).get();
        member.setName(data.getName());
        member.setFirstName(data.getFirstName());
        member.setEditTime(LocalDateTime.now());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserVM> request = new HttpEntity<>(data,headers);

        restTemplate.postForObject(
                "http://localhost:8081/api/account/edit",
                request,
                Object.class
        );
        return memberRepository.save(member);
    }

    @Override
    public void delete(Member data) {
        deleteById(data.getId());
    }

    @Override
    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}










