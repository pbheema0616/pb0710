package com.assessment.toolrental;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class ToolRentalService {

    public RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) throws IllegalArgumentException {
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100");
        }

        Tool tool = getTool(toolCode);
        if (tool == null) {
            throw new IllegalArgumentException("Invalid tool code");
        }

        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        int chargeDays = calculateChargeDays(checkoutDate, dueDate, tool);

        double preDiscountCharge = chargeDays * tool.getDailyCharge();
        double discountAmount = Math.round((preDiscountCharge * discountPercent / 100.0) * 100.0) / 100.0;
        double finalCharge = preDiscountCharge - discountAmount;

        return new RentalAgreement(tool.getCode(), tool.getType(), tool.getBrand(), rentalDays, checkoutDate, dueDate, tool.getDailyCharge(), chargeDays, preDiscountCharge, discountPercent, discountAmount, finalCharge);
    }

    private Tool getTool(String toolCode) {
        switch (toolCode) {
            case "CHNS":
                return new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true);
            case "LADW":
                return new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false);
            case "JAKD":
                return new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, true, false, false);
            case "JAKR":
                return new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false);
            default:
                return null;
        }
    }

    private int calculateChargeDays(LocalDate start, LocalDate end, Tool tool) {
        int chargeDays = 0;
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            DayOfWeek day = date.getDayOfWeek();
            boolean isHoliday = isHoliday(date);
            if ((tool.isWeekdayCharge() && (day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY)) ||
                (tool.isWeekendCharge() && (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)) ||
                (tool.isHolidayCharge() && isHoliday)) {
                chargeDays++;
            }
        }
        return chargeDays;
    }

    private boolean isHoliday(LocalDate date) {
        int year = date.getYear();
        LocalDate independenceDay = LocalDate.of(year, Month.JULY, 4);
        if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            independenceDay = independenceDay.minusDays(1);
        } else if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            independenceDay = independenceDay.plusDays(1);
        }

        LocalDate laborDay = LocalDate.of(year, Month.SEPTEMBER, 1);
        while (laborDay.getDayOfWeek() != DayOfWeek.MONDAY) {
            laborDay = laborDay.plusDays(1);
        }

        return date.isEqual(independenceDay) || date.isEqual(laborDay);
    }
}
