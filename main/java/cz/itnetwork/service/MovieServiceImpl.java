/*  _____ _______         _                      _
 * |_   _|__   __|       | |                    | |
 *   | |    | |_ __   ___| |___      _____  _ __| | __  ___ ____
 *   | |    | | '_ \ / _ \ __\ \ /\ / / _ \| '__| |/ / / __|_  /
 *  _| |_   | | | | |  __/ |_ \ V  V / (_) | |  |   < | (__ / /
 * |_____|  |_|_| |_|\___|\__| \_/\_/ \___/|_|  |_|\_(_)___/___|
 *                                _
 *              ___ ___ ___ _____|_|_ _ _____
 *             | . |  _| -_|     | | | |     |  LICENCE
 *             |  _|_| |___|_|_|_|_|___|_|_|_|
 *             |_|
 *
 *   PROGRAMOVÁNÍ  <>  DESIGN  <>  PRÁCE/PODNIKÁNÍ  <>  HW A SW
 *
 * Tento zdrojový kód je součástí výukových seriálů na
 * IT sociální síti WWW.ITNETWORK.CZ
 *
 * Kód spadá pod licenci prémiového obsahu a vznikl díky podpoře
 * našich členů. Je určen pouze pro osobní užití a nesmí být šířen.
 * Více informací na http://www.itnetwork.cz/licence
 */
package cz.itnetwork.service;

import cz.itnetwork.dto.MovieDTO;
import cz.itnetwork.dto.mapper.MovieMapper;
import cz.itnetwork.entity.MovieEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.filter.MovieFilter;
import cz.itnetwork.entity.repository.MovieRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import cz.itnetwork.entity.repository.specification.MovieSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MovieMapper movieMapper;

    public MovieDTO addMovie(MovieDTO movieDTO){
        MovieEntity movie = movieMapper.toEntity(movieDTO);
        mapPeopleToMovie(movie, movieDTO);
        MovieEntity saved = movieRepository.save(movie);
        return movieMapper.toDTO(saved);
    }

    public List<MovieDTO> getAllMovies(MovieFilter movieFilter){
        MovieSpecification movieSpecification = new MovieSpecification(movieFilter);

        return movieRepository.findAll(movieSpecification, PageRequest.of(0, movieFilter.getLimit()))
                .stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MovieDTO editMovie(MovieDTO movieDTO, long id){
        movieDTO.setId(id);
        MovieEntity entity = movieRepository.getReferenceById(id);
        movieMapper.updateEntity(movieDTO, entity);

        mapPeopleToMovie(entity, movieDTO);
        MovieEntity saved = movieRepository.save(entity);
        return movieMapper.toDTO(saved);
    }

    private void mapPeopleToMovie(MovieEntity movie, MovieDTO movieDTO){
        movie.setActors(new ArrayList<>());

        List<PersonEntity> people = personRepository.findAllById(movieDTO.getActorIDs());
        movie.getActors().addAll(people);
        movie.setDirector(personRepository.getReferenceById(movieDTO.getDirectorID()));
    }

    public MovieDTO removeMovie(Long id){
        MovieEntity movie = movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        MovieDTO model = movieMapper.toDTO(movie);

        movieRepository.delete(movie);
        return model;
    }

    public MovieDTO getMovie(Long id) {
        MovieEntity movie = movieRepository.getReferenceById(id);
        return movieMapper.toDTO(movie);
    }
}
