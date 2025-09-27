package org.workswap.stat.shedulers;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.workswap.core.services.command.StatisticCommandService;
import org.workswap.datasource.stats.model.StatSnapshot.IntervalType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class SaveStatSheduler {

    private final StatisticCommandService statisticCommandService;
    
    @Scheduled(fixedRate = 5 * 60 * 1000) // 5 минут (5 * 60 * 1000)
    public void create5minStatSnapshot() {
        statisticCommandService.saveListingsStat(IntervalType.FIVE_MINUTES);
    }

    @Scheduled(fixedRate = 60 * 60 * 1000) // 5 минут (5 * 60 * 1000)
    public void createHourStatSnapshot() {
        statisticCommandService.saveListingsStat(IntervalType.HOURLY);
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "Europe/Helsinki")
    public void createDayStatSnapshot() {
        statisticCommandService.saveListingsStat(IntervalType.DAILY);
    }

    @Scheduled(cron = "0 0 0 * * SUN", zone = "Europe/Helsinki")
    public void createWeekStatSnapshot() {
        statisticCommandService.saveListingsStat(IntervalType.WEEKLY);
    }

    @Scheduled(cron = "0 0 3 * * *")
    public void cleanUpDuplicateListingsStat() {
        statisticCommandService.cleanUpDuplicateListingsStat();
    }
}
