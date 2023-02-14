package dto.statistic;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class StatisticAboutDTO {
    @JsonProperty("date_time")
    private String dateTime;
    @JsonProperty("about")
    private String about;

    public StatisticAboutDTO(LocalDateTime dateTime, String about) {
        this.dateTime = dateTime.toString();
        this.about = about;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime.toString();
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "{" +
                "dateTime=" + dateTime +
                ", about='" + about + '\'' +
                '}';
    }
}
