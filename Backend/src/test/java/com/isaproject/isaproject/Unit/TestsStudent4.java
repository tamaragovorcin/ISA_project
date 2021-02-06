package com.isaproject.isaproject.Unit;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import static com.isaproject.isaproject.Unit.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Supplier;
import com.isaproject.isaproject.Model.Users.SystemAdmin;
import com.isaproject.isaproject.Repository.PharmacyRepository;
import com.isaproject.isaproject.Repository.SupplierRepository;
import com.isaproject.isaproject.Repository.SystemAdminRepository;
import com.isaproject.isaproject.Service.Implementations.PharmacyService;
import com.isaproject.isaproject.Service.Implementations.SupplierService;
import com.isaproject.isaproject.Service.Implementations.SystemAdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestsStudent4 {

    @Mock
    private PharmacyRepository pharmacyRepositoryMock;
    @Mock
    private Pharmacy pharmacyMock;
    @InjectMocks
    private PharmacyService pharmacyServiceMock;

    @Mock
    private SupplierRepository supplierRepositoryMock;
    @Mock
    private Supplier supplierMock;
    @InjectMocks
    private SupplierService supplierServiceMock;

    @Mock
    private SystemAdminRepository systemAdminRepositoryMock;
    @Mock
    private SystemAdmin systemAdminMock;
    @InjectMocks
    private SystemAdminService systemAdminServiceMock;


    @Test
    @Transactional
    @Rollback(true)
    public void testAddPharmacy() {
        // 1.Ponasanje
        Pharmacy pharmacy = new Pharmacy(ID, NAME, 5, DESCRIPTION, 100 ,ADDRESS);
        when(pharmacyRepositoryMock.findAll()).thenReturn(Arrays.asList(new Pharmacy(ID2, NAME2, 5, DESCRIPTION, 100,ADDRESS2)));
        when(pharmacyRepositoryMock.save(pharmacy)).thenReturn(pharmacy);

        // 2. Akcija
        int dbSizeBeforeAdd = pharmacyServiceMock.findAll().size();
        Pharmacy dbPharmacy = pharmacyRepositoryMock.save(pharmacy);

        when(pharmacyServiceMock.findAll()).thenReturn(Arrays.asList(new Pharmacy(ID2, NAME2, 5, DESCRIPTION, 100,ADDRESS2),dbPharmacy));

        //3.Verifikacija
        assertThat(dbPharmacy).isNotNull();
        List<Pharmacy> pharmacies = pharmacyServiceMock.findAll();
        assertThat(pharmacies).hasSize(dbSizeBeforeAdd + 1);
        dbPharmacy = pharmacies.get(pharmacies.size() - 1);
        assertThat(dbPharmacy.getPharmacyName()).isEqualTo(NAME);
        verify(pharmacyRepositoryMock, times(2)).findAll();
        verify(pharmacyRepositoryMock, times(1)).save(pharmacy);
    }

    @Test
    public void testFindsOneSupplier() {
        // 1. Definisanje ponašanja
        when(supplierRepositoryMock.findById(ID)).thenReturn(Optional.of(supplierMock));

        // 2. Akcija
        Supplier dbSupplier = supplierServiceMock.findById(ID);

        // 3. Verifikacija
        assertEquals(supplierMock, dbSupplier);
        verify(supplierRepositoryMock, times(1)).findById(ID);
        verifyNoMoreInteractions(supplierRepositoryMock);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateSupplier() {
        // 1. Definisanje ponašanja
        when(supplierRepositoryMock.findById(ID)).thenReturn(Optional.of(new Supplier(ID, NAME2, SURNAME2, EMAIL2, PASSWORD2 , PHONENUMBER2,false,
                true,null,AUTHORITIES,ADDRESS2, null,null)));

        // 2. Akcija
        Supplier supplierForUpdate = supplierRepositoryMock.findById(ID).get();
        supplierForUpdate.setName(NEW_NAME);
        supplierForUpdate.setSurname(NEW_SURNAME);
        when(supplierRepositoryMock.save(supplierForUpdate)).thenReturn(supplierForUpdate);
        supplierForUpdate = supplierRepositoryMock.save(supplierForUpdate);

        // 3. Verifikacija
        assertThat(supplierForUpdate).isNotNull();
        supplierForUpdate = supplierServiceMock.findById(ID);
        assertThat(supplierForUpdate.getName()).isEqualTo(NEW_NAME);
        assertThat(supplierForUpdate.getSurname()).isEqualTo(NEW_SURNAME);
        verify(supplierRepositoryMock, times(2)).findById(ID);
        verify(supplierRepositoryMock, times(1)).save(supplierForUpdate);
        verifyNoMoreInteractions(supplierRepositoryMock);
    }

    @Test
    public void testFindSystemAdminByEmail() {
        // 1. Definisanje ponašanja
        when(systemAdminRepositoryMock.findByEmail(EMAIL)).thenReturn(new SystemAdmin(ID, NAME, SURNAME, EMAIL, PASSWORD , PHONENUMBER,false,
                true,null,AUTHORITIES,ADDRESS, true));

        // 2. Akcija
        SystemAdmin dbSystemAdmin = systemAdminServiceMock.findByEmail(EMAIL);

        // 3. Verifikacija
        assertThat(dbSystemAdmin).isNotNull();
        assertThat(dbSystemAdmin.getId()).isEqualTo(ID);
        verify(systemAdminRepositoryMock, times(1)).findByEmail(EMAIL);
        verifyNoMoreInteractions(systemAdminRepositoryMock);
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    @Rollback(true)
    public void testAddNullEmail() {
        SystemAdmin systemAdmin = new SystemAdmin();
        systemAdmin.setName(NAME);

        // 1. Definisanje ponašanja
        when(systemAdminRepositoryMock.save(systemAdmin)).thenThrow(DataIntegrityViolationException.class);

        // 2. Akcija
        systemAdminRepositoryMock.save(systemAdmin);

        // 3. Verifikacija
        verify(systemAdminRepositoryMock, times(1)).save(systemAdmin);
        verifyNoMoreInteractions(systemAdminRepositoryMock);
    }
}
