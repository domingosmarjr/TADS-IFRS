import java.time.LocalDateTime;

public class Tecnico extends Funcionario {
    private String codTecnico;
    private final String tipo = "TÉCNICO";

    public Tecnico (String nome, String cpf, String email, String telefone, String codTec) {
        super.setNome(nome);
        super.setCpf(cpf);
        super.setEmail(email);
        super.setTelefone(telefone);
        this.setCodTecnico(codTec);;
        super.proximoCodigo();
    }

    // -------------------------------------------------
    // GETTERS
    public String getCodTecnico() { return codTecnico; }
    public String getTipo() { return tipo; }

    // -------------------------------------------------
    // SETTERS
    public void setCodTecnico(String codTecnico) {
        // TODO Futuro: adicionar verificação para código técnico (CRM do técnico)
        this.codTecnico = codTecnico;
    }

    // -------------------------------------------------    
    // OPERAÇÕES DE EXAME
    // Técnico marcar exame na lista de exames, sem depender de horário do médico.
    public void marcarExame(String nome, AgendaClinica agenda, Paciente paciente, int dia, int mes, int ano, int hora) {
        agenda.getListaExames().add(new Exame(nome, this, paciente, LocalDateTime.of(ano, mes, dia, hora, 0)));
    }

    public void darLaudo(Exame exame, String laudo) {
        // exame.gerarLaudo(laudo);
    }

    @Override
    public String toString() {
        return "-----Tecnico-----\n" + "|-Nome: " + this.getNome() + "\n|-Codigo: " + this.getCodTecnico() + "\n|----------";
    }
}
