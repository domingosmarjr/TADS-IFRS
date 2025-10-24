import java.util.Date;

public class App {
    public static void main(String[] args) throws Exception {

        // --------TESTES MÉDICO---------
        // Criar médico e adicionar especialidades
        // Medico m1 = new Medico("Linda", "12547892120", "linduska@gmail.com", "55991237891", 12345678);
        // m1.adicionarEspecialidades("Psiquiatria");
        // m1.adicionarEspecialidades("Cardiologia");
        // m1.adicionarEspecialidades("Ortopedia");
        
        // // Teste de Getters
        // System.out.println(m1.getNome() == "Linda");
        // System.out.println(m1.getCpf() == "12547892120");
        // System.out.println(m1.getEmail() == "linduska@gmail.com");
        // System.out.println(m1.getTelefone() == "55991237891");
        // System.out.println(m1.getCrm() == 12345678);
        // System.out.println(m1.getEspecialidades(0) == "Psiquiatria");
        // System.out.println(m1.getEspecialidades(1) == "Cardiologia");
        // System.out.println(m1.getEspecialidades(2) == "Ortopedia");

        // // Teste de Setters
        // m1.setNome("Linda Isabel");
        // m1.setCpf("12547892121");
        // m1.setEmail("linduska2@gmail.com");
        // m1.setTelefone("55991237892");
        // m1.setCrm(12345677);
        // System.out.println(m1.getNome() == "Linda Isabel");
        // System.out.println(m1.getCpf() == "12547892121");
        // System.out.println(m1.getEmail() == "linduska2@gmail.com");
        // System.out.println(m1.getTelefone() == "55991237892");
        // System.out.println(m1.getCrm() == 12345677);

        // // Teste de Especialidades Médicas
        // // - Adicionar Especialidades
        // System.out.println(m1.getEspecialidades());
        // m1.adicionarEspecialidades("Geriatria");
        // System.out.println(m1.getEspecialidades());

        // // - Verficiar slot de especialidade
        // System.out.println(m1.getEspecialidades(0) == "Psiquiatria");
        // System.out.println(m1.getEspecialidades(1) == "Cardiologia");
        // System.out.println(m1.getEspecialidades(2) == "Ortopedia");
        // System.out.println(m1.getEspecialidades(3) == "Geriatria");

        // // Remover especialidade
        // // [0] - Psi            [0] - Psi
        // // [1] - Car    --->    [1] - Ger
        // // [2] - Ort
        // // [3] - Ger
        // m1.removerEspecialidade("Cardiologia");
        // m1.removerEspecialidade(2);
        // System.out.println(m1.getEspecialidades(0) == "Psiquiatria");
        // System.out.println(m1.getEspecialidades(1) == "Ortopedia");

        // // > Tentar remover especialidade de índice inválido
        // try {
        //     m1.removerEspecialidade(5);
        // } catch (Exception e) {
        //     System.out.println(true);
        // }
        // try {
        //     m1.removerEspecialidade(-1);
        // } catch (Exception e) {
        //     System.out.println(true);
        // }

        // m1.abrirAgenda(24,10,2025,13);
        // m1.abrirAgenda(24,12,2025,17);
        // m1.abrirAgenda(01,01,2026,8);

        // System.out.println(m1.listarAgenda());

        // ------------------------------------------------
        Medico m1 = new Medico("Linda", "12547892120", "linduska@gmail.com", "55991237891", 12345678);
        m1.abrirAgenda(24, 10, 2025);
        m1.abrirAgenda(25, 10, 2025);
        System.out.println(m1.listarAgendaCompleta());
        System.out.println("-----------");
        System.out.println(m1.listarAgendaConsultas());
        System.out.println("lista de consultas data específica:");
        System.out.println(m1.listarAgendaConsultas(25,10,2025));


    }
}
