CREATE OR REPLACE FUNCTION SQLTest(text) 
RETURNS text AS 
$BODY$ 

$BODY$
LANGUAGE 'sql' IMMUTABLE STRICT; 


INSERT INTO entidade (ent_codigo, emaildb, ent_inativo, ent_ultimoacesso, nomedb, senhadb, tipo, versionnum) 
VALUES (1, 'admin@acesso.com.br', false, '2016-05-22 00:00:00', 'Jose Admin', 'admin', 'ADMIN', 0);

INSERT INTO entidade (ent_codigo, emaildb, ent_inativo, ent_ultimoacesso, nomedb, senhadb, tipo, versionnum) 
VALUES (2, 'professor@acesso.com.br', false, '2016-05-22 00:00:00', 'Jose Professor', 'professor', 'PROFESSOR', 0);

INSERT INTO entidade (ent_codigo, emaildb, ent_inativo, ent_ultimoacesso, nomedb, senhadb, tipo, versionnum) 
VALUES (3, 'aluno@acesso.com.br', false, '2016-05-22 00:00:00', 'Jose aluno', 'aluno', 'ALUNO', 0);

SELECT nextval('entidade_seq');