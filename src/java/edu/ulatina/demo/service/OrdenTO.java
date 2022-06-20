/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulatina.demo.service;

/**
 *
 * @author User1
 */
public class OrdenTO {
     private String fechaOrden;
   private int idOrden;
   private int idCliente;
   private double montoTotal;
    

    public OrdenTO() {
        this.fechaOrden = fechaOrden;
        this.montoTotal = montoTotal;
        this.idOrden = idOrden;
        this.idCliente = idCliente;
    }
    
    
    public String getFechaOrden() {
        return fechaOrden;
    }

   
    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }
        

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }


   
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    
}
