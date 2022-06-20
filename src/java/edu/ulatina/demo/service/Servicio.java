package edu.ulatina.demo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Servicio {

    Connection conexion = null;

    
    
    private void conectar() {

        try {
            //Paso 1 (Registrar)
            Class.forName("com.mysql.cj.jdbc.Driver");

            try {
            //Paso 2 (Conectar)
                this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/default", "root", "admin");

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }
    
    protected Connection getConexion(){
        try {
            if(this.conexion == null || this.conexion.isClosed()) {
                this.conectar();
            }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         return this.conexion;
    }
    
    public void desconectar() {
        
    try {
        if(conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }  catch (SQLException ex) { 
       ex.printStackTrace();
    }
    
    
    
    }
}
