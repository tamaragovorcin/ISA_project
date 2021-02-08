package com.isaproject.isaproject.Unit;

import com.isaproject.isaproject.DTO.ExaminationDTO;
import com.isaproject.isaproject.DTO.MedicationReservationDTO;
import com.isaproject.isaproject.Model.Examinations.*;
import com.isaproject.isaproject.Model.HelpModel.Complaint;
import com.isaproject.isaproject.Model.HelpModel.Grading;
import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.HelpModel.PatientsMedicationAlergy;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.*;
import com.isaproject.isaproject.Repository.*;
import com.isaproject.isaproject.Service.Implementations.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.isaproject.isaproject.Unit.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestsStudent1 {

    @Mock
    private ExaminationScheduleRepository examinationRepositoryMock;
    @Mock
    private ExaminationSchedule examinationMock;
    @InjectMocks
    private ExaminationScheduleService examinationServiceMock;


    @Mock
    private MedicationReservationRepository medicationReservationRepositoryMock;
    @Mock
    private MedicationReservation medicationReservationMock;
    @InjectMocks
    private MedicationReservationService medicationReservationServiceMock;


    @Mock
    private PatientRepository patientRepositoryMock;
    @Mock
    private Patient patientMock;
    @InjectMocks
    private PatientService patientServiceMock;

    @Mock
    private MarkMedicationRepository markMedicationRepositoryMock;
    @Mock
    private MarkMedication markMedicationMock;
    @InjectMocks
    private MarkMedicationService markMedicationServiceMock;


    @Mock
    private PharmacyRepository pharmacyRepositoryMock;
    @Mock
    private Pharmacy pharmacyMock;
    @InjectMocks
    private PharmacyService pharmacyServiceMock;

    @Test
    public void testFindExamination() {
        // 1. Definisanje ponašanja
        when(examinationRepositoryMock.findById(ID)).thenReturn(Optional.of(examinationMock));

        // 2. Akcija
        ExaminationSchedule dbExamination = examinationServiceMock.findById(ID);

        // 3. Verifikacija
        assertEquals(examinationMock, dbExamination);
        verify(examinationRepositoryMock, times(1)).findById(ID);
        verifyNoMoreInteractions(examinationRepositoryMock);
    }



    @Test
    @Transactional
    @Rollback(true)
    public void testSaveMedicationReservation() {
        // 1.Ponasanje
        MedicationReservation medicationReservation = new MedicationReservation(ID, PATIENT, MEDICATION, PHARMACY, LocalDate.now(), LocalDate.now().plusDays(3), false, UUID.randomUUID(),false);
        when(medicationReservationRepositoryMock.findAll()).thenReturn(Arrays.asList(new MedicationReservation(ID2, PATIENT2, MEDICATION, PHARMACY, LocalDate.now(), LocalDate.now().plusDays(3), false, UUID.randomUUID(),false)));
        when(medicationReservationRepositoryMock.save(medicationReservation)).thenReturn(medicationReservation);

        // 2. Akcija
        int dbSizeBeforeAdd = medicationReservationServiceMock.findAll().size();
        MedicationReservation dbMedicationReservation = medicationReservationRepositoryMock.save(medicationReservation);

        when(medicationReservationServiceMock.findAll()).thenReturn(Arrays.asList(new MedicationReservation(ID2, PATIENT2, MEDICATION, PHARMACY, LocalDate.now(), LocalDate.now().plusDays(3), false, UUID.randomUUID(),false),dbMedicationReservation));

        //3.Verifikacija
        assertThat(dbMedicationReservation).isNotNull();
        List<MedicationReservation> medicationReservations = medicationReservationServiceMock.findAll();
        assertThat(medicationReservations).hasSize(dbSizeBeforeAdd + 1);
        dbMedicationReservation = medicationReservations.get(medicationReservations.size() - 1);
        assertThat(dbMedicationReservation.getPatient().getName()).isEqualTo(PATIENT.getName());
        verify(medicationReservationRepositoryMock, times(2)).findAll();
        verify(medicationReservationRepositoryMock, times(1)).save(medicationReservation);
    }


    @Test
    @Transactional
    @Rollback(true)
    public void testUpdatePatient() {
        // 1. Definisanje ponašanja

        when(patientRepositoryMock.findById(ID)).thenReturn(Optional.of(new Patient(ID, NAME2, SURNAME2, EMAIL2, PASSWORD2 , PHONENUMBER2,false,
                true,null,AUTHORITIES,ADDRESS2, null,null,null,null,null,null,null,null,0,0,"REGULAR", 0)));

        // 2. Akcija
        Patient patientForUpdate = patientRepositoryMock.findById(ID).get();
        patientForUpdate.setName(NEW_NAME);
        patientForUpdate.setSurname(NEW_SURNAME);
        when(patientRepositoryMock.save(patientForUpdate)).thenReturn(patientForUpdate);
        patientForUpdate = patientRepositoryMock.save(patientForUpdate);

        // 3. Verifikacija
        assertThat(patientForUpdate).isNotNull();
        patientForUpdate = patientServiceMock.findById(ID);
        assertThat(patientForUpdate.getName()).isEqualTo(NEW_NAME);
        assertThat(patientForUpdate.getSurname()).isEqualTo(NEW_SURNAME);
        verify(patientRepositoryMock, times(2)).findById(ID);
        verify(patientRepositoryMock, times(1)).save(patientForUpdate);
        verifyNoMoreInteractions(patientRepositoryMock);
    }


    @Test
    @Transactional
    @Rollback(true)
    public void testSaveMarkMedication() {
        // 1.Ponasanje
        MarkMedication markMedication = new MarkMedication(ID, 0, 1,0,0,0, MEDICATION, PATIENT, 2 );
        when(markMedicationRepositoryMock.findAll()).thenReturn(Arrays.asList(new MarkMedication(ID2, 0, 0,3,0,0, MEDICATION, PATIENT, 3)));
        when(markMedicationRepositoryMock.save(markMedication)).thenReturn(markMedication);

        // 2. Akcija
        int dbSizeBeforeAdd = markMedicationRepositoryMock.findAll().size();
        MarkMedication dbMarkMedication = markMedicationRepositoryMock.save(markMedication);

        when(markMedicationRepositoryMock.findAll()).thenReturn(Arrays.asList(new MarkMedication(ID2, 0, 0,3,0,0, MEDICATION, PATIENT, 3),dbMarkMedication));

        //3.Verifikacija
        assertThat(dbMarkMedication).isNotNull();
        List<MarkMedication> markMedications = markMedicationRepositoryMock.findAll();
        assertThat(markMedications).hasSize(dbSizeBeforeAdd + 1);
        dbMarkMedication = markMedications.get(markMedications.size() - 1);
        assertThat(dbMarkMedication.getPatient().getName()).isEqualTo(PATIENT.getName());
        verify(markMedicationRepositoryMock, times(2)).findAll();
        verify(markMedicationRepositoryMock, times(1)).save(dbMarkMedication);
    }

    @Test
    public void testFindPharmacyById() {
        // 1. Definisanje ponašanja
        when(pharmacyRepositoryMock.findById(ID)).thenReturn(Optional.of(pharmacyMock));

        // 2. Akcija
        Pharmacy dbPharmacy = pharmacyServiceMock.findById(ID);

        // 3. Verifikacija
        assertEquals(pharmacyMock, dbPharmacy);
        verify(pharmacyRepositoryMock, times(1)).findById(ID);
        verifyNoMoreInteractions(pharmacyRepositoryMock);
    }


}
