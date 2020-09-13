CREATE TABLE customer(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name  VARCHAR(50) NOT NULL,
	cpf  VARCHAR(11) NOT NULL,
	street VARCHAR(50),
	number VARCHAR(50),
	complement VARCHAR(50),
	neighborhood VARCHAR(50),
	zip_code VARCHAR(50),
	city VARCHAR(50),
	states VARCHAR(50)
);

INSERT INTO customer (name, cpf, street, number,complement, neighborhood, zip_code, city, states) VALUES ('Thiago', '12345678991','Rua Adriano Cintra', '15','Ap. 6', 'Jardim Maringá', '0253520', 'São Paulo', 'São Paulo');
