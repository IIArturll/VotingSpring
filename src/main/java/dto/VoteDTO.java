package dto;

import dao.entity.GenreEntity;
import dao.entity.VoteEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VoteDTO {

    private String email;
    private Long artistId;
    private List<Long> genreIds;
    private String about;

    public VoteDTO(){
    }

    public VoteDTO(Long artistId, List<Long> genreIds, String about, String email) {
        this.artistId = artistId;
        this.genreIds = genreIds;
        this.about = about;
        this.email = email;
    }

    public VoteDTO(VoteEntity vote) {
        this.email = vote.getEmail();
        this.about = vote.getAbout();
        this.artistId = vote.getArtistEntity().getId();
        this.genreIds = vote.getGenreEntities().stream().map(GenreEntity::getId).collect(Collectors.toList());
    }

    public Long getArtistId() {
        return artistId;
    }

    public List<Long> getGenreIds() {
        return Collections.unmodifiableList(genreIds);
    }

    public String getAbout() {
        return about;
    }

    public String getEmail() {
        return email;
    }


}