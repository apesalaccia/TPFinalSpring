package co.com.worldoffice.entities;

import java.util.Date;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SHOPPINGCART")
@Builder
public class ShoppingCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idCart;

    @Column(name = "CURTOMER")
    private long IdCustomer;

    @CreationTimestamp
    @Column(name = "DATECART")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "STATUS")
    private String status;

}