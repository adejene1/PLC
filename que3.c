#include <stdio.h>
#include <time.h>

int main(void){
    clock_t start_t = clock();
        for(int i = 0; i < 50; i++){
            fun_sub();
        }
    clock_t end_t = clock();
    double timeUsed = ((double)(end_t - start_t)) / CLOCKS_PER_SEC;
    printf("Time for subscripting - %f",timeUsed);
    printf(" ")
    start_t = clock();
        for(int i = 0; i < 50; i++){
            fun_poi();
        }
    end_t = clock();
    timeUsed = ((double)(end_t - start_t)) / CLOCKS_PER_SEC;
    printf("Time for pointer - %f",timeUsed);
}

void fun_sub(){
    int myArray[10][10] = {1};

    for(int i = 0; i < 10; i++){
        for(int j = 0; j < 10; j++){
            myArray[i][j];
        }
    }
    return;
}

void fun_poi(){
    int myArray[10][10] = {1};

    for(int i = 0; i < 10; i++){
        for(int j = 0; j < 10; j++){
            *(*(myArray+i)+j);
        }
    }
}