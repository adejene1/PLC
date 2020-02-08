package com.Abrham;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        File le = new File("C:\\Users\\abrham\\Desktop\\Java DataStructuer\\LexicalAnalyzer\\src\\com\\Abrham\\le");

        try{
            BufferedReader myFile = new BufferedReader(new FileReader(le));

            String sent;
            while ((sent = myFile.readLine()) != null)
            {
                for(int i=0;i<sent.length();i++)
                {
                    char sentence=sent.charAt(i);


                    if((sentence>='A' && sentence<='Z') || (sentence>='a' && sentence<='z') || (sentence>='0' && sentence<='9')) {
                        System.out.print(sentence);
                    } else{
                        System.out.println("\n"+sentence);
                }
                    }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }

}