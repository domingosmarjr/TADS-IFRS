public class Main {
    public static void main(String args[]) {

        // main pode ser declarativo
        Paciente p1 = new Paciente("Vinicius","14/06/1991","9999999");
        System.out.println(p1);

        Medico m1 = new Medico("Pedro","CRM_123123");//, ["clinico geral","ortopedista"]);
        System.out.println(medico);
        
        // Tecnico t1 = new Tecnico("Lucas", "COD_123123123", ["RADIOGRAFIA","ANÁLISE CLINICA"]);

        Consulta c1 = new Consulta(m1, p1);

        // ou com swith case - fica mais legal :)

        /*
         * Pessoa
         * -Medico
         * -Tecnico
         * 
         */



    }
}