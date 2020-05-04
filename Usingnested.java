package com.Abrham;

import java.util.Scanner;

public class Usingnested {
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

        if(first>second) {
            if(first>third) {
                if(second>third) {
                    max=first;
                    mid=second;
                    min=third;
                }
                else {
                    max=first;
                    mid=third;
                    min=second;
                }
            }
            else {
                max=third;
                mid=first;
                min=second;
            }

        }
        else {
            if(second<third) {
                max=third;
                mid=second;
                min=first;
            }
            else {
                if(first<third) {
                    max=second;
                    mid=third;
                    min=first;
                }
                else {
                    max=second;
                    mid=first;
                    min=third;
                }
            }

        }
        System.out.println("Max - " + max + "\n" + "Mid - " + mid + "\n" + "Min - " + min);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Time - " + totalTime);
    }
}

/* Time complexity is the nested better however readablity the one that does not use nested is better  */