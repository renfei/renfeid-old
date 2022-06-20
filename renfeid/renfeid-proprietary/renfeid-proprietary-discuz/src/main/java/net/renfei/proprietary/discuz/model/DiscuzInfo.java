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
package net.renfei.proprietary.discuz.model;

/**
 * <p>Title: DiscuzInfo</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
public class DiscuzInfo {
    private String userGroup;
    private Integer postsCount;
    private Short essenceCount;
    private Short oltime;
    private Integer points;
    private Integer money;
    private Integer prestige;
    private Integer contribution;

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public Integer getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(Integer postsCount) {
        this.postsCount = postsCount;
    }

    public Short getEssenceCount() {
        return essenceCount;
    }

    public void setEssenceCount(Short essenceCount) {
        this.essenceCount = essenceCount;
    }

    public Short getOltime() {
        return oltime;
    }

    public void setOltime(Short oltime) {
        this.oltime = oltime;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getPrestige() {
        return prestige;
    }

    public void setPrestige(Integer prestige) {
        this.prestige = prestige;
    }

    public Integer getContribution() {
        return contribution;
    }

    public void setContribution(Integer contribution) {
        this.contribution = contribution;
    }
}
