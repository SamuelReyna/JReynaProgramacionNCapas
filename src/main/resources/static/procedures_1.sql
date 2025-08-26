--------------------------------------------------------
--  File created - viernes-agosto-22-2025   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure ADDDIRECCION
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."ADDDIRECCION" (

pCalle IN VARCHAR, pNumInt IN VARCHAR, pNumExt IN VARCHAR, pIdColonia IN NUMBER, pIdUsuario IN NUMBER)
AS
BEGIN
INSERT INTO Direccion (Calle, NumeroInterior, NumeroExterior, IdColonia, IdUsuario)
VALUES(pCalle, pNumInt, pNumExt, pIdColonia, pIdUsuario);

END AddDireccion;

/
--------------------------------------------------------
--  DDL for Procedure ALLUSERSADDRESS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."ALLUSERSADDRESS" (
pCursor OUT SYS_REFCURSOR
)
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
END AllUsersAddress; 

/
--------------------------------------------------------
--  DDL for Procedure DELETEDIRECCION
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."DELETEDIRECCION" (

pIdDireccion IN NUMBER
)
AS
BEGIN
DELETE FROM Direccion WHERE IdDireccion = pIdDireccion;
END DeleteDireccion;

/
--------------------------------------------------------
--  DDL for Procedure DELETEDIRECCIONESBYUSUARIOID
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."DELETEDIRECCIONESBYUSUARIOID" (
pIdUsuario IN NUMBER)
AS
BEGIN
DELETE FROM Direccion WHERE IdUsuario = pIdUsuario;
END DeleteDireccionesByUsuarioId;

/
--------------------------------------------------------
--  DDL for Procedure DELETEUSERANDDIRECCION
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."DELETEUSERANDDIRECCION" (
pIdUsuario IN NUMBER)
AS
BEGIN
DELETE FROM Direccion WHERE IdUsuario = pIdUsuario;
DELETE FROM Usuarios WHERE IdUser = pIdUsuario;
END DeleteUserAndDireccion;

/
--------------------------------------------------------
--  DDL for Procedure GETALLCOLONIAS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."GETALLCOLONIAS" (
pr OUT SYS_REFCURSOR
)
AS
BEGIN
OPEN pr FOR
SELECT idColonia, Nombre, CodigoPostal FROM Colonia;
END getAllColonias;

/
--------------------------------------------------------
--  DDL for Procedure GETALLESTADO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."GETALLESTADO" (
pr OUT SYS_REFCURSOR
)
AS
BEGIN
OPEN pr FOR
SELECT idEstado, Nombre FROM Estado;
END getAllEstado;

/
--------------------------------------------------------
--  DDL for Procedure GETALLMUNICIPIO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."GETALLMUNICIPIO" (
pr OUT SYS_REFCURSOR
)
AS
BEGIN
OPEN pr FOR
SELECT idMunicipio, Nombre FROM Municipio;
END getAllMunicipio;

/
--------------------------------------------------------
--  DDL for Procedure GETALLPAIS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."GETALLPAIS" (
pr OUT SYS_REFCURSOR
)
AS
BEGIN
OPEN pr FOR
SELECT idPais, Nombre FROM Pais;
END GetAllPais;

/
--------------------------------------------------------
--  DDL for Procedure GETALLUSERSWITHADRESS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."GETALLUSERSWITHADRESS" (
    pCursor  OUT SYS_REFCURSOR,
    pNombre IN VARCHAR2,
    pApellidoPaterno IN VARCHAR2,
    pApellidoMaterno IN VARCHAR2,
    pIdRol IN INT
)
AS
    SQLQuery VARCHAR2(4000);
BEGIN
    SQLQuery := 'SELECT u.iduser,
                        u.nombre, u.apellidoPaterno, u.apellidoMaterno, u.fechaNacimiento, u.password, u.sexo,
                        u.curp, u.username, u.email, u.telefono, u.celular, u.idRol,u.img, r.nombre AS Rol,
                        d.idDireccion, d.calle, d.numeroInterior, d.NumeroExterior, c.idColonia,
                        c.nombre AS Colonia, c.codigoPostal,m.idMunicipio, m.nombre AS Municipio,
                        e.idEstado, e.nombre AS Estado,p.IdPais, p.nombre as Pais
                 FROM usuarios u 
                 LEFT JOIN direccion d ON u.idUser = d.idUsuario
                 LEFT JOIN rol r ON u.idRol = r.idrol
                 LEFT JOIN Colonia c ON d.idColonia = c.idColonia 
                 LEFT JOIN Municipio m ON c.idMunicipio = m.idMunicipio
                 LEFT JOIN Estado e ON m.idEstado = e.idEstado
                 LEFT JOIN Pais p ON e.IdPais = p.IdPais
                 WHERE UPPER(NVL(u.Nombre, '' '')) LIKE ''%'' || UPPER(:pNombre) || ''%''
  AND UPPER(NVL(u.ApellidoPaterno, '' '')) LIKE ''%'' || UPPER(:pApellidoPaterno) || ''%''
  AND UPPER(NVL(u.ApellidoMaterno, '' '')) LIKE ''%'' || UPPER(:pApellidoMaterno) || ''%''';


    IF pIdRol != 0 THEN
        SQLQuery := SQLQuery || ' AND u.idRol = :pIdRol ';
        OPEN pCursor FOR SQLQuery
            USING pNombre, pApellidoPaterno, pApellidoMaterno, pIdRol;
    ELSE
        OPEN pCursor FOR SQLQuery
            USING pNombre, pApellidoPaterno, pApellidoMaterno;
    END IF;

END GetAllUsersWithAdress;

/
--------------------------------------------------------
--  DDL for Procedure GETCOLONIABYMUNICIPIO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."GETCOLONIABYMUNICIPIO" (
pr OUT SYS_REFCURSOR,
pMunicipio IN INT
)
AS
BEGIN
OPEN pr FOR
SELECT idColonia, Nombre, CodigoPostal FROM Colonia WHERE idMunicipio = pMunicipio;
END getColoniaByMunicipio;

/
--------------------------------------------------------
--  DDL for Procedure GETDIRECCIONBYID
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."GETDIRECCIONBYID" (
pr OUT SYS_REFCURSOR,
pIdDireccion IN NUMBER
)
AS
BEGIN
OPEN pr FOR
SELECT u.iduser,
u.nombre, u.apellidoPaterno, u.apellidoMaterno, u.fechaNacimiento, u.password, u.sexo,
u.curp, u.username, u.email, u.telefono, u.celular, u.idRol,u.img, r.nombre AS Rol,
d.idDireccion, d.calle, d.numeroInterior, d.NumeroExterior, c.idColonia,
c.nombre AS Colonia, c.codigoPostal,m.idMunicipio, m.nombre AS Municipio
,e.idEstado, e.nombre AS Estado,p.IdPais, p.nombre as Pais
FROM usuarios u LEFT JOIN direccion d ON u.idUser = d.idUsuario
LEFT JOIN rol r ON u.idRol = r.idrol
LEFT JOIN Colonia c ON d.idColonia = c.idColonia 
LEFT JOIN Municipio m ON c.idMunicipio = m.idMunicipio
LEFT JOIN Estado e ON m.idEstado = e.idEstado
LEFT JOIN Pais p ON e.IdPais = p.IdPais
WHERE idDireccion = pIdDireccion;
END GetDireccionById;

/
--------------------------------------------------------
--  DDL for Procedure GETDIRECCIONBYIDUSER
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."GETDIRECCIONBYIDUSER" (
pCursor OUT SYS_REFCURSOR,
pIdUser IN NUMBER
)
AS
BEGIN
OPEN pCursor FOR
SELECT u.iduser,
u.nombre, u.apellidoPaterno, u.apellidoMaterno, u.fechaNacimiento, u.password, u.sexo,
u.curp, u.username, u.email, u.telefono, u.celular, u.idRol,u.img, r.nombre AS Rol,
d.idDireccion, d.calle, d.numeroInterior, d.NumeroExterior, c.idColonia,
c.nombre AS Colonia, c.codigoPostal,m.idMunicipio, m.nombre AS Municipio
,e.idEstado, e.nombre AS Estado,p.IdPais, p.nombre as Pais
FROM usuarios u LEFT JOIN direccion d ON u.idUser = d.idUsuario
LEFT JOIN rol r ON u.idRol = r.idrol
LEFT JOIN Colonia c ON d.idColonia = c.idColonia 
LEFT JOIN Municipio m ON c.idMunicipio = m.idMunicipio
LEFT JOIN Estado e ON m.idEstado = e.idEstado
LEFT JOIN Pais p ON e.IdPais = p.IdPais
WHERE d.IdUsuario = pIdUser;
END GetDireccionByIdUser;

/
--------------------------------------------------------
--  DDL for Procedure GETESTADOBYPAIS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."GETESTADOBYPAIS" (
pr OUT SYS_REFCURSOR,
pPais IN INT
)
AS
BEGIN
OPEN pr FOR
SELECT idEstado, Nombre FROM Estado WHERE idPais = pPais;
END getEstadoByPais;

/
--------------------------------------------------------
--  DDL for Procedure GETMUNICIPIOBYESTADO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."GETMUNICIPIOBYESTADO" (
pr OUT SYS_REFCURSOR,
pEstado IN INT
)
AS
BEGIN
OPEN pr FOR
SELECT idMunicipio, Nombre FROM Municipio Where idEstado = pEstado;
END getMunicipioByEstado;

/
--------------------------------------------------------
--  DDL for Procedure GETPAIS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."GETPAIS" (
pr OUT SYS_REFCURSOR
)
AS
BEGIN
OPEN pr FOR
SELECT idPais, nombre FROM Pais;
END GetPais;

/
--------------------------------------------------------
--  DDL for Procedure GETROL
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."GETROL" (
pr OUT SYS_REFCURSOR
)
AS
BEGIN
OPEN pr FOR
SELECT idRol, nombre FROM rol;
END GetRol;

/
--------------------------------------------------------
--  DDL for Procedure UPDATEDIRECCION
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."UPDATEDIRECCION" (
pCalle IN VARCHAR,
pNumInt IN VARCHAR,
pNumExt IN VARCHAR,
pIdColonia IN NUMBER,
pIdUsuario IN NUMBER,
pIdDireccion IN NUMBER
)
AS
BEGIN
UPDATE Direccion SET
calle = pCalle, numeroInterior = pNumInt, numeroExterior = pNumExt, idColonia = pIdColonia, IdUsuario = pIdUsuario
WHERE idDireccion = pIdDireccion;
END UpdateDireccion;

/
--------------------------------------------------------
--  DDL for Procedure USERADD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."USERADD" (
pNombre IN VARCHAR,
pApellidoPaterno  IN VARCHAR ,
pApellidoMaterno IN VARCHAR,
pFechaNacimiento   IN DATE,
pPassword IN VARCHAR,
pSexo IN CHAR,
pUsername IN VARCHAR,
pEmail IN VARCHAR,
pTelefono IN VARCHAR,
pCelular IN VARCHAR,
pCurp IN VARCHAR,
pIdRol IN INT
)
AS
BEGIN
INSERT INTO usuarios 
(nombre,
apellidoPaterno,
apellidoMaterno, 
fechaNacimiento,
password,
sexo, 
username,
email,
telefono,
celular,
curp, 
idRol)
VALUES 
(pNombre,
pApellidoPaterno,
pApellidoMaterno,
pFechaNacimiento,
pPassword,
pSexo,
pUsername,
pEmail,
pTelefono,
pCelular,
pCurp,
pIdRol);
END UserAdd;

/
--------------------------------------------------------
--  DDL for Procedure USERDELETE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."USERDELETE" (
pUserId IN INT)
AS
BEGIN
DELETE FROM usuarios WHERE idUser = pUserId;
END UserDelete;

/
--------------------------------------------------------
--  DDL for Procedure USERGETALL
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."USERGETALL" 
(pUserCursor OUT SYS_REFCURSOR)
AS BEGIN
OPEN pUserCursor FOR
SELECT u.IdUser, u.Username, u.nombre AS NombreUsuario, u.apellidoPaterno, u.apellidoMaterno,
u.fechaNacimiento, u.sexo, u.email,u.password, u.telefono, u.celular, u.curp, r.Nombre AS NombreRol
FROM usuarios u LEFT JOIN rol r ON u.idRol = r.idRol;
END UserGetAll;

/
--------------------------------------------------------
--  DDL for Procedure USERGETALLBYNAME
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."USERGETALLBYNAME" 
(pUserCursor OUT SYS_REFCURSOR)
AS BEGIN
OPEN pUserCursor FOR
SELECT u.IdUser, u.Username, u.nombre AS NombreUsuario, u.apellidoPaterno, u.apellidoMaterno,
u.fechaNacimiento, u.sexo, u.email, u.telefono, u.celular, u.curp, r.Nombre AS NombreRol
FROM usuarios u LEFT JOIN rol r ON u.idRol = r.idRol ORDER BY u.nombre;
END UserGetAllByName;

/
--------------------------------------------------------
--  DDL for Procedure USERGETONE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."USERGETONE" (
pUserCursor OUT SYS_REFCURSOR,
pUserId IN INT)
AS
BEGIN
OPEN pUserCursor FOR
SELECT u.IdUser, u.Username, u.nombre AS NombreUsuario, u.apellidoPaterno, u.apellidoMaterno,
u.fechaNacimiento, u.sexo, u.email,u.password, u.telefono, u.celular, u.curp, r.Nombre AS NombreRol
FROM usuarios u LEFT JOIN rol r ON u.idRol = r.idRol WHERE u.idUser = pUserId;
END UserGetOne;

/
--------------------------------------------------------
--  DDL for Procedure USERSTOTALDIRECCIONES
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."USERSTOTALDIRECCIONES" (
pCursor OUT SYS_REFCURSOR
)
AS
BEGIN 
OPEN pCursor FOR
SELECT u.idUser, u.nombre, u.apellidoPaterno, u.apellidoMaterno, u.curp, u.username, u.email, COUNT(d.idusuario) as TotalDirecciones
FROM usuarios u RIGHT JOIN Direccion d ON u.idUser = d.idusuario GROUP BY u.idUser,u.nombre, u.ApellidoPaterno, u.ApellidoMaterno, u.curp, u.username, u.email, d.idusuario
HAVING COUNT(d.idusuario) > 1;
END usersTotalDirecciones; 

/
--------------------------------------------------------
--  DDL for Procedure USERUPDATE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."USERUPDATE" (
pNombre IN VARCHAR,
pApellidoPaterno  IN VARCHAR ,
pApellidoMaterno IN VARCHAR,
pFechaNacimiento   IN DATE,
pPassword IN VARCHAR,
pSexo IN CHAR,
pUsername IN VARCHAR,
pEmail IN VARCHAR,
pTelefono IN VARCHAR,
pCelular IN VARCHAR,
pCurp IN VARCHAR,
pIdRol IN INT,
pIdUser IN INT)
AS
BEGIN
UPDATE usuarios SET
nombre = pNombre, 
apellidoPaterno = pApellidoPaterno,
apellidoMaterno = pApellidoMaterno, 
fechaNacimiento = pFechaNacimiento,
password = pPassword,  
sexo = pSexo,
username = pUsername,
email = pEmail,
telefono = pTelefono,
celular = pCelular,
curp = pCurp,
idRol = pIdRol
WHERE idUser = pIdUser;
END UserUpdate;

/
--------------------------------------------------------
--  DDL for Procedure USUARIODIRECCIONADD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JREYNAPROGRAMACIONNCAPAS"."USUARIODIRECCIONADD" ( 
  pNombre IN VARCHAR2,
  pApellidoPaterno IN VARCHAR2,
  pApellidoMaterno IN VARCHAR2,
  pFechaNacimiento IN DATE,
  pPassword IN VARCHAR2,
  pSexo IN CHAR,
  pUsername IN VARCHAR2,
  pEmail IN VARCHAR2,
  pTelefono IN VARCHAR2,
  pCelular IN VARCHAR2,
  pCurp IN VARCHAR2,
  pIdRol IN NUMBER,
  pCalle IN VARCHAR2,
  pNumeroInterior IN VARCHAR2,
  pNumeroExterior IN VARCHAR2,
  pIdColonia IN NUMBER,
  pImg IN CLOB  
) AS
  ultimo_id NUMBER;

BEGIN
  INSERT INTO usuarios (
    nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, password, sexo,
    username, email, telefono, celular, curp, idRol, img)
  VALUES (
    pNombre, pApellidoPaterno, pApellidoMaterno, pFechaNacimiento, pPassword, pSexo,
    pUsername, pEmail, pTelefono, pCelular, pCurp, pIdRol, pImg
  )
  RETURNING iduser INTO ultimo_id;

  INSERT INTO Direccion (
    calle, numeroInterior, numeroExterior, idColonia, idUsuario
  ) VALUES (
    pCalle, pNumeroInterior, pNumeroExterior, pIdColonia, ultimo_id
  );

  COMMIT;
END UsuarioDireccionAdd;

/
