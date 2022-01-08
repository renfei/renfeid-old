package net.renfei.discuz.repositories.entity;

import java.util.ArrayList;
import java.util.List;

public class DiscuzCommonMemberFieldHomeDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DiscuzCommonMemberFieldHomeDOExample() {
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

        public Criteria andVideophotoIsNull() {
            addCriterion("videophoto is null");
            return (Criteria) this;
        }

        public Criteria andVideophotoIsNotNull() {
            addCriterion("videophoto is not null");
            return (Criteria) this;
        }

        public Criteria andVideophotoEqualTo(String value) {
            addCriterion("videophoto =", value, "videophoto");
            return (Criteria) this;
        }

        public Criteria andVideophotoNotEqualTo(String value) {
            addCriterion("videophoto <>", value, "videophoto");
            return (Criteria) this;
        }

        public Criteria andVideophotoGreaterThan(String value) {
            addCriterion("videophoto >", value, "videophoto");
            return (Criteria) this;
        }

        public Criteria andVideophotoGreaterThanOrEqualTo(String value) {
            addCriterion("videophoto >=", value, "videophoto");
            return (Criteria) this;
        }

        public Criteria andVideophotoLessThan(String value) {
            addCriterion("videophoto <", value, "videophoto");
            return (Criteria) this;
        }

        public Criteria andVideophotoLessThanOrEqualTo(String value) {
            addCriterion("videophoto <=", value, "videophoto");
            return (Criteria) this;
        }

        public Criteria andVideophotoLike(String value) {
            addCriterion("videophoto like", value, "videophoto");
            return (Criteria) this;
        }

        public Criteria andVideophotoNotLike(String value) {
            addCriterion("videophoto not like", value, "videophoto");
            return (Criteria) this;
        }

        public Criteria andVideophotoIn(List<String> values) {
            addCriterion("videophoto in", values, "videophoto");
            return (Criteria) this;
        }

        public Criteria andVideophotoNotIn(List<String> values) {
            addCriterion("videophoto not in", values, "videophoto");
            return (Criteria) this;
        }

        public Criteria andVideophotoBetween(String value1, String value2) {
            addCriterion("videophoto between", value1, value2, "videophoto");
            return (Criteria) this;
        }

        public Criteria andVideophotoNotBetween(String value1, String value2) {
            addCriterion("videophoto not between", value1, value2, "videophoto");
            return (Criteria) this;
        }

        public Criteria andSpacenameIsNull() {
            addCriterion("spacename is null");
            return (Criteria) this;
        }

        public Criteria andSpacenameIsNotNull() {
            addCriterion("spacename is not null");
            return (Criteria) this;
        }

        public Criteria andSpacenameEqualTo(String value) {
            addCriterion("spacename =", value, "spacename");
            return (Criteria) this;
        }

        public Criteria andSpacenameNotEqualTo(String value) {
            addCriterion("spacename <>", value, "spacename");
            return (Criteria) this;
        }

        public Criteria andSpacenameGreaterThan(String value) {
            addCriterion("spacename >", value, "spacename");
            return (Criteria) this;
        }

        public Criteria andSpacenameGreaterThanOrEqualTo(String value) {
            addCriterion("spacename >=", value, "spacename");
            return (Criteria) this;
        }

        public Criteria andSpacenameLessThan(String value) {
            addCriterion("spacename <", value, "spacename");
            return (Criteria) this;
        }

        public Criteria andSpacenameLessThanOrEqualTo(String value) {
            addCriterion("spacename <=", value, "spacename");
            return (Criteria) this;
        }

        public Criteria andSpacenameLike(String value) {
            addCriterion("spacename like", value, "spacename");
            return (Criteria) this;
        }

        public Criteria andSpacenameNotLike(String value) {
            addCriterion("spacename not like", value, "spacename");
            return (Criteria) this;
        }

        public Criteria andSpacenameIn(List<String> values) {
            addCriterion("spacename in", values, "spacename");
            return (Criteria) this;
        }

        public Criteria andSpacenameNotIn(List<String> values) {
            addCriterion("spacename not in", values, "spacename");
            return (Criteria) this;
        }

        public Criteria andSpacenameBetween(String value1, String value2) {
            addCriterion("spacename between", value1, value2, "spacename");
            return (Criteria) this;
        }

        public Criteria andSpacenameNotBetween(String value1, String value2) {
            addCriterion("spacename not between", value1, value2, "spacename");
            return (Criteria) this;
        }

        public Criteria andSpacedescriptionIsNull() {
            addCriterion("spacedescription is null");
            return (Criteria) this;
        }

        public Criteria andSpacedescriptionIsNotNull() {
            addCriterion("spacedescription is not null");
            return (Criteria) this;
        }

        public Criteria andSpacedescriptionEqualTo(String value) {
            addCriterion("spacedescription =", value, "spacedescription");
            return (Criteria) this;
        }

        public Criteria andSpacedescriptionNotEqualTo(String value) {
            addCriterion("spacedescription <>", value, "spacedescription");
            return (Criteria) this;
        }

        public Criteria andSpacedescriptionGreaterThan(String value) {
            addCriterion("spacedescription >", value, "spacedescription");
            return (Criteria) this;
        }

        public Criteria andSpacedescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("spacedescription >=", value, "spacedescription");
            return (Criteria) this;
        }

        public Criteria andSpacedescriptionLessThan(String value) {
            addCriterion("spacedescription <", value, "spacedescription");
            return (Criteria) this;
        }

        public Criteria andSpacedescriptionLessThanOrEqualTo(String value) {
            addCriterion("spacedescription <=", value, "spacedescription");
            return (Criteria) this;
        }

        public Criteria andSpacedescriptionLike(String value) {
            addCriterion("spacedescription like", value, "spacedescription");
            return (Criteria) this;
        }

        public Criteria andSpacedescriptionNotLike(String value) {
            addCriterion("spacedescription not like", value, "spacedescription");
            return (Criteria) this;
        }

        public Criteria andSpacedescriptionIn(List<String> values) {
            addCriterion("spacedescription in", values, "spacedescription");
            return (Criteria) this;
        }

        public Criteria andSpacedescriptionNotIn(List<String> values) {
            addCriterion("spacedescription not in", values, "spacedescription");
            return (Criteria) this;
        }

        public Criteria andSpacedescriptionBetween(String value1, String value2) {
            addCriterion("spacedescription between", value1, value2, "spacedescription");
            return (Criteria) this;
        }

        public Criteria andSpacedescriptionNotBetween(String value1, String value2) {
            addCriterion("spacedescription not between", value1, value2, "spacedescription");
            return (Criteria) this;
        }

        public Criteria andDomainIsNull() {
            addCriterion("domain is null");
            return (Criteria) this;
        }

        public Criteria andDomainIsNotNull() {
            addCriterion("domain is not null");
            return (Criteria) this;
        }

        public Criteria andDomainEqualTo(String value) {
            addCriterion("domain =", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotEqualTo(String value) {
            addCriterion("domain <>", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainGreaterThan(String value) {
            addCriterion("domain >", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainGreaterThanOrEqualTo(String value) {
            addCriterion("domain >=", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLessThan(String value) {
            addCriterion("domain <", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLessThanOrEqualTo(String value) {
            addCriterion("domain <=", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLike(String value) {
            addCriterion("domain like", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotLike(String value) {
            addCriterion("domain not like", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainIn(List<String> values) {
            addCriterion("domain in", values, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotIn(List<String> values) {
            addCriterion("domain not in", values, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainBetween(String value1, String value2) {
            addCriterion("domain between", value1, value2, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotBetween(String value1, String value2) {
            addCriterion("domain not between", value1, value2, "domain");
            return (Criteria) this;
        }

        public Criteria andAddsizeIsNull() {
            addCriterion("addsize is null");
            return (Criteria) this;
        }

        public Criteria andAddsizeIsNotNull() {
            addCriterion("addsize is not null");
            return (Criteria) this;
        }

        public Criteria andAddsizeEqualTo(Integer value) {
            addCriterion("addsize =", value, "addsize");
            return (Criteria) this;
        }

        public Criteria andAddsizeNotEqualTo(Integer value) {
            addCriterion("addsize <>", value, "addsize");
            return (Criteria) this;
        }

        public Criteria andAddsizeGreaterThan(Integer value) {
            addCriterion("addsize >", value, "addsize");
            return (Criteria) this;
        }

        public Criteria andAddsizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("addsize >=", value, "addsize");
            return (Criteria) this;
        }

        public Criteria andAddsizeLessThan(Integer value) {
            addCriterion("addsize <", value, "addsize");
            return (Criteria) this;
        }

        public Criteria andAddsizeLessThanOrEqualTo(Integer value) {
            addCriterion("addsize <=", value, "addsize");
            return (Criteria) this;
        }

        public Criteria andAddsizeIn(List<Integer> values) {
            addCriterion("addsize in", values, "addsize");
            return (Criteria) this;
        }

        public Criteria andAddsizeNotIn(List<Integer> values) {
            addCriterion("addsize not in", values, "addsize");
            return (Criteria) this;
        }

        public Criteria andAddsizeBetween(Integer value1, Integer value2) {
            addCriterion("addsize between", value1, value2, "addsize");
            return (Criteria) this;
        }

        public Criteria andAddsizeNotBetween(Integer value1, Integer value2) {
            addCriterion("addsize not between", value1, value2, "addsize");
            return (Criteria) this;
        }

        public Criteria andAddfriendIsNull() {
            addCriterion("addfriend is null");
            return (Criteria) this;
        }

        public Criteria andAddfriendIsNotNull() {
            addCriterion("addfriend is not null");
            return (Criteria) this;
        }

        public Criteria andAddfriendEqualTo(Short value) {
            addCriterion("addfriend =", value, "addfriend");
            return (Criteria) this;
        }

        public Criteria andAddfriendNotEqualTo(Short value) {
            addCriterion("addfriend <>", value, "addfriend");
            return (Criteria) this;
        }

        public Criteria andAddfriendGreaterThan(Short value) {
            addCriterion("addfriend >", value, "addfriend");
            return (Criteria) this;
        }

        public Criteria andAddfriendGreaterThanOrEqualTo(Short value) {
            addCriterion("addfriend >=", value, "addfriend");
            return (Criteria) this;
        }

        public Criteria andAddfriendLessThan(Short value) {
            addCriterion("addfriend <", value, "addfriend");
            return (Criteria) this;
        }

        public Criteria andAddfriendLessThanOrEqualTo(Short value) {
            addCriterion("addfriend <=", value, "addfriend");
            return (Criteria) this;
        }

        public Criteria andAddfriendIn(List<Short> values) {
            addCriterion("addfriend in", values, "addfriend");
            return (Criteria) this;
        }

        public Criteria andAddfriendNotIn(List<Short> values) {
            addCriterion("addfriend not in", values, "addfriend");
            return (Criteria) this;
        }

        public Criteria andAddfriendBetween(Short value1, Short value2) {
            addCriterion("addfriend between", value1, value2, "addfriend");
            return (Criteria) this;
        }

        public Criteria andAddfriendNotBetween(Short value1, Short value2) {
            addCriterion("addfriend not between", value1, value2, "addfriend");
            return (Criteria) this;
        }

        public Criteria andMenunumIsNull() {
            addCriterion("menunum is null");
            return (Criteria) this;
        }

        public Criteria andMenunumIsNotNull() {
            addCriterion("menunum is not null");
            return (Criteria) this;
        }

        public Criteria andMenunumEqualTo(Short value) {
            addCriterion("menunum =", value, "menunum");
            return (Criteria) this;
        }

        public Criteria andMenunumNotEqualTo(Short value) {
            addCriterion("menunum <>", value, "menunum");
            return (Criteria) this;
        }

        public Criteria andMenunumGreaterThan(Short value) {
            addCriterion("menunum >", value, "menunum");
            return (Criteria) this;
        }

        public Criteria andMenunumGreaterThanOrEqualTo(Short value) {
            addCriterion("menunum >=", value, "menunum");
            return (Criteria) this;
        }

        public Criteria andMenunumLessThan(Short value) {
            addCriterion("menunum <", value, "menunum");
            return (Criteria) this;
        }

        public Criteria andMenunumLessThanOrEqualTo(Short value) {
            addCriterion("menunum <=", value, "menunum");
            return (Criteria) this;
        }

        public Criteria andMenunumIn(List<Short> values) {
            addCriterion("menunum in", values, "menunum");
            return (Criteria) this;
        }

        public Criteria andMenunumNotIn(List<Short> values) {
            addCriterion("menunum not in", values, "menunum");
            return (Criteria) this;
        }

        public Criteria andMenunumBetween(Short value1, Short value2) {
            addCriterion("menunum between", value1, value2, "menunum");
            return (Criteria) this;
        }

        public Criteria andMenunumNotBetween(Short value1, Short value2) {
            addCriterion("menunum not between", value1, value2, "menunum");
            return (Criteria) this;
        }

        public Criteria andThemeIsNull() {
            addCriterion("theme is null");
            return (Criteria) this;
        }

        public Criteria andThemeIsNotNull() {
            addCriterion("theme is not null");
            return (Criteria) this;
        }

        public Criteria andThemeEqualTo(String value) {
            addCriterion("theme =", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeNotEqualTo(String value) {
            addCriterion("theme <>", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeGreaterThan(String value) {
            addCriterion("theme >", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeGreaterThanOrEqualTo(String value) {
            addCriterion("theme >=", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeLessThan(String value) {
            addCriterion("theme <", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeLessThanOrEqualTo(String value) {
            addCriterion("theme <=", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeLike(String value) {
            addCriterion("theme like", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeNotLike(String value) {
            addCriterion("theme not like", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeIn(List<String> values) {
            addCriterion("theme in", values, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeNotIn(List<String> values) {
            addCriterion("theme not in", values, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeBetween(String value1, String value2) {
            addCriterion("theme between", value1, value2, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeNotBetween(String value1, String value2) {
            addCriterion("theme not between", value1, value2, "theme");
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