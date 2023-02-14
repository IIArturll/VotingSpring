package dao.entity;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "genre", schema = "app")
public class GenreEntity {
    @Id
    @GeneratedValue(generator = "genre_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "genre_seq", sequenceName = "genre_id_seq", schema = "app", allocationSize = 1)
    private Long id;
    @Column(name = "name")
    private String genre;
    private Long version;

    public GenreEntity() {
    }

    public GenreEntity(String genre) {
        this.genre = genre;
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
        GenreEntity that = (GenreEntity) o;
        return id == that.id
                && Objects.equals(genre, that.genre)
                && version == ((GenreEntity) o).version;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genre);
    }
}
