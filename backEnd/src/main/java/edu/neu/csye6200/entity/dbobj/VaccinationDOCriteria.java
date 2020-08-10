package edu.neu.csye6200.entity.dbobj;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VaccinationDOCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VaccinationDOCriteria() {
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

        public Criteria andStudentIdIsNull() {
            addCriterion("studentId is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("studentId is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(Integer value) {
            addCriterion("studentId =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(Integer value) {
            addCriterion("studentId <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(Integer value) {
            addCriterion("studentId >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("studentId >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(Integer value) {
            addCriterion("studentId <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(Integer value) {
            addCriterion("studentId <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<Integer> values) {
            addCriterion("studentId in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<Integer> values) {
            addCriterion("studentId not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(Integer value1, Integer value2) {
            addCriterion("studentId between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("studentId not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andRecordDateIsNull() {
            addCriterion("recordDate is null");
            return (Criteria) this;
        }

        public Criteria andRecordDateIsNotNull() {
            addCriterion("recordDate is not null");
            return (Criteria) this;
        }

        public Criteria andRecordDateEqualTo(Date value) {
            addCriterion("recordDate =", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateNotEqualTo(Date value) {
            addCriterion("recordDate <>", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateGreaterThan(Date value) {
            addCriterion("recordDate >", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateGreaterThanOrEqualTo(Date value) {
            addCriterion("recordDate >=", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateLessThan(Date value) {
            addCriterion("recordDate <", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateLessThanOrEqualTo(Date value) {
            addCriterion("recordDate <=", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateIn(List<Date> values) {
            addCriterion("recordDate in", values, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateNotIn(List<Date> values) {
            addCriterion("recordDate not in", values, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateBetween(Date value1, Date value2) {
            addCriterion("recordDate between", value1, value2, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateNotBetween(Date value1, Date value2) {
            addCriterion("recordDate not between", value1, value2, "recordDate");
            return (Criteria) this;
        }

        public Criteria andVaccinationNumberIsNull() {
            addCriterion("vaccinationNumber is null");
            return (Criteria) this;
        }

        public Criteria andVaccinationNumberIsNotNull() {
            addCriterion("vaccinationNumber is not null");
            return (Criteria) this;
        }

        public Criteria andVaccinationNumberEqualTo(Integer value) {
            addCriterion("vaccinationNumber =", value, "vaccinationNumber");
            return (Criteria) this;
        }

        public Criteria andVaccinationNumberNotEqualTo(Integer value) {
            addCriterion("vaccinationNumber <>", value, "vaccinationNumber");
            return (Criteria) this;
        }

        public Criteria andVaccinationNumberGreaterThan(Integer value) {
            addCriterion("vaccinationNumber >", value, "vaccinationNumber");
            return (Criteria) this;
        }

        public Criteria andVaccinationNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("vaccinationNumber >=", value, "vaccinationNumber");
            return (Criteria) this;
        }

        public Criteria andVaccinationNumberLessThan(Integer value) {
            addCriterion("vaccinationNumber <", value, "vaccinationNumber");
            return (Criteria) this;
        }

        public Criteria andVaccinationNumberLessThanOrEqualTo(Integer value) {
            addCriterion("vaccinationNumber <=", value, "vaccinationNumber");
            return (Criteria) this;
        }

        public Criteria andVaccinationNumberIn(List<Integer> values) {
            addCriterion("vaccinationNumber in", values, "vaccinationNumber");
            return (Criteria) this;
        }

        public Criteria andVaccinationNumberNotIn(List<Integer> values) {
            addCriterion("vaccinationNumber not in", values, "vaccinationNumber");
            return (Criteria) this;
        }

        public Criteria andVaccinationNumberBetween(Integer value1, Integer value2) {
            addCriterion("vaccinationNumber between", value1, value2, "vaccinationNumber");
            return (Criteria) this;
        }

        public Criteria andVaccinationNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("vaccinationNumber not between", value1, value2, "vaccinationNumber");
            return (Criteria) this;
        }

        public Criteria andRequiredNumberIsNull() {
            addCriterion("requiredNumber is null");
            return (Criteria) this;
        }

        public Criteria andRequiredNumberIsNotNull() {
            addCriterion("requiredNumber is not null");
            return (Criteria) this;
        }

        public Criteria andRequiredNumberEqualTo(Integer value) {
            addCriterion("requiredNumber =", value, "requiredNumber");
            return (Criteria) this;
        }

        public Criteria andRequiredNumberNotEqualTo(Integer value) {
            addCriterion("requiredNumber <>", value, "requiredNumber");
            return (Criteria) this;
        }

        public Criteria andRequiredNumberGreaterThan(Integer value) {
            addCriterion("requiredNumber >", value, "requiredNumber");
            return (Criteria) this;
        }

        public Criteria andRequiredNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("requiredNumber >=", value, "requiredNumber");
            return (Criteria) this;
        }

        public Criteria andRequiredNumberLessThan(Integer value) {
            addCriterion("requiredNumber <", value, "requiredNumber");
            return (Criteria) this;
        }

        public Criteria andRequiredNumberLessThanOrEqualTo(Integer value) {
            addCriterion("requiredNumber <=", value, "requiredNumber");
            return (Criteria) this;
        }

        public Criteria andRequiredNumberIn(List<Integer> values) {
            addCriterion("requiredNumber in", values, "requiredNumber");
            return (Criteria) this;
        }

        public Criteria andRequiredNumberNotIn(List<Integer> values) {
            addCriterion("requiredNumber not in", values, "requiredNumber");
            return (Criteria) this;
        }

        public Criteria andRequiredNumberBetween(Integer value1, Integer value2) {
            addCriterion("requiredNumber between", value1, value2, "requiredNumber");
            return (Criteria) this;
        }

        public Criteria andRequiredNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("requiredNumber not between", value1, value2, "requiredNumber");
            return (Criteria) this;
        }

        public Criteria andIsCompletedIsNull() {
            addCriterion("isCompleted is null");
            return (Criteria) this;
        }

        public Criteria andIsCompletedIsNotNull() {
            addCriterion("isCompleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsCompletedEqualTo(Short value) {
            addCriterion("isCompleted =", value, "isCompleted");
            return (Criteria) this;
        }

        public Criteria andIsCompletedNotEqualTo(Short value) {
            addCriterion("isCompleted <>", value, "isCompleted");
            return (Criteria) this;
        }

        public Criteria andIsCompletedGreaterThan(Short value) {
            addCriterion("isCompleted >", value, "isCompleted");
            return (Criteria) this;
        }

        public Criteria andIsCompletedGreaterThanOrEqualTo(Short value) {
            addCriterion("isCompleted >=", value, "isCompleted");
            return (Criteria) this;
        }

        public Criteria andIsCompletedLessThan(Short value) {
            addCriterion("isCompleted <", value, "isCompleted");
            return (Criteria) this;
        }

        public Criteria andIsCompletedLessThanOrEqualTo(Short value) {
            addCriterion("isCompleted <=", value, "isCompleted");
            return (Criteria) this;
        }

        public Criteria andIsCompletedIn(List<Short> values) {
            addCriterion("isCompleted in", values, "isCompleted");
            return (Criteria) this;
        }

        public Criteria andIsCompletedNotIn(List<Short> values) {
            addCriterion("isCompleted not in", values, "isCompleted");
            return (Criteria) this;
        }

        public Criteria andIsCompletedBetween(Short value1, Short value2) {
            addCriterion("isCompleted between", value1, value2, "isCompleted");
            return (Criteria) this;
        }

        public Criteria andIsCompletedNotBetween(Short value1, Short value2) {
            addCriterion("isCompleted not between", value1, value2, "isCompleted");
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