public class Prontuario {

    private Consulta consulta;
    private String sintomas;
    private String medicamentos;

    Prontuario(Consulta c, String sintomas, String medicamentos) {
        this.consulta = c;
        this.sintomas = sintomas;
        this.medicamentos = medicamentos;
    }

    public Consulta getConsulta() { return consulta; }
    public String getSintomas() { return sintomas; }
    public String getMedicamentos() { return medicamentos; }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }
    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    @Override
    public String toString() {
        return "---PRONTUARIO DE CONSULTA---" + 
        "\n|- Paciente: " + this.getConsulta().getPaciente().getNome() +
        "\n|- Paciente: " + this.getConsulta().getPaciente().getTipoSanguineo() +
        "\n|- Médico: " + this.getConsulta().getMedico().getNome() +
        "\n|- Hora Consulta: " + this.getConsulta().getDataHora() +
        "\n|- Sintomas: " + this.getSintomas() +
        "\n|- Medicamentos: " + this.getMedicamentos();
    }
    
}
