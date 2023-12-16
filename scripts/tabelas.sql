create table eusably.endereco (
	id int not null auto_increment,
    logradouro varchar(255),
    bairro varchar(255),
    numero int,
    municipio varchar(255),
    estado varchar(255),
    cep int,
    primary key(id)
);

create table eusably.disciplina (
	codigo varchar(255) not null,
    descricao varchar(255),
    professor varchar(255),
    primary key(codigo)
);

create table eusably.aluno (
	id int not null auto_increment,
    nome varchar(255),
    sobrenome varchar(255),
    idade int,
    id_endereco int,
    serie varchar(255),
    sexo varchar(255),
    primary key(id),
    CONSTRAINT fk_aluno_endereco FOREIGN KEY (id_endereco) REFERENCES eusably.endereco(id)
);

create table eusably.matricula (
	id_aluno int not null,
    codigo_disciplina varchar(255) not null,
    primary key(id_aluno, codigo_disciplina),
    CONSTRAINT fk_matricula_aluno FOREIGN KEY (id_aluno) REFERENCES eusably.aluno(id),
    CONSTRAINT fk_matricula_disciplina FOREIGN KEY (codigo_disciplina) REFERENCES eusably.disciplina(codigo)
);