#include <stdio.h>
#include <stdlib.h>

void converte(int minutos, int *h, int *m){
    *m = minutos%60;
    *h = minutos/60;

}

int main()
{
    int minutos = 286;
    int horas;
    int mins;

    converte(minutos, &horas, &mins);

    printf("%d : %d\n", horas, mins);
}