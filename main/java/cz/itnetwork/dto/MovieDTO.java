package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Date;
import java.util.List;

@Data
public class MovieDTO {

    @NotNull
    @JsonProperty("_id")
    private long id;

    @NotBlank
    private String name;
    @NotNull
    private Long directorID;
    @NotNull
    private List<@Positive Long> actorIDs;
    private boolean isAvailable;
    @NotNull
    private List<String> genres;
    @NotNull
    @Positive
    private Integer year;

    @JsonProperty("isAvailable")
    public boolean isAvailable() {
        return isAvailable;
    }

    private Date dateAdded;
    private PersonDTO director;
    private List<PersonDTO> actors;
}
