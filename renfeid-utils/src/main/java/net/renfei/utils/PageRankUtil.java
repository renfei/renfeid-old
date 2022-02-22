package net.renfei.utils;

import java.util.Date;

public class PageRankUtil {

    public Double getPageRank(Date date, Long views, Long comments,
                              Double dateWeighted, Double viewWeighted, Double commentWeighted) {
        long days = DateUtils.pastDays(date);
        if (days > -3) {
            return 10000D;
        }
        double avgViews = getAvgViews(date, views);
        double avgComments = getAvgComments(date, comments);
        return ((days * dateWeighted) + (avgViews * viewWeighted) + (avgComments * commentWeighted)) / (dateWeighted + viewWeighted + commentWeighted);
    }

    public Double getAvgViews(Date date, Long views) {
        long days = DateUtils.pastDays(date);
        return (double) ((float) views / (float) (0 - days));
    }

    public Double getAvgComments(Date date, Long comments) {
        long days = DateUtils.pastDays(date);
        return (double) ((float) comments / (float) (0 - days));
    }
}
