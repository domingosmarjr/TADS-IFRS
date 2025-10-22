SISTEMA CLÍNICA OLIEZZI

Paciente vai até a clínica Oliezzi, solicita uma consulta. Médico realiza uma consulta, solicita exames e dá um
diagnóstico, gera um prognóstico. Tecnico dá laudo de exame. Prontuario, possui histórico de consultas e de exames.

- Classe Consulta implementa Agendavel
    +dataHora
    +tipo(enum)
    +codMedico
    +Paciente
    +Prontuario
- Classe Exame implementa Agendavel
    +nomeExame
    +dataHora
    +responsavel (medico ou tecnico)
    +Laudo
    -gerarLaudo()
- Classe Laudo
    +codExame
    +nomeExame
    +nomePaciene
    +texto
- Classe Diagnóstico
- Classe Prontuario (da consulta)
    +sintomas
    +exames
    +medicamentos
- Classe Histórico (conjunto de prontuários)

- Classe Pessoa
    - Classe Paciente
        +nome
        +cpf
        +email
        +telefone
        +dtNascimento
        +sexo
        +tipoSanguineo
        +Historico<Consultas>

    - Classe Funcionário
        - Classe Médico
            +nome
            +cpf
            +email
            +telefone
            +crm
            +especialidades
            +agendaDisponibilidades
            +agendaConsultas
            -abrirAgenda()
                abre horário de 9 até 17 com slots para consultas
        - Classe Técnico
            -darLaudoExame()

- Classe Agenda
- Interface Agendavel

Consulta implementa Agendavel
Exame implemente Agendavel


Agendamento
|-Consulta
|--Exame
|---Laudo
|--Prontuario
|---Historico

Agendar -> Cria consulta
Consulta -> pode ter exame -> gera laudo
            pode não ter exame
Consulta -> gera prontuario de consulta
Protunario de consulta é agregado ao histórico

Paciente
Médico
Técnico