package co.com.worldoffice.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "DETAILPURCHASE")
public class DetailPurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idDetail;

    @Column(name = "IDPRODUCT")
    private long idProduct;

    @Column(name = "IDCART")
    private long idCart;

    @Column(name = "AMOUNT")
    private Integer amount;

    @Column(name = "PRICE")
    private double price;

}