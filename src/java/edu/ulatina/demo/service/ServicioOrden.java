/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.demo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User1
 */
public class ServicioOrden extends Servicio{
    
    
    public List<OrdenTO> demeOrdenesCliente(int idCliente) {
        List<OrdenTO> listaRetorno = new ArrayList<>();
        Connection conn = getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //Paso 3 (Preparar)
            //super.conectar();
            ps = conn.prepareStatement("SELECT id,Fecha_Orden, Monto_Total, Cliente FROM Orden where Cliente = ?");
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();


            //Paso 4 (Ejectuar)
            while (rs.next()) {

                int id = rs.getInt("id");
                double monto = rs.getDouble("Monto_Total");
                String fecha = rs.getString("Fecha_Orden");
                int Cliente = rs.getInt("Cliente");

                OrdenTO personaTO = new OrdenTO();
                personaTO.setIdOrden(id);
                personaTO.setFechaOrden(fecha);
                personaTO.setMontoTotal(monto);
                personaTO.setIdCliente(Cliente);
                
                listaRetorno.add(personaTO);

                // System.out.println("El valor del ID es; " + rs.getInt("ID"));
                //System.out.println("El valor del ID es; " + rs.getString("NOMBRE"));
                //System.out.println("El valor del ID es; " + rs.getString("DIRECCION"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //Paso 5 (Cerrar)  
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }

            } catch(SQLException ex){
                ex.printStackTrace();
            }
            //super.desconectar();

        }
        return listaRetorno;

    }
    
     public List<OrdenTO> demeOrdenes() {
        List<OrdenTO> listaRetorno = new ArrayList<>();
        Connection conn = super.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //Paso 3 (Preparar) 
            //super.conectar();
            ps = conn.prepareStatement("SELECT id,Fecha_Orden, Monto_Total, Cliente FROM Orden");
            rs = ps.executeQuery();

            //Paso 4 (Ejectuar)
            while (rs.next()) {

                int idOrden = rs.getInt("IDORDEN");
                String fecha = rs.getString("FECHA");
                int monto = rs.getInt("MONTO");
                int idCliente = rs.getInt("IDCLIENTE");
             

                OrdenTO ordenTO = new OrdenTO();
                ordenTO.setIdOrden(idOrden);
                ordenTO.setFechaOrden(fecha);
                ordenTO.setMontoTotal(monto);
                ordenTO.setIdCliente(idCliente);
               
                listaRetorno.add(ordenTO);
            
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //Paso 5 (Cerrar)  
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            //super.desconectar();

        }
        return listaRetorno;

    }
    
    
}
