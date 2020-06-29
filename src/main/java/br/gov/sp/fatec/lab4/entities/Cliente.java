package br.gov.sp.fatec.lab4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//heran�a, indica que s� utiliza uma tabela no banco, mesmo para as classes filhas
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//diferencia o tipo de dado que ser� inserido na tabela (pf - pessoa f�sica ou pj - pessoa jur�dica)
@DiscriminatorColumn
//indica que � uma entidade
@Entity
//indica o nome da tabela no banco, que � diferente do nome da Classe
@Table(name = "cli_cliente")
//anota��o do lombok , preenche os getters, setters e construtores
@Data
//'sobrescreve o nome do atributo'
@AttributeOverride(name = "id", column = @Column(name = "cli_id"))
public abstract class Cliente extends Identificador {

    protected String nome;
    protected String endereco;
    
    //um cliente pode ter muitos pedidos
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    //lista de pedidos
    protected List<Pedido> pedidos = new ArrayList<>();
}
