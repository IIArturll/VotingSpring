package dto;

import dao.entity.ArtistEntity;

import java.util.Objects;

public class ArtistDTO {

    private Long id;
    private String artist;

    private Long version;

    public ArtistDTO() {

    }

    public ArtistDTO(Long id, String artist, Long version) {
        this.id = id;
        this.artist = artist;
        this.version = version;
    }

    public ArtistDTO(ArtistEntity entity) {
        this.id = entity.getId();
        this.artist = entity.getArtist();
        this.version = entity.getVersion();
    }

    public Long getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistDTO that = (ArtistDTO) o;
        return id == that.id && artist.equals(that.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artist);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", artist='" + artist + '\'' +
                ", version=" + version +
                '}';
    }
}