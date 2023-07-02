package co.com.worldoffice.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.worldoffice.dto.DetailPurchaseDto;
import co.com.worldoffice.entities.DetailPurchaseEntity;
import co.com.worldoffice.exception.ResourceException;
import co.com.worldoffice.repositories.DetailPurchaseRepository;
import co.com.worldoffice.services.DetailPurchaseService;
import co.com.worldoffice.utils.DetailsPurchaseConverter;

@Service
public class DetailPurchaseServiceImpl implements DetailPurchaseService {

    private static final Logger log = LoggerFactory.getLogger(DetailPurchaseServiceImpl.class);

    @Autowired
    private DetailPurchaseRepository purchaseRepository;
    @Autowired
    private DetailsPurchaseConverter converter;
    private DetailPurchaseEntity detailEntity;
    List<DetailPurchaseEntity> listDetail;

    @Override
    public DetailPurchaseDto save(DetailPurchaseDto dto) {
        try {
            log.info("Agregando producto a carrito de compra...");
            detailEntity = purchaseRepository.save(DetailPurchaseEntity.builder().idCart(dto.getIdCart())
                    .price(dto.getPrice()).amount(dto.getAmount()).idProduct(dto.getIdProduct()).build());
            log.info("Producto agregado a carrito de compras");
        } catch (Exception e) {
            log.error("No se agrega producto con id: " + dto.getIdProduct() + " al carrito de compras para el cliente");
            throw new ResourceException("No se pudo agregar a carrito de compras", "Producto", "id:",
                    dto.getIdProduct());
        }
        return converter.entityToDto(detailEntity);
    }

    @Override
    public List<DetailPurchaseDto> findByIdCart(long idCart) {
        log.info("Consultando productos de carrito de compra...");
        try {
            listDetail = purchaseRepository.findByIdCart(idCart);
        } catch (Exception e) {
            throw new ResourceException("No se encontro productos", "para el carrito de compras ", "Id", idCart);
        }

        return converter.entityToDto(listDetail);
    }

    @Override
    public void deleteProductInCart(long id) {

        log.info("Eliminando productos de carrito de compra...");
        try {
            purchaseRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceException("No se Elimino productos", "para el carrito de compras ", "Id", id);
        }

    }

}