package com.Abrham;

import java.util.Scanner;

public class Withoutnested {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first - ");
        int first = sc.nextInt();
        System.out.println("Enter second - ");
        int second = sc.nextInt();
        System.out.println("Enter third - ");
        int third = sc.nextInt();

        long startTime = System.nanoTime();

        int max = 0;
        int mid = 0;
        int min = 0;

        if(first > second && second > third && first > third){
            max = first;
            mid = second;
            min = third;
        }else if(first > second && first > third && second < third){
            max = first;
            mid = third;
            min = second;
        }else if(second > first && second > third && first > third){
            max = second;
            mid = first;
            min = third;
        }else if(second > first && second > third && first < third){
            max = second;
            mid = third;
            min = first;
        }else if(third > first && third > second && first > second){
            max = third;
            mid = first;
            min = second;
        }else{
            max = third;
            mid = second;
            min = first;
        }

        System.out.println("Max - " + max + "\n" + "Mid - " + mid + "\n" + "Min - " + min);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Time - " + totalTime);
    }
}
