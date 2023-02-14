package dto.statistic;

public class StatisticGenreDTO {
    private Long id;
    private String genre;
    private Long version;
    private Integer count_vote;

    public StatisticGenreDTO(Long id, String genre, Long version, Integer count_vote) {
        this.id = id;
        this.genre = genre;
        this.version = version;
        this.count_vote = count_vote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getCount_vote() {
        return count_vote;
    }

    public void setCount_vote(Integer count_vote) {
        this.count_vote = count_vote;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                ", version=" + version +
                ", count_vote=" + count_vote +
                '}';
    }
}
