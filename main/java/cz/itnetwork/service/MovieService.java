package cz.itnetwork.service;

import cz.itnetwork.dto.MovieDTO;
import cz.itnetwork.entity.MovieEntity;
import cz.itnetwork.entity.filter.MovieFilter;

import java.util.List;

public interface MovieService {

    MovieDTO addMovie(MovieDTO movieDTO);

    List<MovieDTO> getAllMovies(MovieFilter movieFilter);

    MovieDTO editMovie(MovieDTO movieDTO, long id);

    MovieDTO removeMovie(Long id);

    MovieDTO getMovie(Long id);

}
