/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.demo.controller;

//import com.mysql.cj.xdevapi.Client;
import edu.ulatina.demo.service.ClienteTO;

import edu.ulatina.demo.service.OrdenTO;
import edu.ulatina.demo.service.Servicio;

import edu.ulatina.demo.service.ServicioCliente;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger; 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

/**
 *
 * @author bernal
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController extends Servicio implements Serializable  {
    private String correo;
    private String pass;
    private List<ClienteTO> lista1 = new ArrayList<>();
    private boolean validar;
    private int id;

    private List<OrdenTO> lista2 = new ArrayList<>();

    private ClienteTO clienteTO = new ClienteTO();
    
    

    public LoginController() {
    }

    public void ingresar() {
//        System.out.println("+++++++ El usuario digitado es: " + this.correo);
//        System.out.println("+++++++ El password digitado es: " + this.pass);
//        if (this.correo.equals("diego") && this.pass.equals("1")) {
//            ServicioCliente servicioCliente = new ServicioCliente();
//            this.listaClientes = servicioCliente.demeClientes();
//            this.redireccionar("/faces/bienvenida.xhtml");
//        } else {
//            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_WARN, "Campos inválidos", "El usuario o contraseña no son correctos"));
//        }
       

           ServicioCliente servicioCliente = new ServicioCliente();
//        this.clienteTO = servicioCliente.demeCliente(correo, pass);
//            this.clienteTO.setId(servicioCliente.Corroborar(this.correo, this.pass).getId());
           this.clienteTO = servicioCliente.Corroborar(this.correo, this.pass);
        
        if (this.clienteTO != null) {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Bienvenida Usuario"));
            this.redireccionar("/faces/bienvenida.xhtml");
       
        }else {
          FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_WARN, "Campos inválidos", "El usuario o contraseña no son correctos"));
       
        }
        

    }
    
     public void redireccionar(String ruta) {
        HttpServletRequest request;
        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {

        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    

  
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<ClienteTO> getLista1() {
        return lista1;
    }

    public void setLista1(List<ClienteTO> lista1) {
        this.lista1 = lista1;
    }

    public List<OrdenTO> getLista2() {
        return lista2;
    }

    public void setLista2(List<OrdenTO> lista2) {
        this.lista2 = lista2;
    }

    public ClienteTO getClienteTO() {
        return clienteTO;
    }

    public void setClienteTO(ClienteTO clienteTO) {
        this.clienteTO = clienteTO;
    }

    
    
    

}
