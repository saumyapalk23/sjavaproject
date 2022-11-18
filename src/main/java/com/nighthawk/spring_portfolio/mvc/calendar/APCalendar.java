package com.nighthawk.spring_portfolio.mvc.calendar;

// Prototype Implementation

public class APCalendar {

    /** Returns true if year is a leap year and false otherwise.
     * isLeapYear(2019) returns False
     * isLeapYear(2016) returns True
     */          
    public static boolean isLeapYear(int year1, int year2) {
        // implementation not shown
        // if (year % 4 == 0) and (year % 100 == 0);
        //     return true;
        // if (year % 4 == 0) and (year % 100 != 0);
        //     return true;
        // if (year % 4 != 0) and (year % 4 != 0);
        //     return false;
        // // {
            int numberOfLeapYears = 0;

            for(int y=year1; y<=year2; y++)
                if (isLeapYear(y))
                    numberOfLeapYears++;

            return numberOfLeapYears;  
         }
          
        
    /** Returns the value representing the day of the week  
     * 0 denotes Sunday, 
     * 1 denotes Monday, ..., 
     * 6 denotes Saturday. 
     * firstDayOfYear(2019) returns 2 for Tuesday.
    */
    private static int firstDayOfYear(int year, int month, int day) {
        // implementation not shown
            int firstDay = firstDayOfYear(year);
            int dAy = dayOfYear(month, firstDay, year);
            return (firstDay + dAy -1) % 7; //-1 for 0 number index, mod7 makes sure it's a 7# index
        return 0;
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
         int dayVal = 0;
         for (int i = 1; i < month; i++) {
             // Creates a yearmonth object for each month in the year
             int monthDays = YearMonth.of(year, i).lengthOfMonth();
             dayVal += monthDays;
         }
         //Adds the day of the incomplete month to dayVal
         dayVal += day;
         return dayVal;
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
        Date date = new Date(year, month - 1, day);
        if (date.getDay() == 0) {
            return 6;
        }
        else {
            return date.getDay() - 1;
        }
        }
    /** Tester method */
    public static void main(String[] args) {
        // Private access modifiers
        System.out.println("firstDayOfYear: " + APCalendar.firstDayOfYear(2022));
        System.out.println("dayOfYear: " + APCalendar.dayOfYear(1, 1, 2022));

        // Public access modifiers
        System.out.println("isLeapYear: " + APCalendar.isLeapYear(2022));
        System.out.println("numberOfLeapYears: " + APCalendar.numberOfLeapYears(2000, 2022));
        System.out.println("dayOfWeek: " + APCalendar.dayOfWeek(1, 1, 2022));
    }

}