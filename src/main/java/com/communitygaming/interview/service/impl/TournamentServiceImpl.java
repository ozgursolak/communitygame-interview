package com.communitygaming.interview.service.impl;

import java.util.List;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.communitygaming.interview.model.ETournamentType;
import com.communitygaming.interview.model.Tournament;
import com.communitygaming.interview.payload.request.TournamentRequest;
import com.communitygaming.interview.payload.response.TournamentResponse;
import com.communitygaming.interview.repository.TournamentRepository;
import com.communitygaming.interview.service.TournamentService;

@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;

    @Transactional
    @Override
    public TournamentResponse createTournament(final TournamentRequest tournamentRequest) {
        final Tournament tournament = new Tournament();

        populateTournament(tournament, tournamentRequest);

        tournamentRepository.save(tournament);

        final String tournamentId = tournament.getId();
        final TournamentResponse tournamentResponse = new TournamentResponse(tournamentId);

        return tournamentResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    private void populateTournament(final Tournament tournament, final TournamentRequest tournamentRequest) {
        final String tournamentName = tournamentRequest.getTournamentName();
        final String organizerName = tournamentRequest.getOrganizerName();
        final String startDate = tournamentRequest.getStartDate();
        final String endDate = tournamentRequest.getEndDate();
        final Double target = tournamentRequest.getTarget();
        final String currency = tournamentRequest.getCurrency();
        final Boolean activeTournament = tournamentRequest.isActive();
        final int quota = tournamentRequest.getQuota();
        final ETournamentType tournamentType = tournamentRequest.getTournamentType();
        final String tournamentAddress = tournamentRequest.getTournamentAddress();
        final String description = tournamentRequest.getDescription();

        tournament.setTournamentName(tournamentName);
        tournament.setOrganizerName(organizerName);
        tournament.setStartDate(startDate);
        tournament.setEndDate(endDate);
        tournament.setActive(activeTournament);
        tournament.setQuota(quota);
        tournament.setTournamentAddress(tournamentAddress);
        tournament.setDescription(description);

        if (Objects.nonNull(activeTournament)) tournament.setActive(activeTournament);

        if (Objects.nonNull(tournamentType)) tournament.setTournamentType(tournamentType);

        if (Objects.nonNull(currency)) tournament.setCurrency(currency);

        if (Objects.nonNull(target)) tournament.setTarget(target);
    }
}
