package sjjd.doc.line.pojo;

import java.util.ArrayList;
import java.util.List;

public class RoleExample {
    /**
     * role
     */
    protected String orderByClause;

    /**
     * role
     */
    protected boolean distinct;

    /**
     * role
     */
    protected List<Criteria> oredCriteria;

    public RoleExample() {
        oredCriteria = new ArrayList<Criteria>();
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

    /**
     * role
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNull() {
            addCriterion("role_name is null");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNotNull() {
            addCriterion("role_name is not null");
            return (Criteria) this;
        }

        public Criteria andRoleNameEqualTo(String value) {
            addCriterion("role_name =", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotEqualTo(String value) {
            addCriterion("role_name <>", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThan(String value) {
            addCriterion("role_name >", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("role_name >=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThan(String value) {
            addCriterion("role_name <", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThanOrEqualTo(String value) {
            addCriterion("role_name <=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLike(String value) {
            addCriterion("role_name like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotLike(String value) {
            addCriterion("role_name not like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameIn(List<String> values) {
            addCriterion("role_name in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotIn(List<String> values) {
            addCriterion("role_name not in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameBetween(String value1, String value2) {
            addCriterion("role_name between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotBetween(String value1, String value2) {
            addCriterion("role_name not between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoPermissionIsNull() {
            addCriterion("ro_permission is null");
            return (Criteria) this;
        }

        public Criteria andRoPermissionIsNotNull() {
            addCriterion("ro_permission is not null");
            return (Criteria) this;
        }

        public Criteria andRoPermissionEqualTo(String value) {
            addCriterion("ro_permission =", value, "roPermission");
            return (Criteria) this;
        }

        public Criteria andRoPermissionNotEqualTo(String value) {
            addCriterion("ro_permission <>", value, "roPermission");
            return (Criteria) this;
        }

        public Criteria andRoPermissionGreaterThan(String value) {
            addCriterion("ro_permission >", value, "roPermission");
            return (Criteria) this;
        }

        public Criteria andRoPermissionGreaterThanOrEqualTo(String value) {
            addCriterion("ro_permission >=", value, "roPermission");
            return (Criteria) this;
        }

        public Criteria andRoPermissionLessThan(String value) {
            addCriterion("ro_permission <", value, "roPermission");
            return (Criteria) this;
        }

        public Criteria andRoPermissionLessThanOrEqualTo(String value) {
            addCriterion("ro_permission <=", value, "roPermission");
            return (Criteria) this;
        }

        public Criteria andRoPermissionLike(String value) {
            addCriterion("ro_permission like", value, "roPermission");
            return (Criteria) this;
        }

        public Criteria andRoPermissionNotLike(String value) {
            addCriterion("ro_permission not like", value, "roPermission");
            return (Criteria) this;
        }

        public Criteria andRoPermissionIn(List<String> values) {
            addCriterion("ro_permission in", values, "roPermission");
            return (Criteria) this;
        }

        public Criteria andRoPermissionNotIn(List<String> values) {
            addCriterion("ro_permission not in", values, "roPermission");
            return (Criteria) this;
        }

        public Criteria andRoPermissionBetween(String value1, String value2) {
            addCriterion("ro_permission between", value1, value2, "roPermission");
            return (Criteria) this;
        }

        public Criteria andRoPermissionNotBetween(String value1, String value2) {
            addCriterion("ro_permission not between", value1, value2, "roPermission");
            return (Criteria) this;
        }

        public Criteria andRoExplanIsNull() {
            addCriterion("ro_explan is null");
            return (Criteria) this;
        }

        public Criteria andRoExplanIsNotNull() {
            addCriterion("ro_explan is not null");
            return (Criteria) this;
        }

        public Criteria andRoExplanEqualTo(String value) {
            addCriterion("ro_explan =", value, "roExplan");
            return (Criteria) this;
        }

        public Criteria andRoExplanNotEqualTo(String value) {
            addCriterion("ro_explan <>", value, "roExplan");
            return (Criteria) this;
        }

        public Criteria andRoExplanGreaterThan(String value) {
            addCriterion("ro_explan >", value, "roExplan");
            return (Criteria) this;
        }

        public Criteria andRoExplanGreaterThanOrEqualTo(String value) {
            addCriterion("ro_explan >=", value, "roExplan");
            return (Criteria) this;
        }

        public Criteria andRoExplanLessThan(String value) {
            addCriterion("ro_explan <", value, "roExplan");
            return (Criteria) this;
        }

        public Criteria andRoExplanLessThanOrEqualTo(String value) {
            addCriterion("ro_explan <=", value, "roExplan");
            return (Criteria) this;
        }

        public Criteria andRoExplanLike(String value) {
            addCriterion("ro_explan like", value, "roExplan");
            return (Criteria) this;
        }

        public Criteria andRoExplanNotLike(String value) {
            addCriterion("ro_explan not like", value, "roExplan");
            return (Criteria) this;
        }

        public Criteria andRoExplanIn(List<String> values) {
            addCriterion("ro_explan in", values, "roExplan");
            return (Criteria) this;
        }

        public Criteria andRoExplanNotIn(List<String> values) {
            addCriterion("ro_explan not in", values, "roExplan");
            return (Criteria) this;
        }

        public Criteria andRoExplanBetween(String value1, String value2) {
            addCriterion("ro_explan between", value1, value2, "roExplan");
            return (Criteria) this;
        }

        public Criteria andRoExplanNotBetween(String value1, String value2) {
            addCriterion("ro_explan not between", value1, value2, "roExplan");
            return (Criteria) this;
        }

        public Criteria andRoUpperIsNull() {
            addCriterion("ro_upper is null");
            return (Criteria) this;
        }

        public Criteria andRoUpperIsNotNull() {
            addCriterion("ro_upper is not null");
            return (Criteria) this;
        }

        public Criteria andRoUpperEqualTo(String value) {
            addCriterion("ro_upper =", value, "roUpper");
            return (Criteria) this;
        }

        public Criteria andRoUpperNotEqualTo(String value) {
            addCriterion("ro_upper <>", value, "roUpper");
            return (Criteria) this;
        }

        public Criteria andRoUpperGreaterThan(String value) {
            addCriterion("ro_upper >", value, "roUpper");
            return (Criteria) this;
        }

        public Criteria andRoUpperGreaterThanOrEqualTo(String value) {
            addCriterion("ro_upper >=", value, "roUpper");
            return (Criteria) this;
        }

        public Criteria andRoUpperLessThan(String value) {
            addCriterion("ro_upper <", value, "roUpper");
            return (Criteria) this;
        }

        public Criteria andRoUpperLessThanOrEqualTo(String value) {
            addCriterion("ro_upper <=", value, "roUpper");
            return (Criteria) this;
        }

        public Criteria andRoUpperLike(String value) {
            addCriterion("ro_upper like", value, "roUpper");
            return (Criteria) this;
        }

        public Criteria andRoUpperNotLike(String value) {
            addCriterion("ro_upper not like", value, "roUpper");
            return (Criteria) this;
        }

        public Criteria andRoUpperIn(List<String> values) {
            addCriterion("ro_upper in", values, "roUpper");
            return (Criteria) this;
        }

        public Criteria andRoUpperNotIn(List<String> values) {
            addCriterion("ro_upper not in", values, "roUpper");
            return (Criteria) this;
        }

        public Criteria andRoUpperBetween(String value1, String value2) {
            addCriterion("ro_upper between", value1, value2, "roUpper");
            return (Criteria) this;
        }

        public Criteria andRoUpperNotBetween(String value1, String value2) {
            addCriterion("ro_upper not between", value1, value2, "roUpper");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * role
     */
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