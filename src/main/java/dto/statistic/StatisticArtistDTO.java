package dto.statistic;

public class StatisticArtistDTO {
    private Long id;
    private String artist;
    private Long version;
    private Integer count_vote;

    public StatisticArtistDTO(Long id, String artist, Long version, Integer count_vote) {
        this.id = id;
        this.artist = artist;
        this.version = version;
        this.count_vote = count_vote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
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
                ", artist='" + artist + '\'' +
                ", version=" + version +
                ", count_vote=" + count_vote +
                '}';
    }
}
