package net.renfei.proprietary.discuz.repositories.entity;

import java.util.ArrayList;
import java.util.List;

public class DiscuzCommonMemberProfileDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DiscuzCommonMemberProfileDOExample() {
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

        public Criteria andRealnameIsNull() {
            addCriterion("realname is null");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNotNull() {
            addCriterion("realname is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameEqualTo(String value) {
            addCriterion("realname =", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotEqualTo(String value) {
            addCriterion("realname <>", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThan(String value) {
            addCriterion("realname >", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("realname >=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThan(String value) {
            addCriterion("realname <", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThanOrEqualTo(String value) {
            addCriterion("realname <=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLike(String value) {
            addCriterion("realname like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotLike(String value) {
            addCriterion("realname not like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameIn(List<String> values) {
            addCriterion("realname in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotIn(List<String> values) {
            addCriterion("realname not in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameBetween(String value1, String value2) {
            addCriterion("realname between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotBetween(String value1, String value2) {
            addCriterion("realname not between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(Boolean value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(Boolean value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(Boolean value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(Boolean value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(Boolean value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(Boolean value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<Boolean> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<Boolean> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(Boolean value1, Boolean value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(Boolean value1, Boolean value2) {
            addCriterion("gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andBirthyearIsNull() {
            addCriterion("birthyear is null");
            return (Criteria) this;
        }

        public Criteria andBirthyearIsNotNull() {
            addCriterion("birthyear is not null");
            return (Criteria) this;
        }

        public Criteria andBirthyearEqualTo(Short value) {
            addCriterion("birthyear =", value, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearNotEqualTo(Short value) {
            addCriterion("birthyear <>", value, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearGreaterThan(Short value) {
            addCriterion("birthyear >", value, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearGreaterThanOrEqualTo(Short value) {
            addCriterion("birthyear >=", value, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearLessThan(Short value) {
            addCriterion("birthyear <", value, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearLessThanOrEqualTo(Short value) {
            addCriterion("birthyear <=", value, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearIn(List<Short> values) {
            addCriterion("birthyear in", values, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearNotIn(List<Short> values) {
            addCriterion("birthyear not in", values, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearBetween(Short value1, Short value2) {
            addCriterion("birthyear between", value1, value2, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthyearNotBetween(Short value1, Short value2) {
            addCriterion("birthyear not between", value1, value2, "birthyear");
            return (Criteria) this;
        }

        public Criteria andBirthmonthIsNull() {
            addCriterion("birthmonth is null");
            return (Criteria) this;
        }

        public Criteria andBirthmonthIsNotNull() {
            addCriterion("birthmonth is not null");
            return (Criteria) this;
        }

        public Criteria andBirthmonthEqualTo(Byte value) {
            addCriterion("birthmonth =", value, "birthmonth");
            return (Criteria) this;
        }

        public Criteria andBirthmonthNotEqualTo(Byte value) {
            addCriterion("birthmonth <>", value, "birthmonth");
            return (Criteria) this;
        }

        public Criteria andBirthmonthGreaterThan(Byte value) {
            addCriterion("birthmonth >", value, "birthmonth");
            return (Criteria) this;
        }

        public Criteria andBirthmonthGreaterThanOrEqualTo(Byte value) {
            addCriterion("birthmonth >=", value, "birthmonth");
            return (Criteria) this;
        }

        public Criteria andBirthmonthLessThan(Byte value) {
            addCriterion("birthmonth <", value, "birthmonth");
            return (Criteria) this;
        }

        public Criteria andBirthmonthLessThanOrEqualTo(Byte value) {
            addCriterion("birthmonth <=", value, "birthmonth");
            return (Criteria) this;
        }

        public Criteria andBirthmonthIn(List<Byte> values) {
            addCriterion("birthmonth in", values, "birthmonth");
            return (Criteria) this;
        }

        public Criteria andBirthmonthNotIn(List<Byte> values) {
            addCriterion("birthmonth not in", values, "birthmonth");
            return (Criteria) this;
        }

        public Criteria andBirthmonthBetween(Byte value1, Byte value2) {
            addCriterion("birthmonth between", value1, value2, "birthmonth");
            return (Criteria) this;
        }

        public Criteria andBirthmonthNotBetween(Byte value1, Byte value2) {
            addCriterion("birthmonth not between", value1, value2, "birthmonth");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Byte value) {
            addCriterion("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Byte value) {
            addCriterion("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Byte value) {
            addCriterion("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Byte value) {
            addCriterion("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Byte value) {
            addCriterion("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Byte value) {
            addCriterion("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Byte> values) {
            addCriterion("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Byte> values) {
            addCriterion("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Byte value1, Byte value2) {
            addCriterion("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Byte value1, Byte value2) {
            addCriterion("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andConstellationIsNull() {
            addCriterion("constellation is null");
            return (Criteria) this;
        }

        public Criteria andConstellationIsNotNull() {
            addCriterion("constellation is not null");
            return (Criteria) this;
        }

        public Criteria andConstellationEqualTo(String value) {
            addCriterion("constellation =", value, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationNotEqualTo(String value) {
            addCriterion("constellation <>", value, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationGreaterThan(String value) {
            addCriterion("constellation >", value, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationGreaterThanOrEqualTo(String value) {
            addCriterion("constellation >=", value, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationLessThan(String value) {
            addCriterion("constellation <", value, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationLessThanOrEqualTo(String value) {
            addCriterion("constellation <=", value, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationLike(String value) {
            addCriterion("constellation like", value, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationNotLike(String value) {
            addCriterion("constellation not like", value, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationIn(List<String> values) {
            addCriterion("constellation in", values, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationNotIn(List<String> values) {
            addCriterion("constellation not in", values, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationBetween(String value1, String value2) {
            addCriterion("constellation between", value1, value2, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationNotBetween(String value1, String value2) {
            addCriterion("constellation not between", value1, value2, "constellation");
            return (Criteria) this;
        }

        public Criteria andZodiacIsNull() {
            addCriterion("zodiac is null");
            return (Criteria) this;
        }

        public Criteria andZodiacIsNotNull() {
            addCriterion("zodiac is not null");
            return (Criteria) this;
        }

        public Criteria andZodiacEqualTo(String value) {
            addCriterion("zodiac =", value, "zodiac");
            return (Criteria) this;
        }

        public Criteria andZodiacNotEqualTo(String value) {
            addCriterion("zodiac <>", value, "zodiac");
            return (Criteria) this;
        }

        public Criteria andZodiacGreaterThan(String value) {
            addCriterion("zodiac >", value, "zodiac");
            return (Criteria) this;
        }

        public Criteria andZodiacGreaterThanOrEqualTo(String value) {
            addCriterion("zodiac >=", value, "zodiac");
            return (Criteria) this;
        }

        public Criteria andZodiacLessThan(String value) {
            addCriterion("zodiac <", value, "zodiac");
            return (Criteria) this;
        }

        public Criteria andZodiacLessThanOrEqualTo(String value) {
            addCriterion("zodiac <=", value, "zodiac");
            return (Criteria) this;
        }

        public Criteria andZodiacLike(String value) {
            addCriterion("zodiac like", value, "zodiac");
            return (Criteria) this;
        }

        public Criteria andZodiacNotLike(String value) {
            addCriterion("zodiac not like", value, "zodiac");
            return (Criteria) this;
        }

        public Criteria andZodiacIn(List<String> values) {
            addCriterion("zodiac in", values, "zodiac");
            return (Criteria) this;
        }

        public Criteria andZodiacNotIn(List<String> values) {
            addCriterion("zodiac not in", values, "zodiac");
            return (Criteria) this;
        }

        public Criteria andZodiacBetween(String value1, String value2) {
            addCriterion("zodiac between", value1, value2, "zodiac");
            return (Criteria) this;
        }

        public Criteria andZodiacNotBetween(String value1, String value2) {
            addCriterion("zodiac not between", value1, value2, "zodiac");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("telephone is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("telephone is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("telephone =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("telephone <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("telephone >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("telephone >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("telephone <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("telephone <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("telephone like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("telephone not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("telephone in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("telephone not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("telephone between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("telephone not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeIsNull() {
            addCriterion("idcardtype is null");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeIsNotNull() {
            addCriterion("idcardtype is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeEqualTo(String value) {
            addCriterion("idcardtype =", value, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeNotEqualTo(String value) {
            addCriterion("idcardtype <>", value, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeGreaterThan(String value) {
            addCriterion("idcardtype >", value, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeGreaterThanOrEqualTo(String value) {
            addCriterion("idcardtype >=", value, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeLessThan(String value) {
            addCriterion("idcardtype <", value, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeLessThanOrEqualTo(String value) {
            addCriterion("idcardtype <=", value, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeLike(String value) {
            addCriterion("idcardtype like", value, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeNotLike(String value) {
            addCriterion("idcardtype not like", value, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeIn(List<String> values) {
            addCriterion("idcardtype in", values, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeNotIn(List<String> values) {
            addCriterion("idcardtype not in", values, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeBetween(String value1, String value2) {
            addCriterion("idcardtype between", value1, value2, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardtypeNotBetween(String value1, String value2) {
            addCriterion("idcardtype not between", value1, value2, "idcardtype");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNull() {
            addCriterion("idcard is null");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNotNull() {
            addCriterion("idcard is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardEqualTo(String value) {
            addCriterion("idcard =", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotEqualTo(String value) {
            addCriterion("idcard <>", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThan(String value) {
            addCriterion("idcard >", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("idcard >=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThan(String value) {
            addCriterion("idcard <", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThanOrEqualTo(String value) {
            addCriterion("idcard <=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLike(String value) {
            addCriterion("idcard like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotLike(String value) {
            addCriterion("idcard not like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardIn(List<String> values) {
            addCriterion("idcard in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotIn(List<String> values) {
            addCriterion("idcard not in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardBetween(String value1, String value2) {
            addCriterion("idcard between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotBetween(String value1, String value2) {
            addCriterion("idcard not between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andZipcodeIsNull() {
            addCriterion("zipcode is null");
            return (Criteria) this;
        }

        public Criteria andZipcodeIsNotNull() {
            addCriterion("zipcode is not null");
            return (Criteria) this;
        }

        public Criteria andZipcodeEqualTo(String value) {
            addCriterion("zipcode =", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeNotEqualTo(String value) {
            addCriterion("zipcode <>", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeGreaterThan(String value) {
            addCriterion("zipcode >", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeGreaterThanOrEqualTo(String value) {
            addCriterion("zipcode >=", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeLessThan(String value) {
            addCriterion("zipcode <", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeLessThanOrEqualTo(String value) {
            addCriterion("zipcode <=", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeLike(String value) {
            addCriterion("zipcode like", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeNotLike(String value) {
            addCriterion("zipcode not like", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeIn(List<String> values) {
            addCriterion("zipcode in", values, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeNotIn(List<String> values) {
            addCriterion("zipcode not in", values, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeBetween(String value1, String value2) {
            addCriterion("zipcode between", value1, value2, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeNotBetween(String value1, String value2) {
            addCriterion("zipcode not between", value1, value2, "zipcode");
            return (Criteria) this;
        }

        public Criteria andNationalityIsNull() {
            addCriterion("nationality is null");
            return (Criteria) this;
        }

        public Criteria andNationalityIsNotNull() {
            addCriterion("nationality is not null");
            return (Criteria) this;
        }

        public Criteria andNationalityEqualTo(String value) {
            addCriterion("nationality =", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotEqualTo(String value) {
            addCriterion("nationality <>", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityGreaterThan(String value) {
            addCriterion("nationality >", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityGreaterThanOrEqualTo(String value) {
            addCriterion("nationality >=", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLessThan(String value) {
            addCriterion("nationality <", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLessThanOrEqualTo(String value) {
            addCriterion("nationality <=", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLike(String value) {
            addCriterion("nationality like", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotLike(String value) {
            addCriterion("nationality not like", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityIn(List<String> values) {
            addCriterion("nationality in", values, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotIn(List<String> values) {
            addCriterion("nationality not in", values, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityBetween(String value1, String value2) {
            addCriterion("nationality between", value1, value2, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotBetween(String value1, String value2) {
            addCriterion("nationality not between", value1, value2, "nationality");
            return (Criteria) this;
        }

        public Criteria andBirthprovinceIsNull() {
            addCriterion("birthprovince is null");
            return (Criteria) this;
        }

        public Criteria andBirthprovinceIsNotNull() {
            addCriterion("birthprovince is not null");
            return (Criteria) this;
        }

        public Criteria andBirthprovinceEqualTo(String value) {
            addCriterion("birthprovince =", value, "birthprovince");
            return (Criteria) this;
        }

        public Criteria andBirthprovinceNotEqualTo(String value) {
            addCriterion("birthprovince <>", value, "birthprovince");
            return (Criteria) this;
        }

        public Criteria andBirthprovinceGreaterThan(String value) {
            addCriterion("birthprovince >", value, "birthprovince");
            return (Criteria) this;
        }

        public Criteria andBirthprovinceGreaterThanOrEqualTo(String value) {
            addCriterion("birthprovince >=", value, "birthprovince");
            return (Criteria) this;
        }

        public Criteria andBirthprovinceLessThan(String value) {
            addCriterion("birthprovince <", value, "birthprovince");
            return (Criteria) this;
        }

        public Criteria andBirthprovinceLessThanOrEqualTo(String value) {
            addCriterion("birthprovince <=", value, "birthprovince");
            return (Criteria) this;
        }

        public Criteria andBirthprovinceLike(String value) {
            addCriterion("birthprovince like", value, "birthprovince");
            return (Criteria) this;
        }

        public Criteria andBirthprovinceNotLike(String value) {
            addCriterion("birthprovince not like", value, "birthprovince");
            return (Criteria) this;
        }

        public Criteria andBirthprovinceIn(List<String> values) {
            addCriterion("birthprovince in", values, "birthprovince");
            return (Criteria) this;
        }

        public Criteria andBirthprovinceNotIn(List<String> values) {
            addCriterion("birthprovince not in", values, "birthprovince");
            return (Criteria) this;
        }

        public Criteria andBirthprovinceBetween(String value1, String value2) {
            addCriterion("birthprovince between", value1, value2, "birthprovince");
            return (Criteria) this;
        }

        public Criteria andBirthprovinceNotBetween(String value1, String value2) {
            addCriterion("birthprovince not between", value1, value2, "birthprovince");
            return (Criteria) this;
        }

        public Criteria andBirthcityIsNull() {
            addCriterion("birthcity is null");
            return (Criteria) this;
        }

        public Criteria andBirthcityIsNotNull() {
            addCriterion("birthcity is not null");
            return (Criteria) this;
        }

        public Criteria andBirthcityEqualTo(String value) {
            addCriterion("birthcity =", value, "birthcity");
            return (Criteria) this;
        }

        public Criteria andBirthcityNotEqualTo(String value) {
            addCriterion("birthcity <>", value, "birthcity");
            return (Criteria) this;
        }

        public Criteria andBirthcityGreaterThan(String value) {
            addCriterion("birthcity >", value, "birthcity");
            return (Criteria) this;
        }

        public Criteria andBirthcityGreaterThanOrEqualTo(String value) {
            addCriterion("birthcity >=", value, "birthcity");
            return (Criteria) this;
        }

        public Criteria andBirthcityLessThan(String value) {
            addCriterion("birthcity <", value, "birthcity");
            return (Criteria) this;
        }

        public Criteria andBirthcityLessThanOrEqualTo(String value) {
            addCriterion("birthcity <=", value, "birthcity");
            return (Criteria) this;
        }

        public Criteria andBirthcityLike(String value) {
            addCriterion("birthcity like", value, "birthcity");
            return (Criteria) this;
        }

        public Criteria andBirthcityNotLike(String value) {
            addCriterion("birthcity not like", value, "birthcity");
            return (Criteria) this;
        }

        public Criteria andBirthcityIn(List<String> values) {
            addCriterion("birthcity in", values, "birthcity");
            return (Criteria) this;
        }

        public Criteria andBirthcityNotIn(List<String> values) {
            addCriterion("birthcity not in", values, "birthcity");
            return (Criteria) this;
        }

        public Criteria andBirthcityBetween(String value1, String value2) {
            addCriterion("birthcity between", value1, value2, "birthcity");
            return (Criteria) this;
        }

        public Criteria andBirthcityNotBetween(String value1, String value2) {
            addCriterion("birthcity not between", value1, value2, "birthcity");
            return (Criteria) this;
        }

        public Criteria andBirthdistIsNull() {
            addCriterion("birthdist is null");
            return (Criteria) this;
        }

        public Criteria andBirthdistIsNotNull() {
            addCriterion("birthdist is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdistEqualTo(String value) {
            addCriterion("birthdist =", value, "birthdist");
            return (Criteria) this;
        }

        public Criteria andBirthdistNotEqualTo(String value) {
            addCriterion("birthdist <>", value, "birthdist");
            return (Criteria) this;
        }

        public Criteria andBirthdistGreaterThan(String value) {
            addCriterion("birthdist >", value, "birthdist");
            return (Criteria) this;
        }

        public Criteria andBirthdistGreaterThanOrEqualTo(String value) {
            addCriterion("birthdist >=", value, "birthdist");
            return (Criteria) this;
        }

        public Criteria andBirthdistLessThan(String value) {
            addCriterion("birthdist <", value, "birthdist");
            return (Criteria) this;
        }

        public Criteria andBirthdistLessThanOrEqualTo(String value) {
            addCriterion("birthdist <=", value, "birthdist");
            return (Criteria) this;
        }

        public Criteria andBirthdistLike(String value) {
            addCriterion("birthdist like", value, "birthdist");
            return (Criteria) this;
        }

        public Criteria andBirthdistNotLike(String value) {
            addCriterion("birthdist not like", value, "birthdist");
            return (Criteria) this;
        }

        public Criteria andBirthdistIn(List<String> values) {
            addCriterion("birthdist in", values, "birthdist");
            return (Criteria) this;
        }

        public Criteria andBirthdistNotIn(List<String> values) {
            addCriterion("birthdist not in", values, "birthdist");
            return (Criteria) this;
        }

        public Criteria andBirthdistBetween(String value1, String value2) {
            addCriterion("birthdist between", value1, value2, "birthdist");
            return (Criteria) this;
        }

        public Criteria andBirthdistNotBetween(String value1, String value2) {
            addCriterion("birthdist not between", value1, value2, "birthdist");
            return (Criteria) this;
        }

        public Criteria andBirthcommunityIsNull() {
            addCriterion("birthcommunity is null");
            return (Criteria) this;
        }

        public Criteria andBirthcommunityIsNotNull() {
            addCriterion("birthcommunity is not null");
            return (Criteria) this;
        }

        public Criteria andBirthcommunityEqualTo(String value) {
            addCriterion("birthcommunity =", value, "birthcommunity");
            return (Criteria) this;
        }

        public Criteria andBirthcommunityNotEqualTo(String value) {
            addCriterion("birthcommunity <>", value, "birthcommunity");
            return (Criteria) this;
        }

        public Criteria andBirthcommunityGreaterThan(String value) {
            addCriterion("birthcommunity >", value, "birthcommunity");
            return (Criteria) this;
        }

        public Criteria andBirthcommunityGreaterThanOrEqualTo(String value) {
            addCriterion("birthcommunity >=", value, "birthcommunity");
            return (Criteria) this;
        }

        public Criteria andBirthcommunityLessThan(String value) {
            addCriterion("birthcommunity <", value, "birthcommunity");
            return (Criteria) this;
        }

        public Criteria andBirthcommunityLessThanOrEqualTo(String value) {
            addCriterion("birthcommunity <=", value, "birthcommunity");
            return (Criteria) this;
        }

        public Criteria andBirthcommunityLike(String value) {
            addCriterion("birthcommunity like", value, "birthcommunity");
            return (Criteria) this;
        }

        public Criteria andBirthcommunityNotLike(String value) {
            addCriterion("birthcommunity not like", value, "birthcommunity");
            return (Criteria) this;
        }

        public Criteria andBirthcommunityIn(List<String> values) {
            addCriterion("birthcommunity in", values, "birthcommunity");
            return (Criteria) this;
        }

        public Criteria andBirthcommunityNotIn(List<String> values) {
            addCriterion("birthcommunity not in", values, "birthcommunity");
            return (Criteria) this;
        }

        public Criteria andBirthcommunityBetween(String value1, String value2) {
            addCriterion("birthcommunity between", value1, value2, "birthcommunity");
            return (Criteria) this;
        }

        public Criteria andBirthcommunityNotBetween(String value1, String value2) {
            addCriterion("birthcommunity not between", value1, value2, "birthcommunity");
            return (Criteria) this;
        }

        public Criteria andResideprovinceIsNull() {
            addCriterion("resideprovince is null");
            return (Criteria) this;
        }

        public Criteria andResideprovinceIsNotNull() {
            addCriterion("resideprovince is not null");
            return (Criteria) this;
        }

        public Criteria andResideprovinceEqualTo(String value) {
            addCriterion("resideprovince =", value, "resideprovince");
            return (Criteria) this;
        }

        public Criteria andResideprovinceNotEqualTo(String value) {
            addCriterion("resideprovince <>", value, "resideprovince");
            return (Criteria) this;
        }

        public Criteria andResideprovinceGreaterThan(String value) {
            addCriterion("resideprovince >", value, "resideprovince");
            return (Criteria) this;
        }

        public Criteria andResideprovinceGreaterThanOrEqualTo(String value) {
            addCriterion("resideprovince >=", value, "resideprovince");
            return (Criteria) this;
        }

        public Criteria andResideprovinceLessThan(String value) {
            addCriterion("resideprovince <", value, "resideprovince");
            return (Criteria) this;
        }

        public Criteria andResideprovinceLessThanOrEqualTo(String value) {
            addCriterion("resideprovince <=", value, "resideprovince");
            return (Criteria) this;
        }

        public Criteria andResideprovinceLike(String value) {
            addCriterion("resideprovince like", value, "resideprovince");
            return (Criteria) this;
        }

        public Criteria andResideprovinceNotLike(String value) {
            addCriterion("resideprovince not like", value, "resideprovince");
            return (Criteria) this;
        }

        public Criteria andResideprovinceIn(List<String> values) {
            addCriterion("resideprovince in", values, "resideprovince");
            return (Criteria) this;
        }

        public Criteria andResideprovinceNotIn(List<String> values) {
            addCriterion("resideprovince not in", values, "resideprovince");
            return (Criteria) this;
        }

        public Criteria andResideprovinceBetween(String value1, String value2) {
            addCriterion("resideprovince between", value1, value2, "resideprovince");
            return (Criteria) this;
        }

        public Criteria andResideprovinceNotBetween(String value1, String value2) {
            addCriterion("resideprovince not between", value1, value2, "resideprovince");
            return (Criteria) this;
        }

        public Criteria andResidecityIsNull() {
            addCriterion("residecity is null");
            return (Criteria) this;
        }

        public Criteria andResidecityIsNotNull() {
            addCriterion("residecity is not null");
            return (Criteria) this;
        }

        public Criteria andResidecityEqualTo(String value) {
            addCriterion("residecity =", value, "residecity");
            return (Criteria) this;
        }

        public Criteria andResidecityNotEqualTo(String value) {
            addCriterion("residecity <>", value, "residecity");
            return (Criteria) this;
        }

        public Criteria andResidecityGreaterThan(String value) {
            addCriterion("residecity >", value, "residecity");
            return (Criteria) this;
        }

        public Criteria andResidecityGreaterThanOrEqualTo(String value) {
            addCriterion("residecity >=", value, "residecity");
            return (Criteria) this;
        }

        public Criteria andResidecityLessThan(String value) {
            addCriterion("residecity <", value, "residecity");
            return (Criteria) this;
        }

        public Criteria andResidecityLessThanOrEqualTo(String value) {
            addCriterion("residecity <=", value, "residecity");
            return (Criteria) this;
        }

        public Criteria andResidecityLike(String value) {
            addCriterion("residecity like", value, "residecity");
            return (Criteria) this;
        }

        public Criteria andResidecityNotLike(String value) {
            addCriterion("residecity not like", value, "residecity");
            return (Criteria) this;
        }

        public Criteria andResidecityIn(List<String> values) {
            addCriterion("residecity in", values, "residecity");
            return (Criteria) this;
        }

        public Criteria andResidecityNotIn(List<String> values) {
            addCriterion("residecity not in", values, "residecity");
            return (Criteria) this;
        }

        public Criteria andResidecityBetween(String value1, String value2) {
            addCriterion("residecity between", value1, value2, "residecity");
            return (Criteria) this;
        }

        public Criteria andResidecityNotBetween(String value1, String value2) {
            addCriterion("residecity not between", value1, value2, "residecity");
            return (Criteria) this;
        }

        public Criteria andResidedistIsNull() {
            addCriterion("residedist is null");
            return (Criteria) this;
        }

        public Criteria andResidedistIsNotNull() {
            addCriterion("residedist is not null");
            return (Criteria) this;
        }

        public Criteria andResidedistEqualTo(String value) {
            addCriterion("residedist =", value, "residedist");
            return (Criteria) this;
        }

        public Criteria andResidedistNotEqualTo(String value) {
            addCriterion("residedist <>", value, "residedist");
            return (Criteria) this;
        }

        public Criteria andResidedistGreaterThan(String value) {
            addCriterion("residedist >", value, "residedist");
            return (Criteria) this;
        }

        public Criteria andResidedistGreaterThanOrEqualTo(String value) {
            addCriterion("residedist >=", value, "residedist");
            return (Criteria) this;
        }

        public Criteria andResidedistLessThan(String value) {
            addCriterion("residedist <", value, "residedist");
            return (Criteria) this;
        }

        public Criteria andResidedistLessThanOrEqualTo(String value) {
            addCriterion("residedist <=", value, "residedist");
            return (Criteria) this;
        }

        public Criteria andResidedistLike(String value) {
            addCriterion("residedist like", value, "residedist");
            return (Criteria) this;
        }

        public Criteria andResidedistNotLike(String value) {
            addCriterion("residedist not like", value, "residedist");
            return (Criteria) this;
        }

        public Criteria andResidedistIn(List<String> values) {
            addCriterion("residedist in", values, "residedist");
            return (Criteria) this;
        }

        public Criteria andResidedistNotIn(List<String> values) {
            addCriterion("residedist not in", values, "residedist");
            return (Criteria) this;
        }

        public Criteria andResidedistBetween(String value1, String value2) {
            addCriterion("residedist between", value1, value2, "residedist");
            return (Criteria) this;
        }

        public Criteria andResidedistNotBetween(String value1, String value2) {
            addCriterion("residedist not between", value1, value2, "residedist");
            return (Criteria) this;
        }

        public Criteria andResidecommunityIsNull() {
            addCriterion("residecommunity is null");
            return (Criteria) this;
        }

        public Criteria andResidecommunityIsNotNull() {
            addCriterion("residecommunity is not null");
            return (Criteria) this;
        }

        public Criteria andResidecommunityEqualTo(String value) {
            addCriterion("residecommunity =", value, "residecommunity");
            return (Criteria) this;
        }

        public Criteria andResidecommunityNotEqualTo(String value) {
            addCriterion("residecommunity <>", value, "residecommunity");
            return (Criteria) this;
        }

        public Criteria andResidecommunityGreaterThan(String value) {
            addCriterion("residecommunity >", value, "residecommunity");
            return (Criteria) this;
        }

        public Criteria andResidecommunityGreaterThanOrEqualTo(String value) {
            addCriterion("residecommunity >=", value, "residecommunity");
            return (Criteria) this;
        }

        public Criteria andResidecommunityLessThan(String value) {
            addCriterion("residecommunity <", value, "residecommunity");
            return (Criteria) this;
        }

        public Criteria andResidecommunityLessThanOrEqualTo(String value) {
            addCriterion("residecommunity <=", value, "residecommunity");
            return (Criteria) this;
        }

        public Criteria andResidecommunityLike(String value) {
            addCriterion("residecommunity like", value, "residecommunity");
            return (Criteria) this;
        }

        public Criteria andResidecommunityNotLike(String value) {
            addCriterion("residecommunity not like", value, "residecommunity");
            return (Criteria) this;
        }

        public Criteria andResidecommunityIn(List<String> values) {
            addCriterion("residecommunity in", values, "residecommunity");
            return (Criteria) this;
        }

        public Criteria andResidecommunityNotIn(List<String> values) {
            addCriterion("residecommunity not in", values, "residecommunity");
            return (Criteria) this;
        }

        public Criteria andResidecommunityBetween(String value1, String value2) {
            addCriterion("residecommunity between", value1, value2, "residecommunity");
            return (Criteria) this;
        }

        public Criteria andResidecommunityNotBetween(String value1, String value2) {
            addCriterion("residecommunity not between", value1, value2, "residecommunity");
            return (Criteria) this;
        }

        public Criteria andResidesuiteIsNull() {
            addCriterion("residesuite is null");
            return (Criteria) this;
        }

        public Criteria andResidesuiteIsNotNull() {
            addCriterion("residesuite is not null");
            return (Criteria) this;
        }

        public Criteria andResidesuiteEqualTo(String value) {
            addCriterion("residesuite =", value, "residesuite");
            return (Criteria) this;
        }

        public Criteria andResidesuiteNotEqualTo(String value) {
            addCriterion("residesuite <>", value, "residesuite");
            return (Criteria) this;
        }

        public Criteria andResidesuiteGreaterThan(String value) {
            addCriterion("residesuite >", value, "residesuite");
            return (Criteria) this;
        }

        public Criteria andResidesuiteGreaterThanOrEqualTo(String value) {
            addCriterion("residesuite >=", value, "residesuite");
            return (Criteria) this;
        }

        public Criteria andResidesuiteLessThan(String value) {
            addCriterion("residesuite <", value, "residesuite");
            return (Criteria) this;
        }

        public Criteria andResidesuiteLessThanOrEqualTo(String value) {
            addCriterion("residesuite <=", value, "residesuite");
            return (Criteria) this;
        }

        public Criteria andResidesuiteLike(String value) {
            addCriterion("residesuite like", value, "residesuite");
            return (Criteria) this;
        }

        public Criteria andResidesuiteNotLike(String value) {
            addCriterion("residesuite not like", value, "residesuite");
            return (Criteria) this;
        }

        public Criteria andResidesuiteIn(List<String> values) {
            addCriterion("residesuite in", values, "residesuite");
            return (Criteria) this;
        }

        public Criteria andResidesuiteNotIn(List<String> values) {
            addCriterion("residesuite not in", values, "residesuite");
            return (Criteria) this;
        }

        public Criteria andResidesuiteBetween(String value1, String value2) {
            addCriterion("residesuite between", value1, value2, "residesuite");
            return (Criteria) this;
        }

        public Criteria andResidesuiteNotBetween(String value1, String value2) {
            addCriterion("residesuite not between", value1, value2, "residesuite");
            return (Criteria) this;
        }

        public Criteria andGraduateschoolIsNull() {
            addCriterion("graduateschool is null");
            return (Criteria) this;
        }

        public Criteria andGraduateschoolIsNotNull() {
            addCriterion("graduateschool is not null");
            return (Criteria) this;
        }

        public Criteria andGraduateschoolEqualTo(String value) {
            addCriterion("graduateschool =", value, "graduateschool");
            return (Criteria) this;
        }

        public Criteria andGraduateschoolNotEqualTo(String value) {
            addCriterion("graduateschool <>", value, "graduateschool");
            return (Criteria) this;
        }

        public Criteria andGraduateschoolGreaterThan(String value) {
            addCriterion("graduateschool >", value, "graduateschool");
            return (Criteria) this;
        }

        public Criteria andGraduateschoolGreaterThanOrEqualTo(String value) {
            addCriterion("graduateschool >=", value, "graduateschool");
            return (Criteria) this;
        }

        public Criteria andGraduateschoolLessThan(String value) {
            addCriterion("graduateschool <", value, "graduateschool");
            return (Criteria) this;
        }

        public Criteria andGraduateschoolLessThanOrEqualTo(String value) {
            addCriterion("graduateschool <=", value, "graduateschool");
            return (Criteria) this;
        }

        public Criteria andGraduateschoolLike(String value) {
            addCriterion("graduateschool like", value, "graduateschool");
            return (Criteria) this;
        }

        public Criteria andGraduateschoolNotLike(String value) {
            addCriterion("graduateschool not like", value, "graduateschool");
            return (Criteria) this;
        }

        public Criteria andGraduateschoolIn(List<String> values) {
            addCriterion("graduateschool in", values, "graduateschool");
            return (Criteria) this;
        }

        public Criteria andGraduateschoolNotIn(List<String> values) {
            addCriterion("graduateschool not in", values, "graduateschool");
            return (Criteria) this;
        }

        public Criteria andGraduateschoolBetween(String value1, String value2) {
            addCriterion("graduateschool between", value1, value2, "graduateschool");
            return (Criteria) this;
        }

        public Criteria andGraduateschoolNotBetween(String value1, String value2) {
            addCriterion("graduateschool not between", value1, value2, "graduateschool");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("company like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("company not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("company not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andEducationIsNull() {
            addCriterion("education is null");
            return (Criteria) this;
        }

        public Criteria andEducationIsNotNull() {
            addCriterion("education is not null");
            return (Criteria) this;
        }

        public Criteria andEducationEqualTo(String value) {
            addCriterion("education =", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotEqualTo(String value) {
            addCriterion("education <>", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThan(String value) {
            addCriterion("education >", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThanOrEqualTo(String value) {
            addCriterion("education >=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThan(String value) {
            addCriterion("education <", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThanOrEqualTo(String value) {
            addCriterion("education <=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLike(String value) {
            addCriterion("education like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotLike(String value) {
            addCriterion("education not like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationIn(List<String> values) {
            addCriterion("education in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotIn(List<String> values) {
            addCriterion("education not in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationBetween(String value1, String value2) {
            addCriterion("education between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotBetween(String value1, String value2) {
            addCriterion("education not between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andOccupationIsNull() {
            addCriterion("occupation is null");
            return (Criteria) this;
        }

        public Criteria andOccupationIsNotNull() {
            addCriterion("occupation is not null");
            return (Criteria) this;
        }

        public Criteria andOccupationEqualTo(String value) {
            addCriterion("occupation =", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotEqualTo(String value) {
            addCriterion("occupation <>", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationGreaterThan(String value) {
            addCriterion("occupation >", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationGreaterThanOrEqualTo(String value) {
            addCriterion("occupation >=", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationLessThan(String value) {
            addCriterion("occupation <", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationLessThanOrEqualTo(String value) {
            addCriterion("occupation <=", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationLike(String value) {
            addCriterion("occupation like", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotLike(String value) {
            addCriterion("occupation not like", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationIn(List<String> values) {
            addCriterion("occupation in", values, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotIn(List<String> values) {
            addCriterion("occupation not in", values, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationBetween(String value1, String value2) {
            addCriterion("occupation between", value1, value2, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotBetween(String value1, String value2) {
            addCriterion("occupation not between", value1, value2, "occupation");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("position is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("position is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(String value) {
            addCriterion("position =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(String value) {
            addCriterion("position <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(String value) {
            addCriterion("position >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(String value) {
            addCriterion("position >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(String value) {
            addCriterion("position <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(String value) {
            addCriterion("position <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLike(String value) {
            addCriterion("position like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotLike(String value) {
            addCriterion("position not like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<String> values) {
            addCriterion("position in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<String> values) {
            addCriterion("position not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(String value1, String value2) {
            addCriterion("position between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(String value1, String value2) {
            addCriterion("position not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andRevenueIsNull() {
            addCriterion("revenue is null");
            return (Criteria) this;
        }

        public Criteria andRevenueIsNotNull() {
            addCriterion("revenue is not null");
            return (Criteria) this;
        }

        public Criteria andRevenueEqualTo(String value) {
            addCriterion("revenue =", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueNotEqualTo(String value) {
            addCriterion("revenue <>", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueGreaterThan(String value) {
            addCriterion("revenue >", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueGreaterThanOrEqualTo(String value) {
            addCriterion("revenue >=", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueLessThan(String value) {
            addCriterion("revenue <", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueLessThanOrEqualTo(String value) {
            addCriterion("revenue <=", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueLike(String value) {
            addCriterion("revenue like", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueNotLike(String value) {
            addCriterion("revenue not like", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueIn(List<String> values) {
            addCriterion("revenue in", values, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueNotIn(List<String> values) {
            addCriterion("revenue not in", values, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueBetween(String value1, String value2) {
            addCriterion("revenue between", value1, value2, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueNotBetween(String value1, String value2) {
            addCriterion("revenue not between", value1, value2, "revenue");
            return (Criteria) this;
        }

        public Criteria andAffectivestatusIsNull() {
            addCriterion("affectivestatus is null");
            return (Criteria) this;
        }

        public Criteria andAffectivestatusIsNotNull() {
            addCriterion("affectivestatus is not null");
            return (Criteria) this;
        }

        public Criteria andAffectivestatusEqualTo(String value) {
            addCriterion("affectivestatus =", value, "affectivestatus");
            return (Criteria) this;
        }

        public Criteria andAffectivestatusNotEqualTo(String value) {
            addCriterion("affectivestatus <>", value, "affectivestatus");
            return (Criteria) this;
        }

        public Criteria andAffectivestatusGreaterThan(String value) {
            addCriterion("affectivestatus >", value, "affectivestatus");
            return (Criteria) this;
        }

        public Criteria andAffectivestatusGreaterThanOrEqualTo(String value) {
            addCriterion("affectivestatus >=", value, "affectivestatus");
            return (Criteria) this;
        }

        public Criteria andAffectivestatusLessThan(String value) {
            addCriterion("affectivestatus <", value, "affectivestatus");
            return (Criteria) this;
        }

        public Criteria andAffectivestatusLessThanOrEqualTo(String value) {
            addCriterion("affectivestatus <=", value, "affectivestatus");
            return (Criteria) this;
        }

        public Criteria andAffectivestatusLike(String value) {
            addCriterion("affectivestatus like", value, "affectivestatus");
            return (Criteria) this;
        }

        public Criteria andAffectivestatusNotLike(String value) {
            addCriterion("affectivestatus not like", value, "affectivestatus");
            return (Criteria) this;
        }

        public Criteria andAffectivestatusIn(List<String> values) {
            addCriterion("affectivestatus in", values, "affectivestatus");
            return (Criteria) this;
        }

        public Criteria andAffectivestatusNotIn(List<String> values) {
            addCriterion("affectivestatus not in", values, "affectivestatus");
            return (Criteria) this;
        }

        public Criteria andAffectivestatusBetween(String value1, String value2) {
            addCriterion("affectivestatus between", value1, value2, "affectivestatus");
            return (Criteria) this;
        }

        public Criteria andAffectivestatusNotBetween(String value1, String value2) {
            addCriterion("affectivestatus not between", value1, value2, "affectivestatus");
            return (Criteria) this;
        }

        public Criteria andLookingforIsNull() {
            addCriterion("lookingfor is null");
            return (Criteria) this;
        }

        public Criteria andLookingforIsNotNull() {
            addCriterion("lookingfor is not null");
            return (Criteria) this;
        }

        public Criteria andLookingforEqualTo(String value) {
            addCriterion("lookingfor =", value, "lookingfor");
            return (Criteria) this;
        }

        public Criteria andLookingforNotEqualTo(String value) {
            addCriterion("lookingfor <>", value, "lookingfor");
            return (Criteria) this;
        }

        public Criteria andLookingforGreaterThan(String value) {
            addCriterion("lookingfor >", value, "lookingfor");
            return (Criteria) this;
        }

        public Criteria andLookingforGreaterThanOrEqualTo(String value) {
            addCriterion("lookingfor >=", value, "lookingfor");
            return (Criteria) this;
        }

        public Criteria andLookingforLessThan(String value) {
            addCriterion("lookingfor <", value, "lookingfor");
            return (Criteria) this;
        }

        public Criteria andLookingforLessThanOrEqualTo(String value) {
            addCriterion("lookingfor <=", value, "lookingfor");
            return (Criteria) this;
        }

        public Criteria andLookingforLike(String value) {
            addCriterion("lookingfor like", value, "lookingfor");
            return (Criteria) this;
        }

        public Criteria andLookingforNotLike(String value) {
            addCriterion("lookingfor not like", value, "lookingfor");
            return (Criteria) this;
        }

        public Criteria andLookingforIn(List<String> values) {
            addCriterion("lookingfor in", values, "lookingfor");
            return (Criteria) this;
        }

        public Criteria andLookingforNotIn(List<String> values) {
            addCriterion("lookingfor not in", values, "lookingfor");
            return (Criteria) this;
        }

        public Criteria andLookingforBetween(String value1, String value2) {
            addCriterion("lookingfor between", value1, value2, "lookingfor");
            return (Criteria) this;
        }

        public Criteria andLookingforNotBetween(String value1, String value2) {
            addCriterion("lookingfor not between", value1, value2, "lookingfor");
            return (Criteria) this;
        }

        public Criteria andBloodtypeIsNull() {
            addCriterion("bloodtype is null");
            return (Criteria) this;
        }

        public Criteria andBloodtypeIsNotNull() {
            addCriterion("bloodtype is not null");
            return (Criteria) this;
        }

        public Criteria andBloodtypeEqualTo(String value) {
            addCriterion("bloodtype =", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeNotEqualTo(String value) {
            addCriterion("bloodtype <>", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeGreaterThan(String value) {
            addCriterion("bloodtype >", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeGreaterThanOrEqualTo(String value) {
            addCriterion("bloodtype >=", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeLessThan(String value) {
            addCriterion("bloodtype <", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeLessThanOrEqualTo(String value) {
            addCriterion("bloodtype <=", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeLike(String value) {
            addCriterion("bloodtype like", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeNotLike(String value) {
            addCriterion("bloodtype not like", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeIn(List<String> values) {
            addCriterion("bloodtype in", values, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeNotIn(List<String> values) {
            addCriterion("bloodtype not in", values, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeBetween(String value1, String value2) {
            addCriterion("bloodtype between", value1, value2, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeNotBetween(String value1, String value2) {
            addCriterion("bloodtype not between", value1, value2, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andHeightIsNull() {
            addCriterion("height is null");
            return (Criteria) this;
        }

        public Criteria andHeightIsNotNull() {
            addCriterion("height is not null");
            return (Criteria) this;
        }

        public Criteria andHeightEqualTo(String value) {
            addCriterion("height =", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotEqualTo(String value) {
            addCriterion("height <>", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThan(String value) {
            addCriterion("height >", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThanOrEqualTo(String value) {
            addCriterion("height >=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThan(String value) {
            addCriterion("height <", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThanOrEqualTo(String value) {
            addCriterion("height <=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLike(String value) {
            addCriterion("height like", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotLike(String value) {
            addCriterion("height not like", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightIn(List<String> values) {
            addCriterion("height in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotIn(List<String> values) {
            addCriterion("height not in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightBetween(String value1, String value2) {
            addCriterion("height between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotBetween(String value1, String value2) {
            addCriterion("height not between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(String value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(String value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(String value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(String value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(String value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(String value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLike(String value) {
            addCriterion("weight like", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotLike(String value) {
            addCriterion("weight not like", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<String> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<String> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(String value1, String value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(String value1, String value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andAlipayIsNull() {
            addCriterion("alipay is null");
            return (Criteria) this;
        }

        public Criteria andAlipayIsNotNull() {
            addCriterion("alipay is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayEqualTo(String value) {
            addCriterion("alipay =", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotEqualTo(String value) {
            addCriterion("alipay <>", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayGreaterThan(String value) {
            addCriterion("alipay >", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayGreaterThanOrEqualTo(String value) {
            addCriterion("alipay >=", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayLessThan(String value) {
            addCriterion("alipay <", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayLessThanOrEqualTo(String value) {
            addCriterion("alipay <=", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayLike(String value) {
            addCriterion("alipay like", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotLike(String value) {
            addCriterion("alipay not like", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayIn(List<String> values) {
            addCriterion("alipay in", values, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotIn(List<String> values) {
            addCriterion("alipay not in", values, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayBetween(String value1, String value2) {
            addCriterion("alipay between", value1, value2, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotBetween(String value1, String value2) {
            addCriterion("alipay not between", value1, value2, "alipay");
            return (Criteria) this;
        }

        public Criteria andIcqIsNull() {
            addCriterion("icq is null");
            return (Criteria) this;
        }

        public Criteria andIcqIsNotNull() {
            addCriterion("icq is not null");
            return (Criteria) this;
        }

        public Criteria andIcqEqualTo(String value) {
            addCriterion("icq =", value, "icq");
            return (Criteria) this;
        }

        public Criteria andIcqNotEqualTo(String value) {
            addCriterion("icq <>", value, "icq");
            return (Criteria) this;
        }

        public Criteria andIcqGreaterThan(String value) {
            addCriterion("icq >", value, "icq");
            return (Criteria) this;
        }

        public Criteria andIcqGreaterThanOrEqualTo(String value) {
            addCriterion("icq >=", value, "icq");
            return (Criteria) this;
        }

        public Criteria andIcqLessThan(String value) {
            addCriterion("icq <", value, "icq");
            return (Criteria) this;
        }

        public Criteria andIcqLessThanOrEqualTo(String value) {
            addCriterion("icq <=", value, "icq");
            return (Criteria) this;
        }

        public Criteria andIcqLike(String value) {
            addCriterion("icq like", value, "icq");
            return (Criteria) this;
        }

        public Criteria andIcqNotLike(String value) {
            addCriterion("icq not like", value, "icq");
            return (Criteria) this;
        }

        public Criteria andIcqIn(List<String> values) {
            addCriterion("icq in", values, "icq");
            return (Criteria) this;
        }

        public Criteria andIcqNotIn(List<String> values) {
            addCriterion("icq not in", values, "icq");
            return (Criteria) this;
        }

        public Criteria andIcqBetween(String value1, String value2) {
            addCriterion("icq between", value1, value2, "icq");
            return (Criteria) this;
        }

        public Criteria andIcqNotBetween(String value1, String value2) {
            addCriterion("icq not between", value1, value2, "icq");
            return (Criteria) this;
        }

        public Criteria andQqIsNull() {
            addCriterion("qq is null");
            return (Criteria) this;
        }

        public Criteria andQqIsNotNull() {
            addCriterion("qq is not null");
            return (Criteria) this;
        }

        public Criteria andQqEqualTo(String value) {
            addCriterion("qq =", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotEqualTo(String value) {
            addCriterion("qq <>", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThan(String value) {
            addCriterion("qq >", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThanOrEqualTo(String value) {
            addCriterion("qq >=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThan(String value) {
            addCriterion("qq <", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThanOrEqualTo(String value) {
            addCriterion("qq <=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLike(String value) {
            addCriterion("qq like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotLike(String value) {
            addCriterion("qq not like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqIn(List<String> values) {
            addCriterion("qq in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotIn(List<String> values) {
            addCriterion("qq not in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqBetween(String value1, String value2) {
            addCriterion("qq between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotBetween(String value1, String value2) {
            addCriterion("qq not between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andYahooIsNull() {
            addCriterion("yahoo is null");
            return (Criteria) this;
        }

        public Criteria andYahooIsNotNull() {
            addCriterion("yahoo is not null");
            return (Criteria) this;
        }

        public Criteria andYahooEqualTo(String value) {
            addCriterion("yahoo =", value, "yahoo");
            return (Criteria) this;
        }

        public Criteria andYahooNotEqualTo(String value) {
            addCriterion("yahoo <>", value, "yahoo");
            return (Criteria) this;
        }

        public Criteria andYahooGreaterThan(String value) {
            addCriterion("yahoo >", value, "yahoo");
            return (Criteria) this;
        }

        public Criteria andYahooGreaterThanOrEqualTo(String value) {
            addCriterion("yahoo >=", value, "yahoo");
            return (Criteria) this;
        }

        public Criteria andYahooLessThan(String value) {
            addCriterion("yahoo <", value, "yahoo");
            return (Criteria) this;
        }

        public Criteria andYahooLessThanOrEqualTo(String value) {
            addCriterion("yahoo <=", value, "yahoo");
            return (Criteria) this;
        }

        public Criteria andYahooLike(String value) {
            addCriterion("yahoo like", value, "yahoo");
            return (Criteria) this;
        }

        public Criteria andYahooNotLike(String value) {
            addCriterion("yahoo not like", value, "yahoo");
            return (Criteria) this;
        }

        public Criteria andYahooIn(List<String> values) {
            addCriterion("yahoo in", values, "yahoo");
            return (Criteria) this;
        }

        public Criteria andYahooNotIn(List<String> values) {
            addCriterion("yahoo not in", values, "yahoo");
            return (Criteria) this;
        }

        public Criteria andYahooBetween(String value1, String value2) {
            addCriterion("yahoo between", value1, value2, "yahoo");
            return (Criteria) this;
        }

        public Criteria andYahooNotBetween(String value1, String value2) {
            addCriterion("yahoo not between", value1, value2, "yahoo");
            return (Criteria) this;
        }

        public Criteria andMsnIsNull() {
            addCriterion("msn is null");
            return (Criteria) this;
        }

        public Criteria andMsnIsNotNull() {
            addCriterion("msn is not null");
            return (Criteria) this;
        }

        public Criteria andMsnEqualTo(String value) {
            addCriterion("msn =", value, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnNotEqualTo(String value) {
            addCriterion("msn <>", value, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnGreaterThan(String value) {
            addCriterion("msn >", value, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnGreaterThanOrEqualTo(String value) {
            addCriterion("msn >=", value, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnLessThan(String value) {
            addCriterion("msn <", value, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnLessThanOrEqualTo(String value) {
            addCriterion("msn <=", value, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnLike(String value) {
            addCriterion("msn like", value, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnNotLike(String value) {
            addCriterion("msn not like", value, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnIn(List<String> values) {
            addCriterion("msn in", values, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnNotIn(List<String> values) {
            addCriterion("msn not in", values, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnBetween(String value1, String value2) {
            addCriterion("msn between", value1, value2, "msn");
            return (Criteria) this;
        }

        public Criteria andMsnNotBetween(String value1, String value2) {
            addCriterion("msn not between", value1, value2, "msn");
            return (Criteria) this;
        }

        public Criteria andTaobaoIsNull() {
            addCriterion("taobao is null");
            return (Criteria) this;
        }

        public Criteria andTaobaoIsNotNull() {
            addCriterion("taobao is not null");
            return (Criteria) this;
        }

        public Criteria andTaobaoEqualTo(String value) {
            addCriterion("taobao =", value, "taobao");
            return (Criteria) this;
        }

        public Criteria andTaobaoNotEqualTo(String value) {
            addCriterion("taobao <>", value, "taobao");
            return (Criteria) this;
        }

        public Criteria andTaobaoGreaterThan(String value) {
            addCriterion("taobao >", value, "taobao");
            return (Criteria) this;
        }

        public Criteria andTaobaoGreaterThanOrEqualTo(String value) {
            addCriterion("taobao >=", value, "taobao");
            return (Criteria) this;
        }

        public Criteria andTaobaoLessThan(String value) {
            addCriterion("taobao <", value, "taobao");
            return (Criteria) this;
        }

        public Criteria andTaobaoLessThanOrEqualTo(String value) {
            addCriterion("taobao <=", value, "taobao");
            return (Criteria) this;
        }

        public Criteria andTaobaoLike(String value) {
            addCriterion("taobao like", value, "taobao");
            return (Criteria) this;
        }

        public Criteria andTaobaoNotLike(String value) {
            addCriterion("taobao not like", value, "taobao");
            return (Criteria) this;
        }

        public Criteria andTaobaoIn(List<String> values) {
            addCriterion("taobao in", values, "taobao");
            return (Criteria) this;
        }

        public Criteria andTaobaoNotIn(List<String> values) {
            addCriterion("taobao not in", values, "taobao");
            return (Criteria) this;
        }

        public Criteria andTaobaoBetween(String value1, String value2) {
            addCriterion("taobao between", value1, value2, "taobao");
            return (Criteria) this;
        }

        public Criteria andTaobaoNotBetween(String value1, String value2) {
            addCriterion("taobao not between", value1, value2, "taobao");
            return (Criteria) this;
        }

        public Criteria andSiteIsNull() {
            addCriterion("site is null");
            return (Criteria) this;
        }

        public Criteria andSiteIsNotNull() {
            addCriterion("site is not null");
            return (Criteria) this;
        }

        public Criteria andSiteEqualTo(String value) {
            addCriterion("site =", value, "site");
            return (Criteria) this;
        }

        public Criteria andSiteNotEqualTo(String value) {
            addCriterion("site <>", value, "site");
            return (Criteria) this;
        }

        public Criteria andSiteGreaterThan(String value) {
            addCriterion("site >", value, "site");
            return (Criteria) this;
        }

        public Criteria andSiteGreaterThanOrEqualTo(String value) {
            addCriterion("site >=", value, "site");
            return (Criteria) this;
        }

        public Criteria andSiteLessThan(String value) {
            addCriterion("site <", value, "site");
            return (Criteria) this;
        }

        public Criteria andSiteLessThanOrEqualTo(String value) {
            addCriterion("site <=", value, "site");
            return (Criteria) this;
        }

        public Criteria andSiteLike(String value) {
            addCriterion("site like", value, "site");
            return (Criteria) this;
        }

        public Criteria andSiteNotLike(String value) {
            addCriterion("site not like", value, "site");
            return (Criteria) this;
        }

        public Criteria andSiteIn(List<String> values) {
            addCriterion("site in", values, "site");
            return (Criteria) this;
        }

        public Criteria andSiteNotIn(List<String> values) {
            addCriterion("site not in", values, "site");
            return (Criteria) this;
        }

        public Criteria andSiteBetween(String value1, String value2) {
            addCriterion("site between", value1, value2, "site");
            return (Criteria) this;
        }

        public Criteria andSiteNotBetween(String value1, String value2) {
            addCriterion("site not between", value1, value2, "site");
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