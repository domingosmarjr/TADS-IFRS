
import java.util.Date;
import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) throws Exception {

        AgendaClinica a1 = new AgendaClinica();

        // // ===============================================================================================
        // // 
        // // TASKS BÁSICAS DO TRABALHO
        // // 
        // // [1](0.1) = Criar Paciente
        // Paciente p1 = new Paciente("Pedro", "12345678901", "pedro@live.com", "5553932331212", "01/09/2000", 'M', TipoSanguineo.APositivo);
        
        // // [2](0.1) = Criar Medico
        // Medico m1 = new Medico("Linda", "12345678900", "linda@gmail.com", "5553991752300", 123456);

        // // [3](0.1) = Criar Técnico
        // Tecnico t1 = new Tecnico("Alex", "12345678902", "alex@bol.com", "5553997845122", "123321");

        // // [4](0.1) = Abrir agenda do Médico
        // m1.abrirAgenda(10, 11, 2025);
        // System.out.println(m1.listarAgenda());

        // // [5](0.1) = Fazer agendamento
        // // ------Médico marcar consulta.
        // m1.marcarConsulta(a1, p1, 10, 11, 2025, 14);
        // // ------Paciente marcar consulta
        // p1.marcarConsulta(a1, m1, 10, 11, 2025, 15);

        // // [6](0.1) = Quando acontece agendamento gera um new Consulta()
        // System.out.println(a1.getListaConsultas().get(0).getTipo() == TipoConsulta.CONSULTA);

        // // [7](0.1) = Quando encerra uma consulta se gera um prontuario
        // a1.getListaConsultas().get(0).encerrar("Dor de cabeça", "Dorflex 20mg");
        // System.out.println(a1.getListaConsultas().get(0).getProntuario());

        // // [8](0.1) = Médico/Paciente pode solicitar exame
        // m1.marcarExame("Sangue", a1, p1, 10, 11, 2025, 13);
        // t1.marcarExame("Urina", a1, p1, 12, 11, 2025, 13);
        // p1.marcarExame("Fezes", a1, t1, 12, 11, 2025, 14);
        // p1.marcarExame("Rotina", a1, m1, 10, 11, 2025, 14);

        // // [9](0.1) = Técnico/Médico encerram exames
        // // ------Ao encerrar o exame, já gera laudo.
        // System.out.println(a1.getExame(m1, p1, 10, 11, 2025, 13));
        // m1.encerrarExame("Limpo", a1.getExame(m1, p1, 10, 11, 2025, 13));
        // System.out.println(a1.getExame(m1, p1, 10, 11, 2025, 13).getLaudo() == "Limpo");
        // System.out.println(a1.getExame(m1, p1, 10, 11, 2025, 13));

        // // [10](0.1) = Histórico de Paciente
        // p1.getHistorico();

        // ALTERAÇÕES QUE EU FARIA: 
        // 1)   Adaptaria uma classe só para lidar com lidar com o LocalDateTime
        //      realizando a conversão int(dia, mes, ano, hora) -> LocalDateTime.
        //      Fiz isso diversas vezes repetidamente no código, e notei que poderia ser uma classe estática [correto?]
        //      Util com métodos de conversão, que eu só chamaria.
        // 
        // 2)   Refatoraria alguns métodos e suas assinaturas para fazerem mais sentido lógico na leitura.
        // 
        // 3)   Criaria uma classe específica HorarioConsultaExame(), que teria detalhes como:
        //      -funcionario
        //      -paciente
        //      -data
        //      -etc.
        //      A partir dela, teria uma List<HorarioConsultaExame> dentro de uma AgendaClinica()
        // 
 

        // // ===============================================================================================
        // // 
        // // TESTES DE TODAS AS FUNCIONALIDADES
        // // 


        // // =========================================== SUPERCLASS PESSOA ===========================================
        // System.out.println("------------");
        // Paciente p2 = new Paciente("Adir", "12345678910", "adir@gmail.com", "5553991752680", "01/01/1980", 'M', TipoSanguineo.APositivo);

        // // TESTES DE SUPERCLASS Pessoa()
        // System.out.println("TESTES DE SUPERCLASS PESSOA");
        // System.out.println(p2.getNome() == "Adir");
        // System.out.println(p2.getCpf() == "12345678910");
        // System.out.println(p2.getEmail() == "adir@gmail.com");
        // System.out.println(p2.getTelefone() == "5553991752680");

        // p2.setNome("Adir Junior");
        // p2.setCpf("12345678911");
        // p2.setEmail("adirjunior@gmail.com");
        // p2.setTelefone("5399175268212");

        // System.out.println(p2.getNome() == "Adir Junior");
        // System.out.println(p2.getCpf() == "12345678911");
        // System.out.println(p2.getEmail() == "adirjunior@gmail.com");
        // System.out.println(p2.getTelefone() == "5399175268212");

        // try {
        //     // Nomes devem ser >= 2
        //     p2.setNome("A");
        //     System.out.println(false);
        // } catch (Exception e) {
        //     System.out.println(true);
        // }

        // try {
        //     // CPF deve ter exatamente 11 dígitos (não valida formato matemático de CPF)
        //     p2.setCpf("123");
        //     System.out.println(false);
        // } catch (Exception e) {
        //     System.out.println(true);
        // }

        // try {
        //     // Não pode ser != de "xxxx@xxx.xxx"
        //     p2.setEmail("adir@pedro");
        //     System.out.println(false);
        // } catch (Exception e) {
        //     System.out.println(true);
        // }

        // try {
        //     // Não pode ser != 13 dígitos (só vale números e sem espaços)
        //     // 55 53 9 8888 8888
        //     p2.setEmail("1234");
        //     System.out.println(false);
        // } catch (Exception e) {
        //     System.out.println(true);
        // }

        // // =========================================== CLASSE PACIENTE ===========================================
        // System.out.println("------------");
        // System.out.println(p2.getDataNascimento().equals("01/01/1980"));
        // System.out.println(p2.getSexo() == 'M');
        // System.out.println(p2.getTipoSanguineo() == TipoSanguineo.APositivo);

        // p2.setDataNascimento("02/01/1980");
        // p2.setSexo('F');
        // p2.setTipoSanguineo(TipoSanguineo.ABNegativo);

        // System.out.println(p2.getDataNascimento().equals("02/01/1980"));
        // System.out.println(p2.getSexo() == 'F');
        // System.out.println(p2.getTipoSanguineo() == TipoSanguineo.ABNegativo);

        // try {
        //     // Tentar inserir uma data inválida.
        //     p2.setDataNascimento("2001/10/48");
        //     System.out.println(false);
        // } catch (Exception e) {
        //     System.out.println(true);
        // }

        // try {
        //     // Sexo é exatamente 'M' ou 'F'
        //     p2.setSexo('m');
        //     System.out.println(false);
        // } catch (Exception e) {
        //     System.out.println(true);
        // }

        // // Todo paciente tem um código para cadastro
        // System.out.println(p2.getCodigo() == 1);

        // // =========================================== CLASSE MEDICO ===========================================
        // System.out.println("------------");
        // System.out.println("TESTES DE SUPERCLASS MÉDICO");
        // Medico m1 = new Medico("Linda", "12312312300", "linda@gmail.com", "5553991664578", 123456);

        // System.out.println(m1.getCrm() == 123456);
        // System.out.println(m1.getTipo() == "MEDICO");

        // // Faltou um validador de CRM (preguiça de procurar regra)
        // // Logo, é passível de erros.

        // // Especialidades -> List<String> simples
        // // Melhorias: adicionaria uma List<Especializacoes>
        // // - Isso evitaria repetições e poderia ter uma lista fixa de
        // // especializações que seriam aderidas pelos médicos.
        // // - Como está atualmente -> permite qualquer insert de String entrar.
        // m1.adicionarEspecialidades("Psiquiatria");
        // m1.adicionarEspecialidades("Geriatria");
        // m1.adicionarEspecialidades("Ortopedia");

        // // Listagem de especialidades
        // System.out.println(m1.getEspecialidades());
        
        // // Remover pelo nome
        // m1.removerEspecialidade("Ortopedia");
        // System.out.println(m1.getEspecialidades());

        // // Remover pelo índice
        // m1.removerEspecialidade(1);
        // System.out.println(m1.getEspecialidades());

        // try {
        //     // Não consegue remover índice inexistente
        //     m1.removerEspecialidade(4);
        //     System.out.println(false);
        // } catch (Exception e) {
        //     System.out.println(true);
        // }

        // // Remoção de especialidade inválida, não altera lista.
        // m1.removerEspecialidade("Cardiologia");
        // m1.getEspecialidades();

        // // =========================================== CLASSE TECNICO ===========================================
        // System.out.println("------------");
        // System.out.println("TESTES DE CLASSE TÉCNICO");
        // Tecnico t1 = new Tecnico("Lucas", "12345678977", "lucas@gmail.com", "5553978455623", "123000");
        
        // System.out.println(t1.getCodTecnico() == "123000");
        // System.out.println(t1.getTipo() == "TÉCNICO");
        // System.out.println(t1.getCodigo() == 2);

        // // Melhoria: verificador de código de técnico (regra específica),
        // // pois atualmente aceita qualquer tipo de String.
        // t1.setCodTecnico("123001");
        // System.out.println(t1.getCodTecnico() == "123001");
        

        // // ======================================= INTEGRAÇÃO DE CLASSES ===========================================
        // System.out.println("------------");
        // System.out.println("TESTES DE FUNCIONAMENTO INTERCLASSES");
        // // 1 -
        // // Não é possível criar uma consulta sem paciente ou médico.
        // // Da mesma forma, não é possível criar uma consulta, sem o médico ter agenda aberta.
        // // Para o médico ter agenda aberta, ela deve estar registrada numa AgendaClinica().
        // try {
        //     Consulta c1 = new Consulta(2024, 01, 01, 10, m1, p2);        
        //     System.out.println(false);
        // } catch (Exception e) {
        //     System.out.println(true);
        // }

        // AgendaClinica agenda1 = new AgendaClinica();

        // // Médico pode abrir agenda independente da AgendaClinica()
        // // Disponibilidade pessoal do médico (abre espaço para outras AgendaClinica())
        // // Melhoria -> não há um validador de ano.
        // // Logo pode 0000, 00, 0.
        // m1.abrirAgenda(01, 01, 2025);
        // System.out.println(m1.listarAgenda());

        // // Fechar agenda aberta
        // m1.fecharAgenda(01, 01, 2025);
        // System.out.println(m1.listarAgenda());

        // // Fechar agenda inexistente = não faz diferença
        // m1.fecharAgenda(01, 02, 2025);
        // System.out.println(m1.listarAgenda());

        // // Marcar consulta -> ocupa slot
        // m1.abrirAgenda(01, 01, 2025);
        // m1.marcarConsulta(agenda1, p2, 01, 01, 2025, 12);
        // m1.marcarConsulta(agenda1, p2, 01, 01, 2025, 13);
        // m1.marcarConsulta(agenda1, p2, 01, 01, 2025, 14);
        // System.out.println(m1.listarAgenda());

        // // Médico marcar exame com médico -> ocupa slot
        // m1.marcarExame("Sangue", agenda1, p2, 01, 01, 2025, 15);
        // System.out.println(m1.listarAgenda());

        // // Médico encerra exame e gera um laudo.
        // m1.encerrarExame("Tudo limpo no sangue.", agenda1.getExame(m1, p2, 01, 01, 2025, 15));
        // System.out.println(agenda1.getExame(m1, p2, 01, 01, 2025, 15).getLaudo() == "Tudo limpo no sangue.");

        // LocalDateTime data = LocalDateTime.of(2025,01,01,14,0,0);
        // agenda1.getConsulta(data).encerrar("Dores", "Dorflex");
        // System.out.println(agenda1.getConsulta(data).getProntuario());

        // System.out.println("------ CONSULTAS E EXAMES ----");
        // System.out.println();
        // System.out.println(agenda1.listarConsultasExames());

        // System.out.println("------ CONSULTAS ----");
        // System.out.println();
        // System.out.println(agenda1.listarConsultas());

        // System.out.println("------ EXAMES ----");
        // System.out.println();
        // System.out.println(agenda1.listarExames());
    }
}
