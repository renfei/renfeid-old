package net.renfei.pro.discuz.repositories.entity;

import java.util.ArrayList;
import java.util.List;

public class DiscuzCommonMemberCountDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DiscuzCommonMemberCountDOExample() {
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

        public Criteria andExtcredits1IsNull() {
            addCriterion("extcredits1 is null");
            return (Criteria) this;
        }

        public Criteria andExtcredits1IsNotNull() {
            addCriterion("extcredits1 is not null");
            return (Criteria) this;
        }

        public Criteria andExtcredits1EqualTo(Integer value) {
            addCriterion("extcredits1 =", value, "extcredits1");
            return (Criteria) this;
        }

        public Criteria andExtcredits1NotEqualTo(Integer value) {
            addCriterion("extcredits1 <>", value, "extcredits1");
            return (Criteria) this;
        }

        public Criteria andExtcredits1GreaterThan(Integer value) {
            addCriterion("extcredits1 >", value, "extcredits1");
            return (Criteria) this;
        }

        public Criteria andExtcredits1GreaterThanOrEqualTo(Integer value) {
            addCriterion("extcredits1 >=", value, "extcredits1");
            return (Criteria) this;
        }

        public Criteria andExtcredits1LessThan(Integer value) {
            addCriterion("extcredits1 <", value, "extcredits1");
            return (Criteria) this;
        }

        public Criteria andExtcredits1LessThanOrEqualTo(Integer value) {
            addCriterion("extcredits1 <=", value, "extcredits1");
            return (Criteria) this;
        }

        public Criteria andExtcredits1In(List<Integer> values) {
            addCriterion("extcredits1 in", values, "extcredits1");
            return (Criteria) this;
        }

        public Criteria andExtcredits1NotIn(List<Integer> values) {
            addCriterion("extcredits1 not in", values, "extcredits1");
            return (Criteria) this;
        }

        public Criteria andExtcredits1Between(Integer value1, Integer value2) {
            addCriterion("extcredits1 between", value1, value2, "extcredits1");
            return (Criteria) this;
        }

        public Criteria andExtcredits1NotBetween(Integer value1, Integer value2) {
            addCriterion("extcredits1 not between", value1, value2, "extcredits1");
            return (Criteria) this;
        }

        public Criteria andExtcredits2IsNull() {
            addCriterion("extcredits2 is null");
            return (Criteria) this;
        }

        public Criteria andExtcredits2IsNotNull() {
            addCriterion("extcredits2 is not null");
            return (Criteria) this;
        }

        public Criteria andExtcredits2EqualTo(Integer value) {
            addCriterion("extcredits2 =", value, "extcredits2");
            return (Criteria) this;
        }

        public Criteria andExtcredits2NotEqualTo(Integer value) {
            addCriterion("extcredits2 <>", value, "extcredits2");
            return (Criteria) this;
        }

        public Criteria andExtcredits2GreaterThan(Integer value) {
            addCriterion("extcredits2 >", value, "extcredits2");
            return (Criteria) this;
        }

        public Criteria andExtcredits2GreaterThanOrEqualTo(Integer value) {
            addCriterion("extcredits2 >=", value, "extcredits2");
            return (Criteria) this;
        }

        public Criteria andExtcredits2LessThan(Integer value) {
            addCriterion("extcredits2 <", value, "extcredits2");
            return (Criteria) this;
        }

        public Criteria andExtcredits2LessThanOrEqualTo(Integer value) {
            addCriterion("extcredits2 <=", value, "extcredits2");
            return (Criteria) this;
        }

        public Criteria andExtcredits2In(List<Integer> values) {
            addCriterion("extcredits2 in", values, "extcredits2");
            return (Criteria) this;
        }

        public Criteria andExtcredits2NotIn(List<Integer> values) {
            addCriterion("extcredits2 not in", values, "extcredits2");
            return (Criteria) this;
        }

        public Criteria andExtcredits2Between(Integer value1, Integer value2) {
            addCriterion("extcredits2 between", value1, value2, "extcredits2");
            return (Criteria) this;
        }

        public Criteria andExtcredits2NotBetween(Integer value1, Integer value2) {
            addCriterion("extcredits2 not between", value1, value2, "extcredits2");
            return (Criteria) this;
        }

        public Criteria andExtcredits3IsNull() {
            addCriterion("extcredits3 is null");
            return (Criteria) this;
        }

        public Criteria andExtcredits3IsNotNull() {
            addCriterion("extcredits3 is not null");
            return (Criteria) this;
        }

        public Criteria andExtcredits3EqualTo(Integer value) {
            addCriterion("extcredits3 =", value, "extcredits3");
            return (Criteria) this;
        }

        public Criteria andExtcredits3NotEqualTo(Integer value) {
            addCriterion("extcredits3 <>", value, "extcredits3");
            return (Criteria) this;
        }

        public Criteria andExtcredits3GreaterThan(Integer value) {
            addCriterion("extcredits3 >", value, "extcredits3");
            return (Criteria) this;
        }

        public Criteria andExtcredits3GreaterThanOrEqualTo(Integer value) {
            addCriterion("extcredits3 >=", value, "extcredits3");
            return (Criteria) this;
        }

        public Criteria andExtcredits3LessThan(Integer value) {
            addCriterion("extcredits3 <", value, "extcredits3");
            return (Criteria) this;
        }

        public Criteria andExtcredits3LessThanOrEqualTo(Integer value) {
            addCriterion("extcredits3 <=", value, "extcredits3");
            return (Criteria) this;
        }

        public Criteria andExtcredits3In(List<Integer> values) {
            addCriterion("extcredits3 in", values, "extcredits3");
            return (Criteria) this;
        }

        public Criteria andExtcredits3NotIn(List<Integer> values) {
            addCriterion("extcredits3 not in", values, "extcredits3");
            return (Criteria) this;
        }

        public Criteria andExtcredits3Between(Integer value1, Integer value2) {
            addCriterion("extcredits3 between", value1, value2, "extcredits3");
            return (Criteria) this;
        }

        public Criteria andExtcredits3NotBetween(Integer value1, Integer value2) {
            addCriterion("extcredits3 not between", value1, value2, "extcredits3");
            return (Criteria) this;
        }

        public Criteria andExtcredits4IsNull() {
            addCriterion("extcredits4 is null");
            return (Criteria) this;
        }

        public Criteria andExtcredits4IsNotNull() {
            addCriterion("extcredits4 is not null");
            return (Criteria) this;
        }

        public Criteria andExtcredits4EqualTo(Integer value) {
            addCriterion("extcredits4 =", value, "extcredits4");
            return (Criteria) this;
        }

        public Criteria andExtcredits4NotEqualTo(Integer value) {
            addCriterion("extcredits4 <>", value, "extcredits4");
            return (Criteria) this;
        }

        public Criteria andExtcredits4GreaterThan(Integer value) {
            addCriterion("extcredits4 >", value, "extcredits4");
            return (Criteria) this;
        }

        public Criteria andExtcredits4GreaterThanOrEqualTo(Integer value) {
            addCriterion("extcredits4 >=", value, "extcredits4");
            return (Criteria) this;
        }

        public Criteria andExtcredits4LessThan(Integer value) {
            addCriterion("extcredits4 <", value, "extcredits4");
            return (Criteria) this;
        }

        public Criteria andExtcredits4LessThanOrEqualTo(Integer value) {
            addCriterion("extcredits4 <=", value, "extcredits4");
            return (Criteria) this;
        }

        public Criteria andExtcredits4In(List<Integer> values) {
            addCriterion("extcredits4 in", values, "extcredits4");
            return (Criteria) this;
        }

        public Criteria andExtcredits4NotIn(List<Integer> values) {
            addCriterion("extcredits4 not in", values, "extcredits4");
            return (Criteria) this;
        }

        public Criteria andExtcredits4Between(Integer value1, Integer value2) {
            addCriterion("extcredits4 between", value1, value2, "extcredits4");
            return (Criteria) this;
        }

        public Criteria andExtcredits4NotBetween(Integer value1, Integer value2) {
            addCriterion("extcredits4 not between", value1, value2, "extcredits4");
            return (Criteria) this;
        }

        public Criteria andExtcredits5IsNull() {
            addCriterion("extcredits5 is null");
            return (Criteria) this;
        }

        public Criteria andExtcredits5IsNotNull() {
            addCriterion("extcredits5 is not null");
            return (Criteria) this;
        }

        public Criteria andExtcredits5EqualTo(Integer value) {
            addCriterion("extcredits5 =", value, "extcredits5");
            return (Criteria) this;
        }

        public Criteria andExtcredits5NotEqualTo(Integer value) {
            addCriterion("extcredits5 <>", value, "extcredits5");
            return (Criteria) this;
        }

        public Criteria andExtcredits5GreaterThan(Integer value) {
            addCriterion("extcredits5 >", value, "extcredits5");
            return (Criteria) this;
        }

        public Criteria andExtcredits5GreaterThanOrEqualTo(Integer value) {
            addCriterion("extcredits5 >=", value, "extcredits5");
            return (Criteria) this;
        }

        public Criteria andExtcredits5LessThan(Integer value) {
            addCriterion("extcredits5 <", value, "extcredits5");
            return (Criteria) this;
        }

        public Criteria andExtcredits5LessThanOrEqualTo(Integer value) {
            addCriterion("extcredits5 <=", value, "extcredits5");
            return (Criteria) this;
        }

        public Criteria andExtcredits5In(List<Integer> values) {
            addCriterion("extcredits5 in", values, "extcredits5");
            return (Criteria) this;
        }

        public Criteria andExtcredits5NotIn(List<Integer> values) {
            addCriterion("extcredits5 not in", values, "extcredits5");
            return (Criteria) this;
        }

        public Criteria andExtcredits5Between(Integer value1, Integer value2) {
            addCriterion("extcredits5 between", value1, value2, "extcredits5");
            return (Criteria) this;
        }

        public Criteria andExtcredits5NotBetween(Integer value1, Integer value2) {
            addCriterion("extcredits5 not between", value1, value2, "extcredits5");
            return (Criteria) this;
        }

        public Criteria andExtcredits6IsNull() {
            addCriterion("extcredits6 is null");
            return (Criteria) this;
        }

        public Criteria andExtcredits6IsNotNull() {
            addCriterion("extcredits6 is not null");
            return (Criteria) this;
        }

        public Criteria andExtcredits6EqualTo(Integer value) {
            addCriterion("extcredits6 =", value, "extcredits6");
            return (Criteria) this;
        }

        public Criteria andExtcredits6NotEqualTo(Integer value) {
            addCriterion("extcredits6 <>", value, "extcredits6");
            return (Criteria) this;
        }

        public Criteria andExtcredits6GreaterThan(Integer value) {
            addCriterion("extcredits6 >", value, "extcredits6");
            return (Criteria) this;
        }

        public Criteria andExtcredits6GreaterThanOrEqualTo(Integer value) {
            addCriterion("extcredits6 >=", value, "extcredits6");
            return (Criteria) this;
        }

        public Criteria andExtcredits6LessThan(Integer value) {
            addCriterion("extcredits6 <", value, "extcredits6");
            return (Criteria) this;
        }

        public Criteria andExtcredits6LessThanOrEqualTo(Integer value) {
            addCriterion("extcredits6 <=", value, "extcredits6");
            return (Criteria) this;
        }

        public Criteria andExtcredits6In(List<Integer> values) {
            addCriterion("extcredits6 in", values, "extcredits6");
            return (Criteria) this;
        }

        public Criteria andExtcredits6NotIn(List<Integer> values) {
            addCriterion("extcredits6 not in", values, "extcredits6");
            return (Criteria) this;
        }

        public Criteria andExtcredits6Between(Integer value1, Integer value2) {
            addCriterion("extcredits6 between", value1, value2, "extcredits6");
            return (Criteria) this;
        }

        public Criteria andExtcredits6NotBetween(Integer value1, Integer value2) {
            addCriterion("extcredits6 not between", value1, value2, "extcredits6");
            return (Criteria) this;
        }

        public Criteria andExtcredits7IsNull() {
            addCriterion("extcredits7 is null");
            return (Criteria) this;
        }

        public Criteria andExtcredits7IsNotNull() {
            addCriterion("extcredits7 is not null");
            return (Criteria) this;
        }

        public Criteria andExtcredits7EqualTo(Integer value) {
            addCriterion("extcredits7 =", value, "extcredits7");
            return (Criteria) this;
        }

        public Criteria andExtcredits7NotEqualTo(Integer value) {
            addCriterion("extcredits7 <>", value, "extcredits7");
            return (Criteria) this;
        }

        public Criteria andExtcredits7GreaterThan(Integer value) {
            addCriterion("extcredits7 >", value, "extcredits7");
            return (Criteria) this;
        }

        public Criteria andExtcredits7GreaterThanOrEqualTo(Integer value) {
            addCriterion("extcredits7 >=", value, "extcredits7");
            return (Criteria) this;
        }

        public Criteria andExtcredits7LessThan(Integer value) {
            addCriterion("extcredits7 <", value, "extcredits7");
            return (Criteria) this;
        }

        public Criteria andExtcredits7LessThanOrEqualTo(Integer value) {
            addCriterion("extcredits7 <=", value, "extcredits7");
            return (Criteria) this;
        }

        public Criteria andExtcredits7In(List<Integer> values) {
            addCriterion("extcredits7 in", values, "extcredits7");
            return (Criteria) this;
        }

        public Criteria andExtcredits7NotIn(List<Integer> values) {
            addCriterion("extcredits7 not in", values, "extcredits7");
            return (Criteria) this;
        }

        public Criteria andExtcredits7Between(Integer value1, Integer value2) {
            addCriterion("extcredits7 between", value1, value2, "extcredits7");
            return (Criteria) this;
        }

        public Criteria andExtcredits7NotBetween(Integer value1, Integer value2) {
            addCriterion("extcredits7 not between", value1, value2, "extcredits7");
            return (Criteria) this;
        }

        public Criteria andExtcredits8IsNull() {
            addCriterion("extcredits8 is null");
            return (Criteria) this;
        }

        public Criteria andExtcredits8IsNotNull() {
            addCriterion("extcredits8 is not null");
            return (Criteria) this;
        }

        public Criteria andExtcredits8EqualTo(Integer value) {
            addCriterion("extcredits8 =", value, "extcredits8");
            return (Criteria) this;
        }

        public Criteria andExtcredits8NotEqualTo(Integer value) {
            addCriterion("extcredits8 <>", value, "extcredits8");
            return (Criteria) this;
        }

        public Criteria andExtcredits8GreaterThan(Integer value) {
            addCriterion("extcredits8 >", value, "extcredits8");
            return (Criteria) this;
        }

        public Criteria andExtcredits8GreaterThanOrEqualTo(Integer value) {
            addCriterion("extcredits8 >=", value, "extcredits8");
            return (Criteria) this;
        }

        public Criteria andExtcredits8LessThan(Integer value) {
            addCriterion("extcredits8 <", value, "extcredits8");
            return (Criteria) this;
        }

        public Criteria andExtcredits8LessThanOrEqualTo(Integer value) {
            addCriterion("extcredits8 <=", value, "extcredits8");
            return (Criteria) this;
        }

        public Criteria andExtcredits8In(List<Integer> values) {
            addCriterion("extcredits8 in", values, "extcredits8");
            return (Criteria) this;
        }

        public Criteria andExtcredits8NotIn(List<Integer> values) {
            addCriterion("extcredits8 not in", values, "extcredits8");
            return (Criteria) this;
        }

        public Criteria andExtcredits8Between(Integer value1, Integer value2) {
            addCriterion("extcredits8 between", value1, value2, "extcredits8");
            return (Criteria) this;
        }

        public Criteria andExtcredits8NotBetween(Integer value1, Integer value2) {
            addCriterion("extcredits8 not between", value1, value2, "extcredits8");
            return (Criteria) this;
        }

        public Criteria andFriendsIsNull() {
            addCriterion("friends is null");
            return (Criteria) this;
        }

        public Criteria andFriendsIsNotNull() {
            addCriterion("friends is not null");
            return (Criteria) this;
        }

        public Criteria andFriendsEqualTo(Short value) {
            addCriterion("friends =", value, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsNotEqualTo(Short value) {
            addCriterion("friends <>", value, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsGreaterThan(Short value) {
            addCriterion("friends >", value, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsGreaterThanOrEqualTo(Short value) {
            addCriterion("friends >=", value, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsLessThan(Short value) {
            addCriterion("friends <", value, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsLessThanOrEqualTo(Short value) {
            addCriterion("friends <=", value, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsIn(List<Short> values) {
            addCriterion("friends in", values, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsNotIn(List<Short> values) {
            addCriterion("friends not in", values, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsBetween(Short value1, Short value2) {
            addCriterion("friends between", value1, value2, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsNotBetween(Short value1, Short value2) {
            addCriterion("friends not between", value1, value2, "friends");
            return (Criteria) this;
        }

        public Criteria andPostsIsNull() {
            addCriterion("posts is null");
            return (Criteria) this;
        }

        public Criteria andPostsIsNotNull() {
            addCriterion("posts is not null");
            return (Criteria) this;
        }

        public Criteria andPostsEqualTo(Integer value) {
            addCriterion("posts =", value, "posts");
            return (Criteria) this;
        }

        public Criteria andPostsNotEqualTo(Integer value) {
            addCriterion("posts <>", value, "posts");
            return (Criteria) this;
        }

        public Criteria andPostsGreaterThan(Integer value) {
            addCriterion("posts >", value, "posts");
            return (Criteria) this;
        }

        public Criteria andPostsGreaterThanOrEqualTo(Integer value) {
            addCriterion("posts >=", value, "posts");
            return (Criteria) this;
        }

        public Criteria andPostsLessThan(Integer value) {
            addCriterion("posts <", value, "posts");
            return (Criteria) this;
        }

        public Criteria andPostsLessThanOrEqualTo(Integer value) {
            addCriterion("posts <=", value, "posts");
            return (Criteria) this;
        }

        public Criteria andPostsIn(List<Integer> values) {
            addCriterion("posts in", values, "posts");
            return (Criteria) this;
        }

        public Criteria andPostsNotIn(List<Integer> values) {
            addCriterion("posts not in", values, "posts");
            return (Criteria) this;
        }

        public Criteria andPostsBetween(Integer value1, Integer value2) {
            addCriterion("posts between", value1, value2, "posts");
            return (Criteria) this;
        }

        public Criteria andPostsNotBetween(Integer value1, Integer value2) {
            addCriterion("posts not between", value1, value2, "posts");
            return (Criteria) this;
        }

        public Criteria andThreadsIsNull() {
            addCriterion("threads is null");
            return (Criteria) this;
        }

        public Criteria andThreadsIsNotNull() {
            addCriterion("threads is not null");
            return (Criteria) this;
        }

        public Criteria andThreadsEqualTo(Integer value) {
            addCriterion("threads =", value, "threads");
            return (Criteria) this;
        }

        public Criteria andThreadsNotEqualTo(Integer value) {
            addCriterion("threads <>", value, "threads");
            return (Criteria) this;
        }

        public Criteria andThreadsGreaterThan(Integer value) {
            addCriterion("threads >", value, "threads");
            return (Criteria) this;
        }

        public Criteria andThreadsGreaterThanOrEqualTo(Integer value) {
            addCriterion("threads >=", value, "threads");
            return (Criteria) this;
        }

        public Criteria andThreadsLessThan(Integer value) {
            addCriterion("threads <", value, "threads");
            return (Criteria) this;
        }

        public Criteria andThreadsLessThanOrEqualTo(Integer value) {
            addCriterion("threads <=", value, "threads");
            return (Criteria) this;
        }

        public Criteria andThreadsIn(List<Integer> values) {
            addCriterion("threads in", values, "threads");
            return (Criteria) this;
        }

        public Criteria andThreadsNotIn(List<Integer> values) {
            addCriterion("threads not in", values, "threads");
            return (Criteria) this;
        }

        public Criteria andThreadsBetween(Integer value1, Integer value2) {
            addCriterion("threads between", value1, value2, "threads");
            return (Criteria) this;
        }

        public Criteria andThreadsNotBetween(Integer value1, Integer value2) {
            addCriterion("threads not between", value1, value2, "threads");
            return (Criteria) this;
        }

        public Criteria andDigestpostsIsNull() {
            addCriterion("digestposts is null");
            return (Criteria) this;
        }

        public Criteria andDigestpostsIsNotNull() {
            addCriterion("digestposts is not null");
            return (Criteria) this;
        }

        public Criteria andDigestpostsEqualTo(Short value) {
            addCriterion("digestposts =", value, "digestposts");
            return (Criteria) this;
        }

        public Criteria andDigestpostsNotEqualTo(Short value) {
            addCriterion("digestposts <>", value, "digestposts");
            return (Criteria) this;
        }

        public Criteria andDigestpostsGreaterThan(Short value) {
            addCriterion("digestposts >", value, "digestposts");
            return (Criteria) this;
        }

        public Criteria andDigestpostsGreaterThanOrEqualTo(Short value) {
            addCriterion("digestposts >=", value, "digestposts");
            return (Criteria) this;
        }

        public Criteria andDigestpostsLessThan(Short value) {
            addCriterion("digestposts <", value, "digestposts");
            return (Criteria) this;
        }

        public Criteria andDigestpostsLessThanOrEqualTo(Short value) {
            addCriterion("digestposts <=", value, "digestposts");
            return (Criteria) this;
        }

        public Criteria andDigestpostsIn(List<Short> values) {
            addCriterion("digestposts in", values, "digestposts");
            return (Criteria) this;
        }

        public Criteria andDigestpostsNotIn(List<Short> values) {
            addCriterion("digestposts not in", values, "digestposts");
            return (Criteria) this;
        }

        public Criteria andDigestpostsBetween(Short value1, Short value2) {
            addCriterion("digestposts between", value1, value2, "digestposts");
            return (Criteria) this;
        }

        public Criteria andDigestpostsNotBetween(Short value1, Short value2) {
            addCriterion("digestposts not between", value1, value2, "digestposts");
            return (Criteria) this;
        }

        public Criteria andDoingsIsNull() {
            addCriterion("doings is null");
            return (Criteria) this;
        }

        public Criteria andDoingsIsNotNull() {
            addCriterion("doings is not null");
            return (Criteria) this;
        }

        public Criteria andDoingsEqualTo(Short value) {
            addCriterion("doings =", value, "doings");
            return (Criteria) this;
        }

        public Criteria andDoingsNotEqualTo(Short value) {
            addCriterion("doings <>", value, "doings");
            return (Criteria) this;
        }

        public Criteria andDoingsGreaterThan(Short value) {
            addCriterion("doings >", value, "doings");
            return (Criteria) this;
        }

        public Criteria andDoingsGreaterThanOrEqualTo(Short value) {
            addCriterion("doings >=", value, "doings");
            return (Criteria) this;
        }

        public Criteria andDoingsLessThan(Short value) {
            addCriterion("doings <", value, "doings");
            return (Criteria) this;
        }

        public Criteria andDoingsLessThanOrEqualTo(Short value) {
            addCriterion("doings <=", value, "doings");
            return (Criteria) this;
        }

        public Criteria andDoingsIn(List<Short> values) {
            addCriterion("doings in", values, "doings");
            return (Criteria) this;
        }

        public Criteria andDoingsNotIn(List<Short> values) {
            addCriterion("doings not in", values, "doings");
            return (Criteria) this;
        }

        public Criteria andDoingsBetween(Short value1, Short value2) {
            addCriterion("doings between", value1, value2, "doings");
            return (Criteria) this;
        }

        public Criteria andDoingsNotBetween(Short value1, Short value2) {
            addCriterion("doings not between", value1, value2, "doings");
            return (Criteria) this;
        }

        public Criteria andBlogsIsNull() {
            addCriterion("blogs is null");
            return (Criteria) this;
        }

        public Criteria andBlogsIsNotNull() {
            addCriterion("blogs is not null");
            return (Criteria) this;
        }

        public Criteria andBlogsEqualTo(Short value) {
            addCriterion("blogs =", value, "blogs");
            return (Criteria) this;
        }

        public Criteria andBlogsNotEqualTo(Short value) {
            addCriterion("blogs <>", value, "blogs");
            return (Criteria) this;
        }

        public Criteria andBlogsGreaterThan(Short value) {
            addCriterion("blogs >", value, "blogs");
            return (Criteria) this;
        }

        public Criteria andBlogsGreaterThanOrEqualTo(Short value) {
            addCriterion("blogs >=", value, "blogs");
            return (Criteria) this;
        }

        public Criteria andBlogsLessThan(Short value) {
            addCriterion("blogs <", value, "blogs");
            return (Criteria) this;
        }

        public Criteria andBlogsLessThanOrEqualTo(Short value) {
            addCriterion("blogs <=", value, "blogs");
            return (Criteria) this;
        }

        public Criteria andBlogsIn(List<Short> values) {
            addCriterion("blogs in", values, "blogs");
            return (Criteria) this;
        }

        public Criteria andBlogsNotIn(List<Short> values) {
            addCriterion("blogs not in", values, "blogs");
            return (Criteria) this;
        }

        public Criteria andBlogsBetween(Short value1, Short value2) {
            addCriterion("blogs between", value1, value2, "blogs");
            return (Criteria) this;
        }

        public Criteria andBlogsNotBetween(Short value1, Short value2) {
            addCriterion("blogs not between", value1, value2, "blogs");
            return (Criteria) this;
        }

        public Criteria andAlbumsIsNull() {
            addCriterion("albums is null");
            return (Criteria) this;
        }

        public Criteria andAlbumsIsNotNull() {
            addCriterion("albums is not null");
            return (Criteria) this;
        }

        public Criteria andAlbumsEqualTo(Short value) {
            addCriterion("albums =", value, "albums");
            return (Criteria) this;
        }

        public Criteria andAlbumsNotEqualTo(Short value) {
            addCriterion("albums <>", value, "albums");
            return (Criteria) this;
        }

        public Criteria andAlbumsGreaterThan(Short value) {
            addCriterion("albums >", value, "albums");
            return (Criteria) this;
        }

        public Criteria andAlbumsGreaterThanOrEqualTo(Short value) {
            addCriterion("albums >=", value, "albums");
            return (Criteria) this;
        }

        public Criteria andAlbumsLessThan(Short value) {
            addCriterion("albums <", value, "albums");
            return (Criteria) this;
        }

        public Criteria andAlbumsLessThanOrEqualTo(Short value) {
            addCriterion("albums <=", value, "albums");
            return (Criteria) this;
        }

        public Criteria andAlbumsIn(List<Short> values) {
            addCriterion("albums in", values, "albums");
            return (Criteria) this;
        }

        public Criteria andAlbumsNotIn(List<Short> values) {
            addCriterion("albums not in", values, "albums");
            return (Criteria) this;
        }

        public Criteria andAlbumsBetween(Short value1, Short value2) {
            addCriterion("albums between", value1, value2, "albums");
            return (Criteria) this;
        }

        public Criteria andAlbumsNotBetween(Short value1, Short value2) {
            addCriterion("albums not between", value1, value2, "albums");
            return (Criteria) this;
        }

        public Criteria andSharingsIsNull() {
            addCriterion("sharings is null");
            return (Criteria) this;
        }

        public Criteria andSharingsIsNotNull() {
            addCriterion("sharings is not null");
            return (Criteria) this;
        }

        public Criteria andSharingsEqualTo(Short value) {
            addCriterion("sharings =", value, "sharings");
            return (Criteria) this;
        }

        public Criteria andSharingsNotEqualTo(Short value) {
            addCriterion("sharings <>", value, "sharings");
            return (Criteria) this;
        }

        public Criteria andSharingsGreaterThan(Short value) {
            addCriterion("sharings >", value, "sharings");
            return (Criteria) this;
        }

        public Criteria andSharingsGreaterThanOrEqualTo(Short value) {
            addCriterion("sharings >=", value, "sharings");
            return (Criteria) this;
        }

        public Criteria andSharingsLessThan(Short value) {
            addCriterion("sharings <", value, "sharings");
            return (Criteria) this;
        }

        public Criteria andSharingsLessThanOrEqualTo(Short value) {
            addCriterion("sharings <=", value, "sharings");
            return (Criteria) this;
        }

        public Criteria andSharingsIn(List<Short> values) {
            addCriterion("sharings in", values, "sharings");
            return (Criteria) this;
        }

        public Criteria andSharingsNotIn(List<Short> values) {
            addCriterion("sharings not in", values, "sharings");
            return (Criteria) this;
        }

        public Criteria andSharingsBetween(Short value1, Short value2) {
            addCriterion("sharings between", value1, value2, "sharings");
            return (Criteria) this;
        }

        public Criteria andSharingsNotBetween(Short value1, Short value2) {
            addCriterion("sharings not between", value1, value2, "sharings");
            return (Criteria) this;
        }

        public Criteria andAttachsizeIsNull() {
            addCriterion("attachsize is null");
            return (Criteria) this;
        }

        public Criteria andAttachsizeIsNotNull() {
            addCriterion("attachsize is not null");
            return (Criteria) this;
        }

        public Criteria andAttachsizeEqualTo(Integer value) {
            addCriterion("attachsize =", value, "attachsize");
            return (Criteria) this;
        }

        public Criteria andAttachsizeNotEqualTo(Integer value) {
            addCriterion("attachsize <>", value, "attachsize");
            return (Criteria) this;
        }

        public Criteria andAttachsizeGreaterThan(Integer value) {
            addCriterion("attachsize >", value, "attachsize");
            return (Criteria) this;
        }

        public Criteria andAttachsizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("attachsize >=", value, "attachsize");
            return (Criteria) this;
        }

        public Criteria andAttachsizeLessThan(Integer value) {
            addCriterion("attachsize <", value, "attachsize");
            return (Criteria) this;
        }

        public Criteria andAttachsizeLessThanOrEqualTo(Integer value) {
            addCriterion("attachsize <=", value, "attachsize");
            return (Criteria) this;
        }

        public Criteria andAttachsizeIn(List<Integer> values) {
            addCriterion("attachsize in", values, "attachsize");
            return (Criteria) this;
        }

        public Criteria andAttachsizeNotIn(List<Integer> values) {
            addCriterion("attachsize not in", values, "attachsize");
            return (Criteria) this;
        }

        public Criteria andAttachsizeBetween(Integer value1, Integer value2) {
            addCriterion("attachsize between", value1, value2, "attachsize");
            return (Criteria) this;
        }

        public Criteria andAttachsizeNotBetween(Integer value1, Integer value2) {
            addCriterion("attachsize not between", value1, value2, "attachsize");
            return (Criteria) this;
        }

        public Criteria andViewsIsNull() {
            addCriterion("views is null");
            return (Criteria) this;
        }

        public Criteria andViewsIsNotNull() {
            addCriterion("views is not null");
            return (Criteria) this;
        }

        public Criteria andViewsEqualTo(Integer value) {
            addCriterion("views =", value, "views");
            return (Criteria) this;
        }

        public Criteria andViewsNotEqualTo(Integer value) {
            addCriterion("views <>", value, "views");
            return (Criteria) this;
        }

        public Criteria andViewsGreaterThan(Integer value) {
            addCriterion("views >", value, "views");
            return (Criteria) this;
        }

        public Criteria andViewsGreaterThanOrEqualTo(Integer value) {
            addCriterion("views >=", value, "views");
            return (Criteria) this;
        }

        public Criteria andViewsLessThan(Integer value) {
            addCriterion("views <", value, "views");
            return (Criteria) this;
        }

        public Criteria andViewsLessThanOrEqualTo(Integer value) {
            addCriterion("views <=", value, "views");
            return (Criteria) this;
        }

        public Criteria andViewsIn(List<Integer> values) {
            addCriterion("views in", values, "views");
            return (Criteria) this;
        }

        public Criteria andViewsNotIn(List<Integer> values) {
            addCriterion("views not in", values, "views");
            return (Criteria) this;
        }

        public Criteria andViewsBetween(Integer value1, Integer value2) {
            addCriterion("views between", value1, value2, "views");
            return (Criteria) this;
        }

        public Criteria andViewsNotBetween(Integer value1, Integer value2) {
            addCriterion("views not between", value1, value2, "views");
            return (Criteria) this;
        }

        public Criteria andOltimeIsNull() {
            addCriterion("oltime is null");
            return (Criteria) this;
        }

        public Criteria andOltimeIsNotNull() {
            addCriterion("oltime is not null");
            return (Criteria) this;
        }

        public Criteria andOltimeEqualTo(Short value) {
            addCriterion("oltime =", value, "oltime");
            return (Criteria) this;
        }

        public Criteria andOltimeNotEqualTo(Short value) {
            addCriterion("oltime <>", value, "oltime");
            return (Criteria) this;
        }

        public Criteria andOltimeGreaterThan(Short value) {
            addCriterion("oltime >", value, "oltime");
            return (Criteria) this;
        }

        public Criteria andOltimeGreaterThanOrEqualTo(Short value) {
            addCriterion("oltime >=", value, "oltime");
            return (Criteria) this;
        }

        public Criteria andOltimeLessThan(Short value) {
            addCriterion("oltime <", value, "oltime");
            return (Criteria) this;
        }

        public Criteria andOltimeLessThanOrEqualTo(Short value) {
            addCriterion("oltime <=", value, "oltime");
            return (Criteria) this;
        }

        public Criteria andOltimeIn(List<Short> values) {
            addCriterion("oltime in", values, "oltime");
            return (Criteria) this;
        }

        public Criteria andOltimeNotIn(List<Short> values) {
            addCriterion("oltime not in", values, "oltime");
            return (Criteria) this;
        }

        public Criteria andOltimeBetween(Short value1, Short value2) {
            addCriterion("oltime between", value1, value2, "oltime");
            return (Criteria) this;
        }

        public Criteria andOltimeNotBetween(Short value1, Short value2) {
            addCriterion("oltime not between", value1, value2, "oltime");
            return (Criteria) this;
        }

        public Criteria andTodayattachsIsNull() {
            addCriterion("todayattachs is null");
            return (Criteria) this;
        }

        public Criteria andTodayattachsIsNotNull() {
            addCriterion("todayattachs is not null");
            return (Criteria) this;
        }

        public Criteria andTodayattachsEqualTo(Short value) {
            addCriterion("todayattachs =", value, "todayattachs");
            return (Criteria) this;
        }

        public Criteria andTodayattachsNotEqualTo(Short value) {
            addCriterion("todayattachs <>", value, "todayattachs");
            return (Criteria) this;
        }

        public Criteria andTodayattachsGreaterThan(Short value) {
            addCriterion("todayattachs >", value, "todayattachs");
            return (Criteria) this;
        }

        public Criteria andTodayattachsGreaterThanOrEqualTo(Short value) {
            addCriterion("todayattachs >=", value, "todayattachs");
            return (Criteria) this;
        }

        public Criteria andTodayattachsLessThan(Short value) {
            addCriterion("todayattachs <", value, "todayattachs");
            return (Criteria) this;
        }

        public Criteria andTodayattachsLessThanOrEqualTo(Short value) {
            addCriterion("todayattachs <=", value, "todayattachs");
            return (Criteria) this;
        }

        public Criteria andTodayattachsIn(List<Short> values) {
            addCriterion("todayattachs in", values, "todayattachs");
            return (Criteria) this;
        }

        public Criteria andTodayattachsNotIn(List<Short> values) {
            addCriterion("todayattachs not in", values, "todayattachs");
            return (Criteria) this;
        }

        public Criteria andTodayattachsBetween(Short value1, Short value2) {
            addCriterion("todayattachs between", value1, value2, "todayattachs");
            return (Criteria) this;
        }

        public Criteria andTodayattachsNotBetween(Short value1, Short value2) {
            addCriterion("todayattachs not between", value1, value2, "todayattachs");
            return (Criteria) this;
        }

        public Criteria andTodayattachsizeIsNull() {
            addCriterion("todayattachsize is null");
            return (Criteria) this;
        }

        public Criteria andTodayattachsizeIsNotNull() {
            addCriterion("todayattachsize is not null");
            return (Criteria) this;
        }

        public Criteria andTodayattachsizeEqualTo(Integer value) {
            addCriterion("todayattachsize =", value, "todayattachsize");
            return (Criteria) this;
        }

        public Criteria andTodayattachsizeNotEqualTo(Integer value) {
            addCriterion("todayattachsize <>", value, "todayattachsize");
            return (Criteria) this;
        }

        public Criteria andTodayattachsizeGreaterThan(Integer value) {
            addCriterion("todayattachsize >", value, "todayattachsize");
            return (Criteria) this;
        }

        public Criteria andTodayattachsizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("todayattachsize >=", value, "todayattachsize");
            return (Criteria) this;
        }

        public Criteria andTodayattachsizeLessThan(Integer value) {
            addCriterion("todayattachsize <", value, "todayattachsize");
            return (Criteria) this;
        }

        public Criteria andTodayattachsizeLessThanOrEqualTo(Integer value) {
            addCriterion("todayattachsize <=", value, "todayattachsize");
            return (Criteria) this;
        }

        public Criteria andTodayattachsizeIn(List<Integer> values) {
            addCriterion("todayattachsize in", values, "todayattachsize");
            return (Criteria) this;
        }

        public Criteria andTodayattachsizeNotIn(List<Integer> values) {
            addCriterion("todayattachsize not in", values, "todayattachsize");
            return (Criteria) this;
        }

        public Criteria andTodayattachsizeBetween(Integer value1, Integer value2) {
            addCriterion("todayattachsize between", value1, value2, "todayattachsize");
            return (Criteria) this;
        }

        public Criteria andTodayattachsizeNotBetween(Integer value1, Integer value2) {
            addCriterion("todayattachsize not between", value1, value2, "todayattachsize");
            return (Criteria) this;
        }

        public Criteria andFeedsIsNull() {
            addCriterion("feeds is null");
            return (Criteria) this;
        }

        public Criteria andFeedsIsNotNull() {
            addCriterion("feeds is not null");
            return (Criteria) this;
        }

        public Criteria andFeedsEqualTo(Integer value) {
            addCriterion("feeds =", value, "feeds");
            return (Criteria) this;
        }

        public Criteria andFeedsNotEqualTo(Integer value) {
            addCriterion("feeds <>", value, "feeds");
            return (Criteria) this;
        }

        public Criteria andFeedsGreaterThan(Integer value) {
            addCriterion("feeds >", value, "feeds");
            return (Criteria) this;
        }

        public Criteria andFeedsGreaterThanOrEqualTo(Integer value) {
            addCriterion("feeds >=", value, "feeds");
            return (Criteria) this;
        }

        public Criteria andFeedsLessThan(Integer value) {
            addCriterion("feeds <", value, "feeds");
            return (Criteria) this;
        }

        public Criteria andFeedsLessThanOrEqualTo(Integer value) {
            addCriterion("feeds <=", value, "feeds");
            return (Criteria) this;
        }

        public Criteria andFeedsIn(List<Integer> values) {
            addCriterion("feeds in", values, "feeds");
            return (Criteria) this;
        }

        public Criteria andFeedsNotIn(List<Integer> values) {
            addCriterion("feeds not in", values, "feeds");
            return (Criteria) this;
        }

        public Criteria andFeedsBetween(Integer value1, Integer value2) {
            addCriterion("feeds between", value1, value2, "feeds");
            return (Criteria) this;
        }

        public Criteria andFeedsNotBetween(Integer value1, Integer value2) {
            addCriterion("feeds not between", value1, value2, "feeds");
            return (Criteria) this;
        }

        public Criteria andFollowerIsNull() {
            addCriterion("follower is null");
            return (Criteria) this;
        }

        public Criteria andFollowerIsNotNull() {
            addCriterion("follower is not null");
            return (Criteria) this;
        }

        public Criteria andFollowerEqualTo(Integer value) {
            addCriterion("follower =", value, "follower");
            return (Criteria) this;
        }

        public Criteria andFollowerNotEqualTo(Integer value) {
            addCriterion("follower <>", value, "follower");
            return (Criteria) this;
        }

        public Criteria andFollowerGreaterThan(Integer value) {
            addCriterion("follower >", value, "follower");
            return (Criteria) this;
        }

        public Criteria andFollowerGreaterThanOrEqualTo(Integer value) {
            addCriterion("follower >=", value, "follower");
            return (Criteria) this;
        }

        public Criteria andFollowerLessThan(Integer value) {
            addCriterion("follower <", value, "follower");
            return (Criteria) this;
        }

        public Criteria andFollowerLessThanOrEqualTo(Integer value) {
            addCriterion("follower <=", value, "follower");
            return (Criteria) this;
        }

        public Criteria andFollowerIn(List<Integer> values) {
            addCriterion("follower in", values, "follower");
            return (Criteria) this;
        }

        public Criteria andFollowerNotIn(List<Integer> values) {
            addCriterion("follower not in", values, "follower");
            return (Criteria) this;
        }

        public Criteria andFollowerBetween(Integer value1, Integer value2) {
            addCriterion("follower between", value1, value2, "follower");
            return (Criteria) this;
        }

        public Criteria andFollowerNotBetween(Integer value1, Integer value2) {
            addCriterion("follower not between", value1, value2, "follower");
            return (Criteria) this;
        }

        public Criteria andFollowingIsNull() {
            addCriterion("following is null");
            return (Criteria) this;
        }

        public Criteria andFollowingIsNotNull() {
            addCriterion("following is not null");
            return (Criteria) this;
        }

        public Criteria andFollowingEqualTo(Integer value) {
            addCriterion("following =", value, "following");
            return (Criteria) this;
        }

        public Criteria andFollowingNotEqualTo(Integer value) {
            addCriterion("following <>", value, "following");
            return (Criteria) this;
        }

        public Criteria andFollowingGreaterThan(Integer value) {
            addCriterion("following >", value, "following");
            return (Criteria) this;
        }

        public Criteria andFollowingGreaterThanOrEqualTo(Integer value) {
            addCriterion("following >=", value, "following");
            return (Criteria) this;
        }

        public Criteria andFollowingLessThan(Integer value) {
            addCriterion("following <", value, "following");
            return (Criteria) this;
        }

        public Criteria andFollowingLessThanOrEqualTo(Integer value) {
            addCriterion("following <=", value, "following");
            return (Criteria) this;
        }

        public Criteria andFollowingIn(List<Integer> values) {
            addCriterion("following in", values, "following");
            return (Criteria) this;
        }

        public Criteria andFollowingNotIn(List<Integer> values) {
            addCriterion("following not in", values, "following");
            return (Criteria) this;
        }

        public Criteria andFollowingBetween(Integer value1, Integer value2) {
            addCriterion("following between", value1, value2, "following");
            return (Criteria) this;
        }

        public Criteria andFollowingNotBetween(Integer value1, Integer value2) {
            addCriterion("following not between", value1, value2, "following");
            return (Criteria) this;
        }

        public Criteria andNewfollowerIsNull() {
            addCriterion("newfollower is null");
            return (Criteria) this;
        }

        public Criteria andNewfollowerIsNotNull() {
            addCriterion("newfollower is not null");
            return (Criteria) this;
        }

        public Criteria andNewfollowerEqualTo(Integer value) {
            addCriterion("newfollower =", value, "newfollower");
            return (Criteria) this;
        }

        public Criteria andNewfollowerNotEqualTo(Integer value) {
            addCriterion("newfollower <>", value, "newfollower");
            return (Criteria) this;
        }

        public Criteria andNewfollowerGreaterThan(Integer value) {
            addCriterion("newfollower >", value, "newfollower");
            return (Criteria) this;
        }

        public Criteria andNewfollowerGreaterThanOrEqualTo(Integer value) {
            addCriterion("newfollower >=", value, "newfollower");
            return (Criteria) this;
        }

        public Criteria andNewfollowerLessThan(Integer value) {
            addCriterion("newfollower <", value, "newfollower");
            return (Criteria) this;
        }

        public Criteria andNewfollowerLessThanOrEqualTo(Integer value) {
            addCriterion("newfollower <=", value, "newfollower");
            return (Criteria) this;
        }

        public Criteria andNewfollowerIn(List<Integer> values) {
            addCriterion("newfollower in", values, "newfollower");
            return (Criteria) this;
        }

        public Criteria andNewfollowerNotIn(List<Integer> values) {
            addCriterion("newfollower not in", values, "newfollower");
            return (Criteria) this;
        }

        public Criteria andNewfollowerBetween(Integer value1, Integer value2) {
            addCriterion("newfollower between", value1, value2, "newfollower");
            return (Criteria) this;
        }

        public Criteria andNewfollowerNotBetween(Integer value1, Integer value2) {
            addCriterion("newfollower not between", value1, value2, "newfollower");
            return (Criteria) this;
        }

        public Criteria andBlacklistIsNull() {
            addCriterion("blacklist is null");
            return (Criteria) this;
        }

        public Criteria andBlacklistIsNotNull() {
            addCriterion("blacklist is not null");
            return (Criteria) this;
        }

        public Criteria andBlacklistEqualTo(Integer value) {
            addCriterion("blacklist =", value, "blacklist");
            return (Criteria) this;
        }

        public Criteria andBlacklistNotEqualTo(Integer value) {
            addCriterion("blacklist <>", value, "blacklist");
            return (Criteria) this;
        }

        public Criteria andBlacklistGreaterThan(Integer value) {
            addCriterion("blacklist >", value, "blacklist");
            return (Criteria) this;
        }

        public Criteria andBlacklistGreaterThanOrEqualTo(Integer value) {
            addCriterion("blacklist >=", value, "blacklist");
            return (Criteria) this;
        }

        public Criteria andBlacklistLessThan(Integer value) {
            addCriterion("blacklist <", value, "blacklist");
            return (Criteria) this;
        }

        public Criteria andBlacklistLessThanOrEqualTo(Integer value) {
            addCriterion("blacklist <=", value, "blacklist");
            return (Criteria) this;
        }

        public Criteria andBlacklistIn(List<Integer> values) {
            addCriterion("blacklist in", values, "blacklist");
            return (Criteria) this;
        }

        public Criteria andBlacklistNotIn(List<Integer> values) {
            addCriterion("blacklist not in", values, "blacklist");
            return (Criteria) this;
        }

        public Criteria andBlacklistBetween(Integer value1, Integer value2) {
            addCriterion("blacklist between", value1, value2, "blacklist");
            return (Criteria) this;
        }

        public Criteria andBlacklistNotBetween(Integer value1, Integer value2) {
            addCriterion("blacklist not between", value1, value2, "blacklist");
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