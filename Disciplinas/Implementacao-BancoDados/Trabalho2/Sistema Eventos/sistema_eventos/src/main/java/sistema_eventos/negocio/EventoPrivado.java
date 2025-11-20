package sistema_eventos.negocio;


public class EventoPrivado extends Evento {

    private String senhaAcesso;
    private String listaConvidados;

    public String getSenhaAcesso() { return senhaAcesso; }
    public String getListaConvidados() { return listaConvidados; }

    public void setSenhaAcesso(String senhaAcesso) { this.senhaAcesso = senhaAcesso; }


    public void setListaConvidados(String listaConvidados) { this.listaConvidados = listaConvidados; }
}
