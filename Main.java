package com.Abrham;

import java.io.EOFException;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;

public class Main {
    int charClass;
    char[] lexeme = new char[100];
    char nextChar;
    int lexlen;
    int token;
    int nextToken;
    FileInputStream in_fp = null;


    final int Letter = 0;
    final int Digit = 1;
    final int Unknown = 99;


    final int int_Lit = 10;
    final int Ident = 11;
    final int Assign_op = 20;
    final int Add_op = 21;
    final int Sub_op = 22;
    final int Mul_op = 23;
    final int Div_op = 24;
    final int Mod_op = 25;
    final int Left_paren = 26;
    final int Right_paren = 27;

    
    public int lookup(char ch){
        switch (ch){
            case '(':
                addChar();
                nextToken = Left_paren;
                break;
            case ')':
                addChar();
                nextToken = Right_paren;
                break;
            case '+':
                addChar();
                nextToken = Add_op;
                break;
            case '-':
                addChar();
                nextToken = Sub_op;
                break;
            case '*':
                addChar();
                nextToken = Mul_op;
                break;
            case '/':
                addChar();
                nextToken = Div_op;
                break;
            case '%':
                addChar();
                nextToken = Mod_op;
                break;
            default:
                addChar();
                nextToken = -1;
                break;
                
        }
        
        return nextToken;
    }

    public void addChar(){
        if(lexlen <= 90){
            lexeme[lexlen++] = nextChar;
            lexeme[lexlen] = '\0';
        }else {
            System.out.println("Error - lexeme is too long \n");
        }
    }

    public void getChar(){
        try{
            if(nextChar == in_fp.read()){
                if (Character.isAlphabetic(nextChar)){
                    charClass = Letter;
                }else if (Character.isDigit(nextChar)){
                    charClass = Digit;
                }else {
                    charClass = Unknown;
                }
            }
        }catch (IOException e){
            charClass = -1;
        }
    }

    public void getNonBlank(){
        while (Character.isSpaceChar(nextChar)){
            getChar();
        }
    }

    public int lex(){
        lexlen = 0;
        getNonBlank();
        switch(charClass){
            case Letter:
                addChar();
                getChar();
                while (charClass == Letter || charClass == Digit){
                    addChar();
                    getChar();
                }
                nextToken = Ident;
                break;

            case Digit:
                addChar();
                getChar();
                while (charClass == Digit){
                    addChar();
                    getChar();
                }
                nextToken = int_Lit;
                break;

            case Unknown:
                lookup(nextChar);
                getChar();
                break;

            case -1:
                nextToken = -1;
                lexeme[0] = 'E';
                lexeme[1] = 'O';
                lexeme[2] = 'F';
                lexeme[3] = '\0';
                break;
        }
        System.out.println("Next token is: " + nextToken + " Next lexeme is: " + lexeme);
        return nextToken;
    }

    public void expr(){
        System.out.println("Enter <expr>");
        term();

        while (nextToken == Add_op || nextToken == Sub_op){
            lex();
            term();
        }
        System.out.println("Exit <expr>");
    }

    public void term(){
        System.out.println("Enter <term>");
        mod();

        while (nextToken == Mul_op || nextToken == Div_op){
            lex();
            mod();
        }
        System.out.println("Exit <term>");
    }

    public  void mod(){
        System.out.println("Enter <mod>");
        factor();

        while (nextToken == Mod_op){
            lex();
            factor();
        }

        System.out.println("Exit <mod>");
    }

    public void factor(){
        System.out.println("Enter <factor>");
        if(nextToken == Ident || nextToken == int_Lit){
            lex();
        }else if(nextToken == Left_paren){
            lex();
            expr();

            if(nextToken == Right_paren){
                lex();
            }else{
                error();
            }
        }else {
            error();
        }

        System.out.println("Exit <factor>");
    }

    public void error(){
        System.out.println("Error");
    }
}

/*  The operator precedence is which operator comes first in expression, and associativity is when to operations have
 same precedence appear in same expression. The order of the functions(expr(), term(), mod() and factor()) will
  help you to know the precedence of the operator and if two operator appear in same function it means that
  they have associativity*/