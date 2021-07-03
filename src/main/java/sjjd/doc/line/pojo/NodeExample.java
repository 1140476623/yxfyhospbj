package sjjd.doc.line.pojo;

import java.util.ArrayList;
import java.util.List;

public class NodeExample {
    /**
     * node
     */
    protected String orderByClause;

    /**
     * node
     */
    protected boolean distinct;

    /**
     * node
     */
    protected List<Criteria> oredCriteria;

    public NodeExample() {
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
     * node
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

        public Criteria andNodeNameIsNull() {
            addCriterion("node_name is null");
            return (Criteria) this;
        }

        public Criteria andNodeNameIsNotNull() {
            addCriterion("node_name is not null");
            return (Criteria) this;
        }

        public Criteria andNodeNameEqualTo(String value) {
            addCriterion("node_name =", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotEqualTo(String value) {
            addCriterion("node_name <>", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameGreaterThan(String value) {
            addCriterion("node_name >", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameGreaterThanOrEqualTo(String value) {
            addCriterion("node_name >=", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLessThan(String value) {
            addCriterion("node_name <", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLessThanOrEqualTo(String value) {
            addCriterion("node_name <=", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLike(String value) {
            addCriterion("node_name like", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotLike(String value) {
            addCriterion("node_name not like", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameIn(List<String> values) {
            addCriterion("node_name in", values, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotIn(List<String> values) {
            addCriterion("node_name not in", values, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameBetween(String value1, String value2) {
            addCriterion("node_name between", value1, value2, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotBetween(String value1, String value2) {
            addCriterion("node_name not between", value1, value2, "nodeName");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("parent_id like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("parent_id not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andNodeLinkIsNull() {
            addCriterion("node_link is null");
            return (Criteria) this;
        }

        public Criteria andNodeLinkIsNotNull() {
            addCriterion("node_link is not null");
            return (Criteria) this;
        }

        public Criteria andNodeLinkEqualTo(String value) {
            addCriterion("node_link =", value, "nodeLink");
            return (Criteria) this;
        }

        public Criteria andNodeLinkNotEqualTo(String value) {
            addCriterion("node_link <>", value, "nodeLink");
            return (Criteria) this;
        }

        public Criteria andNodeLinkGreaterThan(String value) {
            addCriterion("node_link >", value, "nodeLink");
            return (Criteria) this;
        }

        public Criteria andNodeLinkGreaterThanOrEqualTo(String value) {
            addCriterion("node_link >=", value, "nodeLink");
            return (Criteria) this;
        }

        public Criteria andNodeLinkLessThan(String value) {
            addCriterion("node_link <", value, "nodeLink");
            return (Criteria) this;
        }

        public Criteria andNodeLinkLessThanOrEqualTo(String value) {
            addCriterion("node_link <=", value, "nodeLink");
            return (Criteria) this;
        }

        public Criteria andNodeLinkLike(String value) {
            addCriterion("node_link like", value, "nodeLink");
            return (Criteria) this;
        }

        public Criteria andNodeLinkNotLike(String value) {
            addCriterion("node_link not like", value, "nodeLink");
            return (Criteria) this;
        }

        public Criteria andNodeLinkIn(List<String> values) {
            addCriterion("node_link in", values, "nodeLink");
            return (Criteria) this;
        }

        public Criteria andNodeLinkNotIn(List<String> values) {
            addCriterion("node_link not in", values, "nodeLink");
            return (Criteria) this;
        }

        public Criteria andNodeLinkBetween(String value1, String value2) {
            addCriterion("node_link between", value1, value2, "nodeLink");
            return (Criteria) this;
        }

        public Criteria andNodeLinkNotBetween(String value1, String value2) {
            addCriterion("node_link not between", value1, value2, "nodeLink");
            return (Criteria) this;
        }

        public Criteria andNodePagelogoIsNull() {
            addCriterion("node_pagelogo is null");
            return (Criteria) this;
        }

        public Criteria andNodePagelogoIsNotNull() {
            addCriterion("node_pagelogo is not null");
            return (Criteria) this;
        }

        public Criteria andNodePagelogoEqualTo(String value) {
            addCriterion("node_pagelogo =", value, "nodePagelogo");
            return (Criteria) this;
        }

        public Criteria andNodePagelogoNotEqualTo(String value) {
            addCriterion("node_pagelogo <>", value, "nodePagelogo");
            return (Criteria) this;
        }

        public Criteria andNodePagelogoGreaterThan(String value) {
            addCriterion("node_pagelogo >", value, "nodePagelogo");
            return (Criteria) this;
        }

        public Criteria andNodePagelogoGreaterThanOrEqualTo(String value) {
            addCriterion("node_pagelogo >=", value, "nodePagelogo");
            return (Criteria) this;
        }

        public Criteria andNodePagelogoLessThan(String value) {
            addCriterion("node_pagelogo <", value, "nodePagelogo");
            return (Criteria) this;
        }

        public Criteria andNodePagelogoLessThanOrEqualTo(String value) {
            addCriterion("node_pagelogo <=", value, "nodePagelogo");
            return (Criteria) this;
        }

        public Criteria andNodePagelogoLike(String value) {
            addCriterion("node_pagelogo like", value, "nodePagelogo");
            return (Criteria) this;
        }

        public Criteria andNodePagelogoNotLike(String value) {
            addCriterion("node_pagelogo not like", value, "nodePagelogo");
            return (Criteria) this;
        }

        public Criteria andNodePagelogoIn(List<String> values) {
            addCriterion("node_pagelogo in", values, "nodePagelogo");
            return (Criteria) this;
        }

        public Criteria andNodePagelogoNotIn(List<String> values) {
            addCriterion("node_pagelogo not in", values, "nodePagelogo");
            return (Criteria) this;
        }

        public Criteria andNodePagelogoBetween(String value1, String value2) {
            addCriterion("node_pagelogo between", value1, value2, "nodePagelogo");
            return (Criteria) this;
        }

        public Criteria andNodePagelogoNotBetween(String value1, String value2) {
            addCriterion("node_pagelogo not between", value1, value2, "nodePagelogo");
            return (Criteria) this;
        }

        public Criteria andNodeIconIsNull() {
            addCriterion("node_icon is null");
            return (Criteria) this;
        }

        public Criteria andNodeIconIsNotNull() {
            addCriterion("node_icon is not null");
            return (Criteria) this;
        }

        public Criteria andNodeIconEqualTo(String value) {
            addCriterion("node_icon =", value, "nodeIcon");
            return (Criteria) this;
        }

        public Criteria andNodeIconNotEqualTo(String value) {
            addCriterion("node_icon <>", value, "nodeIcon");
            return (Criteria) this;
        }

        public Criteria andNodeIconGreaterThan(String value) {
            addCriterion("node_icon >", value, "nodeIcon");
            return (Criteria) this;
        }

        public Criteria andNodeIconGreaterThanOrEqualTo(String value) {
            addCriterion("node_icon >=", value, "nodeIcon");
            return (Criteria) this;
        }

        public Criteria andNodeIconLessThan(String value) {
            addCriterion("node_icon <", value, "nodeIcon");
            return (Criteria) this;
        }

        public Criteria andNodeIconLessThanOrEqualTo(String value) {
            addCriterion("node_icon <=", value, "nodeIcon");
            return (Criteria) this;
        }

        public Criteria andNodeIconLike(String value) {
            addCriterion("node_icon like", value, "nodeIcon");
            return (Criteria) this;
        }

        public Criteria andNodeIconNotLike(String value) {
            addCriterion("node_icon not like", value, "nodeIcon");
            return (Criteria) this;
        }

        public Criteria andNodeIconIn(List<String> values) {
            addCriterion("node_icon in", values, "nodeIcon");
            return (Criteria) this;
        }

        public Criteria andNodeIconNotIn(List<String> values) {
            addCriterion("node_icon not in", values, "nodeIcon");
            return (Criteria) this;
        }

        public Criteria andNodeIconBetween(String value1, String value2) {
            addCriterion("node_icon between", value1, value2, "nodeIcon");
            return (Criteria) this;
        }

        public Criteria andNodeIconNotBetween(String value1, String value2) {
            addCriterion("node_icon not between", value1, value2, "nodeIcon");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * node
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