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
package net.renfei.server.entity;

import net.renfei.common.core.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author renfei
 */
public class SiteMapXml implements Serializable {
    private static final long serialVersionUID = -314420603322403668L;
    private static final Logger logger = LoggerFactory.getLogger(SiteMapXml.class);
    private String loc;
    private ChangefreqEnum changefreqEnum;
    private float priority;
    private Date lastmod;

    public SiteMapXml() {
    }

    public String getLastmod() {
        if (lastmod == null) {
            return "";
        }
        String str = "";
        try {
            // https://www.sitemaps.org/protocol.html#lastmoddef
            return new SimpleDateFormat("yyyy-MM-dd").format(lastmod);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }

    public SiteMapXml(String loc, ChangefreqEnum changefre, String priority) {
        this.setChangefreqEnum(changefre);
        this.setLoc(loc);
        this.setPriority(Float.parseFloat(priority));
    }

    public SiteMapXml(String loc, ChangefreqEnum changefre, String priority, Date lastmod) {
        this.setChangefreqEnum(changefre);
        this.setLoc(loc);
        this.setPriority(Float.parseFloat(priority));
        this.setLastmod(lastmod);
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public ChangefreqEnum getChangefreqEnum() {
        return changefreqEnum;
    }

    public void setChangefreqEnum(ChangefreqEnum changefreqEnum) {
        this.changefreqEnum = changefreqEnum;
    }

    public float getPriority() {
        return priority;
    }

    public void setPriority(float priority) {
        this.priority = priority;
    }

    public void setLastmod(Date lastmod) {
        this.lastmod = lastmod;
    }

    public void setLastmod(String lastmod) {
        this.lastmod = DateUtils.parseDate(lastmod + " 00:00:00");
    }
}
