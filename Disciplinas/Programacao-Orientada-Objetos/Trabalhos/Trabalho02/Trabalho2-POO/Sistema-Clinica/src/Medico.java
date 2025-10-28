import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Medico extends Funcionario{
    
    private int crm;
    private List<String> especialidades = new ArrayList();
    private List<LocalDateTime> agendaMedico = new ArrayList();
    private final String tipo = "MEDICO";
 
    
    public Medico (String nome, String cpf, String email, String telefone, int crm) {
        super.setNome(nome);
        super.setCpf(cpf);
        super.setEmail(email);
        super.setTelefone(telefone);
        this.setCrm(crm);
        super.proximoCodigo();
    }

    // GETTERS
    public int getCrm() { return crm; }
    public String getEspecialidades(int i) { return especialidades.get(i); }
    public String getEspecialidades() {
        String texto = "|ESPECIALIDADES DE " + this.getNome() + ":\n";
        for (String especialidade : especialidades) texto += "| - " + especialidade + "\n";
        return texto;
    }
    public String getTipo() { return tipo; }

    // SETTERS
    public void setCrm(int crm) {
        // TODO Futuro: validar CRM
        this.crm = crm;
    }

    // ---------------------------------------------------------------------------------------
    // -----------OPERAÇÕES DE ESPECIALIDADE--------------
    // ADICIONAR ESPECIALIDADE
    public void adicionarEspecialidades(String n) {
        especialidades.add(n);
    }
    // REMOVER ESPECIALIDADE - NOME
    public void removerEspecialidade(String n) {
        for (int i = 0; i < especialidades.size(); i++) {
            if(especialidades.get(i).equals(n)) especialidades.remove(i);
        }
    }
    // REMOVER ESPECIALIDADE - ÍNDICE
    public void removerEspecialidade(int i) {
        if(i <= especialidades.size() && i >= 0) especialidades.remove(i);
        else throw new IllegalArgumentException("Índice inválido.");
    }

    // --------------OPERAÇÕES DE AGENDA----------------
    // ABRIR AGENDA - DATA [9h~17h]
    public void abrirAgenda(int dia, int mes, int ano) {
        for (int i = 9; i <= 17; i++) {
            agendaMedico.add(LocalDateTime.of(ano, mes, dia, i, 0));
        }
    }
    // FECHAR AGENDA [DATA ESPECÍFICA]
    public void fecharAgenda(int dia, int mes, int ano) {
        for (int i = agendaMedico.size() -1; i >= 0; i--) {
            if((agendaMedico.get(i).getDayOfMonth() == dia) && (agendaMedico.get(i).getMonthValue() == mes) && (agendaMedico.get(i).getYear() == ano)) {
                agendaMedico.remove(i);
            } 
        }
    }
    // LISTAR AGENDA DISPONIVEL - NÃO LISTA RESERVADOS
    public String listarAgenda() {
        String r = "---DATAS DISPONIVEIS---\n";
        for (LocalDateTime data : agendaMedico) {
            r += String.format("%02d/%02d/%04d - %02dh\n", data.getDayOfMonth(), data.getMonthValue(), data.getYear(), data.getHour());
        }
        return r;
    }
    // VERIFICA SE A DATA X ESTÁ DISPONÍVEL NA AGENDA
    // Melhoria: adaptar o parâmetro para String e converter para LocalDateTime
    public boolean dataEstaDisponivel(LocalDateTime data) {
        boolean ver = false;
        if (agendaMedico.contains(data)) ver = true;
        return ver;
    }
    public void dataIndisponivel(LocalDateTime data) {
        this.agendaMedico.remove(data);
    }

    // -----------OPERAÇÕES DE CONSULTA---------------
    // Médico marca consulta, mas ocupa slot de sua agenda disponível.
    public void marcarConsulta(AgendaClinica agenda, Paciente paciente, int dia, int mes, int ano, int hora) {
        if(dataEstaDisponivel(LocalDateTime.of(ano, mes, dia, hora,0))) {
            Consulta consulta = new Consulta(ano, mes, dia, hora, this, paciente); 
            agenda.getListaConsultas().add(consulta);
            this.dataIndisponivel(LocalDateTime.of(ano, mes, dia, hora, 0));
            paciente.adicionarHistorico(consulta);
        } else {
            throw new IllegalArgumentException("Agenda indisponível.");
        }
    }
    
    // -----------OPERAÇÕES DE EXAME--------------------
    // Médico marca exame, mas ocupa slot de sua agenda disponível.
    public void marcarExame(String nome, AgendaClinica agenda, Paciente paciente, int dia, int mes, int ano, int hora) {
        if(dataEstaDisponivel(LocalDateTime.of(ano, mes, dia, hora, 0))){
            agenda.getListaExames().add(new Exame(nome, this, paciente, LocalDateTime.of(ano, mes, dia, hora, 0)));
            this.dataIndisponivel(LocalDateTime.of(ano, mes, dia, hora, 0));
        }
    }
    public void encerrarExame(String laudo, Exame exame) {
        exame.encerrarExame(laudo);
        
    }

    // -------------------------------------------------    
    // TOSTRING
    @Override
    public String toString() {
        return "-------Médico-------\n| Código: " + this.getCodigo() + "\n| Nome: " + this.getNome() + "\n| CRM: " + this.getCrm() + 
        "\n" + "| Especialidades: \n" + getEspecialidades() + "--------------------";
    }

    
}
