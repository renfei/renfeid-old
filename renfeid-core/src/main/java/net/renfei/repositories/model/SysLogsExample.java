package net.renfei.repositories.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysLogsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysLogsExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("`id` is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("`id` is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("`id` =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("`id` <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("`id` >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`id` >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("`id` <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("`id` <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("`id` in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("`id` not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("`id` between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("`id` not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLogTimeIsNull() {
            addCriterion("`log_time` is null");
            return (Criteria) this;
        }

        public Criteria andLogTimeIsNotNull() {
            addCriterion("`log_time` is not null");
            return (Criteria) this;
        }

        public Criteria andLogTimeEqualTo(Date value) {
            addCriterion("`log_time` =", value, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeNotEqualTo(Date value) {
            addCriterion("`log_time` <>", value, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeGreaterThan(Date value) {
            addCriterion("`log_time` >", value, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`log_time` >=", value, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeLessThan(Date value) {
            addCriterion("`log_time` <", value, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeLessThanOrEqualTo(Date value) {
            addCriterion("`log_time` <=", value, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeIn(List<Date> values) {
            addCriterion("`log_time` in", values, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeNotIn(List<Date> values) {
            addCriterion("`log_time` not in", values, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeBetween(Date value1, Date value2) {
            addCriterion("`log_time` between", value1, value2, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeNotBetween(Date value1, Date value2) {
            addCriterion("`log_time` not between", value1, value2, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogLevelIsNull() {
            addCriterion("`log_level` is null");
            return (Criteria) this;
        }

        public Criteria andLogLevelIsNotNull() {
            addCriterion("`log_level` is not null");
            return (Criteria) this;
        }

        public Criteria andLogLevelEqualTo(String value) {
            addCriterion("`log_level` =", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelNotEqualTo(String value) {
            addCriterion("`log_level` <>", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelGreaterThan(String value) {
            addCriterion("`log_level` >", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelGreaterThanOrEqualTo(String value) {
            addCriterion("`log_level` >=", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelLessThan(String value) {
            addCriterion("`log_level` <", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelLessThanOrEqualTo(String value) {
            addCriterion("`log_level` <=", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelLike(String value) {
            addCriterion("`log_level` like", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelNotLike(String value) {
            addCriterion("`log_level` not like", value, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelIn(List<String> values) {
            addCriterion("`log_level` in", values, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelNotIn(List<String> values) {
            addCriterion("`log_level` not in", values, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelBetween(String value1, String value2) {
            addCriterion("`log_level` between", value1, value2, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogLevelNotBetween(String value1, String value2) {
            addCriterion("`log_level` not between", value1, value2, "logLevel");
            return (Criteria) this;
        }

        public Criteria andLogModuleIsNull() {
            addCriterion("`log_module` is null");
            return (Criteria) this;
        }

        public Criteria andLogModuleIsNotNull() {
            addCriterion("`log_module` is not null");
            return (Criteria) this;
        }

        public Criteria andLogModuleEqualTo(String value) {
            addCriterion("`log_module` =", value, "logModule");
            return (Criteria) this;
        }

        public Criteria andLogModuleNotEqualTo(String value) {
            addCriterion("`log_module` <>", value, "logModule");
            return (Criteria) this;
        }

        public Criteria andLogModuleGreaterThan(String value) {
            addCriterion("`log_module` >", value, "logModule");
            return (Criteria) this;
        }

        public Criteria andLogModuleGreaterThanOrEqualTo(String value) {
            addCriterion("`log_module` >=", value, "logModule");
            return (Criteria) this;
        }

        public Criteria andLogModuleLessThan(String value) {
            addCriterion("`log_module` <", value, "logModule");
            return (Criteria) this;
        }

        public Criteria andLogModuleLessThanOrEqualTo(String value) {
            addCriterion("`log_module` <=", value, "logModule");
            return (Criteria) this;
        }

        public Criteria andLogModuleLike(String value) {
            addCriterion("`log_module` like", value, "logModule");
            return (Criteria) this;
        }

        public Criteria andLogModuleNotLike(String value) {
            addCriterion("`log_module` not like", value, "logModule");
            return (Criteria) this;
        }

        public Criteria andLogModuleIn(List<String> values) {
            addCriterion("`log_module` in", values, "logModule");
            return (Criteria) this;
        }

        public Criteria andLogModuleNotIn(List<String> values) {
            addCriterion("`log_module` not in", values, "logModule");
            return (Criteria) this;
        }

        public Criteria andLogModuleBetween(String value1, String value2) {
            addCriterion("`log_module` between", value1, value2, "logModule");
            return (Criteria) this;
        }

        public Criteria andLogModuleNotBetween(String value1, String value2) {
            addCriterion("`log_module` not between", value1, value2, "logModule");
            return (Criteria) this;
        }

        public Criteria andLogTypeIsNull() {
            addCriterion("`log_type` is null");
            return (Criteria) this;
        }

        public Criteria andLogTypeIsNotNull() {
            addCriterion("`log_type` is not null");
            return (Criteria) this;
        }

        public Criteria andLogTypeEqualTo(String value) {
            addCriterion("`log_type` =", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeNotEqualTo(String value) {
            addCriterion("`log_type` <>", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeGreaterThan(String value) {
            addCriterion("`log_type` >", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`log_type` >=", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeLessThan(String value) {
            addCriterion("`log_type` <", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeLessThanOrEqualTo(String value) {
            addCriterion("`log_type` <=", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeLike(String value) {
            addCriterion("`log_type` like", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeNotLike(String value) {
            addCriterion("`log_type` not like", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeIn(List<String> values) {
            addCriterion("`log_type` in", values, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeNotIn(List<String> values) {
            addCriterion("`log_type` not in", values, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeBetween(String value1, String value2) {
            addCriterion("`log_type` between", value1, value2, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeNotBetween(String value1, String value2) {
            addCriterion("`log_type` not between", value1, value2, "logType");
            return (Criteria) this;
        }

        public Criteria andRequMethodIsNull() {
            addCriterion("`requ_method` is null");
            return (Criteria) this;
        }

        public Criteria andRequMethodIsNotNull() {
            addCriterion("`requ_method` is not null");
            return (Criteria) this;
        }

        public Criteria andRequMethodEqualTo(String value) {
            addCriterion("`requ_method` =", value, "requMethod");
            return (Criteria) this;
        }

        public Criteria andRequMethodNotEqualTo(String value) {
            addCriterion("`requ_method` <>", value, "requMethod");
            return (Criteria) this;
        }

        public Criteria andRequMethodGreaterThan(String value) {
            addCriterion("`requ_method` >", value, "requMethod");
            return (Criteria) this;
        }

        public Criteria andRequMethodGreaterThanOrEqualTo(String value) {
            addCriterion("`requ_method` >=", value, "requMethod");
            return (Criteria) this;
        }

        public Criteria andRequMethodLessThan(String value) {
            addCriterion("`requ_method` <", value, "requMethod");
            return (Criteria) this;
        }

        public Criteria andRequMethodLessThanOrEqualTo(String value) {
            addCriterion("`requ_method` <=", value, "requMethod");
            return (Criteria) this;
        }

        public Criteria andRequMethodLike(String value) {
            addCriterion("`requ_method` like", value, "requMethod");
            return (Criteria) this;
        }

        public Criteria andRequMethodNotLike(String value) {
            addCriterion("`requ_method` not like", value, "requMethod");
            return (Criteria) this;
        }

        public Criteria andRequMethodIn(List<String> values) {
            addCriterion("`requ_method` in", values, "requMethod");
            return (Criteria) this;
        }

        public Criteria andRequMethodNotIn(List<String> values) {
            addCriterion("`requ_method` not in", values, "requMethod");
            return (Criteria) this;
        }

        public Criteria andRequMethodBetween(String value1, String value2) {
            addCriterion("`requ_method` between", value1, value2, "requMethod");
            return (Criteria) this;
        }

        public Criteria andRequMethodNotBetween(String value1, String value2) {
            addCriterion("`requ_method` not between", value1, value2, "requMethod");
            return (Criteria) this;
        }

        public Criteria andRequUriIsNull() {
            addCriterion("`requ_uri` is null");
            return (Criteria) this;
        }

        public Criteria andRequUriIsNotNull() {
            addCriterion("`requ_uri` is not null");
            return (Criteria) this;
        }

        public Criteria andRequUriEqualTo(String value) {
            addCriterion("`requ_uri` =", value, "requUri");
            return (Criteria) this;
        }

        public Criteria andRequUriNotEqualTo(String value) {
            addCriterion("`requ_uri` <>", value, "requUri");
            return (Criteria) this;
        }

        public Criteria andRequUriGreaterThan(String value) {
            addCriterion("`requ_uri` >", value, "requUri");
            return (Criteria) this;
        }

        public Criteria andRequUriGreaterThanOrEqualTo(String value) {
            addCriterion("`requ_uri` >=", value, "requUri");
            return (Criteria) this;
        }

        public Criteria andRequUriLessThan(String value) {
            addCriterion("`requ_uri` <", value, "requUri");
            return (Criteria) this;
        }

        public Criteria andRequUriLessThanOrEqualTo(String value) {
            addCriterion("`requ_uri` <=", value, "requUri");
            return (Criteria) this;
        }

        public Criteria andRequUriLike(String value) {
            addCriterion("`requ_uri` like", value, "requUri");
            return (Criteria) this;
        }

        public Criteria andRequUriNotLike(String value) {
            addCriterion("`requ_uri` not like", value, "requUri");
            return (Criteria) this;
        }

        public Criteria andRequUriIn(List<String> values) {
            addCriterion("`requ_uri` in", values, "requUri");
            return (Criteria) this;
        }

        public Criteria andRequUriNotIn(List<String> values) {
            addCriterion("`requ_uri` not in", values, "requUri");
            return (Criteria) this;
        }

        public Criteria andRequUriBetween(String value1, String value2) {
            addCriterion("`requ_uri` between", value1, value2, "requUri");
            return (Criteria) this;
        }

        public Criteria andRequUriNotBetween(String value1, String value2) {
            addCriterion("`requ_uri` not between", value1, value2, "requUri");
            return (Criteria) this;
        }

        public Criteria andRequReferrerIsNull() {
            addCriterion("`requ_referrer` is null");
            return (Criteria) this;
        }

        public Criteria andRequReferrerIsNotNull() {
            addCriterion("`requ_referrer` is not null");
            return (Criteria) this;
        }

        public Criteria andRequReferrerEqualTo(String value) {
            addCriterion("`requ_referrer` =", value, "requReferrer");
            return (Criteria) this;
        }

        public Criteria andRequReferrerNotEqualTo(String value) {
            addCriterion("`requ_referrer` <>", value, "requReferrer");
            return (Criteria) this;
        }

        public Criteria andRequReferrerGreaterThan(String value) {
            addCriterion("`requ_referrer` >", value, "requReferrer");
            return (Criteria) this;
        }

        public Criteria andRequReferrerGreaterThanOrEqualTo(String value) {
            addCriterion("`requ_referrer` >=", value, "requReferrer");
            return (Criteria) this;
        }

        public Criteria andRequReferrerLessThan(String value) {
            addCriterion("`requ_referrer` <", value, "requReferrer");
            return (Criteria) this;
        }

        public Criteria andRequReferrerLessThanOrEqualTo(String value) {
            addCriterion("`requ_referrer` <=", value, "requReferrer");
            return (Criteria) this;
        }

        public Criteria andRequReferrerLike(String value) {
            addCriterion("`requ_referrer` like", value, "requReferrer");
            return (Criteria) this;
        }

        public Criteria andRequReferrerNotLike(String value) {
            addCriterion("`requ_referrer` not like", value, "requReferrer");
            return (Criteria) this;
        }

        public Criteria andRequReferrerIn(List<String> values) {
            addCriterion("`requ_referrer` in", values, "requReferrer");
            return (Criteria) this;
        }

        public Criteria andRequReferrerNotIn(List<String> values) {
            addCriterion("`requ_referrer` not in", values, "requReferrer");
            return (Criteria) this;
        }

        public Criteria andRequReferrerBetween(String value1, String value2) {
            addCriterion("`requ_referrer` between", value1, value2, "requReferrer");
            return (Criteria) this;
        }

        public Criteria andRequReferrerNotBetween(String value1, String value2) {
            addCriterion("`requ_referrer` not between", value1, value2, "requReferrer");
            return (Criteria) this;
        }

        public Criteria andUserUuidIsNull() {
            addCriterion("`user_uuid` is null");
            return (Criteria) this;
        }

        public Criteria andUserUuidIsNotNull() {
            addCriterion("`user_uuid` is not null");
            return (Criteria) this;
        }

        public Criteria andUserUuidEqualTo(String value) {
            addCriterion("`user_uuid` =", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidNotEqualTo(String value) {
            addCriterion("`user_uuid` <>", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidGreaterThan(String value) {
            addCriterion("`user_uuid` >", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidGreaterThanOrEqualTo(String value) {
            addCriterion("`user_uuid` >=", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidLessThan(String value) {
            addCriterion("`user_uuid` <", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidLessThanOrEqualTo(String value) {
            addCriterion("`user_uuid` <=", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidLike(String value) {
            addCriterion("`user_uuid` like", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidNotLike(String value) {
            addCriterion("`user_uuid` not like", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidIn(List<String> values) {
            addCriterion("`user_uuid` in", values, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidNotIn(List<String> values) {
            addCriterion("`user_uuid` not in", values, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidBetween(String value1, String value2) {
            addCriterion("`user_uuid` between", value1, value2, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidNotBetween(String value1, String value2) {
            addCriterion("`user_uuid` not between", value1, value2, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("`user_name` is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("`user_name` is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("`user_name` =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("`user_name` <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("`user_name` >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("`user_name` >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("`user_name` <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("`user_name` <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("`user_name` like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("`user_name` not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("`user_name` in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("`user_name` not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("`user_name` between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("`user_name` not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andRequIpIsNull() {
            addCriterion("`requ_ip` is null");
            return (Criteria) this;
        }

        public Criteria andRequIpIsNotNull() {
            addCriterion("`requ_ip` is not null");
            return (Criteria) this;
        }

        public Criteria andRequIpEqualTo(String value) {
            addCriterion("`requ_ip` =", value, "requIp");
            return (Criteria) this;
        }

        public Criteria andRequIpNotEqualTo(String value) {
            addCriterion("`requ_ip` <>", value, "requIp");
            return (Criteria) this;
        }

        public Criteria andRequIpGreaterThan(String value) {
            addCriterion("`requ_ip` >", value, "requIp");
            return (Criteria) this;
        }

        public Criteria andRequIpGreaterThanOrEqualTo(String value) {
            addCriterion("`requ_ip` >=", value, "requIp");
            return (Criteria) this;
        }

        public Criteria andRequIpLessThan(String value) {
            addCriterion("`requ_ip` <", value, "requIp");
            return (Criteria) this;
        }

        public Criteria andRequIpLessThanOrEqualTo(String value) {
            addCriterion("`requ_ip` <=", value, "requIp");
            return (Criteria) this;
        }

        public Criteria andRequIpLike(String value) {
            addCriterion("`requ_ip` like", value, "requIp");
            return (Criteria) this;
        }

        public Criteria andRequIpNotLike(String value) {
            addCriterion("`requ_ip` not like", value, "requIp");
            return (Criteria) this;
        }

        public Criteria andRequIpIn(List<String> values) {
            addCriterion("`requ_ip` in", values, "requIp");
            return (Criteria) this;
        }

        public Criteria andRequIpNotIn(List<String> values) {
            addCriterion("`requ_ip` not in", values, "requIp");
            return (Criteria) this;
        }

        public Criteria andRequIpBetween(String value1, String value2) {
            addCriterion("`requ_ip` between", value1, value2, "requIp");
            return (Criteria) this;
        }

        public Criteria andRequIpNotBetween(String value1, String value2) {
            addCriterion("`requ_ip` not between", value1, value2, "requIp");
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