package com.isaproject.isaproject.Validation;

import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Model.HelpModel.LoyaltyProgram;
import com.isaproject.isaproject.Model.Users.Supplier;

import java.util.regex.Pattern;

public class CommonValidatior {
    public Boolean checkValidationPersonUser(PersonUserDTO person) {
        if(person.getEmail().isEmpty() || !regexValidationEmail(person.getEmail())) {
            throw new IllegalArgumentException("Please fill all the required fields!");
        }
        if(person.getPassword().isEmpty() || person.getRewritePassword().isEmpty() ||person.getSurname().isEmpty()
                || person.getFirstname().isEmpty() || person.getPhonenumber().isEmpty() ||
                person.getAddress().getCountry().isEmpty() || person.getAddress().getTown().isEmpty() ||
                person.getAddress().getStreet().isEmpty()) {
            throw new IllegalArgumentException("Please fill all the required fields!");
        }
        if(person.getAddress().getNumber() !=(int)person.getAddress().getNumber()
            ||person.getAddress().getPostalCode() !=(int)person.getAddress().getPostalCode() ) {
            throw new IllegalArgumentException("Please fill all the required fields correctly!");
        }
        if(person.getAddress().getNumber()<=0 || person.getAddress().getPostalCode()<=0) {
            throw new IllegalArgumentException("Please fill all the required fields correctly!");
        }
        return true;
    }
    public Boolean checkValidationDermatologist(DermatologistDTO person) {
        if(person.getEmail().isEmpty() || !regexValidationEmail(person.getEmail())) {
            throw new IllegalArgumentException("Please fill all the required fields!");
        }
        if(person.getPassword().isEmpty() || person.getRewritePassword().isEmpty() ||person.getSurname().isEmpty()
                || person.getFirstname().isEmpty() || person.getPhonenumber().isEmpty() ||
                person.getAddress().getCountry().isEmpty() || person.getAddress().getTown().isEmpty() ||
                person.getAddress().getStreet().isEmpty()) {
            throw new IllegalArgumentException("Please fill all the required fields!");
        }
        if(person.getAddress().getNumber() !=(int)person.getAddress().getNumber()
                ||person.getAddress().getPostalCode() !=(int)person.getAddress().getPostalCode() ) {
            throw new IllegalArgumentException("Please fill all the required fields correctly!");
        }
        if(person.getAddress().getNumber()<=0 || person.getAddress().getPostalCode()<=0) {
            throw new IllegalArgumentException("Please fill all the required fields correctly!");
        }
        return true;
    }
    public boolean regexValidationEmail(String regex) {
        return Pattern.matches("^[a-zA-Za-z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Za-z0-9.-]+$",regex);
    }

    public boolean checkValidationPharmacyAdmin(PharmacyAdminDTO person) {
        if(person.getEmail().isEmpty() || !regexValidationEmail(person.getEmail())) {
            throw new IllegalArgumentException("Please fill all the required fields!");
        }
        if(person.getPassword().isEmpty() || person.getRewritePassword().isEmpty() ||person.getSurname().isEmpty()
                || person.getFirstname().isEmpty() || person.getPhonenumber().isEmpty() ||
                person.getAddress().getCountry().isEmpty() || person.getAddress().getTown().isEmpty() ||
                person.getAddress().getStreet().isEmpty()) {
            throw new IllegalArgumentException("Please fill all the required fields!");
        }
        if(person.getAddress().getNumber() !=(int)person.getAddress().getNumber()
                ||person.getAddress().getPostalCode() !=(int)person.getAddress().getPostalCode() ) {
            throw new IllegalArgumentException("Please fill all the required fields correctly!");
        }
        if(person.getAddress().getNumber()<=0 || person.getAddress().getPostalCode()<=0) {
            throw new IllegalArgumentException("Please fill all the required fields correctly!");
        }
        if(person.getPharmacyId()==null || person.getPharmacyId()<=0) {
            throw new IllegalArgumentException("Please select the pharmacy!");
        }
         return true;
    }

    public boolean checkValidatioPharmacy(PharmacyDTO pharmacyDTO) {
        if(pharmacyDTO.getPharmacyName().isEmpty() ||
                pharmacyDTO.getAddress().getCountry().isEmpty() || pharmacyDTO.getAddress().getTown().isEmpty() ||
                pharmacyDTO.getAddress().getStreet().isEmpty() || pharmacyDTO.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Please fill all the required fields!");
        }

        if(pharmacyDTO.getAddress().getNumber() !=(int)pharmacyDTO.getAddress().getNumber()
                ||pharmacyDTO.getAddress().getPostalCode() !=(int)pharmacyDTO.getAddress().getPostalCode()
                || pharmacyDTO.getConsultingPrice()!=(int)pharmacyDTO.getConsultingPrice()) {
            throw new IllegalArgumentException("Please fill all the required fields correctly!");
        }
        if(pharmacyDTO.getAddress().getNumber()<=0 || pharmacyDTO.getAddress().getPostalCode()<=0 ||
            pharmacyDTO.getConsultingPrice()<=0) {
            throw new IllegalArgumentException("Please fill all the required fields correctly!");
        }
        return true;
    }

    public boolean checkComplaint(ComplaintDTO complaintDTO) {
        if(complaintDTO.getMassage().isEmpty() || complaintDTO.getSubject().isEmpty()) {
            throw new IllegalArgumentException("Please fill all the required fields correctly!");
        }
        if(complaintDTO.getPharmacist()==null && complaintDTO.getDermatologist()==null && complaintDTO.getPharmacist()==null) {
            throw new IllegalArgumentException("Please choose subject of complaint!");
        }
        return true;
    }

    public boolean checkEprescription(ChoosenPharmacyDTO choosenPharmacy) {
        if(choosenPharmacy.getPharmacyId()<=0 || choosenPharmacy.getPharmacyId()!=(int)choosenPharmacy.getPharmacyId()) {
            return false;
        }
        if(choosenPharmacy.getMedications()==null) {
            return false;
        }
        return true;
    }

    public boolean checkValidationLoyaltyProgram2(LoyaltyProgram loyaltyProgramDTO) {
        if(loyaltyProgramDTO.getConsultingPoints()<=0 || loyaltyProgramDTO.getConsultingPoints()!=(double)loyaltyProgramDTO.getConsultingPoints()) return false;
        if(loyaltyProgramDTO.getExaminationPoints()<=0 || loyaltyProgramDTO.getExaminationPoints()!=(double)loyaltyProgramDTO.getExaminationPoints()) return false;
        if(loyaltyProgramDTO.getGoldenDiscount()<=0 || loyaltyProgramDTO.getGoldenDiscount()!=(double)loyaltyProgramDTO.getGoldenDiscount()) return false;
        if(loyaltyProgramDTO.getGoldLimit()<=0 || loyaltyProgramDTO.getGoldLimit()!=(double)loyaltyProgramDTO.getGoldLimit()) return false;
        if(loyaltyProgramDTO.getRegularDiscount()<=0 || loyaltyProgramDTO.getRegularDiscount()!=(double)loyaltyProgramDTO.getRegularDiscount()) return false;
        if(loyaltyProgramDTO.getSilverLimit()<=0 || loyaltyProgramDTO.getSilverLimit()!=(double)loyaltyProgramDTO.getSilverLimit()) return false;
        if(loyaltyProgramDTO.getSilverDiscount()<=0 || loyaltyProgramDTO.getSilverDiscount()!=(double)loyaltyProgramDTO.getSilverDiscount()) return false;

        return true;
    }

    public boolean checkValidationMedication(MedicationDTO medicationDTO) {
        if(medicationDTO.getName().isEmpty() || medicationDTO.getForm().isEmpty() || medicationDTO.getType().isEmpty()) return false;
        if(medicationDTO.getIssuanceRegime().isEmpty() || medicationDTO.getWayOfSelling().isEmpty() ) return false;
        if(medicationDTO.getLoyaltyPoints()<=0 || medicationDTO.getLoyaltyPoints()!=(double)medicationDTO.getLoyaltyPoints()) return false;
        if(medicationDTO.getCode()<=0 || medicationDTO.getCode()!=(double)medicationDTO.getCode()) return false;
        if(medicationDTO.getSpecification().getContraIndications().isEmpty() || medicationDTO.getSpecification().getManufacturer().isEmpty()
            ||medicationDTO.getSpecification().getStructure().isEmpty() || medicationDTO.getSpecification().getRecommendedConsumption().isEmpty()) return false;

        return true;
    }

    public boolean checkValidationOffer(OfferDTO offerDTO) {
        if(offerDTO.getSupplier()<=0 || offerDTO.getSupplier()!=(int)offerDTO.getSupplier()) return false;
        if(offerDTO.getOrderId()<=0 || offerDTO.getOrderId()!=(int)offerDTO.getOrderId()) return false;
        return true;
    }

    public boolean checkValidationUpdateSupplier(Supplier person) {
        if(person.getPassword().isEmpty() || person.getSurname().isEmpty()
                || person.getName().isEmpty() || person.getPhoneNumber().isEmpty() ||
                person.getAddress().getCountry().isEmpty() || person.getAddress().getTown().isEmpty() ||
                person.getAddress().getStreet().isEmpty()) {
            throw new IllegalArgumentException("Please fill all the required fields!");
        }
        if(person.getAddress().getNumber() !=(int)person.getAddress().getNumber()
                ||person.getAddress().getPostalCode() !=(int)person.getAddress().getPostalCode() ) {
            throw new IllegalArgumentException("Please fill all the required fields correctly!");
        }
        if(person.getAddress().getNumber()<=0 || person.getAddress().getPostalCode()<=0) {
            throw new IllegalArgumentException("Please fill all the required fields correctly!");
        }
        return true;
    }

    public boolean checkValidationSupplierMedication(SupplierMedicationsDTO supplierMedicationsDTO) {
        if(supplierMedicationsDTO.getName().isEmpty()) return false;
        if(supplierMedicationsDTO.getCode()<=0 || supplierMedicationsDTO.getCode()!=(double)supplierMedicationsDTO.getCode()) return false;
        if(supplierMedicationsDTO.getQuantity() !=(int)supplierMedicationsDTO.getQuantity() || supplierMedicationsDTO.getQuantity()<=0 ) return false;
            return true;
    }
}
