package edu.neu.csye6200.entity.dbobj;

import java.util.ArrayList;
import java.util.List;

public class GroupDOCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GroupDOCriteria() {
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

        public Criteria andGroupIdIsNull() {
            addCriterion("groupId is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("groupId is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Integer value) {
            addCriterion("groupId =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Integer value) {
            addCriterion("groupId <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Integer value) {
            addCriterion("groupId >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("groupId >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Integer value) {
            addCriterion("groupId <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("groupId <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Integer> values) {
            addCriterion("groupId in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Integer> values) {
            addCriterion("groupId not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("groupId between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("groupId not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdIsNull() {
            addCriterion("classroomId is null");
            return (Criteria) this;
        }

        public Criteria andClassroomIdIsNotNull() {
            addCriterion("classroomId is not null");
            return (Criteria) this;
        }

        public Criteria andClassroomIdEqualTo(Integer value) {
            addCriterion("classroomId =", value, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdNotEqualTo(Integer value) {
            addCriterion("classroomId <>", value, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdGreaterThan(Integer value) {
            addCriterion("classroomId >", value, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("classroomId >=", value, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdLessThan(Integer value) {
            addCriterion("classroomId <", value, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdLessThanOrEqualTo(Integer value) {
            addCriterion("classroomId <=", value, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdIn(List<Integer> values) {
            addCriterion("classroomId in", values, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdNotIn(List<Integer> values) {
            addCriterion("classroomId not in", values, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdBetween(Integer value1, Integer value2) {
            addCriterion("classroomId between", value1, value2, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdNotBetween(Integer value1, Integer value2) {
            addCriterion("classroomId not between", value1, value2, "classroomId");
            return (Criteria) this;
        }

        public Criteria andAgeStateIsNull() {
            addCriterion("ageState is null");
            return (Criteria) this;
        }

        public Criteria andAgeStateIsNotNull() {
            addCriterion("ageState is not null");
            return (Criteria) this;
        }

        public Criteria andAgeStateEqualTo(Integer value) {
            addCriterion("ageState =", value, "ageState");
            return (Criteria) this;
        }

        public Criteria andAgeStateNotEqualTo(Integer value) {
            addCriterion("ageState <>", value, "ageState");
            return (Criteria) this;
        }

        public Criteria andAgeStateGreaterThan(Integer value) {
            addCriterion("ageState >", value, "ageState");
            return (Criteria) this;
        }

        public Criteria andAgeStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("ageState >=", value, "ageState");
            return (Criteria) this;
        }

        public Criteria andAgeStateLessThan(Integer value) {
            addCriterion("ageState <", value, "ageState");
            return (Criteria) this;
        }

        public Criteria andAgeStateLessThanOrEqualTo(Integer value) {
            addCriterion("ageState <=", value, "ageState");
            return (Criteria) this;
        }

        public Criteria andAgeStateIn(List<Integer> values) {
            addCriterion("ageState in", values, "ageState");
            return (Criteria) this;
        }

        public Criteria andAgeStateNotIn(List<Integer> values) {
            addCriterion("ageState not in", values, "ageState");
            return (Criteria) this;
        }

        public Criteria andAgeStateBetween(Integer value1, Integer value2) {
            addCriterion("ageState between", value1, value2, "ageState");
            return (Criteria) this;
        }

        public Criteria andAgeStateNotBetween(Integer value1, Integer value2) {
            addCriterion("ageState not between", value1, value2, "ageState");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIsNull() {
            addCriterion("teacherId is null");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIsNotNull() {
            addCriterion("teacherId is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherIdEqualTo(Integer value) {
            addCriterion("teacherId =", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotEqualTo(Integer value) {
            addCriterion("teacherId <>", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdGreaterThan(Integer value) {
            addCriterion("teacherId >", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("teacherId >=", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLessThan(Integer value) {
            addCriterion("teacherId <", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLessThanOrEqualTo(Integer value) {
            addCriterion("teacherId <=", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIn(List<Integer> values) {
            addCriterion("teacherId in", values, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotIn(List<Integer> values) {
            addCriterion("teacherId not in", values, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdBetween(Integer value1, Integer value2) {
            addCriterion("teacherId between", value1, value2, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotBetween(Integer value1, Integer value2) {
            addCriterion("teacherId not between", value1, value2, "teacherId");
            return (Criteria) this;
        }

        public Criteria andStudentCountIsNull() {
            addCriterion("studentCount is null");
            return (Criteria) this;
        }

        public Criteria andStudentCountIsNotNull() {
            addCriterion("studentCount is not null");
            return (Criteria) this;
        }

        public Criteria andStudentCountEqualTo(Integer value) {
            addCriterion("studentCount =", value, "studentCount");
            return (Criteria) this;
        }

        public Criteria andStudentCountNotEqualTo(Integer value) {
            addCriterion("studentCount <>", value, "studentCount");
            return (Criteria) this;
        }

        public Criteria andStudentCountGreaterThan(Integer value) {
            addCriterion("studentCount >", value, "studentCount");
            return (Criteria) this;
        }

        public Criteria andStudentCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("studentCount >=", value, "studentCount");
            return (Criteria) this;
        }

        public Criteria andStudentCountLessThan(Integer value) {
            addCriterion("studentCount <", value, "studentCount");
            return (Criteria) this;
        }

        public Criteria andStudentCountLessThanOrEqualTo(Integer value) {
            addCriterion("studentCount <=", value, "studentCount");
            return (Criteria) this;
        }

        public Criteria andStudentCountIn(List<Integer> values) {
            addCriterion("studentCount in", values, "studentCount");
            return (Criteria) this;
        }

        public Criteria andStudentCountNotIn(List<Integer> values) {
            addCriterion("studentCount not in", values, "studentCount");
            return (Criteria) this;
        }

        public Criteria andStudentCountBetween(Integer value1, Integer value2) {
            addCriterion("studentCount between", value1, value2, "studentCount");
            return (Criteria) this;
        }

        public Criteria andStudentCountNotBetween(Integer value1, Integer value2) {
            addCriterion("studentCount not between", value1, value2, "studentCount");
            return (Criteria) this;
        }

        public Criteria andRatioIsNull() {
            addCriterion("ratio is null");
            return (Criteria) this;
        }

        public Criteria andRatioIsNotNull() {
            addCriterion("ratio is not null");
            return (Criteria) this;
        }

        public Criteria andRatioEqualTo(Integer value) {
            addCriterion("ratio =", value, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioNotEqualTo(Integer value) {
            addCriterion("ratio <>", value, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioGreaterThan(Integer value) {
            addCriterion("ratio >", value, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioGreaterThanOrEqualTo(Integer value) {
            addCriterion("ratio >=", value, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioLessThan(Integer value) {
            addCriterion("ratio <", value, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioLessThanOrEqualTo(Integer value) {
            addCriterion("ratio <=", value, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioIn(List<Integer> values) {
            addCriterion("ratio in", values, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioNotIn(List<Integer> values) {
            addCriterion("ratio not in", values, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioBetween(Integer value1, Integer value2) {
            addCriterion("ratio between", value1, value2, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioNotBetween(Integer value1, Integer value2) {
            addCriterion("ratio not between", value1, value2, "ratio");
            return (Criteria) this;
        }

        public Criteria andIsFullIsNull() {
            addCriterion("isFull is null");
            return (Criteria) this;
        }

        public Criteria andIsFullIsNotNull() {
            addCriterion("isFull is not null");
            return (Criteria) this;
        }

        public Criteria andIsFullEqualTo(Boolean value) {
            addCriterion("isFull =", value, "isFull");
            return (Criteria) this;
        }

        public Criteria andIsFullNotEqualTo(Boolean value) {
            addCriterion("isFull <>", value, "isFull");
            return (Criteria) this;
        }

        public Criteria andIsFullGreaterThan(Boolean value) {
            addCriterion("isFull >", value, "isFull");
            return (Criteria) this;
        }

        public Criteria andIsFullGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isFull >=", value, "isFull");
            return (Criteria) this;
        }

        public Criteria andIsFullLessThan(Boolean value) {
            addCriterion("isFull <", value, "isFull");
            return (Criteria) this;
        }

        public Criteria andIsFullLessThanOrEqualTo(Boolean value) {
            addCriterion("isFull <=", value, "isFull");
            return (Criteria) this;
        }

        public Criteria andIsFullIn(List<Boolean> values) {
            addCriterion("isFull in", values, "isFull");
            return (Criteria) this;
        }

        public Criteria andIsFullNotIn(List<Boolean> values) {
            addCriterion("isFull not in", values, "isFull");
            return (Criteria) this;
        }

        public Criteria andIsFullBetween(Boolean value1, Boolean value2) {
            addCriterion("isFull between", value1, value2, "isFull");
            return (Criteria) this;
        }

        public Criteria andIsFullNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isFull not between", value1, value2, "isFull");
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