--
-- Banco de Dados: PostgreSQL 13.10
-- Plataforma: ElephantSQL - PostgreSQL as a Service
-- https://www.elephantsql.com/
--

--
-- TABELA: usuarios
--

CREATE SEQUENCE IF NOT EXISTS usuarios_seq_id
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
	
CREATE TABLE IF NOT EXISTS usuarios
(
    id bigint NOT NULL DEFAULT nextval('usuarios_seq_id'::regclass),
    nome character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    login character varying(100) NOT NULL,
    senha character varying(255) NOT NULL,
    CONSTRAINT usuarios_pk UNIQUE (id)
)
WITH (
    autovacuum_enabled = TRUE
)
TABLESPACE pg_default;

--
-- TABELA: telefones
--
-- A tabela telefones usa FK com a coluna id da tabela 'usuarios'
-- Na FK temos o ON DELETE CASCADE, pois quando apagamos um usuário na tabela 'usuarios', precisamos apagar seus 
-- telefones na tabela 'telefones'

CREATE SEQUENCE IF NOT EXISTS telefones_seq_id
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS telefones
(
    id bigint NOT NULL DEFAULT nextval('telefones_seq_id'::regclass),
    tipo character varying(10) NOT NULL,
    telefone character varying(15) NOT NULL,
    usuariosid bigint NOT NULL,
    CONSTRAINT telefones_pk PRIMARY KEY (id),
    CONSTRAINT validar_tipo CHECK (tipo IN ('Celular', 'Fixo', 'Comercial', 'Fax') ),
    CONSTRAINT telefones_fk FOREIGN KEY (usuariosid) REFERENCES usuarios(id) ON DELETE CASCADE
)
WITH (
    autovacuum_enabled = TRUE
)
TABLESPACE pg_default;
