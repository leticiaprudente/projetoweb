package br.gov.sp.fatec.lab4.entities;

//import lombok.Data;

import javax.persistence.*;

//heran�a, indica que utiliza mais de uma tabela no banco, uma para cada classe filha
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "pag_pagamento")
//@Data
@AttributeOverride(name = "id", column = @Column(name = "pag_id"))
public abstract class Pagamento extends Identificador {

    protected Double valor;

    @ManyToOne
    @JoinColumn(name = "ped_id")
    protected Pedido pedido;

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
