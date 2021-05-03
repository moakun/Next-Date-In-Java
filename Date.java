import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;
public class Date {
    private final int year;
    private final int month;
    private final int day;
    private final int[] monthLengths = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public Date(int year, int month, int day) { this.year = year; this.month = month; this.day = day;
    try {
        validate(day,month,year);
    }
    catch (IllegalArgumentException e){
        System.out.println("Invalid Date Entered.");
    }
} //main method
 public static void main(String [] args)throws IOException{ //asking the input from the user
         BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
         System.out.println("Enter year: (Should not be negative) ");
         int y = Integer.parseInt(in.readLine());
         System.out.println("Enter Month: (Should be between 1-12) ");
         int m = Integer.parseInt(in.readLine());
         System.out.println("Enter day: (Should not exceed the maximum days of the entered month) ");
         int d = Integer.parseInt(in.readLine()); //creating a class object
     while(y < 1812 || y > 2012){
         System.out.println("Please enter a valid year between 1812 and 2012");
         System.out.println("Enter year: (Should not be negative) ");
          y = Integer.parseInt(in.readLine());
         System.out.println("Enter Month: (Should be between 1-12) ");
          m = Integer.parseInt(in.readLine());
         System.out.println("Enter day: (Should not exceed the maximum days of the entered month) ");
          d = Integer.parseInt(in.readLine()); //creating a class object
     }
      Date date = new Date(y,m,d);
      System.out.println("Today's Date: "+date.toString()); //calling the nextDate function to get the next day
      Date newDate = date.nextDate(y,m,d);
      System.out.println("Next Day's Date: "+newDate.toString());
    }
    public Date nextDate(int year,int month, int day){
        day += 1; //if it is a leap year
         if(year %4 == 0){ //if the day entered is the last day of the month it will now be greater than the month length
              if(day>monthLengths[month-1]){ //day will now be 1
                   day = 1; //and month will be incremented
                   month+=1; //if after incrementing the month it is greater than 12
                   if(month > 12){ //it is a new year the month will be 1
                        month = 1; //year will be incremented
                        year +=1;
                   }
              }
         } //if it is not a leap year
         else{ //check if the month is Feb
              if(month == 2){ //the the day was the last day of Feb
                   if(day>27) { //day will be 1
                        day = 1; //month will increase
                        month += 1; }
              }else{ //if the month is not Feb then same as above if the month is the last day of the month
                   if(day>monthLengths[month-1]){ day = 1; month+=1; if(month > 12){ month = 1; year +=1; }
                   }
              }
         } //creating a date object
        return new Date(year,month,day);
    }
    @Override public String toString() {
        String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
        return String.format("%d, %s, %d", day,months[month-1],year);
    }
    private void validate(int day,int month, int year)throws IllegalArgumentException {
        // throw an exception if the current values of year, month and day do not // make a valid date
         if(month<=0||month>12){ throw new IllegalArgumentException("Month Error!");
         } //if the year is not a leap year and the month entered is february
         if(year%4 != 0&&month == 2){ //the day cannot exceed 27
              if(day > 27){ throw new IllegalArgumentException("Non Leap Year Day Error!");
              }
         } //days cannot be negative and greater than month's length
         if(day>monthLengths[month-1]||day<=0){ throw new IllegalArgumentException("Day Error!"); }
    }
}