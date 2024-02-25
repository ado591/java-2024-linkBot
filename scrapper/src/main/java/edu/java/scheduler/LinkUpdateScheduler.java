package edu.java.scheduler;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinkUpdateScheduler {
    private static final Logger LOGGER = LogManager.getLogger();

    @Scheduled(fixedDelayString = "#{@scheduler.interval().toMillis()}")
    public void update() {
        LOGGER.info("Searching for updates");
    }
}
