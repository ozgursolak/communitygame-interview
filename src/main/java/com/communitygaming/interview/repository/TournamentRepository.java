package com.communitygaming.interview.repository;

import com.communitygaming.interview.model.Tournament;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TournamentRepository extends MongoRepository<Tournament, String> {
}
