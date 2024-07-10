package com.assessment.toolrental;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class ToolRentalServiceTest {

    private final ToolRentalService service = new ToolRentalService();

    @Test
    public void testInvalidDiscount() {
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
        assertThrows(IllegalArgumentException.class, () -> {
            service.checkout("JAKR", 5, 101, checkoutDate);
        });
    }

    @Test
    public void testLadderRental() {
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        RentalAgreement agreement = service.checkout("LADW", 3, 10, checkoutDate);
        assertEquals("LADW", agreement.getToolCode());
        assertEquals("Ladder", agreement.getToolType());
        assertEquals("Werner", agreement.getToolBrand());
        assertEquals(3, agreement.getRentalDays());
        assertEquals(LocalDate.of(2020, 7, 2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2020, 7, 5), agreement.getDueDate());
        assertEquals(1.99, agreement.getDailyRentalCharge());
        assertEquals(4, agreement.getChargeDays());
        assertEquals(7.96, agreement.getPreDiscountCharge());
        assertEquals(10, agreement.getDiscountPercent());
        assertEquals(0.80, agreement.getDiscountAmount());
        assertEquals(7.16, agreement.getFinalCharge());
    }

    @Test
    public void testChainsawRental() {
        LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
        RentalAgreement agreement = service.checkout("CHNS", 5, 25, checkoutDate);
        assertEquals("CHNS", agreement.getToolCode());
        assertEquals("Chainsaw", agreement.getToolType());
        assertEquals("Stihl", agreement.getToolBrand());
        assertEquals(5, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 7, 2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015, 7, 7), agreement.getDueDate());
        assertEquals(1.49, agreement.getDailyRentalCharge());
        assertEquals(4, agreement.getChargeDays());
        assertEquals(5.96, agreement.getPreDiscountCharge());
        assertEquals(25, agreement.getDiscountPercent());
        assertEquals(1.49, agreement.getDiscountAmount());
        assertEquals(4.47, agreement.getFinalCharge(),0.01);
    }

    @Test
    public void testJackhammerRental1() {
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
        RentalAgreement agreement = service.checkout("JAKD", 6, 0, checkoutDate);
        assertEquals("JAKD", agreement.getToolCode());
        assertEquals("Jackhammer", agreement.getToolType());
        assertEquals("DeWalt", agreement.getToolBrand());
        assertEquals(6, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 9, 3), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015, 9, 9), agreement.getDueDate());
        assertEquals(2.99, agreement.getDailyRentalCharge());
        assertEquals(5, agreement.getChargeDays());
        assertEquals(14.97, agreement.getPreDiscountCharge(),0.02);
        assertEquals(0, agreement.getDiscountPercent());
        assertEquals(0.00, agreement.getDiscountAmount());
        assertEquals(14.95, Math.round(agreement.getFinalCharge() * 100.0) / 100.0);
    }

    @Test
    public void testJackhammerRental2() {
        LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
        RentalAgreement agreement = service.checkout("JAKR", 9, 0, checkoutDate);
        assertEquals("JAKR", agreement.getToolCode());
        assertEquals("Jackhammer", agreement.getToolType());
        assertEquals("Ridgid", agreement.getToolBrand());
        assertEquals(9, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 7, 2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015, 7, 11), agreement.getDueDate());
        assertEquals(2.99, agreement.getDailyRentalCharge());
        assertEquals(7, agreement.getChargeDays());
        assertEquals(20.93, agreement.getPreDiscountCharge());
        assertEquals(0, agreement.getDiscountPercent());
        assertEquals(0.00, agreement.getDiscountAmount());
        assertEquals(20.93, agreement.getFinalCharge());
    }

    @Test
    public void testJackhammerRental3() {
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        RentalAgreement agreement = service.checkout("JAKR", 4, 50, checkoutDate);
        assertEquals("JAKR", agreement.getToolCode());
        assertEquals("Jackhammer", agreement.getToolType());
        assertEquals("Ridgid", agreement.getToolBrand());
        assertEquals(4, agreement.getRentalDays());
        assertEquals(LocalDate.of(2020, 7, 2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2020, 7, 6), agreement.getDueDate());
        assertEquals(2.99, agreement.getDailyRentalCharge());
        assertEquals(3, agreement.getChargeDays());
        assertEquals(8.97, agreement.getPreDiscountCharge());
        assertEquals(50, agreement.getDiscountPercent());
        assertEquals(4.49, agreement.getDiscountAmount());
        assertEquals(4.48, agreement.getFinalCharge());
    }
}
