/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Aron
 * Created: 5 de fev. de 2025
 */

CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(64) NOT NULL
);

CREATE TABLE funcionarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_admissao DATE NOT NULL,
    salario NUMERIC(10,2) NOT NULL,
    status BOOLEAN NOT NULL
);

-- Usuário de teste
INSERT INTO usuarios(nome, email, senha)
VALUES ('Teste', 'teste@exemplo.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92'); -- Senha: 123456

-- Funcionários de teste
INSERT INTO funcionarios(nome, data_admissao, salario, status)
VALUES 
('Funcionário A', '2022-01-15', 2500.00, true),
('Funcionário B', '2021-06-10', 3200.50, false);
