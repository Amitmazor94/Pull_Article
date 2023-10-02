package Cron_Job;

import Excecutions.ExecuteTwelve;

import java.util.Timer;
import java.util.TimerTask;

public class CronJob {



        public static void main(String[] args) {
            Timer timer = new Timer();
            ExecuteTwelve executeTwelve = new ExecuteTwelve(); // Create an instance of ExecuteTwelve
            TimerTask task = new ExpiredSchedular(executeTwelve); // Pass the instance to the constructor
            timer.schedule(task, 1, 54000000);

        }


    }


