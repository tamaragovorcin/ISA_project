package com.isaproject.isaproject.Unit;

import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Pharmacy.Actions;
import com.isaproject.isaproject.Model.Schedule.HolidaySchedulePharmacist;
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

import static com.isaproject.isaproject.Unit.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestsStudent2 {
    @Mock
    private MedicationPriceRepository medicationPriceRepositoryMock;
    @Mock
    private MedicationPrice medicationPriceMock;
    @InjectMocks
    private MedicationPriceService medicationPriceServiceMock;

    @Mock
    private ActionsRepository actionsRepositoryMock;
    @Mock
    private Actions actionMock;
    @InjectMocks
    private ActionsService actionsServiceMock;

    @Mock
    private MedicationRepository medicationRepositoryMock;
    @Mock
    private Medication medicationMock;
    @InjectMocks
    private MedicationService medicationServiceMock;

    @Mock
    private HolidaySchedulePharmacistRepository holidaySchedulePharmacistRepositoryMock;
    @Mock
    private HolidaySchedulePharmacist holidaySchedulePharmacistMock;
    @Mock
    private HolidaySchedulePharmacistService holidaySchedulePharmacistServiceMock;





    @Test
    @Transactional
    @Rollback(true)
    public void testAddMedicationInPharmacy() {
        // 1.Ponasanje
        MedicationPrice medicationPrice = new MedicationPrice(ID, MEDICATION,250,1000,LocalDate.now(),PHARMACY);

        when(medicationPriceRepositoryMock.findAll()).thenReturn(Arrays.asList(new MedicationPrice(ID2, MEDICATION2,250,1000,LocalDate.now(),PHARMACY)));
        when(medicationPriceRepositoryMock.save(medicationPrice)).thenReturn(medicationPrice);

        // 2. Akcija
        int dbSizeBeforeAdd = medicationPriceServiceMock.findAll().size();
        MedicationPrice dbMedicationPrice= medicationPriceRepositoryMock.save(medicationPrice);

        when(medicationPriceServiceMock.findAll()).thenReturn(Arrays.asList(new MedicationPrice(ID2, MEDICATION2,250,1000,LocalDate.now(),PHARMACY),dbMedicationPrice));

        //3.Verifikacija
        assertThat(dbMedicationPrice).isNotNull();
        List<MedicationPrice> medicationPrices = medicationPriceServiceMock.findAll();
        assertThat(medicationPrices).hasSize(dbSizeBeforeAdd + 1);
        dbMedicationPrice = medicationPrices.get(medicationPrices.size() - 1);
        assertThat(dbMedicationPrice.getMedication().getName()).isEqualTo(MEDICATION.getName());
        verify(medicationPriceRepositoryMock, times(2)).findAll();
        verify(medicationPriceRepositoryMock, times(1)).save(medicationPrice);
    }
    @Test
    public void testFindOneActionById() {
        // 1. Definisanje ponašanja
        when(actionsRepositoryMock.findById(ID)).thenReturn(Optional.of(actionMock));

        // 2. Akcija
        Actions dbAction = actionsServiceMock.findById(ID);

        // 3. Verifikacija
        assertEquals(actionMock, dbAction);
        verify(actionsRepositoryMock, times(1)).findById(ID);
        verifyNoMoreInteractions(actionsRepositoryMock);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateActionDescription() {
        // 1. Definisanje ponašanja

        when(actionsRepositoryMock.findById(ID)).thenReturn(Optional.of(new Actions(ID, PHARMACY, DESCRIPTION, LocalDate.now().plusDays(10))));

        // 2. Akcija
        Actions actionForUpdate = actionsRepositoryMock.findById(ID).get();
        actionForUpdate.setDescription(NEW_DESCRIPTION);
        actionForUpdate.setExpiryDate(LocalDate.now().plusDays(5));
        when(actionsRepositoryMock.save(actionForUpdate)).thenReturn(actionForUpdate);
        actionForUpdate = actionsRepositoryMock.save(actionForUpdate);

        // 3. Verifikacija
        assertThat(actionForUpdate).isNotNull();
        actionForUpdate = actionsServiceMock.findById(ID);
        assertThat(actionForUpdate.getDescription()).isEqualTo(NEW_DESCRIPTION);
        assertThat(actionForUpdate.getExpiryDate()).isEqualTo(LocalDate.now().plusDays(5));
        verify(actionsRepositoryMock, times(2)).findById(ID);
        verify(actionsRepositoryMock, times(1)).save(actionForUpdate);
        verifyNoMoreInteractions(actionsRepositoryMock);
    }

    @Test
    public void testFindMedicationByCode() {
        // 1. Definisanje ponašanja
        when(medicationRepositoryMock.findByCode(CODE)).thenReturn(new Medication(ID, "Medication" ,  1234l));

        // 2. Akcija
        Medication dbMedication = medicationServiceMock.findByCode(CODE);

        // 3. Verifikacija
        assertThat(dbMedication).isNotNull();
        assertThat(dbMedication.getId()).isEqualTo(ID);
        verify(medicationRepositoryMock, times(1)).findByCode(CODE);
        verifyNoMoreInteractions(medicationRepositoryMock);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testSaveHolidayRequestPharmacist() {
        // 1.Ponasanje
        HolidaySchedulePharmacist holidaySchedulePharmacist = new HolidaySchedulePharmacist(ID,PHARMACIST, LocalDate.now(),LocalDate.now().plusDays(15),"APPROVED","Message","HOLIDAY" );
        when(holidaySchedulePharmacistRepositoryMock.findAll()).thenReturn(Arrays.asList(new HolidaySchedulePharmacist(ID2,PHARMACIST2, LocalDate.now(),LocalDate.now().plusDays(20),"APPROVED","Message","HOLIDAY")));
        when(holidaySchedulePharmacistRepositoryMock.save(holidaySchedulePharmacist)).thenReturn(holidaySchedulePharmacist);

        // 2. Akcija
        int dbSizeBeforeAdd = holidaySchedulePharmacistRepositoryMock.findAll().size();
        HolidaySchedulePharmacist dbHolidayPharmacist= holidaySchedulePharmacistRepositoryMock.save(holidaySchedulePharmacist);

        when(holidaySchedulePharmacistRepositoryMock.findAll()).thenReturn(Arrays.asList(new HolidaySchedulePharmacist(ID2,PHARMACIST2, LocalDate.now(),LocalDate.now().plusDays(20),"APPROVED","Message","HOLIDAY"),dbHolidayPharmacist));

        //3.Verifikacija
        assertThat(dbHolidayPharmacist).isNotNull();
        List<HolidaySchedulePharmacist> holidaySchedulePharmacists = holidaySchedulePharmacistRepositoryMock.findAll();
        assertThat(holidaySchedulePharmacists).hasSize(dbSizeBeforeAdd + 1);
        dbHolidayPharmacist = holidaySchedulePharmacists.get(holidaySchedulePharmacists.size() - 1);
        assertThat(dbHolidayPharmacist.getPharmacist().getId()).isEqualTo(PHARMACIST.getId());
        verify(holidaySchedulePharmacistRepositoryMock, times(2)).findAll();
        verify(holidaySchedulePharmacistRepositoryMock, times(1)).save(dbHolidayPharmacist);
    }

}

