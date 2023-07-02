package co.com.worldoffice.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.worldoffice.dto.ProductDto;
import co.com.worldoffice.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Product", description = "Product API")
@RequestMapping("product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "consulta los productos en el catalogo", notes = "El servicio soporta la consulta de productos por alguno de los parametros si no se envia ninguno de los parametros devolvera todos los productos")
    @GetMapping
    public ResponseEntity<List<ProductDto>> getProduct(@RequestParam(required = false, name = "name") String name,
                                                       @RequestParam(required = false, name = "price") Integer price,
                                                       @RequestParam(required = false, name = "trademark") String trademark) {
        log.info("Consumo getProduct de API de Productos");
        return ResponseEntity.ok(productService.getAllProduct(name, price, trademark));
    }

}