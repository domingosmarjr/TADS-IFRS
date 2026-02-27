Tipo ideal para armazenar arquivos no PostgreSQL

- OID -> para arquivos gigantecos (+5gb)
- Bytea -> arquivos até 5GB

ALTER TABLE participante ADD COLUMN foto bytea;
private byte[] foto;



-----
Site da Escola Pholmann
- Página Inicial
- Explicação do Taekwondo
- História da Escola
- Álbum de Fotos

Sistema da Escola Pholmann
- Alunos (Faixas)
- Pagamentos Mensalidades
