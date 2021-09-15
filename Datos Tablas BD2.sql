insert into Empleado(ciE,nombre,apellido,fechaNac) values
	('11111111','Juan','Perez','01/01/1997'),
	('22222222','Enrique','Duki','05/07/1995'),
	('33333333','Alejandro','Gutierrez','09/12/1987'),
	('44444444','Rodriguez','Sodre','15/08/1965'),
	('55555555','Ignacio','Estevez','28/02/1996'),
	('66666666','Elsa','Bandija','30/05/1986')


insert into Cliente(ciC,nombre,apellido,fechaNac) values
	('12345678','Roberto','Perez','02/02/1997'),
	('12345323','Pedro','Gutierrez','06/08/1995'),
	('36598789','German','Gutierrez','10/02/1987'),
	('23698754','Rodrigo','Rodriguez','29/06/1970'),
	('22359754','Lorena','Suarez','25/10/1975'),
	('65898456','Fernanda','Hernandez','10/07/1980'),
	('59756426','Esteban','Lema','30/08/1985'),
	('56478966','Juan','Rodriguez','03/03/1990'),
	('23648972','Lucas','Perez','24/12/1966'),
	('78562513','Natalia','Gutierrez','06/09/1967'),
	('62346879','Romina','Dawson','05/06/1968'),
	('32168793','Camila','Fernandez','10/02/1969'),
	('12312348','Lucia','Estevez','20/03/1997'),
	('87654321','Carla','Rodriguez','09/12/1990'),
	('49863875','Alejandro','Rodriguez','03/03/1998')


insert into Aerolinea(nombre) values
	('American Airlines'),
	('Latam'),
	('Pluna'),
	('Fly Emirates'),
	('Montevideo Airlines'),
	('Singapore Airlines'),
	('Qatar Airways'),
	('Lufthansa'),
	('Hainan Airlines'),
	('Garuda Indonesia'),
	('EVA Air')

insert into Ciudad(nombre) values
	('Montevideo'),
    ('Madrid'),
    ('Londres'),
    ('Paris'),
    ('Marsella'),
    ('Roma'),
    ('Genova'),
    ('Norwich'),
    ('Hong Kong'),
    ('Tokio'),
    ('Buenos Aires'),
    ('Moscu'),
    ('Rio de Janeiro'),
    ('San Pablo'),
    ('Barcelona'),
    ('Milan'),
    ('Nueva York'),
    ('Los Angeles'),
    ('La Habana'),
    ('Ciudad de Mexico'),
	('Tacuarembo'),
	('Jericoacoara'),
	('San Petesburgo')

insert into Vuelo(idvueloorigen,idvuelodestino,origen,destino,horario_partida,horario_llegada,precio) values
	(1,6,'Montevideo','Roma','12:00:00','20:00:00','3500'),
	(2,4,'Madrid','Paris','21:00:00','03:00:00','1000'),
	(7,6,'Genova','Roma','22:00:00','02:00:00','1500'),
	(6,3,'Roma','Londres','22:00:00','02:00:00','1500'),
	(14,2,'San Pablo','Madrid','22:00:00','06:00:00','5000'),
	(19,5,'La Habana','Marsella','10:00:00','20:00:00','7000'),
	(20,14,'Ciudad de Mexico','San Pablo','18:00:00','24:00:00','4500'),
	(17,19,'Nueva York','La Habana','14:00:00','17:00:00','2000'),
	(13,15,'Rio de Janeiro','Barcelona','15:00:00','21:00:00','6000'),
	(1,18,'Montevideo','Los Angeles','11:00:00','20:00:00','6500'),
	(1,16,'Montevideo','Milan','20:00:00','06:00:00','5500'),
	(3,11,'Londres','Buenos Aires','00:00:00','07:00:00','4500'),
	(13,6,'Rio de Janeiro','Roma','02:00:00','12:00:00','7000'),
	(21,1,'Tacuarembo','Montevideo','12:00:00','12:30:00','1000'),
	(11,12,'Buenos Aires','Moscu','18:30:00','07:45:00','12000'),
	(1,22,'Montevideo','Jericoacoara','07:00:00','19:00:00','3200'),
	(23,1,'San Petesburgo','Montevideo','17:45:00','05:15:00','8000')
	(22,16,'Jericoacoara','Milan','10:00:00','15:00:00','5500'),
	(3,15,'Londres','Barcelona','01:00:00','03:00:00','4500'),
	(15,1,'Barcelona','Montevideo','04:00:00','14:00:00','4500'),
	(1,11,'Montevideo','Buenos Aires','16:00:00','17:00:00','4500')

insert into ViajeVendido(idvueloorigen,idvuelodestino,origenVuelo,destinoVuelo,horario_partida,horario_llegada,ciEmpleado,ciCliente,escala,montoVenta,fechaCompra,nombreAero,cantEscala) values
	(1,18,'Montevideo','Los Angeles','18:00:00','24:00:00','11111111','12345678','1','6500','23/05/2019','Montevideo Airlines',1),
	(2,4,'Madrid','Paris','21:00:00','03:00:00','44444444','87654321','0','1000','25/03/2019','Fly Emirates',0),
	(19,5,'La Habana','Marsella','10:00:00','20:00:00','33333333','23648972','0','1500','19/05/2019','Singapore Airlines',0),
	(7,3,'Genova','Londres','22:00:00','02:00:00','44444444','12345323','1','1000','29/12/2018','Qatar Airways',1),
	(13,6,'Rio de Janeiro','Roma','02:00:00','12:00:00','55555555','12312348','0','7000','01/01/2019','Latam',0),
	(13,15,'Rio de Janeiro','Barcelona','15:00:00','21:00:00','22222222','12312348','1','7000','01/01/2019','Latam',2),
	(1,16,'Montevideo','Milan','22:00:00','06:00:00','11111111','59756426','1','5500','14/07/2012','Pluna',1),
	(20,14,'Ciudad de Mexico','San Pablo','18:00:00','24:00:00','33333333','23698754','0','4500','29/02/2012','Latam',0),
	(17,19,'Nueva York','La Habana','18:00:00','24:00:00','66666666','23698754','0','4500','29/02/2012','Latam',0),
	(3,11,'Londres','Buenos Aires','00:00:00','07:00:00','33333333','36598789','1','4500','18/04/2019','Fly Emirates',2),
	(21,1,'Tacuarembo','Montevideo','12:00:00','12:30:00','11111111','49863875','0','1000','18/04/2019','Fly Emirates',0)
		
insert into FormadePago(ciCliente,debito,credito) values
	('12345678','0','1'),
	('87654321','1','0'),
	('23648972','0','1'),
	('12345323','1','0'),
	('12312348','1','0'),
	('12312348','1','0'),
	('62346879','0','1'),
	('59756426','1','0'),
	('23698754','0','1'),
	('23698754','0','1'),
	('36598789','1','0'),
	('49863875','1','0')
	
insert into vuelovv(idViajeVen,idV) values
	(1,1),
	(1,5),
	(2,2),
	(3,5),
	(4,3),
	(4,4),
	(5,13),
	(6,9),
	(7,15),
	(7,18),
	(8,7),
	(9,8),
	(10,19),
	(10,20),
	(10,21),
	(11,14)