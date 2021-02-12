package com.isaproject.isaproject.DTO;

import javax.persistence.criteria.CriteriaBuilder;

public class GraphicExaminationQuarterDTO {
    private  Integer first;
    private  Integer second;
    private  Integer third;

    public GraphicExaminationQuarterDTO() {
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public Integer getThird() {
        return third;
    }

    public void setThird(Integer third) {
        this.third = third;
    }

}
