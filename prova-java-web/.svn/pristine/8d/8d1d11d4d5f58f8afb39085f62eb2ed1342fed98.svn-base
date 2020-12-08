CREATE DATABASE BD_Prova;
USE BD_Prova;

CREATE TABLE Endereco (
	idEndereco INT(6) NOT NULL AUTO_INCREMENT PRIMARY KEY, 
    rua VARCHAR(60) NOT NULL,
    numero INT,
    bairro VARCHAR(30) NOT NULL,
    dtRemocao DATETIME
);

CREATE TABLE Pessoa (
	idPessoa INT(6) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idEndereco INT NOT NULL,
    nome VARCHAR(30) NOT NULL,
    sobrenome VARCHAR(60),
    cpf VARCHAR(11),
    dtNascimento DATETIME,
    login varchar(255),
    senha varchar(255),
    tipo_permisao int(2),
    CONSTRAINT fk_endereco
		FOREIGN KEY(idEndereco)
        REFERENCES Endereco(idEndereco)    
);

CREATE TABLE Montadora(
	idMontadora INT PRIMARY KEY AUTO_INCREMENT,
    nomeMontadora VARCHAR(255),
    pais VARCHAR(255)
);

CREATE TABLE Carro(
  idCarro INT NOT NULL AUTO_INCREMENT,
  idMontadora INT NOT NULL,
  ano INT NULL,
  modelo VARCHAR(255) NOT NULL,
  valor DOUBLE NULL,
  PRIMARY KEY (idCarro),
  CONSTRAINT fk_montadora
    FOREIGN KEY (idMontadora)
    REFERENCES Montadora (idMontadora)
);