#include <stdio.h>

int main()
{
    int i = 10, j = 10, sum1, sum2;
    sum1 = (i / 2) + fun(&i);
    sum2 = fun(&j) + (j / 2);
        printf("Sum1: %d ",sum1);
        printf("Sum2: %d",sum2);
    return 0;
}
int fun(int *k) {
    *k += 4;
    return 3 * (*k) - 1;
}
