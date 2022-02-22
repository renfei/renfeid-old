package net.renfei.discuz.repositories.entity;

import java.util.ArrayList;
import java.util.List;

public class DiscuzCommonMemberFieldForumDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DiscuzCommonMemberFieldForumDOExample() {
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

        public Criteria andPublishfeedIsNull() {
            addCriterion("publishfeed is null");
            return (Criteria) this;
        }

        public Criteria andPublishfeedIsNotNull() {
            addCriterion("publishfeed is not null");
            return (Criteria) this;
        }

        public Criteria andPublishfeedEqualTo(Byte value) {
            addCriterion("publishfeed =", value, "publishfeed");
            return (Criteria) this;
        }

        public Criteria andPublishfeedNotEqualTo(Byte value) {
            addCriterion("publishfeed <>", value, "publishfeed");
            return (Criteria) this;
        }

        public Criteria andPublishfeedGreaterThan(Byte value) {
            addCriterion("publishfeed >", value, "publishfeed");
            return (Criteria) this;
        }

        public Criteria andPublishfeedGreaterThanOrEqualTo(Byte value) {
            addCriterion("publishfeed >=", value, "publishfeed");
            return (Criteria) this;
        }

        public Criteria andPublishfeedLessThan(Byte value) {
            addCriterion("publishfeed <", value, "publishfeed");
            return (Criteria) this;
        }

        public Criteria andPublishfeedLessThanOrEqualTo(Byte value) {
            addCriterion("publishfeed <=", value, "publishfeed");
            return (Criteria) this;
        }

        public Criteria andPublishfeedIn(List<Byte> values) {
            addCriterion("publishfeed in", values, "publishfeed");
            return (Criteria) this;
        }

        public Criteria andPublishfeedNotIn(List<Byte> values) {
            addCriterion("publishfeed not in", values, "publishfeed");
            return (Criteria) this;
        }

        public Criteria andPublishfeedBetween(Byte value1, Byte value2) {
            addCriterion("publishfeed between", value1, value2, "publishfeed");
            return (Criteria) this;
        }

        public Criteria andPublishfeedNotBetween(Byte value1, Byte value2) {
            addCriterion("publishfeed not between", value1, value2, "publishfeed");
            return (Criteria) this;
        }

        public Criteria andCustomshowIsNull() {
            addCriterion("customshow is null");
            return (Criteria) this;
        }

        public Criteria andCustomshowIsNotNull() {
            addCriterion("customshow is not null");
            return (Criteria) this;
        }

        public Criteria andCustomshowEqualTo(Boolean value) {
            addCriterion("customshow =", value, "customshow");
            return (Criteria) this;
        }

        public Criteria andCustomshowNotEqualTo(Boolean value) {
            addCriterion("customshow <>", value, "customshow");
            return (Criteria) this;
        }

        public Criteria andCustomshowGreaterThan(Boolean value) {
            addCriterion("customshow >", value, "customshow");
            return (Criteria) this;
        }

        public Criteria andCustomshowGreaterThanOrEqualTo(Boolean value) {
            addCriterion("customshow >=", value, "customshow");
            return (Criteria) this;
        }

        public Criteria andCustomshowLessThan(Boolean value) {
            addCriterion("customshow <", value, "customshow");
            return (Criteria) this;
        }

        public Criteria andCustomshowLessThanOrEqualTo(Boolean value) {
            addCriterion("customshow <=", value, "customshow");
            return (Criteria) this;
        }

        public Criteria andCustomshowIn(List<Boolean> values) {
            addCriterion("customshow in", values, "customshow");
            return (Criteria) this;
        }

        public Criteria andCustomshowNotIn(List<Boolean> values) {
            addCriterion("customshow not in", values, "customshow");
            return (Criteria) this;
        }

        public Criteria andCustomshowBetween(Boolean value1, Boolean value2) {
            addCriterion("customshow between", value1, value2, "customshow");
            return (Criteria) this;
        }

        public Criteria andCustomshowNotBetween(Boolean value1, Boolean value2) {
            addCriterion("customshow not between", value1, value2, "customshow");
            return (Criteria) this;
        }

        public Criteria andCustomstatusIsNull() {
            addCriterion("customstatus is null");
            return (Criteria) this;
        }

        public Criteria andCustomstatusIsNotNull() {
            addCriterion("customstatus is not null");
            return (Criteria) this;
        }

        public Criteria andCustomstatusEqualTo(String value) {
            addCriterion("customstatus =", value, "customstatus");
            return (Criteria) this;
        }

        public Criteria andCustomstatusNotEqualTo(String value) {
            addCriterion("customstatus <>", value, "customstatus");
            return (Criteria) this;
        }

        public Criteria andCustomstatusGreaterThan(String value) {
            addCriterion("customstatus >", value, "customstatus");
            return (Criteria) this;
        }

        public Criteria andCustomstatusGreaterThanOrEqualTo(String value) {
            addCriterion("customstatus >=", value, "customstatus");
            return (Criteria) this;
        }

        public Criteria andCustomstatusLessThan(String value) {
            addCriterion("customstatus <", value, "customstatus");
            return (Criteria) this;
        }

        public Criteria andCustomstatusLessThanOrEqualTo(String value) {
            addCriterion("customstatus <=", value, "customstatus");
            return (Criteria) this;
        }

        public Criteria andCustomstatusLike(String value) {
            addCriterion("customstatus like", value, "customstatus");
            return (Criteria) this;
        }

        public Criteria andCustomstatusNotLike(String value) {
            addCriterion("customstatus not like", value, "customstatus");
            return (Criteria) this;
        }

        public Criteria andCustomstatusIn(List<String> values) {
            addCriterion("customstatus in", values, "customstatus");
            return (Criteria) this;
        }

        public Criteria andCustomstatusNotIn(List<String> values) {
            addCriterion("customstatus not in", values, "customstatus");
            return (Criteria) this;
        }

        public Criteria andCustomstatusBetween(String value1, String value2) {
            addCriterion("customstatus between", value1, value2, "customstatus");
            return (Criteria) this;
        }

        public Criteria andCustomstatusNotBetween(String value1, String value2) {
            addCriterion("customstatus not between", value1, value2, "customstatus");
            return (Criteria) this;
        }

        public Criteria andAuthstrIsNull() {
            addCriterion("authstr is null");
            return (Criteria) this;
        }

        public Criteria andAuthstrIsNotNull() {
            addCriterion("authstr is not null");
            return (Criteria) this;
        }

        public Criteria andAuthstrEqualTo(String value) {
            addCriterion("authstr =", value, "authstr");
            return (Criteria) this;
        }

        public Criteria andAuthstrNotEqualTo(String value) {
            addCriterion("authstr <>", value, "authstr");
            return (Criteria) this;
        }

        public Criteria andAuthstrGreaterThan(String value) {
            addCriterion("authstr >", value, "authstr");
            return (Criteria) this;
        }

        public Criteria andAuthstrGreaterThanOrEqualTo(String value) {
            addCriterion("authstr >=", value, "authstr");
            return (Criteria) this;
        }

        public Criteria andAuthstrLessThan(String value) {
            addCriterion("authstr <", value, "authstr");
            return (Criteria) this;
        }

        public Criteria andAuthstrLessThanOrEqualTo(String value) {
            addCriterion("authstr <=", value, "authstr");
            return (Criteria) this;
        }

        public Criteria andAuthstrLike(String value) {
            addCriterion("authstr like", value, "authstr");
            return (Criteria) this;
        }

        public Criteria andAuthstrNotLike(String value) {
            addCriterion("authstr not like", value, "authstr");
            return (Criteria) this;
        }

        public Criteria andAuthstrIn(List<String> values) {
            addCriterion("authstr in", values, "authstr");
            return (Criteria) this;
        }

        public Criteria andAuthstrNotIn(List<String> values) {
            addCriterion("authstr not in", values, "authstr");
            return (Criteria) this;
        }

        public Criteria andAuthstrBetween(String value1, String value2) {
            addCriterion("authstr between", value1, value2, "authstr");
            return (Criteria) this;
        }

        public Criteria andAuthstrNotBetween(String value1, String value2) {
            addCriterion("authstr not between", value1, value2, "authstr");
            return (Criteria) this;
        }

        public Criteria andAttentiongroupIsNull() {
            addCriterion("attentiongroup is null");
            return (Criteria) this;
        }

        public Criteria andAttentiongroupIsNotNull() {
            addCriterion("attentiongroup is not null");
            return (Criteria) this;
        }

        public Criteria andAttentiongroupEqualTo(String value) {
            addCriterion("attentiongroup =", value, "attentiongroup");
            return (Criteria) this;
        }

        public Criteria andAttentiongroupNotEqualTo(String value) {
            addCriterion("attentiongroup <>", value, "attentiongroup");
            return (Criteria) this;
        }

        public Criteria andAttentiongroupGreaterThan(String value) {
            addCriterion("attentiongroup >", value, "attentiongroup");
            return (Criteria) this;
        }

        public Criteria andAttentiongroupGreaterThanOrEqualTo(String value) {
            addCriterion("attentiongroup >=", value, "attentiongroup");
            return (Criteria) this;
        }

        public Criteria andAttentiongroupLessThan(String value) {
            addCriterion("attentiongroup <", value, "attentiongroup");
            return (Criteria) this;
        }

        public Criteria andAttentiongroupLessThanOrEqualTo(String value) {
            addCriterion("attentiongroup <=", value, "attentiongroup");
            return (Criteria) this;
        }

        public Criteria andAttentiongroupLike(String value) {
            addCriterion("attentiongroup like", value, "attentiongroup");
            return (Criteria) this;
        }

        public Criteria andAttentiongroupNotLike(String value) {
            addCriterion("attentiongroup not like", value, "attentiongroup");
            return (Criteria) this;
        }

        public Criteria andAttentiongroupIn(List<String> values) {
            addCriterion("attentiongroup in", values, "attentiongroup");
            return (Criteria) this;
        }

        public Criteria andAttentiongroupNotIn(List<String> values) {
            addCriterion("attentiongroup not in", values, "attentiongroup");
            return (Criteria) this;
        }

        public Criteria andAttentiongroupBetween(String value1, String value2) {
            addCriterion("attentiongroup between", value1, value2, "attentiongroup");
            return (Criteria) this;
        }

        public Criteria andAttentiongroupNotBetween(String value1, String value2) {
            addCriterion("attentiongroup not between", value1, value2, "attentiongroup");
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