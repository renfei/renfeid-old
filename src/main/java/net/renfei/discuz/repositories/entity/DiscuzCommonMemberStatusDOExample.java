package net.renfei.discuz.repositories.entity;

import java.util.ArrayList;
import java.util.List;

public class DiscuzCommonMemberStatusDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DiscuzCommonMemberStatusDOExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andRegipIsNull() {
            addCriterion("regip is null");
            return (Criteria) this;
        }

        public Criteria andRegipIsNotNull() {
            addCriterion("regip is not null");
            return (Criteria) this;
        }

        public Criteria andRegipEqualTo(String value) {
            addCriterion("regip =", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipNotEqualTo(String value) {
            addCriterion("regip <>", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipGreaterThan(String value) {
            addCriterion("regip >", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipGreaterThanOrEqualTo(String value) {
            addCriterion("regip >=", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipLessThan(String value) {
            addCriterion("regip <", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipLessThanOrEqualTo(String value) {
            addCriterion("regip <=", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipLike(String value) {
            addCriterion("regip like", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipNotLike(String value) {
            addCriterion("regip not like", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipIn(List<String> values) {
            addCriterion("regip in", values, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipNotIn(List<String> values) {
            addCriterion("regip not in", values, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipBetween(String value1, String value2) {
            addCriterion("regip between", value1, value2, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipNotBetween(String value1, String value2) {
            addCriterion("regip not between", value1, value2, "regip");
            return (Criteria) this;
        }

        public Criteria andLastipIsNull() {
            addCriterion("lastip is null");
            return (Criteria) this;
        }

        public Criteria andLastipIsNotNull() {
            addCriterion("lastip is not null");
            return (Criteria) this;
        }

        public Criteria andLastipEqualTo(String value) {
            addCriterion("lastip =", value, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipNotEqualTo(String value) {
            addCriterion("lastip <>", value, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipGreaterThan(String value) {
            addCriterion("lastip >", value, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipGreaterThanOrEqualTo(String value) {
            addCriterion("lastip >=", value, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipLessThan(String value) {
            addCriterion("lastip <", value, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipLessThanOrEqualTo(String value) {
            addCriterion("lastip <=", value, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipLike(String value) {
            addCriterion("lastip like", value, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipNotLike(String value) {
            addCriterion("lastip not like", value, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipIn(List<String> values) {
            addCriterion("lastip in", values, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipNotIn(List<String> values) {
            addCriterion("lastip not in", values, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipBetween(String value1, String value2) {
            addCriterion("lastip between", value1, value2, "lastip");
            return (Criteria) this;
        }

        public Criteria andLastipNotBetween(String value1, String value2) {
            addCriterion("lastip not between", value1, value2, "lastip");
            return (Criteria) this;
        }

        public Criteria andPortIsNull() {
            addCriterion("port is null");
            return (Criteria) this;
        }

        public Criteria andPortIsNotNull() {
            addCriterion("port is not null");
            return (Criteria) this;
        }

        public Criteria andPortEqualTo(Short value) {
            addCriterion("port =", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotEqualTo(Short value) {
            addCriterion("port <>", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThan(Short value) {
            addCriterion("port >", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanOrEqualTo(Short value) {
            addCriterion("port >=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThan(Short value) {
            addCriterion("port <", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThanOrEqualTo(Short value) {
            addCriterion("port <=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortIn(List<Short> values) {
            addCriterion("port in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotIn(List<Short> values) {
            addCriterion("port not in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortBetween(Short value1, Short value2) {
            addCriterion("port between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotBetween(Short value1, Short value2) {
            addCriterion("port not between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andLastvisitIsNull() {
            addCriterion("lastvisit is null");
            return (Criteria) this;
        }

        public Criteria andLastvisitIsNotNull() {
            addCriterion("lastvisit is not null");
            return (Criteria) this;
        }

        public Criteria andLastvisitEqualTo(Integer value) {
            addCriterion("lastvisit =", value, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitNotEqualTo(Integer value) {
            addCriterion("lastvisit <>", value, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitGreaterThan(Integer value) {
            addCriterion("lastvisit >", value, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitGreaterThanOrEqualTo(Integer value) {
            addCriterion("lastvisit >=", value, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitLessThan(Integer value) {
            addCriterion("lastvisit <", value, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitLessThanOrEqualTo(Integer value) {
            addCriterion("lastvisit <=", value, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitIn(List<Integer> values) {
            addCriterion("lastvisit in", values, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitNotIn(List<Integer> values) {
            addCriterion("lastvisit not in", values, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitBetween(Integer value1, Integer value2) {
            addCriterion("lastvisit between", value1, value2, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastvisitNotBetween(Integer value1, Integer value2) {
            addCriterion("lastvisit not between", value1, value2, "lastvisit");
            return (Criteria) this;
        }

        public Criteria andLastactivityIsNull() {
            addCriterion("lastactivity is null");
            return (Criteria) this;
        }

        public Criteria andLastactivityIsNotNull() {
            addCriterion("lastactivity is not null");
            return (Criteria) this;
        }

        public Criteria andLastactivityEqualTo(Integer value) {
            addCriterion("lastactivity =", value, "lastactivity");
            return (Criteria) this;
        }

        public Criteria andLastactivityNotEqualTo(Integer value) {
            addCriterion("lastactivity <>", value, "lastactivity");
            return (Criteria) this;
        }

        public Criteria andLastactivityGreaterThan(Integer value) {
            addCriterion("lastactivity >", value, "lastactivity");
            return (Criteria) this;
        }

        public Criteria andLastactivityGreaterThanOrEqualTo(Integer value) {
            addCriterion("lastactivity >=", value, "lastactivity");
            return (Criteria) this;
        }

        public Criteria andLastactivityLessThan(Integer value) {
            addCriterion("lastactivity <", value, "lastactivity");
            return (Criteria) this;
        }

        public Criteria andLastactivityLessThanOrEqualTo(Integer value) {
            addCriterion("lastactivity <=", value, "lastactivity");
            return (Criteria) this;
        }

        public Criteria andLastactivityIn(List<Integer> values) {
            addCriterion("lastactivity in", values, "lastactivity");
            return (Criteria) this;
        }

        public Criteria andLastactivityNotIn(List<Integer> values) {
            addCriterion("lastactivity not in", values, "lastactivity");
            return (Criteria) this;
        }

        public Criteria andLastactivityBetween(Integer value1, Integer value2) {
            addCriterion("lastactivity between", value1, value2, "lastactivity");
            return (Criteria) this;
        }

        public Criteria andLastactivityNotBetween(Integer value1, Integer value2) {
            addCriterion("lastactivity not between", value1, value2, "lastactivity");
            return (Criteria) this;
        }

        public Criteria andLastpostIsNull() {
            addCriterion("lastpost is null");
            return (Criteria) this;
        }

        public Criteria andLastpostIsNotNull() {
            addCriterion("lastpost is not null");
            return (Criteria) this;
        }

        public Criteria andLastpostEqualTo(Integer value) {
            addCriterion("lastpost =", value, "lastpost");
            return (Criteria) this;
        }

        public Criteria andLastpostNotEqualTo(Integer value) {
            addCriterion("lastpost <>", value, "lastpost");
            return (Criteria) this;
        }

        public Criteria andLastpostGreaterThan(Integer value) {
            addCriterion("lastpost >", value, "lastpost");
            return (Criteria) this;
        }

        public Criteria andLastpostGreaterThanOrEqualTo(Integer value) {
            addCriterion("lastpost >=", value, "lastpost");
            return (Criteria) this;
        }

        public Criteria andLastpostLessThan(Integer value) {
            addCriterion("lastpost <", value, "lastpost");
            return (Criteria) this;
        }

        public Criteria andLastpostLessThanOrEqualTo(Integer value) {
            addCriterion("lastpost <=", value, "lastpost");
            return (Criteria) this;
        }

        public Criteria andLastpostIn(List<Integer> values) {
            addCriterion("lastpost in", values, "lastpost");
            return (Criteria) this;
        }

        public Criteria andLastpostNotIn(List<Integer> values) {
            addCriterion("lastpost not in", values, "lastpost");
            return (Criteria) this;
        }

        public Criteria andLastpostBetween(Integer value1, Integer value2) {
            addCriterion("lastpost between", value1, value2, "lastpost");
            return (Criteria) this;
        }

        public Criteria andLastpostNotBetween(Integer value1, Integer value2) {
            addCriterion("lastpost not between", value1, value2, "lastpost");
            return (Criteria) this;
        }

        public Criteria andLastsendmailIsNull() {
            addCriterion("lastsendmail is null");
            return (Criteria) this;
        }

        public Criteria andLastsendmailIsNotNull() {
            addCriterion("lastsendmail is not null");
            return (Criteria) this;
        }

        public Criteria andLastsendmailEqualTo(Integer value) {
            addCriterion("lastsendmail =", value, "lastsendmail");
            return (Criteria) this;
        }

        public Criteria andLastsendmailNotEqualTo(Integer value) {
            addCriterion("lastsendmail <>", value, "lastsendmail");
            return (Criteria) this;
        }

        public Criteria andLastsendmailGreaterThan(Integer value) {
            addCriterion("lastsendmail >", value, "lastsendmail");
            return (Criteria) this;
        }

        public Criteria andLastsendmailGreaterThanOrEqualTo(Integer value) {
            addCriterion("lastsendmail >=", value, "lastsendmail");
            return (Criteria) this;
        }

        public Criteria andLastsendmailLessThan(Integer value) {
            addCriterion("lastsendmail <", value, "lastsendmail");
            return (Criteria) this;
        }

        public Criteria andLastsendmailLessThanOrEqualTo(Integer value) {
            addCriterion("lastsendmail <=", value, "lastsendmail");
            return (Criteria) this;
        }

        public Criteria andLastsendmailIn(List<Integer> values) {
            addCriterion("lastsendmail in", values, "lastsendmail");
            return (Criteria) this;
        }

        public Criteria andLastsendmailNotIn(List<Integer> values) {
            addCriterion("lastsendmail not in", values, "lastsendmail");
            return (Criteria) this;
        }

        public Criteria andLastsendmailBetween(Integer value1, Integer value2) {
            addCriterion("lastsendmail between", value1, value2, "lastsendmail");
            return (Criteria) this;
        }

        public Criteria andLastsendmailNotBetween(Integer value1, Integer value2) {
            addCriterion("lastsendmail not between", value1, value2, "lastsendmail");
            return (Criteria) this;
        }

        public Criteria andInvisibleIsNull() {
            addCriterion("invisible is null");
            return (Criteria) this;
        }

        public Criteria andInvisibleIsNotNull() {
            addCriterion("invisible is not null");
            return (Criteria) this;
        }

        public Criteria andInvisibleEqualTo(Integer value) {
            addCriterion("invisible =", value, "invisible");
            return (Criteria) this;
        }

        public Criteria andInvisibleNotEqualTo(Integer value) {
            addCriterion("invisible <>", value, "invisible");
            return (Criteria) this;
        }

        public Criteria andInvisibleGreaterThan(Integer value) {
            addCriterion("invisible >", value, "invisible");
            return (Criteria) this;
        }

        public Criteria andInvisibleGreaterThanOrEqualTo(Integer value) {
            addCriterion("invisible >=", value, "invisible");
            return (Criteria) this;
        }

        public Criteria andInvisibleLessThan(Integer value) {
            addCriterion("invisible <", value, "invisible");
            return (Criteria) this;
        }

        public Criteria andInvisibleLessThanOrEqualTo(Integer value) {
            addCriterion("invisible <=", value, "invisible");
            return (Criteria) this;
        }

        public Criteria andInvisibleIn(List<Integer> values) {
            addCriterion("invisible in", values, "invisible");
            return (Criteria) this;
        }

        public Criteria andInvisibleNotIn(List<Integer> values) {
            addCriterion("invisible not in", values, "invisible");
            return (Criteria) this;
        }

        public Criteria andInvisibleBetween(Integer value1, Integer value2) {
            addCriterion("invisible between", value1, value2, "invisible");
            return (Criteria) this;
        }

        public Criteria andInvisibleNotBetween(Integer value1, Integer value2) {
            addCriterion("invisible not between", value1, value2, "invisible");
            return (Criteria) this;
        }

        public Criteria andBuyercreditIsNull() {
            addCriterion("buyercredit is null");
            return (Criteria) this;
        }

        public Criteria andBuyercreditIsNotNull() {
            addCriterion("buyercredit is not null");
            return (Criteria) this;
        }

        public Criteria andBuyercreditEqualTo(Short value) {
            addCriterion("buyercredit =", value, "buyercredit");
            return (Criteria) this;
        }

        public Criteria andBuyercreditNotEqualTo(Short value) {
            addCriterion("buyercredit <>", value, "buyercredit");
            return (Criteria) this;
        }

        public Criteria andBuyercreditGreaterThan(Short value) {
            addCriterion("buyercredit >", value, "buyercredit");
            return (Criteria) this;
        }

        public Criteria andBuyercreditGreaterThanOrEqualTo(Short value) {
            addCriterion("buyercredit >=", value, "buyercredit");
            return (Criteria) this;
        }

        public Criteria andBuyercreditLessThan(Short value) {
            addCriterion("buyercredit <", value, "buyercredit");
            return (Criteria) this;
        }

        public Criteria andBuyercreditLessThanOrEqualTo(Short value) {
            addCriterion("buyercredit <=", value, "buyercredit");
            return (Criteria) this;
        }

        public Criteria andBuyercreditIn(List<Short> values) {
            addCriterion("buyercredit in", values, "buyercredit");
            return (Criteria) this;
        }

        public Criteria andBuyercreditNotIn(List<Short> values) {
            addCriterion("buyercredit not in", values, "buyercredit");
            return (Criteria) this;
        }

        public Criteria andBuyercreditBetween(Short value1, Short value2) {
            addCriterion("buyercredit between", value1, value2, "buyercredit");
            return (Criteria) this;
        }

        public Criteria andBuyercreditNotBetween(Short value1, Short value2) {
            addCriterion("buyercredit not between", value1, value2, "buyercredit");
            return (Criteria) this;
        }

        public Criteria andSellercreditIsNull() {
            addCriterion("sellercredit is null");
            return (Criteria) this;
        }

        public Criteria andSellercreditIsNotNull() {
            addCriterion("sellercredit is not null");
            return (Criteria) this;
        }

        public Criteria andSellercreditEqualTo(Short value) {
            addCriterion("sellercredit =", value, "sellercredit");
            return (Criteria) this;
        }

        public Criteria andSellercreditNotEqualTo(Short value) {
            addCriterion("sellercredit <>", value, "sellercredit");
            return (Criteria) this;
        }

        public Criteria andSellercreditGreaterThan(Short value) {
            addCriterion("sellercredit >", value, "sellercredit");
            return (Criteria) this;
        }

        public Criteria andSellercreditGreaterThanOrEqualTo(Short value) {
            addCriterion("sellercredit >=", value, "sellercredit");
            return (Criteria) this;
        }

        public Criteria andSellercreditLessThan(Short value) {
            addCriterion("sellercredit <", value, "sellercredit");
            return (Criteria) this;
        }

        public Criteria andSellercreditLessThanOrEqualTo(Short value) {
            addCriterion("sellercredit <=", value, "sellercredit");
            return (Criteria) this;
        }

        public Criteria andSellercreditIn(List<Short> values) {
            addCriterion("sellercredit in", values, "sellercredit");
            return (Criteria) this;
        }

        public Criteria andSellercreditNotIn(List<Short> values) {
            addCriterion("sellercredit not in", values, "sellercredit");
            return (Criteria) this;
        }

        public Criteria andSellercreditBetween(Short value1, Short value2) {
            addCriterion("sellercredit between", value1, value2, "sellercredit");
            return (Criteria) this;
        }

        public Criteria andSellercreditNotBetween(Short value1, Short value2) {
            addCriterion("sellercredit not between", value1, value2, "sellercredit");
            return (Criteria) this;
        }

        public Criteria andFavtimesIsNull() {
            addCriterion("favtimes is null");
            return (Criteria) this;
        }

        public Criteria andFavtimesIsNotNull() {
            addCriterion("favtimes is not null");
            return (Criteria) this;
        }

        public Criteria andFavtimesEqualTo(Integer value) {
            addCriterion("favtimes =", value, "favtimes");
            return (Criteria) this;
        }

        public Criteria andFavtimesNotEqualTo(Integer value) {
            addCriterion("favtimes <>", value, "favtimes");
            return (Criteria) this;
        }

        public Criteria andFavtimesGreaterThan(Integer value) {
            addCriterion("favtimes >", value, "favtimes");
            return (Criteria) this;
        }

        public Criteria andFavtimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("favtimes >=", value, "favtimes");
            return (Criteria) this;
        }

        public Criteria andFavtimesLessThan(Integer value) {
            addCriterion("favtimes <", value, "favtimes");
            return (Criteria) this;
        }

        public Criteria andFavtimesLessThanOrEqualTo(Integer value) {
            addCriterion("favtimes <=", value, "favtimes");
            return (Criteria) this;
        }

        public Criteria andFavtimesIn(List<Integer> values) {
            addCriterion("favtimes in", values, "favtimes");
            return (Criteria) this;
        }

        public Criteria andFavtimesNotIn(List<Integer> values) {
            addCriterion("favtimes not in", values, "favtimes");
            return (Criteria) this;
        }

        public Criteria andFavtimesBetween(Integer value1, Integer value2) {
            addCriterion("favtimes between", value1, value2, "favtimes");
            return (Criteria) this;
        }

        public Criteria andFavtimesNotBetween(Integer value1, Integer value2) {
            addCriterion("favtimes not between", value1, value2, "favtimes");
            return (Criteria) this;
        }

        public Criteria andSharetimesIsNull() {
            addCriterion("sharetimes is null");
            return (Criteria) this;
        }

        public Criteria andSharetimesIsNotNull() {
            addCriterion("sharetimes is not null");
            return (Criteria) this;
        }

        public Criteria andSharetimesEqualTo(Integer value) {
            addCriterion("sharetimes =", value, "sharetimes");
            return (Criteria) this;
        }

        public Criteria andSharetimesNotEqualTo(Integer value) {
            addCriterion("sharetimes <>", value, "sharetimes");
            return (Criteria) this;
        }

        public Criteria andSharetimesGreaterThan(Integer value) {
            addCriterion("sharetimes >", value, "sharetimes");
            return (Criteria) this;
        }

        public Criteria andSharetimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("sharetimes >=", value, "sharetimes");
            return (Criteria) this;
        }

        public Criteria andSharetimesLessThan(Integer value) {
            addCriterion("sharetimes <", value, "sharetimes");
            return (Criteria) this;
        }

        public Criteria andSharetimesLessThanOrEqualTo(Integer value) {
            addCriterion("sharetimes <=", value, "sharetimes");
            return (Criteria) this;
        }

        public Criteria andSharetimesIn(List<Integer> values) {
            addCriterion("sharetimes in", values, "sharetimes");
            return (Criteria) this;
        }

        public Criteria andSharetimesNotIn(List<Integer> values) {
            addCriterion("sharetimes not in", values, "sharetimes");
            return (Criteria) this;
        }

        public Criteria andSharetimesBetween(Integer value1, Integer value2) {
            addCriterion("sharetimes between", value1, value2, "sharetimes");
            return (Criteria) this;
        }

        public Criteria andSharetimesNotBetween(Integer value1, Integer value2) {
            addCriterion("sharetimes not between", value1, value2, "sharetimes");
            return (Criteria) this;
        }

        public Criteria andProfileprogressIsNull() {
            addCriterion("profileprogress is null");
            return (Criteria) this;
        }

        public Criteria andProfileprogressIsNotNull() {
            addCriterion("profileprogress is not null");
            return (Criteria) this;
        }

        public Criteria andProfileprogressEqualTo(Byte value) {
            addCriterion("profileprogress =", value, "profileprogress");
            return (Criteria) this;
        }

        public Criteria andProfileprogressNotEqualTo(Byte value) {
            addCriterion("profileprogress <>", value, "profileprogress");
            return (Criteria) this;
        }

        public Criteria andProfileprogressGreaterThan(Byte value) {
            addCriterion("profileprogress >", value, "profileprogress");
            return (Criteria) this;
        }

        public Criteria andProfileprogressGreaterThanOrEqualTo(Byte value) {
            addCriterion("profileprogress >=", value, "profileprogress");
            return (Criteria) this;
        }

        public Criteria andProfileprogressLessThan(Byte value) {
            addCriterion("profileprogress <", value, "profileprogress");
            return (Criteria) this;
        }

        public Criteria andProfileprogressLessThanOrEqualTo(Byte value) {
            addCriterion("profileprogress <=", value, "profileprogress");
            return (Criteria) this;
        }

        public Criteria andProfileprogressIn(List<Byte> values) {
            addCriterion("profileprogress in", values, "profileprogress");
            return (Criteria) this;
        }

        public Criteria andProfileprogressNotIn(List<Byte> values) {
            addCriterion("profileprogress not in", values, "profileprogress");
            return (Criteria) this;
        }

        public Criteria andProfileprogressBetween(Byte value1, Byte value2) {
            addCriterion("profileprogress between", value1, value2, "profileprogress");
            return (Criteria) this;
        }

        public Criteria andProfileprogressNotBetween(Byte value1, Byte value2) {
            addCriterion("profileprogress not between", value1, value2, "profileprogress");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}