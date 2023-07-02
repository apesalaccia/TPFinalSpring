package co.com.worldoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailPurchaseDto {

    private long idDetail;

    private long idProduct;

    private long idCart;

    private Integer amount;

    private double price;
}