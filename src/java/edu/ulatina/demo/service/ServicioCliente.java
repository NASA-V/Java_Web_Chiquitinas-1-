/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.demo.service;

import edu.ulatina.demo.controller.LoginController;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class ServicioCliente extends Servicio {

    Connection conexion = null;

//    public List<ClienteTO> demeClientes() {
//        
//        List<ClienteTO> listaRetorno = new ArrayList<>();
//        
//        for (int i=0; i<=100; i++) {
//            ClienteTO clienteTO = new ClienteTO(i, "11111111", "Juan Mora "+ i, "88349999", "Cartago");
//            listaRetorno.add(clienteTO);
//        }
//        
//        
//        return listaRetorno;
//    }
    public void insertarBaseDatos(ClienteTO clienteTO) {

        PreparedStatement ps = null;

        try {

            LoginController login = new LoginController();

            Connection conn = super.getConexion();
            ps = conn.prepareStatement("INSERT INTO Cliente(Nombre,Cedula, Direccion, Telefono,Contraseña, Correo) VALUES(?,?,?,?,?,?)");

            ps.setString(1, clienteTO.getNombre());
            ps.setString(2, clienteTO.getCedula());
            ps.setString(3, clienteTO.getDireccion());
            ps.setString(4, clienteTO.getTelefono());
            ps.setString(5, clienteTO.getPass());
            ps.setString(6, clienteTO.getCorreo());
            ps.execute();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                    FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.FACES_MESSAGES, "Creado Cliente"));

                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<OrdenTO> demeOrdenes(int cliente) {
        List<OrdenTO> listaRetorno = new ArrayList<>();
        Connection conn = getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //Paso 3 (Preparar)
            //super.conectar();
            ps = conn.prepareStatement("SELECT id,Fecha_Orden, Monto_Total, Cliente FROM Orden Cliente = ?'");
            ps.setInt(1, cliente);
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

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            //super.desconectar();

        }
        return listaRetorno;

    }

    public ClienteTO Corroborar(String Correo, String Contra) {
        Connection conn = getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        ClienteTO clienteTO = null;

        try {
            ps = conn.prepareStatement("SELECT id, Nombre, Cedula, Direccion, Telefono, nomRepresentante, Contrasenna, Correo"
                    + "FROM Cliente"
                    + "WHERE Contrasenna = ?"
                    + "AND"
                    + "Correo = ?");
            ps.setString(1, Correo);
            ps.setString(2, Contra);

            rs = ps.executeQuery();

            while (rs.next()) {
                clienteTO = new ClienteTO();
                clienteTO.setNombre(rs.getString("Nombre"));
                clienteTO.setCedula(rs.getString("Cedula"));
                clienteTO.setDireccion(rs.getString("Direccion"));
                clienteTO.setTelefono(rs.getString("Telefono"));
                clienteTO.setPass(rs.getString("Contrasenna"));
                clienteTO.setCorreo(rs.getString("Correo"));

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
        return clienteTO;

    }

//    
//        try {
//            //Paso 3 (Preparar)
//            //super.conectar();
//            ps = conn.prepareStatement("SELECT count(correo) FROM Cliente where Correo = ? AND Contrasenna=?");
//        ps.setString(1, Correo);
//        ps.setString(2, Contra);
//
//        rs = ps.executeQuery();
//
//        //Paso 4 (Ejectuar)
//        while (rs.next()) {
//
//            int count = rs.getInt("count(correo)");
////                System.out.println(count);
//
//            if (count > 1) {
//                ps = conn.prepareStatement("SELECT id FROM Cliente where Correo = ? AND Contrasenna=?");
//                ps.setString(1, Correo);
//                ps.setString(2, Contra);
//
//                rs = ps.executeQuery();
//
//                if (rs.next()) {
//                    int id = rs.getInt("id");
//
//                    clienteTO.setId(id);
//                }
//            }
//            //System.out.println("El valor del ID es; " + rs.getString("NOMBRE"));
//            //System.out.println("El valor del ID es; " + rs.getString("DIRECCION"));
//        }
//
//        // System.out.println("El valor del ID es; " + rs.getInt("ID"));
//    }
//    catch (Exception ex
//
//    
//        ) {
//            ex.printStackTrace();
//        //Paso 5 (Cerrar)  
//    }
//
//    
//        finally {
//            try {
//            if (ps != null && !ps.isClosed()) {
//                ps.close();
//            }
//            if (rs != null && !rs.isClosed()) {
//                rs.close();
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        //super.desconectar();
//
//    }
//    return clienteTO ;
//}

}
