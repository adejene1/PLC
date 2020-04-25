#include <iostream>
using namespace std;

int main(){
   int one = 1996;
   int two = 1997;
   int three = 1998;
   int four = 1999;
   int five = 2000;
   
   string str1 = "Doe";
   string str2 = "John";
   
   if(str1 == "Doe"){
       cout << "Doe's age - ";
       cout << 2019 - three;
   }else {
       cout <<"Hard to identify the person";
   }

   return 0;
}

/* Enumeration has more readability since we are not gonna decalare the data type every time we 
we assign a value to variable. This make it to not get confuesed easily. But integer variable has 
more reliabilty*/