package com.isaproject.isaproject.Model.Examinations;

import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import javax.persistence.*;


@Entity
public class Examination {


    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false, unique = false)
    private Patient patient;

    @Column(name = "cancelled", nullable = true)
    private Boolean cancelled;


    @Column(name = "showedUp", nullable = true)
    private Boolean showedUp;

    @OneToOne
    @JoinColumn(name = "examination_id", referencedColumnName = "id", nullable = true, unique = false)
    private ExaminationSchedule examinationSchedule;

    @Column(name = "information", nullable = true)
    private String information;

    public Examination() {

    }

    public ExaminationSchedule getExaminationSchedule() {
        return examinationSchedule;
    }

    public void setExaminationSchedule(ExaminationSchedule examinationSchedule) {
        this.examinationSchedule = examinationSchedule;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Boolean getShowedUp() {
        return showedUp;
    }

    public void setShowedUp(Boolean showedUp) {
        this.showedUp = showedUp;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
