package com.assessment.toolrental;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ToolRentalService service = new ToolRentalService();

        try {
            LocalDate checkoutDate1 = LocalDate.of(2015, 9, 3);
            RentalAgreement agreement1 = service.checkout("JAKR", 5, 101, checkoutDate1);
            agreement1.print();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            LocalDate checkoutDate2 = LocalDate.of(2020, 7, 2);
            RentalAgreement agreement2 = service.checkout("LADW", 3, 10, checkoutDate2);
            agreement2.print();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            LocalDate checkoutDate3 = LocalDate.of(2015, 7, 2);
            RentalAgreement agreement3 = service.checkout("CHNS", 5, 25, checkoutDate3);
            agreement3.print();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            LocalDate checkoutDate4 = LocalDate.of(2015, 9, 3);
            RentalAgreement agreement4 = service.checkout("JAKD", 6, 0, checkoutDate4);
            agreement4.print();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            LocalDate checkoutDate5 = LocalDate.of(2015, 7, 2);
            RentalAgreement agreement5 = service.checkout("JAKR", 9, 0, checkoutDate5);
            agreement5.print();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            LocalDate checkoutDate6 = LocalDate.of(2020, 7, 2);
            RentalAgreement agreement6 = service.checkout("JAKR", 4, 50, checkoutDate6);
            agreement6.print();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
