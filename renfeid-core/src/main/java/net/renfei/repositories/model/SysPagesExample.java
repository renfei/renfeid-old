package net.renfei.repositories.model;

import net.renfei.exception.DaoExampleException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysPagesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysPagesExample() {
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

        public Criteria andPageAuthorIsNull() {
            addCriterion("`page_author` is null");
            return (Criteria) this;
        }

        public Criteria andPageAuthorIsNotNull() {
            addCriterion("`page_author` is not null");
            return (Criteria) this;
        }

        public Criteria andPageAuthorEqualTo(Long value) {
            addCriterion("`page_author` =", value, "pageAuthor");
            return (Criteria) this;
        }

        public Criteria andPageAuthorNotEqualTo(Long value) {
            addCriterion("`page_author` <>", value, "pageAuthor");
            return (Criteria) this;
        }

        public Criteria andPageAuthorGreaterThan(Long value) {
            addCriterion("`page_author` >", value, "pageAuthor");
            return (Criteria) this;
        }

        public Criteria andPageAuthorGreaterThanOrEqualTo(Long value) {
            addCriterion("`page_author` >=", value, "pageAuthor");
            return (Criteria) this;
        }

        public Criteria andPageAuthorLessThan(Long value) {
            addCriterion("`page_author` <", value, "pageAuthor");
            return (Criteria) this;
        }

        public Criteria andPageAuthorLessThanOrEqualTo(Long value) {
            addCriterion("`page_author` <=", value, "pageAuthor");
            return (Criteria) this;
        }

        public Criteria andPageAuthorIn(List<Long> values) {
            addCriterion("`page_author` in", values, "pageAuthor");
            return (Criteria) this;
        }

        public Criteria andPageAuthorNotIn(List<Long> values) {
            addCriterion("`page_author` not in", values, "pageAuthor");
            return (Criteria) this;
        }

        public Criteria andPageAuthorBetween(Long value1, Long value2) {
            addCriterion("`page_author` between", value1, value2, "pageAuthor");
            return (Criteria) this;
        }

        public Criteria andPageAuthorNotBetween(Long value1, Long value2) {
            addCriterion("`page_author` not between", value1, value2, "pageAuthor");
            return (Criteria) this;
        }

        public Criteria andPageDateIsNull() {
            addCriterion("`page_date` is null");
            return (Criteria) this;
        }

        public Criteria andPageDateIsNotNull() {
            addCriterion("`page_date` is not null");
            return (Criteria) this;
        }

        public Criteria andPageDateEqualTo(Date value) {
            addCriterion("`page_date` =", value, "pageDate");
            return (Criteria) this;
        }

        public Criteria andPageDateNotEqualTo(Date value) {
            addCriterion("`page_date` <>", value, "pageDate");
            return (Criteria) this;
        }

        public Criteria andPageDateGreaterThan(Date value) {
            addCriterion("`page_date` >", value, "pageDate");
            return (Criteria) this;
        }

        public Criteria andPageDateGreaterThanOrEqualTo(Date value) {
            addCriterion("`page_date` >=", value, "pageDate");
            return (Criteria) this;
        }

        public Criteria andPageDateLessThan(Date value) {
            addCriterion("`page_date` <", value, "pageDate");
            return (Criteria) this;
        }

        public Criteria andPageDateLessThanOrEqualTo(Date value) {
            addCriterion("`page_date` <=", value, "pageDate");
            return (Criteria) this;
        }

        public Criteria andPageDateIn(List<Date> values) {
            addCriterion("`page_date` in", values, "pageDate");
            return (Criteria) this;
        }

        public Criteria andPageDateNotIn(List<Date> values) {
            addCriterion("`page_date` not in", values, "pageDate");
            return (Criteria) this;
        }

        public Criteria andPageDateBetween(Date value1, Date value2) {
            addCriterion("`page_date` between", value1, value2, "pageDate");
            return (Criteria) this;
        }

        public Criteria andPageDateNotBetween(Date value1, Date value2) {
            addCriterion("`page_date` not between", value1, value2, "pageDate");
            return (Criteria) this;
        }

        public Criteria andPageStatusIsNull() {
            addCriterion("`page_status` is null");
            return (Criteria) this;
        }

        public Criteria andPageStatusIsNotNull() {
            addCriterion("`page_status` is not null");
            return (Criteria) this;
        }

        public Criteria andPageStatusEqualTo(String value) {
            addCriterion("`page_status` =", value, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusNotEqualTo(String value) {
            addCriterion("`page_status` <>", value, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusGreaterThan(String value) {
            addCriterion("`page_status` >", value, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusGreaterThanOrEqualTo(String value) {
            addCriterion("`page_status` >=", value, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusLessThan(String value) {
            addCriterion("`page_status` <", value, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusLessThanOrEqualTo(String value) {
            addCriterion("`page_status` <=", value, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusLike(String value) {
            addCriterion("`page_status` like", value, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusNotLike(String value) {
            addCriterion("`page_status` not like", value, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusIn(List<String> values) {
            addCriterion("`page_status` in", values, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusNotIn(List<String> values) {
            addCriterion("`page_status` not in", values, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusBetween(String value1, String value2) {
            addCriterion("`page_status` between", value1, value2, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusNotBetween(String value1, String value2) {
            addCriterion("`page_status` not between", value1, value2, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageViewsIsNull() {
            addCriterion("`page_views` is null");
            return (Criteria) this;
        }

        public Criteria andPageViewsIsNotNull() {
            addCriterion("`page_views` is not null");
            return (Criteria) this;
        }

        public Criteria andPageViewsEqualTo(Long value) {
            addCriterion("`page_views` =", value, "pageViews");
            return (Criteria) this;
        }

        public Criteria andPageViewsNotEqualTo(Long value) {
            addCriterion("`page_views` <>", value, "pageViews");
            return (Criteria) this;
        }

        public Criteria andPageViewsGreaterThan(Long value) {
            addCriterion("`page_views` >", value, "pageViews");
            return (Criteria) this;
        }

        public Criteria andPageViewsGreaterThanOrEqualTo(Long value) {
            addCriterion("`page_views` >=", value, "pageViews");
            return (Criteria) this;
        }

        public Criteria andPageViewsLessThan(Long value) {
            addCriterion("`page_views` <", value, "pageViews");
            return (Criteria) this;
        }

        public Criteria andPageViewsLessThanOrEqualTo(Long value) {
            addCriterion("`page_views` <=", value, "pageViews");
            return (Criteria) this;
        }

        public Criteria andPageViewsIn(List<Long> values) {
            addCriterion("`page_views` in", values, "pageViews");
            return (Criteria) this;
        }

        public Criteria andPageViewsNotIn(List<Long> values) {
            addCriterion("`page_views` not in", values, "pageViews");
            return (Criteria) this;
        }

        public Criteria andPageViewsBetween(Long value1, Long value2) {
            addCriterion("`page_views` between", value1, value2, "pageViews");
            return (Criteria) this;
        }

        public Criteria andPageViewsNotBetween(Long value1, Long value2) {
            addCriterion("`page_views` not between", value1, value2, "pageViews");
            return (Criteria) this;
        }

        public Criteria andPagePasswordIsNull() {
            addCriterion("`page_password` is null");
            return (Criteria) this;
        }

        public Criteria andPagePasswordIsNotNull() {
            addCriterion("`page_password` is not null");
            return (Criteria) this;
        }

        public Criteria andPagePasswordEqualTo(String value) {
            addCriterion("`page_password` =", value, "pagePassword");
            return (Criteria) this;
        }

        public Criteria andPagePasswordNotEqualTo(String value) {
            addCriterion("`page_password` <>", value, "pagePassword");
            return (Criteria) this;
        }

        public Criteria andPagePasswordGreaterThan(String value) {
            addCriterion("`page_password` >", value, "pagePassword");
            return (Criteria) this;
        }

        public Criteria andPagePasswordGreaterThanOrEqualTo(String value) {
            addCriterion("`page_password` >=", value, "pagePassword");
            return (Criteria) this;
        }

        public Criteria andPagePasswordLessThan(String value) {
            addCriterion("`page_password` <", value, "pagePassword");
            return (Criteria) this;
        }

        public Criteria andPagePasswordLessThanOrEqualTo(String value) {
            addCriterion("`page_password` <=", value, "pagePassword");
            return (Criteria) this;
        }

        public Criteria andPagePasswordLike(String value) {
            addCriterion("`page_password` like", value, "pagePassword");
            return (Criteria) this;
        }

        public Criteria andPagePasswordNotLike(String value) {
            addCriterion("`page_password` not like", value, "pagePassword");
            return (Criteria) this;
        }

        public Criteria andPagePasswordIn(List<String> values) {
            addCriterion("`page_password` in", values, "pagePassword");
            return (Criteria) this;
        }

        public Criteria andPagePasswordNotIn(List<String> values) {
            addCriterion("`page_password` not in", values, "pagePassword");
            return (Criteria) this;
        }

        public Criteria andPagePasswordBetween(String value1, String value2) {
            addCriterion("`page_password` between", value1, value2, "pagePassword");
            return (Criteria) this;
        }

        public Criteria andPagePasswordNotBetween(String value1, String value2) {
            addCriterion("`page_password` not between", value1, value2, "pagePassword");
            return (Criteria) this;
        }

        public Criteria andPageModifiedIsNull() {
            addCriterion("`page_modified` is null");
            return (Criteria) this;
        }

        public Criteria andPageModifiedIsNotNull() {
            addCriterion("`page_modified` is not null");
            return (Criteria) this;
        }

        public Criteria andPageModifiedEqualTo(Date value) {
            addCriterion("`page_modified` =", value, "pageModified");
            return (Criteria) this;
        }

        public Criteria andPageModifiedNotEqualTo(Date value) {
            addCriterion("`page_modified` <>", value, "pageModified");
            return (Criteria) this;
        }

        public Criteria andPageModifiedGreaterThan(Date value) {
            addCriterion("`page_modified` >", value, "pageModified");
            return (Criteria) this;
        }

        public Criteria andPageModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("`page_modified` >=", value, "pageModified");
            return (Criteria) this;
        }

        public Criteria andPageModifiedLessThan(Date value) {
            addCriterion("`page_modified` <", value, "pageModified");
            return (Criteria) this;
        }

        public Criteria andPageModifiedLessThanOrEqualTo(Date value) {
            addCriterion("`page_modified` <=", value, "pageModified");
            return (Criteria) this;
        }

        public Criteria andPageModifiedIn(List<Date> values) {
            addCriterion("`page_modified` in", values, "pageModified");
            return (Criteria) this;
        }

        public Criteria andPageModifiedNotIn(List<Date> values) {
            addCriterion("`page_modified` not in", values, "pageModified");
            return (Criteria) this;
        }

        public Criteria andPageModifiedBetween(Date value1, Date value2) {
            addCriterion("`page_modified` between", value1, value2, "pageModified");
            return (Criteria) this;
        }

        public Criteria andPageModifiedNotBetween(Date value1, Date value2) {
            addCriterion("`page_modified` not between", value1, value2, "pageModified");
            return (Criteria) this;
        }

        public Criteria andPageParentIsNull() {
            addCriterion("`page_parent` is null");
            return (Criteria) this;
        }

        public Criteria andPageParentIsNotNull() {
            addCriterion("`page_parent` is not null");
            return (Criteria) this;
        }

        public Criteria andPageParentEqualTo(Long value) {
            addCriterion("`page_parent` =", value, "pageParent");
            return (Criteria) this;
        }

        public Criteria andPageParentNotEqualTo(Long value) {
            addCriterion("`page_parent` <>", value, "pageParent");
            return (Criteria) this;
        }

        public Criteria andPageParentGreaterThan(Long value) {
            addCriterion("`page_parent` >", value, "pageParent");
            return (Criteria) this;
        }

        public Criteria andPageParentGreaterThanOrEqualTo(Long value) {
            addCriterion("`page_parent` >=", value, "pageParent");
            return (Criteria) this;
        }

        public Criteria andPageParentLessThan(Long value) {
            addCriterion("`page_parent` <", value, "pageParent");
            return (Criteria) this;
        }

        public Criteria andPageParentLessThanOrEqualTo(Long value) {
            addCriterion("`page_parent` <=", value, "pageParent");
            return (Criteria) this;
        }

        public Criteria andPageParentIn(List<Long> values) {
            addCriterion("`page_parent` in", values, "pageParent");
            return (Criteria) this;
        }

        public Criteria andPageParentNotIn(List<Long> values) {
            addCriterion("`page_parent` not in", values, "pageParent");
            return (Criteria) this;
        }

        public Criteria andPageParentBetween(Long value1, Long value2) {
            addCriterion("`page_parent` between", value1, value2, "pageParent");
            return (Criteria) this;
        }

        public Criteria andPageParentNotBetween(Long value1, Long value2) {
            addCriterion("`page_parent` not between", value1, value2, "pageParent");
            return (Criteria) this;
        }

        public Criteria andThumbsUpIsNull() {
            addCriterion("`thumbs_up` is null");
            return (Criteria) this;
        }

        public Criteria andThumbsUpIsNotNull() {
            addCriterion("`thumbs_up` is not null");
            return (Criteria) this;
        }

        public Criteria andThumbsUpEqualTo(Long value) {
            addCriterion("`thumbs_up` =", value, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsUpNotEqualTo(Long value) {
            addCriterion("`thumbs_up` <>", value, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsUpGreaterThan(Long value) {
            addCriterion("`thumbs_up` >", value, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsUpGreaterThanOrEqualTo(Long value) {
            addCriterion("`thumbs_up` >=", value, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsUpLessThan(Long value) {
            addCriterion("`thumbs_up` <", value, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsUpLessThanOrEqualTo(Long value) {
            addCriterion("`thumbs_up` <=", value, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsUpIn(List<Long> values) {
            addCriterion("`thumbs_up` in", values, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsUpNotIn(List<Long> values) {
            addCriterion("`thumbs_up` not in", values, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsUpBetween(Long value1, Long value2) {
            addCriterion("`thumbs_up` between", value1, value2, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsUpNotBetween(Long value1, Long value2) {
            addCriterion("`thumbs_up` not between", value1, value2, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsDownIsNull() {
            addCriterion("`thumbs_down` is null");
            return (Criteria) this;
        }

        public Criteria andThumbsDownIsNotNull() {
            addCriterion("`thumbs_down` is not null");
            return (Criteria) this;
        }

        public Criteria andThumbsDownEqualTo(Long value) {
            addCriterion("`thumbs_down` =", value, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andThumbsDownNotEqualTo(Long value) {
            addCriterion("`thumbs_down` <>", value, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andThumbsDownGreaterThan(Long value) {
            addCriterion("`thumbs_down` >", value, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andThumbsDownGreaterThanOrEqualTo(Long value) {
            addCriterion("`thumbs_down` >=", value, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andThumbsDownLessThan(Long value) {
            addCriterion("`thumbs_down` <", value, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andThumbsDownLessThanOrEqualTo(Long value) {
            addCriterion("`thumbs_down` <=", value, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andThumbsDownIn(List<Long> values) {
            addCriterion("`thumbs_down` in", values, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andThumbsDownNotIn(List<Long> values) {
            addCriterion("`thumbs_down` not in", values, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andThumbsDownBetween(Long value1, Long value2) {
            addCriterion("`thumbs_down` between", value1, value2, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andThumbsDownNotBetween(Long value1, Long value2) {
            addCriterion("`thumbs_down` not between", value1, value2, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andSecretLevelIsNull() {
            addCriterion("`secret_level` is null");
            return (Criteria) this;
        }

        public Criteria andSecretLevelIsNotNull() {
            addCriterion("`secret_level` is not null");
            return (Criteria) this;
        }

        public Criteria andSecretLevelEqualTo(Integer value) {
            addCriterion("`secret_level` =", value, "secretLevel");
            return (Criteria) this;
        }

        public Criteria andSecretLevelNotEqualTo(Integer value) {
            addCriterion("`secret_level` <>", value, "secretLevel");
            return (Criteria) this;
        }

        public Criteria andSecretLevelGreaterThan(Integer value) {
            addCriterion("`secret_level` >", value, "secretLevel");
            return (Criteria) this;
        }

        public Criteria andSecretLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("`secret_level` >=", value, "secretLevel");
            return (Criteria) this;
        }

        public Criteria andSecretLevelLessThan(Integer value) {
            addCriterion("`secret_level` <", value, "secretLevel");
            return (Criteria) this;
        }

        public Criteria andSecretLevelLessThanOrEqualTo(Integer value) {
            addCriterion("`secret_level` <=", value, "secretLevel");
            return (Criteria) this;
        }

        public Criteria andSecretLevelIn(List<Integer> values) {
            addCriterion("`secret_level` in", values, "secretLevel");
            return (Criteria) this;
        }

        public Criteria andSecretLevelNotIn(List<Integer> values) {
            addCriterion("`secret_level` not in", values, "secretLevel");
            return (Criteria) this;
        }

        public Criteria andSecretLevelBetween(Integer value1, Integer value2) {
            addCriterion("`secret_level` between", value1, value2, "secretLevel");
            return (Criteria) this;
        }

        public Criteria andSecretLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("`secret_level` not between", value1, value2, "secretLevel");
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