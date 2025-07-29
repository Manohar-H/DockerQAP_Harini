package com.golfclub.controller;

import com.golfclub.model.Tournament;
import com.golfclub.model.Member;
import com.golfclub.repository.TournamentRepository;
import com.golfclub.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        if (location != null) {
            return tournamentRepository.findByLocationContainingIgnoreCase(location);
        }
        if (date != null) {
            return tournamentRepository.findByStartDate(LocalDate.parse(date));
        }
        return tournamentRepository.findAll();
    }

    @PostMapping("/{tournamentId}/members/{memberId}")
    public Tournament addMemberToTournament(@PathVariable Long tournamentId, @PathVariable Long memberId) {
        Optional<Tournament> tournament = tournamentRepository.findById(tournamentId);
        Optional<Member> member = memberRepository.findById(memberId);

        if (tournament.isPresent() && member.isPresent()) {
            Tournament t = tournament.get();
            t.getMembers().add(member.get());
            return tournamentRepository.save(t);
        }
        throw new RuntimeException("Tournament or Member not found.");
    }
}