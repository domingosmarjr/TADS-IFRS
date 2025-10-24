import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Medico extends Funcionario{
    
    private int crm;
    private List<String> especialidades = new ArrayList();
    private AgendaMedico agendaBase = new AgendaMedico(this);
    
    public Medico (String nome, String cpf, String email, String telefone, int crm) {
        super.setNome(nome);
        super.setCpf(cpf);
        super.setEmail(email);
        super.setTelefone(telefone);
        this.crm = crm;
        this.proximoCodigo();
    }

    // Getters
    public int getCrm() {
        return crm;
    }
    public String getEspecialidades() {
        String texto = "";
        for (String especialidade : especialidades) texto += "| - " + especialidade + "\n";
        return texto;
    }
    public String getEspecialidades(int i) {
        return especialidades.get(i);
    }

    // Setters
    public void setCrm(int crm) {
        this.crm = crm;
    }
    public void adicionarEspecialidades(String n) {
        especialidades.add(n);
    }
    public void removerEspecialidade(String n) {
        for (int i = 0; i < especialidades.size(); i++) {
            if(especialidades.get(i).equals(n)) especialidades.remove(i);
        }
    }
    public void removerEspecialidade(int i) {
        if(i <= especialidades.size() && i >= 0) especialidades.remove(i);
        else throw new IllegalArgumentException("Índice inválido.");
    }

    public void abrirAgenda(int dia, int mes, int ano) {
        agendaBase.abrirAgenda(dia, mes, ano);
    }


    public void marcarConsulta(Consulta consulta, Calendar data){
        new Horario(data.get(Calendar.DAY_OF_MONTH), data.get(Calendar.MONTH), data.get(Calendar.YEAR), data.get(Calendar.HOUR_OF_DAY));
    }






    public String listarAgendaCompleta() {
        return agendaBase.listarAgendaCompleta();
    }
    public String listarAgendaConsultas() {
        return agendaBase.listarAgendaConsultas();
    }
    public String listarAgendaConsultas(int dia, int mes, int ano) {
        return agendaBase.listarAgendaConsultas(dia, mes, ano);
    }

    // TODO: solicitarExame()


    @Override
    public String toString() {
        return "-------Médico-------\n| Código: " + this.getCodigo() + "\n| Nome: " + this.getNome() + "\n| CRM: " + this.getCrm() + 
        "\n" + "| Especialidades: \n" + getEspecialidades() + "--------------------" + this.agendaBase;
    }
}
