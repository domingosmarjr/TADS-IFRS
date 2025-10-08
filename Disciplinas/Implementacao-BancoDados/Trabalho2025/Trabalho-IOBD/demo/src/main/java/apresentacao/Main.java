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
import persistencia.DAOEvento;

public class Main {
    public static void main(String[] args) {

        // LEVANTA UM SERVIDOR COM JAVALIN -> USANDO MUSTACHE COMO RENDERIZADOR DE HTML DINÂMICO
        var app = Javalin.create(config -> {
            config.fileRenderer(new JavalinMustache());
            config.staticFiles.add("/static", Location.CLASSPATH);
        }).start(7070);

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

    }
}