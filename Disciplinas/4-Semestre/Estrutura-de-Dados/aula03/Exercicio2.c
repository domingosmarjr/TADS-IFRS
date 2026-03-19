#include <stdio.h>
#include <stdlib.h>

// 1) Defina uma estrutura Motor (int potencia, int modelo, double consumo)
typedef struct motor {
    int potencia;
    int modelo;
    double consumo;
} Motor;

// 2) Inclua na estrutura Carro, um motor;
typedef struct carro {
    char nome[20];
    char marca[20];
    int ano;
    Motor motor;
} Carro;

void mostrarDadosCarro (Carro carro) {
    printf("\n|=== CARRO ================================\n");
    printf("|NOME: %s\t", carro.nome);
    printf("MARCA: %s\n", carro.marca);
    printf("|ANO: %d\t\tMOTOR: %d\n", carro.ano, carro.motor.modelo);
    printf("|CONSUMO: %.2fl\t\tPOTÊNCIA: %d\n", carro.motor.consumo, carro.motor.potencia);
    printf("|==========================================\n");
};

void trocarConsumoMotor (Carro *carro, int consumo) {
    carro->motor.consumo = consumo;
};

void consultarModeloMotor (Carro *carro) {
    printf("\n|Modelo Motor: %d\n\n", carro->motor.modelo);
}

int main() {
// 3) Defina valores para os atributos de um carro e seu motor;
    Motor v8 = {800, 8, 10};
    Carro c1 = {"Fusca 1600", "Volkswagen", 1978, v8};

// 4) Mostre os dados do carro;
    mostrarDadosCarro(c1);
    
// 5) Crie uma função para trocar o consumo do motor, passe a estrutura carro para função;
    trocarConsumoMotor(&c1, 30);
    mostrarDadosCarro(c1);

// 6) Crie uma função para consultar o modelo do motor, passe a estrutura carro como parâmetro, mostre o modelo do motor;
    consultarModeloMotor(&c1);

}