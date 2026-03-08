#include <stdio.h>
#include <stdlib.h>

int main()
{

    // ------------------------------------------------
    // FOR
    int x;
    int y;
    for(x = 0, y = 0; x + y < 100; ++x, y = x+y)
        printf("X = %x \t Y = %d \t\t X+Y = %d \n", x, y, x+y);

    // ------------------------------------------------
    // WHILE
    int i = 0;
    while (i < 10) {
        printf("%d\n",i);
        i++;
    }

    // ------------------------------------------------
    // DO WHILE (testa condição só no final, garantindo que execute uma vez)
    int num = 0;
    do {
        printf("Num: %d\n", num);
        scanf("%d",&num);
    } while(num < 100);

    return 0;
}