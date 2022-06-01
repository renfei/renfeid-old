package net.renfei.uaa.repositories.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UaaMenuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UaaMenuExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Long value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Long value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Long value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Long value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Long value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Long value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Long> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Long> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Long value1, Long value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Long value1, Long value2) {
            addCriterion("pid not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andMenuNameIsNull() {
            addCriterion("menu_name is null");
            return (Criteria) this;
        }

        public Criteria andMenuNameIsNotNull() {
            addCriterion("menu_name is not null");
            return (Criteria) this;
        }

        public Criteria andMenuNameEqualTo(String value) {
            addCriterion("menu_name =", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotEqualTo(String value) {
            addCriterion("menu_name <>", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThan(String value) {
            addCriterion("menu_name >", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThanOrEqualTo(String value) {
            addCriterion("menu_name >=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThan(String value) {
            addCriterion("menu_name <", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThanOrEqualTo(String value) {
            addCriterion("menu_name <=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLike(String value) {
            addCriterion("menu_name like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotLike(String value) {
            addCriterion("menu_name not like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameIn(List<String> values) {
            addCriterion("menu_name in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotIn(List<String> values) {
            addCriterion("menu_name not in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameBetween(String value1, String value2) {
            addCriterion("menu_name between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotBetween(String value1, String value2) {
            addCriterion("menu_name not between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuIconIsNull() {
            addCriterion("menu_icon is null");
            return (Criteria) this;
        }

        public Criteria andMenuIconIsNotNull() {
            addCriterion("menu_icon is not null");
            return (Criteria) this;
        }

        public Criteria andMenuIconEqualTo(String value) {
            addCriterion("menu_icon =", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconNotEqualTo(String value) {
            addCriterion("menu_icon <>", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconGreaterThan(String value) {
            addCriterion("menu_icon >", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconGreaterThanOrEqualTo(String value) {
            addCriterion("menu_icon >=", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconLessThan(String value) {
            addCriterion("menu_icon <", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconLessThanOrEqualTo(String value) {
            addCriterion("menu_icon <=", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconLike(String value) {
            addCriterion("menu_icon like", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconNotLike(String value) {
            addCriterion("menu_icon not like", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconIn(List<String> values) {
            addCriterion("menu_icon in", values, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconNotIn(List<String> values) {
            addCriterion("menu_icon not in", values, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconBetween(String value1, String value2) {
            addCriterion("menu_icon between", value1, value2, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconNotBetween(String value1, String value2) {
            addCriterion("menu_icon not between", value1, value2, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuTargetIsNull() {
            addCriterion("menu_target is null");
            return (Criteria) this;
        }

        public Criteria andMenuTargetIsNotNull() {
            addCriterion("menu_target is not null");
            return (Criteria) this;
        }

        public Criteria andMenuTargetEqualTo(String value) {
            addCriterion("menu_target =", value, "menuTarget");
            return (Criteria) this;
        }

        public Criteria andMenuTargetNotEqualTo(String value) {
            addCriterion("menu_target <>", value, "menuTarget");
            return (Criteria) this;
        }

        public Criteria andMenuTargetGreaterThan(String value) {
            addCriterion("menu_target >", value, "menuTarget");
            return (Criteria) this;
        }

        public Criteria andMenuTargetGreaterThanOrEqualTo(String value) {
            addCriterion("menu_target >=", value, "menuTarget");
            return (Criteria) this;
        }

        public Criteria andMenuTargetLessThan(String value) {
            addCriterion("menu_target <", value, "menuTarget");
            return (Criteria) this;
        }

        public Criteria andMenuTargetLessThanOrEqualTo(String value) {
            addCriterion("menu_target <=", value, "menuTarget");
            return (Criteria) this;
        }

        public Criteria andMenuTargetLike(String value) {
            addCriterion("menu_target like", value, "menuTarget");
            return (Criteria) this;
        }

        public Criteria andMenuTargetNotLike(String value) {
            addCriterion("menu_target not like", value, "menuTarget");
            return (Criteria) this;
        }

        public Criteria andMenuTargetIn(List<String> values) {
            addCriterion("menu_target in", values, "menuTarget");
            return (Criteria) this;
        }

        public Criteria andMenuTargetNotIn(List<String> values) {
            addCriterion("menu_target not in", values, "menuTarget");
            return (Criteria) this;
        }

        public Criteria andMenuTargetBetween(String value1, String value2) {
            addCriterion("menu_target between", value1, value2, "menuTarget");
            return (Criteria) this;
        }

        public Criteria andMenuTargetNotBetween(String value1, String value2) {
            addCriterion("menu_target not between", value1, value2, "menuTarget");
            return (Criteria) this;
        }

        public Criteria andMenuClassIsNull() {
            addCriterion("menu_class is null");
            return (Criteria) this;
        }

        public Criteria andMenuClassIsNotNull() {
            addCriterion("menu_class is not null");
            return (Criteria) this;
        }

        public Criteria andMenuClassEqualTo(String value) {
            addCriterion("menu_class =", value, "menuClass");
            return (Criteria) this;
        }

        public Criteria andMenuClassNotEqualTo(String value) {
            addCriterion("menu_class <>", value, "menuClass");
            return (Criteria) this;
        }

        public Criteria andMenuClassGreaterThan(String value) {
            addCriterion("menu_class >", value, "menuClass");
            return (Criteria) this;
        }

        public Criteria andMenuClassGreaterThanOrEqualTo(String value) {
            addCriterion("menu_class >=", value, "menuClass");
            return (Criteria) this;
        }

        public Criteria andMenuClassLessThan(String value) {
            addCriterion("menu_class <", value, "menuClass");
            return (Criteria) this;
        }

        public Criteria andMenuClassLessThanOrEqualTo(String value) {
            addCriterion("menu_class <=", value, "menuClass");
            return (Criteria) this;
        }

        public Criteria andMenuClassLike(String value) {
            addCriterion("menu_class like", value, "menuClass");
            return (Criteria) this;
        }

        public Criteria andMenuClassNotLike(String value) {
            addCriterion("menu_class not like", value, "menuClass");
            return (Criteria) this;
        }

        public Criteria andMenuClassIn(List<String> values) {
            addCriterion("menu_class in", values, "menuClass");
            return (Criteria) this;
        }

        public Criteria andMenuClassNotIn(List<String> values) {
            addCriterion("menu_class not in", values, "menuClass");
            return (Criteria) this;
        }

        public Criteria andMenuClassBetween(String value1, String value2) {
            addCriterion("menu_class between", value1, value2, "menuClass");
            return (Criteria) this;
        }

        public Criteria andMenuClassNotBetween(String value1, String value2) {
            addCriterion("menu_class not between", value1, value2, "menuClass");
            return (Criteria) this;
        }

        public Criteria andMenuTitleIsNull() {
            addCriterion("menu_title is null");
            return (Criteria) this;
        }

        public Criteria andMenuTitleIsNotNull() {
            addCriterion("menu_title is not null");
            return (Criteria) this;
        }

        public Criteria andMenuTitleEqualTo(String value) {
            addCriterion("menu_title =", value, "menuTitle");
            return (Criteria) this;
        }

        public Criteria andMenuTitleNotEqualTo(String value) {
            addCriterion("menu_title <>", value, "menuTitle");
            return (Criteria) this;
        }

        public Criteria andMenuTitleGreaterThan(String value) {
            addCriterion("menu_title >", value, "menuTitle");
            return (Criteria) this;
        }

        public Criteria andMenuTitleGreaterThanOrEqualTo(String value) {
            addCriterion("menu_title >=", value, "menuTitle");
            return (Criteria) this;
        }

        public Criteria andMenuTitleLessThan(String value) {
            addCriterion("menu_title <", value, "menuTitle");
            return (Criteria) this;
        }

        public Criteria andMenuTitleLessThanOrEqualTo(String value) {
            addCriterion("menu_title <=", value, "menuTitle");
            return (Criteria) this;
        }

        public Criteria andMenuTitleLike(String value) {
            addCriterion("menu_title like", value, "menuTitle");
            return (Criteria) this;
        }

        public Criteria andMenuTitleNotLike(String value) {
            addCriterion("menu_title not like", value, "menuTitle");
            return (Criteria) this;
        }

        public Criteria andMenuTitleIn(List<String> values) {
            addCriterion("menu_title in", values, "menuTitle");
            return (Criteria) this;
        }

        public Criteria andMenuTitleNotIn(List<String> values) {
            addCriterion("menu_title not in", values, "menuTitle");
            return (Criteria) this;
        }

        public Criteria andMenuTitleBetween(String value1, String value2) {
            addCriterion("menu_title between", value1, value2, "menuTitle");
            return (Criteria) this;
        }

        public Criteria andMenuTitleNotBetween(String value1, String value2) {
            addCriterion("menu_title not between", value1, value2, "menuTitle");
            return (Criteria) this;
        }

        public Criteria andMenuOnclickIsNull() {
            addCriterion("menu_onclick is null");
            return (Criteria) this;
        }

        public Criteria andMenuOnclickIsNotNull() {
            addCriterion("menu_onclick is not null");
            return (Criteria) this;
        }

        public Criteria andMenuOnclickEqualTo(String value) {
            addCriterion("menu_onclick =", value, "menuOnclick");
            return (Criteria) this;
        }

        public Criteria andMenuOnclickNotEqualTo(String value) {
            addCriterion("menu_onclick <>", value, "menuOnclick");
            return (Criteria) this;
        }

        public Criteria andMenuOnclickGreaterThan(String value) {
            addCriterion("menu_onclick >", value, "menuOnclick");
            return (Criteria) this;
        }

        public Criteria andMenuOnclickGreaterThanOrEqualTo(String value) {
            addCriterion("menu_onclick >=", value, "menuOnclick");
            return (Criteria) this;
        }

        public Criteria andMenuOnclickLessThan(String value) {
            addCriterion("menu_onclick <", value, "menuOnclick");
            return (Criteria) this;
        }

        public Criteria andMenuOnclickLessThanOrEqualTo(String value) {
            addCriterion("menu_onclick <=", value, "menuOnclick");
            return (Criteria) this;
        }

        public Criteria andMenuOnclickLike(String value) {
            addCriterion("menu_onclick like", value, "menuOnclick");
            return (Criteria) this;
        }

        public Criteria andMenuOnclickNotLike(String value) {
            addCriterion("menu_onclick not like", value, "menuOnclick");
            return (Criteria) this;
        }

        public Criteria andMenuOnclickIn(List<String> values) {
            addCriterion("menu_onclick in", values, "menuOnclick");
            return (Criteria) this;
        }

        public Criteria andMenuOnclickNotIn(List<String> values) {
            addCriterion("menu_onclick not in", values, "menuOnclick");
            return (Criteria) this;
        }

        public Criteria andMenuOnclickBetween(String value1, String value2) {
            addCriterion("menu_onclick between", value1, value2, "menuOnclick");
            return (Criteria) this;
        }

        public Criteria andMenuOnclickNotBetween(String value1, String value2) {
            addCriterion("menu_onclick not between", value1, value2, "menuOnclick");
            return (Criteria) this;
        }

        public Criteria andMenuOrderIsNull() {
            addCriterion("menu_order is null");
            return (Criteria) this;
        }

        public Criteria andMenuOrderIsNotNull() {
            addCriterion("menu_order is not null");
            return (Criteria) this;
        }

        public Criteria andMenuOrderEqualTo(Integer value) {
            addCriterion("menu_order =", value, "menuOrder");
            return (Criteria) this;
        }

        public Criteria andMenuOrderNotEqualTo(Integer value) {
            addCriterion("menu_order <>", value, "menuOrder");
            return (Criteria) this;
        }

        public Criteria andMenuOrderGreaterThan(Integer value) {
            addCriterion("menu_order >", value, "menuOrder");
            return (Criteria) this;
        }

        public Criteria andMenuOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("menu_order >=", value, "menuOrder");
            return (Criteria) this;
        }

        public Criteria andMenuOrderLessThan(Integer value) {
            addCriterion("menu_order <", value, "menuOrder");
            return (Criteria) this;
        }

        public Criteria andMenuOrderLessThanOrEqualTo(Integer value) {
            addCriterion("menu_order <=", value, "menuOrder");
            return (Criteria) this;
        }

        public Criteria andMenuOrderIn(List<Integer> values) {
            addCriterion("menu_order in", values, "menuOrder");
            return (Criteria) this;
        }

        public Criteria andMenuOrderNotIn(List<Integer> values) {
            addCriterion("menu_order not in", values, "menuOrder");
            return (Criteria) this;
        }

        public Criteria andMenuOrderBetween(Integer value1, Integer value2) {
            addCriterion("menu_order between", value1, value2, "menuOrder");
            return (Criteria) this;
        }

        public Criteria andMenuOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("menu_order not between", value1, value2, "menuOrder");
            return (Criteria) this;
        }

        public Criteria andEnableIsNull() {
            addCriterion("enable is null");
            return (Criteria) this;
        }

        public Criteria andEnableIsNotNull() {
            addCriterion("enable is not null");
            return (Criteria) this;
        }

        public Criteria andEnableEqualTo(Boolean value) {
            addCriterion("enable =", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotEqualTo(Boolean value) {
            addCriterion("enable <>", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThan(Boolean value) {
            addCriterion("enable >", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("enable >=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThan(Boolean value) {
            addCriterion("enable <", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThanOrEqualTo(Boolean value) {
            addCriterion("enable <=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableIn(List<Boolean> values) {
            addCriterion("enable in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotIn(List<Boolean> values) {
            addCriterion("enable not in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableBetween(Boolean value1, Boolean value2) {
            addCriterion("enable between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("enable not between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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