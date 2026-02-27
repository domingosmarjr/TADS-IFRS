package sistema_eventos.apresentacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;

import io.javalin.Javalin;
import io.javalin.http.UploadedFile;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinMustache;
import sistema_eventos.negocio.Evento;
import sistema_eventos.negocio.EventoPrivado;
import sistema_eventos.negocio.Organizador;
import sistema_eventos.negocio.Palestra;
import sistema_eventos.negocio.Palestrante;
import sistema_eventos.negocio.Participante;
import sistema_eventos.negocio.Usuario;
import sistema_eventos.persistencia.ConexaoPostgres;
import sistema_eventos.persistencia.DAOEvento;
import sistema_eventos.persistencia.DAOEventoPrivado;
import sistema_eventos.persistencia.DAOOrganizador;
import sistema_eventos.persistencia.DAOPalestra;
import sistema_eventos.persistencia.DAOPalestrante;
import sistema_eventos.persistencia.DAOParticipante;
import sistema_eventos.persistencia.DAOUsuario;

public class Main {
    public static void main(String[] args) throws SQLException {

        // LEVANTAR SERVIDOR JAVALIN -> MUSTACHE RENDERIZADOR DINÂMICO
        var app = Javalin.create(config -> {
            config.fileRenderer(new JavalinMustache());
            config.staticFiles.add("/static", Location.CLASSPATH);
        });

        // HOME
        app.get("/", ctx -> {
            ctx.render("landing/index.html");
        });


        // ----------- LOGIN --------------
        // TELA LOGIN - GET
        app.get("/login", ctx -> {
            ctx.render("login/login.html");
        });
        // TELA LOGIN - POST (ADMIN/ORG/PART)
        app.post("/login", ctx -> {
            String login = ctx.formParam("login");
            String senha = ctx.formParam("senha");
            if (login != null) login = login.trim();
            if (senha != null) senha = senha.trim();
            DAOUsuario usuarioDAO = new DAOUsuario();
            Usuario usuario = usuarioDAO.autenticar(login, senha);
            if (usuario != null) {
                System.out.println("Usuário encontrado: " + usuario.getLogin() + " " + usuario.getSenha() + ", Tipo: " + usuario.getTipo());
            } else {
                System.out.println("Login falhou para: " + login);
            }
            if (usuario == null) {
                ctx.html(
                    "<script>" +
                    "alert('Login ou senha incorretos.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }
            ctx.sessionAttribute("usuario",usuario);
            System.out.println("Sessão criada para: " + usuario.getLogin());
            switch (usuario.getTipo()) {
                case 1 -> ctx.redirect("/area/admin");
                case 2 -> ctx.redirect("/area/organizador");
                case 3 -> ctx.redirect("/participante/perfil");
                case 4 -> ctx.redirect("/palestrante/perfil");
                default -> ctx.redirect("/login");
            }
        });
        
        
        // ----------- CADASTRO ORGANIZADOR/PARTICIPANTE --------------
        // TELA DE CADASTRO - GET
        app.get("/cadastro", ctx -> {
            ctx.render("login/cadastro.html");
        });
        // TELA DE CADASTRO - POST
        app.post("/cadastro", ctx -> {
            String nome = ctx.formParam("nome");
            String data_nascimento = ctx.formParam("data_nascimento");
            String cpf = ctx.formParam("cpf");
            String email = ctx.formParam("email");
            String login = ctx.formParam("login").trim();
            String senha = ctx.formParam("senha").trim();
            int tipo = Integer.parseInt(ctx.formParam("tipo"));
            DAOUsuario usuarioDAO = new DAOUsuario();
            if (usuarioDAO.loginExiste(login)) {
                ctx.html(
                    "<script>" +
                    "alert('Usuário já cadastrado.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }
            int usuarioId = usuarioDAO.inserir(login, senha, tipo);
            switch (tipo) {
                case 2 -> {
                    // Organizador
                    Organizador novoOrganizador = new Organizador();
                    novoOrganizador.setNome(nome);
                    novoOrganizador.setCpf(cpf);
                    novoOrganizador.setEmail(email);
                    novoOrganizador.setUsuarioId(usuarioId);
                    DAOOrganizador orgDAO = new DAOOrganizador();
                    orgDAO.inserir(novoOrganizador);
                }
                case 3 -> {
                    // Participante
                    Participante novoParticipante = new Participante();
                    novoParticipante.setNome(nome);
                    novoParticipante.setCpf(cpf);
                    novoParticipante.setEmail(email);
                    novoParticipante.setUsuarioId(usuarioId);
                    DAOParticipante partDAO = new DAOParticipante();
                    partDAO.inserir(novoParticipante);
                }
                case 4 -> {
                    // Palestrante
                    Palestrante novoPalestrante = new Palestrante();
                    novoPalestrante.setNome(nome);
                    novoPalestrante.setCpf(cpf);
                    novoPalestrante.setEmail(email);
                    novoPalestrante.setUsuarioId(usuarioId);
                    DAOPalestrante palDAO = new DAOPalestrante();
                    palDAO.inserir(novoPalestrante);
                }
                default -> {
                }
            }
            Usuario usuario = usuarioDAO.autenticar(login, senha);
            ctx.sessionAttribute("usuario", usuario);
            switch (usuario.getTipo()) {
                case 1:
                    ctx.redirect("/area/admin");
                    break;
                case 2:
                    ctx.redirect("/area/organizador");
                    break;
                case 3:
                    ctx.redirect("/participante/perfil");
                    break;
                case 4:
                    ctx.redirect("/palestrante/perfil");
                    break;
                default:
                    ctx.redirect("/login");
            }
        });

        //------ ACESSOS DE LOGIN (ADMIN/ORGANIZADOR/PARTICIPANTE)
        // LOGIN ADM
        app.get("/area/admin", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }
            ctx.render("/area/admin/dash_admin.html");
        });
        // LOGIN ORG
        app.get("/area/organizador", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 2) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            DAOOrganizador daoOrganizador = new DAOOrganizador();
            Organizador organizador = daoOrganizador.buscarUsuarioId(usuarioLogado.getId());

            DAOEvento daoEvento = new DAOEvento();
            List<Evento> eventos = daoEvento.listarPorOrganizador(organizador.getId());

            Map<String, Object> model = new HashMap<>();
            model.put("organizador", organizador);
            model.put("eventos",eventos);
            model.put("nomeUsuario", organizador.getNome());
            ctx.render("/area/organizador/dash_org.html", model);
        });
        // LOGIN PART
        app.get("/area/participante", ctx -> {
            ctx.redirect("/participante/perfil");
        });
        // PÁGINA VIEWER DE EVENTO
        app.get("/landing/eventos", ctx -> {
            List<Evento> vetEvento = new DAOEvento().listarEventos();
            Map<String, Object> model = new HashMap<>();
            model.put("vetEvento", vetEvento);
            ctx.render("/landing/eventos/eventos.html", model);
        });

        // ------------- PARTICIPANTE -------------
        // PERFIL PARTICIPANTE
        app.get("/participante/perfil", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 3) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            int usuarioId = usuarioLogado.getId();

            DAOParticipante daoP = new DAOParticipante();
            DAOEvento daoE = new DAOEvento();

            Participante participante = daoP.buscarUsuarioId(usuarioId);

            List<Evento> eventosInscritos = daoE.listarInscritos(participante.getId());
            List<Evento> eventosNaoInscritos = daoE.listarNaoInscritos(participante.getId());
            for (Evento e : eventosInscritos) {
                if (e.getDetalhes() != null && e.getDetalhes().trim().startsWith("{")) {
                    String detalhes = e.getDetalhes();
                    int idx = detalhes.indexOf("\"descricao\"");
                    if (idx != -1) {
                        int start = detalhes.indexOf(":", idx) + 2;
                        int end = detalhes.indexOf("\"", start);
                        if (start != -1 && end != -1) {
                            e.setDetalhes(detalhes.substring(start, end));
                        }
                    }
                }
            }

            for (Evento e : eventosNaoInscritos) {
                if (e.getDetalhes() != null && e.getDetalhes().trim().startsWith("{")) {
                    String detalhes = e.getDetalhes();
                    int idx = detalhes.indexOf("\"descricao\"");
                    if (idx != -1) {
                        int start = detalhes.indexOf(":", idx) + 2;
                        int end = detalhes.indexOf("\"", start);
                        if (start != -1 && end != -1) {
                            e.setDetalhes(detalhes.substring(start, end));
                        }
                    }
                }
            }
            Map<String, Object> model = new HashMap<>();
            model.put("participante", participante);
            model.put("eventosInscritos", eventosInscritos);
            model.put("eventosNaoInscritos", eventosNaoInscritos);

            ctx.render("/area/participante/perfil.html", model);
        });
        // EDITAR PERFIL GET
        app.get("/participante/perfil/editar", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            DAOParticipante dao = new DAOParticipante();
            Participante participante = dao.buscarUsuarioId(usuarioLogado.getId());

            Map<String, Object> model = new HashMap<>();
            model.put("participante", participante);

            ctx.render("/area/participante/editar_perfil.html", model);
        });
        app.post("/participante/perfil/editar", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 3) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            DAOParticipante dao = new DAOParticipante();
            Participante participante = dao.buscarUsuarioId(usuarioLogado.getId());

            participante.setNome(ctx.formParam("nome"));

            String dataStr = ctx.formParam("data_nascimento");
            if (dataStr != null && !dataStr.isEmpty()) {
                try {
                    participante.setNascimento(java.sql.Date.valueOf(dataStr));
                } catch (Exception e) {
                    ctx.status(400).result("Data inválida.");
                    return;
                }
            }

            participante.setCpf(ctx.formParam("cpf"));
            participante.setEmail(ctx.formParam("email"));

            dao.atualizar(participante);

            ctx.redirect("/participante/perfil");
        });
        // EVENTO DO PARTICIPANTE AO CLICAR
        app.get("/participante/evento/{id}/inscrever", ctx -> {
            int eventoId = Integer.parseInt(ctx.pathParam("id"));
            DAOEvento daoEvento = new DAOEvento();

            Evento evento = daoEvento.buscarPorId(eventoId);
            boolean eventoPrivado = daoEvento.isEventoPrivado(eventoId);

            if (evento == null) {
                ctx.html("<script>alert('Evento não encontrado'); window.history.back();</script>");
                return;
            }

            Map<String, Object> model = new HashMap<>();
            model.put("evento", evento);
            model.put("eventoPrivado", eventoPrivado);

            ctx.render("participante/evento_inscrever.html", model);
        });
        app.post("/participante/evento/{id}/inscrever", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if (usuarioLogado == null || usuarioLogado.getTipo() != 3) {
                ctx.html("""
                    <script>
                        alert('Sessão encerrada. Logue novamente.');
                        window.location.href = '/login';
                    </script>
                """);
                return;
            }
            int eventoId = Integer.parseInt(ctx.pathParam("id"));
            DAOParticipante daoP = new DAOParticipante();
            DAOEvento daoEvento = new DAOEvento();

            Participante participante = daoP.buscarUsuarioId(usuarioLogado.getId());
            if (participante == null) {
                ctx.status(404).result("Participante não encontrado");
                return;
            }

            int eventoPaiId = eventoId;

            if (daoEvento.isEventoPrivado(eventoId)) {
                String senhaInformada = ctx.formParam("senha");

                if (!daoEvento.verificarSenhaEventoPrivado(eventoId, senhaInformada)) {
                    ctx.status(403).result("Senha incorreta para inscrição neste evento privado");
                    return;
                }

                eventoPaiId = daoEvento.buscarEventoPaiId(eventoId);

                if (eventoPaiId == -1) {
                    ctx.status(500).result("Erro: evento privado não possui evento pai cadastrado");
                    return;
                }
            }

            if (daoEvento.verificarInscricao(eventoPaiId, participante.getId())) {
                ctx.status(400).result("Você já está inscrito neste evento");
                return;
            }
            daoEvento.inscreverParticipante(eventoPaiId, participante.getId());
            ctx.redirect("/participante/perfil");
        });
        // DESINSCRIÇÃO DO PARTICIPANTE
        app.post("/participante/evento/{id}/desinscrever", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 3) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            int eventoId = Integer.parseInt(ctx.pathParam("id"));
            int participanteId = new DAOParticipante().buscarUsuarioId(usuarioLogado.getId()).getId();

            DAOEvento daoEvento = new DAOEvento();
            daoEvento.desinscreverParticipante(eventoId, participanteId);

            ctx.redirect("/participante/perfil");
        });
        // PÁGINA DO EVENTO PELO PARTICIPANTE
        app.get("/participante/evento/{id}", ctx -> {
            int eventoId = Integer.parseInt(ctx.pathParam("id"));

            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if (usuarioLogado == null || usuarioLogado.getTipo() != 3) {
                ctx.html("<script>alert('Acesso negado'); window.location.href='/login';</script>");
                return;
            }

            DAOParticipante daoP = new DAOParticipante();
            Participante participante = daoP.buscarUsuarioId(usuarioLogado.getId());
            if (participante == null) {
                ctx.html("<script>alert('Participante não encontrado'); window.location.href='/login';</script>");
                return;
            }

            // Busca o evento
            DAOEvento daoEvento = new DAOEvento();
            Evento evento = daoEvento.buscarPorId(eventoId); // método que retorna os dados do evento
            if (evento == null) {
                ctx.html("<script>alert('Evento não encontrado'); window.location.href='/participante/meus-eventos';</script>");
                return;
            }

            // Verifica se já está inscrito
            boolean inscrito = daoP.estaInscrito(participante.getId(), eventoId);

            // Prepara o modelo para o template
            Map<String, Object> model = new HashMap<>();
            model.put("evento", evento);
            model.put("participante", participante);
            model.put("inscrito", inscrito);

            ctx.render("/area/participante/evento.html", model); // arquivo HTML Mustache
        });

        // ------------- PALESTRANTE -------------
        app.get("/palestrante/perfil", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            DAOPalestrante daoPalestrante = new DAOPalestrante();
            Palestrante palestrante = daoPalestrante.listarTodos().stream().filter(p -> p.getUsuarioId() == usuarioLogado.getId()).findFirst().orElse(null);

            if (palestrante == null) {
                ctx.status(404).result("Palestrante não encontrado.");
                return;
            }
            DAOPalestra daoPalestra = new DAOPalestra();
            List<Palestra> palestras = daoPalestra.listarTodasPorPalestrante(palestrante.getId());
            for (Palestra p : palestras) {
                if (p.getData() != null) {
                    p.setDataHora(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(p.getData()));
                }
            }
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("palestrante", palestrante);
            modelo.put("palestras", palestras);
            ctx.render("/area/palestrante/perfil.html", modelo);
        });        
        
        // ------------- ORGANIZADOR ------------- 
        // Perfil Organizador
        app.get("/organizador/eventos", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 2) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }
            
            DAOOrganizador daoOrg = new DAOOrganizador();
            Organizador org = daoOrg.buscarUsuarioId(usuarioLogado.getId());

            DAOEvento daoEvento = new DAOEvento();
            List<Evento> eventos = daoEvento.listarPorOrganizador(org.getId());

            Map<String, Object> model = new HashMap<>();
            model.put("nomeUsuario", org.getNome());
            model.put("eventos", eventos);
            model.put("organizador", org);
            ctx.render("/area/organizador/dash_org.html", model);
        });
        // Cadastro de Evento
        app.get("/organizador/evento/cadastrar", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 2) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            DAOOrganizador daoOrg = new DAOOrganizador();
            Organizador org = daoOrg.buscarUsuarioId(usuarioLogado.getId());
            
            Map<String,Object> model = new HashMap<>();
            model.put("organizador", org);
            ctx.render("/area/organizador/cadastrar_evento.html", model);
        });
        app.post("/organizador/evento/cadastrar", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 2) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            DAOOrganizador daoOrg = new DAOOrganizador();
            Organizador org = daoOrg.buscarUsuarioId(usuarioLogado.getId());

            Evento novoEvento = new Evento();
            novoEvento.setNome(ctx.formParam("nome"));
            novoEvento.setLocal(ctx.formParam("local"));
            novoEvento.setData_inicio(java.sql.Date.valueOf(ctx.formParam("data_inicio")));
            novoEvento.setData_fim(java.sql.Date.valueOf(ctx.formParam("data_fim")));

            String detalhesTexto = ctx.formParam("detalhes");
            String detalhesJson = "{\"detalhes\": \"" + detalhesTexto.replace("\"", "\\\"") + "\"}";
            novoEvento.setDetalhes(detalhesJson);

            novoEvento.setOrganizadorId(org.getId());

            if (ctx.uploadedFile("banner") != null) {
                byte[] bannerBytes = ctx.uploadedFile("banner").content().readAllBytes();
                novoEvento.setImagemBanner(bannerBytes);
            }

            DAOEvento daoEvento = new DAOEvento();
            daoEvento.inserir(novoEvento);

            ctx.redirect("/organizador/eventos");
        });
        // Editar Evento
        app.get("/organizador/evento/{id}/editar", ctx -> {
            int eventoId = Integer.parseInt(ctx.pathParam("id"));
            DAOEvento daoEvento = new DAOEvento();
            Evento evento = daoEvento.buscarPorId(eventoId);
            ctx.render("/area/organizador/editar_evento.html", Map.of("evento", evento));
        });
        app.post("/organizador/evento/{id}/editar", ctx -> {
            int eventoId = Integer.parseInt(ctx.pathParam("id"));
            DAOEvento daoEvento = new DAOEvento();
            Evento evento = daoEvento.buscarPorId(eventoId);

            evento.setNome(ctx.formParam("nome"));
            evento.setLocal(ctx.formParam("local"));
            evento.setData_inicio(java.sql.Date.valueOf(ctx.formParam("data_inicio")));
            evento.setData_fim(java.sql.Date.valueOf(ctx.formParam("data_fim")));
            evento.setDetalhes(ctx.formParam("detalhes") != null ? ctx.formParam("detalhes") : "{}");

            if (ctx.uploadedFile("banner") != null && ctx.uploadedFile("banner").content().available() > 0) {
                byte[] bannerBytes = ctx.uploadedFile("banner").content().readAllBytes();
                evento.setImagemBanner(bannerBytes);
            }

            daoEvento.atualizar(evento);
            ctx.redirect("/organizador/eventos");
        });
        // Deletar Evento
        app.post("/organizador/evento/{id}/excluir", ctx -> {
            int eventoId = Integer.parseInt(ctx.pathParam("id"));
            DAOEvento daoEvento = new DAOEvento();
            daoEvento.deletar(eventoId);
            ctx.redirect("/organizador/eventos");
        });
        // Visualizar Evento
        app.get("/organizador/evento/{id}/visualizar", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 2) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            int eventoId = Integer.parseInt(ctx.pathParam("id"));
            DAOEvento daoEvento = new DAOEvento();
            Evento evento = daoEvento.buscarPorId(eventoId);

            if (evento == null) {
                ctx.status(404).result("Evento não encontrado");
                return;
            }

            String detalhesTexto = evento.getDetalhes();
            try {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, String> map = mapper.readValue(evento.getDetalhes(), Map.class);
                detalhesTexto = map.get("detalhes");
            } catch (Exception e) {
                detalhesTexto = evento.getDetalhes();
            }

            DAOPalestra daoPalestra = new DAOPalestra();
            List<Palestra> palestras = daoPalestra.listarPorEvento(eventoId);

            Map<Integer, List<Palestrante>> palestrantesPorPalestra = new HashMap<>();
            for (Palestra p : palestras) {
                List<Palestrante> palestrantes = daoPalestra.listarPalestrantesPorPalestra(p.getId());
                palestrantesPorPalestra.put(p.getId(), palestrantes);
            }

            Map<String, Object> model = new HashMap<>();
            model.put("evento", evento);
            model.put("palestras", palestras);
            model.put("palestrantesPorPalestra", palestrantesPorPalestra);
            model.put("detalhesTexto", detalhesTexto);

            if (evento.getImagemBanner() != null) {
                String base64Image = Base64.getEncoder().encodeToString(evento.getImagemBanner());
                model.put("imagemBannerBase64", base64Image);
            }

            ctx.render("/area/organizador/visualizar_evento.html", model);
        });
        // Cadastrar Palestra em Evento 
        app.get("/organizador/evento/{id}/palestra/cadastrar", ctx -> {
            int eventoId = Integer.parseInt(ctx.pathParam("id"));
            ctx.render("/area/organizador/cadastrar_palestra.html", Map.of("eventoId", eventoId));
        });
        app.post("/organizador/evento/{id}/palestra/cadastrar", ctx -> {
            int eventoId = Integer.parseInt(ctx.pathParam("id"));

            String titulo = ctx.formParam("titulo");
            int duracao = Integer.parseInt(ctx.formParam("duracao"));

            String nomePalestrante = ctx.formParam("novoPalestranteNome");
            String emailPalestrante = ctx.formParam("novoPalestranteEmail");
            String cpfPalestrante = ctx.formParam("novoPalestranteCpf");

            String dataHoraStr = ctx.formParam("data_hora");
            Timestamp dataHora = null;
            if (dataHoraStr != null && !dataHoraStr.isEmpty()) {
                LocalDateTime ldt = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
                dataHora = Timestamp.valueOf(ldt);
            }

            String login = nomePalestrante.toLowerCase().replace(" ", ".");
            String senha = Long.toHexString(Double.doubleToLongBits(Math.random()));

            DAOPalestrante daoPalestrante = new DAOPalestrante();
            DAOPalestra daoPalestra = new DAOPalestra();

            Palestrante novoPalestrante = new Palestrante();
            novoPalestrante.setNome(nomePalestrante);
            novoPalestrante.setEmail(emailPalestrante);
            novoPalestrante.setCpf(cpfPalestrante);
            novoPalestrante.setUsuario(login);
            novoPalestrante.setSenha(senha);

            int palestranteId = daoPalestrante.inserirRetornandoId(novoPalestrante);

            int palestraId = daoPalestra.inserirRetornandoId(eventoId, titulo, duracao, dataHora);

            daoPalestra.adicionarPalestrante(palestraId, palestranteId);

            ctx.redirect("/organizador/evento/" + eventoId + "/visualizar");
        });
        // Visualizar Palestras do Evento
        app.get("/organizador/evento/{eventoId}/palestras", ctx -> {
            int eventoId = Integer.parseInt(ctx.pathParam("eventoId"));

            DAOEvento daoEvento = new DAOEvento();
            Evento evento = daoEvento.buscarPorId(eventoId);

            DAOPalestra daoPalestra = new DAOPalestra();
            List<Palestra> palestras = daoPalestra.listarPorEvento(eventoId);

            for (Palestra p : palestras) {

                if (p.getData() != null) {
                    LocalDateTime ldt = p.getData().toLocalDateTime();
                    String dataFormatada = ldt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                    p.setDataHora(dataFormatada);
                }

                List<Palestrante> listaP = daoPalestra.listarPalestrantesPorPalestra(p.getId());
                p.setPalestrantes(listaP);
            }

            Map<String, Object> model = new HashMap<>();
            model.put("eventoNome", evento.getNome());
            model.put("eventoId", eventoId);
            model.put("palestras", palestras);

            ctx.render("/area/organizador/evento_palestras.html", model);
        });
        // Editar Palestra do Evento
        app.get("/organizador/palestra/{id}/editar", ctx -> {
            int palestraId = Integer.parseInt(ctx.pathParam("id"));

            DAOPalestra daoPalestra = new DAOPalestra();
            Palestra palestra = daoPalestra.buscarPorId(palestraId);

            if (palestra == null) {
                ctx.status(404).result("Palestra não encontrada");
                return;
            }

            String dataHoraStr = "";
            Timestamp ts = palestra.getData();
            if (ts != null) {
                LocalDateTime ldt = ts.toLocalDateTime();
                dataHoraStr = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            }

            DAOPalestrante daoPalestrante = new DAOPalestrante();
            List<Palestrante> palestrantesTodos = daoPalestrante.listarTodos();

            List<Palestrante> associados = daoPalestrante.listarPorPalestraId(palestraId);

            Set<Integer> idsAssociados = new HashSet<>();
            for (Palestrante p : associados) {
                idsAssociados.add(p.getId());
            }

            for (Palestrante p : palestrantesTodos) {
                p.setSelecionado(idsAssociados.contains(p.getId()));
            }

            Map<String, Object> model = new HashMap<>();
            model.put("palestra", palestra);
            model.put("dataHora", dataHoraStr);
            model.put("palestrantesTodos", palestrantesTodos);

            ctx.render("/area/organizador/editar_palestra.html", model);
        });
        app.post("/organizador/palestra/{id}/editar", ctx -> {
            int palestraId = Integer.parseInt(ctx.pathParam("id"));
            DAOPalestra daoPalestra = new DAOPalestra();
            Palestra palestra = daoPalestra.buscarPorId(palestraId);

            palestra.setTitulo(ctx.formParam("titulo"));
            palestra.setDuracao(Integer.parseInt(ctx.formParam("duracao")));

            if (palestra == null) {
                ctx.status(404).result("Palestra não encontrada");
                return;
            }
            String dataHoraStr = ctx.formParam("data_hora");
            if (dataHoraStr != null && !dataHoraStr.isEmpty()) {
                try {
                    java.time.LocalDateTime ldt = java.time.LocalDateTime.parse(dataHoraStr, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
                    Timestamp ts = Timestamp.valueOf(ldt);
                    palestra.setData(ts);
                } catch (Exception e) {
                    System.out.println("erro");
                }
            }

            daoPalestra.atualizar(palestra);

            String palestranteIdStr = ctx.formParam("palestrante_id");

            List<Palestrante> atuais = daoPalestra.listarPalestrantesPorPalestra(palestraId);
            for (Palestrante p : atuais) {
                daoPalestra.removerPalestrante(palestraId, p.getId());
            }

            if (palestranteIdStr != null && !palestranteIdStr.isBlank()) {
                int palestranteId = Integer.parseInt(palestranteIdStr);
                daoPalestra.adicionarPalestrante(palestraId, palestranteId);
            }

            ctx.redirect("/organizador/evento/" + palestra.getEventoId() + "/palestras");
        });
        // Deletar Palestra do Evento
        app.post("/organizador/palestra/{id}/deletar", ctx -> {
            int palestraId = Integer.parseInt(ctx.pathParam("id"));
            DAOPalestra daoPalestra = new DAOPalestra();

            int eventoId = daoPalestra.buscarPorId(palestraId).getEventoId();
            daoPalestra.deletar(palestraId); //todo: criar o métod

            ctx.redirect("/organizador/evento/" + eventoId + "/palestras");
        });
        // Cadastrar Palestrante em Evento
        app.get("/organizador/evento/{id}/palestrante/cadastrar", ctx -> {
            int eventoId = Integer.parseInt(ctx.pathParam("id"));
            ctx.render("/area/organizador/cadastrar_palestrante.html", Map.of("eventoId", eventoId));
        });
        app.post("/organizador/evento/{id}/palestrante/cadastrar", ctx -> {
            int eventoId = Integer.parseInt(ctx.pathParam("id"));
            int palestranteId = Integer.parseInt(ctx.formParam("palestranteId"));

            DAOPalestra daoPalestra = new DAOPalestra();
            daoPalestra.adicionarPalestrante(eventoId, palestranteId); // criar método adicionarPalestrant
            ctx.redirect("/organizador/evento/" + eventoId + "/editar");
        });
        // Remover Palestrante
        app.post("/organizador/palestrante/{id}/excluir", ctx -> {
            int palestraId = Integer.parseInt(ctx.pathParam("id"));
            int palestranteId = Integer.parseInt(ctx.formParam("palestranteId"));

            DAOPalestra daoPalestra = new DAOPalestra();
            daoPalestra.removerPalestrante(palestraId, palestranteId);
            ctx.render("/area/organizador/dash_org.html");
        });
        // Listar Participantes do Evento
        app.get("/organizador/evento/{id}/participantes", ctx -> {
            int eventoId = Integer.parseInt(ctx.pathParam("id"));

            DAOParticipante dao = new DAOParticipante();
            List<Participante> participantes = dao.listarPorEvento(eventoId);

            ctx.render("/area/organizador/listar_participantes_evento.html", Map.of(
                "eventoId", eventoId,
                "participantes", participantes
            ));
        });
        // Remover Participante do Evento
        app.post("/organizador/evento/{eventoId}/participante/{participanteId}/remover", ctx -> {
            int eventoId = Integer.parseInt(ctx.pathParam("eventoId"));
            int participanteId = Integer.parseInt(ctx.pathParam("participanteId"));

            // DAO para gerenciar inscrições
            DAOEvento daoEvento = new DAOEvento();
            daoEvento.desinscreverParticipante(eventoId, participanteId);

            ctx.redirect("/organizador/evento/" + eventoId + "/participantes");
        });

        // ------ADM
        // >Rota Cadastrar Evento
        app.get("/admin/evento/novo", ctx -> {
            List<Organizador> organizadores = new DAOOrganizador().listarTodos();
            Map<String, Object> model = new HashMap<>();
            model.put("organizadores", organizadores);
            ctx.render("area/admin/form_novo_evento.html", model);
        });
        // Rota Cdastrar Evento - POST
        app.post("/admin/evento/novo", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            // Campos do formulário
            String nome = ctx.formParam("nome");
            String local = ctx.formParam("local");
            String dataInicio = ctx.formParam("data_inicio");
            String horaInicio = ctx.formParam("hora_inicio");
            String dataFim = ctx.formParam("data_fim");
            String horaFim = ctx.formParam("hora_fim");
            String detalhesTexto = ctx.formParam("detalhes");
            String organizadorId = ctx.formParam("organizador_id");

            // Processa JSON do campo detalhes
            ObjectMapper mapper = new ObjectMapper();
            String detalhesJSON;
            if (detalhesTexto == null || detalhesTexto.isBlank()) {
                detalhesJSON = "{}"; 
            } else {
                try {
                    JsonNode node = mapper.readTree(detalhesTexto);
                    detalhesJSON = mapper.writeValueAsString(node);
                } catch (JsonProcessingException e) {
                    Map<String, String> obj = new HashMap<>();
                    obj.put("descricao", detalhesTexto);
                    detalhesJSON = mapper.writeValueAsString(obj);
                }
            }

            // Cria objeto Evento
            Evento e = new Evento();
            e.setNome(nome);
            e.setLocal(local);
            e.setData_inicio(java.sql.Date.valueOf(LocalDate.parse(dataInicio)));
            e.setHora_inicio(java.sql.Time.valueOf(LocalTime.parse(horaInicio)));
            e.setData_fim(java.sql.Date.valueOf(LocalDate.parse(dataFim)));
            e.setHora_fim(java.sql.Time.valueOf(LocalTime.parse(horaFim)));
            e.setDetalhes(detalhesJSON);
            e.setOrganizadorId(Integer.parseInt(organizadorId));

            // Processa banner (arquivo enviado)
            if (ctx.uploadedFile("banner") != null && ctx.uploadedFile("banner").content().available() > 0) {
                byte[] bannerBytes = ctx.uploadedFile("banner").content().readAllBytes();
                e.setImagemBanner(bannerBytes);
            }

            // Inserção no banco
            new DAOEvento().inserir(e);

            // Redirecionamento
            ctx.redirect("/area/admin");
        });
        // Evento Privado - GET
        app.get("/admin/evento-privado/novo", ctx -> {
            List<Organizador> organizadores = new DAOOrganizador().listarTodos();
            Map<String, Object> model = new HashMap<>();
            model.put("organizadores", organizadores);
            ctx.render("area/admin/form_novo_evento_privado.html", model);
        });
        // Evento Privado - POST
        app.post("/admin/evento-privado/novo", ctx -> {
            try {
                Usuario usuarioLogado = ctx.sessionAttribute("usuario");
                if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                    ctx.html(
                        "<script>" +
                        "alert('Sessão encerrada. Logue novamente.');" +
                        "window.location.href = '/login';" +
                        "</script>"
                    );
                    return;
                }

                String nome = ctx.formParam("nome");
                String local = ctx.formParam("local");
                String dataInicio = ctx.formParam("data_inicio");
                String dataFim = ctx.formParam("data_fim");
                String horaInicio = ctx.formParam("hora_inicio");
                String horaFim = ctx.formParam("hora_fim");
                String senhaAcesso = ctx.formParam("senha_acesso");
                String detalhesTexto = ctx.formParam("detalhes");
                String convidadosTexto = ctx.formParam("lista_convidados");
                int organizadorId = Integer.parseInt(ctx.formParam("organizador_id"));

                ObjectMapper mapper = new ObjectMapper();

                String detalhesJSON;
                if (detalhesTexto == null || detalhesTexto.isBlank()) {
                    detalhesJSON = "{}";
                } else {
                    try {
                        JsonNode node = mapper.readTree(detalhesTexto);
                        detalhesJSON = mapper.writeValueAsString(node);
                    } catch (JsonProcessingException ex) {
                        Map<String, String> obj = new HashMap<>();
                        obj.put("descricao", detalhesTexto);
                        detalhesJSON = mapper.writeValueAsString(obj);
                    }
                }

                String convidadosJSON;
                if (convidadosTexto == null || convidadosTexto.isBlank()) {
                    convidadosJSON = "[]";
                } else {
                    String[] lista = convidadosTexto.split(",");
                    List<String> convidados = new ArrayList<>();
                    for (String c : lista) {
                        convidados.add(c.trim());
                    }
                    convidadosJSON = mapper.writeValueAsString(convidados);
                }

                byte[] imagemBanner = null;
                UploadedFile arquivo = ctx.uploadedFile("imagem_banner");
                if (arquivo != null) {
                    imagemBanner = arquivo.content().readAllBytes();
                }

                EventoPrivado ep = new EventoPrivado();
                ep.setNome(nome);
                ep.setLocal(local);
                ep.setData_inicio(java.sql.Date.valueOf(LocalDate.parse(dataInicio)));
                ep.setData_fim(java.sql.Date.valueOf(LocalDate.parse(dataFim)));
                if (horaInicio != null && !horaInicio.isBlank()) ep.setHora_inicio(java.sql.Time.valueOf(horaInicio + ":00"));
                if (horaFim != null && !horaFim.isBlank()) ep.setHora_fim(java.sql.Time.valueOf(horaFim + ":00"));
                ep.setSenhaAcesso(senhaAcesso);
                ep.setOrganizadorId(organizadorId);
                ep.setDetalhes(detalhesJSON);
                ep.setListaConvidados(convidadosJSON);
                ep.setImagemBanner(imagemBanner);

                DAOEvento dao = new DAOEvento();
                int novoId = dao.inserirEventoPrivado(ep);

                ctx.redirect("/admin/evento-privado/" + novoId);

            } catch (Exception e) {
                ctx.html("<script>alert('Erro ao cadastrar evento privado: " + e.getMessage() + "'); window.location.href='/area/admin';</script>");
            }
        });
        // EXIBIR EVENTO PRIVADO
        app.get("/admin/evento-privado/{id}", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if (usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html("<script>alert('Sessão encerrada. Logue novamente.'); window.location.href='/login';</script>");
                return;
            }

            int idEvento = Integer.parseInt(ctx.pathParam("id"));
            DAOEventoPrivado dao = new DAOEventoPrivado();
            EventoPrivado ep = dao.buscarPorId(idEvento);
            if (ep == null) {
                ctx.html("<script>alert('Evento não encontrado.'); window.location.href='/area/admin';</script>");
                return;
            }

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> detalhesMap = new HashMap<>();
            if (ep.getDetalhes() != null && !ep.getDetalhes().isBlank()) {
                try {
                    detalhesMap = mapper.readValue(ep.getDetalhes(), Map.class);
                } catch (JsonProcessingException e) {
                    detalhesMap.put("descricao", ep.getDetalhes());
                }
            }

            List<String> listaConvidados = new ArrayList<>();
            if (ep.getListaConvidados() != null && !ep.getListaConvidados().isBlank()) {
                try {
                    listaConvidados = mapper.readValue(ep.getListaConvidados(), List.class);
                } catch (JsonProcessingException e) {
                    System.out.println("erro no json");
                }
            }

            String imagemBannerBase64 = null;
            if (ep.getImagemBanner() != null) {
                imagemBannerBase64 = Base64.getEncoder().encodeToString(ep.getImagemBanner());
            }

            Map<String, Object> model = new HashMap<>();
            model.put("evento", ep);
            model.put("detalhes", detalhesMap);
            model.put("listaConvidados", listaConvidados);
            model.put("imagemBannerBase64", imagemBannerBase64);

            ctx.render("area/admin/evento_privado_ver.html", model);
        });

        // Cadastrar Organizador (GET)
        app.get("/admin/organizador/novo", ctx -> {
            ctx.render("area/admin/form_novo_organizador.html");
        });
        // Cadastrar Organizador (POST)
        app.post("/admin/organizador/novo", ctx -> {
            try {
                String nome = ctx.formParam("nome");
                String email = ctx.formParam("email");
                String cpf = ctx.formParam("cpf");
                String dataNascimentoStr = ctx.formParam("data_nascimento");

                if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d{11}")) {
                    ctx.html("<script>alert('CPF inválido. Deve conter 11 dígitos numéricos.'); window.location.href='/area/admin';</script>");
                    return;
                }

                String login = ctx.formParam("usuario");
                String senha = ctx.formParam("senha");
                int tipo = 2;

                DAOUsuario daoUsuario = new DAOUsuario();
                int usuarioId = daoUsuario.inserir(login, senha, tipo);

                Organizador org = new Organizador();
                org.setNome(nome);
                org.setEmail(email);
                org.setCpf(cpf);
                org.setUsuarioId(usuarioId);

                if (dataNascimentoStr != null && !dataNascimentoStr.isBlank()) {
                    org.setNascimento(java.sql.Date.valueOf(LocalDate.parse(dataNascimentoStr)));
                }

                new DAOOrganizador().inserir(org);

                ctx.sessionAttribute("usuario_id", usuarioId);
                ctx.sessionAttribute("tipo", tipo);

                ctx.redirect("/area/admin");

            } catch (Exception e) {
                ctx.html("<script>alert('Erro ao cadastrar organizador.'); window.location.href = '/';</script>");
                return;
            }
        });
        // Cadastrar Participante (GET)
        app.get("/admin/participante/novo", ctx -> {
            ctx.render("area/admin/form_novo_participante.html");
        });
        // Cadastrar Participante (POST)
        app.post("/admin/participante/novo", ctx -> {
            try {
                String nome = ctx.formParam("nome");
                String data_nascimento = ctx.formParam("data_nascimento");
                String cpf = ctx.formParam("cpf");
                String email = ctx.formParam("email");
                String login = ctx.formParam("login");
                String senha = ctx.formParam("senha");
                DAOUsuario usuarioDAO = new DAOUsuario();
                int usuarioId = usuarioDAO.inserir(login, senha, 3);

                Participante novoParticipante = new Participante();
                novoParticipante.setNome(nome);
                novoParticipante.setCpf(cpf);
                novoParticipante.setEmail(email);
                novoParticipante.setUsuarioId(usuarioId);
                novoParticipante.setNascimento(java.sql.Date.valueOf(data_nascimento));
                DAOParticipante novoParticipanteBanco = new DAOParticipante();
                novoParticipanteBanco.inserir(novoParticipante);

                Usuario usuario = usuarioDAO.autenticar(login, senha);
                // ctx.sessionAttribute("usuario",usuario);
                ctx.redirect("/area/admin");
            } catch (Exception e) {
                ctx.html(
                "<script>" +
                    "alert('Erro');" +
                    "window.history.back();" +
                "</script>");
            }
                        
        });
        // Cadastrar Palestrante (GET)
        app.get("/admin/palestrante/novo", ctx -> {
            ctx.render("area/admin/form_novo_palestrante.html");
        });
        // Cadastrar Palestrante (POST)
        app.post("/admin/palestrante/novo", ctx -> {
            try {
                String nome = ctx.formParam("nome");
                String cpf = ctx.formParam("cpf");
                String email = ctx.formParam("email");
                String login = ctx.formParam("login");
                String senha = ctx.formParam("senha");

                // 4 = Palestrante
                int tipo = 4;

                DAOUsuario usuarioDAO = new DAOUsuario();
                int usuarioId = usuarioDAO.inserir(login, senha, tipo);

                Palestrante novoPalestrante = new Palestrante();
                novoPalestrante.setNome(nome);
                novoPalestrante.setCpf(cpf);
                novoPalestrante.setEmail(email);

                novoPalestrante.setUsuario(login);
                novoPalestrante.setUsuarioId(usuarioId);

                DAOPalestrante palestraDAO = new DAOPalestrante();
                palestraDAO.inserir(novoPalestrante);

                usuarioDAO.autenticar(login, senha);

                ctx.redirect("/area/admin");
            } catch (Exception e) {
                e.printStackTrace();
                ctx.html(
                    "<script>" +
                        "alert('Erro ao cadastrar palestrante: dados inválidos ou duplicados.');" +
                        "window.location.href = '/admin/palestrante/novo';" +
                    "</script>"
                );
            }
        });
        // Cadastrar Palestra (GET)
        app.get("/admin/palestra/novo", ctx -> {
            List<Evento> eventos = new DAOEvento().listarEventos();
            List<Palestrante> palestrantes = new DAOPalestrante().listarPalestrantes();
            Map<String, Object> model = new HashMap<>();
            model.put("eventos", eventos);
            model.put("palestrantes", palestrantes);
            ctx.render("area/admin/form_nova_palestra.html", model);
        });
        // Cadastrar Palestra (POST)
        app.post("/admin/palestra/novo", ctx -> {
            try {
                String titulo = ctx.formParam("titulo");
                int duracao = Integer.parseInt(ctx.formParam("duracao"));
                String data = ctx.formParam("data");  // yyyy-MM-dd
                String hora = ctx.formParam("hora");  // HH:mm
                int eventoId = Integer.parseInt(ctx.formParam("eventoId"));
                
                List<String> palestranteIds = ctx.formParams("palestranteId");

                String dataHoraStr = data + " " + hora + ":00";
                Timestamp dataHora = Timestamp.valueOf(dataHoraStr);

                Palestra nova = new Palestra();
                nova.setTitulo(titulo);
                nova.setDuracao(duracao);
                nova.setData(dataHora);
                nova.setEventoId(eventoId);

                DAOPalestra dao = new DAOPalestra();
                int palestraId = dao.inserirRetornandoId(eventoId, titulo, duracao, dataHora);

                for (String idString : palestranteIds) {
                    int palestranteId = Integer.parseInt(idString);
                    dao.adicionarPalestrante(palestraId, palestranteId);
                }
                ctx.redirect("/area/admin");
            } catch (Exception e) {
                e.printStackTrace();
                ctx.html("<script>alert('Erro ao cadastrar palestra.'); window.location.href='/admin/palestra/novo';</script>");
            }
        });

        // GERENCIAR EVENTOS
        app.get("/admin/eventos", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            List<Evento> eventos = new DAOEvento().listarEventos();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            List<Map<String, String>> eventosFormatados = new ArrayList<>();
            for (Evento e : eventos) {
                Map<String, String> ev = new HashMap<>();
                ev.put("id", String.valueOf(e.getId()));
                ev.put("nome", e.getNome());
                ev.put("local", e.getLocal());
                ev.put("dataInicio", e.getData_inicio().toLocalDate().format(formatter));
                ev.put("dataFim", e.getData_fim().toLocalDate().format(formatter));
                eventosFormatados.add(ev);
            }

            Map<String, Object> modelo = new HashMap<>();
            modelo.put("eventos", eventosFormatados);
            ctx.render("area/admin/lista_eventos.html", modelo);
        });
        // Editar Evento - GET
        app.get("/admin/eventos/editar/{id}", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            int eventoId = Integer.parseInt(ctx.pathParam("id"));
            DAOEvento daoEvento = new DAOEvento();
            Evento evento = daoEvento.buscarPorId(eventoId);

            String imagemBannerBase64 = null;
            if (evento.getImagemBanner() != null) {
                imagemBannerBase64 = Base64.getEncoder().encodeToString(evento.getImagemBanner());
            }

            Map<String, Object> model = new HashMap<>();
            model.put("evento", evento);
            model.put("imagemBannerBase64", imagemBannerBase64);

            ctx.render("/area/admin/editar_evento.html", model);
        });
        // Editar Evento - POST
        app.post("/admin/eventos/editar/{id}", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            int eventoId = Integer.parseInt(ctx.pathParam("id"));
            DAOEvento daoEvento = new DAOEvento();
            Evento evento = daoEvento.buscarPorId(eventoId);

            evento.setNome(ctx.formParam("nome"));
            evento.setLocal(ctx.formParam("local"));
            evento.setData_inicio(java.sql.Date.valueOf(ctx.formParam("data_inicio")));
            evento.setData_fim(java.sql.Date.valueOf(ctx.formParam("data_fim")));
            String detalhes = ctx.formParam("detalhes") != null ? ctx.formParam("detalhes") : "{}";
            evento.setDetalhes(detalhes);

            if (ctx.uploadedFile("banner") != null && ctx.uploadedFile("banner").content().available() > 0) {
                byte[] bannerBytes = ctx.uploadedFile("banner").content().readAllBytes();
                evento.setImagemBanner(bannerBytes);
            }

            daoEvento.atualizar(evento);
            ctx.redirect("/admin/eventos/ver/"+evento.getId());
        });
        // Deletar Evento 
        app.post("/admin/eventos/excluir/{id}", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            int eventoId = Integer.parseInt(ctx.pathParam("id"));

            DAOEvento daoEvento = new DAOEvento();
            daoEvento.deletar(eventoId);

            ctx.redirect("/admin/eventos");
        });
        // Ver Evento
        app.get("/admin/eventos/ver/{id}", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }
            int eventoId = Integer.parseInt(ctx.pathParam("id"));
            DAOEvento daoEvento = new DAOEvento();
            DAOPalestra daoPalestra = new DAOPalestra();
            Evento evento = daoEvento.buscarPorId(eventoId);
            if (evento == null) {
                ctx.status(404).result("Evento não encontrado");
                return;
            }
            boolean privado = daoEvento.isEventoPrivado(eventoId);
            List<Palestra> palestras = daoPalestra.listarPorEvento(eventoId);
            Map<Integer, List<Palestrante>> palestrantesPorPalestra = new HashMap<>();
            for (Palestra p : palestras) {
                List<Palestrante> palestrantes = daoPalestra.listarPalestrantesPorPalestra(p.getId());
                palestrantesPorPalestra.put(p.getId(), palestrantes);
            }
            String imagemBannerBase64 = null;
            if (evento.getImagemBanner() != null) {
                imagemBannerBase64 = Base64.getEncoder().encodeToString(evento.getImagemBanner());
            }
            Map<String, Object> detalhesMap = new HashMap<>();
            if (evento.getDetalhes() != null && !evento.getDetalhes().isBlank()) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    detalhesMap = mapper.readValue(evento.getDetalhes(), new TypeReference<Map<String,Object>>() {});
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
            Map<String, Object> model = new HashMap<>();
            model.put("evento", evento);
            model.put("privado", privado);
            model.put("palestras", palestras);
            model.put("palestrantesPorPalestra", palestrantesPorPalestra);
            model.put("imagemBannerBase64", imagemBannerBase64);
            model.put("detalhes", detalhesMap);
            model.put("eventoDetalhes", evento.getDetalhes());
            ctx.render("/area/admin/evento_ver.html", model);
        });

        // GERENCIAR ORGANIZADORES
        app.get("/admin/organizadores", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }
            DAOOrganizador dao = new DAOOrganizador();
            List<Organizador> lista = dao.listarTodosComDetalhes();
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("organizadores", lista);
            ctx.render("/area/admin/gerenciar_organizadores.html", modelo);
        });
        // EXCLUIR ORGANIZADOR
        app.post("/admin/organizador/excluir/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            new DAOOrganizador().deletar(id);
            ctx.redirect("/admin/organizadores");
        });
        // PERFIL DE ORGANIZADOR
        app.get("/admin/organizador/perfil/{id}", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }
            int id = Integer.parseInt(ctx.pathParam("id"));
            DAOOrganizador dao = new DAOOrganizador();
            Organizador organizador = dao.buscarPorIdComEventos(id);

            if(organizador == null) {
                ctx.status(404).result("Organizador não encontrado");
                return;
            }

            Map<String, Object> modelo = new HashMap<>();
            modelo.put("organizador", organizador);
            modelo.put("eventos", organizador.getEventos());
            ctx.render("/area/admin/perfil_organizador.html", modelo);
        });
        // PÁGINA EDITAR PERFIL ORGANIZADOR
        app.get("/admin/organizador/editar/{id}", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            int id = Integer.parseInt(ctx.pathParam("id"));
            DAOOrganizador daoOrg = new DAOOrganizador();
            Organizador organizador = daoOrg.buscarPorId(id);

            if(organizador == null) {
                ctx.status(404).result("Organizador não encontrado");
                return;
            }

            DAOUsuario daoUsuario = new DAOUsuario();
            Usuario usuario = daoUsuario.buscarPorId(organizador.getUsuarioId());

            Map<String, Object> modelo = new HashMap<>();
            modelo.put("organizador", organizador);
            modelo.put("usuarioLogin", usuario.getLogin());
            modelo.put("usuarioSenha", usuario.getSenha());

            ctx.render("/area/admin/editar_organizador.html", modelo);
        });
        app.post("/admin/organizador/editar/{id}", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            int id = Integer.parseInt(ctx.pathParam("id"));
            String nome = ctx.formParam("nome");
            String cpf = ctx.formParam("cpf");
            String email = ctx.formParam("email");
            String dataNascimentoStr = ctx.formParam("data_nascimento");
            String usuarioLogin = ctx.formParam("usuario");
            String senha = ctx.formParam("senha");

            DAOOrganizador daoOrg = new DAOOrganizador();
            DAOUsuario daoUsuario = new DAOUsuario();

            Organizador org = daoOrg.buscarPorId(id);
            if(org == null) {
                ctx.status(404).result("Organizador não encontrado");
                return;
            }

            org.setNome(nome);
            org.setCpf(cpf);
            org.setEmail(email);
            org.setNascimento(java.sql.Date.valueOf(dataNascimentoStr));
            daoOrg.atualizar(org);

            daoUsuario.atualizarUsuario(org.getUsuarioId(), usuarioLogin, senha);
            ctx.redirect("/admin/organizador/perfil/" + id);
        });

        // GERENCIAR PARTICIPANTES
        app.get("/admin/participantes", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }
            DAOParticipante dao = new DAOParticipante();
            List<Participante> lista = dao.listarTodosComDetalhes();
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("participantes", lista);
            ctx.render("/area/admin/gerenciar_participantes.html", modelo);
        });
        // EXCLUIR PARTICIPANTE
        app.post("/admin/participantes/excluir/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            DAOParticipante daoParticipante = new DAOParticipante();
            DAOUsuario daoUsuario = new DAOUsuario();

            Participante participante = daoParticipante.buscarPorId(id);
            
            if(participante != null) {
                int usuarioId = participante.getUsuarioId();
                daoParticipante.deletar(id);
                daoUsuario.deletar(usuarioId);
            }
            new DAOUsuario().deletar(id);
            ctx.redirect("/admin/participantes");
        });
        // PERFIL DE PARTICIPANTE
        app.get("/admin/participantes/perfil/{id}", ctx ->{
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            int id = Integer.parseInt(ctx.pathParam("id"));
            DAOParticipante dao = new DAOParticipante();
            Participante participante = dao.buscarPorIdComEventos(id);

            if(participante == null) {
                ctx.status(404).result("Participante não encontrado");
                return;
            }

            DAOUsuario daoUsuario = new DAOUsuario();
            Usuario usuario = daoUsuario.buscarPorId(participante.getUsuarioId());
            participante.setUsuario(usuario);

            Map<String, Object> modelo = new HashMap<>();
            modelo.put("usuario",usuario);
            modelo.put("participante", participante);
            modelo.put("eventos", participante.getEventos());
            ctx.render("/area/admin/perfil_participante.html", modelo);
        });
        // PÁGINA DE EDITAR PERFIL PARTICIPANTE
        app.get("/admin/participantes/editar/{id}", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            int id = Integer.parseInt(ctx.pathParam("id"));
            DAOParticipante daoParticipante = new DAOParticipante();
            Participante participante = daoParticipante.buscarPorId(id);

            if(participante == null) {
                ctx.status(404).result("Participante não encontrado");
                return;
            }

            DAOUsuario daoUsuario = new DAOUsuario();
            Usuario usuario = daoUsuario.buscarPorId(participante.getUsuarioId());

            Map<String, Object> modelo = new HashMap<>();
            modelo.put("participante", participante);
            modelo.put("usuarioLogin", usuario.getLogin());
            modelo.put("usuarioSenha", usuario.getSenha());

            ctx.render("/area/admin/editar_participante.html", modelo);
        });
        app.post("/admin/participantes/editar/{id}", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }
            
            int id = Integer.parseInt(ctx.pathParam("id"));
            String nome = ctx.formParam("nome");
            String dataNascimentoStr = ctx.formParam("data_nascimento");
            String cpf = ctx.formParam("cpf");
            String email = ctx.formParam("email");
            String usuarioLogin = ctx.formParam("usuario");
            String senha = ctx.formParam("senha");

            DAOParticipante daoParticipante = new DAOParticipante();
            DAOUsuario daoUsuario = new DAOUsuario();

            Participante participante = daoParticipante.buscarPorId(id);
            if(participante == null) {
                ctx.status(404).result("Participante não encontrado");
                return;
            }

            participante.setNome(nome);
            participante.setNascimento(java.sql.Date.valueOf(dataNascimentoStr));
            participante.setCpf(cpf);
            participante.setEmail(email);
            daoParticipante.atualizar(participante);

            daoUsuario.atualizarUsuario(participante.getUsuarioId(), usuarioLogin, senha);

            ctx.redirect("/admin/participantes/perfil/" + id);
        });
        
        // GERENCIAR PALESTRA
        app.get("/admin/palestras", ctx ->{
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }
            DAOPalestra daoPalestra = new DAOPalestra();
            List<Palestra> lista = daoPalestra.listarTodasComDetalhes();
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("palestras", lista);
            ctx.render("/area/admin/gerenciar_palestras.html", modelo);
        });
        // EXCLUIR PALESTRA
        app.post("/admin/palestras/excluir/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            DAOPalestra daoPalestra = new DAOPalestra();
            Palestra palestra = daoPalestra.buscarPorId(id);
            if(palestra != null) daoPalestra.deletar(id);
            ctx.redirect("/admin/palestras");
        });
        // PERFIL DE PALESTRA
        app.get("/admin/palestras/dados/{id}", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            int id = Integer.parseInt(ctx.pathParam("id"));

            DAOPalestra daoPalestra = new DAOPalestra();
            Palestra palestra = daoPalestra.buscarPorIdComDetalhes(id);

             if (palestra == null) {
                ctx.status(404).result("Palestra não encontrada");
                return;
            }
            if (palestra.getData() != null) {
                palestra.setDataHora(
                    palestra.getData().toLocalDateTime()
                        .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                );
            }
            DAOPalestrante daoPalestrante = new DAOPalestrante();
            List<Palestrante> palestrantes = daoPalestrante.listarPorPalestraId(id);

            DAOEvento daoEvento = new DAOEvento();
            Evento evento = daoEvento.buscarPorId(palestra.getEventoId());

            Map<String, Object> modelo = new HashMap<>();
            modelo.put("palestra", palestra);
            modelo.put("evento", evento);
            modelo.put("palestrantes", palestrantes);

            ctx.render("/area/admin/perfil_palestra.html", modelo);
        });
        // EDITAR PERFIL DE PALESTRA
        app.get("/admin/palestras/editar/{id}", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }
            int id = Integer.parseInt(ctx.pathParam("id"));
            
            DAOPalestra daoPalestra = new DAOPalestra();
            Palestra palestra = daoPalestra.buscarPorIdComDetalhes(id);

            if(palestra == null) {
                ctx.status(404).result("Palestra não encontrada.");
                return;
            }
            if (palestra.getData() != null) {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                palestra.setDataHoraInput(sdf.format(palestra.getData()));
            }

            DAOEvento daoEvento = new DAOEvento();
            List<Evento> eventos = daoEvento.listarEventos();
            for (Evento e : eventos) {
                e.setSelecionado(e.getId() == palestra.getEventoId());
            }

            DAOPalestrante daoPalestrante = new DAOPalestrante();
            List<Palestrante> palestrantesSelecionados = daoPalestrante.listarPorPalestraId(id);
            List<Palestrante> palestrantesTodos = daoPalestrante.listarTodos();

            for (Palestrante p : palestrantesTodos) {
                p.setSelecionado(palestrantesSelecionados.stream().anyMatch(ps -> ps.getId() == p.getId()));
            }

            Map<String, Object> modelo = new HashMap<>();
            modelo.put("palestra", palestra);
            modelo.put("eventos", eventos);
            modelo.put("palestrantesSelecionados", palestrantesSelecionados);
            modelo.put("palestrantesTodos", palestrantesTodos);

            ctx.render("/area/admin/editar_palestra.html", modelo);
        });
        app.post("/admin/palestras/editar/{id}", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            int id = Integer.parseInt(ctx.pathParam("id"));
            DAOPalestra daoPalestra = new DAOPalestra();
            Palestra palestra = daoPalestra.buscarPorIdComDetalhes(id);

            if (palestra == null) {
                ctx.status(404).result("Palestra não encontrada.");
                return;
            }

            String titulo = ctx.formParam("titulo");
            int duracao = Integer.parseInt(ctx.formParam("duracao"));
            String dataHoraStr = ctx.formParam("data_hora_inicio"); 
            int eventoId = Integer.parseInt(ctx.formParam("evento_id"));

            java.sql.Timestamp dataHora = null;
            if (dataHoraStr != null && !dataHoraStr.isEmpty()) {
                java.time.LocalDateTime ldt = java.time.LocalDateTime.parse(dataHoraStr);
                dataHora = java.sql.Timestamp.valueOf(ldt);
            }

            DAOEvento daoEvento = new DAOEvento();
            if (daoEvento.buscarPorId(eventoId) == null) {
                ctx.status(400).result("Evento selecionado não existe.");
                return;
            }

            palestra.setTitulo(titulo);
            palestra.setDuracao(duracao);
            palestra.setData(dataHora);
            palestra.setEventoId(eventoId);

            String[] idsPalestrantes = ctx.formParams("palestrantes").toArray(new String[0]);

            try (Connection conn = new ConexaoPostgres("postgres","postgres").getConnection()) {
                conn.setAutoCommit(false);

                String sqlUpdate = "UPDATE palestra SET titulo = ?, duracao = ?, data_hora_inicio = ?, evento_id = ? WHERE id = ?";
                try (PreparedStatement ps = conn.prepareStatement(sqlUpdate)) {
                    ps.setString(1, palestra.getTitulo());
                    ps.setInt(2, palestra.getDuracao());
                    ps.setTimestamp(3, palestra.getData());
                    ps.setInt(4, palestra.getEventoId());
                    ps.setInt(5, palestra.getId());
                    ps.executeUpdate();
                }

                String sqlDeletePP = "DELETE FROM palestra_palestrante WHERE palestra_id = ?";
                try (PreparedStatement ps = conn.prepareStatement(sqlDeletePP)) {
                    ps.setInt(1, palestra.getId());
                    ps.executeUpdate();
                }

                String sqlInsertPP = "INSERT INTO palestra_palestrante (palestra_id, palestrante_id) VALUES (?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sqlInsertPP)) {
                    for (String pid : idsPalestrantes) {
                        int palestranteId = Integer.parseInt(pid);
                        ps.setInt(1, palestra.getId());
                        ps.setInt(2, palestranteId);
                        ps.addBatch();
                    }
                    ps.executeBatch();
                }

                conn.commit();
                ctx.redirect("/admin/palestras/dados/" + palestra.getId());

            } catch (Exception e) {
                e.printStackTrace();
                ctx.status(500).result("Erro ao atualizar palestra: " + e.getMessage());
            }
        });

        // GERENCIAR PALESTRANTES
        app.get("/admin/palestrantes", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }
            DAOPalestrante daoPalestrante = new DAOPalestrante();
            List<Palestrante> lista = daoPalestrante.listarTodosComDetalhes();
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("palestrantes", lista);
            ctx.render("/area/admin/gerenciar_palestrantes.html", modelo);
        });
        // EXCLUIR PALESTRANTE
        app.post("/admin/palestrantes/excluir/{id}", ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        DAOPalestrante daoPalestrante = new DAOPalestrante();

        try {
            List<Palestra> palestras = daoPalestrante.listarPalestrasPorPalestrante(id);

            if (palestras.size() > 1) {
                ctx.status(400).result("Não é possível excluir o palestrante: está vinculado a mais de uma palestra.");
                return;
            }

            daoPalestrante.excluir(id);

            ctx.redirect("/admin/palestrantes");

        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Erro ao excluir palestrante: " + e.getMessage());
        }
    });
        // PERFIL PALESTRANTE
        app.get("/admin/palestrantes/dados/{id}", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }
            int id = Integer.parseInt(ctx.pathParam("id"));

            DAOPalestrante daoPalestrante = new DAOPalestrante();
            Palestrante palestrante = daoPalestrante.buscarPorId(id);

            if (palestrante == null) {
                ctx.status(404).result("Palestrante não encontrado");
                return;
            }

            DAOPalestra daoPalestra = new DAOPalestra();
            List<Palestra> palestras = daoPalestra.listarTodasPorPalestrante(id);

            for (Palestra p : palestras) {
                if (p.getData() != null) {
                    p.setDataHora(
                        p.getData().toLocalDateTime()
                            .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                    );
                }
            }

            Map<String, Object> modelo = new HashMap<>();
            modelo.put("palestrante", palestrante);
            modelo.put("palestras", palestras);

            ctx.render("/area/admin/perfil_palestrante.html", modelo);
        });
        // EDITAR PERFIL DE PALESTRANTE
        app.get("/admin/palestrantes/editar/{id}", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }
            int id = Integer.parseInt(ctx.pathParam("id"));
            DAOPalestrante dao = new DAOPalestrante();
            Palestrante palestrante = dao.listarTodos().stream().filter(p -> p.getId() == id).findFirst().orElse(null);

            if (palestrante == null) {
                ctx.status(404).result("Palestrante não encontrado.");
                return;
            }

            Map<String, Object> modelo = new HashMap<>();
            modelo.put("palestrante", palestrante);

            ctx.render("/area/admin/editar_palestrante.html", modelo);
        });    
        app.post("/admin/palestrantes/editar/{id}", ctx -> {
            Usuario usuarioLogado = ctx.sessionAttribute("usuario");
            if(usuarioLogado == null || usuarioLogado.getTipo() != 1) {
                ctx.html(
                    "<script>" +
                    "alert('Sessão encerrada. Logue novamente.');" +
                    "window.location.href = '/login';" +
                    "</script>"
                );
                return;
            }

            int id = Integer.parseInt(ctx.pathParam("id"));
            DAOPalestrante daoPalestrante = new DAOPalestrante();
            DAOUsuario daoUsuario = new DAOUsuario();

            Palestrante palestrante = daoPalestrante.listarTodos().stream().filter(p -> p.getId() == id).findFirst().orElse(null);

            if (palestrante == null) {
                ctx.status(404).result("Palestrante não encontrado.");
                return;
            }

            String nome = ctx.formParam("nome");
            String usuario = ctx.formParam("usuario");
            String email = ctx.formParam("email");
            String cpf = ctx.formParam("cpf");
            String biografia = ctx.formParam("biografia");
            String senha = ctx.formParam("senha");

            palestrante.setNome(nome);
            palestrante.setUsuario(usuario);
            palestrante.setEmail(email);
            palestrante.setCpf(cpf);
            palestrante.setBiografia(biografia);


            try {
                daoPalestrante.atualizarPalestranteComUsuario(palestrante, senha, daoUsuario);
                daoUsuario.atualizarUsuario(palestrante.getUsuarioId(), usuario, senha);
                ctx.redirect("/admin/palestrantes/dados/" + palestrante.getId());
            } catch (Exception e) {
                e.printStackTrace();
                ctx.status(500).result("Erro ao atualizar palestrante: " + e.getMessage());
            }
        });
        
        // --- INICIA SERVIDOR HTTP
        app.start(7070);
    }
}