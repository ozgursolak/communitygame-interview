package com.communitygaming.interview.resolver.query;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.communitygaming.interview.model.Tournament;
import com.communitygaming.interview.service.TournamentService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
@RequiredArgsConstructor
public class TournamentQuery implements GraphQLQueryResolver {

    private final TournamentService tournamentService;

    public List<Tournament> getTournaments() {
        return this.tournamentService.getAllTournaments();
    }
}
