package com.nighthawk.spring_portfolio.mvc.calendar;
import java.time.*;

// Prototype Implementation

public class APCalendar {

    /** Returns true if year is a leap year and false otherwise.
     * isLeapYear(2019) returns False
     * isLeapYear(2016) returns True
     */          
    public static boolean isLeapYear(int year) {
        // implementation not shown
        // if (year % 4 == 0) and (year % 100 == 0);
        //     return true;
        // if (year % 4 == 0) and (year % 100 != 0);
        //     return true;
        // if (year % 4 != 0) and (year % 4 != 0);
        //     return false;
        // // {
            {
                boolean result = (year % 400 == 0) || (year % 4 == 0) && (year % 100 != 0);
                return result; }
            }
        
            /** Returns the value representing the day of the week  
     * 0 denotes Sunday, 
     * 1 denotes Monday, ..., 
     * 6 denotes Saturday. 
     * firstDayOfYear(2019) returns 2 for Tuesday.
    */
    private static int firstDayOfYear(int year) {
        // implementation not shown
		LocalDateTime t = LocalDateTime.of(year, Month.JANUARY, 1, 0, 0);
		// get Java day of week which is 1-7, where 1=Monday and 7=Sunday
		int result = t.getDayOfWeek().getValue();
		// adjust from 1-7 numbering (Mon->Sun) to 0-6 numbering (Sun->Sat) to meet AP Exam requirement
		if(result == 7)
			result = 0;
		System.out.format("APCalendar firstDayOfYear: %d-%02d-%02d is day of year number %d%n", year, 1, 1, result);
		return result;
        }


    /** Returns n, where month, day, and year specify the nth day of the year.
     * This method accounts for whether year is a leap year. 
     * dayOfYear(1, 1, 2019) return 1
     * dayOfYear(3, 1, 2017) returns 60, since 2017 is not a leap year
     * dayOfYear(3, 1, 2016) returns 61, since 2016 is a leap year. 
    */ 
    private static int dayOfYear(int month, int day, int year) {
        // implementation not shown
         // initializes dayValue as 0
         int number = 0;
        if (isLeapYear(year)){
            number +=1;
        }

        //jan
        if (month ==1){
            number = number + day;
        }
        //feb
        else if (month ==2){
            number = number + (31 + day);
        }
        else if (month ==3){
            number = number + (31 + 28 + day);
        }
        else if (month ==4){
            number = number + (31 + 28 + 31+ day);
        }
        else if (month ==5){
            number = number + (31 + 28 + 31+ 30 + day);
        }
        else if (month ==6){
            number = number + (31 + 28 + 31+ +30 + 31+ day);
        }
        else if (month ==7){
            number = number + (31 + 28 + 31+ +30 + 31+ 30 + day);
        }
        else if (month ==8){
            number = number + (31 + 28 + 31+ +30 + 31+ 30 + 31+ day);
        }
        else if (month ==9){
            number = number + (31 + 28 + 31+ +30 + 31+ 30 + 31+ 31 + day);
        }
        else if (month ==10){
            number = number + (31 + 28 + 31+ +30 + 31+ 30 + 31+ 31 + 30 + day);
        }
        else if (month ==11){
            number = number + (31 + 28 + 31+ +30 + 31+ 30 + 31+ 31 + 30 + 31 + day);
        }
        else if (month ==12){
            number = number + (31 + 28 + 31+ +30 + 31+ 30 + 31+ 31 + 30 + 31 + 30 + day);
        }

        return number;
    }
    /** Returns the number of leap years between year1 and year2, inclusive.
     * Precondition: 0 <= year1 <= year2
    */ 
    public static int numberOfLeapYears(int year1, int year2) {
         // to be implemented in part (a)
         int leapYears = 0;
        for (int y = year1; y <= year2; y++)
            if (isLeapYear(y))
                leapYears++;
        return leapYears;
    }

    /** Returns the value representing the day of the week for the given date
     * Precondition: The date represented by month, day, year is a valid date.
    */
    public static int dayOfWeek(int month, int day, int year) { 
        // to be implemented in part (b)
        int day_of_the_year = dayOfYear(month, day, year);
        int remainder = day_of_the_year % 7;
        int first_day = firstDayOfYear(year);
        return first_day + remainder - 1;
        }

    /** Tester method */
    public static void main(String[] args) {
        // Private access modifiers
            System.out.println("firstDayOfYear: " + APCalendar.firstDayOfYear(2022));
            System.out.println("dayOfYear: " + APCalendar.dayOfYear(1, 1, 2022));
    }
            // Public access modifiers
         {   System.out.println("isLeapYear: " + APCalendar.isLeapYear(2022));
            System.out.println("numberOfLeapYears: " + APCalendar.numberOfLeapYears(2000, 2022));
            System.out.println("dayOfWeek: " + APCalendar.dayOfWeek(1, 1, 2022));
           }
        }