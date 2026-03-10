#include <stdio.h>
#include <stdlib.h>

int main()
{
    double a;
    double b;

    printf("A:");
    scanf("%lf",&a);
    printf("B:");
    scanf("%lf",&b);

    double *ps;
    ps = (double*)malloc(sizeof(double));
    *ps = a + b;

    // 1) Resultado de A + B referenciado por *ps
    printf("A + B (referenciado) = %2.2lf\n", *ps);

    // 2) Endereço armazenado em *ps e o valor referenciado
    printf("Endereço armazendo em *ps = %p\n", ps);
    printf("Valor referenciado por *ps = %2.2lf\n", *ps);

    // 3) Criar variável doubleC e guardar valor de *ps
    double c = *ps;
    printf("Valor de C (referenciado de *ps) = %2.2lf\n", c);
    
    // 4) Copiar endereço de *ps para o ponteiro *qs
    double *qs;
    qs = ps;
    *qs += 100.0;
    printf("Valor referenciado por *ps = %2.2lf\n", *ps); //mesmo valor *ps == *qs
    printf("Valor referenciado por *qs = %2.2lf\n", *qs);

    a = *qs;
    qs = &b;
    *qs -= 10;

    printf("\t|ENDEREÇO\t|VALOR\t\n");
    printf("A\t|%p |%2.2lf\n",&a,a);
    printf("B\t|%p |%2.2lf\n",&b,b);
    printf("*ps\t|%p |%2.2lf\n", ps, *ps);
    printf("*qs\t|%p |%2.2lf\n", qs, *qs);
}