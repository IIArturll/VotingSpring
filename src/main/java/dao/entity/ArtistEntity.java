package dao.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "artist", schema = "app")
public class ArtistEntity {
    @Id
    @GeneratedValue(generator = "artist_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "artist_seq", sequenceName = "artist_id_seq", schema = "app", allocationSize = 1)
    private Long id;
    @Column(name = "name")
    private String artist;

    private Long version;

    public ArtistEntity() {
    }

    public ArtistEntity(String artist) {
        this.artist = artist;
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

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setVersion(Long version) {
        this.version = version;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistEntity that = (ArtistEntity) o;
        return id == that.id
                && Objects.equals(artist, that.artist)
                && version == ((ArtistEntity) o).version;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artist);
    }

}
