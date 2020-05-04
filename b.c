#include <stdio.h>

int main()
{
   
    int j = -3;
    
    int i;
    
    for(i = 0; i < 3; i++){
        if(j + 2 == 3){
            
        }else if(j + 2 == 2){
            j--;
        }else if(j + 2 == 0){
            j += 2;
        }else{
            j = 0;
        }
        
        if(j > 0){
            goto Label;
        }
        
        j = 3 - i;
        printf("%d ", j);
    }
    
    Label: i = 3;
}
