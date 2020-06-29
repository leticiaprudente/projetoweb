package br.gov.sp.fatec.lab4.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ped_pedido")
@Data
@AttributeOverride(name = "id", column = @Column(name = "ped_id"))
public class Pedido extends Identificador{

	//muitos pedidos podem pertencer a um cliente
    @ManyToOne
    @JoinColumn(name = "cli_id")
    private Cliente cliente;
    
    
    @ManyToMany
    @JoinTable(name = "item_pedido",
            joinColumns =
                    { @JoinColumn(name = "ped_id")},
    inverseJoinColumns = { @JoinColumn(name = "ite_id")})
    private List<Item> items = new ArrayList<>();
    //um pedido pode ter vários pagamentos (mais de 1 cartão/dinheiro)
    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    private List<Pagamento> pagamentos = new ArrayList<>();
}
