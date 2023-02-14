package dto.statistic;

import com.fasterxml.jackson.annotation.JsonProperty;
import dto.ArtistDTO;
import dto.GenreDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class StatisticsDTO {
    @JsonProperty("artists")
    private final List<StatisticArtistDTO> artists = new ArrayList<>();
    @JsonProperty("genres")
    private final List<StatisticGenreDTO> genres = new ArrayList<>();
    @JsonProperty("abouts")
    private final List<StatisticAboutDTO> abouts=new ArrayList<>();

    public StatisticsDTO(Map<ArtistDTO, Integer> bestArtists,
                         Map<GenreDTO, Integer> bestGenres,
                         Map<LocalDateTime, String> abouts) {
        bestArtists.forEach((artistDTO, integer) ->
                artists.add(new StatisticArtistDTO(
                        artistDTO.getId(),
                        artistDTO.getArtist(),
                        artistDTO.getVersion(),
                        integer)));
        bestGenres.forEach((genreDTO, integer) ->
                genres.add(new StatisticGenreDTO(
                        genreDTO.getId(),
                        genreDTO.getGenre(),
                        genreDTO.getVersion(),
                        integer)));
        abouts.forEach((localDateTime, s) ->
                this.abouts.add(new StatisticAboutDTO(localDateTime, s)));
    }

    public List<StatisticArtistDTO> getArtists() {
        return artists;
    }

    public List<StatisticGenreDTO> getGenres() {
        return genres;
    }

    public List<StatisticAboutDTO> getAbouts() {
        return abouts;
    }

}