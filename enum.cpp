#include <iostream>
using namespace std;

enum Mynumbers{
    five = 5,
    ten = 10,
    two = 2,
    eight = 8
};

int main(void){
cout << (five + ten) << endl;
cout << (ten / two) << endl;
cout << (eight - five) << endl;
cout << (five * two) << endl;
cout << (five & ten) << endl;
cout << (ten ^ two) << endl;
cout << (eight | five) << endl;
cout << (five == two) << endl;
cout << (five != ten) << endl;
cout << (eight >> 1) << endl;


}