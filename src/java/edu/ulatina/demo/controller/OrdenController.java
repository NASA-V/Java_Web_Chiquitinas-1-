/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.demo.controller;

import edu.ulatina.demo.service.OrdenTO;
import edu.ulatina.demo.service.ServicioOrden;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author User1
 */


@ManagedBean(name = "OrdenController")
@ViewScoped
public class OrdenController {
    
    
    private List<OrdenTO> listaOrdenes = new ArrayList<>();
    @ManagedProperty("#{loginController}")
    private LoginController loginController;
    

    public List<OrdenTO> getListaOrdenes() {
        return listaOrdenes;
    }

    public void setListaOrdenes(List<OrdenTO> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }
@PostConstruct
    public void cargarDatos() {
 
        ServicioOrden servicioOrden = new ServicioOrden();

        this.listaOrdenes = servicioOrden.demeOrdenesCliente(loginController.getClienteTO().getId());

    }

    public OrdenController() {
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
    
    


    
}
