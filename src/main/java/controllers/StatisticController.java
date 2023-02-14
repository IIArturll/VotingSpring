package controllers;

import dto.statistic.StatisticsDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.api.IStatisticsService;

@RestController
@RequestMapping("/results")
public class StatisticController {

    private final IStatisticsService service;

    public StatisticController(IStatisticsService service) {
        this.service = service;
    }

    @GetMapping()
    public StatisticsDTO getResult(){
        StatisticsDTO statistics = service.getStatistics();
        return statistics;
    }

}
