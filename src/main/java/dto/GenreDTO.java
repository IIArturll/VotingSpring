package dto;

import dao.entity.GenreEntity;

import java.util.Objects;

public class GenreDTO {

    private Long id;
    private String genre;
    private Long version;

    public GenreDTO() {
    }

    public GenreDTO(Long id, String genre, Long version) {
        this.id = id;
        this.genre = genre;
        this.version = version;
    }

    public GenreDTO(GenreEntity entity) {
        this.id = entity.getId();
        this.genre = entity.getGenre();
        this.version=entity.getVersion();
    }

    public Long getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public Long getVersion() {
        return version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreDTO genreDTO = (GenreDTO) o;
        return id == genreDTO.id && genre.equals(genreDTO.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genre);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}