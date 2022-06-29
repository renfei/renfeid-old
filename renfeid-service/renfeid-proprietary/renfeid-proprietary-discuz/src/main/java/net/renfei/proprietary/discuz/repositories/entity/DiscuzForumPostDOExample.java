package net.renfei.proprietary.discuz.repositories.entity;

import java.util.ArrayList;
import java.util.List;

public class DiscuzForumPostDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DiscuzForumPostDOExample() {
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

        public Criteria andTidIsNull() {
            addCriterion("tid is null");
            return (Criteria) this;
        }

        public Criteria andTidIsNotNull() {
            addCriterion("tid is not null");
            return (Criteria) this;
        }

        public Criteria andTidEqualTo(Integer value) {
            addCriterion("tid =", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotEqualTo(Integer value) {
            addCriterion("tid <>", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThan(Integer value) {
            addCriterion("tid >", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThanOrEqualTo(Integer value) {
            addCriterion("tid >=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThan(Integer value) {
            addCriterion("tid <", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThanOrEqualTo(Integer value) {
            addCriterion("tid <=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidIn(List<Integer> values) {
            addCriterion("tid in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotIn(List<Integer> values) {
            addCriterion("tid not in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidBetween(Integer value1, Integer value2) {
            addCriterion("tid between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotBetween(Integer value1, Integer value2) {
            addCriterion("tid not between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("position is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("position is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(Integer value) {
            addCriterion("position =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(Integer value) {
            addCriterion("position <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(Integer value) {
            addCriterion("position >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(Integer value) {
            addCriterion("position >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(Integer value) {
            addCriterion("position <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(Integer value) {
            addCriterion("position <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<Integer> values) {
            addCriterion("position in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<Integer> values) {
            addCriterion("position not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(Integer value1, Integer value2) {
            addCriterion("position between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(Integer value1, Integer value2) {
            addCriterion("position not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Integer value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Integer value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Integer value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Integer value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Integer value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Integer> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Integer> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Integer value1, Integer value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Integer value1, Integer value2) {
            addCriterion("pid not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andFidIsNull() {
            addCriterion("fid is null");
            return (Criteria) this;
        }

        public Criteria andFidIsNotNull() {
            addCriterion("fid is not null");
            return (Criteria) this;
        }

        public Criteria andFidEqualTo(Integer value) {
            addCriterion("fid =", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotEqualTo(Integer value) {
            addCriterion("fid <>", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidGreaterThan(Integer value) {
            addCriterion("fid >", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidGreaterThanOrEqualTo(Integer value) {
            addCriterion("fid >=", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidLessThan(Integer value) {
            addCriterion("fid <", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidLessThanOrEqualTo(Integer value) {
            addCriterion("fid <=", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidIn(List<Integer> values) {
            addCriterion("fid in", values, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotIn(List<Integer> values) {
            addCriterion("fid not in", values, "fid");
            return (Criteria) this;
        }

        public Criteria andFidBetween(Integer value1, Integer value2) {
            addCriterion("fid between", value1, value2, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotBetween(Integer value1, Integer value2) {
            addCriterion("fid not between", value1, value2, "fid");
            return (Criteria) this;
        }

        public Criteria andFirstIsNull() {
            addCriterion("first is null");
            return (Criteria) this;
        }

        public Criteria andFirstIsNotNull() {
            addCriterion("first is not null");
            return (Criteria) this;
        }

        public Criteria andFirstEqualTo(Integer value) {
            addCriterion("first =", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstNotEqualTo(Integer value) {
            addCriterion("first <>", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstGreaterThan(Integer value) {
            addCriterion("first >", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstGreaterThanOrEqualTo(Integer value) {
            addCriterion("first >=", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstLessThan(Integer value) {
            addCriterion("first <", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstLessThanOrEqualTo(Integer value) {
            addCriterion("first <=", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstIn(List<Integer> values) {
            addCriterion("first in", values, "first");
            return (Criteria) this;
        }

        public Criteria andFirstNotIn(List<Integer> values) {
            addCriterion("first not in", values, "first");
            return (Criteria) this;
        }

        public Criteria andFirstBetween(Integer value1, Integer value2) {
            addCriterion("first between", value1, value2, "first");
            return (Criteria) this;
        }

        public Criteria andFirstNotBetween(Integer value1, Integer value2) {
            addCriterion("first not between", value1, value2, "first");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthoridIsNull() {
            addCriterion("authorid is null");
            return (Criteria) this;
        }

        public Criteria andAuthoridIsNotNull() {
            addCriterion("authorid is not null");
            return (Criteria) this;
        }

        public Criteria andAuthoridEqualTo(Integer value) {
            addCriterion("authorid =", value, "authorid");
            return (Criteria) this;
        }

        public Criteria andAuthoridNotEqualTo(Integer value) {
            addCriterion("authorid <>", value, "authorid");
            return (Criteria) this;
        }

        public Criteria andAuthoridGreaterThan(Integer value) {
            addCriterion("authorid >", value, "authorid");
            return (Criteria) this;
        }

        public Criteria andAuthoridGreaterThanOrEqualTo(Integer value) {
            addCriterion("authorid >=", value, "authorid");
            return (Criteria) this;
        }

        public Criteria andAuthoridLessThan(Integer value) {
            addCriterion("authorid <", value, "authorid");
            return (Criteria) this;
        }

        public Criteria andAuthoridLessThanOrEqualTo(Integer value) {
            addCriterion("authorid <=", value, "authorid");
            return (Criteria) this;
        }

        public Criteria andAuthoridIn(List<Integer> values) {
            addCriterion("authorid in", values, "authorid");
            return (Criteria) this;
        }

        public Criteria andAuthoridNotIn(List<Integer> values) {
            addCriterion("authorid not in", values, "authorid");
            return (Criteria) this;
        }

        public Criteria andAuthoridBetween(Integer value1, Integer value2) {
            addCriterion("authorid between", value1, value2, "authorid");
            return (Criteria) this;
        }

        public Criteria andAuthoridNotBetween(Integer value1, Integer value2) {
            addCriterion("authorid not between", value1, value2, "authorid");
            return (Criteria) this;
        }

        public Criteria andSubjectIsNull() {
            addCriterion("subject is null");
            return (Criteria) this;
        }

        public Criteria andSubjectIsNotNull() {
            addCriterion("subject is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectEqualTo(String value) {
            addCriterion("subject =", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotEqualTo(String value) {
            addCriterion("subject <>", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThan(String value) {
            addCriterion("subject >", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("subject >=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThan(String value) {
            addCriterion("subject <", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThanOrEqualTo(String value) {
            addCriterion("subject <=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLike(String value) {
            addCriterion("subject like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotLike(String value) {
            addCriterion("subject not like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectIn(List<String> values) {
            addCriterion("subject in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotIn(List<String> values) {
            addCriterion("subject not in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectBetween(String value1, String value2) {
            addCriterion("subject between", value1, value2, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotBetween(String value1, String value2) {
            addCriterion("subject not between", value1, value2, "subject");
            return (Criteria) this;
        }

        public Criteria andDatelineIsNull() {
            addCriterion("dateline is null");
            return (Criteria) this;
        }

        public Criteria andDatelineIsNotNull() {
            addCriterion("dateline is not null");
            return (Criteria) this;
        }

        public Criteria andDatelineEqualTo(Integer value) {
            addCriterion("dateline =", value, "dateline");
            return (Criteria) this;
        }

        public Criteria andDatelineNotEqualTo(Integer value) {
            addCriterion("dateline <>", value, "dateline");
            return (Criteria) this;
        }

        public Criteria andDatelineGreaterThan(Integer value) {
            addCriterion("dateline >", value, "dateline");
            return (Criteria) this;
        }

        public Criteria andDatelineGreaterThanOrEqualTo(Integer value) {
            addCriterion("dateline >=", value, "dateline");
            return (Criteria) this;
        }

        public Criteria andDatelineLessThan(Integer value) {
            addCriterion("dateline <", value, "dateline");
            return (Criteria) this;
        }

        public Criteria andDatelineLessThanOrEqualTo(Integer value) {
            addCriterion("dateline <=", value, "dateline");
            return (Criteria) this;
        }

        public Criteria andDatelineIn(List<Integer> values) {
            addCriterion("dateline in", values, "dateline");
            return (Criteria) this;
        }

        public Criteria andDatelineNotIn(List<Integer> values) {
            addCriterion("dateline not in", values, "dateline");
            return (Criteria) this;
        }

        public Criteria andDatelineBetween(Integer value1, Integer value2) {
            addCriterion("dateline between", value1, value2, "dateline");
            return (Criteria) this;
        }

        public Criteria andDatelineNotBetween(Integer value1, Integer value2) {
            addCriterion("dateline not between", value1, value2, "dateline");
            return (Criteria) this;
        }

        public Criteria andUseipIsNull() {
            addCriterion("useip is null");
            return (Criteria) this;
        }

        public Criteria andUseipIsNotNull() {
            addCriterion("useip is not null");
            return (Criteria) this;
        }

        public Criteria andUseipEqualTo(String value) {
            addCriterion("useip =", value, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipNotEqualTo(String value) {
            addCriterion("useip <>", value, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipGreaterThan(String value) {
            addCriterion("useip >", value, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipGreaterThanOrEqualTo(String value) {
            addCriterion("useip >=", value, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipLessThan(String value) {
            addCriterion("useip <", value, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipLessThanOrEqualTo(String value) {
            addCriterion("useip <=", value, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipLike(String value) {
            addCriterion("useip like", value, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipNotLike(String value) {
            addCriterion("useip not like", value, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipIn(List<String> values) {
            addCriterion("useip in", values, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipNotIn(List<String> values) {
            addCriterion("useip not in", values, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipBetween(String value1, String value2) {
            addCriterion("useip between", value1, value2, "useip");
            return (Criteria) this;
        }

        public Criteria andUseipNotBetween(String value1, String value2) {
            addCriterion("useip not between", value1, value2, "useip");
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

        public Criteria andPortEqualTo(Integer value) {
            addCriterion("port =", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotEqualTo(Integer value) {
            addCriterion("port <>", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThan(Integer value) {
            addCriterion("port >", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("port >=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThan(Integer value) {
            addCriterion("port <", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThanOrEqualTo(Integer value) {
            addCriterion("port <=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortIn(List<Integer> values) {
            addCriterion("port in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotIn(List<Integer> values) {
            addCriterion("port not in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortBetween(Integer value1, Integer value2) {
            addCriterion("port between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotBetween(Integer value1, Integer value2) {
            addCriterion("port not between", value1, value2, "port");
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

        public Criteria andAnonymousIsNull() {
            addCriterion("anonymous is null");
            return (Criteria) this;
        }

        public Criteria andAnonymousIsNotNull() {
            addCriterion("anonymous is not null");
            return (Criteria) this;
        }

        public Criteria andAnonymousEqualTo(Integer value) {
            addCriterion("anonymous =", value, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousNotEqualTo(Integer value) {
            addCriterion("anonymous <>", value, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousGreaterThan(Integer value) {
            addCriterion("anonymous >", value, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousGreaterThanOrEqualTo(Integer value) {
            addCriterion("anonymous >=", value, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousLessThan(Integer value) {
            addCriterion("anonymous <", value, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousLessThanOrEqualTo(Integer value) {
            addCriterion("anonymous <=", value, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousIn(List<Integer> values) {
            addCriterion("anonymous in", values, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousNotIn(List<Integer> values) {
            addCriterion("anonymous not in", values, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousBetween(Integer value1, Integer value2) {
            addCriterion("anonymous between", value1, value2, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousNotBetween(Integer value1, Integer value2) {
            addCriterion("anonymous not between", value1, value2, "anonymous");
            return (Criteria) this;
        }

        public Criteria andUsesigIsNull() {
            addCriterion("usesig is null");
            return (Criteria) this;
        }

        public Criteria andUsesigIsNotNull() {
            addCriterion("usesig is not null");
            return (Criteria) this;
        }

        public Criteria andUsesigEqualTo(Integer value) {
            addCriterion("usesig =", value, "usesig");
            return (Criteria) this;
        }

        public Criteria andUsesigNotEqualTo(Integer value) {
            addCriterion("usesig <>", value, "usesig");
            return (Criteria) this;
        }

        public Criteria andUsesigGreaterThan(Integer value) {
            addCriterion("usesig >", value, "usesig");
            return (Criteria) this;
        }

        public Criteria andUsesigGreaterThanOrEqualTo(Integer value) {
            addCriterion("usesig >=", value, "usesig");
            return (Criteria) this;
        }

        public Criteria andUsesigLessThan(Integer value) {
            addCriterion("usesig <", value, "usesig");
            return (Criteria) this;
        }

        public Criteria andUsesigLessThanOrEqualTo(Integer value) {
            addCriterion("usesig <=", value, "usesig");
            return (Criteria) this;
        }

        public Criteria andUsesigIn(List<Integer> values) {
            addCriterion("usesig in", values, "usesig");
            return (Criteria) this;
        }

        public Criteria andUsesigNotIn(List<Integer> values) {
            addCriterion("usesig not in", values, "usesig");
            return (Criteria) this;
        }

        public Criteria andUsesigBetween(Integer value1, Integer value2) {
            addCriterion("usesig between", value1, value2, "usesig");
            return (Criteria) this;
        }

        public Criteria andUsesigNotBetween(Integer value1, Integer value2) {
            addCriterion("usesig not between", value1, value2, "usesig");
            return (Criteria) this;
        }

        public Criteria andHtmlonIsNull() {
            addCriterion("htmlon is null");
            return (Criteria) this;
        }

        public Criteria andHtmlonIsNotNull() {
            addCriterion("htmlon is not null");
            return (Criteria) this;
        }

        public Criteria andHtmlonEqualTo(Integer value) {
            addCriterion("htmlon =", value, "htmlon");
            return (Criteria) this;
        }

        public Criteria andHtmlonNotEqualTo(Integer value) {
            addCriterion("htmlon <>", value, "htmlon");
            return (Criteria) this;
        }

        public Criteria andHtmlonGreaterThan(Integer value) {
            addCriterion("htmlon >", value, "htmlon");
            return (Criteria) this;
        }

        public Criteria andHtmlonGreaterThanOrEqualTo(Integer value) {
            addCriterion("htmlon >=", value, "htmlon");
            return (Criteria) this;
        }

        public Criteria andHtmlonLessThan(Integer value) {
            addCriterion("htmlon <", value, "htmlon");
            return (Criteria) this;
        }

        public Criteria andHtmlonLessThanOrEqualTo(Integer value) {
            addCriterion("htmlon <=", value, "htmlon");
            return (Criteria) this;
        }

        public Criteria andHtmlonIn(List<Integer> values) {
            addCriterion("htmlon in", values, "htmlon");
            return (Criteria) this;
        }

        public Criteria andHtmlonNotIn(List<Integer> values) {
            addCriterion("htmlon not in", values, "htmlon");
            return (Criteria) this;
        }

        public Criteria andHtmlonBetween(Integer value1, Integer value2) {
            addCriterion("htmlon between", value1, value2, "htmlon");
            return (Criteria) this;
        }

        public Criteria andHtmlonNotBetween(Integer value1, Integer value2) {
            addCriterion("htmlon not between", value1, value2, "htmlon");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffIsNull() {
            addCriterion("bbcodeoff is null");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffIsNotNull() {
            addCriterion("bbcodeoff is not null");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffEqualTo(Integer value) {
            addCriterion("bbcodeoff =", value, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffNotEqualTo(Integer value) {
            addCriterion("bbcodeoff <>", value, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffGreaterThan(Integer value) {
            addCriterion("bbcodeoff >", value, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffGreaterThanOrEqualTo(Integer value) {
            addCriterion("bbcodeoff >=", value, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffLessThan(Integer value) {
            addCriterion("bbcodeoff <", value, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffLessThanOrEqualTo(Integer value) {
            addCriterion("bbcodeoff <=", value, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffIn(List<Integer> values) {
            addCriterion("bbcodeoff in", values, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffNotIn(List<Integer> values) {
            addCriterion("bbcodeoff not in", values, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffBetween(Integer value1, Integer value2) {
            addCriterion("bbcodeoff between", value1, value2, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andBbcodeoffNotBetween(Integer value1, Integer value2) {
            addCriterion("bbcodeoff not between", value1, value2, "bbcodeoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffIsNull() {
            addCriterion("smileyoff is null");
            return (Criteria) this;
        }

        public Criteria andSmileyoffIsNotNull() {
            addCriterion("smileyoff is not null");
            return (Criteria) this;
        }

        public Criteria andSmileyoffEqualTo(Integer value) {
            addCriterion("smileyoff =", value, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffNotEqualTo(Integer value) {
            addCriterion("smileyoff <>", value, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffGreaterThan(Integer value) {
            addCriterion("smileyoff >", value, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffGreaterThanOrEqualTo(Integer value) {
            addCriterion("smileyoff >=", value, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffLessThan(Integer value) {
            addCriterion("smileyoff <", value, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffLessThanOrEqualTo(Integer value) {
            addCriterion("smileyoff <=", value, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffIn(List<Integer> values) {
            addCriterion("smileyoff in", values, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffNotIn(List<Integer> values) {
            addCriterion("smileyoff not in", values, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffBetween(Integer value1, Integer value2) {
            addCriterion("smileyoff between", value1, value2, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andSmileyoffNotBetween(Integer value1, Integer value2) {
            addCriterion("smileyoff not between", value1, value2, "smileyoff");
            return (Criteria) this;
        }

        public Criteria andParseurloffIsNull() {
            addCriterion("parseurloff is null");
            return (Criteria) this;
        }

        public Criteria andParseurloffIsNotNull() {
            addCriterion("parseurloff is not null");
            return (Criteria) this;
        }

        public Criteria andParseurloffEqualTo(Integer value) {
            addCriterion("parseurloff =", value, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andParseurloffNotEqualTo(Integer value) {
            addCriterion("parseurloff <>", value, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andParseurloffGreaterThan(Integer value) {
            addCriterion("parseurloff >", value, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andParseurloffGreaterThanOrEqualTo(Integer value) {
            addCriterion("parseurloff >=", value, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andParseurloffLessThan(Integer value) {
            addCriterion("parseurloff <", value, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andParseurloffLessThanOrEqualTo(Integer value) {
            addCriterion("parseurloff <=", value, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andParseurloffIn(List<Integer> values) {
            addCriterion("parseurloff in", values, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andParseurloffNotIn(List<Integer> values) {
            addCriterion("parseurloff not in", values, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andParseurloffBetween(Integer value1, Integer value2) {
            addCriterion("parseurloff between", value1, value2, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andParseurloffNotBetween(Integer value1, Integer value2) {
            addCriterion("parseurloff not between", value1, value2, "parseurloff");
            return (Criteria) this;
        }

        public Criteria andAttachmentIsNull() {
            addCriterion("attachment is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentIsNotNull() {
            addCriterion("attachment is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentEqualTo(Integer value) {
            addCriterion("attachment =", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotEqualTo(Integer value) {
            addCriterion("attachment <>", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentGreaterThan(Integer value) {
            addCriterion("attachment >", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentGreaterThanOrEqualTo(Integer value) {
            addCriterion("attachment >=", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentLessThan(Integer value) {
            addCriterion("attachment <", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentLessThanOrEqualTo(Integer value) {
            addCriterion("attachment <=", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentIn(List<Integer> values) {
            addCriterion("attachment in", values, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotIn(List<Integer> values) {
            addCriterion("attachment not in", values, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentBetween(Integer value1, Integer value2) {
            addCriterion("attachment between", value1, value2, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotBetween(Integer value1, Integer value2) {
            addCriterion("attachment not between", value1, value2, "attachment");
            return (Criteria) this;
        }

        public Criteria andRateIsNull() {
            addCriterion("rate is null");
            return (Criteria) this;
        }

        public Criteria andRateIsNotNull() {
            addCriterion("rate is not null");
            return (Criteria) this;
        }

        public Criteria andRateEqualTo(Short value) {
            addCriterion("rate =", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotEqualTo(Short value) {
            addCriterion("rate <>", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThan(Short value) {
            addCriterion("rate >", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThanOrEqualTo(Short value) {
            addCriterion("rate >=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThan(Short value) {
            addCriterion("rate <", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThanOrEqualTo(Short value) {
            addCriterion("rate <=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateIn(List<Short> values) {
            addCriterion("rate in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotIn(List<Short> values) {
            addCriterion("rate not in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateBetween(Short value1, Short value2) {
            addCriterion("rate between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotBetween(Short value1, Short value2) {
            addCriterion("rate not between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRatetimesIsNull() {
            addCriterion("ratetimes is null");
            return (Criteria) this;
        }

        public Criteria andRatetimesIsNotNull() {
            addCriterion("ratetimes is not null");
            return (Criteria) this;
        }

        public Criteria andRatetimesEqualTo(Integer value) {
            addCriterion("ratetimes =", value, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andRatetimesNotEqualTo(Integer value) {
            addCriterion("ratetimes <>", value, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andRatetimesGreaterThan(Integer value) {
            addCriterion("ratetimes >", value, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andRatetimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("ratetimes >=", value, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andRatetimesLessThan(Integer value) {
            addCriterion("ratetimes <", value, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andRatetimesLessThanOrEqualTo(Integer value) {
            addCriterion("ratetimes <=", value, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andRatetimesIn(List<Integer> values) {
            addCriterion("ratetimes in", values, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andRatetimesNotIn(List<Integer> values) {
            addCriterion("ratetimes not in", values, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andRatetimesBetween(Integer value1, Integer value2) {
            addCriterion("ratetimes between", value1, value2, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andRatetimesNotBetween(Integer value1, Integer value2) {
            addCriterion("ratetimes not between", value1, value2, "ratetimes");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTagsIsNull() {
            addCriterion("tags is null");
            return (Criteria) this;
        }

        public Criteria andTagsIsNotNull() {
            addCriterion("tags is not null");
            return (Criteria) this;
        }

        public Criteria andTagsEqualTo(String value) {
            addCriterion("tags =", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotEqualTo(String value) {
            addCriterion("tags <>", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThan(String value) {
            addCriterion("tags >", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThanOrEqualTo(String value) {
            addCriterion("tags >=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThan(String value) {
            addCriterion("tags <", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThanOrEqualTo(String value) {
            addCriterion("tags <=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLike(String value) {
            addCriterion("tags like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotLike(String value) {
            addCriterion("tags not like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsIn(List<String> values) {
            addCriterion("tags in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotIn(List<String> values) {
            addCriterion("tags not in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsBetween(String value1, String value2) {
            addCriterion("tags between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotBetween(String value1, String value2) {
            addCriterion("tags not between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(Integer value) {
            addCriterion("comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(Integer value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(Integer value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(Integer value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(Integer value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<Integer> values) {
            addCriterion("comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<Integer> values) {
            addCriterion("comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(Integer value1, Integer value2) {
            addCriterion("comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(Integer value1, Integer value2) {
            addCriterion("comment not between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andReplycreditIsNull() {
            addCriterion("replycredit is null");
            return (Criteria) this;
        }

        public Criteria andReplycreditIsNotNull() {
            addCriterion("replycredit is not null");
            return (Criteria) this;
        }

        public Criteria andReplycreditEqualTo(Integer value) {
            addCriterion("replycredit =", value, "replycredit");
            return (Criteria) this;
        }

        public Criteria andReplycreditNotEqualTo(Integer value) {
            addCriterion("replycredit <>", value, "replycredit");
            return (Criteria) this;
        }

        public Criteria andReplycreditGreaterThan(Integer value) {
            addCriterion("replycredit >", value, "replycredit");
            return (Criteria) this;
        }

        public Criteria andReplycreditGreaterThanOrEqualTo(Integer value) {
            addCriterion("replycredit >=", value, "replycredit");
            return (Criteria) this;
        }

        public Criteria andReplycreditLessThan(Integer value) {
            addCriterion("replycredit <", value, "replycredit");
            return (Criteria) this;
        }

        public Criteria andReplycreditLessThanOrEqualTo(Integer value) {
            addCriterion("replycredit <=", value, "replycredit");
            return (Criteria) this;
        }

        public Criteria andReplycreditIn(List<Integer> values) {
            addCriterion("replycredit in", values, "replycredit");
            return (Criteria) this;
        }

        public Criteria andReplycreditNotIn(List<Integer> values) {
            addCriterion("replycredit not in", values, "replycredit");
            return (Criteria) this;
        }

        public Criteria andReplycreditBetween(Integer value1, Integer value2) {
            addCriterion("replycredit between", value1, value2, "replycredit");
            return (Criteria) this;
        }

        public Criteria andReplycreditNotBetween(Integer value1, Integer value2) {
            addCriterion("replycredit not between", value1, value2, "replycredit");
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