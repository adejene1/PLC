#include <stdio.h>

int main()
{
   int j = -3;
   
   int i = 0;
   
   while(i < 3){
       switch(j + 2){
         case 3:
         case 2:
            j--;
            continue;
         case 0:
            j += 2;
            continue;
         default:
            j = 0;
        }
        
       
       j = 3 - i;
       printf("%d ",j);
       i++;
   }
}