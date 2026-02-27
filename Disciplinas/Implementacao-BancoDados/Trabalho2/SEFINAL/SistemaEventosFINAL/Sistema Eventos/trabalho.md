
Requisitos:
> DCL
    - papeis definidos por roles no DCL
    - principais perfis: administrador, organizador evento, participante, visitante.

        /app_admin
            >gerencia todo sistema
            >cria usuários
            >cria eventos
            >delega permissões
            >acesso total a todas tabelas
            >criação, modificação e remoção de usuários
        
        /app_organizer
            >cria e administra seus próprios eventos
            >insere novos eventos e arquivos relacionados ao evento
            >atualiza e exclui seus proprios eventos
            >consulta eventos e arquivos do eventos

        /app_attendee
            >visualiza e participa de eventos
            >consulta eventos inscritos
            >se inscreve em eventos
            >le arquivos de eventos que participa
            >não pode excluir eventos

        /app_viewer
            >apenas leituras (select)
            >sem permissão para alterar nada

> Herança de Tabelas -> tabelas herdadas para representar especializações de eventos
> Colunas JSON

Funcionalidades:
> Cadastrar e Editar eventos
> Armazenar arquivos de eventos (banner evento, ppt palestra)
> Controle de permissão de acessos (quais atores?) -> roles

Herança de Tabelas:
> evento(pai) -> evento_privado
    >evento = atributos prinicipais de todos os eventos (arquivos, metadados e campo JSONB [detalhes])
    >evento_privado = adiciona senha_de_acesso, lista_convidados

Upload de Arquivos:
> algum lugar do sistema deve fazer upload de arquivo









<!-- EXEMPLO JSON -->
-- Atualizar detalhes de um evento com programação e links
UPDATE evento
SET detalhes = jsonb_build_object(
    'programacao', jsonb_build_array(
        jsonb_build_object('hora', '10:00', 'atividade', 'Palestra de Abertura'),
        jsonb_build_object('hora', '14:00', 'atividade', 'Workshop de PostgreSQL')
    ),
    'contatos', jsonb_build_object('email', 'contato@evento.com', 'telefone', '(11) 99999-9999'),
    'links', jsonb_build_array('https://evento.com/inscricao', 'https://evento.com/programacao')
)
WHERE id_evento = 1;

-- Consultar apenas os contatos do evento
SELECT detalhes->'contatos' AS contatos
FROM evento
WHERE id_evento = 1;