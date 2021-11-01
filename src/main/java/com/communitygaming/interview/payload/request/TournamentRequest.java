package com.communitygaming.interview.payload.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.communitygaming.interview.model.ETournamentType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TournamentRequest {

    @NotBlank
    private String tournamentName;

    @NotBlank
    private String organizerName;

    @Max(value = 1000)
    @Min(value = 1)
    private int quota;

    @NotBlank
    private String startDate;

    @NotBlank
    private String endDate;

    private double target;

    private String currency;

    private String description;

    private boolean isActive;

    private ETournamentType tournamentType;

    private String tournamentAddress;
}
