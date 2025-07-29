package com.golfclub.repository;

import com.golfclub.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByNameContainingIgnoreCase(String name);
    List<Member> findByPhone(String phone);
    List<Member> findByMembershipStartDate(LocalDate date);
}