package com.microservice.member.controller;

import com.microservice.member.entity.Member;
import com.microservice.member.response.UserVM;
import com.microservice.member.service.impl.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    MemberServiceImpl memberService;

    @GetMapping("/{pageId}")
    public ResponseEntity<Page<Member>> getAll(@PathVariable Long pageId) {
        Pageable pageable = PageRequest.of(pageId.intValue() - 1, 50);
        return ResponseEntity.ok(memberService.getAll(pageable));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Member> getById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<Member> save(@RequestBody UserVM userVM) {

        return ResponseEntity.ok(memberService.create(userVM));
    }

    @PutMapping()
    public ResponseEntity<Member> update(@RequestBody UserVM userVM) {
        return ResponseEntity.ok(memberService.update(userVM));
    }

    @DeleteMapping("/delete/{id}")
    public void getDeleteId(@PathVariable Long id) {
        memberService.getById(id);
    }

}
