
//package javaapplication3;

import java.sql.*;
import java.util.Scanner;
import org.postgresql.Driver;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JavaApplication3 {
    
    public static void main(String[] args) throws Exception {
        menuGeneral();
        
    }
    
    public static void conectarCrearBase() throws Exception {
        Connection con = null;
        Statement stmt = null;
        try{
            String driver = "org.postgresql.Driver";
            String url = "jdbc:postgresql://localhost:5433/postgres";
            String user = "postgres";
            String pass = "ale";
            Class.forName("org.postgresql.Driver");
            
            System.out.println("Conectando al servidor localhost de postgre...");
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Creando base de datos ManejadorBD...");
            stmt = con.createStatement();
            String sql = "CREATE DATABASE ManejadorBD";
            stmt.executeUpdate(sql);
            System.out.println("Base de datos creada con exito...");
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){}
            try{
                if(con!=null)
                    con.close();
            }catch(SQLException se){
                System.out.println(se.getMessage());
            }
        }
        System.out.println("Chauu!");
    }
    
    public static Connection getConnection() throws Exception {
        try{
            String driver = "org.postgresql.Driver";
            String url = "jdbc:postgresql://localhost:5433/postgres";
            String user = "postgres";
            String pass = "ale";
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url,user,pass);
            //System.out.println("Conectado!");
            return conn;
        }catch(Exception e){
            System.out.println("Error "+e);
        }
        return null;
    }//Alejandro
    
    public static Connection getConnection1() throws Exception {
        try{
            String driver = "org.postgresql.Driver";
            String url = "jdbc:postgresql://localhost:5433/postgres";
            String user = "postgres";
            String pass = "ale";
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url,user,pass);
            //System.out.println("Conectado!");
            return conn;
        }catch(Exception e){
            System.out.println("Error "+e);
        }
        return null;
    }//Joaquin
    
    public static void createTable() throws Exception{
        
        //Creo la tabla Empleado
        try{
            Connection con = getConnection();
            PreparedStatement createEmpleado = con.prepareStatement("CREATE TABLE Empleado"
                + "(idE SERIAL PRIMARY KEY,"
                + "ciE varchar unique NOT NULL,"
                + "nombre varchar(15) NOT NULL,"
                + "apellido varchar(25) NOT NULL,"
                + "fechaNac date NOT NULL "
                + ");");
            createEmpleado.executeUpdate();
                       
        }catch(Exception e){
            System.out.println("Error" + e);
        }finally{
            System.out.println("Se ha creado la tabla 'Empleado' con exito");
        }
        
        //Creo la tabla cliente
        try{
            Connection con = getConnection();
            PreparedStatement createCliente = con.prepareStatement("CREATE TABLE Cliente"
                + "(idC SERIAL PRIMARY KEY,"
                + "ciC varchar unique NOT NULL,"
                + "nombre varchar(15) NOT NULL,"
                + "apellido varchar(25) NOT NULL,"
                + "fechaNac date NOT NULL "
                + ");");
            createCliente.executeUpdate();
        }catch(Exception e){
            System.out.println("Error " + e);
        }finally{
            System.out.println("Se ha creado la tabla 'Cliente' con exito");
        }

        //Creo la tabla Aerolinea
        try{
            Connection con = getConnection();
            PreparedStatement createAerolinea = con.prepareStatement("CREATE TABLE Aerolinea"
                + "(idAerolinea SERIAL,"
                + "nombre varchar(25) unique NOT NULL,"
                + "PRIMARY KEY(idAerolinea,nombre)"
                + ");");
            createAerolinea.executeUpdate();
        }catch(Exception e){
            System.out.println("Error" + e);
        }finally{
            System.out.println("Se ha creado la tabla 'Aerolinea' con exito");
        }

        //Creo la tabla Ciudad
        try{
            Connection con = getConnection();
            PreparedStatement createCiudad = con.prepareStatement("CREATE TABLE Ciudad"
                + "(idCiudad SERIAL,"
                + "nombre varchar(25) unique NOT NULL,"
                + "PRIMARY KEY(idCiudad) "
                + ");");
            createCiudad.executeUpdate();
        }catch(Exception e){
            System.out.println("Error" + e);
        }finally{
            System.out.println("Se ha creado la tabla 'Ciudad' con exito");
        }
        
        //Creo la tabla Vuelo
        try{
            Connection con = getConnection();
            PreparedStatement createVuelo = con.prepareStatement("CREATE TABLE Vuelo"
                + "(idVuelo SERIAL unique,"
                + "idVueloOrigen int,"
                + "idVueloDestino int,"
                + "origen varchar(45) NOT NULL,"
                + "destino varchar(45) NOT NULL,"
                + "horario_partida time NOT NULL,"
                + "horario_llegada time NOT NULL,"
                + "precio real NOT NULL,"
                + "PRIMARY KEY (idVuelo,idVueloOrigen,idVueloDestino), "
                + "FOREIGN KEY (origen) references Ciudad(nombre),"
                + "FOREIGN KEY (destino) references Ciudad(nombre), "
                + "FOREIGN KEY (idVueloOrigen) references Ciudad(idCiudad),"
                + "FOREIGN KEY (idVueloDestino) references Ciudad(idCiudad)"
                + ");");
            createVuelo.executeUpdate();
        }catch(Exception e){
            System.out.println("Error" + e);
        }finally{
            System.out.println("Se ha creado la tabla 'Vuelo' con exito");
        }
        
        //Creo la tabla FormaDePago
        try{
            Connection con = getConnection();
            PreparedStatement createFormadePago = con.prepareStatement("CREATE TABLE FormadePago"
                + "(idPago SERIAL PRIMARY KEY,"
                + "ciCliente varchar NOT NULL,"
                + "debito boolean NOT NULL,"
                + "credito boolean NOT NULL,"
                + "FOREIGN KEY (ciCliente) references Cliente(ciC) "
                + ");");
            createFormadePago.executeUpdate();
        }catch(Exception e){
            System.out.println("Error" + e);
        }finally{
            System.out.println("Se ha creado la tabla 'FormadePago' con exito");
        }
        
        //Creo la tabla ViajeVendido
        try{
            Connection con = getConnection();
            PreparedStatement createViajeVendido = con.prepareStatement("CREATE TABLE ViajeVendido"
                +"(idViajeVendido SERIAL unique, "
                +"idVueloOrigen int,"
                +"idVueloDestino int,"
                +"origenVuelo varchar(45) NOT NULL, "
                +"destinoVuelo varchar(45) NOT NULL, "
                +"horario_partida time NOT NULL, "
                +"horario_llegada time NOT NULL, "
                +"ciEmpleado varchar(8) NOT NULL, "
                +"ciCliente varchar(8) NOT NULL, "
                +"escala boolean NOT NULL, "				
                +"montoVenta real NOT NULL, "
                +"fechaCompra date NOT NULL, "
                +"nombreAero varchar(25) NOT NULL, "
                +"cantEscala int NOT NULL, "
                +"PRIMARY KEY (idViajeVendido,idVueloOrigen,idVueloDestino), "
                +"FOREIGN KEY (origenVuelo) references Ciudad(nombre), "
                +"FOREIGN KEY (destinoVuelo) references Ciudad(nombre), "
                +"FOREIGN KEY (ciEmpleado) references Empleado(ciE), "
                +"FOREIGN KEY (nombreAero) references Aerolinea(nombre), "
                +"FOREIGN KEY (ciCliente) references Cliente (ciC),"
                +"FOREIGN KEY (idVueloOrigen) references Ciudad(idCiudad),"
                +"FOREIGN KEY (idVueloDestino) references Ciudad(idCiudad)"
                +");");
            createViajeVendido.executeUpdate();
        }catch(Exception e){
            System.out.println("Error" + e);
        }finally{
            System.out.println("Se ha creado la tabla 'ViajeVendido' con exito");
        }  

        try{
            Connection con = getConnection();
            PreparedStatement createVueloVV = con.prepareStatement("CREATE TABLE VueloVV"
                +"(idViajeVen int, "
                +"idV int,"
                +"PRIMARY KEY(idV,idViajeVen),"
                +"FOREIGN KEY (idV) references Vuelo(idVuelo),"
                +"FOREIGN KEY (idViajeVen) references ViajeVendido(idViajeVendido)"
                +");");
            createVueloVV.executeUpdate();
        }catch(Exception e){
            System.out.println("Error" + e);
        }finally{
            System.out.println("Se ha creado la tabla 'VueloVV' con exito");
        }  
    }  //Parte B
        
    //parte C    
    public static void insert() throws Exception{  
        try{
            Connection con = getConnection();
            PreparedStatement insertInto1 = con.prepareStatement("INSERT INTO Ciudad(idciudad, nombre) VALUES(1, 'Treinta y Tres');");
            insertInto1.executeUpdate();
            PreparedStatement insertInto2 = con.prepareStatement("INSERT INTO Cliente(cic,nombre,apellido,fechanac) VALUES('11111111','Esteban','Quito','05/06/2012');");
            insertInto2.executeUpdate();
            PreparedStatement insertInto3 = con.prepareStatement("INSERT INTO Aerolinea(idaerolinea, nombre) VALUES(1, 'Nippon Airlines');");
            insertInto3.executeUpdate();
            PreparedStatement insertInto4 = con.prepareStatement("INSERT INTO Empleado(ciE,nombre,apellido,fechanac)VALUES('99999999','Elba','Gallo','31/12/1951');");
            insertInto4.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error " + e);
        }
    }//INSERT   
    
    public static void update() throws Exception{    
        try {
            Connection con = getConnection();
            PreparedStatement update1 = con.prepareStatement("UPDATE Ciudad SET nombre = 'Montevideo' WHERE nombre='Treinta y Tres';");
            update1.executeUpdate();
            PreparedStatement update2 = con.prepareStatement("UPDATE Cliente SET nombre = 'Aquel' WHERE ciC='11111111';");
            update2.executeUpdate();
            PreparedStatement update3 = con.prepareStatement("UPDATE Aerolinea SET nombre = 'Singapore Airlines' WHERE nombre='Nippon Airlines';");
            update3.executeUpdate();
            PreparedStatement update4 = con.prepareStatement("UPDATE Empleado SET nombre = 'Este' WHERE ciE='99999999';");
            update4.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Error " + e);
        }
    }//UPDATE
         
    public static void delete() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement delete1 = con.prepareStatement("DELETE FROM Ciudad WHERE idciudad = 1;");
            delete1.executeUpdate();
            PreparedStatement delete2 = con.prepareStatement("DELETE FROM Cliente WHERE ciC = '11111111';");
            delete2.executeUpdate();
            PreparedStatement delete3 = con.prepareStatement("DELETE FROM Aerolinea WHERE idaerolinea = 1;");
            delete3.executeUpdate();
            PreparedStatement delete4 = con.prepareStatement("DELETE FROM Empleado WHERE ciE = '99999999';");
            delete4.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error " + e);
        }
    }//DELETE
       
    public static void select() throws Exception{
        try{
            Connection con = getConnection();
            Statement select1 = con.createStatement();
            ResultSet rs1;
            rs1 = select1.executeQuery("SELECT * FROM Aerolinea;");
            while(rs1.next()){
                System.out.println(rs1.getString("nombre"));
            }
            
            Statement select2 = con.createStatement();
            ResultSet rs2;
            rs2 = select2.executeQuery("SELECT * FROM Ciudad;");
            while(rs2.next()){
                System.out.println(rs2.getString("nombre"));
            }
              
            Statement select3 = con.createStatement();
            ResultSet rs3;
            rs3 = select3.executeQuery("SELECT * FROM Cliente;");
            while(rs3.next()){
                System.out.println(rs3.getString("nombre") + "\t" 
                + rs3.getString("apellido"));       
            }
 
            Statement select4 = con.createStatement();
            ResultSet rs4;
            rs4 = select4.executeQuery("SELECT * FROM Empleado");
            while(rs4.next()){
                System.out.println(rs4.getString("nombre") + "\t"
                + rs4.getString("apellido"));           
            }
        }catch(SQLException e){
            System.out.println("Error " + e);
        }
    }//SELECT
    //fin parte C
    
    public static void sentenciasConError() throws Exception{
        //INSERT
        try{
            Connection con = getConnection();
            PreparedStatement ins1 = con.prepareStatement( "INSERT INTO Aerolinea(nombre) VALUES (aerolinea1);");
            ins1.executeUpdate();
            PreparedStatement ins2 = con.prepareStatement( "INSERT INTO FormadePago(ciCliente, debito, credito) VALUES ('00000000','0','1');");
            ins2.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        //DELETE
        try{
            Connection con = getConnection();
            PreparedStatement del1 = con.prepareStatement( "DELETE FROM Cliente WHERE debito = true;");
            del1.executeUpdate();
            PreparedStatement del2 = con.prepareStatement( "DELETE FROM FormadePago WHERE ciCliente = '00000000';");
            del2.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        //DELETE
        try{
            Connection con = getConnection();            
            PreparedStatement update1 = con.prepareStatement( "UPDATE ViajeVendido SET origenvuelo = 'ciudad1' WHERE destinovuelo = 'ciudad1';");
            update1.executeUpdate();
            PreparedStatement update2 = con.prepareStatement( "UPDATE FormadePago SET debito=true,credito=false WHERE ciCliente = '9';");
            update2.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    } //Parte D
    
    public static void parteE() throws Exception{
    	try{
            Connection con = getConnection1();
            PreparedStatement update = con.prepareStatement("UPDATE Cliente SET ciC = '12345678' WHERE ciC = '200';");
            update.executeUpdate();
    	}catch(Exception e){
            System.out.println("Error "+e);
    	}
    	try{
            Connection con = getConnection1();
            PreparedStatement delefrom = con.prepareStatement("DELETE FROM ViajeVendido WHERE origenVuelo = 'ciudad1';");
            delefrom.executeUpdate();
    	}catch(Exception e){
            System.out.println("Error "+e);
    	}
    	try{
            Connection con = getConnection1();
            PreparedStatement altert = con.prepareStatement("ALTER TABLE Cliente DROP COLUMN nombre;");
            altert.executeUpdate(); 
    	}catch(Exception e){
            System.out.println("Error "+e);
    	}

    } //Hacer modificaciones con usuario SELECT
    
    public static void parteF() throws Exception{
        try{
            Connection con = getConnection1();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO ViajeVendido (idvueloorigen, idvuelodestino, origenVuelo, destinoVuelo, horario_partida, horario_llegada, ciEmpleado, ciCliente, escala, montoVenta, fechaCompra, nombreAero, cantEscala)"
                 + " VALUES (1, 2, 'ciudad1', 'ciudad2', '02:15:00', '10:30:00', '11', '1', false, 2500, '07/12/2017', 'aerolinea1', 0),"
                 + "(3, 4, 'ciudad3', 'ciudad4', '15:45:00', '22:30:00', '12', '2', true, 7900, '11/04/2019', 'aerolinea2', 1),"
                 + "(5, 6, 'ciudad5', 'ciudad6', '22:00:00', '02:30:00', '13', '3', true, 1800, '12/01/2019', 'aerolinea3', 1),"
                 + "(7, 8, 'ciudad7', 'ciudad8', '02:15:00', '10:30:00', '14', '4', true, 2500, '01/10/2018', 'aerolinea4', 2),"
                 + "(9, 10, 'ciudad9', 'ciudad10', '04:15:00', '12:15:00', '15', '5', true, 3200, '05/03/2019', 'aerolinea5', 2)"
                 + ";");
            pstmt.addBatch();  
            pstmt.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
       
        try{
            Connection con = getConnection();
            PreparedStatement pstmt1 = con.prepareStatement("DELETE FROM ViajeVendido WHERE origenVuelo='ciudad1' and destinoVuelo='ciudad2';");
            //pstmt1.addBatch();  
            pstmt1.executeUpdate();
            PreparedStatement pstmt2 = con.prepareStatement("DELETE FROM ViajeVendido WHERE montoVenta=7900;");
            //pstmt2.addBatch();  
            pstmt2.executeUpdate();
        }catch(Exception e){
            System.out.println("ERROR "+e.getMessage());
        }
    } //Ingresar cinco ViajeVendido y eliminar dos.
    
    public static void parteGTransacciones() throws Exception{
        Connection con = null;
        try{
            con = getConnection1();
            con.setAutoCommit(false);
            PreparedStatement ins1 = con.prepareStatement("INSERT INTO ViajeVendido (idvueloorigen,idvuelodestino,origenVuelo, destinoVuelo, horario_partida, horario_llegada, ciEmpleado, ciCliente,escala, montoVenta, fechaCompra, nombreAero, cantEscala) VALUES(4,6,'ciudad1','ciudad2','02:15:00','22:30:00','11','1',false,2000,'02/01/2018','aerolinea1',0);");
            ins1.executeUpdate();
            PreparedStatement ins2 = con.prepareStatement("INSERT INTO ViajeVendido (idvueloorigen,idvuelodestino,origenVuelo, destinoVuelo, horario_partida, horario_llegada, ciEmpleado, ciCliente,escala, montoVenta, fechaCompra, nombreAero, cantEscala) VALUES(10,20,'ciudad10','ciudad20','02:15:00','22:30:00','11','1',false,2500,'02/01/2018','aerolinea2',0);");
            ins2.executeUpdate();
            PreparedStatement ins3 = con.prepareStatement("INSERT INTO ViajeVendido (idvueloorigen,idvuelodestino,origenVuelo, destinoVuelo, horario_partida, horario_llegada, ciEmpleado, ciCliente,escala, montoVenta, fechaCompra, nombreAero, cantEscala) VALUES(30,25,'ciudad30','ciudad25','02:15:00','22:30:00','11','1',false,5200,'02/01/2018','aerolinea3',0);");
            ins3.executeUpdate();
            PreparedStatement ins4 = con.prepareStatement("INSERT INTO ViajeVendido (idvueloorigen,idvuelodestino,origenVuelo, destinoVuelo, horario_partida, horario_llegada, ciEmpleado, ciCliente,escala, montoVenta, fechaCompra, nombreAero, cantEscala) VALUES(11,55,'ciudad11','ciudad55','02:15:00','22:30:00','11','1',false,8050,'02/01/2018','aerolinea4',0);");
            ins4.executeUpdate();
            PreparedStatement ins5 = con.prepareStatement("INSERT INTO ViajeVendido (idvueloorigen,idvuelodestino,origenVuelo, destinoVuelo, horario_partida, horario_llegada, ciEmpleado, ciCliente,escala, montoVenta, fechaCompra, nombreAero, cantEscala) VALUES(15,60,'ciudad15','ciudad60','02:15:00','22:30:00','11','1',false,7900,'02/01/2018','aerolinea5',0);");
            ins5.executeUpdate();
            PreparedStatement pstmt1 = con.prepareStatement("DELETE FROM ViajeVendido WHERE origenVuelo='ciudad1' and destinoVuelo='ciudad2'");
            pstmt1.executeUpdate();
            PreparedStatement pstmt2 = con.prepareStatement("DELETE FROM ViajeVendido WHERE montoVenta=7900;");
            pstmt2.executeUpdate();
            con.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{  
                if(con!=null){
                    System.out.print("Se esta haciendo un roll back\n");
                    con.rollback();
                } 
            }catch(Exception ex) {
                System.out.println("Error"+ex);
            }
        }      
    } //Ingresar cinco ViajeVendido y eliminar dos.
    
    //parte H
    public static void agregarClientes() throws Exception{
        try{
            Connection con = getConnection();   
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Cliente (ciC, nombre, apellido, fechaNac) VALUES(?,?,?,'05/05/2005');");
            for (int i=1;i<=200;i++) {
                pstmt.setString(1, ""+i);
                pstmt.setString(2, "nombre"+i);
                pstmt.setString(3, "apellido"+i);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    } //200 clientes
     
    public static void agregarEmpleado() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Empleado (ciE, nombre, apellido, fechaNac) VALUES (?,?,?,'05/05/2005');");
            for (int i=1;i<=50;i++) {
                pstmt.setString(1, ""+i);
                pstmt.setString(2, "nombre"+i);
                pstmt.setString(3, "apellido"+i);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }   
    } //50 empleados
    
    public static void agregarAerolinea() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Aerolinea (nombre) VALUES(?)");
            for (int i=1;i<=50;i++){
                pstmt.setString(1, "aerolinea"+i);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    } //50 aerolíneas
    
    public static void agregarCiudades() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Ciudad (nombre) VALUES(?)");
            for (int i=1;i<=100;i++) {
                pstmt.setString(1, "ciudad"+i);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    } //100 ciudades
    
    public static void agregarFormadePago() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO FormadePago (ciCliente,debito,credito) VALUES(?,false,true);");           
            for(int i=1;i<=8;i++){
                pstmt.setString(1,""+i);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            
            PreparedStatement pstmt1 = con.prepareStatement("INSERT INTO FormadePago (ciCliente,debito,credito) VALUES(?,true,false);");           
            for(int i=9;i<=15;i++){
                pstmt1.setString(1,""+i);
                pstmt1.addBatch();                
            }
            pstmt1.executeBatch();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    } //15 formas de pago

    
    public static void agregarViajesVendidos() throws Exception{
        // 0 escalas
        try{
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO ViajeVendido(idvueloorigen, idvuelodestino, origenVuelo, destinoVuelo, horario_partida, horario_llegada, ciEmpleado, ciCliente,escala, montoVenta, fechaCompra, nombreAero, cantEscala)"
                    + " VALUES(?, ?, ?, ?, '02:15:00', '22:30:00', '11', ?, false, 2000, '02/01/2018', ?, 0);");
            for(int i=1;i<=80;i++){
                pstmt.setInt(1, i);   
                pstmt.setInt(2, (i+1));   
                pstmt.setString(3, "ciudad"+i);
                pstmt.setString(4, "ciudad"+(i+1));
                pstmt.setString(5, ""+i);
                if(i<=50){
                    pstmt.setString(6, "aerolinea"+i);
                }else{
                    pstmt.setString(6, "aerolinea"+(i-50));
                }
                pstmt.addBatch();
            }
            pstmt.executeBatch();
     
        }catch(Exception e){
            e.getStackTrace();
        } 
  
        // 1 escala
        try{
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO ViajeVendido(idvueloorigen, idvuelodestino, origenVuelo, destinoVuelo, horario_partida, horario_llegada, ciEmpleado, ciCliente,escala, montoVenta, fechaCompra, nombreAero, cantEscala)"
                    + " VALUES(?, ?, ?, ?, '02:15:00', '22:30:00', '12', ?, true, 2000, '02/01/2018', ?, 1);");
            for(int i=1;i<=100;i++){
                pstmt.setInt(1, i);   
                if(i<100){
                    pstmt.setInt(2, (i+1));   
                }else{
                    pstmt.setInt(2, ((i+1)-99));
                }
                pstmt.setString(3, "ciudad"+i);
                if(i<100){
                    pstmt.setString(4, "ciudad"+(i+1));
                }else{
                    pstmt.setString(4, "ciudad"+((i+1)-99));
                }
                pstmt.setString(5, ""+i);
                if(i<=50){
                    pstmt.setString(6, "aerolinea"+i);
                }else{
                    pstmt.setString(6, "aerolinea"+(i-50));
                }
                pstmt.addBatch();
            }
            pstmt.executeBatch();
     
        }catch(Exception e){
            e.getStackTrace();
        }
        // 2 escala
        try{
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO ViajeVendido(idvueloorigen, idvuelodestino, origenVuelo, destinoVuelo, horario_partida, horario_llegada, ciEmpleado, ciCliente,escala, montoVenta, fechaCompra, nombreAero, cantEscala)"
                    + " VALUES(?, ?, ?, ?, '02:15:00', '22:30:00', '13', ?, true, 2000, '02/01/2018', ?, 2);");
            for(int i=1;i<=100;i++){
                pstmt.setInt(1, i);   
                if(i<100){
                    pstmt.setInt(2, (i+1));   
                }else{
                    pstmt.setInt(2, ((i+1)-99));
                }
                pstmt.setString(3, "ciudad"+i);
                if(i<100){
                    pstmt.setString(4, "ciudad"+(i+1));
                }else{
                    pstmt.setString(4, "ciudad"+((i+1)-99));
                }
                pstmt.setString(5, ""+i);
                if(i<=50){
                    pstmt.setString(6, "aerolinea"+i);
                }else{
                    pstmt.setString(6, "aerolinea"+(i-50));
                }
                pstmt.addBatch();
            }
            pstmt.executeBatch();
     
        }catch(Exception e){
            e.getStackTrace();
        }
        // 3 escala
        try{
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO ViajeVendido(idvueloorigen, idvuelodestino, origenVuelo, destinoVuelo, horario_partida, horario_llegada, ciEmpleado, ciCliente,escala, montoVenta, fechaCompra, nombreAero, cantEscala)"
                    + " VALUES(?, ?, ?, ?, '02:15:00', '22:30:00', '14', ?, true, 2000, '02/01/2018', ?, 3);");
            for(int i=1;i<=80;i++){
                pstmt.setInt(1, i);   
                pstmt.setInt(2, (i+1));   
                pstmt.setString(3, "ciudad"+i);
                pstmt.setString(4, "ciudad"+(i+1));
                pstmt.setString(5, ""+i);
                if(i<=50){
                    pstmt.setString(6, "aerolinea"+i);
                }else{
                    pstmt.setString(6, "aerolinea"+(i-50));
                }
                pstmt.addBatch();
            }
            pstmt.executeBatch();
     
        }catch(Exception e){
            e.getStackTrace();
        }
    } //360 viajes vendidos
    
    public static void modificarDestino() throws Exception{
        try {
            Connection con = getConnection();
            PreparedStatement update1 = con.prepareStatement("UPDATE ViajeVendido SET destinoVuelo = 'ciudad5' WHERE ciEmpleado = '12';");
            update1.executeUpdate();
            PreparedStatement update2 = con.prepareStatement("UPDATE ViajeVendido SET ciCliente = '11' WHERE ciEmpleado = '12';");
            update2.executeUpdate();
            PreparedStatement update3 = con.prepareStatement("UPDATE ViajeVendido SET ciCliente = '11'  WHERE ciEmpleado='11';");
            update3.executeUpdate();
            PreparedStatement update4 = con.prepareStatement("UPDATE ViajeVendido SET destinoVuelo = 'ciudad5' WHERE ciEmpleado = '11';");
            update4.executeUpdate();
            PreparedStatement delete1 = con.prepareStatement("DELETE FROM ViajeVendido WHERE ciEmpleado = '13';");
            delete1.executeUpdate();
            PreparedStatement delete2 = con.prepareStatement("DELETE FROM ViajeVendido WHERE ciEmpleado = '14';");
            delete2.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Error "+e.getMessage());
        }
                
    } //Modificar la mitad de los Viajes vendidos y elimianr el resto
    //Fin parte H
    
    //parte H Transacciones
    public static void agregarClientesTransacciones() throws Exception{
        Connection con = null;
    	try{
            con = getConnection();
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Cliente (ciC, nombre, apellido, fechaNac) VALUES(?,?,?,'05/05/2005');");
            for (int i=1;i<=200;i++) {
                pstmt.setString(1, ""+i);
                pstmt.setString(2, "nombre"+i);
                pstmt.setString(3, "apellido"+i);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            con.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{  
                if(con!=null){
                    System.out.print("Se esta haciendo un roll back\n");
                    con.rollback();
                } 
            }catch(Exception ex) {
                System.out.println("Error"+ex);
            }
    	}
    }
    
    public static void agregarEmpleadoTransacciones() throws Exception{
        Connection con = null;
    	try{
            con = getConnection();
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Empleado (ciE, nombre, apellido, fechaNac) VALUES (?,?,?,'05/05/2005');");
            for (int i=1;i<=50;i++) {
                pstmt.setString(1, "1"+i);
                pstmt.setString(2, "nombre"+i);
                pstmt.setString(3, "apellido"+i);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            con.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{  
                if(con!=null){
                    System.out.print("Se esta haciendo un roll back\n");
                    con.rollback();
                } 
            }catch(Exception ex) {
                System.out.println("Error"+ex);
            }
    	}
    }
        
    public static void agregarAerolineaTransacciones() throws Exception{
        Connection con = null;
    	try{
            con = getConnection();
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Aerolinea (nombre) VALUES(?)");
            for (int i=1;i<=50;i++){
                pstmt.setString(1, "aerolinea"+i);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            con.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{  
                if(con!=null){
                    System.out.print("Se esta haciendo un roll back\n");
                    con.rollback();
                } 
            }catch(Exception ex) {
                System.out.println("Error"+ex);
            }
    	}
    }

    public static void agregarCiudadTransacciones() throws Exception{
        Connection con = null;
    	try{
            con = getConnection();
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Ciudad (nombre) VALUES(?)");
            for (int i=1;i<=100;i++) {
                pstmt.setString(1, "ciudad"+i);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            con.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{  
                if(con!=null){
                    System.out.print("Se esta haciendo un roll back\n");
                    con.rollback();
                } 
            }catch(Exception ex) {
                System.out.println("Error"+ex);
            }
    	}
    }

    public static void agregarFormadePagoTransacciones() throws Exception{
        Connection con = null;
    	try{
            con = getConnection();
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO FormadePago (ciCliente,debito,credito) VALUES(?,false,true);");           
            for(int i=1;i<=8;i++){
                pstmt.setString(1,""+i);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            
            PreparedStatement pstmt1 = con.prepareStatement("INSERT INTO FormadePago (ciCliente,debito,credito) VALUES(?,true,false);");           
            for(int i=9;i<=15;i++){
                pstmt1.setString(1,""+i);
                pstmt1.addBatch();                
            }
            pstmt1.executeBatch();
            con.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{  
                if(con!=null){
                    System.out.print("Se esta haciendo un roll back\n");
                    con.rollback();
                } 
            }catch(Exception ex) {
                System.out.println("Error"+ex);
            }
    	}
    }
    
    public static void agregarViajesVendidosTransacciones() throws Exception{
        Connection con = null;
    	// 0 escalas
        try{
            con = getConnection();
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO ViajeVendido(idvueloorigen, idvuelodestino, origenVuelo, destinoVuelo, horario_partida, horario_llegada, ciEmpleado, ciCliente,escala, montoVenta, fechaCompra, nombreAero, cantEscala)"
                    + " VALUES(?, ?, ?, ?, '02:15:00', '22:30:00', '11', ?, false, 2000, '02/01/2018', ?, 0);");
            for(int i=1;i<=80;i++){
                pstmt.setInt(1, i);   
                pstmt.setInt(2, (i+1));   
                pstmt.setString(3, "ciudad"+i);
                pstmt.setString(4, "ciudad"+(i+1));
                pstmt.setString(5, ""+i);
                if(i<=50){
                    pstmt.setString(6, "aerolinea"+i);
                }else{
                    pstmt.setString(6, "aerolinea"+(i-50));
                }
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            con.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{  
                if(con!=null){
                    System.out.print("Se esta haciendo un roll back\n");
                    con.rollback();
                } 
            }catch(Exception ex) {
                System.out.println("Error"+ex);
            }
    	}
        // 1 escalas
        try{
            con = getConnection();
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO ViajeVendido(idvueloorigen, idvuelodestino, origenVuelo, destinoVuelo, horario_partida, horario_llegada, ciEmpleado, ciCliente,escala, montoVenta, fechaCompra, nombreAero, cantEscala)"
                    + " VALUES(?, ?, ?, ?, '02:15:00', '22:30:00', '12', ?, true, 2000, '02/01/2018', ?, 1);");
            for(int i=1;i<=100;i++){
                pstmt.setInt(1, i);   
                if(i<100){
                    pstmt.setInt(2, (i+1));   
                }else{
                    pstmt.setInt(2, ((i+1)-99));
                }
                pstmt.setString(3, "ciudad"+i);
                if(i<100){
                    pstmt.setString(4, "ciudad"+(i+1));
                }else{
                    pstmt.setString(4, "ciudad"+((i+1)-99));
                }
                pstmt.setString(5, ""+i);
                if(i<=50){
                    pstmt.setString(6, "aerolinea"+i);
                }else{
                    pstmt.setString(6, "aerolinea"+(i-50));
                }
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            con.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{  
                if(con!=null){
                    System.out.print("Se esta haciendo un roll back\n");
                    con.rollback();
                } 
            }catch(Exception ex) {
                System.out.println("Error"+ex);
            }
    	}// 2 escalas
        try{
            con = getConnection();
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO ViajeVendido(idvueloorigen, idvuelodestino, origenVuelo, destinoVuelo, horario_partida, horario_llegada, ciEmpleado, ciCliente,escala, montoVenta, fechaCompra, nombreAero, cantEscala)"
                    + " VALUES(?, ?, ?, ?, '02:15:00', '22:30:00', '13', ?, true, 2000, '02/01/2018', ?, 2);");
            for(int i=1;i<=100;i++){
                pstmt.setInt(1, i);   
                if(i<100){
                    pstmt.setInt(2, (i+1));   
                }else{
                    pstmt.setInt(2, ((i+1)-99));
                }
                pstmt.setString(3, "ciudad"+i);
                if(i<100){
                    pstmt.setString(4, "ciudad"+(i+1));
                }else{
                    pstmt.setString(4, "ciudad"+((i+1)-99));
                }
                pstmt.setString(5, ""+i);
                if(i<=50){
                    pstmt.setString(6, "aerolinea"+i);
                }else{
                    pstmt.setString(6, "aerolinea"+(i-50));
                }
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            con.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{  
                if(con!=null){
                    System.out.print("Se esta haciendo un roll back\n");
                    con.rollback();
                } 
            }catch(Exception ex) {
                System.out.println("Error"+ex);
            }
    	}// 3 escalas
        try{
            con = getConnection();
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO ViajeVendido(idvueloorigen, idvuelodestino, origenVuelo, destinoVuelo, horario_partida, horario_llegada, ciEmpleado, ciCliente,escala, montoVenta, fechaCompra, nombreAero, cantEscala)"
                    + " VALUES(?, ?, ?, ?, '02:15:00', '22:30:00', '14', ?, true, 2000, '02/01/2018', ?, 3);");
            for(int i=1;i<=80;i++){
                pstmt.setInt(1, i);   
                pstmt.setInt(2, (i+1));   
                pstmt.setString(3, "ciudad"+i);
                pstmt.setString(4, "ciudad"+(i+1));
                pstmt.setString(5, ""+i);
                if(i<=50){
                    pstmt.setString(6, "aerolinea"+i);
                }else{
                    pstmt.setString(6, "aerolinea"+(i-50));
                }
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            con.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{  
                if(con!=null){
                    System.out.print("Se esta haciendo un roll back\n");
                    con.rollback();
                } 
            }catch(Exception ex) {
                System.out.println("Error"+ex);
            }
    	}
    }
    
    public static void modificarDestinoTransacciones() throws Exception{
        Connection con = null;
    	try{
            con = getConnection();
            con.setAutoCommit(false);
            PreparedStatement update1 = con.prepareStatement("UPDATE ViajeVendido SET destinovuelo = 'ciudad5' WHERE ciEmpleado='11';");
            update1.executeUpdate();
            PreparedStatement update2 = con.prepareStatement("UPDATE ViajeVendido SET ciC = '5' WHERE ciEmpleado='12';");
            update2.executeUpdate();
            PreparedStatement delete1 = con.prepareStatement("DELETE FROM ViajeVendido WHERE ciEmpleado = '13';");
            delete1.executeUpdate();
            PreparedStatement delete2 = con.prepareStatement("DELETE FROM ViajeVendido WHERE ciEmpleado = '14';");
            delete2.executeUpdate();
            con.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{  
                if(con!=null){
                    System.out.print("Se esta haciendo un roll back\n");
                    con.rollback();
                } 
            }catch(Exception ex) {
                System.out.println("Error"+ex);
            }
    	}
    }
    //fin parte H 
    public static void agregarAerolineaTE() throws Exception{
        try{
            long inicio, fin;
                inicio = System.currentTimeMillis();
                agregarAerolinea(); // llamamos a la tarea
                fin = System.currentTimeMillis();
                System.out.println("La funcion a demorado "+ ( fin - inicio ) +" milisegundos");
        }catch(Exception e){
            e.printStackTrace();
        }
    } 

    public static void agregarCiudadesTE() throws Exception{
        try{
            long inicio, fin;
                inicio = System.currentTimeMillis();
                agregarCiudades(); // llamamos a la tarea
                fin = System.currentTimeMillis();
                System.out.println("La funcion a demorado "+ ( fin - inicio ) +" milisegundos");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void agregarClientesTE() throws Exception{
        try{
            long inicio, fin;
                inicio = System.currentTimeMillis();
                agregarClientes(); // llamamos a la tarea
                fin = System.currentTimeMillis();
                System.out.println("La funcion a demorado "+ ( fin - inicio ) +" milisegundos");
        }catch(Exception e){
            e.printStackTrace();
        }
    } 

    public static void agregarEmpleadoTE() throws Exception{
        try{
            long inicio, fin;
                inicio = System.currentTimeMillis();
                agregarEmpleado(); // llamamos a la tarea
                fin = System.currentTimeMillis();
                System.out.println("La funcion a demorado "+ ( fin - inicio ) +" milisegundos");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void agregarFormadePagoTE() throws Exception{
        try{
            long inicio, fin;
                inicio = System.currentTimeMillis();
                agregarFormadePago(); // llamamos a la tarea
                fin = System.currentTimeMillis();
                System.out.println("La funcion a demorado "+ ( fin - inicio ) +" milisegundos");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void agregarViajesVendidosTE() throws Exception{
        try{
            long inicio, fin;
                inicio = System.currentTimeMillis();
                agregarViajesVendidos(); // llamamos a la tarea
                fin = System.currentTimeMillis();
                System.out.println("La funcion a demorado "+ ( fin - inicio ) +" milisegundos");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void modificarDestinoTE() throws Exception{
        try{
            long inicio, fin;
                inicio = System.currentTimeMillis();
                modificarDestino(); // llamamos a la tarea
                fin = System.currentTimeMillis();
                System.out.println("La funcion a demorado "+ ( fin - inicio ) +" milisegundos");
        }catch(Exception e){
            e.printStackTrace();
        }
    } 
    public static void agregarAerolineaTransaccionesTE() throws Exception{
        try{
            long inicio, fin;
            inicio = System.currentTimeMillis();
            agregarAerolineaTransacciones(); // llamamos a la tarea
            fin = System.currentTimeMillis();
            System.out.println("La funcion a demorado "+ ( fin - inicio ) +" milisegundos");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void agregarCiudadTransaccionesTE() throws Exception{
        try{
            long inicio, fin;
            inicio = System.currentTimeMillis();
            agregarCiudadTransacciones(); // llamamos a la tarea
            fin = System.currentTimeMillis();
            System.out.println("La funcion a demorado "+ ( fin - inicio ) +" milisegundos");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void agregarClientesTransaccionesTE() throws Exception{
        try{
            long inicio, fin;
            inicio = System.currentTimeMillis();
            agregarClientesTransacciones(); // llamamos a la tarea
            fin = System.currentTimeMillis();
            System.out.println("La funcion a demorado "+ ( fin - inicio ) +" milisegundos");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void agregarEmpleadoTransaccionesTE() throws Exception{
        try{
            long inicio, fin;
            inicio = System.currentTimeMillis();
            agregarEmpleadoTransacciones(); // llamamos a la tarea
            fin = System.currentTimeMillis();
            System.out.println("La funcion a demorado "+ ( fin - inicio ) +" milisegundos");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void agregarFormadePagoTransaccionesTE() throws Exception{
        try{
            long inicio, fin;
            inicio = System.currentTimeMillis();
            agregarFormadePagoTransacciones(); // llamamos a la tarea
            fin = System.currentTimeMillis();
            System.out.println("La funcion a demorado "+ ( fin - inicio ) +" milisegundos");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void agregarViajesVendidosTransaccionesTE() throws Exception{
        try{
            long inicio, fin;
            inicio = System.currentTimeMillis();
            agregarViajesVendidosTransacciones(); // llamamos a la tarea
            fin = System.currentTimeMillis();
            System.out.println("La funcion a demorado "+ ( fin - inicio ) +" milisegundos");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void modificarDestinoTransaccionesTE() throws Exception{
        try{
            long inicio, fin;
            inicio = System.currentTimeMillis();
            modificarDestinoTransacciones(); // llamamos a la tarea
            fin = System.currentTimeMillis();
            System.out.println("La funcion a demorado "+ ( fin - inicio ) +" milisegundos");
        }catch(Exception e){
            e.printStackTrace();
        }
    }                                   
                       
    public static void parteJ() throws Exception{
        try{
            Connection con = getConnection();
            Statement select1 = con.createStatement();
            ResultSet rs;
            rs = select1.executeQuery("SELECT * FROM ViajeVendido ORDER BY origenVuelo desc;");
            while(rs.next()){
                System.out.println(rs.getString("idvueloorigen")
                        +"\t"+     rs.getString("idvuelodestino")
                        +"\t"+     rs.getString("origenVuelo")
                        +"\t"+     rs.getString("destinoVuelo")
                        +"\t"+     rs.getString("horario_partida")
                        +"\t"+     rs.getString("horario_llegada")
                        +"\t"+     rs.getString("ciEmpleado")
                        +"\t"+     rs.getString("ciCliente")
                        +"\t"+     rs.getString("escala")
                        +"\t"+     rs.getString("montoVenta")
                        +"\t"+     rs.getString("fechaCompra")
                        +"\t"+     rs.getString("nombreAero")
                        +"\t"+     rs.getString("cantEscala"));
            }
        }catch(Exception e){
    		System.out.println("Error "+e);
    	}
    } //Select * From viajeVendido Orden DESC
    
    public static void parteL() throws Exception{
    	try{
    		Connection con = getConnection();
    		PreparedStatement index = con.prepareStatement("CREATE INDEX viajesv_idx ON ViajeVendido(origenVuelo);");
    		index.executeUpdate();
    		parteJ();
    	}catch(Exception e){
    		e.printStackTrace();
    	}

    }//Select * From viajeVendido con indice
    
    public static void tiempodeEjecucionParteL() throws Exception{
        try{
            long inicio, fin;
                inicio = System.currentTimeMillis();
                parteL(); // llamamos a la tarea
                fin = System.currentTimeMillis();
                System.out.println("La funcion a demorado "+ ( fin - inicio ) +" milisegundos");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void tiempodeEjecucionParteJ() throws Exception{
        try{
            long inicio, fin;
                inicio = System.currentTimeMillis();
                parteJ(); // llamamos a la tarea
                fin = System.currentTimeMillis();
                System.out.println("La funcion a demorado "+ ( fin - inicio ) +" milisegundos");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void parte6() throws Exception{
        try{
            Connection con = getConnection();//Aca va getConnection en vez de alepgAdmin
            String query = "{CALL par5()}";
            CallableStatement stmt = con.prepareCall(query);
        }catch(Exception e){
            System.out.println("Error "+e);
            //e.printStackTrace();
        }

    }
    public static void tiempodeEjecucionParte6() throws Exception{
        try{
            long inicio, fin;
            inicio = System.currentTimeMillis();
            parte6(); // llamamos a la tarea
            fin = System.currentTimeMillis();
            System.out.println("La funcion a demorado "+ ( fin - inicio ) +" milisegundos");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void parte8() throws Exception{
        Connection con = getConnection();
        //INSERT
        try{
            PreparedStatement ins1 = con.prepareStatement("INSERT INTO  Ciudad(nombre) VALUES ('Oslo');");
            ins1.executeUpdate();
            PreparedStatement ins2 = con.prepareStatement("INSERT INTO Aerolinea(nombre) VALUES ('Canada Airlines');");
            ins2.executeUpdate();
            PreparedStatement ins3 = con.prepareStatement("INSERT INTO ViajeVendido(idVueloOrigen,idVueloDestino,origenVuelo,destinoVuelo,horario_partida,horario_llegada,ciEmpleado,ciCliente,escala, montoVenta,fechaCompra,nombreAero,cantEscala) VALUES (1, 2, 'ciudad1', 'ciudad2', '10:00:00', '20:00:00', '11', '1', false, 2500, '05/05/2019', 'aerolinea2', 0);");
            ins3.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }

        //UPDATE
        try{
            PreparedStatement update = con.prepareStatement("UPDATE Ciudad SET idCiudad = 5500 WHERE nombre='Oslo';");
            update.executeUpdate();
            PreparedStatement update2 = con.prepareStatement("UPDATE Aerolinea SET nombre='aerolinea0' WHERE nombre = 'Canada Airlines';");
            update2.executeUpdate();
            PreparedStatement update3 = con.prepareStatement("UPDATE ViajeVendido SET montoVenta = 50000 WHERE cantEscala=0;");
            update3.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
      
        //DELETE
        try{
            PreparedStatement delete1 = con.prepareStatement("DELETE FROM Ciudad WHERE idCiudad = 5500;");
            delete1.executeUpdate();
            PreparedStatement delete3 = con.prepareStatement("DELETE FROM Aerolinea WHERE nombre = 'aerolinea0';");
            delete3.executeUpdate();
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM ViajeVendido WHERE cantEscala = 0;");
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }             
    } //parte8

    public static void borrarTablas() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement drop1 = con.prepareStatement("DROP TABLE vuelo cascade;");
            drop1.executeUpdate();
            PreparedStatement drop2 = con.prepareStatement("DROP TABLE viajevendido cascade;");
            drop2.executeUpdate();
            PreparedStatement drop3 = con.prepareStatement("DROP TABLE ciudad cascade;");
            drop3.executeUpdate();
            PreparedStatement drop4 = con.prepareStatement("DROP TABLE formadepago cascade;");
            drop4.executeUpdate();
            PreparedStatement drop5 = con.prepareStatement("DROP TABLE cliente cascade;");
            drop5.executeUpdate();
            PreparedStatement drop6 = con.prepareStatement("DROP TABLE empleado cascade;");
            drop6.executeUpdate();
            PreparedStatement drop7 = con.prepareStatement("DROP TABLE aerolinea cascade;");
            drop7.executeUpdate();
            PreparedStatement drop8 = con.prepareStatement("DROP TABLE vuelovv cascade;");
            drop8.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void menuPARTE4() throws Exception{
    	try{
	    Scanner sn = new Scanner(System.in);
	    boolean salir = false;
	    int opcion; //Guardaremos la opcion del usuario
	    
	    while(!salir){
	         
	        System.out.println("\n\tMENU PARTE 4\n");
	        System.out.println("1. (Parte A)");
	        System.out.println("2. (Parte B)");
	        System.out.println("3. (Parte C)");
	        System.out.println("4. (Parte D)");
	        System.out.println("5. (Parte E)");
	        System.out.println("6. (Parte F)");
	        System.out.println("7. (Parte G)");
	        System.out.println("8. (Parte H)");
	        System.out.println("9. (Parte I)");
	        System.out.println("10. (Parte J)");
	        System.out.println("11. (Parte K)");
	        System.out.println("12. (Parte L)");
                System.out.println("0. Salir");
               
                System.out.println("Escribe una de las opciones ");
	        opcion = sn.nextInt();
	         
	        switch(opcion){
	            case 1: //Parte A
	                conectarCrearBase();
	                System.out.println("Conexion exitosa...\n");
                break;
	            case 2: //Parte B
	                createTable();
	                System.out.println("Tablas creadas...\n");
                break;
	            case 3: //Parte C
	                menu3();
	                System.out.println("Sentencias INSERT, DELETE, UPDATE Y SELECT...\n");
                break;
	            case 4: //Parte D
	                sentenciasConError();
	                System.out.println("Sentencias con error...\n");
                break;
	            case 5: //Parte E
	              	//Sentencias de modificacion para usuario que solo tiene SELECT
	                parteE();
                break;
	            case 6: //PARTE F
                    parteF();
                break;
	            case 7: //Parte G
                    parteGTransacciones();
                break; //Parte H
                case 8:
                	menu8();
                    System.out.println("Bucle de autollenado...\n");
                break;
                case 9: //Parte I
                	menu9();
                break;
                case 10: //Parte J
                	tiempodeEjecucionParteJ();
                    System.out.println("Select parte J...\n");
                break;
                case 11: //Parte K
                	//TODO
                    System.out.println("Aca no va nada, es solo investigar indice\n");
                break;
                case 12: //Parte L
            	   tiempodeEjecucionParteL();
                   System.out.println("Select parte L...\n");
                break;
	            case 0:
	                System.out.println("Has salido...\n");  
                        salir=true;
                        break;
	            default:
	                System.out.println("Opcion Incorrecta! Seleccione otra.\n");
	                break;
	            }    
	        }
	    }catch(Exception e){
	    	System.out.println("Error "+e);
	    }
    } //Menu Parte 4 TAREA  
    
    public static void menu3() throws Exception{
    	try{
            Scanner sn = new Scanner(System.in);
	    boolean salir = false;
	    int opcion; //Guardaremos la opcion del usuario
	      
	    while(!salir){
	         
	        System.out.println("\n\tMENU PARTE 3\n");
	        System.out.println("1. (INSERT)");
	        System.out.println("2. (UPDATE)");
	        System.out.println("3. (DELETE)");
	        System.out.println("4. (SELECT)");
	        System.out.println("0. Salir");
		        
	        System.out.println("Escribe una de las opciones ");
	        opcion = sn.nextInt();
	         
	        switch(opcion){
	            case 1: //Parte A
                        insert();
	                System.out.println("INSERT Existoso");
	                break;
	            case 2: //Parte B
	                update();
	                System.out.println("UPDATE Existoso");
	                break;
	            case 3: //Parte C
	                delete();
	                System.out.println("DELETE Existoso");
	                break;
	            case 4: //Parte D
	                select();
	                System.out.println("SELECT Existoso");
	                break;
	            case 0:
	                System.out.println("Has salido...\n");  
	                salir=true;
	                break;
	            default:
	                System.out.println("Opcion Incorrecta! Seleccione otra.\n");
	                break;
	        }    
	    }
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    } //menu parte C
 
    public static void menu8() throws Exception{
    	try{
            Scanner sn = new Scanner(System.in);
            boolean salir = false;
    	    int opcion; //Guardaremos la opcion del usuario
    	    while(!salir){
    	        System.out.println("\n\tMENU PARTE 8\n");
    	        System.out.println("1. (Agregar Aerolineas)");
    	        System.out.println("2. (Agregar Ciudades)");
    	        System.out.println("3. (Agregar Clientes)");
    	        System.out.println("4. (Agregar Empleados)");
    	        System.out.println("5. (Agregar Formas de Pagos)");
    	        System.out.println("6. (Agregar Viajes Vendidos)");
    	        System.out.println("7. (Modificar Datos)");
    	        System.out.println("0. Salir");
    	        System.out.println("Escribe una de las opciones ");
    	        opcion = sn.nextInt();
    	        switch(opcion){
    	            case 1: //Parte A
    	                agregarAerolineaTE();
    	                System.out.println("Aerolineas agregadas...\n");
    	                break;
    	            case 2: //Parte B
    	                agregarCiudadesTE();
    	                System.out.println("Ciudades agregadas...\n");
    	                break;
    	            case 3: //Parte C
    	                agregarClientesTE();
    	                System.out.println("Clientes agregados...\n");
    	                break;
    	            case 4: //Parte D
    	                agregarEmpleadoTE();
    	                System.out.println("Empleados agregados...\n");
    	                break;
    	            case 5: //Parte E
    	                agregarFormadePagoTE();
    	                System.out.println("Formas de pagos agregadas...\n");
    	                break;
    	            case 6: //PARTE F
    	                agregarViajesVendidosTE();
    	                System.out.println("Viajes vendidos agregados...\n");
    	                break;
    	            case 7: //Parte G
    	                modificarDestinoTE();
    	                System.out.println("Modificar destinos...\n");
    	                break; //Parte H
    	            case 0:
    	                System.out.println("Has salido...\n");  
    	                salir=true;
    	                break;
    	            default:
    	                System.out.println("Opcion Incorrecta! Seleccione otra.\n");
    	                break;
    	        }    
            }
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    } //menu parte H

    public static void menu9() throws Exception{
    	try{
            Scanner sn = new Scanner(System.in);
            boolean salir = false;
            int opcion; //Guardaremos la opcion del usuario
	        while(!salir){
    	        System.out.println("\n\tMENU PARTE 9 TRANSACCIONES\n");
    	        System.out.println("1. (Agregar Aerolineas)");
    	        System.out.println("2. (Agregar Ciudades)");
    	        System.out.println("3. (Agregar Clientes)");
    	        System.out.println("4. (Agregar Empleados)");
    	        System.out.println("5. (Agregar Formas de Pagos)");
    	        System.out.println("6. (Agregar Viajes Vendidos)");
    	        System.out.println("7. (Modificar Datos)");
    	        System.out.println("0. Salir");
    	        System.out.println("Escribe una de las opciones ");
    	        opcion = sn.nextInt();
    	        switch(opcion){
    	            case 1: //Parte A
    	                agregarAerolineaTransaccionesTE();
    	                System.out.println("Aerolineas agregadas...\n");
    	                break;
    	            case 2: //Parte B
    	                agregarCiudadTransaccionesTE();
    	                System.out.println("Ciudades agregadas...\n");
    	                break;
    	            case 3: //Parte C
    	                agregarClientesTransaccionesTE();
    	                System.out.println("Clientes agregados...\n");
    	                break;
    	            case 4: //Parte D
    	                agregarEmpleadoTransaccionesTE();
    	                System.out.println("Empleados agregados...\n");
    	                break;
    	            case 5: //Parte E
    	                agregarFormadePagoTransaccionesTE();
    	                System.out.println("Formas de pagos agregadas...\n");
    	                break;
    	            case 6: //PARTE F
    	                agregarViajesVendidosTransaccionesTE();
    	                System.out.println("Viajes vendidos agregados...\n");
    	                break;
    	            case 7: //Parte G
    	                modificarDestinoTransaccionesTE();
    	                System.out.println("Modificar destinos...\n");
    	                break; //Parte H
    	            case 0:
    	                System.out.println("Has salido...\n");  
    	                salir=true;
    	                break;
    	            default:
    	                System.out.println("Opcion Incorrecta! Seleccione otra.\n");
    	                break;
    	        }    
	       }
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    } //menu parte H TRANSACCIONES

    public static void menuGeneral()throws Exception{
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
        
        while(!salir){
            System.out.println("\n\tMENU GENERAL\n");
            System.out.println("1. BORRAR TABLAS");
            System.out.println("4. (Parte 4)");
            System.out.println("6. (Parte 6)");
            System.out.println("8. (Parte 8)");
            System.out.println("0. Salir");
            System.out.println("Escribe una de las opciones ");
            opcion = sn.nextInt();
            switch(opcion){
                case 1: //Parte 4
                    borrarTablas();
                    System.out.println("Tablas Borradas...!");
                break;
                case 4: //Parte 4
                    menuPARTE4();
                    System.out.println("Saliste de la parte 4...!");
                break;
                case 6: //Parte 6
                    parte6();
                    System.out.println("Store Procedure exitoso...!");
                break;
                case 8: //Parte 8
                    parte8();        
                    System.out.println("Triggers realizados con exito...!");
                break;
                case 0: // salir
                    salir = true;
                break;
            }
        }
    } //Menu General
    
}