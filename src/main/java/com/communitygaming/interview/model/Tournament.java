package com.communitygaming.interview.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "tournament")
@Getter
@Setter
@NoArgsConstructor
public class Tournament {

    @Id
    private String id;

    @Field(name = "tournament_name")
    private String tournamentName;

    @Field(name = "organizer_name")
    private String organizerName;

    @Field(name = "quota")
    private int quota;

    @Field(name = "target")
    private double target = 0.0;

    @Field(name = "currency")
    private String currency = "$";

    @Field(name = "start_date")
    private String startDate;

    @Field(name = "end_date")
    private String endDate;

    @Field(name = "tournament_type")
    private ETournamentType tournamentType = ETournamentType.NON_SPONSORED;

    @Field(name = "description")
    private String description;

    @Field(name = "tournament_address")
    private String tournamentAddress;

    @Field(name = "is_active")
    private boolean isActive = Boolean.FALSE;
}
