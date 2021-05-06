package com.grimthedev.counter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Example{

    public static void startCounter(){
        ArrayList<String> a = new ArrayList<String>();

        a.add("timer1");
        a.add("timer2");

        Timer timer = new Timer();

        for (String test:a){
            TimerTask timerTask = new TimerTask() {

                int counter = 45665464;
                @Override
                public void run() {
                    if (counter <= 0){
                        timer.cancel();
                    }else {
                        counter--;
                        calculateTime(counter);
                        System.out.println(test+": counter");
                    }
                }
            };
            timer.scheduleAtFixedRate(timerTask,1000,1000);
        }

}

    public static void calculateTime(long seconds) {
        long sec = seconds % 60;
        long minutes = seconds % 3600 / 60;
        long hours = seconds % 86400 / 3600;
        long days = seconds / 86400;

        System.out.println("Day " + days + " Hour " + hours + " Minute " + minutes + " Seconds " + sec);
    }

}
