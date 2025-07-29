package com.golfclub.controller;

import com.golfclub.model.Member;
import com.golfclub.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberRepository.save(member);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @GetMapping("/search")
    public List<Member> searchMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String date) {
        if (name != null) {
            return memberRepository.findByNameContainingIgnoreCase(name);
        }
        if (phone != null) {
            return memberRepository.findByPhone(phone);
        }
        if (date != null) {
            return memberRepository.findByMembershipStartDate(LocalDate.parse(date));
        }
        return memberRepository.findAll();
    }
}