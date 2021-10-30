package com.communitygaming.interview.service;

import java.util.List;

import com.communitygaming.interview.model.Tournament;
import com.communitygaming.interview.payload.request.TournamentRequest;
import com.communitygaming.interview.payload.response.TournamentResponse;

public interface TournamentService {

    TournamentResponse createTournament(TournamentRequest tournamentRequest);
    List<Tournament> getAllTournaments();
}
