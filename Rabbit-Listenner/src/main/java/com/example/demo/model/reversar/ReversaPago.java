package com.example.demo.model.reversar;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = ReversaPago.class)
public class ReversaPago {
    private PagoR pago;
    private CorresponsalR corresponsal;

    public PagoR getPago() {
        return pago;
    }

    public void setPago(PagoR pago) {
        this.pago = pago;
    }

    public CorresponsalR getCorresponsal() {
        return corresponsal;
    }

    public void setCorresponsal(CorresponsalR corresponsal) {
        this.corresponsal = corresponsal;
    }
}
