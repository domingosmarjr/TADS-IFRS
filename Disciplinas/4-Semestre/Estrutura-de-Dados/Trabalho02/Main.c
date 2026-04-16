#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Musica.h"
#include "Playlist.h"

/*
    Musica:
        exibirNomeMusica

    Player:
        Play (mostra música atual e se for primeira começa)
        Avançar (move ponteiro musicaAtual para proximo)
            - chegou no fim = avisa que chegou no fim e pergunta se quer ir para primeira
        Retroceder (move ponteiro musicaAtual para anterior)
            - chegou no incio = avisa que é primeira e pergunta se quer ir pro fim
        Sair (encerra o programa)

*/

int main() {

    Musica *m1 = (Musica*)malloc(sizeof(Musica));
    Musica *m2 = (Musica*)malloc(sizeof(Musica));
    Musica *m3 = (Musica*)malloc(sizeof(Musica));
    Musica *m4 = (Musica*)malloc(sizeof(Musica));
    Musica *m5 = (Musica*)malloc(sizeof(Musica));
    Musica *m6 = (Musica*)malloc(sizeof(Musica));
    Musica *m7 = (Musica*)malloc(sizeof(Musica));
    Musica *m8 = (Musica*)malloc(sizeof(Musica));
    Musica *m9 = (Musica*)malloc(sizeof(Musica));
    Musica *m10 = (Musica*)malloc(sizeof(Musica));
 
    m1->id = 0;
    strcpy(m1->titulo, "In the End");
    strcpy(m1->artista, "Linkin Park");
    m1->duracao = 120;

    m2->id = 1;
    strcpy(m2->titulo, "Paradise");
    strcpy(m2->artista, "Stratovarius");
    m2->duracao = 121;

    m3->id = 2;
    strcpy(m3->titulo, "Se o Mundo Acabar");
    strcpy(m3->artista, "Rashid");
    m3->duracao = 122;

    m4->id = 3;
    strcpy(m4->titulo, "Orientai-me");
    strcpy(m4->artista, "Oriente");
    m4->duracao = 123;

    m5->id = 4;
    strcpy(m5->titulo, "Pantera Negra");
    strcpy(m5->artista, "Emicida");
    m5->duracao = 124;

    m6->id = 5;
    strcpy(m6->titulo, "Dadinho");
    strcpy(m6->artista, "Cone Crew");
    m6->duracao = 125;

    m7->id = 6;
    strcpy(m7->titulo, "Religião do F");
    strcpy(m7->artista, "Cone Crew");
    m7->duracao = 126;

    m8->id = 7;
    strcpy(m8->titulo, "Repente");
    strcpy(m8->artista, "Rapadura");
    m8->duracao = 127;

    m9->id = 8;
    strcpy(m9->titulo, "Pangeia");
    strcpy(m9->artista, "Fabio Braza");
    m9->duracao = 128;

    m10->id = 9;
    strcpy(m10->titulo, "Memento");
    strcpy(m10->artista, "Leto");
    m10->duracao = 129;

    No *n1 = (No*)malloc(sizeof(No));
    n1->dado = *m1;
    No *n2 = (No*)malloc(sizeof(No));
    n2->dado = *m2;
    No *n3 = (No*)malloc(sizeof(No));
    n3->dado = *m3;
    No *n4 = (No*)malloc(sizeof(No));
    n4->dado = *m4;
    No *n5 = (No*)malloc(sizeof(No));
    n5->dado = *m5;
    No *n6 = (No*)malloc(sizeof(No));
    n6->dado = *m6;
    No *n7 = (No*)malloc(sizeof(No));
    n7->dado = *m7;
    No *n8 = (No*)malloc(sizeof(No));
    n8->dado = *m8;
    No *n9 = (No*)malloc(sizeof(No));
    n9->dado = *m9;
    No *n10 = (No*)malloc(sizeof(No));
    n10->dado = *m10;

    exibirMusica(m1);
    exibirMusica(m2);
    exibirMusica(m3);
    exibirMusica(m4);
    exibirMusica(m5);
    exibirMusica(m6);
    exibirMusica(m7);
    exibirMusica(m8 );
    exibirMusica(m9);
    exibirMusica(m10);

    Playlist *p1 = criaPlaylist();


    insereInicio(p1, n3);
    insereInicio(p1, n2);
    insereInicio(p1, n1);
    inserePosicao(p1, n4, 4);

    // removeInicio(p1);
    // removeInicio(p1);
    // removeInicio(p1);
    // removeInicio(p1);
    
    // removeFim(p1);
    // removeFim(p1);
    // removeFim(p1);
    // removeFim(p1);

    // removePosicao(p1, -1);
        
    // insereFim(p1, n1);
    // insereFim(p1, n2);
    // insereFim(p1, n3);
    // insereInicio(p1, n4);
    

    
    play(p1);
    mostraMusicaAtual(p1);

    mostraPlayslistED(*p1);


 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
    // printf("Olá\n");

    // int opcao;
    // do {
    //     scanf("%d", &opcao);
    //     switch (opcao) {
    //         case 1:
    //             printf("1\n");
    //             break;
    //         case 2:
    //             printf("2\n");            
    //             break;
    //         default:
    //             break;
    //     }

    // } while (opcao != 0);
}