package com.Abrham;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyFirstQue { public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);


    System.out.println("Enter the literal: ");
    String myLiterals = sc.nextLine();

    String intRegx = "[+-]?[0-9][0-9]*";
    Pattern intP = Pattern.compile(intRegx);
    Matcher intMatch = intP.matcher(myLiterals);

    String floatRegx = "[+-]?[0-9]+(\\.[0-9]+)?([Ee][+-]?[0-9]+)?";
    Pattern floatP = Pattern.compile(floatRegx);
    Matcher floatMatch = floatP.matcher(myLiterals);

    String varRegx = "[a-zA-z$_][a-zA-Z0-9$_]*";
    Pattern varP = Pattern.compile(varRegx);
    Matcher varMatch = varP.matcher(myLiterals);

    if(intMatch.find() && intMatch.group().equals(myLiterals)){
        System.out.println("Integer");
    }else if(floatMatch.find() && floatMatch.group().equals(myLiterals)){
        System.out.println("Float");
    }else if(varMatch.find() && varMatch.group().equals(myLiterals)){
        System.out.println("Variable");
    }else{
        System.out.println("String");
    }
}



}