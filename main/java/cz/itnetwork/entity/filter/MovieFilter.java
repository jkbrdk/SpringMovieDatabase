package cz.itnetwork.entity.filter;

import lombok.Data;

@Data
public class MovieFilter {

    private Long directorID;
    private Long actorID;
    private String genre;
    private Integer fromYear;
    private Integer toYear;
    private Integer limit = 10;

}
