package com.Abrham;

public class MyFifth {
    public static void main(String[] args) {
        int[][] myVal = {{0,5,0}, {1,0,0}, {0,0,0}};
        noGoTo(myVal);

    }

    public static void noGoTo(int[][] myArray){
        boolean myBool = true;
        for(int i = 0; i < myArray.length; i++){
            int count = 0;
            for(int j = 0; j < myArray.length; j++){
                if(myArray[i][j] == 0){
                    count++;
                }

                if(count == myArray.length && myBool == true){
                    System.out.println("First all zero-row is: " + i);
                    myBool = false;
                }
            }
        }
    }
}
