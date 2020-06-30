package br.gov.sp.fatec.lab4.entities;

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
//'sobrescreve o nome do atributo'
@AttributeOverride(name = "id", column = @Column(name = "cli_id"))
public abstract class Cliente extends Identificador {

    protected String nome;
    protected String endereco;
    
    //um cliente pode ter muitos pedidos
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    //lista de pedidos
    protected List<Pedido> pedidos = new ArrayList<>();

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
