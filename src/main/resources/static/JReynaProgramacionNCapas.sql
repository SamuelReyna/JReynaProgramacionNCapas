--creación de tabla
CREATE TABLE usuarios (
idUser number generated always as identity,
nombre varchar(20),
apellidos varchar(20),
fechaNacimiento date,
edad int,
contrasena varchar(30),
sexo char(1)
);
---Insersión de datos
INSERT INTO usuarios (nombre, apellidos, fechaNacimiento, edad, contrasena, sexo) 
VALUES ( 'Samuel', 'Reyna', '08-AUG-03', '21', '1234', 'M');
INSERT INTO usuarios (nombre, apellidos, fechaNacimiento, edad, contrasena, sexo) 
VALUES ( 'Eduardo', 'Reyna', '21-NOV-01', '23', '1234', 'M');
INSERT INTO usuarios (nombre, apellidos, fechaNacimiento, edad, contrasena, sexo) 
VALUES ( 'Eduardo', 'Reyna', '23-SEP-72', '50', '1234', 'M');
INSERT INTO usuarios (nombre, apellidos, fechaNacimiento, edad, contrasena, sexo) 
VALUES ( 'Claudia', 'Gonzalez', '08-SEP-76', '47', '1234', 'F');
INSERT INTO usuarios (nombre, apellidos, fechaNacimiento, edad, contrasena, sexo) 
VALUES ( 'Pedro', 'Garcia', '26-AUG-05', '19', '1234', 'M');
INSERT INTO usuarios (nombre, apellidos, fechaNacimiento, edad, contrasena, sexo) 
VALUES ( 'Miriam', 'Garcia', '13-MAR-97', '25', '1234', 'F');
INSERT INTO usuarios (nombre, apellidos, fechaNacimiento, edad, contrasena, sexo) 
VALUES ( 'Paola', 'ho', '11-FEB-2002', '22', '1234', 'F');
INSERT INTO usuarios (nombre, apellidos, fechaNacimiento, edad, contrasena, sexo) 
VALUES ( 'Amy', 'Vega', '03-OCT-02', '22', '1234', 'M');
INSERT INTO usuarios (nombre, apellidos, fechaNacimiento, edad, contrasena, sexo) 
VALUES ( 'Eve', 'Lopez', '05-DEC-08', '17', '1234', 'F');
INSERT INTO usuarios (nombre, apellidos, fechaNacimiento, edad, contrasena, sexo) 
VALUES ( 'Yareli', 'Solis', '14-FEB-96', '29', '1234', 'F');

--Actualización de datos
UPDATE usuarios SET nombre = 'Roxana' where idUser = 12;
UPDATE usuarios SET edad = 15 where idUser = 5;
UPDATE usuarios SET apellidos = 'Contreras' where iduser = 12;
UPDATE usuarios SET apellidos = 'Contreras', nombre = 'Rocio' where iduser = 12;

--Eliminación de datos
DELETE from usuarios where iduser =10;
DELETE from usuarios where iduser =6;

--Consulta de datos
SELECT * FROM usuarios;

