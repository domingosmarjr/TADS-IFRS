Paciente p1 = new Paciente();
Medica m1 = new Medica();
Tecnico t1 = new Tecnico();
Secretaria s1 = new Secretaria();

m1.abrirAgenda(1,2); //abre dia 01/02
s1.agendarConsulta(p1, 10,1,2) //marca consulta do paciente1 para 10h do dia 01/02
// agendar consulta ->  new Consulta (verificar se bate com agenda do médico)

m1.encerrarConsulta(Consulta); //fim consulta -> new Prontuario do Paciente
m1.marcarExame(p1, /*dados exame*/); //marcarExame -> new Exame do Paciente
m1.encerrarExame(Exame); //encerrarExame -> new Laudo
t1.encerrarExame(Exame); //encerrarExame -> new Laudo

p1.getHistorico();


