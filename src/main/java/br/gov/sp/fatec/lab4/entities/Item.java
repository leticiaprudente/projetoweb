package br.gov.sp.fatec.lab4.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ite_item")
@Data
@AttributeOverride(name = "id", column = @Column(name = "ite_id"))
public class Item extends Identificador{

    private String nome;
    private Double preco;
    
    //vários fornecedores podem ter o mesmo item
    //o item só tem 1 fornecedor
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "for_id")
    private Fornecedor fornecedor;

}
