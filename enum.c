#include <stdio.h>
enum Mynumbers{
    two = 2,
    eight = 8
};
int main(void){
    printf("%d",eight / two);
    printf("%d",eight * two);
    printf("%d",eight + two);
    printf("%d",eight - two);
    printf("%d",eight ^ two);
    printf("%d",eight & two);
    printf("%d",eight == two);
    printf("%d",eight != two);
    printf("%d",eight | two);
    printf("%d",eight);
}
   