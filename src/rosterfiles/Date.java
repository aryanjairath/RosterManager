package rosterfiles;
import java.util.Calendar;

/**
 * This class defines a date by year, month, and day from an input String,
 * it also auto-generates a date object with calendar date if no parameter is sent,
 * and checks if dates are valid calendar dates based on the Calendar library
 * @author Aryan Jairath, Anis Chihoub
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int MONTHINDEX = 0;
    public static final int DAYINDEX = 1;
    public static final int YEARINDEX = 2;

    public static final int OLDERDATE = 1;
    public static final int EQUALDATES = 0;
    public static final int EARLIERDATE = -1;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;
    public static final int NUMBEROFMONTHS = 12;


    public static final int THIRTYONEDAYS = 31;
    public static final int THIRTYDAYS = 30;
    public static final int TWENTYEIGHTDAYSNOLEAP = 28;
    public static final int TWENTYEIGHTDAYSLEAP = 29;

    public static final int DIVISIBLENOREMAINDER = 0;
    public static final int ZEROTH = 0;


    /**
     * Makes a date object with the current date from calendar class
     */
    public Date() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + DAYINDEX;
        day = calendar.get(Calendar.DATE);
    }

    /**
     * Makes a date object by parsing the String into month, day, and year ints
     * @param date string that is split into three parts to fill the date attributes
     */
    public Date(String date) {
        String[] y = date.split("/");
        year = Integer.parseInt(y[YEARINDEX]);
        month = Integer.parseInt(y[MONTHINDEX]);
        day = Integer.parseInt(y[DAYINDEX]);
    }

    /**
     * Returns the year associated with the date object
     * @return year, an int representing the year of the date
     */
    public int getYear(){
        return this.year;
    }

    /**
     * Returns the month associated with the date object
     * @return month, an int representing the month of the date
     */
    public int getMonth(){
        return this.month;
    }

    /**
     * Returns the day associated with the date object
     * @return day, an int representing the day of the date
     */
    public int getDay(){
        return this.day;
    }

    /**
     * This method checks whether a date object is valid by checking
     * if the date exists on a real calendar and accounts for leap years if
     * the specified year is leap.
     * @return true if the date is not a future date or today's date or
     * a date that is not calendar date
     */
    public boolean isValid() {
        boolean isLeap = leapYear(this.year);
        Date birthDate = new Date(this.month + "/" + this.day + "/" + this.year);
        Date todayDate = new Date();
        if(this.month <= ZEROTH || this.year <= ZEROTH ||
                this.day <= ZEROTH || this.month > NUMBEROFMONTHS)
            return false;
        if((this.month == JANUARY || this.month == MARCH || this.month == MAY ||
                this.month == JULY || this.month == AUGUST || this.month == OCTOBER ||
                this.month == DECEMBER) && this.day > THIRTYONEDAYS){
            return false;
        }
        if((this.month == APRIL || this.month == JUNE || this.month == SEPTEMBER||
                this.month == NOVEMBER) && this.day > THIRTYDAYS){
            return false;
        }
        if(isLeap){
            if((this.month == FEBRUARY) && this.day > TWENTYEIGHTDAYSLEAP)
                return false;
        }else{
            if((this.month == FEBRUARY) && this.day > TWENTYEIGHTDAYSNOLEAP)
                return false;
        }
        if(birthDate.compareTo(todayDate) >= OLDERDATE)
            return false;
        return true;
    }

    /**
     * This method accepts an int to determine whether the specified
     * year is a leap year or not
     * @param year the year that is being checked for whether it is a leap year
     * @return true if it is leapyear, false otherwise
     */
    private boolean leapYear(int year){
        if(year % QUADRENNIAL == DIVISIBLENOREMAINDER){
            if(year % CENTENNIAL != DIVISIBLENOREMAINDER ||
                    year % QUATERCENTENNIAL == DIVISIBLENOREMAINDER){
                return true;
            }
        }
        return false;
    }

    /**
     * This method compares two date objects
     * @param otherDate the object to be compared.
     * @return 1,0, or -1 depending on whether Date o is less than
     * equal to or greater than, date being compared
     */
    @Override
    public int compareTo(Date otherDate) {
        if(this.year > otherDate.year){
            return OLDERDATE;
        }else if(this.year == otherDate.year){
            if(this.month > otherDate.month)
                return OLDERDATE;
            else if(this.month == otherDate.month){
                if(this.day > otherDate.day)
                    return OLDERDATE;
                if(this.day == otherDate.day)
                    return EQUALDATES;
            }
        }
        return EARLIERDATE;
    }

    /**
     * This method determines if two date objects are equal
     * based on their attributes.
     * @param otherObject the object that is being compared to
     * @return true if two dates are the same,
     * false if they are different dates.
     */
    @Override
    public boolean equals(Object otherObject){
        if(otherObject instanceof Date){
            return ((Date) otherObject).year == this.year && ((Date) otherObject).month == this.month
                    && ((Date) otherObject).day == this.day;
        }
        return false;
    }

    /**
     * Generates a string version of the date object
     * @return A string version of date in mm/dd/yy format
     */
    @Override
    public String toString(){
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     * This method prints a statement based on the testing of the isValid method
     * @param date the date being tested
     * @param expectedOutput the expected output for whether the date should be valid
     * @param actualOutput the output returned by the isValid method for the date
     */
    public static void testResult(Date date, boolean expectedOutput, boolean actualOutput){
        if(expectedOutput == actualOutput){
            System.out.println("For the date: " + date.toString() +
                    ", both the expectedOutput and actualOutput are "+ actualOutput);
        }
        else{
            System.out.println("For the date: " + date.toString() +
                    ", the expectedOutput is " + expectedOutput + " and the" +
                    " actualOutput is " + actualOutput);
        }
    }

    /**
     * Executes the seventh test case as described on the design document -
     * February can have 29 days in a leap year.
     */
    private static void testMethodSeven(){
        Date date = new Date("2/29/2004");
        boolean expectedOutput = true;
        boolean actualOutput = date.isValid();
        System.out.print("**Test case #7: February has 29 days in a leap year. ");
        testResult(date, expectedOutput, actualOutput);
    }

    /**
     * Testbed main to exercise the isValid method.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Date date = new Date("2/29/2003");
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.print("**Test case #1: February only has 28 days in a non leapyear. ");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("-9/-2/-2023");
        expectedOutput = false;
        actualOutput=date.isValid();
        System.out.print("**Test case #2: There are not negative days, months, or years ");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("4/31/2003");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.print("**Test case #3: May does not have 31 days. ");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("13/31/2003");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.print("**Test case #4: There are not negative days," +
                " months, or years. ");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("12/32/2003");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.print("**Test case #5: December does not have 32 days. ");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("1/31/2003");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.print("**Test case #6: A valid date with 31 days. ");
        testResult(date, expectedOutput, actualOutput);

        testMethodSeven();
    }
}