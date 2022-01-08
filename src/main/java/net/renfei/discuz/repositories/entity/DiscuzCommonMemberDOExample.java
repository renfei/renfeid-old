package net.renfei.discuz.repositories.entity;

import java.util.ArrayList;
import java.util.List;

public class DiscuzCommonMemberDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DiscuzCommonMemberDOExample() {
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

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Boolean value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Boolean value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Boolean value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Boolean value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Boolean> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Boolean> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andEmailstatusIsNull() {
            addCriterion("emailstatus is null");
            return (Criteria) this;
        }

        public Criteria andEmailstatusIsNotNull() {
            addCriterion("emailstatus is not null");
            return (Criteria) this;
        }

        public Criteria andEmailstatusEqualTo(Integer value) {
            addCriterion("emailstatus =", value, "emailstatus");
            return (Criteria) this;
        }

        public Criteria andEmailstatusNotEqualTo(Integer value) {
            addCriterion("emailstatus <>", value, "emailstatus");
            return (Criteria) this;
        }

        public Criteria andEmailstatusGreaterThan(Integer value) {
            addCriterion("emailstatus >", value, "emailstatus");
            return (Criteria) this;
        }

        public Criteria andEmailstatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("emailstatus >=", value, "emailstatus");
            return (Criteria) this;
        }

        public Criteria andEmailstatusLessThan(Integer value) {
            addCriterion("emailstatus <", value, "emailstatus");
            return (Criteria) this;
        }

        public Criteria andEmailstatusLessThanOrEqualTo(Integer value) {
            addCriterion("emailstatus <=", value, "emailstatus");
            return (Criteria) this;
        }

        public Criteria andEmailstatusIn(List<Integer> values) {
            addCriterion("emailstatus in", values, "emailstatus");
            return (Criteria) this;
        }

        public Criteria andEmailstatusNotIn(List<Integer> values) {
            addCriterion("emailstatus not in", values, "emailstatus");
            return (Criteria) this;
        }

        public Criteria andEmailstatusBetween(Integer value1, Integer value2) {
            addCriterion("emailstatus between", value1, value2, "emailstatus");
            return (Criteria) this;
        }

        public Criteria andEmailstatusNotBetween(Integer value1, Integer value2) {
            addCriterion("emailstatus not between", value1, value2, "emailstatus");
            return (Criteria) this;
        }

        public Criteria andAvatarstatusIsNull() {
            addCriterion("avatarstatus is null");
            return (Criteria) this;
        }

        public Criteria andAvatarstatusIsNotNull() {
            addCriterion("avatarstatus is not null");
            return (Criteria) this;
        }

        public Criteria andAvatarstatusEqualTo(Boolean value) {
            addCriterion("avatarstatus =", value, "avatarstatus");
            return (Criteria) this;
        }

        public Criteria andAvatarstatusNotEqualTo(Boolean value) {
            addCriterion("avatarstatus <>", value, "avatarstatus");
            return (Criteria) this;
        }

        public Criteria andAvatarstatusGreaterThan(Boolean value) {
            addCriterion("avatarstatus >", value, "avatarstatus");
            return (Criteria) this;
        }

        public Criteria andAvatarstatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("avatarstatus >=", value, "avatarstatus");
            return (Criteria) this;
        }

        public Criteria andAvatarstatusLessThan(Boolean value) {
            addCriterion("avatarstatus <", value, "avatarstatus");
            return (Criteria) this;
        }

        public Criteria andAvatarstatusLessThanOrEqualTo(Boolean value) {
            addCriterion("avatarstatus <=", value, "avatarstatus");
            return (Criteria) this;
        }

        public Criteria andAvatarstatusIn(List<Boolean> values) {
            addCriterion("avatarstatus in", values, "avatarstatus");
            return (Criteria) this;
        }

        public Criteria andAvatarstatusNotIn(List<Boolean> values) {
            addCriterion("avatarstatus not in", values, "avatarstatus");
            return (Criteria) this;
        }

        public Criteria andAvatarstatusBetween(Boolean value1, Boolean value2) {
            addCriterion("avatarstatus between", value1, value2, "avatarstatus");
            return (Criteria) this;
        }

        public Criteria andAvatarstatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("avatarstatus not between", value1, value2, "avatarstatus");
            return (Criteria) this;
        }

        public Criteria andVideophotostatusIsNull() {
            addCriterion("videophotostatus is null");
            return (Criteria) this;
        }

        public Criteria andVideophotostatusIsNotNull() {
            addCriterion("videophotostatus is not null");
            return (Criteria) this;
        }

        public Criteria andVideophotostatusEqualTo(Boolean value) {
            addCriterion("videophotostatus =", value, "videophotostatus");
            return (Criteria) this;
        }

        public Criteria andVideophotostatusNotEqualTo(Boolean value) {
            addCriterion("videophotostatus <>", value, "videophotostatus");
            return (Criteria) this;
        }

        public Criteria andVideophotostatusGreaterThan(Boolean value) {
            addCriterion("videophotostatus >", value, "videophotostatus");
            return (Criteria) this;
        }

        public Criteria andVideophotostatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("videophotostatus >=", value, "videophotostatus");
            return (Criteria) this;
        }

        public Criteria andVideophotostatusLessThan(Boolean value) {
            addCriterion("videophotostatus <", value, "videophotostatus");
            return (Criteria) this;
        }

        public Criteria andVideophotostatusLessThanOrEqualTo(Boolean value) {
            addCriterion("videophotostatus <=", value, "videophotostatus");
            return (Criteria) this;
        }

        public Criteria andVideophotostatusIn(List<Boolean> values) {
            addCriterion("videophotostatus in", values, "videophotostatus");
            return (Criteria) this;
        }

        public Criteria andVideophotostatusNotIn(List<Boolean> values) {
            addCriterion("videophotostatus not in", values, "videophotostatus");
            return (Criteria) this;
        }

        public Criteria andVideophotostatusBetween(Boolean value1, Boolean value2) {
            addCriterion("videophotostatus between", value1, value2, "videophotostatus");
            return (Criteria) this;
        }

        public Criteria andVideophotostatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("videophotostatus not between", value1, value2, "videophotostatus");
            return (Criteria) this;
        }

        public Criteria andAdminidIsNull() {
            addCriterion("adminid is null");
            return (Criteria) this;
        }

        public Criteria andAdminidIsNotNull() {
            addCriterion("adminid is not null");
            return (Criteria) this;
        }

        public Criteria andAdminidEqualTo(Boolean value) {
            addCriterion("adminid =", value, "adminid");
            return (Criteria) this;
        }

        public Criteria andAdminidNotEqualTo(Boolean value) {
            addCriterion("adminid <>", value, "adminid");
            return (Criteria) this;
        }

        public Criteria andAdminidGreaterThan(Boolean value) {
            addCriterion("adminid >", value, "adminid");
            return (Criteria) this;
        }

        public Criteria andAdminidGreaterThanOrEqualTo(Boolean value) {
            addCriterion("adminid >=", value, "adminid");
            return (Criteria) this;
        }

        public Criteria andAdminidLessThan(Boolean value) {
            addCriterion("adminid <", value, "adminid");
            return (Criteria) this;
        }

        public Criteria andAdminidLessThanOrEqualTo(Boolean value) {
            addCriterion("adminid <=", value, "adminid");
            return (Criteria) this;
        }

        public Criteria andAdminidIn(List<Boolean> values) {
            addCriterion("adminid in", values, "adminid");
            return (Criteria) this;
        }

        public Criteria andAdminidNotIn(List<Boolean> values) {
            addCriterion("adminid not in", values, "adminid");
            return (Criteria) this;
        }

        public Criteria andAdminidBetween(Boolean value1, Boolean value2) {
            addCriterion("adminid between", value1, value2, "adminid");
            return (Criteria) this;
        }

        public Criteria andAdminidNotBetween(Boolean value1, Boolean value2) {
            addCriterion("adminid not between", value1, value2, "adminid");
            return (Criteria) this;
        }

        public Criteria andGroupidIsNull() {
            addCriterion("groupid is null");
            return (Criteria) this;
        }

        public Criteria andGroupidIsNotNull() {
            addCriterion("groupid is not null");
            return (Criteria) this;
        }

        public Criteria andGroupidEqualTo(Short value) {
            addCriterion("groupid =", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotEqualTo(Short value) {
            addCriterion("groupid <>", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidGreaterThan(Short value) {
            addCriterion("groupid >", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidGreaterThanOrEqualTo(Short value) {
            addCriterion("groupid >=", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidLessThan(Short value) {
            addCriterion("groupid <", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidLessThanOrEqualTo(Short value) {
            addCriterion("groupid <=", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidIn(List<Short> values) {
            addCriterion("groupid in", values, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotIn(List<Short> values) {
            addCriterion("groupid not in", values, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidBetween(Short value1, Short value2) {
            addCriterion("groupid between", value1, value2, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotBetween(Short value1, Short value2) {
            addCriterion("groupid not between", value1, value2, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupexpiryIsNull() {
            addCriterion("groupexpiry is null");
            return (Criteria) this;
        }

        public Criteria andGroupexpiryIsNotNull() {
            addCriterion("groupexpiry is not null");
            return (Criteria) this;
        }

        public Criteria andGroupexpiryEqualTo(Integer value) {
            addCriterion("groupexpiry =", value, "groupexpiry");
            return (Criteria) this;
        }

        public Criteria andGroupexpiryNotEqualTo(Integer value) {
            addCriterion("groupexpiry <>", value, "groupexpiry");
            return (Criteria) this;
        }

        public Criteria andGroupexpiryGreaterThan(Integer value) {
            addCriterion("groupexpiry >", value, "groupexpiry");
            return (Criteria) this;
        }

        public Criteria andGroupexpiryGreaterThanOrEqualTo(Integer value) {
            addCriterion("groupexpiry >=", value, "groupexpiry");
            return (Criteria) this;
        }

        public Criteria andGroupexpiryLessThan(Integer value) {
            addCriterion("groupexpiry <", value, "groupexpiry");
            return (Criteria) this;
        }

        public Criteria andGroupexpiryLessThanOrEqualTo(Integer value) {
            addCriterion("groupexpiry <=", value, "groupexpiry");
            return (Criteria) this;
        }

        public Criteria andGroupexpiryIn(List<Integer> values) {
            addCriterion("groupexpiry in", values, "groupexpiry");
            return (Criteria) this;
        }

        public Criteria andGroupexpiryNotIn(List<Integer> values) {
            addCriterion("groupexpiry not in", values, "groupexpiry");
            return (Criteria) this;
        }

        public Criteria andGroupexpiryBetween(Integer value1, Integer value2) {
            addCriterion("groupexpiry between", value1, value2, "groupexpiry");
            return (Criteria) this;
        }

        public Criteria andGroupexpiryNotBetween(Integer value1, Integer value2) {
            addCriterion("groupexpiry not between", value1, value2, "groupexpiry");
            return (Criteria) this;
        }

        public Criteria andExtgroupidsIsNull() {
            addCriterion("extgroupids is null");
            return (Criteria) this;
        }

        public Criteria andExtgroupidsIsNotNull() {
            addCriterion("extgroupids is not null");
            return (Criteria) this;
        }

        public Criteria andExtgroupidsEqualTo(String value) {
            addCriterion("extgroupids =", value, "extgroupids");
            return (Criteria) this;
        }

        public Criteria andExtgroupidsNotEqualTo(String value) {
            addCriterion("extgroupids <>", value, "extgroupids");
            return (Criteria) this;
        }

        public Criteria andExtgroupidsGreaterThan(String value) {
            addCriterion("extgroupids >", value, "extgroupids");
            return (Criteria) this;
        }

        public Criteria andExtgroupidsGreaterThanOrEqualTo(String value) {
            addCriterion("extgroupids >=", value, "extgroupids");
            return (Criteria) this;
        }

        public Criteria andExtgroupidsLessThan(String value) {
            addCriterion("extgroupids <", value, "extgroupids");
            return (Criteria) this;
        }

        public Criteria andExtgroupidsLessThanOrEqualTo(String value) {
            addCriterion("extgroupids <=", value, "extgroupids");
            return (Criteria) this;
        }

        public Criteria andExtgroupidsLike(String value) {
            addCriterion("extgroupids like", value, "extgroupids");
            return (Criteria) this;
        }

        public Criteria andExtgroupidsNotLike(String value) {
            addCriterion("extgroupids not like", value, "extgroupids");
            return (Criteria) this;
        }

        public Criteria andExtgroupidsIn(List<String> values) {
            addCriterion("extgroupids in", values, "extgroupids");
            return (Criteria) this;
        }

        public Criteria andExtgroupidsNotIn(List<String> values) {
            addCriterion("extgroupids not in", values, "extgroupids");
            return (Criteria) this;
        }

        public Criteria andExtgroupidsBetween(String value1, String value2) {
            addCriterion("extgroupids between", value1, value2, "extgroupids");
            return (Criteria) this;
        }

        public Criteria andExtgroupidsNotBetween(String value1, String value2) {
            addCriterion("extgroupids not between", value1, value2, "extgroupids");
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

        public Criteria andCreditsIsNull() {
            addCriterion("credits is null");
            return (Criteria) this;
        }

        public Criteria andCreditsIsNotNull() {
            addCriterion("credits is not null");
            return (Criteria) this;
        }

        public Criteria andCreditsEqualTo(Integer value) {
            addCriterion("credits =", value, "credits");
            return (Criteria) this;
        }

        public Criteria andCreditsNotEqualTo(Integer value) {
            addCriterion("credits <>", value, "credits");
            return (Criteria) this;
        }

        public Criteria andCreditsGreaterThan(Integer value) {
            addCriterion("credits >", value, "credits");
            return (Criteria) this;
        }

        public Criteria andCreditsGreaterThanOrEqualTo(Integer value) {
            addCriterion("credits >=", value, "credits");
            return (Criteria) this;
        }

        public Criteria andCreditsLessThan(Integer value) {
            addCriterion("credits <", value, "credits");
            return (Criteria) this;
        }

        public Criteria andCreditsLessThanOrEqualTo(Integer value) {
            addCriterion("credits <=", value, "credits");
            return (Criteria) this;
        }

        public Criteria andCreditsIn(List<Integer> values) {
            addCriterion("credits in", values, "credits");
            return (Criteria) this;
        }

        public Criteria andCreditsNotIn(List<Integer> values) {
            addCriterion("credits not in", values, "credits");
            return (Criteria) this;
        }

        public Criteria andCreditsBetween(Integer value1, Integer value2) {
            addCriterion("credits between", value1, value2, "credits");
            return (Criteria) this;
        }

        public Criteria andCreditsNotBetween(Integer value1, Integer value2) {
            addCriterion("credits not between", value1, value2, "credits");
            return (Criteria) this;
        }

        public Criteria andNotifysoundIsNull() {
            addCriterion("notifysound is null");
            return (Criteria) this;
        }

        public Criteria andNotifysoundIsNotNull() {
            addCriterion("notifysound is not null");
            return (Criteria) this;
        }

        public Criteria andNotifysoundEqualTo(Boolean value) {
            addCriterion("notifysound =", value, "notifysound");
            return (Criteria) this;
        }

        public Criteria andNotifysoundNotEqualTo(Boolean value) {
            addCriterion("notifysound <>", value, "notifysound");
            return (Criteria) this;
        }

        public Criteria andNotifysoundGreaterThan(Boolean value) {
            addCriterion("notifysound >", value, "notifysound");
            return (Criteria) this;
        }

        public Criteria andNotifysoundGreaterThanOrEqualTo(Boolean value) {
            addCriterion("notifysound >=", value, "notifysound");
            return (Criteria) this;
        }

        public Criteria andNotifysoundLessThan(Boolean value) {
            addCriterion("notifysound <", value, "notifysound");
            return (Criteria) this;
        }

        public Criteria andNotifysoundLessThanOrEqualTo(Boolean value) {
            addCriterion("notifysound <=", value, "notifysound");
            return (Criteria) this;
        }

        public Criteria andNotifysoundIn(List<Boolean> values) {
            addCriterion("notifysound in", values, "notifysound");
            return (Criteria) this;
        }

        public Criteria andNotifysoundNotIn(List<Boolean> values) {
            addCriterion("notifysound not in", values, "notifysound");
            return (Criteria) this;
        }

        public Criteria andNotifysoundBetween(Boolean value1, Boolean value2) {
            addCriterion("notifysound between", value1, value2, "notifysound");
            return (Criteria) this;
        }

        public Criteria andNotifysoundNotBetween(Boolean value1, Boolean value2) {
            addCriterion("notifysound not between", value1, value2, "notifysound");
            return (Criteria) this;
        }

        public Criteria andTimeoffsetIsNull() {
            addCriterion("timeoffset is null");
            return (Criteria) this;
        }

        public Criteria andTimeoffsetIsNotNull() {
            addCriterion("timeoffset is not null");
            return (Criteria) this;
        }

        public Criteria andTimeoffsetEqualTo(String value) {
            addCriterion("timeoffset =", value, "timeoffset");
            return (Criteria) this;
        }

        public Criteria andTimeoffsetNotEqualTo(String value) {
            addCriterion("timeoffset <>", value, "timeoffset");
            return (Criteria) this;
        }

        public Criteria andTimeoffsetGreaterThan(String value) {
            addCriterion("timeoffset >", value, "timeoffset");
            return (Criteria) this;
        }

        public Criteria andTimeoffsetGreaterThanOrEqualTo(String value) {
            addCriterion("timeoffset >=", value, "timeoffset");
            return (Criteria) this;
        }

        public Criteria andTimeoffsetLessThan(String value) {
            addCriterion("timeoffset <", value, "timeoffset");
            return (Criteria) this;
        }

        public Criteria andTimeoffsetLessThanOrEqualTo(String value) {
            addCriterion("timeoffset <=", value, "timeoffset");
            return (Criteria) this;
        }

        public Criteria andTimeoffsetLike(String value) {
            addCriterion("timeoffset like", value, "timeoffset");
            return (Criteria) this;
        }

        public Criteria andTimeoffsetNotLike(String value) {
            addCriterion("timeoffset not like", value, "timeoffset");
            return (Criteria) this;
        }

        public Criteria andTimeoffsetIn(List<String> values) {
            addCriterion("timeoffset in", values, "timeoffset");
            return (Criteria) this;
        }

        public Criteria andTimeoffsetNotIn(List<String> values) {
            addCriterion("timeoffset not in", values, "timeoffset");
            return (Criteria) this;
        }

        public Criteria andTimeoffsetBetween(String value1, String value2) {
            addCriterion("timeoffset between", value1, value2, "timeoffset");
            return (Criteria) this;
        }

        public Criteria andTimeoffsetNotBetween(String value1, String value2) {
            addCriterion("timeoffset not between", value1, value2, "timeoffset");
            return (Criteria) this;
        }

        public Criteria andNewpmIsNull() {
            addCriterion("newpm is null");
            return (Criteria) this;
        }

        public Criteria andNewpmIsNotNull() {
            addCriterion("newpm is not null");
            return (Criteria) this;
        }

        public Criteria andNewpmEqualTo(Short value) {
            addCriterion("newpm =", value, "newpm");
            return (Criteria) this;
        }

        public Criteria andNewpmNotEqualTo(Short value) {
            addCriterion("newpm <>", value, "newpm");
            return (Criteria) this;
        }

        public Criteria andNewpmGreaterThan(Short value) {
            addCriterion("newpm >", value, "newpm");
            return (Criteria) this;
        }

        public Criteria andNewpmGreaterThanOrEqualTo(Short value) {
            addCriterion("newpm >=", value, "newpm");
            return (Criteria) this;
        }

        public Criteria andNewpmLessThan(Short value) {
            addCriterion("newpm <", value, "newpm");
            return (Criteria) this;
        }

        public Criteria andNewpmLessThanOrEqualTo(Short value) {
            addCriterion("newpm <=", value, "newpm");
            return (Criteria) this;
        }

        public Criteria andNewpmIn(List<Short> values) {
            addCriterion("newpm in", values, "newpm");
            return (Criteria) this;
        }

        public Criteria andNewpmNotIn(List<Short> values) {
            addCriterion("newpm not in", values, "newpm");
            return (Criteria) this;
        }

        public Criteria andNewpmBetween(Short value1, Short value2) {
            addCriterion("newpm between", value1, value2, "newpm");
            return (Criteria) this;
        }

        public Criteria andNewpmNotBetween(Short value1, Short value2) {
            addCriterion("newpm not between", value1, value2, "newpm");
            return (Criteria) this;
        }

        public Criteria andNewpromptIsNull() {
            addCriterion("newprompt is null");
            return (Criteria) this;
        }

        public Criteria andNewpromptIsNotNull() {
            addCriterion("newprompt is not null");
            return (Criteria) this;
        }

        public Criteria andNewpromptEqualTo(Short value) {
            addCriterion("newprompt =", value, "newprompt");
            return (Criteria) this;
        }

        public Criteria andNewpromptNotEqualTo(Short value) {
            addCriterion("newprompt <>", value, "newprompt");
            return (Criteria) this;
        }

        public Criteria andNewpromptGreaterThan(Short value) {
            addCriterion("newprompt >", value, "newprompt");
            return (Criteria) this;
        }

        public Criteria andNewpromptGreaterThanOrEqualTo(Short value) {
            addCriterion("newprompt >=", value, "newprompt");
            return (Criteria) this;
        }

        public Criteria andNewpromptLessThan(Short value) {
            addCriterion("newprompt <", value, "newprompt");
            return (Criteria) this;
        }

        public Criteria andNewpromptLessThanOrEqualTo(Short value) {
            addCriterion("newprompt <=", value, "newprompt");
            return (Criteria) this;
        }

        public Criteria andNewpromptIn(List<Short> values) {
            addCriterion("newprompt in", values, "newprompt");
            return (Criteria) this;
        }

        public Criteria andNewpromptNotIn(List<Short> values) {
            addCriterion("newprompt not in", values, "newprompt");
            return (Criteria) this;
        }

        public Criteria andNewpromptBetween(Short value1, Short value2) {
            addCriterion("newprompt between", value1, value2, "newprompt");
            return (Criteria) this;
        }

        public Criteria andNewpromptNotBetween(Short value1, Short value2) {
            addCriterion("newprompt not between", value1, value2, "newprompt");
            return (Criteria) this;
        }

        public Criteria andAccessmasksIsNull() {
            addCriterion("accessmasks is null");
            return (Criteria) this;
        }

        public Criteria andAccessmasksIsNotNull() {
            addCriterion("accessmasks is not null");
            return (Criteria) this;
        }

        public Criteria andAccessmasksEqualTo(Boolean value) {
            addCriterion("accessmasks =", value, "accessmasks");
            return (Criteria) this;
        }

        public Criteria andAccessmasksNotEqualTo(Boolean value) {
            addCriterion("accessmasks <>", value, "accessmasks");
            return (Criteria) this;
        }

        public Criteria andAccessmasksGreaterThan(Boolean value) {
            addCriterion("accessmasks >", value, "accessmasks");
            return (Criteria) this;
        }

        public Criteria andAccessmasksGreaterThanOrEqualTo(Boolean value) {
            addCriterion("accessmasks >=", value, "accessmasks");
            return (Criteria) this;
        }

        public Criteria andAccessmasksLessThan(Boolean value) {
            addCriterion("accessmasks <", value, "accessmasks");
            return (Criteria) this;
        }

        public Criteria andAccessmasksLessThanOrEqualTo(Boolean value) {
            addCriterion("accessmasks <=", value, "accessmasks");
            return (Criteria) this;
        }

        public Criteria andAccessmasksIn(List<Boolean> values) {
            addCriterion("accessmasks in", values, "accessmasks");
            return (Criteria) this;
        }

        public Criteria andAccessmasksNotIn(List<Boolean> values) {
            addCriterion("accessmasks not in", values, "accessmasks");
            return (Criteria) this;
        }

        public Criteria andAccessmasksBetween(Boolean value1, Boolean value2) {
            addCriterion("accessmasks between", value1, value2, "accessmasks");
            return (Criteria) this;
        }

        public Criteria andAccessmasksNotBetween(Boolean value1, Boolean value2) {
            addCriterion("accessmasks not between", value1, value2, "accessmasks");
            return (Criteria) this;
        }

        public Criteria andAllowadmincpIsNull() {
            addCriterion("allowadmincp is null");
            return (Criteria) this;
        }

        public Criteria andAllowadmincpIsNotNull() {
            addCriterion("allowadmincp is not null");
            return (Criteria) this;
        }

        public Criteria andAllowadmincpEqualTo(Boolean value) {
            addCriterion("allowadmincp =", value, "allowadmincp");
            return (Criteria) this;
        }

        public Criteria andAllowadmincpNotEqualTo(Boolean value) {
            addCriterion("allowadmincp <>", value, "allowadmincp");
            return (Criteria) this;
        }

        public Criteria andAllowadmincpGreaterThan(Boolean value) {
            addCriterion("allowadmincp >", value, "allowadmincp");
            return (Criteria) this;
        }

        public Criteria andAllowadmincpGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allowadmincp >=", value, "allowadmincp");
            return (Criteria) this;
        }

        public Criteria andAllowadmincpLessThan(Boolean value) {
            addCriterion("allowadmincp <", value, "allowadmincp");
            return (Criteria) this;
        }

        public Criteria andAllowadmincpLessThanOrEqualTo(Boolean value) {
            addCriterion("allowadmincp <=", value, "allowadmincp");
            return (Criteria) this;
        }

        public Criteria andAllowadmincpIn(List<Boolean> values) {
            addCriterion("allowadmincp in", values, "allowadmincp");
            return (Criteria) this;
        }

        public Criteria andAllowadmincpNotIn(List<Boolean> values) {
            addCriterion("allowadmincp not in", values, "allowadmincp");
            return (Criteria) this;
        }

        public Criteria andAllowadmincpBetween(Boolean value1, Boolean value2) {
            addCriterion("allowadmincp between", value1, value2, "allowadmincp");
            return (Criteria) this;
        }

        public Criteria andAllowadmincpNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allowadmincp not between", value1, value2, "allowadmincp");
            return (Criteria) this;
        }

        public Criteria andOnlyacceptfriendpmIsNull() {
            addCriterion("onlyacceptfriendpm is null");
            return (Criteria) this;
        }

        public Criteria andOnlyacceptfriendpmIsNotNull() {
            addCriterion("onlyacceptfriendpm is not null");
            return (Criteria) this;
        }

        public Criteria andOnlyacceptfriendpmEqualTo(Boolean value) {
            addCriterion("onlyacceptfriendpm =", value, "onlyacceptfriendpm");
            return (Criteria) this;
        }

        public Criteria andOnlyacceptfriendpmNotEqualTo(Boolean value) {
            addCriterion("onlyacceptfriendpm <>", value, "onlyacceptfriendpm");
            return (Criteria) this;
        }

        public Criteria andOnlyacceptfriendpmGreaterThan(Boolean value) {
            addCriterion("onlyacceptfriendpm >", value, "onlyacceptfriendpm");
            return (Criteria) this;
        }

        public Criteria andOnlyacceptfriendpmGreaterThanOrEqualTo(Boolean value) {
            addCriterion("onlyacceptfriendpm >=", value, "onlyacceptfriendpm");
            return (Criteria) this;
        }

        public Criteria andOnlyacceptfriendpmLessThan(Boolean value) {
            addCriterion("onlyacceptfriendpm <", value, "onlyacceptfriendpm");
            return (Criteria) this;
        }

        public Criteria andOnlyacceptfriendpmLessThanOrEqualTo(Boolean value) {
            addCriterion("onlyacceptfriendpm <=", value, "onlyacceptfriendpm");
            return (Criteria) this;
        }

        public Criteria andOnlyacceptfriendpmIn(List<Boolean> values) {
            addCriterion("onlyacceptfriendpm in", values, "onlyacceptfriendpm");
            return (Criteria) this;
        }

        public Criteria andOnlyacceptfriendpmNotIn(List<Boolean> values) {
            addCriterion("onlyacceptfriendpm not in", values, "onlyacceptfriendpm");
            return (Criteria) this;
        }

        public Criteria andOnlyacceptfriendpmBetween(Boolean value1, Boolean value2) {
            addCriterion("onlyacceptfriendpm between", value1, value2, "onlyacceptfriendpm");
            return (Criteria) this;
        }

        public Criteria andOnlyacceptfriendpmNotBetween(Boolean value1, Boolean value2) {
            addCriterion("onlyacceptfriendpm not between", value1, value2, "onlyacceptfriendpm");
            return (Criteria) this;
        }

        public Criteria andConisbindIsNull() {
            addCriterion("conisbind is null");
            return (Criteria) this;
        }

        public Criteria andConisbindIsNotNull() {
            addCriterion("conisbind is not null");
            return (Criteria) this;
        }

        public Criteria andConisbindEqualTo(Boolean value) {
            addCriterion("conisbind =", value, "conisbind");
            return (Criteria) this;
        }

        public Criteria andConisbindNotEqualTo(Boolean value) {
            addCriterion("conisbind <>", value, "conisbind");
            return (Criteria) this;
        }

        public Criteria andConisbindGreaterThan(Boolean value) {
            addCriterion("conisbind >", value, "conisbind");
            return (Criteria) this;
        }

        public Criteria andConisbindGreaterThanOrEqualTo(Boolean value) {
            addCriterion("conisbind >=", value, "conisbind");
            return (Criteria) this;
        }

        public Criteria andConisbindLessThan(Boolean value) {
            addCriterion("conisbind <", value, "conisbind");
            return (Criteria) this;
        }

        public Criteria andConisbindLessThanOrEqualTo(Boolean value) {
            addCriterion("conisbind <=", value, "conisbind");
            return (Criteria) this;
        }

        public Criteria andConisbindIn(List<Boolean> values) {
            addCriterion("conisbind in", values, "conisbind");
            return (Criteria) this;
        }

        public Criteria andConisbindNotIn(List<Boolean> values) {
            addCriterion("conisbind not in", values, "conisbind");
            return (Criteria) this;
        }

        public Criteria andConisbindBetween(Boolean value1, Boolean value2) {
            addCriterion("conisbind between", value1, value2, "conisbind");
            return (Criteria) this;
        }

        public Criteria andConisbindNotBetween(Boolean value1, Boolean value2) {
            addCriterion("conisbind not between", value1, value2, "conisbind");
            return (Criteria) this;
        }

        public Criteria andFreezeIsNull() {
            addCriterion("freeze is null");
            return (Criteria) this;
        }

        public Criteria andFreezeIsNotNull() {
            addCriterion("freeze is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeEqualTo(Boolean value) {
            addCriterion("freeze =", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeNotEqualTo(Boolean value) {
            addCriterion("freeze <>", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeGreaterThan(Boolean value) {
            addCriterion("freeze >", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("freeze >=", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeLessThan(Boolean value) {
            addCriterion("freeze <", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeLessThanOrEqualTo(Boolean value) {
            addCriterion("freeze <=", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeIn(List<Boolean> values) {
            addCriterion("freeze in", values, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeNotIn(List<Boolean> values) {
            addCriterion("freeze not in", values, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeBetween(Boolean value1, Boolean value2) {
            addCriterion("freeze between", value1, value2, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("freeze not between", value1, value2, "freeze");
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