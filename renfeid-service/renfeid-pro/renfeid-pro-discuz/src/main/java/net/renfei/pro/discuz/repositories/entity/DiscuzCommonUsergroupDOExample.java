package net.renfei.pro.discuz.repositories.entity;

import java.util.ArrayList;
import java.util.List;

public class DiscuzCommonUsergroupDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DiscuzCommonUsergroupDOExample() {
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

        public Criteria andRadminidIsNull() {
            addCriterion("radminid is null");
            return (Criteria) this;
        }

        public Criteria andRadminidIsNotNull() {
            addCriterion("radminid is not null");
            return (Criteria) this;
        }

        public Criteria andRadminidEqualTo(Byte value) {
            addCriterion("radminid =", value, "radminid");
            return (Criteria) this;
        }

        public Criteria andRadminidNotEqualTo(Byte value) {
            addCriterion("radminid <>", value, "radminid");
            return (Criteria) this;
        }

        public Criteria andRadminidGreaterThan(Byte value) {
            addCriterion("radminid >", value, "radminid");
            return (Criteria) this;
        }

        public Criteria andRadminidGreaterThanOrEqualTo(Byte value) {
            addCriterion("radminid >=", value, "radminid");
            return (Criteria) this;
        }

        public Criteria andRadminidLessThan(Byte value) {
            addCriterion("radminid <", value, "radminid");
            return (Criteria) this;
        }

        public Criteria andRadminidLessThanOrEqualTo(Byte value) {
            addCriterion("radminid <=", value, "radminid");
            return (Criteria) this;
        }

        public Criteria andRadminidIn(List<Byte> values) {
            addCriterion("radminid in", values, "radminid");
            return (Criteria) this;
        }

        public Criteria andRadminidNotIn(List<Byte> values) {
            addCriterion("radminid not in", values, "radminid");
            return (Criteria) this;
        }

        public Criteria andRadminidBetween(Byte value1, Byte value2) {
            addCriterion("radminid between", value1, value2, "radminid");
            return (Criteria) this;
        }

        public Criteria andRadminidNotBetween(Byte value1, Byte value2) {
            addCriterion("radminid not between", value1, value2, "radminid");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andSystemIsNull() {
            addCriterion("system is null");
            return (Criteria) this;
        }

        public Criteria andSystemIsNotNull() {
            addCriterion("system is not null");
            return (Criteria) this;
        }

        public Criteria andSystemEqualTo(String value) {
            addCriterion("system =", value, "system");
            return (Criteria) this;
        }

        public Criteria andSystemNotEqualTo(String value) {
            addCriterion("system <>", value, "system");
            return (Criteria) this;
        }

        public Criteria andSystemGreaterThan(String value) {
            addCriterion("system >", value, "system");
            return (Criteria) this;
        }

        public Criteria andSystemGreaterThanOrEqualTo(String value) {
            addCriterion("system >=", value, "system");
            return (Criteria) this;
        }

        public Criteria andSystemLessThan(String value) {
            addCriterion("system <", value, "system");
            return (Criteria) this;
        }

        public Criteria andSystemLessThanOrEqualTo(String value) {
            addCriterion("system <=", value, "system");
            return (Criteria) this;
        }

        public Criteria andSystemLike(String value) {
            addCriterion("system like", value, "system");
            return (Criteria) this;
        }

        public Criteria andSystemNotLike(String value) {
            addCriterion("system not like", value, "system");
            return (Criteria) this;
        }

        public Criteria andSystemIn(List<String> values) {
            addCriterion("system in", values, "system");
            return (Criteria) this;
        }

        public Criteria andSystemNotIn(List<String> values) {
            addCriterion("system not in", values, "system");
            return (Criteria) this;
        }

        public Criteria andSystemBetween(String value1, String value2) {
            addCriterion("system between", value1, value2, "system");
            return (Criteria) this;
        }

        public Criteria andSystemNotBetween(String value1, String value2) {
            addCriterion("system not between", value1, value2, "system");
            return (Criteria) this;
        }

        public Criteria andGrouptitleIsNull() {
            addCriterion("grouptitle is null");
            return (Criteria) this;
        }

        public Criteria andGrouptitleIsNotNull() {
            addCriterion("grouptitle is not null");
            return (Criteria) this;
        }

        public Criteria andGrouptitleEqualTo(String value) {
            addCriterion("grouptitle =", value, "grouptitle");
            return (Criteria) this;
        }

        public Criteria andGrouptitleNotEqualTo(String value) {
            addCriterion("grouptitle <>", value, "grouptitle");
            return (Criteria) this;
        }

        public Criteria andGrouptitleGreaterThan(String value) {
            addCriterion("grouptitle >", value, "grouptitle");
            return (Criteria) this;
        }

        public Criteria andGrouptitleGreaterThanOrEqualTo(String value) {
            addCriterion("grouptitle >=", value, "grouptitle");
            return (Criteria) this;
        }

        public Criteria andGrouptitleLessThan(String value) {
            addCriterion("grouptitle <", value, "grouptitle");
            return (Criteria) this;
        }

        public Criteria andGrouptitleLessThanOrEqualTo(String value) {
            addCriterion("grouptitle <=", value, "grouptitle");
            return (Criteria) this;
        }

        public Criteria andGrouptitleLike(String value) {
            addCriterion("grouptitle like", value, "grouptitle");
            return (Criteria) this;
        }

        public Criteria andGrouptitleNotLike(String value) {
            addCriterion("grouptitle not like", value, "grouptitle");
            return (Criteria) this;
        }

        public Criteria andGrouptitleIn(List<String> values) {
            addCriterion("grouptitle in", values, "grouptitle");
            return (Criteria) this;
        }

        public Criteria andGrouptitleNotIn(List<String> values) {
            addCriterion("grouptitle not in", values, "grouptitle");
            return (Criteria) this;
        }

        public Criteria andGrouptitleBetween(String value1, String value2) {
            addCriterion("grouptitle between", value1, value2, "grouptitle");
            return (Criteria) this;
        }

        public Criteria andGrouptitleNotBetween(String value1, String value2) {
            addCriterion("grouptitle not between", value1, value2, "grouptitle");
            return (Criteria) this;
        }

        public Criteria andCreditshigherIsNull() {
            addCriterion("creditshigher is null");
            return (Criteria) this;
        }

        public Criteria andCreditshigherIsNotNull() {
            addCriterion("creditshigher is not null");
            return (Criteria) this;
        }

        public Criteria andCreditshigherEqualTo(Integer value) {
            addCriterion("creditshigher =", value, "creditshigher");
            return (Criteria) this;
        }

        public Criteria andCreditshigherNotEqualTo(Integer value) {
            addCriterion("creditshigher <>", value, "creditshigher");
            return (Criteria) this;
        }

        public Criteria andCreditshigherGreaterThan(Integer value) {
            addCriterion("creditshigher >", value, "creditshigher");
            return (Criteria) this;
        }

        public Criteria andCreditshigherGreaterThanOrEqualTo(Integer value) {
            addCriterion("creditshigher >=", value, "creditshigher");
            return (Criteria) this;
        }

        public Criteria andCreditshigherLessThan(Integer value) {
            addCriterion("creditshigher <", value, "creditshigher");
            return (Criteria) this;
        }

        public Criteria andCreditshigherLessThanOrEqualTo(Integer value) {
            addCriterion("creditshigher <=", value, "creditshigher");
            return (Criteria) this;
        }

        public Criteria andCreditshigherIn(List<Integer> values) {
            addCriterion("creditshigher in", values, "creditshigher");
            return (Criteria) this;
        }

        public Criteria andCreditshigherNotIn(List<Integer> values) {
            addCriterion("creditshigher not in", values, "creditshigher");
            return (Criteria) this;
        }

        public Criteria andCreditshigherBetween(Integer value1, Integer value2) {
            addCriterion("creditshigher between", value1, value2, "creditshigher");
            return (Criteria) this;
        }

        public Criteria andCreditshigherNotBetween(Integer value1, Integer value2) {
            addCriterion("creditshigher not between", value1, value2, "creditshigher");
            return (Criteria) this;
        }

        public Criteria andCreditslowerIsNull() {
            addCriterion("creditslower is null");
            return (Criteria) this;
        }

        public Criteria andCreditslowerIsNotNull() {
            addCriterion("creditslower is not null");
            return (Criteria) this;
        }

        public Criteria andCreditslowerEqualTo(Integer value) {
            addCriterion("creditslower =", value, "creditslower");
            return (Criteria) this;
        }

        public Criteria andCreditslowerNotEqualTo(Integer value) {
            addCriterion("creditslower <>", value, "creditslower");
            return (Criteria) this;
        }

        public Criteria andCreditslowerGreaterThan(Integer value) {
            addCriterion("creditslower >", value, "creditslower");
            return (Criteria) this;
        }

        public Criteria andCreditslowerGreaterThanOrEqualTo(Integer value) {
            addCriterion("creditslower >=", value, "creditslower");
            return (Criteria) this;
        }

        public Criteria andCreditslowerLessThan(Integer value) {
            addCriterion("creditslower <", value, "creditslower");
            return (Criteria) this;
        }

        public Criteria andCreditslowerLessThanOrEqualTo(Integer value) {
            addCriterion("creditslower <=", value, "creditslower");
            return (Criteria) this;
        }

        public Criteria andCreditslowerIn(List<Integer> values) {
            addCriterion("creditslower in", values, "creditslower");
            return (Criteria) this;
        }

        public Criteria andCreditslowerNotIn(List<Integer> values) {
            addCriterion("creditslower not in", values, "creditslower");
            return (Criteria) this;
        }

        public Criteria andCreditslowerBetween(Integer value1, Integer value2) {
            addCriterion("creditslower between", value1, value2, "creditslower");
            return (Criteria) this;
        }

        public Criteria andCreditslowerNotBetween(Integer value1, Integer value2) {
            addCriterion("creditslower not between", value1, value2, "creditslower");
            return (Criteria) this;
        }

        public Criteria andStarsIsNull() {
            addCriterion("stars is null");
            return (Criteria) this;
        }

        public Criteria andStarsIsNotNull() {
            addCriterion("stars is not null");
            return (Criteria) this;
        }

        public Criteria andStarsEqualTo(Byte value) {
            addCriterion("stars =", value, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsNotEqualTo(Byte value) {
            addCriterion("stars <>", value, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsGreaterThan(Byte value) {
            addCriterion("stars >", value, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsGreaterThanOrEqualTo(Byte value) {
            addCriterion("stars >=", value, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsLessThan(Byte value) {
            addCriterion("stars <", value, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsLessThanOrEqualTo(Byte value) {
            addCriterion("stars <=", value, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsIn(List<Byte> values) {
            addCriterion("stars in", values, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsNotIn(List<Byte> values) {
            addCriterion("stars not in", values, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsBetween(Byte value1, Byte value2) {
            addCriterion("stars between", value1, value2, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsNotBetween(Byte value1, Byte value2) {
            addCriterion("stars not between", value1, value2, "stars");
            return (Criteria) this;
        }

        public Criteria andColorIsNull() {
            addCriterion("color is null");
            return (Criteria) this;
        }

        public Criteria andColorIsNotNull() {
            addCriterion("color is not null");
            return (Criteria) this;
        }

        public Criteria andColorEqualTo(String value) {
            addCriterion("color =", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotEqualTo(String value) {
            addCriterion("color <>", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThan(String value) {
            addCriterion("color >", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThanOrEqualTo(String value) {
            addCriterion("color >=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThan(String value) {
            addCriterion("color <", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThanOrEqualTo(String value) {
            addCriterion("color <=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLike(String value) {
            addCriterion("color like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotLike(String value) {
            addCriterion("color not like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorIn(List<String> values) {
            addCriterion("color in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotIn(List<String> values) {
            addCriterion("color not in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorBetween(String value1, String value2) {
            addCriterion("color between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotBetween(String value1, String value2) {
            addCriterion("color not between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andIconIsNull() {
            addCriterion("icon is null");
            return (Criteria) this;
        }

        public Criteria andIconIsNotNull() {
            addCriterion("icon is not null");
            return (Criteria) this;
        }

        public Criteria andIconEqualTo(String value) {
            addCriterion("icon =", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotEqualTo(String value) {
            addCriterion("icon <>", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThan(String value) {
            addCriterion("icon >", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThanOrEqualTo(String value) {
            addCriterion("icon >=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThan(String value) {
            addCriterion("icon <", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThanOrEqualTo(String value) {
            addCriterion("icon <=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLike(String value) {
            addCriterion("icon like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotLike(String value) {
            addCriterion("icon not like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconIn(List<String> values) {
            addCriterion("icon in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotIn(List<String> values) {
            addCriterion("icon not in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconBetween(String value1, String value2) {
            addCriterion("icon between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotBetween(String value1, String value2) {
            addCriterion("icon not between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andAllowvisitIsNull() {
            addCriterion("allowvisit is null");
            return (Criteria) this;
        }

        public Criteria andAllowvisitIsNotNull() {
            addCriterion("allowvisit is not null");
            return (Criteria) this;
        }

        public Criteria andAllowvisitEqualTo(Boolean value) {
            addCriterion("allowvisit =", value, "allowvisit");
            return (Criteria) this;
        }

        public Criteria andAllowvisitNotEqualTo(Boolean value) {
            addCriterion("allowvisit <>", value, "allowvisit");
            return (Criteria) this;
        }

        public Criteria andAllowvisitGreaterThan(Boolean value) {
            addCriterion("allowvisit >", value, "allowvisit");
            return (Criteria) this;
        }

        public Criteria andAllowvisitGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allowvisit >=", value, "allowvisit");
            return (Criteria) this;
        }

        public Criteria andAllowvisitLessThan(Boolean value) {
            addCriterion("allowvisit <", value, "allowvisit");
            return (Criteria) this;
        }

        public Criteria andAllowvisitLessThanOrEqualTo(Boolean value) {
            addCriterion("allowvisit <=", value, "allowvisit");
            return (Criteria) this;
        }

        public Criteria andAllowvisitIn(List<Boolean> values) {
            addCriterion("allowvisit in", values, "allowvisit");
            return (Criteria) this;
        }

        public Criteria andAllowvisitNotIn(List<Boolean> values) {
            addCriterion("allowvisit not in", values, "allowvisit");
            return (Criteria) this;
        }

        public Criteria andAllowvisitBetween(Boolean value1, Boolean value2) {
            addCriterion("allowvisit between", value1, value2, "allowvisit");
            return (Criteria) this;
        }

        public Criteria andAllowvisitNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allowvisit not between", value1, value2, "allowvisit");
            return (Criteria) this;
        }

        public Criteria andAllowsendpmIsNull() {
            addCriterion("allowsendpm is null");
            return (Criteria) this;
        }

        public Criteria andAllowsendpmIsNotNull() {
            addCriterion("allowsendpm is not null");
            return (Criteria) this;
        }

        public Criteria andAllowsendpmEqualTo(Boolean value) {
            addCriterion("allowsendpm =", value, "allowsendpm");
            return (Criteria) this;
        }

        public Criteria andAllowsendpmNotEqualTo(Boolean value) {
            addCriterion("allowsendpm <>", value, "allowsendpm");
            return (Criteria) this;
        }

        public Criteria andAllowsendpmGreaterThan(Boolean value) {
            addCriterion("allowsendpm >", value, "allowsendpm");
            return (Criteria) this;
        }

        public Criteria andAllowsendpmGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allowsendpm >=", value, "allowsendpm");
            return (Criteria) this;
        }

        public Criteria andAllowsendpmLessThan(Boolean value) {
            addCriterion("allowsendpm <", value, "allowsendpm");
            return (Criteria) this;
        }

        public Criteria andAllowsendpmLessThanOrEqualTo(Boolean value) {
            addCriterion("allowsendpm <=", value, "allowsendpm");
            return (Criteria) this;
        }

        public Criteria andAllowsendpmIn(List<Boolean> values) {
            addCriterion("allowsendpm in", values, "allowsendpm");
            return (Criteria) this;
        }

        public Criteria andAllowsendpmNotIn(List<Boolean> values) {
            addCriterion("allowsendpm not in", values, "allowsendpm");
            return (Criteria) this;
        }

        public Criteria andAllowsendpmBetween(Boolean value1, Boolean value2) {
            addCriterion("allowsendpm between", value1, value2, "allowsendpm");
            return (Criteria) this;
        }

        public Criteria andAllowsendpmNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allowsendpm not between", value1, value2, "allowsendpm");
            return (Criteria) this;
        }

        public Criteria andAllowinviteIsNull() {
            addCriterion("allowinvite is null");
            return (Criteria) this;
        }

        public Criteria andAllowinviteIsNotNull() {
            addCriterion("allowinvite is not null");
            return (Criteria) this;
        }

        public Criteria andAllowinviteEqualTo(Boolean value) {
            addCriterion("allowinvite =", value, "allowinvite");
            return (Criteria) this;
        }

        public Criteria andAllowinviteNotEqualTo(Boolean value) {
            addCriterion("allowinvite <>", value, "allowinvite");
            return (Criteria) this;
        }

        public Criteria andAllowinviteGreaterThan(Boolean value) {
            addCriterion("allowinvite >", value, "allowinvite");
            return (Criteria) this;
        }

        public Criteria andAllowinviteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allowinvite >=", value, "allowinvite");
            return (Criteria) this;
        }

        public Criteria andAllowinviteLessThan(Boolean value) {
            addCriterion("allowinvite <", value, "allowinvite");
            return (Criteria) this;
        }

        public Criteria andAllowinviteLessThanOrEqualTo(Boolean value) {
            addCriterion("allowinvite <=", value, "allowinvite");
            return (Criteria) this;
        }

        public Criteria andAllowinviteIn(List<Boolean> values) {
            addCriterion("allowinvite in", values, "allowinvite");
            return (Criteria) this;
        }

        public Criteria andAllowinviteNotIn(List<Boolean> values) {
            addCriterion("allowinvite not in", values, "allowinvite");
            return (Criteria) this;
        }

        public Criteria andAllowinviteBetween(Boolean value1, Boolean value2) {
            addCriterion("allowinvite between", value1, value2, "allowinvite");
            return (Criteria) this;
        }

        public Criteria andAllowinviteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allowinvite not between", value1, value2, "allowinvite");
            return (Criteria) this;
        }

        public Criteria andAllowmailinviteIsNull() {
            addCriterion("allowmailinvite is null");
            return (Criteria) this;
        }

        public Criteria andAllowmailinviteIsNotNull() {
            addCriterion("allowmailinvite is not null");
            return (Criteria) this;
        }

        public Criteria andAllowmailinviteEqualTo(Boolean value) {
            addCriterion("allowmailinvite =", value, "allowmailinvite");
            return (Criteria) this;
        }

        public Criteria andAllowmailinviteNotEqualTo(Boolean value) {
            addCriterion("allowmailinvite <>", value, "allowmailinvite");
            return (Criteria) this;
        }

        public Criteria andAllowmailinviteGreaterThan(Boolean value) {
            addCriterion("allowmailinvite >", value, "allowmailinvite");
            return (Criteria) this;
        }

        public Criteria andAllowmailinviteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allowmailinvite >=", value, "allowmailinvite");
            return (Criteria) this;
        }

        public Criteria andAllowmailinviteLessThan(Boolean value) {
            addCriterion("allowmailinvite <", value, "allowmailinvite");
            return (Criteria) this;
        }

        public Criteria andAllowmailinviteLessThanOrEqualTo(Boolean value) {
            addCriterion("allowmailinvite <=", value, "allowmailinvite");
            return (Criteria) this;
        }

        public Criteria andAllowmailinviteIn(List<Boolean> values) {
            addCriterion("allowmailinvite in", values, "allowmailinvite");
            return (Criteria) this;
        }

        public Criteria andAllowmailinviteNotIn(List<Boolean> values) {
            addCriterion("allowmailinvite not in", values, "allowmailinvite");
            return (Criteria) this;
        }

        public Criteria andAllowmailinviteBetween(Boolean value1, Boolean value2) {
            addCriterion("allowmailinvite between", value1, value2, "allowmailinvite");
            return (Criteria) this;
        }

        public Criteria andAllowmailinviteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allowmailinvite not between", value1, value2, "allowmailinvite");
            return (Criteria) this;
        }

        public Criteria andMaxinvitenumIsNull() {
            addCriterion("maxinvitenum is null");
            return (Criteria) this;
        }

        public Criteria andMaxinvitenumIsNotNull() {
            addCriterion("maxinvitenum is not null");
            return (Criteria) this;
        }

        public Criteria andMaxinvitenumEqualTo(Byte value) {
            addCriterion("maxinvitenum =", value, "maxinvitenum");
            return (Criteria) this;
        }

        public Criteria andMaxinvitenumNotEqualTo(Byte value) {
            addCriterion("maxinvitenum <>", value, "maxinvitenum");
            return (Criteria) this;
        }

        public Criteria andMaxinvitenumGreaterThan(Byte value) {
            addCriterion("maxinvitenum >", value, "maxinvitenum");
            return (Criteria) this;
        }

        public Criteria andMaxinvitenumGreaterThanOrEqualTo(Byte value) {
            addCriterion("maxinvitenum >=", value, "maxinvitenum");
            return (Criteria) this;
        }

        public Criteria andMaxinvitenumLessThan(Byte value) {
            addCriterion("maxinvitenum <", value, "maxinvitenum");
            return (Criteria) this;
        }

        public Criteria andMaxinvitenumLessThanOrEqualTo(Byte value) {
            addCriterion("maxinvitenum <=", value, "maxinvitenum");
            return (Criteria) this;
        }

        public Criteria andMaxinvitenumIn(List<Byte> values) {
            addCriterion("maxinvitenum in", values, "maxinvitenum");
            return (Criteria) this;
        }

        public Criteria andMaxinvitenumNotIn(List<Byte> values) {
            addCriterion("maxinvitenum not in", values, "maxinvitenum");
            return (Criteria) this;
        }

        public Criteria andMaxinvitenumBetween(Byte value1, Byte value2) {
            addCriterion("maxinvitenum between", value1, value2, "maxinvitenum");
            return (Criteria) this;
        }

        public Criteria andMaxinvitenumNotBetween(Byte value1, Byte value2) {
            addCriterion("maxinvitenum not between", value1, value2, "maxinvitenum");
            return (Criteria) this;
        }

        public Criteria andInvitepriceIsNull() {
            addCriterion("inviteprice is null");
            return (Criteria) this;
        }

        public Criteria andInvitepriceIsNotNull() {
            addCriterion("inviteprice is not null");
            return (Criteria) this;
        }

        public Criteria andInvitepriceEqualTo(Short value) {
            addCriterion("inviteprice =", value, "inviteprice");
            return (Criteria) this;
        }

        public Criteria andInvitepriceNotEqualTo(Short value) {
            addCriterion("inviteprice <>", value, "inviteprice");
            return (Criteria) this;
        }

        public Criteria andInvitepriceGreaterThan(Short value) {
            addCriterion("inviteprice >", value, "inviteprice");
            return (Criteria) this;
        }

        public Criteria andInvitepriceGreaterThanOrEqualTo(Short value) {
            addCriterion("inviteprice >=", value, "inviteprice");
            return (Criteria) this;
        }

        public Criteria andInvitepriceLessThan(Short value) {
            addCriterion("inviteprice <", value, "inviteprice");
            return (Criteria) this;
        }

        public Criteria andInvitepriceLessThanOrEqualTo(Short value) {
            addCriterion("inviteprice <=", value, "inviteprice");
            return (Criteria) this;
        }

        public Criteria andInvitepriceIn(List<Short> values) {
            addCriterion("inviteprice in", values, "inviteprice");
            return (Criteria) this;
        }

        public Criteria andInvitepriceNotIn(List<Short> values) {
            addCriterion("inviteprice not in", values, "inviteprice");
            return (Criteria) this;
        }

        public Criteria andInvitepriceBetween(Short value1, Short value2) {
            addCriterion("inviteprice between", value1, value2, "inviteprice");
            return (Criteria) this;
        }

        public Criteria andInvitepriceNotBetween(Short value1, Short value2) {
            addCriterion("inviteprice not between", value1, value2, "inviteprice");
            return (Criteria) this;
        }

        public Criteria andMaxinvitedayIsNull() {
            addCriterion("maxinviteday is null");
            return (Criteria) this;
        }

        public Criteria andMaxinvitedayIsNotNull() {
            addCriterion("maxinviteday is not null");
            return (Criteria) this;
        }

        public Criteria andMaxinvitedayEqualTo(Short value) {
            addCriterion("maxinviteday =", value, "maxinviteday");
            return (Criteria) this;
        }

        public Criteria andMaxinvitedayNotEqualTo(Short value) {
            addCriterion("maxinviteday <>", value, "maxinviteday");
            return (Criteria) this;
        }

        public Criteria andMaxinvitedayGreaterThan(Short value) {
            addCriterion("maxinviteday >", value, "maxinviteday");
            return (Criteria) this;
        }

        public Criteria andMaxinvitedayGreaterThanOrEqualTo(Short value) {
            addCriterion("maxinviteday >=", value, "maxinviteday");
            return (Criteria) this;
        }

        public Criteria andMaxinvitedayLessThan(Short value) {
            addCriterion("maxinviteday <", value, "maxinviteday");
            return (Criteria) this;
        }

        public Criteria andMaxinvitedayLessThanOrEqualTo(Short value) {
            addCriterion("maxinviteday <=", value, "maxinviteday");
            return (Criteria) this;
        }

        public Criteria andMaxinvitedayIn(List<Short> values) {
            addCriterion("maxinviteday in", values, "maxinviteday");
            return (Criteria) this;
        }

        public Criteria andMaxinvitedayNotIn(List<Short> values) {
            addCriterion("maxinviteday not in", values, "maxinviteday");
            return (Criteria) this;
        }

        public Criteria andMaxinvitedayBetween(Short value1, Short value2) {
            addCriterion("maxinviteday between", value1, value2, "maxinviteday");
            return (Criteria) this;
        }

        public Criteria andMaxinvitedayNotBetween(Short value1, Short value2) {
            addCriterion("maxinviteday not between", value1, value2, "maxinviteday");
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