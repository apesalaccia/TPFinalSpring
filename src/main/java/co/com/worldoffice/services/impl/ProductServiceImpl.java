package co.com.worldoffice.services.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.worldoffice.dto.ProductDto;
import co.com.worldoffice.exception.ResourceException;
import co.com.worldoffice.repositories.ProductRepository;
import co.com.worldoffice.services.ProductService;
import co.com.worldoffice.utils.ProductConverter;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductConverter converter;

    @Override
    public List<ProductDto> getAllProduct(String name, Integer price, String trademark) {
        HashMap<String, Object> data = new HashMap<>();

        if (name != null)
            data.put("name", name);
        if (trademark != null)
            data.put("trademark", trademark);
        if (price != null)
            data.put("price", price);

        return converter.entityToDto(repository.getData(data));
    }

    @Override
    public ProductDto getProductById(long idProduct) {
        log.info("Consultando producto por id: " + idProduct + "...");
        return converter.entityToDto(repository.findById(idProduct)
                .orElseThrow(() -> new ResourceException("No se Encontro", "Producto", "id", idProduct)));

    }

    @Override
    public void saveOrUpdate(ProductDto dto) {

        log.info("Guardando o Actualizando producto...");
        try {
            repository.save(converter.dtoToEntity(dto));
        } catch (Exception e) {
            throw new ResourceException("No se guardo o actualizo", "Producto ", "nombre", dto.getName());
        }

    }

}