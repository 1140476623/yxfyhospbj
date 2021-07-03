package sjjd.doc.line.pojo;

import java.util.ArrayList;
import java.util.List;

public class FacilityExample {
    /**
     * facility
     */
    protected String orderByClause;

    /**
     * facility
     */
    protected boolean distinct;

    /**
     * facility
     */
    protected List<Criteria> oredCriteria;

    public FacilityExample() {
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
     * facility
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

        public Criteria andFacMacidIsNull() {
            addCriterion("fac_macid is null");
            return (Criteria) this;
        }

        public Criteria andFacMacidIsNotNull() {
            addCriterion("fac_macid is not null");
            return (Criteria) this;
        }

        public Criteria andFacMacidEqualTo(String value) {
            addCriterion("fac_macid =", value, "facMacid");
            return (Criteria) this;
        }

        public Criteria andFacMacidNotEqualTo(String value) {
            addCriterion("fac_macid <>", value, "facMacid");
            return (Criteria) this;
        }

        public Criteria andFacMacidGreaterThan(String value) {
            addCriterion("fac_macid >", value, "facMacid");
            return (Criteria) this;
        }

        public Criteria andFacMacidGreaterThanOrEqualTo(String value) {
            addCriterion("fac_macid >=", value, "facMacid");
            return (Criteria) this;
        }

        public Criteria andFacMacidLessThan(String value) {
            addCriterion("fac_macid <", value, "facMacid");
            return (Criteria) this;
        }

        public Criteria andFacMacidLessThanOrEqualTo(String value) {
            addCriterion("fac_macid <=", value, "facMacid");
            return (Criteria) this;
        }

        public Criteria andFacMacidLike(String value) {
            addCriterion("fac_macid like", value, "facMacid");
            return (Criteria) this;
        }

        public Criteria andFacMacidNotLike(String value) {
            addCriterion("fac_macid not like", value, "facMacid");
            return (Criteria) this;
        }

        public Criteria andFacMacidIn(List<String> values) {
            addCriterion("fac_macid in", values, "facMacid");
            return (Criteria) this;
        }

        public Criteria andFacMacidNotIn(List<String> values) {
            addCriterion("fac_macid not in", values, "facMacid");
            return (Criteria) this;
        }

        public Criteria andFacMacidBetween(String value1, String value2) {
            addCriterion("fac_macid between", value1, value2, "facMacid");
            return (Criteria) this;
        }

        public Criteria andFacMacidNotBetween(String value1, String value2) {
            addCriterion("fac_macid not between", value1, value2, "facMacid");
            return (Criteria) this;
        }

        public Criteria andFacEncryptIsNull() {
            addCriterion("fac_encrypt is null");
            return (Criteria) this;
        }

        public Criteria andFacEncryptIsNotNull() {
            addCriterion("fac_encrypt is not null");
            return (Criteria) this;
        }

        public Criteria andFacEncryptEqualTo(String value) {
            addCriterion("fac_encrypt =", value, "facEncrypt");
            return (Criteria) this;
        }

        public Criteria andFacEncryptNotEqualTo(String value) {
            addCriterion("fac_encrypt <>", value, "facEncrypt");
            return (Criteria) this;
        }

        public Criteria andFacEncryptGreaterThan(String value) {
            addCriterion("fac_encrypt >", value, "facEncrypt");
            return (Criteria) this;
        }

        public Criteria andFacEncryptGreaterThanOrEqualTo(String value) {
            addCriterion("fac_encrypt >=", value, "facEncrypt");
            return (Criteria) this;
        }

        public Criteria andFacEncryptLessThan(String value) {
            addCriterion("fac_encrypt <", value, "facEncrypt");
            return (Criteria) this;
        }

        public Criteria andFacEncryptLessThanOrEqualTo(String value) {
            addCriterion("fac_encrypt <=", value, "facEncrypt");
            return (Criteria) this;
        }

        public Criteria andFacEncryptLike(String value) {
            addCriterion("fac_encrypt like", value, "facEncrypt");
            return (Criteria) this;
        }

        public Criteria andFacEncryptNotLike(String value) {
            addCriterion("fac_encrypt not like", value, "facEncrypt");
            return (Criteria) this;
        }

        public Criteria andFacEncryptIn(List<String> values) {
            addCriterion("fac_encrypt in", values, "facEncrypt");
            return (Criteria) this;
        }

        public Criteria andFacEncryptNotIn(List<String> values) {
            addCriterion("fac_encrypt not in", values, "facEncrypt");
            return (Criteria) this;
        }

        public Criteria andFacEncryptBetween(String value1, String value2) {
            addCriterion("fac_encrypt between", value1, value2, "facEncrypt");
            return (Criteria) this;
        }

        public Criteria andFacEncryptNotBetween(String value1, String value2) {
            addCriterion("fac_encrypt not between", value1, value2, "facEncrypt");
            return (Criteria) this;
        }

        public Criteria andFacDecodeIsNull() {
            addCriterion("fac_decode is null");
            return (Criteria) this;
        }

        public Criteria andFacDecodeIsNotNull() {
            addCriterion("fac_decode is not null");
            return (Criteria) this;
        }

        public Criteria andFacDecodeEqualTo(String value) {
            addCriterion("fac_decode =", value, "facDecode");
            return (Criteria) this;
        }

        public Criteria andFacDecodeNotEqualTo(String value) {
            addCriterion("fac_decode <>", value, "facDecode");
            return (Criteria) this;
        }

        public Criteria andFacDecodeGreaterThan(String value) {
            addCriterion("fac_decode >", value, "facDecode");
            return (Criteria) this;
        }

        public Criteria andFacDecodeGreaterThanOrEqualTo(String value) {
            addCriterion("fac_decode >=", value, "facDecode");
            return (Criteria) this;
        }

        public Criteria andFacDecodeLessThan(String value) {
            addCriterion("fac_decode <", value, "facDecode");
            return (Criteria) this;
        }

        public Criteria andFacDecodeLessThanOrEqualTo(String value) {
            addCriterion("fac_decode <=", value, "facDecode");
            return (Criteria) this;
        }

        public Criteria andFacDecodeLike(String value) {
            addCriterion("fac_decode like", value, "facDecode");
            return (Criteria) this;
        }

        public Criteria andFacDecodeNotLike(String value) {
            addCriterion("fac_decode not like", value, "facDecode");
            return (Criteria) this;
        }

        public Criteria andFacDecodeIn(List<String> values) {
            addCriterion("fac_decode in", values, "facDecode");
            return (Criteria) this;
        }

        public Criteria andFacDecodeNotIn(List<String> values) {
            addCriterion("fac_decode not in", values, "facDecode");
            return (Criteria) this;
        }

        public Criteria andFacDecodeBetween(String value1, String value2) {
            addCriterion("fac_decode between", value1, value2, "facDecode");
            return (Criteria) this;
        }

        public Criteria andFacDecodeNotBetween(String value1, String value2) {
            addCriterion("fac_decode not between", value1, value2, "facDecode");
            return (Criteria) this;
        }

        public Criteria andFacNumberIsNull() {
            addCriterion("fac_number is null");
            return (Criteria) this;
        }

        public Criteria andFacNumberIsNotNull() {
            addCriterion("fac_number is not null");
            return (Criteria) this;
        }

        public Criteria andFacNumberEqualTo(String value) {
            addCriterion("fac_number =", value, "facNumber");
            return (Criteria) this;
        }

        public Criteria andFacNumberNotEqualTo(String value) {
            addCriterion("fac_number <>", value, "facNumber");
            return (Criteria) this;
        }

        public Criteria andFacNumberGreaterThan(String value) {
            addCriterion("fac_number >", value, "facNumber");
            return (Criteria) this;
        }

        public Criteria andFacNumberGreaterThanOrEqualTo(String value) {
            addCriterion("fac_number >=", value, "facNumber");
            return (Criteria) this;
        }

        public Criteria andFacNumberLessThan(String value) {
            addCriterion("fac_number <", value, "facNumber");
            return (Criteria) this;
        }

        public Criteria andFacNumberLessThanOrEqualTo(String value) {
            addCriterion("fac_number <=", value, "facNumber");
            return (Criteria) this;
        }

        public Criteria andFacNumberLike(String value) {
            addCriterion("fac_number like", value, "facNumber");
            return (Criteria) this;
        }

        public Criteria andFacNumberNotLike(String value) {
            addCriterion("fac_number not like", value, "facNumber");
            return (Criteria) this;
        }

        public Criteria andFacNumberIn(List<String> values) {
            addCriterion("fac_number in", values, "facNumber");
            return (Criteria) this;
        }

        public Criteria andFacNumberNotIn(List<String> values) {
            addCriterion("fac_number not in", values, "facNumber");
            return (Criteria) this;
        }

        public Criteria andFacNumberBetween(String value1, String value2) {
            addCriterion("fac_number between", value1, value2, "facNumber");
            return (Criteria) this;
        }

        public Criteria andFacNumberNotBetween(String value1, String value2) {
            addCriterion("fac_number not between", value1, value2, "facNumber");
            return (Criteria) this;
        }

        public Criteria andFacStateIsNull() {
            addCriterion("fac_state is null");
            return (Criteria) this;
        }

        public Criteria andFacStateIsNotNull() {
            addCriterion("fac_state is not null");
            return (Criteria) this;
        }

        public Criteria andFacStateEqualTo(String value) {
            addCriterion("fac_state =", value, "facState");
            return (Criteria) this;
        }

        public Criteria andFacStateNotEqualTo(String value) {
            addCriterion("fac_state <>", value, "facState");
            return (Criteria) this;
        }

        public Criteria andFacStateGreaterThan(String value) {
            addCriterion("fac_state >", value, "facState");
            return (Criteria) this;
        }

        public Criteria andFacStateGreaterThanOrEqualTo(String value) {
            addCriterion("fac_state >=", value, "facState");
            return (Criteria) this;
        }

        public Criteria andFacStateLessThan(String value) {
            addCriterion("fac_state <", value, "facState");
            return (Criteria) this;
        }

        public Criteria andFacStateLessThanOrEqualTo(String value) {
            addCriterion("fac_state <=", value, "facState");
            return (Criteria) this;
        }

        public Criteria andFacStateLike(String value) {
            addCriterion("fac_state like", value, "facState");
            return (Criteria) this;
        }

        public Criteria andFacStateNotLike(String value) {
            addCriterion("fac_state not like", value, "facState");
            return (Criteria) this;
        }

        public Criteria andFacStateIn(List<String> values) {
            addCriterion("fac_state in", values, "facState");
            return (Criteria) this;
        }

        public Criteria andFacStateNotIn(List<String> values) {
            addCriterion("fac_state not in", values, "facState");
            return (Criteria) this;
        }

        public Criteria andFacStateBetween(String value1, String value2) {
            addCriterion("fac_state between", value1, value2, "facState");
            return (Criteria) this;
        }

        public Criteria andFacStateNotBetween(String value1, String value2) {
            addCriterion("fac_state not between", value1, value2, "facState");
            return (Criteria) this;
        }

        public Criteria andFacManageIsNull() {
            addCriterion("fac_manage is null");
            return (Criteria) this;
        }

        public Criteria andFacManageIsNotNull() {
            addCriterion("fac_manage is not null");
            return (Criteria) this;
        }

        public Criteria andFacManageEqualTo(String value) {
            addCriterion("fac_manage =", value, "facManage");
            return (Criteria) this;
        }

        public Criteria andFacManageNotEqualTo(String value) {
            addCriterion("fac_manage <>", value, "facManage");
            return (Criteria) this;
        }

        public Criteria andFacManageGreaterThan(String value) {
            addCriterion("fac_manage >", value, "facManage");
            return (Criteria) this;
        }

        public Criteria andFacManageGreaterThanOrEqualTo(String value) {
            addCriterion("fac_manage >=", value, "facManage");
            return (Criteria) this;
        }

        public Criteria andFacManageLessThan(String value) {
            addCriterion("fac_manage <", value, "facManage");
            return (Criteria) this;
        }

        public Criteria andFacManageLessThanOrEqualTo(String value) {
            addCriterion("fac_manage <=", value, "facManage");
            return (Criteria) this;
        }

        public Criteria andFacManageLike(String value) {
            addCriterion("fac_manage like", value, "facManage");
            return (Criteria) this;
        }

        public Criteria andFacManageNotLike(String value) {
            addCriterion("fac_manage not like", value, "facManage");
            return (Criteria) this;
        }

        public Criteria andFacManageIn(List<String> values) {
            addCriterion("fac_manage in", values, "facManage");
            return (Criteria) this;
        }

        public Criteria andFacManageNotIn(List<String> values) {
            addCriterion("fac_manage not in", values, "facManage");
            return (Criteria) this;
        }

        public Criteria andFacManageBetween(String value1, String value2) {
            addCriterion("fac_manage between", value1, value2, "facManage");
            return (Criteria) this;
        }

        public Criteria andFacManageNotBetween(String value1, String value2) {
            addCriterion("fac_manage not between", value1, value2, "facManage");
            return (Criteria) this;
        }

        public Criteria andFacClientidIsNull() {
            addCriterion("fac_clientId is null");
            return (Criteria) this;
        }

        public Criteria andFacClientidIsNotNull() {
            addCriterion("fac_clientId is not null");
            return (Criteria) this;
        }

        public Criteria andFacClientidEqualTo(String value) {
            addCriterion("fac_clientId =", value, "facClientid");
            return (Criteria) this;
        }

        public Criteria andFacClientidNotEqualTo(String value) {
            addCriterion("fac_clientId <>", value, "facClientid");
            return (Criteria) this;
        }

        public Criteria andFacClientidGreaterThan(String value) {
            addCriterion("fac_clientId >", value, "facClientid");
            return (Criteria) this;
        }

        public Criteria andFacClientidGreaterThanOrEqualTo(String value) {
            addCriterion("fac_clientId >=", value, "facClientid");
            return (Criteria) this;
        }

        public Criteria andFacClientidLessThan(String value) {
            addCriterion("fac_clientId <", value, "facClientid");
            return (Criteria) this;
        }

        public Criteria andFacClientidLessThanOrEqualTo(String value) {
            addCriterion("fac_clientId <=", value, "facClientid");
            return (Criteria) this;
        }

        public Criteria andFacClientidLike(String value) {
            addCriterion("fac_clientId like", value, "facClientid");
            return (Criteria) this;
        }

        public Criteria andFacClientidNotLike(String value) {
            addCriterion("fac_clientId not like", value, "facClientid");
            return (Criteria) this;
        }

        public Criteria andFacClientidIn(List<String> values) {
            addCriterion("fac_clientId in", values, "facClientid");
            return (Criteria) this;
        }

        public Criteria andFacClientidNotIn(List<String> values) {
            addCriterion("fac_clientId not in", values, "facClientid");
            return (Criteria) this;
        }

        public Criteria andFacClientidBetween(String value1, String value2) {
            addCriterion("fac_clientId between", value1, value2, "facClientid");
            return (Criteria) this;
        }

        public Criteria andFacClientidNotBetween(String value1, String value2) {
            addCriterion("fac_clientId not between", value1, value2, "facClientid");
            return (Criteria) this;
        }

        public Criteria andFacRoomIsNull() {
            addCriterion("fac_room is null");
            return (Criteria) this;
        }

        public Criteria andFacRoomIsNotNull() {
            addCriterion("fac_room is not null");
            return (Criteria) this;
        }

        public Criteria andFacRoomEqualTo(String value) {
            addCriterion("fac_room =", value, "facRoom");
            return (Criteria) this;
        }

        public Criteria andFacRoomNotEqualTo(String value) {
            addCriterion("fac_room <>", value, "facRoom");
            return (Criteria) this;
        }

        public Criteria andFacRoomGreaterThan(String value) {
            addCriterion("fac_room >", value, "facRoom");
            return (Criteria) this;
        }

        public Criteria andFacRoomGreaterThanOrEqualTo(String value) {
            addCriterion("fac_room >=", value, "facRoom");
            return (Criteria) this;
        }

        public Criteria andFacRoomLessThan(String value) {
            addCriterion("fac_room <", value, "facRoom");
            return (Criteria) this;
        }

        public Criteria andFacRoomLessThanOrEqualTo(String value) {
            addCriterion("fac_room <=", value, "facRoom");
            return (Criteria) this;
        }

        public Criteria andFacRoomLike(String value) {
            addCriterion("fac_room like", value, "facRoom");
            return (Criteria) this;
        }

        public Criteria andFacRoomNotLike(String value) {
            addCriterion("fac_room not like", value, "facRoom");
            return (Criteria) this;
        }

        public Criteria andFacRoomIn(List<String> values) {
            addCriterion("fac_room in", values, "facRoom");
            return (Criteria) this;
        }

        public Criteria andFacRoomNotIn(List<String> values) {
            addCriterion("fac_room not in", values, "facRoom");
            return (Criteria) this;
        }

        public Criteria andFacRoomBetween(String value1, String value2) {
            addCriterion("fac_room between", value1, value2, "facRoom");
            return (Criteria) this;
        }

        public Criteria andFacRoomNotBetween(String value1, String value2) {
            addCriterion("fac_room not between", value1, value2, "facRoom");
            return (Criteria) this;
        }

        public Criteria andFacFloorIsNull() {
            addCriterion("fac_floor is null");
            return (Criteria) this;
        }

        public Criteria andFacFloorIsNotNull() {
            addCriterion("fac_floor is not null");
            return (Criteria) this;
        }

        public Criteria andFacFloorEqualTo(String value) {
            addCriterion("fac_floor =", value, "facFloor");
            return (Criteria) this;
        }

        public Criteria andFacFloorNotEqualTo(String value) {
            addCriterion("fac_floor <>", value, "facFloor");
            return (Criteria) this;
        }

        public Criteria andFacFloorGreaterThan(String value) {
            addCriterion("fac_floor >", value, "facFloor");
            return (Criteria) this;
        }

        public Criteria andFacFloorGreaterThanOrEqualTo(String value) {
            addCriterion("fac_floor >=", value, "facFloor");
            return (Criteria) this;
        }

        public Criteria andFacFloorLessThan(String value) {
            addCriterion("fac_floor <", value, "facFloor");
            return (Criteria) this;
        }

        public Criteria andFacFloorLessThanOrEqualTo(String value) {
            addCriterion("fac_floor <=", value, "facFloor");
            return (Criteria) this;
        }

        public Criteria andFacFloorLike(String value) {
            addCriterion("fac_floor like", value, "facFloor");
            return (Criteria) this;
        }

        public Criteria andFacFloorNotLike(String value) {
            addCriterion("fac_floor not like", value, "facFloor");
            return (Criteria) this;
        }

        public Criteria andFacFloorIn(List<String> values) {
            addCriterion("fac_floor in", values, "facFloor");
            return (Criteria) this;
        }

        public Criteria andFacFloorNotIn(List<String> values) {
            addCriterion("fac_floor not in", values, "facFloor");
            return (Criteria) this;
        }

        public Criteria andFacFloorBetween(String value1, String value2) {
            addCriterion("fac_floor between", value1, value2, "facFloor");
            return (Criteria) this;
        }

        public Criteria andFacFloorNotBetween(String value1, String value2) {
            addCriterion("fac_floor not between", value1, value2, "facFloor");
            return (Criteria) this;
        }

        public Criteria andFacDidIsNull() {
            addCriterion("fac_did is null");
            return (Criteria) this;
        }

        public Criteria andFacDidIsNotNull() {
            addCriterion("fac_did is not null");
            return (Criteria) this;
        }

        public Criteria andFacDidEqualTo(String value) {
            addCriterion("fac_did =", value, "facDid");
            return (Criteria) this;
        }

        public Criteria andFacDidNotEqualTo(String value) {
            addCriterion("fac_did <>", value, "facDid");
            return (Criteria) this;
        }

        public Criteria andFacDidGreaterThan(String value) {
            addCriterion("fac_did >", value, "facDid");
            return (Criteria) this;
        }

        public Criteria andFacDidGreaterThanOrEqualTo(String value) {
            addCriterion("fac_did >=", value, "facDid");
            return (Criteria) this;
        }

        public Criteria andFacDidLessThan(String value) {
            addCriterion("fac_did <", value, "facDid");
            return (Criteria) this;
        }

        public Criteria andFacDidLessThanOrEqualTo(String value) {
            addCriterion("fac_did <=", value, "facDid");
            return (Criteria) this;
        }

        public Criteria andFacDidLike(String value) {
            addCriterion("fac_did like", value, "facDid");
            return (Criteria) this;
        }

        public Criteria andFacDidNotLike(String value) {
            addCriterion("fac_did not like", value, "facDid");
            return (Criteria) this;
        }

        public Criteria andFacDidIn(List<String> values) {
            addCriterion("fac_did in", values, "facDid");
            return (Criteria) this;
        }

        public Criteria andFacDidNotIn(List<String> values) {
            addCriterion("fac_did not in", values, "facDid");
            return (Criteria) this;
        }

        public Criteria andFacDidBetween(String value1, String value2) {
            addCriterion("fac_did between", value1, value2, "facDid");
            return (Criteria) this;
        }

        public Criteria andFacDidNotBetween(String value1, String value2) {
            addCriterion("fac_did not between", value1, value2, "facDid");
            return (Criteria) this;
        }

        public Criteria andFacRegisterIsNull() {
            addCriterion("fac_register is null");
            return (Criteria) this;
        }

        public Criteria andFacRegisterIsNotNull() {
            addCriterion("fac_register is not null");
            return (Criteria) this;
        }

        public Criteria andFacRegisterEqualTo(String value) {
            addCriterion("fac_register =", value, "facRegister");
            return (Criteria) this;
        }

        public Criteria andFacRegisterNotEqualTo(String value) {
            addCriterion("fac_register <>", value, "facRegister");
            return (Criteria) this;
        }

        public Criteria andFacRegisterGreaterThan(String value) {
            addCriterion("fac_register >", value, "facRegister");
            return (Criteria) this;
        }

        public Criteria andFacRegisterGreaterThanOrEqualTo(String value) {
            addCriterion("fac_register >=", value, "facRegister");
            return (Criteria) this;
        }

        public Criteria andFacRegisterLessThan(String value) {
            addCriterion("fac_register <", value, "facRegister");
            return (Criteria) this;
        }

        public Criteria andFacRegisterLessThanOrEqualTo(String value) {
            addCriterion("fac_register <=", value, "facRegister");
            return (Criteria) this;
        }

        public Criteria andFacRegisterLike(String value) {
            addCriterion("fac_register like", value, "facRegister");
            return (Criteria) this;
        }

        public Criteria andFacRegisterNotLike(String value) {
            addCriterion("fac_register not like", value, "facRegister");
            return (Criteria) this;
        }

        public Criteria andFacRegisterIn(List<String> values) {
            addCriterion("fac_register in", values, "facRegister");
            return (Criteria) this;
        }

        public Criteria andFacRegisterNotIn(List<String> values) {
            addCriterion("fac_register not in", values, "facRegister");
            return (Criteria) this;
        }

        public Criteria andFacRegisterBetween(String value1, String value2) {
            addCriterion("fac_register between", value1, value2, "facRegister");
            return (Criteria) this;
        }

        public Criteria andFacRegisterNotBetween(String value1, String value2) {
            addCriterion("fac_register not between", value1, value2, "facRegister");
            return (Criteria) this;
        }

        public Criteria andFacIpIsNull() {
            addCriterion("fac_ip is null");
            return (Criteria) this;
        }

        public Criteria andFacIpIsNotNull() {
            addCriterion("fac_ip is not null");
            return (Criteria) this;
        }

        public Criteria andFacIpEqualTo(String value) {
            addCriterion("fac_ip =", value, "facIp");
            return (Criteria) this;
        }

        public Criteria andFacIpNotEqualTo(String value) {
            addCriterion("fac_ip <>", value, "facIp");
            return (Criteria) this;
        }

        public Criteria andFacIpGreaterThan(String value) {
            addCriterion("fac_ip >", value, "facIp");
            return (Criteria) this;
        }

        public Criteria andFacIpGreaterThanOrEqualTo(String value) {
            addCriterion("fac_ip >=", value, "facIp");
            return (Criteria) this;
        }

        public Criteria andFacIpLessThan(String value) {
            addCriterion("fac_ip <", value, "facIp");
            return (Criteria) this;
        }

        public Criteria andFacIpLessThanOrEqualTo(String value) {
            addCriterion("fac_ip <=", value, "facIp");
            return (Criteria) this;
        }

        public Criteria andFacIpLike(String value) {
            addCriterion("fac_ip like", value, "facIp");
            return (Criteria) this;
        }

        public Criteria andFacIpNotLike(String value) {
            addCriterion("fac_ip not like", value, "facIp");
            return (Criteria) this;
        }

        public Criteria andFacIpIn(List<String> values) {
            addCriterion("fac_ip in", values, "facIp");
            return (Criteria) this;
        }

        public Criteria andFacIpNotIn(List<String> values) {
            addCriterion("fac_ip not in", values, "facIp");
            return (Criteria) this;
        }

        public Criteria andFacIpBetween(String value1, String value2) {
            addCriterion("fac_ip between", value1, value2, "facIp");
            return (Criteria) this;
        }

        public Criteria andFacIpNotBetween(String value1, String value2) {
            addCriterion("fac_ip not between", value1, value2, "facIp");
            return (Criteria) this;
        }

        public Criteria andFacTypeIsNull() {
            addCriterion("fac_type is null");
            return (Criteria) this;
        }

        public Criteria andFacTypeIsNotNull() {
            addCriterion("fac_type is not null");
            return (Criteria) this;
        }

        public Criteria andFacTypeEqualTo(String value) {
            addCriterion("fac_type =", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeNotEqualTo(String value) {
            addCriterion("fac_type <>", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeGreaterThan(String value) {
            addCriterion("fac_type >", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeGreaterThanOrEqualTo(String value) {
            addCriterion("fac_type >=", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeLessThan(String value) {
            addCriterion("fac_type <", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeLessThanOrEqualTo(String value) {
            addCriterion("fac_type <=", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeLike(String value) {
            addCriterion("fac_type like", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeNotLike(String value) {
            addCriterion("fac_type not like", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeIn(List<String> values) {
            addCriterion("fac_type in", values, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeNotIn(List<String> values) {
            addCriterion("fac_type not in", values, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeBetween(String value1, String value2) {
            addCriterion("fac_type between", value1, value2, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeNotBetween(String value1, String value2) {
            addCriterion("fac_type not between", value1, value2, "facType");
            return (Criteria) this;
        }

        public Criteria andSysTypeIsNull() {
            addCriterion("sys_type is null");
            return (Criteria) this;
        }

        public Criteria andSysTypeIsNotNull() {
            addCriterion("sys_type is not null");
            return (Criteria) this;
        }

        public Criteria andSysTypeEqualTo(String value) {
            addCriterion("sys_type =", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeNotEqualTo(String value) {
            addCriterion("sys_type <>", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeGreaterThan(String value) {
            addCriterion("sys_type >", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sys_type >=", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeLessThan(String value) {
            addCriterion("sys_type <", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeLessThanOrEqualTo(String value) {
            addCriterion("sys_type <=", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeLike(String value) {
            addCriterion("sys_type like", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeNotLike(String value) {
            addCriterion("sys_type not like", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeIn(List<String> values) {
            addCriterion("sys_type in", values, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeNotIn(List<String> values) {
            addCriterion("sys_type not in", values, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeBetween(String value1, String value2) {
            addCriterion("sys_type between", value1, value2, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeNotBetween(String value1, String value2) {
            addCriterion("sys_type not between", value1, value2, "sysType");
            return (Criteria) this;
        }

        public Criteria andShotLinkIsNull() {
            addCriterion("shot_link is null");
            return (Criteria) this;
        }

        public Criteria andShotLinkIsNotNull() {
            addCriterion("shot_link is not null");
            return (Criteria) this;
        }

        public Criteria andShotLinkEqualTo(String value) {
            addCriterion("shot_link =", value, "shotLink");
            return (Criteria) this;
        }

        public Criteria andShotLinkNotEqualTo(String value) {
            addCriterion("shot_link <>", value, "shotLink");
            return (Criteria) this;
        }

        public Criteria andShotLinkGreaterThan(String value) {
            addCriterion("shot_link >", value, "shotLink");
            return (Criteria) this;
        }

        public Criteria andShotLinkGreaterThanOrEqualTo(String value) {
            addCriterion("shot_link >=", value, "shotLink");
            return (Criteria) this;
        }

        public Criteria andShotLinkLessThan(String value) {
            addCriterion("shot_link <", value, "shotLink");
            return (Criteria) this;
        }

        public Criteria andShotLinkLessThanOrEqualTo(String value) {
            addCriterion("shot_link <=", value, "shotLink");
            return (Criteria) this;
        }

        public Criteria andShotLinkLike(String value) {
            addCriterion("shot_link like", value, "shotLink");
            return (Criteria) this;
        }

        public Criteria andShotLinkNotLike(String value) {
            addCriterion("shot_link not like", value, "shotLink");
            return (Criteria) this;
        }

        public Criteria andShotLinkIn(List<String> values) {
            addCriterion("shot_link in", values, "shotLink");
            return (Criteria) this;
        }

        public Criteria andShotLinkNotIn(List<String> values) {
            addCriterion("shot_link not in", values, "shotLink");
            return (Criteria) this;
        }

        public Criteria andShotLinkBetween(String value1, String value2) {
            addCriterion("shot_link between", value1, value2, "shotLink");
            return (Criteria) this;
        }

        public Criteria andShotLinkNotBetween(String value1, String value2) {
            addCriterion("shot_link not between", value1, value2, "shotLink");
            return (Criteria) this;
        }

        public Criteria andFacAddresIsNull() {
            addCriterion("fac_addres is null");
            return (Criteria) this;
        }

        public Criteria andFacAddresIsNotNull() {
            addCriterion("fac_addres is not null");
            return (Criteria) this;
        }

        public Criteria andFacAddresEqualTo(String value) {
            addCriterion("fac_addres =", value, "facAddres");
            return (Criteria) this;
        }

        public Criteria andFacAddresNotEqualTo(String value) {
            addCriterion("fac_addres <>", value, "facAddres");
            return (Criteria) this;
        }

        public Criteria andFacAddresGreaterThan(String value) {
            addCriterion("fac_addres >", value, "facAddres");
            return (Criteria) this;
        }

        public Criteria andFacAddresGreaterThanOrEqualTo(String value) {
            addCriterion("fac_addres >=", value, "facAddres");
            return (Criteria) this;
        }

        public Criteria andFacAddresLessThan(String value) {
            addCriterion("fac_addres <", value, "facAddres");
            return (Criteria) this;
        }

        public Criteria andFacAddresLessThanOrEqualTo(String value) {
            addCriterion("fac_addres <=", value, "facAddres");
            return (Criteria) this;
        }

        public Criteria andFacAddresLike(String value) {
            addCriterion("fac_addres like", value, "facAddres");
            return (Criteria) this;
        }

        public Criteria andFacAddresNotLike(String value) {
            addCriterion("fac_addres not like", value, "facAddres");
            return (Criteria) this;
        }

        public Criteria andFacAddresIn(List<String> values) {
            addCriterion("fac_addres in", values, "facAddres");
            return (Criteria) this;
        }

        public Criteria andFacAddresNotIn(List<String> values) {
            addCriterion("fac_addres not in", values, "facAddres");
            return (Criteria) this;
        }

        public Criteria andFacAddresBetween(String value1, String value2) {
            addCriterion("fac_addres between", value1, value2, "facAddres");
            return (Criteria) this;
        }

        public Criteria andFacAddresNotBetween(String value1, String value2) {
            addCriterion("fac_addres not between", value1, value2, "facAddres");
            return (Criteria) this;
        }

        public Criteria andVIdIsNull() {
            addCriterion("v_id is null");
            return (Criteria) this;
        }

        public Criteria andVIdIsNotNull() {
            addCriterion("v_id is not null");
            return (Criteria) this;
        }

        public Criteria andVIdEqualTo(String value) {
            addCriterion("v_id =", value, "vId");
            return (Criteria) this;
        }

        public Criteria andVIdNotEqualTo(String value) {
            addCriterion("v_id <>", value, "vId");
            return (Criteria) this;
        }

        public Criteria andVIdGreaterThan(String value) {
            addCriterion("v_id >", value, "vId");
            return (Criteria) this;
        }

        public Criteria andVIdGreaterThanOrEqualTo(String value) {
            addCriterion("v_id >=", value, "vId");
            return (Criteria) this;
        }

        public Criteria andVIdLessThan(String value) {
            addCriterion("v_id <", value, "vId");
            return (Criteria) this;
        }

        public Criteria andVIdLessThanOrEqualTo(String value) {
            addCriterion("v_id <=", value, "vId");
            return (Criteria) this;
        }

        public Criteria andVIdLike(String value) {
            addCriterion("v_id like", value, "vId");
            return (Criteria) this;
        }

        public Criteria andVIdNotLike(String value) {
            addCriterion("v_id not like", value, "vId");
            return (Criteria) this;
        }

        public Criteria andVIdIn(List<String> values) {
            addCriterion("v_id in", values, "vId");
            return (Criteria) this;
        }

        public Criteria andVIdNotIn(List<String> values) {
            addCriterion("v_id not in", values, "vId");
            return (Criteria) this;
        }

        public Criteria andVIdBetween(String value1, String value2) {
            addCriterion("v_id between", value1, value2, "vId");
            return (Criteria) this;
        }

        public Criteria andVIdNotBetween(String value1, String value2) {
            addCriterion("v_id not between", value1, value2, "vId");
            return (Criteria) this;
        }

        public Criteria andIsVoiceIsNull() {
            addCriterion("is_voice is null");
            return (Criteria) this;
        }

        public Criteria andIsVoiceIsNotNull() {
            addCriterion("is_voice is not null");
            return (Criteria) this;
        }

        public Criteria andIsVoiceEqualTo(String value) {
            addCriterion("is_voice =", value, "isVoice");
            return (Criteria) this;
        }

        public Criteria andIsVoiceNotEqualTo(String value) {
            addCriterion("is_voice <>", value, "isVoice");
            return (Criteria) this;
        }

        public Criteria andIsVoiceGreaterThan(String value) {
            addCriterion("is_voice >", value, "isVoice");
            return (Criteria) this;
        }

        public Criteria andIsVoiceGreaterThanOrEqualTo(String value) {
            addCriterion("is_voice >=", value, "isVoice");
            return (Criteria) this;
        }

        public Criteria andIsVoiceLessThan(String value) {
            addCriterion("is_voice <", value, "isVoice");
            return (Criteria) this;
        }

        public Criteria andIsVoiceLessThanOrEqualTo(String value) {
            addCriterion("is_voice <=", value, "isVoice");
            return (Criteria) this;
        }

        public Criteria andIsVoiceLike(String value) {
            addCriterion("is_voice like", value, "isVoice");
            return (Criteria) this;
        }

        public Criteria andIsVoiceNotLike(String value) {
            addCriterion("is_voice not like", value, "isVoice");
            return (Criteria) this;
        }

        public Criteria andIsVoiceIn(List<String> values) {
            addCriterion("is_voice in", values, "isVoice");
            return (Criteria) this;
        }

        public Criteria andIsVoiceNotIn(List<String> values) {
            addCriterion("is_voice not in", values, "isVoice");
            return (Criteria) this;
        }

        public Criteria andIsVoiceBetween(String value1, String value2) {
            addCriterion("is_voice between", value1, value2, "isVoice");
            return (Criteria) this;
        }

        public Criteria andIsVoiceNotBetween(String value1, String value2) {
            addCriterion("is_voice not between", value1, value2, "isVoice");
            return (Criteria) this;
        }

        public Criteria andVoiceSizeIsNull() {
            addCriterion("voice_size is null");
            return (Criteria) this;
        }

        public Criteria andVoiceSizeIsNotNull() {
            addCriterion("voice_size is not null");
            return (Criteria) this;
        }

        public Criteria andVoiceSizeEqualTo(String value) {
            addCriterion("voice_size =", value, "voiceSize");
            return (Criteria) this;
        }

        public Criteria andVoiceSizeNotEqualTo(String value) {
            addCriterion("voice_size <>", value, "voiceSize");
            return (Criteria) this;
        }

        public Criteria andVoiceSizeGreaterThan(String value) {
            addCriterion("voice_size >", value, "voiceSize");
            return (Criteria) this;
        }

        public Criteria andVoiceSizeGreaterThanOrEqualTo(String value) {
            addCriterion("voice_size >=", value, "voiceSize");
            return (Criteria) this;
        }

        public Criteria andVoiceSizeLessThan(String value) {
            addCriterion("voice_size <", value, "voiceSize");
            return (Criteria) this;
        }

        public Criteria andVoiceSizeLessThanOrEqualTo(String value) {
            addCriterion("voice_size <=", value, "voiceSize");
            return (Criteria) this;
        }

        public Criteria andVoiceSizeLike(String value) {
            addCriterion("voice_size like", value, "voiceSize");
            return (Criteria) this;
        }

        public Criteria andVoiceSizeNotLike(String value) {
            addCriterion("voice_size not like", value, "voiceSize");
            return (Criteria) this;
        }

        public Criteria andVoiceSizeIn(List<String> values) {
            addCriterion("voice_size in", values, "voiceSize");
            return (Criteria) this;
        }

        public Criteria andVoiceSizeNotIn(List<String> values) {
            addCriterion("voice_size not in", values, "voiceSize");
            return (Criteria) this;
        }

        public Criteria andVoiceSizeBetween(String value1, String value2) {
            addCriterion("voice_size between", value1, value2, "voiceSize");
            return (Criteria) this;
        }

        public Criteria andVoiceSizeNotBetween(String value1, String value2) {
            addCriterion("voice_size not between", value1, value2, "voiceSize");
            return (Criteria) this;
        }

        public Criteria andCoSwitchIsNull() {
            addCriterion("co_switch is null");
            return (Criteria) this;
        }

        public Criteria andCoSwitchIsNotNull() {
            addCriterion("co_switch is not null");
            return (Criteria) this;
        }

        public Criteria andCoSwitchEqualTo(String value) {
            addCriterion("co_switch =", value, "coSwitch");
            return (Criteria) this;
        }

        public Criteria andCoSwitchNotEqualTo(String value) {
            addCriterion("co_switch <>", value, "coSwitch");
            return (Criteria) this;
        }

        public Criteria andCoSwitchGreaterThan(String value) {
            addCriterion("co_switch >", value, "coSwitch");
            return (Criteria) this;
        }

        public Criteria andCoSwitchGreaterThanOrEqualTo(String value) {
            addCriterion("co_switch >=", value, "coSwitch");
            return (Criteria) this;
        }

        public Criteria andCoSwitchLessThan(String value) {
            addCriterion("co_switch <", value, "coSwitch");
            return (Criteria) this;
        }

        public Criteria andCoSwitchLessThanOrEqualTo(String value) {
            addCriterion("co_switch <=", value, "coSwitch");
            return (Criteria) this;
        }

        public Criteria andCoSwitchLike(String value) {
            addCriterion("co_switch like", value, "coSwitch");
            return (Criteria) this;
        }

        public Criteria andCoSwitchNotLike(String value) {
            addCriterion("co_switch not like", value, "coSwitch");
            return (Criteria) this;
        }

        public Criteria andCoSwitchIn(List<String> values) {
            addCriterion("co_switch in", values, "coSwitch");
            return (Criteria) this;
        }

        public Criteria andCoSwitchNotIn(List<String> values) {
            addCriterion("co_switch not in", values, "coSwitch");
            return (Criteria) this;
        }

        public Criteria andCoSwitchBetween(String value1, String value2) {
            addCriterion("co_switch between", value1, value2, "coSwitch");
            return (Criteria) this;
        }

        public Criteria andCoSwitchNotBetween(String value1, String value2) {
            addCriterion("co_switch not between", value1, value2, "coSwitch");
            return (Criteria) this;
        }

        public Criteria andFacAreaIsNull() {
            addCriterion("fac_area is null");
            return (Criteria) this;
        }

        public Criteria andFacAreaIsNotNull() {
            addCriterion("fac_area is not null");
            return (Criteria) this;
        }

        public Criteria andFacAreaEqualTo(String value) {
            addCriterion("fac_area =", value, "facArea");
            return (Criteria) this;
        }

        public Criteria andFacAreaNotEqualTo(String value) {
            addCriterion("fac_area <>", value, "facArea");
            return (Criteria) this;
        }

        public Criteria andFacAreaGreaterThan(String value) {
            addCriterion("fac_area >", value, "facArea");
            return (Criteria) this;
        }

        public Criteria andFacAreaGreaterThanOrEqualTo(String value) {
            addCriterion("fac_area >=", value, "facArea");
            return (Criteria) this;
        }

        public Criteria andFacAreaLessThan(String value) {
            addCriterion("fac_area <", value, "facArea");
            return (Criteria) this;
        }

        public Criteria andFacAreaLessThanOrEqualTo(String value) {
            addCriterion("fac_area <=", value, "facArea");
            return (Criteria) this;
        }

        public Criteria andFacAreaLike(String value) {
            addCriterion("fac_area like", value, "facArea");
            return (Criteria) this;
        }

        public Criteria andFacAreaNotLike(String value) {
            addCriterion("fac_area not like", value, "facArea");
            return (Criteria) this;
        }

        public Criteria andFacAreaIn(List<String> values) {
            addCriterion("fac_area in", values, "facArea");
            return (Criteria) this;
        }

        public Criteria andFacAreaNotIn(List<String> values) {
            addCriterion("fac_area not in", values, "facArea");
            return (Criteria) this;
        }

        public Criteria andFacAreaBetween(String value1, String value2) {
            addCriterion("fac_area between", value1, value2, "facArea");
            return (Criteria) this;
        }

        public Criteria andFacAreaNotBetween(String value1, String value2) {
            addCriterion("fac_area not between", value1, value2, "facArea");
            return (Criteria) this;
        }

        public Criteria andUptimeIsNull() {
            addCriterion("uptime is null");
            return (Criteria) this;
        }

        public Criteria andUptimeIsNotNull() {
            addCriterion("uptime is not null");
            return (Criteria) this;
        }

        public Criteria andUptimeEqualTo(String value) {
            addCriterion("uptime =", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeNotEqualTo(String value) {
            addCriterion("uptime <>", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeGreaterThan(String value) {
            addCriterion("uptime >", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeGreaterThanOrEqualTo(String value) {
            addCriterion("uptime >=", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeLessThan(String value) {
            addCriterion("uptime <", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeLessThanOrEqualTo(String value) {
            addCriterion("uptime <=", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeLike(String value) {
            addCriterion("uptime like", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeNotLike(String value) {
            addCriterion("uptime not like", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeIn(List<String> values) {
            addCriterion("uptime in", values, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeNotIn(List<String> values) {
            addCriterion("uptime not in", values, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeBetween(String value1, String value2) {
            addCriterion("uptime between", value1, value2, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeNotBetween(String value1, String value2) {
            addCriterion("uptime not between", value1, value2, "uptime");
            return (Criteria) this;
        }

        public Criteria andOfftimeIsNull() {
            addCriterion("offtime is null");
            return (Criteria) this;
        }

        public Criteria andOfftimeIsNotNull() {
            addCriterion("offtime is not null");
            return (Criteria) this;
        }

        public Criteria andOfftimeEqualTo(String value) {
            addCriterion("offtime =", value, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeNotEqualTo(String value) {
            addCriterion("offtime <>", value, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeGreaterThan(String value) {
            addCriterion("offtime >", value, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeGreaterThanOrEqualTo(String value) {
            addCriterion("offtime >=", value, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeLessThan(String value) {
            addCriterion("offtime <", value, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeLessThanOrEqualTo(String value) {
            addCriterion("offtime <=", value, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeLike(String value) {
            addCriterion("offtime like", value, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeNotLike(String value) {
            addCriterion("offtime not like", value, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeIn(List<String> values) {
            addCriterion("offtime in", values, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeNotIn(List<String> values) {
            addCriterion("offtime not in", values, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeBetween(String value1, String value2) {
            addCriterion("offtime between", value1, value2, "offtime");
            return (Criteria) this;
        }

        public Criteria andOfftimeNotBetween(String value1, String value2) {
            addCriterion("offtime not between", value1, value2, "offtime");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNull() {
            addCriterion("org_code is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNotNull() {
            addCriterion("org_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeEqualTo(String value) {
            addCriterion("org_code =", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotEqualTo(String value) {
            addCriterion("org_code <>", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThan(String value) {
            addCriterion("org_code >", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("org_code >=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThan(String value) {
            addCriterion("org_code <", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("org_code <=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLike(String value) {
            addCriterion("org_code like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotLike(String value) {
            addCriterion("org_code not like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIn(List<String> values) {
            addCriterion("org_code in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotIn(List<String> values) {
            addCriterion("org_code not in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeBetween(String value1, String value2) {
            addCriterion("org_code between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotBetween(String value1, String value2) {
            addCriterion("org_code not between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andTempIsNull() {
            addCriterion("temp is null");
            return (Criteria) this;
        }

        public Criteria andTempIsNotNull() {
            addCriterion("temp is not null");
            return (Criteria) this;
        }

        public Criteria andTempEqualTo(String value) {
            addCriterion("temp =", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempNotEqualTo(String value) {
            addCriterion("temp <>", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempGreaterThan(String value) {
            addCriterion("temp >", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempGreaterThanOrEqualTo(String value) {
            addCriterion("temp >=", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempLessThan(String value) {
            addCriterion("temp <", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempLessThanOrEqualTo(String value) {
            addCriterion("temp <=", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempLike(String value) {
            addCriterion("temp like", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempNotLike(String value) {
            addCriterion("temp not like", value, "temp");
            return (Criteria) this;
        }

        public Criteria andTempIn(List<String> values) {
            addCriterion("temp in", values, "temp");
            return (Criteria) this;
        }

        public Criteria andTempNotIn(List<String> values) {
            addCriterion("temp not in", values, "temp");
            return (Criteria) this;
        }

        public Criteria andTempBetween(String value1, String value2) {
            addCriterion("temp between", value1, value2, "temp");
            return (Criteria) this;
        }

        public Criteria andTempNotBetween(String value1, String value2) {
            addCriterion("temp not between", value1, value2, "temp");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * facility
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