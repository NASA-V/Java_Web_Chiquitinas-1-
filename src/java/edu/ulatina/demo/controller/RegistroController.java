/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.demo.controller;

import edu.ulatina.demo.service.ClienteTO;
import edu.ulatina.demo.service.ServicioCliente;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "ClienteTO")
@SessionScoped
public class RegistroController {

    private String cedula;
    private String nombre;
    private String telefono;
    private String direccion;
    private String correo;
    private String pass;

    public RegistroController() {
    }

    public void Registrar() {
        ServicioCliente sc = new ServicioCliente();
        ClienteTO clienteTO = new ClienteTO();

        clienteTO.setCedula(cedula);
        clienteTO.setNombre(nombre);
        clienteTO.setTelefono(telefono);
        clienteTO.setDireccion(direccion);
        clienteTO.setCorreo(correo); 
        clienteTO.setPass(pass);

        if(clienteTO !=null){
            
        sc.insertarBaseDatos(clienteTO);
        redireccionar("/faces/index.xhtml");
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

}
