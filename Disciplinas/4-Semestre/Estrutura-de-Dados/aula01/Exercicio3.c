#include <stdio.h>
#include <stdlib.h>

int main()
{
    char ch;
    printf("Digite um caractere: ");
    scanf("%c", &ch);
    printf("CHAR    = %c\n", ch);
    printf("DECIMAL = %d\n", ch);
    printf("OCTAL   = %o\n", ch);
    printf("HEXA    = %x\n", ch);
}