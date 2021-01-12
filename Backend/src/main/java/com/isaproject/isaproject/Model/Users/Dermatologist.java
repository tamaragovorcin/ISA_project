package com.isaproject.isaproject.Model.Users;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//ovom anotacijom se navodi vrednost diskriminatorske kolone koja vazi za
//objekte ove klase

@Entity
@DiscriminatorValue("Dermatologist")
public class Dermatologist extends PersonUser{

    @Column(name = "markDermatologist", nullable = true)
    private double markDermatologist;

    @OneToMany(mappedBy = "dermatologist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ExaminationSchedule> examinationSchedules = new HashSet<ExaminationSchedule>();

    public Dermatologist() {
    }

    public Set<ExaminationSchedule> getExaminationSchedules() {
        return examinationSchedules;
    }

    public void setExaminationSchedules(Set<ExaminationSchedule> examinationSchedules) {
        this.examinationSchedules = examinationSchedules;
    }

    public double getMarkDermatologist() {
        return markDermatologist;
    }

    public void setMarkDermatologist(double mark) {
        this.markDermatologist = mark;
    }
}
