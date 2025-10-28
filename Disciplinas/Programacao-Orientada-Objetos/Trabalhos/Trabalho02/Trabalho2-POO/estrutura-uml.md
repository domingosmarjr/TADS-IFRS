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


        +----------IAgendavel---------+
        |                             |
    Consulta                        Exame
        |
        |
        <>
    Prontuario

---------------------------

Pessoa ()
    -String nome;
    -String cpf;
    -String email;
    -String telefone;
    +getNome() : String;
    +getCpf() : String;
    +getEmail() : String;
    +getTelefone() : String;
    +setNome(String n) : void;
    +setCpf(String c) : void;
    +setEmail(String e) : void;
    +setTelefone(String t) : void;

    Funcionario ()
        -int codFunc;
        +getCodigo() : int;
        #proximoCodigo() : void;

        Medico()
            -int crm
            -List<String> especialidades;
            -AgendaMedico agenda = new AgendaMedico(this);
            +getCrm : int;
            +getEspecialidades() : String;
            +getEspecialidades(int i) : String;
            +setCrm(int c) : void;
            +adicionarEspecialidade(String n) : void;
            +removerEspecialidade(String n) : void;
            +removerEspecialidade(int i) : void;
            +abrirAgenda(int dia, int mes, int ano) : void;
            +marcarConsulta(Paciente p, int dia, int mes, int ano) : void [new Consulta];
            +marcarExame(Paciente p, int dia, int mes, int ano) : void;
        
        Tecnico()
            -int codTecnico
            +darLaudo(Exame) : void;

        Secretaria()
            +marcarConsulta(Paciente p, Medico m, int dia, int mes, int ano) : void [new Consulta];

    Paciente()
             -Calendar dataNascimento
             -char sexo
             -TipoSanguineo tipoSanguineo
             -List<Consulta> historico = new ArrayList()
             +getCodigo() : int;
             +getSexo() : char;
             +getTipoSanguineo() : tipoSanguineo;
             +setDataNascimento(Calendar d) : void;
             +setSexo(char s) : void;

             +historico() : Consulta;
             +marcarExame(this, int dia, int mes, int ano) : void;
             
                
Agenda()
    AgendaMedico()
    -Medico medico;
    -List<DataConsulta> datas = new ArrayList();

        DataConsulta()
            -Calendar dataHora;
            -Consulta consulta;
            -boolean disponivel;

    AgendaExame()
    -Medico/Tecnico;
    -List<Data> datas = new ArrayList();
        
        DataExame()
            -Calendar dataHora;
            -Exame exame;
            -boolean disponivel;
    

    <!-- IDEAL SERIA FAZER UMA AGENDA DE EXAMES E CONSULTAS, QUE FOSSE
    GENERALISTA DO SISTEMA. O MÉDICO SÓ PODE FAZER EXAME NO PACIENTE 
    MARCADO PARA A HORA DA CONSULTA. -->
    AgendaCompleta()
        -List<Data> datas = new ArrayList();
        
            Data()
                -Calendar dataHora;
                -Consulta = null;
                -Exame = null;
                -Medico/Tecnico;



Consulta() implements Agendavel
    -Paciente paciente
    -Medico medico
    -Calendar data
    -Prontuario prontuario
    -boolean encerrado = false;
    +encerrarConsulta() : void [new Prontuario];

Prontuario()
    -String sintomas
    -String examesSolicitados
    -String medicamentos
    -Consulta consulta;
    -List<Exame> exames = new ArrayList();
 
Exame() implements Agendavel
    -static List<Exame> listaDeExame = new ArrayList();
    -String nome;
    -Calendar dataHora;
    -(Medico/Tecnico) responsavel;
    -String laudo = "";
    -boolean ecerrado = false;
    +encerrarExame(String l) : void [Laudo = l][encerrado = true];

