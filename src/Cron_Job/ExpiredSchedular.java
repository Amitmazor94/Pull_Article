package Cron_Job;
//import Service.SubscriptionService;
import Excecutions.ExecuteTwelve;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.TimerTask;


@Component

public class ExpiredSchedular extends TimerTask {
    private final ExecuteTwelve executeTwelve;

    public ExpiredSchedular(ExecuteTwelve executeTwelve) {
        this.executeTwelve = executeTwelve;
    }

    @Override
    public void run() {

        System.out.println(new Date());
        try {
            executeTwelve.execute01_printMainArticleAttributes();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
