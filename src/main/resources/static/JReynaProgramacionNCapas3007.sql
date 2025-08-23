CREATE TABLE Pais (
IdPais NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Nombre VARCHAR(50) NOT NULL);

CREATE TABLE Estado (
IdEstado NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Nombre VARCHAR(50) NOT NULL,
IdPais NUMBER,
FOREIGN KEY (IdPais) REFERENCES Pais(IdPais));

CREATE TABLE Municipio (
IdMunicipio NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Nombre VARCHAR(50) NOT NULL,
IdEstado NUMBER,
FOREIGN KEY (IdEstado) REFERENCES Estado(IdEstado));

CREATE TABLE Colonia (
IdColonia NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Nombre VARCHAR(50) NOT NULL,
CodigoPostal VARCHAR(50) NOT NULL,
IdMunicipio NUMBER,
FOREIGN KEY (IdMunicipio) REFERENCES Municipio(IdMunicipio));

ALTER TABLE Usuarios MODIFY IdUSER PRIMARY KEY;

CREATE TABLE Direccion (
IdDireccion NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Calle VARCHAR(50) NOT NULL,
NumeroInterior VARCHAR(20) NULL,
NumeroExterior VARCHAR(20) NOT NULL,
IdColonia NUMBER,
IdUsuario NUMBER,
FOREIGN KEY (IdColonia) REFERENCES Colonia(IdColonia),
FOREIGN KEY (IdUsuario) REFERENCES Usuarios(IdUser)
);

INSERT INTO Pais (nombre) VALUES ('México');
INSERT INTO Pais (nombre) VALUES ('Irlanda');

INSERT INTO Estado (Nombre, IdPais) VALUES ('Durango','3');
INSERT INTO Estado (Nombre, IdPais) VALUES ('Nuevo Leon','3');

INSERT INTO Municipio (Nombre, IdEstado) VALUES ('Victoria de Durango','5');
INSERT INTO Municipio (Nombre, IdEstado) VALUES ('Cuencame de Ceniceros','5');

INSERT INTO Colonia(Nombre, codigoPostal, idMunicipio) VALUES ('SEDUE', '34166', '1');
INSERT INTO Colonia(Nombre, codigoPostal, idMunicipio) VALUES ('SANTA TERERESA', '35808', '2');

INSERT INTO Direccion(Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('Cerceta','#5245','#A452','2','5');
INSERT INTO Direccion(Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('otraCalle','','#13425','1','8');
INSERT INTO Direccion(Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('Principal','','#1156','2','13');
INSERT INTO Direccion(Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('Principal','','#1156','2','13');
INSERT INTO Direccion(Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('Principal','','#1156','2','13');
INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('Av. Juárez', '12B', '524', 1, '43');

INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('Calle Hidalgo', '', '1342', 2, 54);

INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('Av. Revolución', 'A3', '1156', 1, 12);

INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('Calle Allende', '', '982', 2, 58);

INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('Blvd. Durango', '4C', '211', 1, 13);

INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('Av. 20 de Noviembre', '', '350', 2, 58);

INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('Calle Victoria', 'B1', '675', 1, 43);

INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('Calle Negrete', '', '890', 2, 11);

INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('Av. Universidad', 'A2', '1420', 1, 9);

INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('Calle Morelos', '', '763', 2, 46);

INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('Calle Pino Suárez', '3D', '2154', 1, 12);

INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('Blvd. Felipe Pescador', '', '876', 2, 11);

INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('Av. Patoni', '5B', '1024', 1, 59);

INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('Calle Zaragoza', '', '509', 2, 5);

INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES ('Blvd. Guadiana', 'C1', '3345', 1, 7);

SELECT u.nombre, u.apellidoPaterno, u.apellidoMaterno, u.curp, u.username, u.email,
d.calle, d.numeroInterior, d.NumeroExterior, c.nombre AS Colonia, c.codigoPostal, m.nombre AS Municipio
, e.nombre AS Estado, p.nombre as Pais
FROM usuarios u INNER JOIN direccion d ON u.idUser = d.idUsuario
INNER JOIN Colonia c ON d.idColonia = c.idColonia 
INNER JOIN Municipio m ON c.idMunicipio = m.idMunicipio
INNER JOIN Estado e ON m.idEstado = e.idEstado
INNER JOIN Pais p ON e.IdPais = p.IdPais
;

SELECT u.nombre, u.apellidoPaterno, u.apellidoMaterno, u.curp, u.username, u.email,
d.calle, d.numeroInterior, d.NumeroExterior, c.nombre AS Colonia, c.codigoPostal, m.nombre AS Municipio
, e.nombre AS Estado, p.nombre as Pais
FROM usuarios u LEFT JOIN direccion d ON u.idUser = d.idUsuario
LEFT JOIN Colonia c ON d.idColonia = c.idColonia 
LEFT JOIN Municipio m ON c.idMunicipio = m.idMunicipio
LEFT JOIN Estado e ON m.idEstado = e.idEstado
LEFT JOIN Pais p ON e.IdPais = p.IdPais;

SELECT u.nombre, u.apellidoPaterno, u.apellidoMaterno, u.curp, u.username, u.email,
d.calle, d.numeroInterior, d.NumeroExterior, c.nombre AS Colonia, c.codigoPostal, m.nombre AS Municipio
, e.nombre AS Estado, p.nombre as Pais
FROM usuarios u LEFT JOIN direccion d ON u.idUser = d.idUsuario
LEFT JOIN Colonia c ON d.idColonia = c.idColonia 
LEFT JOIN Municipio m ON c.idMunicipio = m.idMunicipio
LEFT JOIN Estado e ON m.idEstado = e.idEstado
LEFT JOIN Pais p ON e.IdPais = p.IdPais;

SELECT u.idUser, u.nombre, u.apellidoPaterno, u.apellidoMaterno, u.curp, u.username, u.email, COUNT(d.idusuario) as TotalDirecciones
FROM usuarios u RIGHT JOIN Direccion d ON u.idUser = d.idusuario GROUP BY u.idUser,u.nombre, u.ApellidoPaterno, u.ApellidoMaterno, u.curp, u.username, u.email, d.idusuario
HAVING COUNT(d.idusuario) > 1;


CREATE OR REPLACE PROCEDURE GetAllUsersWithAdress(
pCursor  out SYS_REFCURSOR)
AS
BEGIN
OPEN pCursor FOR
SELECT u.nombre, u.apellidoPaterno, u.apellidoMaterno, u.curp, u.username, u.email,
d.calle, d.numeroInterior, d.NumeroExterior, c.nombre AS Colonia, c.codigoPostal, m.nombre AS Municipio
, e.nombre AS Estado, p.nombre as Pais
FROM usuarios u LEFT JOIN direccion d ON u.idUser = d.idUsuario
LEFT JOIN Colonia c ON d.idColonia = c.idColonia 
LEFT JOIN Municipio m ON c.idMunicipio = m.idMunicipio
LEFT JOIN Estado e ON m.idEstado = e.idEstado
LEFT JOIN Pais p ON e.IdPais = p.IdPais;
END GetAllUsersWithAdress;

var pr refcursor;
call GetAllUsersWithAdress(:pr);
print pr;


