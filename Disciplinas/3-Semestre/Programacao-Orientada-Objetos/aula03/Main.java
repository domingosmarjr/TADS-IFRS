public class Main {
    public static void main(String args[]){

        // DESENVOLVA UM SISTEMA 
        // QUE DADO UMA LISTA DE ALUNOS E SUAS PROVAS,
        // DÊ UM GABARITO E A NOTA & QUANTIDADE MAX DE APROVADOS,
        // RETORNANDO A LISTA COM NÚMERO DE INSCRIÇÃO E NOME ORDENADOS POR NOTA

        // String [] gabarito = {"A", "A", "A", "D", "E", "C", "B", "B", "C", "E"};
        // String [] prova1 =   {"A", "B", "C", "A", "C", "C", "B", "B", "C", "E"};
        // String [] prova2 =   {"B", "A", "C", "A", "E", "E", "B", "B", "C", "D"};
        // //Qual foi a nota da prova P1 e P2?
        // int n1 = 0;
        // for (int i = 0; i < gabarito.length; i++) {
        //     if (gabarito[i] == prova1[i]){
        //         n1++;
        //     }    
        // }
        // System.out.println(n1);

        // PODERIA SER UMA MATRIZ [ALUNOS] [RESPOSTAS]
        // PROBLEMAS?
        // FALTA DE CONFIABILIDADE, SEGURANÇA NO ACESSO E ALTERAÇÃO DE INFORMAÇÕES
        // SE TUDO É PÚBLICO, QUALQUER CLASSE EM QUALQUER LUGAR PODE MODIFICAR
        

        // ---------------------------------------

        // CRIANDO INSTÂNCIA GABARITO
        // 1 - Possibilidade 1:
        //      Gabarito gabarito = new Gabarito();
        // 2 - Possibilidade 2:
        //      Gabarito gabarito = new Gabarito("AABCDEBCDE");

        Gabarito gabarito = new Gabarito("AABCDEBCDE");
        System.out.println(gabarito.getRespostaQuestao(1));

        Aluno a1 = new Aluno ("Francine Moraes");
        Aluno a2 = new Aluno ("Jorge Alves");
        System.out.println(a1);
        System.out.println(a2);

        Prova p1 = new Prova(a1);
        p1.responder("AABCDEBCDE");
        Prova p2 = new Prova(a2);
        p2.responder("DABCDEBCDE");

        CorretorProva corretor = new CorretorProva();
        
        List<Prova> provas = Arrays.asList(p1, p2);
        for (Prova prova : provas) {
            int nota = corretor.corrigir(prova, gabarito);
            System.out.println(prova.getAluno() + " NOTA " + nota);
        }

    }
}