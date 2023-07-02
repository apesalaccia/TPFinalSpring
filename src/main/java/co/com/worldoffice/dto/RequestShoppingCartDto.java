package co.com.worldoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestShoppingCartDto {

    private long IdCustomer;
    private long idProdut;
    private Integer amount;

}