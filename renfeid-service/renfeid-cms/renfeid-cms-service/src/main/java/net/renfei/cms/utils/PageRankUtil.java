/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.cms.utils;

import net.renfei.common.core.utils.DateUtils;

import java.util.Date;

/**
 * @author renfei
 */
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
