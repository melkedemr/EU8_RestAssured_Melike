package com.melnac.review;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class Postcode {
    @JsonProperty("post code")
    private String postCode;

    @JsonProperty("country")
    private String country;

    @JsonProperty("country abbrevbiation")
    private String countryAbbreviation;

    @JsonProperty("places")
    private List<Place> places= null;
}
