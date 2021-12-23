package net.renfei.repositories.model;

import java.util.ArrayList;
import java.util.List;

public class SysSiteFooterMenuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysSiteFooterMenuExample() {
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

        public Criteria andPidIsNull() {
            addCriterion("`pid` is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("`pid` is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Long value) {
            addCriterion("`pid` =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Long value) {
            addCriterion("`pid` <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Long value) {
            addCriterion("`pid` >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Long value) {
            addCriterion("`pid` >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Long value) {
            addCriterion("`pid` <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Long value) {
            addCriterion("`pid` <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Long> values) {
            addCriterion("`pid` in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Long> values) {
            addCriterion("`pid` not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Long value1, Long value2) {
            addCriterion("`pid` between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Long value1, Long value2) {
            addCriterion("`pid` not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andMenuTextIsNull() {
            addCriterion("`menu_text` is null");
            return (Criteria) this;
        }

        public Criteria andMenuTextIsNotNull() {
            addCriterion("`menu_text` is not null");
            return (Criteria) this;
        }

        public Criteria andMenuTextEqualTo(String value) {
            addCriterion("`menu_text` =", value, "menuText");
            return (Criteria) this;
        }

        public Criteria andMenuTextNotEqualTo(String value) {
            addCriterion("`menu_text` <>", value, "menuText");
            return (Criteria) this;
        }

        public Criteria andMenuTextGreaterThan(String value) {
            addCriterion("`menu_text` >", value, "menuText");
            return (Criteria) this;
        }

        public Criteria andMenuTextGreaterThanOrEqualTo(String value) {
            addCriterion("`menu_text` >=", value, "menuText");
            return (Criteria) this;
        }

        public Criteria andMenuTextLessThan(String value) {
            addCriterion("`menu_text` <", value, "menuText");
            return (Criteria) this;
        }

        public Criteria andMenuTextLessThanOrEqualTo(String value) {
            addCriterion("`menu_text` <=", value, "menuText");
            return (Criteria) this;
        }

        public Criteria andMenuTextLike(String value) {
            addCriterion("`menu_text` like", value, "menuText");
            return (Criteria) this;
        }

        public Criteria andMenuTextNotLike(String value) {
            addCriterion("`menu_text` not like", value, "menuText");
            return (Criteria) this;
        }

        public Criteria andMenuTextIn(List<String> values) {
            addCriterion("`menu_text` in", values, "menuText");
            return (Criteria) this;
        }

        public Criteria andMenuTextNotIn(List<String> values) {
            addCriterion("`menu_text` not in", values, "menuText");
            return (Criteria) this;
        }

        public Criteria andMenuTextBetween(String value1, String value2) {
            addCriterion("`menu_text` between", value1, value2, "menuText");
            return (Criteria) this;
        }

        public Criteria andMenuTextNotBetween(String value1, String value2) {
            addCriterion("`menu_text` not between", value1, value2, "menuText");
            return (Criteria) this;
        }

        public Criteria andMenuLinkIsNull() {
            addCriterion("`menu_link` is null");
            return (Criteria) this;
        }

        public Criteria andMenuLinkIsNotNull() {
            addCriterion("`menu_link` is not null");
            return (Criteria) this;
        }

        public Criteria andMenuLinkEqualTo(String value) {
            addCriterion("`menu_link` =", value, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkNotEqualTo(String value) {
            addCriterion("`menu_link` <>", value, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkGreaterThan(String value) {
            addCriterion("`menu_link` >", value, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkGreaterThanOrEqualTo(String value) {
            addCriterion("`menu_link` >=", value, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkLessThan(String value) {
            addCriterion("`menu_link` <", value, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkLessThanOrEqualTo(String value) {
            addCriterion("`menu_link` <=", value, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkLike(String value) {
            addCriterion("`menu_link` like", value, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkNotLike(String value) {
            addCriterion("`menu_link` not like", value, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkIn(List<String> values) {
            addCriterion("`menu_link` in", values, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkNotIn(List<String> values) {
            addCriterion("`menu_link` not in", values, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkBetween(String value1, String value2) {
            addCriterion("`menu_link` between", value1, value2, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkNotBetween(String value1, String value2) {
            addCriterion("`menu_link` not between", value1, value2, "menuLink");
            return (Criteria) this;
        }

        public Criteria andIsNewWinIsNull() {
            addCriterion("`is_new_win` is null");
            return (Criteria) this;
        }

        public Criteria andIsNewWinIsNotNull() {
            addCriterion("`is_new_win` is not null");
            return (Criteria) this;
        }

        public Criteria andIsNewWinEqualTo(Boolean value) {
            addCriterion("`is_new_win` =", value, "isNewWin");
            return (Criteria) this;
        }

        public Criteria andIsNewWinNotEqualTo(Boolean value) {
            addCriterion("`is_new_win` <>", value, "isNewWin");
            return (Criteria) this;
        }

        public Criteria andIsNewWinGreaterThan(Boolean value) {
            addCriterion("`is_new_win` >", value, "isNewWin");
            return (Criteria) this;
        }

        public Criteria andIsNewWinGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`is_new_win` >=", value, "isNewWin");
            return (Criteria) this;
        }

        public Criteria andIsNewWinLessThan(Boolean value) {
            addCriterion("`is_new_win` <", value, "isNewWin");
            return (Criteria) this;
        }

        public Criteria andIsNewWinLessThanOrEqualTo(Boolean value) {
            addCriterion("`is_new_win` <=", value, "isNewWin");
            return (Criteria) this;
        }

        public Criteria andIsNewWinIn(List<Boolean> values) {
            addCriterion("`is_new_win` in", values, "isNewWin");
            return (Criteria) this;
        }

        public Criteria andIsNewWinNotIn(List<Boolean> values) {
            addCriterion("`is_new_win` not in", values, "isNewWin");
            return (Criteria) this;
        }

        public Criteria andIsNewWinBetween(Boolean value1, Boolean value2) {
            addCriterion("`is_new_win` between", value1, value2, "isNewWin");
            return (Criteria) this;
        }

        public Criteria andIsNewWinNotBetween(Boolean value1, Boolean value2) {
            addCriterion("`is_new_win` not between", value1, value2, "isNewWin");
            return (Criteria) this;
        }

        public Criteria andIsEnableIsNull() {
            addCriterion("`is_enable` is null");
            return (Criteria) this;
        }

        public Criteria andIsEnableIsNotNull() {
            addCriterion("`is_enable` is not null");
            return (Criteria) this;
        }

        public Criteria andIsEnableEqualTo(Boolean value) {
            addCriterion("`is_enable` =", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotEqualTo(Boolean value) {
            addCriterion("`is_enable` <>", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThan(Boolean value) {
            addCriterion("`is_enable` >", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`is_enable` >=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThan(Boolean value) {
            addCriterion("`is_enable` <", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThanOrEqualTo(Boolean value) {
            addCriterion("`is_enable` <=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableIn(List<Boolean> values) {
            addCriterion("`is_enable` in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotIn(List<Boolean> values) {
            addCriterion("`is_enable` not in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableBetween(Boolean value1, Boolean value2) {
            addCriterion("`is_enable` between", value1, value2, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("`is_enable` not between", value1, value2, "isEnable");
            return (Criteria) this;
        }

        public Criteria andOrderNumberIsNull() {
            addCriterion("`order_number` is null");
            return (Criteria) this;
        }

        public Criteria andOrderNumberIsNotNull() {
            addCriterion("`order_number` is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNumberEqualTo(Integer value) {
            addCriterion("`order_number` =", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotEqualTo(Integer value) {
            addCriterion("`order_number` <>", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberGreaterThan(Integer value) {
            addCriterion("`order_number` >", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("`order_number` >=", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberLessThan(Integer value) {
            addCriterion("`order_number` <", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberLessThanOrEqualTo(Integer value) {
            addCriterion("`order_number` <=", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberIn(List<Integer> values) {
            addCriterion("`order_number` in", values, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotIn(List<Integer> values) {
            addCriterion("`order_number` not in", values, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberBetween(Integer value1, Integer value2) {
            addCriterion("`order_number` between", value1, value2, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("`order_number` not between", value1, value2, "orderNumber");
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