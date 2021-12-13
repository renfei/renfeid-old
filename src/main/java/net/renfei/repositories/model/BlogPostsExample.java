package net.renfei.repositories.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlogPostsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BlogPostsExample() {
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

        public Criteria andCategoryIdIsNull() {
            addCriterion("`category_id` is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("`category_id` is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Long value) {
            addCriterion("`category_id` =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Long value) {
            addCriterion("`category_id` <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Long value) {
            addCriterion("`category_id` >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`category_id` >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Long value) {
            addCriterion("`category_id` <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Long value) {
            addCriterion("`category_id` <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Long> values) {
            addCriterion("`category_id` in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Long> values) {
            addCriterion("`category_id` not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Long value1, Long value2) {
            addCriterion("`category_id` between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Long value1, Long value2) {
            addCriterion("`category_id` not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andPostAuthorIsNull() {
            addCriterion("`post_author` is null");
            return (Criteria) this;
        }

        public Criteria andPostAuthorIsNotNull() {
            addCriterion("`post_author` is not null");
            return (Criteria) this;
        }

        public Criteria andPostAuthorEqualTo(Long value) {
            addCriterion("`post_author` =", value, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostAuthorNotEqualTo(Long value) {
            addCriterion("`post_author` <>", value, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostAuthorGreaterThan(Long value) {
            addCriterion("`post_author` >", value, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostAuthorGreaterThanOrEqualTo(Long value) {
            addCriterion("`post_author` >=", value, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostAuthorLessThan(Long value) {
            addCriterion("`post_author` <", value, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostAuthorLessThanOrEqualTo(Long value) {
            addCriterion("`post_author` <=", value, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostAuthorIn(List<Long> values) {
            addCriterion("`post_author` in", values, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostAuthorNotIn(List<Long> values) {
            addCriterion("`post_author` not in", values, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostAuthorBetween(Long value1, Long value2) {
            addCriterion("`post_author` between", value1, value2, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostAuthorNotBetween(Long value1, Long value2) {
            addCriterion("`post_author` not between", value1, value2, "postAuthor");
            return (Criteria) this;
        }

        public Criteria andPostDateIsNull() {
            addCriterion("`post_date` is null");
            return (Criteria) this;
        }

        public Criteria andPostDateIsNotNull() {
            addCriterion("`post_date` is not null");
            return (Criteria) this;
        }

        public Criteria andPostDateEqualTo(Date value) {
            addCriterion("`post_date` =", value, "postDate");
            return (Criteria) this;
        }

        public Criteria andPostDateNotEqualTo(Date value) {
            addCriterion("`post_date` <>", value, "postDate");
            return (Criteria) this;
        }

        public Criteria andPostDateGreaterThan(Date value) {
            addCriterion("`post_date` >", value, "postDate");
            return (Criteria) this;
        }

        public Criteria andPostDateGreaterThanOrEqualTo(Date value) {
            addCriterion("`post_date` >=", value, "postDate");
            return (Criteria) this;
        }

        public Criteria andPostDateLessThan(Date value) {
            addCriterion("`post_date` <", value, "postDate");
            return (Criteria) this;
        }

        public Criteria andPostDateLessThanOrEqualTo(Date value) {
            addCriterion("`post_date` <=", value, "postDate");
            return (Criteria) this;
        }

        public Criteria andPostDateIn(List<Date> values) {
            addCriterion("`post_date` in", values, "postDate");
            return (Criteria) this;
        }

        public Criteria andPostDateNotIn(List<Date> values) {
            addCriterion("`post_date` not in", values, "postDate");
            return (Criteria) this;
        }

        public Criteria andPostDateBetween(Date value1, Date value2) {
            addCriterion("`post_date` between", value1, value2, "postDate");
            return (Criteria) this;
        }

        public Criteria andPostDateNotBetween(Date value1, Date value2) {
            addCriterion("`post_date` not between", value1, value2, "postDate");
            return (Criteria) this;
        }

        public Criteria andPostStatusIsNull() {
            addCriterion("`post_status` is null");
            return (Criteria) this;
        }

        public Criteria andPostStatusIsNotNull() {
            addCriterion("`post_status` is not null");
            return (Criteria) this;
        }

        public Criteria andPostStatusEqualTo(String value) {
            addCriterion("`post_status` =", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusNotEqualTo(String value) {
            addCriterion("`post_status` <>", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusGreaterThan(String value) {
            addCriterion("`post_status` >", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusGreaterThanOrEqualTo(String value) {
            addCriterion("`post_status` >=", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusLessThan(String value) {
            addCriterion("`post_status` <", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusLessThanOrEqualTo(String value) {
            addCriterion("`post_status` <=", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusLike(String value) {
            addCriterion("`post_status` like", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusNotLike(String value) {
            addCriterion("`post_status` not like", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusIn(List<String> values) {
            addCriterion("`post_status` in", values, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusNotIn(List<String> values) {
            addCriterion("`post_status` not in", values, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusBetween(String value1, String value2) {
            addCriterion("`post_status` between", value1, value2, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusNotBetween(String value1, String value2) {
            addCriterion("`post_status` not between", value1, value2, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostViewsIsNull() {
            addCriterion("`post_views` is null");
            return (Criteria) this;
        }

        public Criteria andPostViewsIsNotNull() {
            addCriterion("`post_views` is not null");
            return (Criteria) this;
        }

        public Criteria andPostViewsEqualTo(Long value) {
            addCriterion("`post_views` =", value, "postViews");
            return (Criteria) this;
        }

        public Criteria andPostViewsNotEqualTo(Long value) {
            addCriterion("`post_views` <>", value, "postViews");
            return (Criteria) this;
        }

        public Criteria andPostViewsGreaterThan(Long value) {
            addCriterion("`post_views` >", value, "postViews");
            return (Criteria) this;
        }

        public Criteria andPostViewsGreaterThanOrEqualTo(Long value) {
            addCriterion("`post_views` >=", value, "postViews");
            return (Criteria) this;
        }

        public Criteria andPostViewsLessThan(Long value) {
            addCriterion("`post_views` <", value, "postViews");
            return (Criteria) this;
        }

        public Criteria andPostViewsLessThanOrEqualTo(Long value) {
            addCriterion("`post_views` <=", value, "postViews");
            return (Criteria) this;
        }

        public Criteria andPostViewsIn(List<Long> values) {
            addCriterion("`post_views` in", values, "postViews");
            return (Criteria) this;
        }

        public Criteria andPostViewsNotIn(List<Long> values) {
            addCriterion("`post_views` not in", values, "postViews");
            return (Criteria) this;
        }

        public Criteria andPostViewsBetween(Long value1, Long value2) {
            addCriterion("`post_views` between", value1, value2, "postViews");
            return (Criteria) this;
        }

        public Criteria andPostViewsNotBetween(Long value1, Long value2) {
            addCriterion("`post_views` not between", value1, value2, "postViews");
            return (Criteria) this;
        }

        public Criteria andCommentStatusIsNull() {
            addCriterion("`comment_status` is null");
            return (Criteria) this;
        }

        public Criteria andCommentStatusIsNotNull() {
            addCriterion("`comment_status` is not null");
            return (Criteria) this;
        }

        public Criteria andCommentStatusEqualTo(String value) {
            addCriterion("`comment_status` =", value, "commentStatus");
            return (Criteria) this;
        }

        public Criteria andCommentStatusNotEqualTo(String value) {
            addCriterion("`comment_status` <>", value, "commentStatus");
            return (Criteria) this;
        }

        public Criteria andCommentStatusGreaterThan(String value) {
            addCriterion("`comment_status` >", value, "commentStatus");
            return (Criteria) this;
        }

        public Criteria andCommentStatusGreaterThanOrEqualTo(String value) {
            addCriterion("`comment_status` >=", value, "commentStatus");
            return (Criteria) this;
        }

        public Criteria andCommentStatusLessThan(String value) {
            addCriterion("`comment_status` <", value, "commentStatus");
            return (Criteria) this;
        }

        public Criteria andCommentStatusLessThanOrEqualTo(String value) {
            addCriterion("`comment_status` <=", value, "commentStatus");
            return (Criteria) this;
        }

        public Criteria andCommentStatusLike(String value) {
            addCriterion("`comment_status` like", value, "commentStatus");
            return (Criteria) this;
        }

        public Criteria andCommentStatusNotLike(String value) {
            addCriterion("`comment_status` not like", value, "commentStatus");
            return (Criteria) this;
        }

        public Criteria andCommentStatusIn(List<String> values) {
            addCriterion("`comment_status` in", values, "commentStatus");
            return (Criteria) this;
        }

        public Criteria andCommentStatusNotIn(List<String> values) {
            addCriterion("`comment_status` not in", values, "commentStatus");
            return (Criteria) this;
        }

        public Criteria andCommentStatusBetween(String value1, String value2) {
            addCriterion("`comment_status` between", value1, value2, "commentStatus");
            return (Criteria) this;
        }

        public Criteria andCommentStatusNotBetween(String value1, String value2) {
            addCriterion("`comment_status` not between", value1, value2, "commentStatus");
            return (Criteria) this;
        }

        public Criteria andPostPasswordIsNull() {
            addCriterion("`post_password` is null");
            return (Criteria) this;
        }

        public Criteria andPostPasswordIsNotNull() {
            addCriterion("`post_password` is not null");
            return (Criteria) this;
        }

        public Criteria andPostPasswordEqualTo(String value) {
            addCriterion("`post_password` =", value, "postPassword");
            return (Criteria) this;
        }

        public Criteria andPostPasswordNotEqualTo(String value) {
            addCriterion("`post_password` <>", value, "postPassword");
            return (Criteria) this;
        }

        public Criteria andPostPasswordGreaterThan(String value) {
            addCriterion("`post_password` >", value, "postPassword");
            return (Criteria) this;
        }

        public Criteria andPostPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("`post_password` >=", value, "postPassword");
            return (Criteria) this;
        }

        public Criteria andPostPasswordLessThan(String value) {
            addCriterion("`post_password` <", value, "postPassword");
            return (Criteria) this;
        }

        public Criteria andPostPasswordLessThanOrEqualTo(String value) {
            addCriterion("`post_password` <=", value, "postPassword");
            return (Criteria) this;
        }

        public Criteria andPostPasswordLike(String value) {
            addCriterion("`post_password` like", value, "postPassword");
            return (Criteria) this;
        }

        public Criteria andPostPasswordNotLike(String value) {
            addCriterion("`post_password` not like", value, "postPassword");
            return (Criteria) this;
        }

        public Criteria andPostPasswordIn(List<String> values) {
            addCriterion("`post_password` in", values, "postPassword");
            return (Criteria) this;
        }

        public Criteria andPostPasswordNotIn(List<String> values) {
            addCriterion("`post_password` not in", values, "postPassword");
            return (Criteria) this;
        }

        public Criteria andPostPasswordBetween(String value1, String value2) {
            addCriterion("`post_password` between", value1, value2, "postPassword");
            return (Criteria) this;
        }

        public Criteria andPostPasswordNotBetween(String value1, String value2) {
            addCriterion("`post_password` not between", value1, value2, "postPassword");
            return (Criteria) this;
        }

        public Criteria andPostModifiedIsNull() {
            addCriterion("`post_modified` is null");
            return (Criteria) this;
        }

        public Criteria andPostModifiedIsNotNull() {
            addCriterion("`post_modified` is not null");
            return (Criteria) this;
        }

        public Criteria andPostModifiedEqualTo(Date value) {
            addCriterion("`post_modified` =", value, "postModified");
            return (Criteria) this;
        }

        public Criteria andPostModifiedNotEqualTo(Date value) {
            addCriterion("`post_modified` <>", value, "postModified");
            return (Criteria) this;
        }

        public Criteria andPostModifiedGreaterThan(Date value) {
            addCriterion("`post_modified` >", value, "postModified");
            return (Criteria) this;
        }

        public Criteria andPostModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("`post_modified` >=", value, "postModified");
            return (Criteria) this;
        }

        public Criteria andPostModifiedLessThan(Date value) {
            addCriterion("`post_modified` <", value, "postModified");
            return (Criteria) this;
        }

        public Criteria andPostModifiedLessThanOrEqualTo(Date value) {
            addCriterion("`post_modified` <=", value, "postModified");
            return (Criteria) this;
        }

        public Criteria andPostModifiedIn(List<Date> values) {
            addCriterion("`post_modified` in", values, "postModified");
            return (Criteria) this;
        }

        public Criteria andPostModifiedNotIn(List<Date> values) {
            addCriterion("`post_modified` not in", values, "postModified");
            return (Criteria) this;
        }

        public Criteria andPostModifiedBetween(Date value1, Date value2) {
            addCriterion("`post_modified` between", value1, value2, "postModified");
            return (Criteria) this;
        }

        public Criteria andPostModifiedNotBetween(Date value1, Date value2) {
            addCriterion("`post_modified` not between", value1, value2, "postModified");
            return (Criteria) this;
        }

        public Criteria andPostParentIsNull() {
            addCriterion("`post_parent` is null");
            return (Criteria) this;
        }

        public Criteria andPostParentIsNotNull() {
            addCriterion("`post_parent` is not null");
            return (Criteria) this;
        }

        public Criteria andPostParentEqualTo(Long value) {
            addCriterion("`post_parent` =", value, "postParent");
            return (Criteria) this;
        }

        public Criteria andPostParentNotEqualTo(Long value) {
            addCriterion("`post_parent` <>", value, "postParent");
            return (Criteria) this;
        }

        public Criteria andPostParentGreaterThan(Long value) {
            addCriterion("`post_parent` >", value, "postParent");
            return (Criteria) this;
        }

        public Criteria andPostParentGreaterThanOrEqualTo(Long value) {
            addCriterion("`post_parent` >=", value, "postParent");
            return (Criteria) this;
        }

        public Criteria andPostParentLessThan(Long value) {
            addCriterion("`post_parent` <", value, "postParent");
            return (Criteria) this;
        }

        public Criteria andPostParentLessThanOrEqualTo(Long value) {
            addCriterion("`post_parent` <=", value, "postParent");
            return (Criteria) this;
        }

        public Criteria andPostParentIn(List<Long> values) {
            addCriterion("`post_parent` in", values, "postParent");
            return (Criteria) this;
        }

        public Criteria andPostParentNotIn(List<Long> values) {
            addCriterion("`post_parent` not in", values, "postParent");
            return (Criteria) this;
        }

        public Criteria andPostParentBetween(Long value1, Long value2) {
            addCriterion("`post_parent` between", value1, value2, "postParent");
            return (Criteria) this;
        }

        public Criteria andPostParentNotBetween(Long value1, Long value2) {
            addCriterion("`post_parent` not between", value1, value2, "postParent");
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

        public Criteria andAvgViewsIsNull() {
            addCriterion("`avg_views` is null");
            return (Criteria) this;
        }

        public Criteria andAvgViewsIsNotNull() {
            addCriterion("`avg_views` is not null");
            return (Criteria) this;
        }

        public Criteria andAvgViewsEqualTo(Double value) {
            addCriterion("`avg_views` =", value, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgViewsNotEqualTo(Double value) {
            addCriterion("`avg_views` <>", value, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgViewsGreaterThan(Double value) {
            addCriterion("`avg_views` >", value, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgViewsGreaterThanOrEqualTo(Double value) {
            addCriterion("`avg_views` >=", value, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgViewsLessThan(Double value) {
            addCriterion("`avg_views` <", value, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgViewsLessThanOrEqualTo(Double value) {
            addCriterion("`avg_views` <=", value, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgViewsIn(List<Double> values) {
            addCriterion("`avg_views` in", values, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgViewsNotIn(List<Double> values) {
            addCriterion("`avg_views` not in", values, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgViewsBetween(Double value1, Double value2) {
            addCriterion("`avg_views` between", value1, value2, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgViewsNotBetween(Double value1, Double value2) {
            addCriterion("`avg_views` not between", value1, value2, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgCommentIsNull() {
            addCriterion("`avg_comment` is null");
            return (Criteria) this;
        }

        public Criteria andAvgCommentIsNotNull() {
            addCriterion("`avg_comment` is not null");
            return (Criteria) this;
        }

        public Criteria andAvgCommentEqualTo(Double value) {
            addCriterion("`avg_comment` =", value, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentNotEqualTo(Double value) {
            addCriterion("`avg_comment` <>", value, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentGreaterThan(Double value) {
            addCriterion("`avg_comment` >", value, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentGreaterThanOrEqualTo(Double value) {
            addCriterion("`avg_comment` >=", value, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentLessThan(Double value) {
            addCriterion("`avg_comment` <", value, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentLessThanOrEqualTo(Double value) {
            addCriterion("`avg_comment` <=", value, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentIn(List<Double> values) {
            addCriterion("`avg_comment` in", values, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentNotIn(List<Double> values) {
            addCriterion("`avg_comment` not in", values, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentBetween(Double value1, Double value2) {
            addCriterion("`avg_comment` between", value1, value2, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentNotBetween(Double value1, Double value2) {
            addCriterion("`avg_comment` not between", value1, value2, "avgComment");
            return (Criteria) this;
        }

        public Criteria andPageRankIsNull() {
            addCriterion("`page_rank` is null");
            return (Criteria) this;
        }

        public Criteria andPageRankIsNotNull() {
            addCriterion("`page_rank` is not null");
            return (Criteria) this;
        }

        public Criteria andPageRankEqualTo(Double value) {
            addCriterion("`page_rank` =", value, "pageRank");
            return (Criteria) this;
        }

        public Criteria andPageRankNotEqualTo(Double value) {
            addCriterion("`page_rank` <>", value, "pageRank");
            return (Criteria) this;
        }

        public Criteria andPageRankGreaterThan(Double value) {
            addCriterion("`page_rank` >", value, "pageRank");
            return (Criteria) this;
        }

        public Criteria andPageRankGreaterThanOrEqualTo(Double value) {
            addCriterion("`page_rank` >=", value, "pageRank");
            return (Criteria) this;
        }

        public Criteria andPageRankLessThan(Double value) {
            addCriterion("`page_rank` <", value, "pageRank");
            return (Criteria) this;
        }

        public Criteria andPageRankLessThanOrEqualTo(Double value) {
            addCriterion("`page_rank` <=", value, "pageRank");
            return (Criteria) this;
        }

        public Criteria andPageRankIn(List<Double> values) {
            addCriterion("`page_rank` in", values, "pageRank");
            return (Criteria) this;
        }

        public Criteria andPageRankNotIn(List<Double> values) {
            addCriterion("`page_rank` not in", values, "pageRank");
            return (Criteria) this;
        }

        public Criteria andPageRankBetween(Double value1, Double value2) {
            addCriterion("`page_rank` between", value1, value2, "pageRank");
            return (Criteria) this;
        }

        public Criteria andPageRankNotBetween(Double value1, Double value2) {
            addCriterion("`page_rank` not between", value1, value2, "pageRank");
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

        public Criteria andIsOriginalIsNull() {
            addCriterion("`is_original` is null");
            return (Criteria) this;
        }

        public Criteria andIsOriginalIsNotNull() {
            addCriterion("`is_original` is not null");
            return (Criteria) this;
        }

        public Criteria andIsOriginalEqualTo(Boolean value) {
            addCriterion("`is_original` =", value, "isOriginal");
            return (Criteria) this;
        }

        public Criteria andIsOriginalNotEqualTo(Boolean value) {
            addCriterion("`is_original` <>", value, "isOriginal");
            return (Criteria) this;
        }

        public Criteria andIsOriginalGreaterThan(Boolean value) {
            addCriterion("`is_original` >", value, "isOriginal");
            return (Criteria) this;
        }

        public Criteria andIsOriginalGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`is_original` >=", value, "isOriginal");
            return (Criteria) this;
        }

        public Criteria andIsOriginalLessThan(Boolean value) {
            addCriterion("`is_original` <", value, "isOriginal");
            return (Criteria) this;
        }

        public Criteria andIsOriginalLessThanOrEqualTo(Boolean value) {
            addCriterion("`is_original` <=", value, "isOriginal");
            return (Criteria) this;
        }

        public Criteria andIsOriginalIn(List<Boolean> values) {
            addCriterion("`is_original` in", values, "isOriginal");
            return (Criteria) this;
        }

        public Criteria andIsOriginalNotIn(List<Boolean> values) {
            addCriterion("`is_original` not in", values, "isOriginal");
            return (Criteria) this;
        }

        public Criteria andIsOriginalBetween(Boolean value1, Boolean value2) {
            addCriterion("`is_original` between", value1, value2, "isOriginal");
            return (Criteria) this;
        }

        public Criteria andIsOriginalNotBetween(Boolean value1, Boolean value2) {
            addCriterion("`is_original` not between", value1, value2, "isOriginal");
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