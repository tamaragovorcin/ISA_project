package com.isaproject.isaproject.Model.HelpModel;

import com.isaproject.isaproject.Model.Entity;

public class MedicationAlternatives extends Entity {
    private long medicationCode;
    private long medicationCodeAlternative;

    public MedicationAlternatives() {
        super();
    }
    public MedicationAlternatives(int id, long medicationCode, long medicationCodeAlternative) {
        super(id);
        this.medicationCode = medicationCode;
        this.medicationCodeAlternative = medicationCodeAlternative;
    }

    public MedicationAlternatives(long medicationCode, long medicationCodeAlternative) {
        this.medicationCode = medicationCode;
        this.medicationCodeAlternative = medicationCodeAlternative;
    }

    public long getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(long medicationCode) {
        this.medicationCode = medicationCode;
    }

    public long getMedicationCodeAlternative() {
        return medicationCodeAlternative;
    }

    public void setMedicationCodeAlternative(long medicationCodeAlternative) {
        this.medicationCodeAlternative = medicationCodeAlternative;
    }
}
