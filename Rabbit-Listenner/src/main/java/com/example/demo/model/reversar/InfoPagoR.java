package com.example.demo.model.reversar;

import java.util.Date;

public class InfoPagoR {
    private Date fechaTransaccion;
    private InstruccionesPagoR instruccionesPago;

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public InstruccionesPagoR getInstruccionesPago() {
        return instruccionesPago;
    }

    public void setInstruccionesPago(InstruccionesPagoR instruccionesPago) {
        this.instruccionesPago = instruccionesPago;
    }
}
