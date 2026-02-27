import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Paciente extends Pessoa{

    private static int codigo;
    private LocalDate dataNascimento;
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private char sexo;
    private TipoSanguineo tipoSanguineo;
    private List<Consulta> historico = new ArrayList();

    public Paciente (String nome, String cpf, String email, String telefone, String dataNascimento, char sexo, TipoSanguineo tipoSanguineo) {
        super.setNome(nome);
        super.setCpf(cpf);
        super.setEmail(email);
        super.setTelefone(telefone);
        this.sexo = sexo;
        setDataNascimento(dataNascimento);
        this.tipoSanguineo = tipoSanguineo;
        codigo++;
    }

    // -------------------------------------------------
    // GETTERS
    public static int getCodigo() { return codigo; }
    public String getDataNascimento() { return this.dataNascimento.format(formatador); }
    public char getSexo() { return sexo; }
    public TipoSanguineo getTipoSanguineo() { return tipoSanguineo; }

    // -------------------------------------------------
    // SETTERS
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = converteData(dataNascimento);
    }
    public void setSexo(char sexo) {
        if(sexo == 'M' || sexo == 'F') this.sexo = sexo;
        else throw new IllegalArgumentException("Sexo inválido.");
    }
    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) throws IllegalArgumentException {
        try {
            this.tipoSanguineo = tipoSanguineo;
        } catch (Exception e) { 
            throw new IllegalArgumentException("Tipo inválido.");
        }
    }

    // -------------------------------------------------
    // OPERAÇÃO HISTORICO
    public void getHistorico() {
        for (Consulta consulta : historico) System.out.println(consulta);
    }
    public void adicionarHistorico(Consulta consulta) {
        historico.add(consulta);
    }

    // -------------------------------------------------
    // OPERAÇÃO MARCAR EXAME
    // Marcar exame com técnico (não dependendo de disponibilidade, só agenda)
    public void marcarExame(String nome, AgendaClinica agenda, Tecnico tecnico, int dia, int mes, int ano, int hora) {
        // Chamar direto o Tecnico.marcarExame() e delegar responsabilidade para o técnico:
        // tecnico.marcarExame(nome, agenda, this, dia, mes, ano, hora);
        agenda.getListaExames().add(new Exame(nome, tecnico, this, LocalDateTime.of(ano, mes, dia, hora, 0)));
    }

    // Marcar exame com médico (dependendo de agenda médica disponível)
    public void marcarExame(String nome, AgendaClinica agenda, Medico medico, int dia, int mes, int ano, int hora) {
        if(medico.dataEstaDisponivel(LocalDateTime.of(ano, mes, dia, hora, 0))){
            agenda.getListaExames().add(new Exame(nome, medico, this, LocalDateTime.of(ano, mes, dia, hora, 0)));
            medico.dataIndisponivel(LocalDateTime.of(ano, mes, dia, hora, 0));
        }
    }     
    // -------------------------------------------------
    // OPERAÇÃO MARCAR EXAME
    // Verifica se o médico tem data disponível.
    // Se tiver, ele cria consulta e adiciona na Agenda.
    // Depois de marcar, remove a data disponível do médico.
    public void marcarConsulta(AgendaClinica a, Medico m, int dia, int mes, int ano, int hora) throws Exception {
        if(m.dataEstaDisponivel(LocalDateTime.of(ano, mes, dia, hora, 0))) {
            // (0.1) - Agendamento -> new Consulta()
            Consulta consulta = new Consulta(ano, mes, dia, hora, m, this);
            a.getListaConsultas().add(consulta);
            m.dataIndisponivel(LocalDateTime.of(ano, mes, dia, hora, 0));
            this.adicionarHistorico(consulta);
        } else {
            throw new Exception("Agenda indisponível");
        }
    }

    // -------------------------------------------------    
    // UTIL
    // Função interna de converter String -> LocalDate
    private LocalDate converteData(String data) {
        LocalDate dataFinal = LocalDate.parse(data, formatador);
        return dataFinal;
    }

    // -------------------------------------------------
    // TOSTRING()
    @Override
    public String toString() {
        return "----Paciente----\n" + "| Código: " + this.getCodigo() + "\n| Nome: " + this.getNome() + "\n| Data Nascimento: " + this.getDataNascimento() +
        "\n| Tipo Sanguíneo: " + this.getTipoSanguineo() + "\n-----------";
    }
}