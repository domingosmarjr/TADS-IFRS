package apresentacao;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinMustache;
import negocio.Evento;
import negocio.Palestrante;
import negocio.Participante;
import persistencia.DAOEvento;
import persistencia.DAOPalestra;
import persistencia.DAOPalestrante;
import persistencia.DAOParticipante;

public class Main {
    public static void main(String[] args) {

        // LEVANTA UM SERVIDOR COM JAVALIN -> USANDO MUSTACHE COMO RENDERIZADOR DE HTML DINÂMICO
        var app = Javalin.create(config -> {
            config.fileRenderer(new JavalinMustache());
            config.staticFiles.add("/static", Location.CLASSPATH);
        });

        // CRIA UMA ROTA
        app.get("/", ctx -> ctx.result("Hello World"));

        // CRIA ROTA E RENDERIZA ELA A PARTIR DE UM GET
        app.get("admin/eventos", ctx -> {
            // AQUI EMBAIXO É COMPORTAMETNO DA ROTA
            Map<String, Object> model = new HashMap<>();
            // x = String (conteúdo "oi");
            model.put("x","oi");

            // // vetEvento -> coleção de Objetos
            // List<Evento> vetEvento = new ArrayList();
            // vetEvento.add(new Evento());

            model.put("vetEvento", new DAOEvento().listarEventos());

            // AQUI EMBAIXO É A RENDERIZAÇÃO DA ROTA COM index.html
            ctx.render("admin/eventos/index.html", model);
        });

        app.get("/participante/{cpf}", ctx -> {
            String cpf = ctx.pathParam("cpf");
            if(cpf.isEmpty() || cpf == null) {
                ctx.status(400).result("CPF não fornecido.");
                return;
            }
            Map<String, Object> pessoa = new HashMap<>();

            // Busco participante no banco
            DAOParticipante daoParticipante = new DAOParticipante();
            DAOEvento daoEvento = new DAOEvento();
            // Defino o participante com base no CPF digitado na URL
            Participante participante = daoParticipante.buscarPorCPF(cpf);
            List<Evento> eventos = daoEvento.listarEventosInscritos(cpf);

            pessoa.put("participante", participante);
            pessoa.put("eventos", new DAOEvento().listarEventosInscritos(cpf));

            ctx.render("participante/perfil.html", pessoa);
        });

        app.get("/palestra/nova", ctx -> {
            Map<String, Object> model2 = new HashMap<>();

            DAOPalestrante daoPalestrante = new DAOPalestrante();
            model2.put("palestrantes", daoPalestrante.listarPalestrantes());

            DAOEvento daoEvento = new DAOEvento();
            model2.put("eventos", daoEvento.listarEventos());

            ctx.render("palestra/nova.html", model2);
        });

        app.post("/palestra", ctx -> {
            String titulo = ctx.formParam("titulo");
            int duracao = Integer.parseInt(ctx.formParam("duracao"));
            String dataHoraI = ctx.formParam("data_hora_inicio");
            int evento_id = Integer.parseInt(ctx.formParam("evento_id"));
            String[] palestrantes = ctx.formParams("palestrantes").toArray(new String[0]);

            if(palestrantes.length == 0) {
                ctx.status(400).result("Mínimo 1 palestrante.");
                return;
            }

            java.sql.Timestamp dataHoraInicio = java.sql.Timestamp.valueOf(dataHoraI.replace("T", " ") + ":00");

            // Cadastrar
            DAOPalestra daoPalestra = new DAOPalestra();
            daoPalestra.cadastrarPalestra(titulo, duracao, dataHoraInicio, evento_id, palestrantes);

            ctx.result("Palestra cadastrada com sucesso!");

        });

        app.get("/admin/palestrantes", ctx -> {
            DAOPalestrante daoPalestrante = new DAOPalestrante();
            List<Palestrante> palestrantes = daoPalestrante.quantidadePalestras();

            Map<String, Object> model3 = new HashMap<>();
            model3.put("palestrantes",palestrantes);

            ctx.render("admin/palestrantes/relatorio.html",model3);

        });

        app.get("/evento/disponiveis/{cpf}", ctx -> {
            String cpf = ctx.pathParam("cpf");
            DAOEvento daoEvento= new DAOEvento();
            List<Evento> eDisponiveis = daoEvento.listarEventosDisponiveis(cpf);

            Map<String, Object> model4 = new HashMap<>();
            model4.put("cpf",cpf);
            model4.put("eventos", eDisponiveis); 

            ctx.render("eventos/disponiveis.html", model4);
        });

        app.post("/inscricao", ctx -> {
            String cpf = ctx.formParam("cpf");
            int eventoId = Integer.parseInt(ctx.formParam("evento_id"));

            DAOParticipante daoParticipante = new DAOParticipante();
            DAOEvento daoEvento = new DAOEvento();

            boolean jaInscrito = daoEvento.verificarInscricao(cpf, eventoId);
            if(jaInscrito) {
                ctx.status(400).result("Já inscrito.");
                return;
            }

            daoEvento.cadastrarInscricao(cpf, eventoId);
            ctx.result("Inscricão realizada com sucesso!");
        });

        app.start(7070);


    }
}