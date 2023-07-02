package co.com.worldoffice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.worldoffice.dto.ProductDto;

@Service
public interface ProductService {

    List<ProductDto> getAllProduct(String name, Integer price, String trademark);

    ProductDto getProductById(long idProduct);

    void saveOrUpdate(ProductDto dto);

}