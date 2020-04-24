package com.reviewcongty.backend.web.util;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface TimeUtils {
    List<Long> times = Arrays.asList(
            TimeUnit.DAYS.toMillis(365),
            TimeUnit.DAYS.toMillis(30),
            TimeUnit.DAYS.toMillis(1),
            TimeUnit.HOURS.toMillis(1),
            TimeUnit.MINUTES.toMillis(1),
            TimeUnit.SECONDS.toMillis(1));
    List<String> timesString = Arrays.asList("year", "month", "day", "hour", "minute", "second");

    static String getRelativeTime(Date date) {
        Long duration = (new Date()).getTime() - date.getTime();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < TimeUtils.times.size(); i++) {
            Long current = TimeUtils.times.get(i);
            long temp = duration / current;
            if (temp > 0) {
                res.append(temp).append(" ").append(TimeUtils.timesString.get(i)).append(temp != 1 ? "s" : "").append(" ago");
                break;
            }
        }
        if ("".equals(res.toString()))
            return "0 seconds ago";
        else
            return res.toString();
    }
}
