package co.com.worldoffice.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.worldoffice.dto.DetailPurchaseDto;
import co.com.worldoffice.dto.ProductDto;
import co.com.worldoffice.dto.RequestShoppingCartDto;
import co.com.worldoffice.dto.ResponseShoppingCartDto;
import co.com.worldoffice.dto.ShoppingCartDto;
import co.com.worldoffice.entities.ShoppingCartEntity;
import co.com.worldoffice.exception.ResourceException;
import co.com.worldoffice.repositories.ShoppingCartRepository;
import co.com.worldoffice.services.DetailPurchaseService;
import co.com.worldoffice.services.ProductService;
import co.com.worldoffice.services.ShoppingCartService;
import co.com.worldoffice.utils.Constant;
import co.com.worldoffice.utils.ShoppingCartConverter;
import co.com.worldoffice.utils.Utility;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private static final Logger log = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private DetailPurchaseService detailService;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartConverter converter;

    private ProductDto productDto;
    private ShoppingCartDto shoppingCartDto;
    private ShoppingCartEntity shoppingCartEntity;

    @Override
    public void addProductToCart(RequestShoppingCartDto dto) {
        log.info("Metodo addProductToCart iniciado");

        productDto = productService.getProductById(dto.getIdProdut());

        log.info("Verificando disponibilidad...");
        if (productDto.getStock() < dto.getAmount()) {
            log.error("la cantidad solicitada supera el stock");
            throw new ResourceException("No hay suficiente cantidad,", "Producto", "Stock", productDto.getStock());
        }

        shoppingCartDto = getShoppingCartByCustomer(dto.getIdCustomer());

        if (shoppingCartDto == null) {
            log.info("Creando carrito de compra...");
            try {
                shoppingCartEntity = shoppingCartRepository.save(
                        ShoppingCartEntity.builder().IdCustomer(dto.getIdCustomer()).status(Constant.CART_NEW).build());
                log.info("Carrito de compras creado para el cliente " + dto.getIdCustomer());

            } catch (Exception e) {
                log.error("No se crea carrito de compras para el cliente " + dto.getIdCustomer());

                throw new ResourceException("No se pudo crear", "Carrito de compras para el cliente", "Identificado",
                        dto.getIdCustomer());
            }
        }

        log.info("Carrito de compras del cliente " + dto.getIdCustomer() + " disponible");

        detailService.save(DetailPurchaseDto.builder().idCart(shoppingCartEntity.getIdCart())
                .price(Utility.discount(productDto.getPrice(), productDto.getDiscount(), dto.getAmount()))
                .amount(dto.getAmount()).idProduct(dto.getIdProdut()).build());

    }

    @Override
    public ResponseShoppingCartDto getShoppingCart(long idCustomer) {

        shoppingCartDto = getShoppingCartByCustomer(idCustomer);

        List<ProductDto> products = getProductList(shoppingCartDto.getIdCart());

        return ResponseShoppingCartDto.builder().shoppingCartDto(shoppingCartDto).products(products).build();
    }

    @Override
    public void cancelShoppingCart(long idCustomer) {
        shoppingCartDto = getShoppingCartByCustomer(idCustomer);
        deleteProductsInCart(shoppingCartDto.getIdCart());
    }

    @Override
    public ResponseShoppingCartDto toBuy(Integer idCustomer) {

        shoppingCartDto = getShoppingCartByCustomer(idCustomer);
        buy(shoppingCartDto.getIdCart());
        List<ProductDto> products = getProductList(shoppingCartDto.getIdCart());
        shoppingCartDto.setStatus(Constant.CART_BUY);
        shoppingCartRepository.save(converter.dtoToEntity(shoppingCartDto));

        return ResponseShoppingCartDto.builder().shoppingCartDto(shoppingCartDto).products(products).build();

    }

    private void buy(long idCart) {
        List<ProductDto> products = getProductList(idCart);

        for (ProductDto dto : products) {

            productDto = productService.getProductById(dto.getIdProduct());

            productDto.setStock(productDto.getStock() - dto.getStock());

            productService.saveOrUpdate(productDto);

        }
    }

    private ShoppingCartDto getShoppingCartByCustomer(long idCustomer) {

        log.info("Consultando carrito de compra...");
        shoppingCartEntity = shoppingCartRepository.findByCustomer(idCustomer);

        if (shoppingCartEntity == null) {

            log.warn("No se encontro carrito de compra con id: " + idCustomer + "...");
            return null;
        }

        return converter.entityToDto(shoppingCartEntity);
    }

    private List<ProductDto> getProductList(long idCart) {
        List<ProductDto> products = new ArrayList<ProductDto>();

        List<DetailPurchaseDto> listDetail = detailService.findByIdCart(idCart);

        for (DetailPurchaseDto dpe : listDetail) {

            productDto = productService.getProductById(dpe.getIdProduct());

            productDto.setPrice(dpe.getPrice());
            productDto.setStock(dpe.getAmount());

            products.add(productDto);

        }

        return products;

    }

    private void deleteProductsInCart(long idCart) {

        List<DetailPurchaseDto> listDetail = detailService.findByIdCart(idCart);

        for (DetailPurchaseDto dpe : listDetail) {

            detailService.deleteProductInCart(dpe.getIdDetail());

        }

    }
}