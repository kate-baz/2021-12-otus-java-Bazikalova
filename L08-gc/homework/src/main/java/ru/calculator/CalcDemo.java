package ru.calculator;


/*
-Xms256m
-Xmx256m
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=./logs/heapdump.hprof
-XX:+UseG1GC
-Xlog:gc=debug:file=./logs/gc-%p-%t.log:tags,uptime,time,level:filecount=5,filesize=10m
*/

/*
Before optimisation:
spend msec:17431, sec:17 - 64m
spend msec:17111, sec:17 - 128m
spend msec:10057, sec:10 - 256m - optimal heap size
spend msec:10147, sec:10 - 512m
spend msec:10008, sec:10 - 1g
spend msec:10109, sec:10 - 2g

After optimisation-2:
spend msec:997, sec:0 - 32m
spend msec:609, sec:0 - 64m - optimal heap size
spend msec:598, sec:0 - 128m
spend msec:615, sec:0 - 256m
spend msec:606, sec:0 - 1024m
spend msec:1207, sec:1 - 2048m
 */

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CalcDemo {
    public static void main(String[] args) {
        var results = new ArrayList<Long>();
        for (int i = 0; i<15; i++) {
            results.add(runMeasuring());
        }
        var sum = 0;
        for (Long delta: results) {
            sum += delta;
        }
        System.out.println("AVR MS= " + sum/results.size());
    }

    private static long runMeasuring() {
        long counter = 100_000_000;
        var summator = new Summator();
        long startTime = System.currentTimeMillis();

        var data = new Data();
        for (var idx = 0; idx < counter; idx++) {
            data.setValue(idx);
            summator.calc(data);

            if (idx % 10_000_000 == 0) {
                System.out.println(LocalDateTime.now() + " current idx:" + idx);
            }
        }

        long delta = System.currentTimeMillis() - startTime;
        System.out.println(summator.getPrevValue());
        System.out.println(summator.getPrevPrevValue());
        System.out.println(summator.getSumLastThreeValues());
        System.out.println(summator.getSomeValue());
        System.out.println(summator.getSum());
        System.out.println("spend msec:" + delta + ", sec:" + (delta / 1000));
        return delta;
    }
}
