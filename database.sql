DROP TABLE IF EXISTS factura;
CREATE TABLE factura (
  Codigo INT(10) AUTO_INCREMENT,
  Usuario_COD INT(10),
  Nombre VARCHAR(50),
  Vehiculo_Tipo VARCHAR(20),
  Vehiculo_Placa VARCHAR(20),
  Hora_Entrada TIME,
  Puesto INT(10),
  Salio VARCHAR(20),
  Hora_Salida TIME,
  Valor_Pagar DOUBLE,
  PRIMARY KEY (Codigo)
);
INSERT INTO factura VALUES (1, 1, 'Pedro', 'carro', 'abc123', '10:00:00', 5, 'true', '11:00:00', 3000.0);
INSERT INTO factura VALUES (2, 2, 'Vicente', 'carro', 'rrr777', '02:00:00', 20, 'true', '03:00:00', 1000.0);
INSERT INTO factura VALUES (3, 2, 'Steven', 'moto', 'xxx11a', '10:10:00', 15, 'true', '12:00:00', 4000.0);
INSERT INTO factura VALUES (4, 3, 'Alberto', 'bicicleta', '0000', '02:00:00', 21, 'true', '02:30:00', 1500.0);
INSERT INTO factura VALUES (5, 2, 'Alberto', 'bicicleta', '0000', '03:00:00', 22, 'false', NULL, 0.0);

DROP TABLE IF EXISTS usuarios;
CREATE TABLE usuarios (
  Codigo INT(10) AUTO_INCREMENT,
  Nombre VARCHAR(100),
  Contrasena VARCHAR(100),
  Correo VARCHAR(100),
  Nivel INT(10),
  PRIMARY KEY (Codigo)
);
INSERT INTO usuarios VALUES (1, 'admin', 'admin', 'admin@gmail.com', 1);
INSERT INTO usuarios VALUES (2, 'empleado1', 'empleado', 'empleado1@gmail.com', 2);
INSERT INTO usuarios VALUES (3, 'empleado2', 'empleado', 'empleado2@gmail.com', 2);
INSERT INTO usuarios VALUES (4, 'clienteA', 'cliente', 'clientea@gmail.com', 3);
INSERT INTO usuarios VALUES (5, 'clienteB', 'cliente', 'clienteb@gmail.com', 3);

