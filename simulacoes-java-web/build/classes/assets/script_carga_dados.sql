-- INSERE AS MONTADORAS
INSERT INTO MONTADORA
VALUES (1, 'Ford', 'Estados Unidos');

INSERT INTO MONTADORA
VALUES (2, 'FIAT', 'Itália');

INSERT INTO MONTADORA
VALUES (3, 'Chevrolet', 'Estados Unidos');

INSERT INTO MONTADORA
VALUES (4, 'Volkswagen', 'Alemanha');

INSERT INTO MONTADORA
VALUES (5, 'Audi', 'Alemanha');

COMMIT;

-- INSERE OS CARROS
INSERT INTO CARRO
VALUES (1, 1, 2016, 'Fusion', '140000.00');

INSERT INTO CARRO
VALUES (2, 2, 2017, 'Uno', '40000.00');

INSERT INTO CARRO
VALUES (3, 4, 2010, 'Golf', '42500.00');

INSERT INTO CARRO
VALUES (4, 5, 2018, 'A1', '110000.00');

INSERT INTO CARRO
VALUES (5, 3, 2015, 'Ônix','55000.00');

COMMIT;


-- INSERE OS ENDERECOS
INSERT INTO ENDERECO
VALUES (1, 'Presidente Kennedy', 123, 'Kobrasol');

INSERT INTO ENDERECO
VALUES (2, 'Felipe Schimidt', 456, 'Centro');

INSERT INTO ENDERECO
VALUES (3, 'Lauro Linhares', 789, 'Trindade');

INSERT INTO ENDERECO
VALUES (4, 'Silva Jardim', 134, 'Centro');
COMMIT;

-- INSERE AS PESSOAS
INSERT INTO PESSOA
VALUES (1, 'Edson', 'Arantes do Nascimento', 1, 'edson', 'senha', 1);

INSERT INTO PESSOA
VALUES (2, 'Arhur', 'Antunes Coimbra', 2, 'zico', 'senha', 2);

INSERT INTO PESSOA
VALUES (3, 'Manoel', 'Francisco dos Santos', 3, 'garrincha', 'senha', 3);

INSERT INTO PESSOA
VALUES (4, 'Mário', 'Jorge Lobo Zagallo', 1, 'lobo', 'senha', 1);

INSERT INTO PESSOA
VALUES (5, 'Eduardo', 'Gonçalves de Andrade', 4, 'tostao', 'senha', 2);
COMMIT;