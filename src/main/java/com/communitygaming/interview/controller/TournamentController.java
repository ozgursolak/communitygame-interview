package com.communitygaming.interview.controller;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.communitygaming.interview.payload.request.TournamentRequest;
import com.communitygaming.interview.payload.response.TournamentResponse;
import com.communitygaming.interview.service.TournamentService;

@CrossOrigin(origins = "localhost", maxAge = 3600)
@RestController
@RequestMapping("/community-gaming")
@RequiredArgsConstructor
public class TournamentController {
    private final TournamentService tournamentService;

    @PostMapping("/tournament")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?>  createTournament(@Valid @RequestBody final TournamentRequest tournamentRequest) {
        final TournamentResponse tournamentResponse = tournamentService.createTournament(tournamentRequest);

        return ResponseEntity.ok(tournamentResponse);
    }
}
