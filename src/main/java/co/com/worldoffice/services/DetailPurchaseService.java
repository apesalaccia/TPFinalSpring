package co.com.worldoffice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.worldoffice.dto.DetailPurchaseDto;

@Service
public interface DetailPurchaseService {

    DetailPurchaseDto save(DetailPurchaseDto dto);

    List<DetailPurchaseDto> findByIdCart(long idCart);

    void deleteProductInCart(long id);

}