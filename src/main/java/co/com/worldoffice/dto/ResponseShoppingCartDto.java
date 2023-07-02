package co.com.worldoffice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseShoppingCartDto {

    private ShoppingCartDto shoppingCartDto;
    private List<ProductDto> products;

}