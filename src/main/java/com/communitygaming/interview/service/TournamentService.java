package com.communitygaming.interview.service;

import com.communitygaming.interview.payload.request.TournamentRequest;

public interface TournamentService {

    String createTournament(TournamentRequest tournamentRequest);
}
