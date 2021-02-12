package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.HelpModel.MedicationPriceHistory;
import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import com.isaproject.isaproject.Model.Users.PharmacyAdmin;
import com.isaproject.isaproject.Service.Implementations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;

@RestController
@RequestMapping("/api/graphical")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GraphicalController {
    @Autowired
    ExaminationScheduleService examinationScheduleService;
    @Autowired
    ExaminationService examinationService;
    @Autowired
    MedicationReservationService medicationReservationService;
    @Autowired
    PharmacyAdminService pharmacyAdminService;
    @Autowired
    MedicationPriceHistoryService  medicationPriceHistoryService;

    @GetMapping("/examinationYearly")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    GraphicExaminationYearlyDTO getNumberOfAppointmentsYearly() {
        int january = 0;
        int february = 0;
        int march = 0;
        int april = 0;
        int may = 0;
        int june = 0;
        int july = 0;
        int august = 0;
        int september = 0;
        int october = 0;
        int november = 0;
        int december = 0;
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser) currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        GraphicExaminationYearlyDTO graphicExaminationYearlyDTO = new GraphicExaminationYearlyDTO();
        for (ExaminationSchedule examinationSchedule : pharmacyAdmin.getPharmacy().getExaminationSchedules()) {
            if (examinationSchedule.getDate().getMonth() == Month.JANUARY && examinationSchedule.getFinished()) {
                january += 1;
            }
            if (examinationSchedule.getDate().getMonth() == Month.FEBRUARY) {
                february += 1;
            }
            if (examinationSchedule.getDate().getMonth() == Month.MARCH && examinationSchedule.getFinished()) {
                march += 1;
            }
            if (examinationSchedule.getDate().getMonth() == Month.APRIL && examinationSchedule.getFinished()) {
                april += 1;
            }
            if (examinationSchedule.getDate().getMonth() == Month.MAY && examinationSchedule.getFinished()) {
                may += 1;
            }
            if (examinationSchedule.getDate().getMonth() == Month.JUNE && examinationSchedule.getFinished()) {
                june += 1;
            }
            if (examinationSchedule.getDate().getMonth() == Month.JULY && examinationSchedule.getFinished()) {
                july += 1;
            }
            if (examinationSchedule.getDate().getMonth() == Month.AUGUST && examinationSchedule.getFinished()) {
                august += 1;
            }
            if (examinationSchedule.getDate().getMonth() == Month.SEPTEMBER && examinationSchedule.getFinished()) {
                september += 1;
            }
            if (examinationSchedule.getDate().getMonth() == Month.OCTOBER && examinationSchedule.getFinished()) {
                october += 1;
            }
            if (examinationSchedule.getDate().getMonth() == Month.NOVEMBER && examinationSchedule.getFinished()) {
                november += 1;
            }
            if (examinationSchedule.getDate().getMonth() == Month.DECEMBER && examinationSchedule.getFinished()) {
                december += 1;
            }
        }

        // }
        graphicExaminationYearlyDTO.setJanuary(january);
        graphicExaminationYearlyDTO.setFebruary(february);
        graphicExaminationYearlyDTO.setMarch(march);
        graphicExaminationYearlyDTO.setApril(april);
        graphicExaminationYearlyDTO.setMay(may);
        graphicExaminationYearlyDTO.setJune(june);
        graphicExaminationYearlyDTO.setJuly(july);
        graphicExaminationYearlyDTO.setAugust(august);
        graphicExaminationYearlyDTO.setSeptember(september);
        graphicExaminationYearlyDTO.setOctober(october);
        graphicExaminationYearlyDTO.setNovember(november);
        graphicExaminationYearlyDTO.setDecember(december);
        return graphicExaminationYearlyDTO;
    }

    @GetMapping("/examinationMonthly")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    GraphicExaminationMonthlyDTO getNumberOfAppointmentsMonthly() {
        Integer one = 0;
        Integer two = 0;
        Integer three = 0;
        Integer four = 0;
        Integer five = 0;
        Integer six = 0;
        Integer seven = 0;
        Integer eight = 0;
        Integer nine = 0;
        Integer ten = 0;
        Integer eleven = 0;
        Integer twelve = 0;
        Integer thirteen = 0;
        Integer fourteen = 0;
        Integer fifteen = 0;
        Integer sixteen = 0;
        Integer seventeen = 0;
        Integer eighteen = 0;
        Integer nineteen = 0;
        Integer twenty = 0;
        Integer twentyOne = 0;
        Integer twentyTwo = 0;
        Integer twentyThree = 0;
        Integer twentyFour = 0;
        Integer twentyFive = 0;
        Integer twentySix = 0;
        Integer twentySeven = 0;
        Integer twentyEight = 0;
        Integer twentyNine = 0;
        Integer thirty = 0;
        Integer thirtyOne = 0;
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser) currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        GraphicExaminationMonthlyDTO graphicExaminationMonthlyDTO = new GraphicExaminationMonthlyDTO();
        for (ExaminationSchedule examinationSchedule : pharmacyAdmin.getPharmacy().getExaminationSchedules()) {
            if (examinationSchedule.getDate().getMonth() == LocalDate.now().getMonth()) {
                if (examinationSchedule.getDate().getDayOfMonth() == 1) {
                    one += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 2) {
                    two += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 3) {
                    three += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 4) {
                    four += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 5) {
                    five += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 6) {
                    six += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 7) {
                    seven += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 8) {
                    eight += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 9) {
                    nine += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 10) {
                    ten += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 11) {
                    eleven += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 12) {
                    twelve += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 13) {
                    thirteen += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 14) {
                    fourteen += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 15) {
                    fifteen += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 16) {
                    sixteen += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 17) {
                    seventeen += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 18) {
                    eighteen += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 19) {
                    nineteen += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 20) {
                    twenty += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 21) {
                    twentyOne += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 22) {
                    twentyTwo += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 23) {
                    twentyThree += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 24) {
                    twentyFour += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 25) {
                    twentyFive += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 26) {
                    twentySix += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 27) {
                    twentySeven += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 28) {
                    twentyEight += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 29) {
                    twentyNine += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 30) {
                    thirteen += 1;
                }
                if (examinationSchedule.getDate().getDayOfMonth() == 31) {
                    thirtyOne += 1;
                }


            }

        }

        graphicExaminationMonthlyDTO.setOne(one);
        graphicExaminationMonthlyDTO.setTwo(two);
        graphicExaminationMonthlyDTO.setThree(three);
        graphicExaminationMonthlyDTO.setFour(four);
        graphicExaminationMonthlyDTO.setFive(five);
        graphicExaminationMonthlyDTO.setSix(six);
        graphicExaminationMonthlyDTO.setSeven(seven);
        graphicExaminationMonthlyDTO.setEight(eight);
        graphicExaminationMonthlyDTO.setNine(nine);
        graphicExaminationMonthlyDTO.setTen(ten);
        graphicExaminationMonthlyDTO.setEleven(eleven);
        graphicExaminationMonthlyDTO.setTwelve(twelve);
        graphicExaminationMonthlyDTO.setThirteen(thirteen);
        graphicExaminationMonthlyDTO.setFourteen(fourteen);
        graphicExaminationMonthlyDTO.setFifteen(fifteen);
        graphicExaminationMonthlyDTO.setSixteen(sixteen);
        graphicExaminationMonthlyDTO.setSeventeen(seventeen);
        graphicExaminationMonthlyDTO.setEighteen(eighteen);
        graphicExaminationMonthlyDTO.setNineteen(nineteen);
        graphicExaminationMonthlyDTO.setTwenty(twenty);
        graphicExaminationMonthlyDTO.setTwentyOne(twentyOne);
        graphicExaminationMonthlyDTO.setTwentyTwo(twentyTwo);
        graphicExaminationMonthlyDTO.setTwentyThree(twentyThree);
        graphicExaminationMonthlyDTO.setTwentyFour(twentyFour);
        graphicExaminationMonthlyDTO.setTwentyFive(twentyFive);
        graphicExaminationMonthlyDTO.setTwentySix(twentySix);
        graphicExaminationMonthlyDTO.setTwentySeven(twentySeven);
        graphicExaminationMonthlyDTO.setTwentyEight(twentyEight);
        graphicExaminationMonthlyDTO.setTwentyNine(twentyNine);
        graphicExaminationMonthlyDTO.setThirty(thirty);
        graphicExaminationMonthlyDTO.setThirtyOne(thirtyOne);

        return graphicExaminationMonthlyDTO;
    }

    @GetMapping("/examinationQuarter")
    GraphicExaminationQuarterDTO getNumberOfAppointmentsQuarter() {
        int first = 0;
        int second = 0;
        int third = 0;
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser) currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        GraphicExaminationQuarterDTO graphicExaminationQuarterDTO = new GraphicExaminationQuarterDTO();
        for (ExaminationSchedule examinationSchedule : pharmacyAdmin.getPharmacy().getExaminationSchedules()) {
            // if (examinationSchedule.getPharmacy().getId() == id) {
            if (examinationSchedule.getDate().getMonth() == Month.JANUARY || examinationSchedule.getDate().getMonth() == Month.FEBRUARY
                    || examinationSchedule.getDate().getMonth() == Month.MARCH || examinationSchedule.getDate().getMonth() == Month.APRIL) {
                first += 1;
            }
            if (examinationSchedule.getDate().getMonth() == Month.MAY || examinationSchedule.getDate().getMonth() == Month.JUNE
                    || examinationSchedule.getDate().getMonth() == Month.JULY || examinationSchedule.getDate().getMonth() == Month.AUGUST) {
                second += 1;
            }
            if (examinationSchedule.getDate().getMonth() == Month.SEPTEMBER || examinationSchedule.getDate().getMonth() == Month.OCTOBER
                    || examinationSchedule.getDate().getMonth() == Month.NOVEMBER || examinationSchedule.getDate().getMonth() == Month.DECEMBER) {
                third += 1;
            }
        }
        //}

        graphicExaminationQuarterDTO.setFirst(first);
        graphicExaminationQuarterDTO.setSecond(second);
        graphicExaminationQuarterDTO.setThird(third);
        return graphicExaminationQuarterDTO;
    }

    @GetMapping("/medicationYearly")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    GraphicMedicationYearlyDTO getNumberOfSoldMedicationYearly() {
        int january = 0;
        int february = 0;
        int march = 0;
        int april = 0;
        int may = 0;
        int june = 0;
        int july = 0;
        int august = 0;
        int september = 0;
        int october = 0;
        int november = 0;
        int december = 0;
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser) currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        GraphicMedicationYearlyDTO graphicExaminationYearlyDTO = new GraphicMedicationYearlyDTO();
        for (MedicationReservation medicationReservation : pharmacyAdmin.getPharmacy().getMedicationReservations()) {
            if (medicationReservation.getDateOfTakeOver().getMonth() == Month.JANUARY) {
                january += 1;
            }
            if (medicationReservation.getDateOfTakeOver().getMonth() == Month.FEBRUARY) {
                february += 1;
            }
            if (medicationReservation.getDateOfTakeOver().getMonth() == Month.MARCH) {
                march += 1;
            }
            if (medicationReservation.getDateOfTakeOver().getMonth() == Month.APRIL) {
                april += 1;
            }
            if (medicationReservation.getDateOfTakeOver().getMonth() == Month.MAY) {
                may += 1;
            }
            if (medicationReservation.getDateOfTakeOver().getMonth() == Month.JUNE) {
                june += 1;
            }
            if (medicationReservation.getDateOfTakeOver().getMonth() == Month.JULY) {
                july += 1;
            }
            if (medicationReservation.getDateOfTakeOver().getMonth() == Month.AUGUST) {
                august += 1;
            }
            if (medicationReservation.getDateOfTakeOver().getMonth() == Month.SEPTEMBER) {
                september += 1;
            }
            if (medicationReservation.getDateOfTakeOver().getMonth() == Month.OCTOBER) {
                october += 1;
            }
            if (medicationReservation.getDateOfTakeOver().getMonth() == Month.NOVEMBER) {
                november += 1;
            }
            if (medicationReservation.getDateOfTakeOver().getMonth() == Month.DECEMBER) {
                december += 1;
            }
        }
        graphicExaminationYearlyDTO.setJanuary(january);
        graphicExaminationYearlyDTO.setFebruary(february);
        graphicExaminationYearlyDTO.setMarch(march);
        graphicExaminationYearlyDTO.setApril(april);
        graphicExaminationYearlyDTO.setMay(may);
        graphicExaminationYearlyDTO.setJune(june);
        graphicExaminationYearlyDTO.setJuly(july);
        graphicExaminationYearlyDTO.setAugust(august);
        graphicExaminationYearlyDTO.setSeptember(september);
        graphicExaminationYearlyDTO.setOctober(october);
        graphicExaminationYearlyDTO.setNovember(november);
        graphicExaminationYearlyDTO.setDecember(december);
        return graphicExaminationYearlyDTO;

    }

    @GetMapping("/medicationMonthly")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    GraphicMedicationMonthlyDTO getNumberOfSoldMedicationMonthly() {
        System.out.println("POGODIO JEEEEEEEEEEEEEEEEEE");
        Integer one = 0;
        Integer two = 0;
        Integer three = 0;
        Integer four = 0;
        Integer five = 0;
        Integer six = 0;
        Integer seven = 0;
        Integer eight = 0;
        Integer nine = 0;
        Integer ten = 0;
        Integer eleven = 0;
        Integer twelve = 0;
        Integer thirteen = 0;
        Integer fourteen = 0;
        Integer fifteen = 0;
        Integer sixteen = 0;
        Integer seventeen = 0;
        Integer eighteen = 0;
        Integer nineteen = 0;
        Integer twenty = 0;
        Integer twentyOne = 0;
        Integer twentyTwo = 0;
        Integer twentyThree = 0;
        Integer twentyFour = 0;
        Integer twentyFive = 0;
        Integer twentySix = 0;
        Integer twentySeven = 0;
        Integer twentyEight = 0;
        Integer twentyNine = 0;
        Integer thirty = 0;
        Integer thirtyOne = 0;
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser) currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        GraphicMedicationMonthlyDTO graphicExaminationMonthlyDTO = new GraphicMedicationMonthlyDTO();
        for (MedicationReservation medicationReservation : pharmacyAdmin.getPharmacy().getMedicationReservations()) {
            if (medicationReservation.getDateOfTakeOver().getMonth() == LocalDate.now().getMonth()) {
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 1 && medicationReservation.getCollected()) {
                    one += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 2) {
                    two += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 3) {
                    three += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 4) {
                    four += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 5) {
                    five += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 6) {
                    six += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 7) {
                    seven += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 8) {
                    eight += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 9) {
                    nine += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 10) {
                    ten += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 11) {
                    eleven += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 12) {
                    twelve += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 13) {
                    thirteen += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 14) {
                    fourteen += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 15) {
                    fifteen += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 16) {
                    sixteen += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 17) {
                    seventeen += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 18) {
                    eighteen += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 19) {
                    nineteen += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 20) {
                    twenty += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 21) {
                    twentyOne += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 22) {
                    twentyTwo += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 23) {
                    twentyThree += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 24) {
                    twentyFour += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 25) {
                    twentyFive += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 26) {
                    twentySix += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 27) {
                    twentySeven += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 28) {
                    twentyEight += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 29) {
                    twentyNine += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 30) {
                    thirteen += 1;
                }
                if (medicationReservation.getDateOfTakeOver().getDayOfMonth() == 31) {
                    thirtyOne += 1;
                }


            }


        }
        graphicExaminationMonthlyDTO.setOne(one);
        graphicExaminationMonthlyDTO.setTwo(two);
        graphicExaminationMonthlyDTO.setThree(three);
        graphicExaminationMonthlyDTO.setFour(four);
        graphicExaminationMonthlyDTO.setFive(five);
        graphicExaminationMonthlyDTO.setSix(six);
        graphicExaminationMonthlyDTO.setSeven(seven);
        graphicExaminationMonthlyDTO.setEight(eight);
        graphicExaminationMonthlyDTO.setNine(nine);
        graphicExaminationMonthlyDTO.setTen(ten);
        graphicExaminationMonthlyDTO.setEleven(eleven);
        graphicExaminationMonthlyDTO.setTwelve(twelve);
        graphicExaminationMonthlyDTO.setThirteen(thirteen);
        graphicExaminationMonthlyDTO.setFourteen(fourteen);
        graphicExaminationMonthlyDTO.setFifteen(fifteen);
        graphicExaminationMonthlyDTO.setSixteen(sixteen);
        graphicExaminationMonthlyDTO.setSeventeen(seventeen);
        graphicExaminationMonthlyDTO.setEighteen(eighteen);
        graphicExaminationMonthlyDTO.setNineteen(nineteen);
        graphicExaminationMonthlyDTO.setTwenty(twenty);
        graphicExaminationMonthlyDTO.setTwentyOne(twentyOne);
        graphicExaminationMonthlyDTO.setTwentyTwo(twentyTwo);
        graphicExaminationMonthlyDTO.setTwentyThree(twentyThree);
        graphicExaminationMonthlyDTO.setTwentyFour(twentyFour);
        graphicExaminationMonthlyDTO.setTwentyFive(twentyFive);
        graphicExaminationMonthlyDTO.setTwentySix(twentySix);
        graphicExaminationMonthlyDTO.setTwentySeven(twentySeven);
        graphicExaminationMonthlyDTO.setTwentyEight(twentyEight);
        graphicExaminationMonthlyDTO.setTwentyNine(twentyNine);
        graphicExaminationMonthlyDTO.setThirty(thirty);
        graphicExaminationMonthlyDTO.setThirtyOne(thirtyOne);

        return graphicExaminationMonthlyDTO;
    }

    @GetMapping("/medicationQuarter")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    GraphicMedicationQuarterDTO getNumberOfSoldMedicationQuarter() {
        int first = 0;
        int second = 0;
        int third = 0;
        GraphicMedicationQuarterDTO graphicExaminationQuarterDTO = new GraphicMedicationQuarterDTO();
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser) currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        for (MedicationReservation medicationReservation : pharmacyAdmin.getPharmacy().getMedicationReservations()) {
            if (medicationReservation.getDateOfTakeOver().getMonth() == Month.JANUARY || medicationReservation.getDateOfTakeOver().getMonth() == Month.FEBRUARY
                    || medicationReservation.getDateOfTakeOver().getMonth() == Month.MARCH || medicationReservation.getDateOfTakeOver().getMonth() == Month.APRIL) {
                first += 1;
            }
            if (medicationReservation.getDateOfTakeOver().getMonth() == Month.MAY || medicationReservation.getDateOfTakeOver().getMonth() == Month.JUNE
                    || medicationReservation.getDateOfTakeOver().getMonth() == Month.JULY || medicationReservation.getDateOfTakeOver().getMonth() == Month.AUGUST) {
                second += 1;
            }
            if (medicationReservation.getDateOfTakeOver().getMonth() == Month.SEPTEMBER || medicationReservation.getDateOfTakeOver().getMonth() == Month.OCTOBER
                    || medicationReservation.getDateOfTakeOver().getMonth() == Month.NOVEMBER || medicationReservation.getDateOfTakeOver().getMonth() == Month.DECEMBER) {
                third += 1;
            }

        }

        graphicExaminationQuarterDTO.setFirst(first);
        graphicExaminationQuarterDTO.setSecond(second);
        graphicExaminationQuarterDTO.setThird(third);
        return graphicExaminationQuarterDTO;
    }

    @GetMapping("/income")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    GraphicHalfYearIncomeDTO getHalfYearIncome() {
        int january = 0;
        int february = 0;
        int march = 0;
        int april = 0;
        int may = 0;
        int june = 0;
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser) currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        GraphicHalfYearIncomeDTO graphicExaminationYearlyDTO = new GraphicHalfYearIncomeDTO();
        for (ExaminationSchedule examinationSchedule : pharmacyAdmin.getPharmacy().getExaminationSchedules()) {
            if (examinationSchedule.getDate().getMonth() == Month.JANUARY) {
                january += examinationSchedule.getPrice();
            }
            if (examinationSchedule.getDate().getMonth() == Month.FEBRUARY) {
                february += examinationSchedule.getPrice();
            }
            if (examinationSchedule.getDate().getMonth() == Month.MARCH) {
                march += examinationSchedule.getPrice();
            }
            if (examinationSchedule.getDate().getMonth() == Month.APRIL) {
                april += examinationSchedule.getPrice();
            }
            if (examinationSchedule.getDate().getMonth() == Month.MAY) {
                may += examinationSchedule.getPrice();
            }
            if (examinationSchedule.getDate().getMonth() == Month.JUNE) {
                june += examinationSchedule.getPrice();
            }

        }
        for (Pharmacist pharmacist : pharmacyAdmin.getPharmacy().getPharmacists()) {
            for (Consulting consulting : pharmacist.getConsulting()) {
                if (consulting.getDate().getMonth() == Month.JANUARY) {
                    january += pharmacyAdmin.getPharmacy().getConsultingPrice();
                }

                if (consulting.getDate().getMonth() == Month.FEBRUARY) {
                    february += pharmacyAdmin.getPharmacy().getConsultingPrice();
                }

                if (consulting.getDate().getMonth() == Month.MARCH) {
                    march += pharmacyAdmin.getPharmacy().getConsultingPrice();
                }

                if (consulting.getDate().getMonth() == Month.APRIL) {
                    april += pharmacyAdmin.getPharmacy().getConsultingPrice();
                }

                if (consulting.getDate().getMonth() == Month.JUNE) {
                    june += pharmacyAdmin.getPharmacy().getConsultingPrice();
                }
            }
        }

        for (MedicationReservation medicationReservation : pharmacyAdmin.getPharmacy().getMedicationReservations()) {
                for(MedicationPriceHistory medicationPriceHistory : medicationPriceHistoryService.findAll()) {
                    if (medicationPriceHistory.getMedication_id() == medicationReservation.getMedicine().getId()) {
                        if (medicationReservation.getDateOfReservation().getMonth() == Month.JANUARY) {
                            if (medicationPriceHistory.getEndDate().isAfter(medicationReservation.getDateOfReservation()) && medicationPriceHistory.getStartDate().minusDays(1).isBefore(medicationReservation.getDateOfReservation())) {
                                january += medicationPriceHistory.getPrice();
                            }
                        }
                        if (medicationReservation.getDateOfReservation().getMonth() == Month.FEBRUARY) {
                            if (medicationPriceHistory.getEndDate().isAfter(medicationReservation.getDateOfReservation()) && medicationPriceHistory.getStartDate().minusDays(1).isBefore(medicationReservation.getDateOfReservation())) {
                                february += medicationPriceHistory.getPrice();
                                System.out.println("DODAO ZA FEBRUAR");
                                System.out.println(medicationPriceHistory.getPrice());
                            }
                        }
                        if (medicationReservation.getDateOfReservation().getMonth() == Month.MARCH) {
                            if (medicationPriceHistory.getEndDate().isAfter(medicationReservation.getDateOfReservation()) && medicationPriceHistory.getStartDate().minusDays(1).isBefore(medicationReservation.getDateOfReservation())) {
                                march += medicationPriceHistory.getPrice();
                            }
                        }
                        if (medicationReservation.getDateOfReservation().getMonth() == Month.APRIL) {
                            if (medicationPriceHistory.getEndDate().isAfter(medicationReservation.getDateOfReservation()) && medicationPriceHistory.getStartDate().minusDays(1).isBefore(medicationReservation.getDateOfReservation())) {
                                april += medicationPriceHistory.getPrice();
                            }
                        }
                        if (medicationReservation.getDateOfReservation().getMonth() == Month.MAY) {
                            if (medicationPriceHistory.getEndDate().isAfter(medicationReservation.getDateOfReservation()) && medicationPriceHistory.getStartDate().minusDays(1).isBefore(medicationReservation.getDateOfReservation())) {
                                may += medicationPriceHistory.getPrice();
                            }
                        }
                        if (medicationReservation.getDateOfReservation().getMonth() == Month.JUNE) {
                            if (medicationPriceHistory.getEndDate().isAfter(medicationReservation.getDateOfReservation()) && medicationPriceHistory.getStartDate().minusDays(1).isBefore(medicationReservation.getDateOfReservation())) {
                                june += medicationPriceHistory.getPrice();
                            }
                        }

                    }
                }
        }
        graphicExaminationYearlyDTO.setJanuary(january);
        graphicExaminationYearlyDTO.setFebruary(february);
        graphicExaminationYearlyDTO.setMarch(march);
        graphicExaminationYearlyDTO.setApril(april);
        graphicExaminationYearlyDTO.setMay(may);
        graphicExaminationYearlyDTO.setJune(june);

        return graphicExaminationYearlyDTO;

    }

    @GetMapping("/geographicCoordinates")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    GeographicCoordinatesDTO getNumberOfSoldMedicationYearly(@PathVariable Integer id) {

        return null;
    }
}
