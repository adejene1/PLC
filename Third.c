#include <stdio.h>
#include <time.h>
#include <stdlib.h>

void stat_arr(){
  static int arr[50000];
}

void stack_arr(){
  int arr[50000];
}

void heap_arr(){
  int *arr = (int *) malloc(50000 * sizeof(int));
}


int main(void) {
        clock_t start_t = clock();

        for(int i = 0; i < 10000; i++){
                stat_arr();
        }
        clock_t end_t = clock();
        double timused = ((double) (end_t - start_t)) / CLOCKS_PER_SEC;
        printf("Static: %f \n",timused);
        

        start_t = clock();
           for(int i = 0; i < 10000; i++){
                 stack_arr();
        }
      end_t = clock();
      timused = ((double) (end_t - start_t)) / CLOCKS_PER_SEC;
      printf("Stack: %f \n",timused);
      


      
        start_t = clock();
           for(int i = 0; i < 10000; i++){
                 heap_arr();
        }
      end_t = clock();
      timused = ((double) (end_t - start_t)) / CLOCKS_PER_SEC;
      printf("Heap: %f \n",timused);
      
}

/*
The one that declared array statically and array on the stack have not much different on the 
required to call those functions. Even thought the static array much more effiecent than
the stack array. However, the array from the heap has much different on time and takes longe 
to call the function becuase the function called through pointer. And this will take time 
compared to the other two. 
We can not use array from the heap on java because we cant use pointer on  java. 




*/