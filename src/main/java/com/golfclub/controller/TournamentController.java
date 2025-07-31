package com.golfclub.controller;

import com.golfclub.model.Tournament;
import com.golfclub.model.Member;
import com.golfclub.repository.TournamentRepository;
import com.golfclub.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    @GetMapping("/search")
    public List<Tournament> searchTournaments(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String date) {

        if (location != null && !location.isEmpty()) {
            return tournamentRepository.findByLocationContainingIgnoreCase(location);
        } else if (date != null && !date.isEmpty()) {
            return tournamentRepository.findByStartDate(LocalDate.parse(date));
        }

        return tournamentRepository.findAll();
    }

    @PostMapping("/{tournamentId}/add-member/{memberId}")
    public ResponseEntity<String> addMemberToTournament(
            @PathVariable Long tournamentId,
            @PathVariable Long memberId) {

        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        tournament.getMembers().add(member);
        tournamentRepository.save(tournament);

        return ResponseEntity.ok("Member added to tournament ✅");
    }
}