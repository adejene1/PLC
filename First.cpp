#include <iostream>
using namespace std;

enum num1 {
        one = 1996,
        two = 1997,
        three = 1998,
        four = 1999,
        five = 2000
};
enum num2 { John, Doe};

int main(){
    num1 n = three;
    num2 m = Doe;
    
    if(m==Doe){
        int age = 2019 - n;
        cout <<"Doe's age - ";
        cout << age;
    }else {
        cout<<"Hard to identify the person and age";
    }
       return 0;
}
