package net.renfei.repositories.model;

import java.util.ArrayList;
import java.util.List;

public class KitboxIcpCacheExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public KitboxIcpCacheExample() {
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

        public Criteria andContentTypeNameIsNull() {
            addCriterion("`content_type_name` is null");
            return (Criteria) this;
        }

        public Criteria andContentTypeNameIsNotNull() {
            addCriterion("`content_type_name` is not null");
            return (Criteria) this;
        }

        public Criteria andContentTypeNameEqualTo(String value) {
            addCriterion("`content_type_name` =", value, "contentTypeName");
            return (Criteria) this;
        }

        public Criteria andContentTypeNameNotEqualTo(String value) {
            addCriterion("`content_type_name` <>", value, "contentTypeName");
            return (Criteria) this;
        }

        public Criteria andContentTypeNameGreaterThan(String value) {
            addCriterion("`content_type_name` >", value, "contentTypeName");
            return (Criteria) this;
        }

        public Criteria andContentTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("`content_type_name` >=", value, "contentTypeName");
            return (Criteria) this;
        }

        public Criteria andContentTypeNameLessThan(String value) {
            addCriterion("`content_type_name` <", value, "contentTypeName");
            return (Criteria) this;
        }

        public Criteria andContentTypeNameLessThanOrEqualTo(String value) {
            addCriterion("`content_type_name` <=", value, "contentTypeName");
            return (Criteria) this;
        }

        public Criteria andContentTypeNameLike(String value) {
            addCriterion("`content_type_name` like", value, "contentTypeName");
            return (Criteria) this;
        }

        public Criteria andContentTypeNameNotLike(String value) {
            addCriterion("`content_type_name` not like", value, "contentTypeName");
            return (Criteria) this;
        }

        public Criteria andContentTypeNameIn(List<String> values) {
            addCriterion("`content_type_name` in", values, "contentTypeName");
            return (Criteria) this;
        }

        public Criteria andContentTypeNameNotIn(List<String> values) {
            addCriterion("`content_type_name` not in", values, "contentTypeName");
            return (Criteria) this;
        }

        public Criteria andContentTypeNameBetween(String value1, String value2) {
            addCriterion("`content_type_name` between", value1, value2, "contentTypeName");
            return (Criteria) this;
        }

        public Criteria andContentTypeNameNotBetween(String value1, String value2) {
            addCriterion("`content_type_name` not between", value1, value2, "contentTypeName");
            return (Criteria) this;
        }

        public Criteria andDomainIsNull() {
            addCriterion("`domain` is null");
            return (Criteria) this;
        }

        public Criteria andDomainIsNotNull() {
            addCriterion("`domain` is not null");
            return (Criteria) this;
        }

        public Criteria andDomainEqualTo(String value) {
            addCriterion("`domain` =", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotEqualTo(String value) {
            addCriterion("`domain` <>", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainGreaterThan(String value) {
            addCriterion("`domain` >", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainGreaterThanOrEqualTo(String value) {
            addCriterion("`domain` >=", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLessThan(String value) {
            addCriterion("`domain` <", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLessThanOrEqualTo(String value) {
            addCriterion("`domain` <=", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLike(String value) {
            addCriterion("`domain` like", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotLike(String value) {
            addCriterion("`domain` not like", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainIn(List<String> values) {
            addCriterion("`domain` in", values, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotIn(List<String> values) {
            addCriterion("`domain` not in", values, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainBetween(String value1, String value2) {
            addCriterion("`domain` between", value1, value2, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotBetween(String value1, String value2) {
            addCriterion("`domain` not between", value1, value2, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainIdIsNull() {
            addCriterion("`domain_id` is null");
            return (Criteria) this;
        }

        public Criteria andDomainIdIsNotNull() {
            addCriterion("`domain_id` is not null");
            return (Criteria) this;
        }

        public Criteria andDomainIdEqualTo(Long value) {
            addCriterion("`domain_id` =", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdNotEqualTo(Long value) {
            addCriterion("`domain_id` <>", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdGreaterThan(Long value) {
            addCriterion("`domain_id` >", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`domain_id` >=", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdLessThan(Long value) {
            addCriterion("`domain_id` <", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdLessThanOrEqualTo(Long value) {
            addCriterion("`domain_id` <=", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdIn(List<Long> values) {
            addCriterion("`domain_id` in", values, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdNotIn(List<Long> values) {
            addCriterion("`domain_id` not in", values, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdBetween(Long value1, Long value2) {
            addCriterion("`domain_id` between", value1, value2, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdNotBetween(Long value1, Long value2) {
            addCriterion("`domain_id` not between", value1, value2, "domainId");
            return (Criteria) this;
        }

        public Criteria andLeaderNameIsNull() {
            addCriterion("`leader_name` is null");
            return (Criteria) this;
        }

        public Criteria andLeaderNameIsNotNull() {
            addCriterion("`leader_name` is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderNameEqualTo(String value) {
            addCriterion("`leader_name` =", value, "leaderName");
            return (Criteria) this;
        }

        public Criteria andLeaderNameNotEqualTo(String value) {
            addCriterion("`leader_name` <>", value, "leaderName");
            return (Criteria) this;
        }

        public Criteria andLeaderNameGreaterThan(String value) {
            addCriterion("`leader_name` >", value, "leaderName");
            return (Criteria) this;
        }

        public Criteria andLeaderNameGreaterThanOrEqualTo(String value) {
            addCriterion("`leader_name` >=", value, "leaderName");
            return (Criteria) this;
        }

        public Criteria andLeaderNameLessThan(String value) {
            addCriterion("`leader_name` <", value, "leaderName");
            return (Criteria) this;
        }

        public Criteria andLeaderNameLessThanOrEqualTo(String value) {
            addCriterion("`leader_name` <=", value, "leaderName");
            return (Criteria) this;
        }

        public Criteria andLeaderNameLike(String value) {
            addCriterion("`leader_name` like", value, "leaderName");
            return (Criteria) this;
        }

        public Criteria andLeaderNameNotLike(String value) {
            addCriterion("`leader_name` not like", value, "leaderName");
            return (Criteria) this;
        }

        public Criteria andLeaderNameIn(List<String> values) {
            addCriterion("`leader_name` in", values, "leaderName");
            return (Criteria) this;
        }

        public Criteria andLeaderNameNotIn(List<String> values) {
            addCriterion("`leader_name` not in", values, "leaderName");
            return (Criteria) this;
        }

        public Criteria andLeaderNameBetween(String value1, String value2) {
            addCriterion("`leader_name` between", value1, value2, "leaderName");
            return (Criteria) this;
        }

        public Criteria andLeaderNameNotBetween(String value1, String value2) {
            addCriterion("`leader_name` not between", value1, value2, "leaderName");
            return (Criteria) this;
        }

        public Criteria andLimitAccessIsNull() {
            addCriterion("`limit_access` is null");
            return (Criteria) this;
        }

        public Criteria andLimitAccessIsNotNull() {
            addCriterion("`limit_access` is not null");
            return (Criteria) this;
        }

        public Criteria andLimitAccessEqualTo(String value) {
            addCriterion("`limit_access` =", value, "limitAccess");
            return (Criteria) this;
        }

        public Criteria andLimitAccessNotEqualTo(String value) {
            addCriterion("`limit_access` <>", value, "limitAccess");
            return (Criteria) this;
        }

        public Criteria andLimitAccessGreaterThan(String value) {
            addCriterion("`limit_access` >", value, "limitAccess");
            return (Criteria) this;
        }

        public Criteria andLimitAccessGreaterThanOrEqualTo(String value) {
            addCriterion("`limit_access` >=", value, "limitAccess");
            return (Criteria) this;
        }

        public Criteria andLimitAccessLessThan(String value) {
            addCriterion("`limit_access` <", value, "limitAccess");
            return (Criteria) this;
        }

        public Criteria andLimitAccessLessThanOrEqualTo(String value) {
            addCriterion("`limit_access` <=", value, "limitAccess");
            return (Criteria) this;
        }

        public Criteria andLimitAccessLike(String value) {
            addCriterion("`limit_access` like", value, "limitAccess");
            return (Criteria) this;
        }

        public Criteria andLimitAccessNotLike(String value) {
            addCriterion("`limit_access` not like", value, "limitAccess");
            return (Criteria) this;
        }

        public Criteria andLimitAccessIn(List<String> values) {
            addCriterion("`limit_access` in", values, "limitAccess");
            return (Criteria) this;
        }

        public Criteria andLimitAccessNotIn(List<String> values) {
            addCriterion("`limit_access` not in", values, "limitAccess");
            return (Criteria) this;
        }

        public Criteria andLimitAccessBetween(String value1, String value2) {
            addCriterion("`limit_access` between", value1, value2, "limitAccess");
            return (Criteria) this;
        }

        public Criteria andLimitAccessNotBetween(String value1, String value2) {
            addCriterion("`limit_access` not between", value1, value2, "limitAccess");
            return (Criteria) this;
        }

        public Criteria andMainIdIsNull() {
            addCriterion("`main_id` is null");
            return (Criteria) this;
        }

        public Criteria andMainIdIsNotNull() {
            addCriterion("`main_id` is not null");
            return (Criteria) this;
        }

        public Criteria andMainIdEqualTo(Long value) {
            addCriterion("`main_id` =", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdNotEqualTo(Long value) {
            addCriterion("`main_id` <>", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdGreaterThan(Long value) {
            addCriterion("`main_id` >", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`main_id` >=", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdLessThan(Long value) {
            addCriterion("`main_id` <", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdLessThanOrEqualTo(Long value) {
            addCriterion("`main_id` <=", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdIn(List<Long> values) {
            addCriterion("`main_id` in", values, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdNotIn(List<Long> values) {
            addCriterion("`main_id` not in", values, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdBetween(Long value1, Long value2) {
            addCriterion("`main_id` between", value1, value2, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdNotBetween(Long value1, Long value2) {
            addCriterion("`main_id` not between", value1, value2, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainLicenceIsNull() {
            addCriterion("`main_licence` is null");
            return (Criteria) this;
        }

        public Criteria andMainLicenceIsNotNull() {
            addCriterion("`main_licence` is not null");
            return (Criteria) this;
        }

        public Criteria andMainLicenceEqualTo(String value) {
            addCriterion("`main_licence` =", value, "mainLicence");
            return (Criteria) this;
        }

        public Criteria andMainLicenceNotEqualTo(String value) {
            addCriterion("`main_licence` <>", value, "mainLicence");
            return (Criteria) this;
        }

        public Criteria andMainLicenceGreaterThan(String value) {
            addCriterion("`main_licence` >", value, "mainLicence");
            return (Criteria) this;
        }

        public Criteria andMainLicenceGreaterThanOrEqualTo(String value) {
            addCriterion("`main_licence` >=", value, "mainLicence");
            return (Criteria) this;
        }

        public Criteria andMainLicenceLessThan(String value) {
            addCriterion("`main_licence` <", value, "mainLicence");
            return (Criteria) this;
        }

        public Criteria andMainLicenceLessThanOrEqualTo(String value) {
            addCriterion("`main_licence` <=", value, "mainLicence");
            return (Criteria) this;
        }

        public Criteria andMainLicenceLike(String value) {
            addCriterion("`main_licence` like", value, "mainLicence");
            return (Criteria) this;
        }

        public Criteria andMainLicenceNotLike(String value) {
            addCriterion("`main_licence` not like", value, "mainLicence");
            return (Criteria) this;
        }

        public Criteria andMainLicenceIn(List<String> values) {
            addCriterion("`main_licence` in", values, "mainLicence");
            return (Criteria) this;
        }

        public Criteria andMainLicenceNotIn(List<String> values) {
            addCriterion("`main_licence` not in", values, "mainLicence");
            return (Criteria) this;
        }

        public Criteria andMainLicenceBetween(String value1, String value2) {
            addCriterion("`main_licence` between", value1, value2, "mainLicence");
            return (Criteria) this;
        }

        public Criteria andMainLicenceNotBetween(String value1, String value2) {
            addCriterion("`main_licence` not between", value1, value2, "mainLicence");
            return (Criteria) this;
        }

        public Criteria andNatureNameIsNull() {
            addCriterion("`nature_name` is null");
            return (Criteria) this;
        }

        public Criteria andNatureNameIsNotNull() {
            addCriterion("`nature_name` is not null");
            return (Criteria) this;
        }

        public Criteria andNatureNameEqualTo(String value) {
            addCriterion("`nature_name` =", value, "natureName");
            return (Criteria) this;
        }

        public Criteria andNatureNameNotEqualTo(String value) {
            addCriterion("`nature_name` <>", value, "natureName");
            return (Criteria) this;
        }

        public Criteria andNatureNameGreaterThan(String value) {
            addCriterion("`nature_name` >", value, "natureName");
            return (Criteria) this;
        }

        public Criteria andNatureNameGreaterThanOrEqualTo(String value) {
            addCriterion("`nature_name` >=", value, "natureName");
            return (Criteria) this;
        }

        public Criteria andNatureNameLessThan(String value) {
            addCriterion("`nature_name` <", value, "natureName");
            return (Criteria) this;
        }

        public Criteria andNatureNameLessThanOrEqualTo(String value) {
            addCriterion("`nature_name` <=", value, "natureName");
            return (Criteria) this;
        }

        public Criteria andNatureNameLike(String value) {
            addCriterion("`nature_name` like", value, "natureName");
            return (Criteria) this;
        }

        public Criteria andNatureNameNotLike(String value) {
            addCriterion("`nature_name` not like", value, "natureName");
            return (Criteria) this;
        }

        public Criteria andNatureNameIn(List<String> values) {
            addCriterion("`nature_name` in", values, "natureName");
            return (Criteria) this;
        }

        public Criteria andNatureNameNotIn(List<String> values) {
            addCriterion("`nature_name` not in", values, "natureName");
            return (Criteria) this;
        }

        public Criteria andNatureNameBetween(String value1, String value2) {
            addCriterion("`nature_name` between", value1, value2, "natureName");
            return (Criteria) this;
        }

        public Criteria andNatureNameNotBetween(String value1, String value2) {
            addCriterion("`nature_name` not between", value1, value2, "natureName");
            return (Criteria) this;
        }

        public Criteria andServiceIdIsNull() {
            addCriterion("`service_id` is null");
            return (Criteria) this;
        }

        public Criteria andServiceIdIsNotNull() {
            addCriterion("`service_id` is not null");
            return (Criteria) this;
        }

        public Criteria andServiceIdEqualTo(Long value) {
            addCriterion("`service_id` =", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotEqualTo(Long value) {
            addCriterion("`service_id` <>", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdGreaterThan(Long value) {
            addCriterion("`service_id` >", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`service_id` >=", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLessThan(Long value) {
            addCriterion("`service_id` <", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLessThanOrEqualTo(Long value) {
            addCriterion("`service_id` <=", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdIn(List<Long> values) {
            addCriterion("`service_id` in", values, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotIn(List<Long> values) {
            addCriterion("`service_id` not in", values, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdBetween(Long value1, Long value2) {
            addCriterion("`service_id` between", value1, value2, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotBetween(Long value1, Long value2) {
            addCriterion("`service_id` not between", value1, value2, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceLicenceIsNull() {
            addCriterion("`service_licence` is null");
            return (Criteria) this;
        }

        public Criteria andServiceLicenceIsNotNull() {
            addCriterion("`service_licence` is not null");
            return (Criteria) this;
        }

        public Criteria andServiceLicenceEqualTo(String value) {
            addCriterion("`service_licence` =", value, "serviceLicence");
            return (Criteria) this;
        }

        public Criteria andServiceLicenceNotEqualTo(String value) {
            addCriterion("`service_licence` <>", value, "serviceLicence");
            return (Criteria) this;
        }

        public Criteria andServiceLicenceGreaterThan(String value) {
            addCriterion("`service_licence` >", value, "serviceLicence");
            return (Criteria) this;
        }

        public Criteria andServiceLicenceGreaterThanOrEqualTo(String value) {
            addCriterion("`service_licence` >=", value, "serviceLicence");
            return (Criteria) this;
        }

        public Criteria andServiceLicenceLessThan(String value) {
            addCriterion("`service_licence` <", value, "serviceLicence");
            return (Criteria) this;
        }

        public Criteria andServiceLicenceLessThanOrEqualTo(String value) {
            addCriterion("`service_licence` <=", value, "serviceLicence");
            return (Criteria) this;
        }

        public Criteria andServiceLicenceLike(String value) {
            addCriterion("`service_licence` like", value, "serviceLicence");
            return (Criteria) this;
        }

        public Criteria andServiceLicenceNotLike(String value) {
            addCriterion("`service_licence` not like", value, "serviceLicence");
            return (Criteria) this;
        }

        public Criteria andServiceLicenceIn(List<String> values) {
            addCriterion("`service_licence` in", values, "serviceLicence");
            return (Criteria) this;
        }

        public Criteria andServiceLicenceNotIn(List<String> values) {
            addCriterion("`service_licence` not in", values, "serviceLicence");
            return (Criteria) this;
        }

        public Criteria andServiceLicenceBetween(String value1, String value2) {
            addCriterion("`service_licence` between", value1, value2, "serviceLicence");
            return (Criteria) this;
        }

        public Criteria andServiceLicenceNotBetween(String value1, String value2) {
            addCriterion("`service_licence` not between", value1, value2, "serviceLicence");
            return (Criteria) this;
        }

        public Criteria andUnitNameIsNull() {
            addCriterion("`unit_name` is null");
            return (Criteria) this;
        }

        public Criteria andUnitNameIsNotNull() {
            addCriterion("`unit_name` is not null");
            return (Criteria) this;
        }

        public Criteria andUnitNameEqualTo(String value) {
            addCriterion("`unit_name` =", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotEqualTo(String value) {
            addCriterion("`unit_name` <>", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameGreaterThan(String value) {
            addCriterion("`unit_name` >", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameGreaterThanOrEqualTo(String value) {
            addCriterion("`unit_name` >=", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameLessThan(String value) {
            addCriterion("`unit_name` <", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameLessThanOrEqualTo(String value) {
            addCriterion("`unit_name` <=", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameLike(String value) {
            addCriterion("`unit_name` like", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotLike(String value) {
            addCriterion("`unit_name` not like", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameIn(List<String> values) {
            addCriterion("`unit_name` in", values, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotIn(List<String> values) {
            addCriterion("`unit_name` not in", values, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameBetween(String value1, String value2) {
            addCriterion("`unit_name` between", value1, value2, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotBetween(String value1, String value2) {
            addCriterion("`unit_name` not between", value1, value2, "unitName");
            return (Criteria) this;
        }

        public Criteria andUpdateRecordTimeIsNull() {
            addCriterion("`update_record_time` is null");
            return (Criteria) this;
        }

        public Criteria andUpdateRecordTimeIsNotNull() {
            addCriterion("`update_record_time` is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateRecordTimeEqualTo(String value) {
            addCriterion("`update_record_time` =", value, "updateRecordTime");
            return (Criteria) this;
        }

        public Criteria andUpdateRecordTimeNotEqualTo(String value) {
            addCriterion("`update_record_time` <>", value, "updateRecordTime");
            return (Criteria) this;
        }

        public Criteria andUpdateRecordTimeGreaterThan(String value) {
            addCriterion("`update_record_time` >", value, "updateRecordTime");
            return (Criteria) this;
        }

        public Criteria andUpdateRecordTimeGreaterThanOrEqualTo(String value) {
            addCriterion("`update_record_time` >=", value, "updateRecordTime");
            return (Criteria) this;
        }

        public Criteria andUpdateRecordTimeLessThan(String value) {
            addCriterion("`update_record_time` <", value, "updateRecordTime");
            return (Criteria) this;
        }

        public Criteria andUpdateRecordTimeLessThanOrEqualTo(String value) {
            addCriterion("`update_record_time` <=", value, "updateRecordTime");
            return (Criteria) this;
        }

        public Criteria andUpdateRecordTimeLike(String value) {
            addCriterion("`update_record_time` like", value, "updateRecordTime");
            return (Criteria) this;
        }

        public Criteria andUpdateRecordTimeNotLike(String value) {
            addCriterion("`update_record_time` not like", value, "updateRecordTime");
            return (Criteria) this;
        }

        public Criteria andUpdateRecordTimeIn(List<String> values) {
            addCriterion("`update_record_time` in", values, "updateRecordTime");
            return (Criteria) this;
        }

        public Criteria andUpdateRecordTimeNotIn(List<String> values) {
            addCriterion("`update_record_time` not in", values, "updateRecordTime");
            return (Criteria) this;
        }

        public Criteria andUpdateRecordTimeBetween(String value1, String value2) {
            addCriterion("`update_record_time` between", value1, value2, "updateRecordTime");
            return (Criteria) this;
        }

        public Criteria andUpdateRecordTimeNotBetween(String value1, String value2) {
            addCriterion("`update_record_time` not between", value1, value2, "updateRecordTime");
            return (Criteria) this;
        }

        public Criteria andCacheTimeIsNull() {
            addCriterion("`cache_time` is null");
            return (Criteria) this;
        }

        public Criteria andCacheTimeIsNotNull() {
            addCriterion("`cache_time` is not null");
            return (Criteria) this;
        }

        public Criteria andCacheTimeEqualTo(String value) {
            addCriterion("`cache_time` =", value, "cacheTime");
            return (Criteria) this;
        }

        public Criteria andCacheTimeNotEqualTo(String value) {
            addCriterion("`cache_time` <>", value, "cacheTime");
            return (Criteria) this;
        }

        public Criteria andCacheTimeGreaterThan(String value) {
            addCriterion("`cache_time` >", value, "cacheTime");
            return (Criteria) this;
        }

        public Criteria andCacheTimeGreaterThanOrEqualTo(String value) {
            addCriterion("`cache_time` >=", value, "cacheTime");
            return (Criteria) this;
        }

        public Criteria andCacheTimeLessThan(String value) {
            addCriterion("`cache_time` <", value, "cacheTime");
            return (Criteria) this;
        }

        public Criteria andCacheTimeLessThanOrEqualTo(String value) {
            addCriterion("`cache_time` <=", value, "cacheTime");
            return (Criteria) this;
        }

        public Criteria andCacheTimeLike(String value) {
            addCriterion("`cache_time` like", value, "cacheTime");
            return (Criteria) this;
        }

        public Criteria andCacheTimeNotLike(String value) {
            addCriterion("`cache_time` not like", value, "cacheTime");
            return (Criteria) this;
        }

        public Criteria andCacheTimeIn(List<String> values) {
            addCriterion("`cache_time` in", values, "cacheTime");
            return (Criteria) this;
        }

        public Criteria andCacheTimeNotIn(List<String> values) {
            addCriterion("`cache_time` not in", values, "cacheTime");
            return (Criteria) this;
        }

        public Criteria andCacheTimeBetween(String value1, String value2) {
            addCriterion("`cache_time` between", value1, value2, "cacheTime");
            return (Criteria) this;
        }

        public Criteria andCacheTimeNotBetween(String value1, String value2) {
            addCriterion("`cache_time` not between", value1, value2, "cacheTime");
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