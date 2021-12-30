package net.renfei.repositories.model;

import net.renfei.exception.DaoExampleException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeafAllocExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LeafAllocExample() {
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
                throw new DaoExampleException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new DaoExampleException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new DaoExampleException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andBizTagIsNull() {
            addCriterion("`biz_tag` is null");
            return (Criteria) this;
        }

        public Criteria andBizTagIsNotNull() {
            addCriterion("`biz_tag` is not null");
            return (Criteria) this;
        }

        public Criteria andBizTagEqualTo(String value) {
            addCriterion("`biz_tag` =", value, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagNotEqualTo(String value) {
            addCriterion("`biz_tag` <>", value, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagGreaterThan(String value) {
            addCriterion("`biz_tag` >", value, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagGreaterThanOrEqualTo(String value) {
            addCriterion("`biz_tag` >=", value, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagLessThan(String value) {
            addCriterion("`biz_tag` <", value, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagLessThanOrEqualTo(String value) {
            addCriterion("`biz_tag` <=", value, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagLike(String value) {
            addCriterion("`biz_tag` like", value, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagNotLike(String value) {
            addCriterion("`biz_tag` not like", value, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagIn(List<String> values) {
            addCriterion("`biz_tag` in", values, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagNotIn(List<String> values) {
            addCriterion("`biz_tag` not in", values, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagBetween(String value1, String value2) {
            addCriterion("`biz_tag` between", value1, value2, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagNotBetween(String value1, String value2) {
            addCriterion("`biz_tag` not between", value1, value2, "bizTag");
            return (Criteria) this;
        }

        public Criteria andMaxIdIsNull() {
            addCriterion("`max_id` is null");
            return (Criteria) this;
        }

        public Criteria andMaxIdIsNotNull() {
            addCriterion("`max_id` is not null");
            return (Criteria) this;
        }

        public Criteria andMaxIdEqualTo(Long value) {
            addCriterion("`max_id` =", value, "maxId");
            return (Criteria) this;
        }

        public Criteria andMaxIdNotEqualTo(Long value) {
            addCriterion("`max_id` <>", value, "maxId");
            return (Criteria) this;
        }

        public Criteria andMaxIdGreaterThan(Long value) {
            addCriterion("`max_id` >", value, "maxId");
            return (Criteria) this;
        }

        public Criteria andMaxIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`max_id` >=", value, "maxId");
            return (Criteria) this;
        }

        public Criteria andMaxIdLessThan(Long value) {
            addCriterion("`max_id` <", value, "maxId");
            return (Criteria) this;
        }

        public Criteria andMaxIdLessThanOrEqualTo(Long value) {
            addCriterion("`max_id` <=", value, "maxId");
            return (Criteria) this;
        }

        public Criteria andMaxIdIn(List<Long> values) {
            addCriterion("`max_id` in", values, "maxId");
            return (Criteria) this;
        }

        public Criteria andMaxIdNotIn(List<Long> values) {
            addCriterion("`max_id` not in", values, "maxId");
            return (Criteria) this;
        }

        public Criteria andMaxIdBetween(Long value1, Long value2) {
            addCriterion("`max_id` between", value1, value2, "maxId");
            return (Criteria) this;
        }

        public Criteria andMaxIdNotBetween(Long value1, Long value2) {
            addCriterion("`max_id` not between", value1, value2, "maxId");
            return (Criteria) this;
        }

        public Criteria andStepIsNull() {
            addCriterion("`step` is null");
            return (Criteria) this;
        }

        public Criteria andStepIsNotNull() {
            addCriterion("`step` is not null");
            return (Criteria) this;
        }

        public Criteria andStepEqualTo(Integer value) {
            addCriterion("`step` =", value, "step");
            return (Criteria) this;
        }

        public Criteria andStepNotEqualTo(Integer value) {
            addCriterion("`step` <>", value, "step");
            return (Criteria) this;
        }

        public Criteria andStepGreaterThan(Integer value) {
            addCriterion("`step` >", value, "step");
            return (Criteria) this;
        }

        public Criteria andStepGreaterThanOrEqualTo(Integer value) {
            addCriterion("`step` >=", value, "step");
            return (Criteria) this;
        }

        public Criteria andStepLessThan(Integer value) {
            addCriterion("`step` <", value, "step");
            return (Criteria) this;
        }

        public Criteria andStepLessThanOrEqualTo(Integer value) {
            addCriterion("`step` <=", value, "step");
            return (Criteria) this;
        }

        public Criteria andStepIn(List<Integer> values) {
            addCriterion("`step` in", values, "step");
            return (Criteria) this;
        }

        public Criteria andStepNotIn(List<Integer> values) {
            addCriterion("`step` not in", values, "step");
            return (Criteria) this;
        }

        public Criteria andStepBetween(Integer value1, Integer value2) {
            addCriterion("`step` between", value1, value2, "step");
            return (Criteria) this;
        }

        public Criteria andStepNotBetween(Integer value1, Integer value2) {
            addCriterion("`step` not between", value1, value2, "step");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("`description` is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("`description` is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("`description` =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("`description` <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("`description` >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("`description` >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("`description` <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("`description` <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("`description` like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("`description` not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("`description` in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("`description` not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("`description` between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("`description` not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("`update_time` is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("`update_time` is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("`update_time` =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("`update_time` <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("`update_time` >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`update_time` >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("`update_time` <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("`update_time` <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("`update_time` in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("`update_time` not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("`update_time` between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("`update_time` not between", value1, value2, "updateTime");
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