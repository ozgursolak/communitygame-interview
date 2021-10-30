package com.communitygaming.interview.resolver.mutation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.communitygaming.interview.payload.request.TournamentRequest;
import com.communitygaming.interview.payload.response.TournamentResponse;
import com.communitygaming.interview.service.TournamentService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

@Component
@RequiredArgsConstructor
public class TournamentMutation implements GraphQLMutationResolver {

    private final TournamentService tournamentService;

    public TournamentResponse createTournament(final TournamentRequest tournamentRequest) {
        final TournamentResponse tournamentResponse = tournamentService.createTournament(tournamentRequest);

        return tournamentResponse;
    }
}
