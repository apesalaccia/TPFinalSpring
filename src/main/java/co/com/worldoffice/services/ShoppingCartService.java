package co.com.worldoffice.services;

import org.springframework.stereotype.Service;

import co.com.worldoffice.dto.RequestShoppingCartDto;
import co.com.worldoffice.dto.ResponseShoppingCartDto;

@Service
public interface ShoppingCartService {

    void addProductToCart(RequestShoppingCartDto dto);

    ResponseShoppingCartDto getShoppingCart(long idCustomer);

    void cancelShoppingCart(long idCustomer);

    ResponseShoppingCartDto toBuy(Integer idCustomer);

}