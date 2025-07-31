package com.golfclub.controller;

import com.golfclub.model.Member;
import com.golfclub.repository.MemberRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member savedMember = memberRepository.save(member);
        return ResponseEntity.ok(savedMember);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @GetMapping("/search")
    public List<Member> searchMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String date) {

        Specification<Member> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (phone != null && !phone.isEmpty()) {
                predicates.add(cb.equal(root.get("phone"), phone));
            }
            if (type != null && !type.isEmpty()) {
                predicates.add(cb.equal(root.get("membershipType"), type));
            }
            if (date != null && !date.isEmpty()) {
                try {
                    LocalDate parsedDate = LocalDate.parse(date);
                    predicates.add(cb.equal(root.get("membershipStartDate"), parsedDate));
                } catch (Exception e) {
                    throw new RuntimeException("Invalid date format. Use yyyy-MM-dd.");
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return memberRepository.findAll(spec);
    }
}