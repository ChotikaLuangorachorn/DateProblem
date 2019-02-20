package program;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("insert day: ");
        int day = sc.nextInt();
        System.out.print("insert month: ");
        int mon = sc.nextInt();
        System.out.print("insert year: ");
        int year = sc.nextInt();

        nextDate(day,mon,year);



    }
    public static boolean isLeap(int year){
        if((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
            return true;
        }
        return false;
    }
     static void nextDate(int day, int mon, int year){
         String next_date = "";
        if(mon==12){
            if(day==31){
                day = 1; mon = 1 ; year++;
            }else{
                day++;
            }
        }else if (mon==2){
            if(isLeap(year)){
                if(day==29){
                    day = 1; mon = 3;
                }else{
                    day++;
                }
            }else {
                if(day==28){
                    day = 1; mon = 3;
                }else {
                    day++;
                }
            }
        } else if(mon==1 || mon == 3 || mon ==7 || mon == 8 || mon ==10){
            if(day==31){
                day = 1; mon++;
            }else{
                day++;
            }
        }else{
            if(day==30){
                day = 1; mon++;
            }else {
                day++;
            }
        }
        try {
            LocalDate date = LocalDate.of(year,mon,day);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYY");
            next_date = date.format(formatter);
            System.out.println(next_date);

        }catch (DateTimeException e){
            System.out.println("invalid input");
        }

     }
}
