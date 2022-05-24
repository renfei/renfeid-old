package net.renfei.proprietary.discuz.repositories.entity;

import java.util.ArrayList;
import java.util.List;

public class DiscuzUcenterMembersDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DiscuzUcenterMembersDOExample() {
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

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andMyidIsNull() {
            addCriterion("myid is null");
            return (Criteria) this;
        }

        public Criteria andMyidIsNotNull() {
            addCriterion("myid is not null");
            return (Criteria) this;
        }

        public Criteria andMyidEqualTo(String value) {
            addCriterion("myid =", value, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidNotEqualTo(String value) {
            addCriterion("myid <>", value, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidGreaterThan(String value) {
            addCriterion("myid >", value, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidGreaterThanOrEqualTo(String value) {
            addCriterion("myid >=", value, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidLessThan(String value) {
            addCriterion("myid <", value, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidLessThanOrEqualTo(String value) {
            addCriterion("myid <=", value, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidLike(String value) {
            addCriterion("myid like", value, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidNotLike(String value) {
            addCriterion("myid not like", value, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidIn(List<String> values) {
            addCriterion("myid in", values, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidNotIn(List<String> values) {
            addCriterion("myid not in", values, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidBetween(String value1, String value2) {
            addCriterion("myid between", value1, value2, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidNotBetween(String value1, String value2) {
            addCriterion("myid not between", value1, value2, "myid");
            return (Criteria) this;
        }

        public Criteria andMyidkeyIsNull() {
            addCriterion("myidkey is null");
            return (Criteria) this;
        }

        public Criteria andMyidkeyIsNotNull() {
            addCriterion("myidkey is not null");
            return (Criteria) this;
        }

        public Criteria andMyidkeyEqualTo(String value) {
            addCriterion("myidkey =", value, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyNotEqualTo(String value) {
            addCriterion("myidkey <>", value, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyGreaterThan(String value) {
            addCriterion("myidkey >", value, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyGreaterThanOrEqualTo(String value) {
            addCriterion("myidkey >=", value, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyLessThan(String value) {
            addCriterion("myidkey <", value, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyLessThanOrEqualTo(String value) {
            addCriterion("myidkey <=", value, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyLike(String value) {
            addCriterion("myidkey like", value, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyNotLike(String value) {
            addCriterion("myidkey not like", value, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyIn(List<String> values) {
            addCriterion("myidkey in", values, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyNotIn(List<String> values) {
            addCriterion("myidkey not in", values, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyBetween(String value1, String value2) {
            addCriterion("myidkey between", value1, value2, "myidkey");
            return (Criteria) this;
        }

        public Criteria andMyidkeyNotBetween(String value1, String value2) {
            addCriterion("myidkey not between", value1, value2, "myidkey");
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

        public Criteria andRegdateIsNull() {
            addCriterion("regdate is null");
            return (Criteria) this;
        }

        public Criteria andRegdateIsNotNull() {
            addCriterion("regdate is not null");
            return (Criteria) this;
        }

        public Criteria andRegdateEqualTo(Integer value) {
            addCriterion("regdate =", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateNotEqualTo(Integer value) {
            addCriterion("regdate <>", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateGreaterThan(Integer value) {
            addCriterion("regdate >", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateGreaterThanOrEqualTo(Integer value) {
            addCriterion("regdate >=", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateLessThan(Integer value) {
            addCriterion("regdate <", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateLessThanOrEqualTo(Integer value) {
            addCriterion("regdate <=", value, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateIn(List<Integer> values) {
            addCriterion("regdate in", values, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateNotIn(List<Integer> values) {
            addCriterion("regdate not in", values, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateBetween(Integer value1, Integer value2) {
            addCriterion("regdate between", value1, value2, "regdate");
            return (Criteria) this;
        }

        public Criteria andRegdateNotBetween(Integer value1, Integer value2) {
            addCriterion("regdate not between", value1, value2, "regdate");
            return (Criteria) this;
        }

        public Criteria andLastloginipIsNull() {
            addCriterion("lastloginip is null");
            return (Criteria) this;
        }

        public Criteria andLastloginipIsNotNull() {
            addCriterion("lastloginip is not null");
            return (Criteria) this;
        }

        public Criteria andLastloginipEqualTo(Integer value) {
            addCriterion("lastloginip =", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipNotEqualTo(Integer value) {
            addCriterion("lastloginip <>", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipGreaterThan(Integer value) {
            addCriterion("lastloginip >", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipGreaterThanOrEqualTo(Integer value) {
            addCriterion("lastloginip >=", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipLessThan(Integer value) {
            addCriterion("lastloginip <", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipLessThanOrEqualTo(Integer value) {
            addCriterion("lastloginip <=", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipIn(List<Integer> values) {
            addCriterion("lastloginip in", values, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipNotIn(List<Integer> values) {
            addCriterion("lastloginip not in", values, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipBetween(Integer value1, Integer value2) {
            addCriterion("lastloginip between", value1, value2, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipNotBetween(Integer value1, Integer value2) {
            addCriterion("lastloginip not between", value1, value2, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeIsNull() {
            addCriterion("lastlogintime is null");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeIsNotNull() {
            addCriterion("lastlogintime is not null");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeEqualTo(Integer value) {
            addCriterion("lastlogintime =", value, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeNotEqualTo(Integer value) {
            addCriterion("lastlogintime <>", value, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeGreaterThan(Integer value) {
            addCriterion("lastlogintime >", value, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("lastlogintime >=", value, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeLessThan(Integer value) {
            addCriterion("lastlogintime <", value, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeLessThanOrEqualTo(Integer value) {
            addCriterion("lastlogintime <=", value, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeIn(List<Integer> values) {
            addCriterion("lastlogintime in", values, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeNotIn(List<Integer> values) {
            addCriterion("lastlogintime not in", values, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeBetween(Integer value1, Integer value2) {
            addCriterion("lastlogintime between", value1, value2, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeNotBetween(Integer value1, Integer value2) {
            addCriterion("lastlogintime not between", value1, value2, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andSaltIsNull() {
            addCriterion("salt is null");
            return (Criteria) this;
        }

        public Criteria andSaltIsNotNull() {
            addCriterion("salt is not null");
            return (Criteria) this;
        }

        public Criteria andSaltEqualTo(String value) {
            addCriterion("salt =", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotEqualTo(String value) {
            addCriterion("salt <>", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThan(String value) {
            addCriterion("salt >", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThanOrEqualTo(String value) {
            addCriterion("salt >=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThan(String value) {
            addCriterion("salt <", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThanOrEqualTo(String value) {
            addCriterion("salt <=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLike(String value) {
            addCriterion("salt like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotLike(String value) {
            addCriterion("salt not like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltIn(List<String> values) {
            addCriterion("salt in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotIn(List<String> values) {
            addCriterion("salt not in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltBetween(String value1, String value2) {
            addCriterion("salt between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotBetween(String value1, String value2) {
            addCriterion("salt not between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andSecquesIsNull() {
            addCriterion("secques is null");
            return (Criteria) this;
        }

        public Criteria andSecquesIsNotNull() {
            addCriterion("secques is not null");
            return (Criteria) this;
        }

        public Criteria andSecquesEqualTo(String value) {
            addCriterion("secques =", value, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesNotEqualTo(String value) {
            addCriterion("secques <>", value, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesGreaterThan(String value) {
            addCriterion("secques >", value, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesGreaterThanOrEqualTo(String value) {
            addCriterion("secques >=", value, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesLessThan(String value) {
            addCriterion("secques <", value, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesLessThanOrEqualTo(String value) {
            addCriterion("secques <=", value, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesLike(String value) {
            addCriterion("secques like", value, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesNotLike(String value) {
            addCriterion("secques not like", value, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesIn(List<String> values) {
            addCriterion("secques in", values, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesNotIn(List<String> values) {
            addCriterion("secques not in", values, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesBetween(String value1, String value2) {
            addCriterion("secques between", value1, value2, "secques");
            return (Criteria) this;
        }

        public Criteria andSecquesNotBetween(String value1, String value2) {
            addCriterion("secques not between", value1, value2, "secques");
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