SISTEMA CLÍNIA OLIEZZI

Descrição:
Sistema de agendamento de consultas médicas, incluindo realização de exames e registro de 
prontuários que incluem as consultas e exames. Também que gere um histórico dos pacientes.

Atores:
- Médico
- Paciente
- Técnico

Classes:
- Pessoa(SuperClass)
    - Paciente
    - Funcionario (SuperClass)
        - Médico
        - Técnico
            darLaudoExame()
        - Secretaria
Agenda
Especialidade
Consulta
Prontuario
Exame
Laudo
Historico

- Secretaria
+nome
+cpf
+email
+telefone
    -agendarConsulta()

- Medico
+nome
+cpf
+email
+telefone
    +crm
    +Especialidades
    +Agenda
    -abrirAgenda(data) [9-17h com slots de 1h]
    -solicitarExame()

- Tecnico
+nome
+cpf
+email
+telefone
    +codigoProfissional

- Paciente
+nome
+cpf
+email
+telefone
    +dataNascimento
    +sexo
    +tipoSanguineo(enum)
    +HistoricoConsulta
    -solicitarExame()

- Consulta [implementa Agendavel]
    +dataHora [verificar se tá na agenda do médico]
        -> horário indisponível = exception AgendaNaoDisponivelException
    +tipo(enum)
    +Medico
    +Paciente
    +Prontuario
    -verificarAgendaMedico()
    -encerrarConsulta()
        -> gerar Prontuario (dados consulta + exame)

- Exame [implementa Agendavel]
    +nome
    +dataHora
    +Responsavel(Medico/Tecnico)
    +Laudo
    -encerrarExame()
        -> gerar um Laudo

- Prontuario
    +Paciente
    +Consulta
    +Exames<>
    +medicamentos

- Consulta [implementa Agendavel]
- Exame [implementa Agendavel]
- Laudo
- Especialidades
- Agenda
- Historico

INTERFACE
-Agendável


-----------------------------------------------
Estrutura de Classes - Atores:
                       +------------Pessoa---------+
                       |                           |
            +-----Funcionario------+           Paciente
            |          |           |
        Medico      Tecnico     Secretaria


Classes Internas:

Historico
|---Consulta
|   |---Prontuario
|   |   |---Exame
|   |   |   |---Laudo
Agenda
Especialidade<>
