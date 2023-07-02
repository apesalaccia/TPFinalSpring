package co.com.worldoffice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.worldoffice.dto.RequestShoppingCartDto;
import co.com.worldoffice.dto.ResponseShoppingCartDto;
import co.com.worldoffice.services.ShoppingCartService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("purchase")
public class ShoppingCartController {

    private static final Logger log = LoggerFactory.getLogger(ShoppingCartController.class);

    @Autowired
    private ShoppingCartService shoppingCartService;

    @ApiOperation(value = "Agrega producto al carrito de compras", notes = "El servicio permite agregar un producto a un carrito de compra, se verifica si el producto existe y si tiene la disponibilidad de unidades, ademas, consulsulta si el cliente tiene un carrito disponible de lo contrario crrea uno ")
    @PostMapping
    public ResponseEntity<?> postAddProduct(@RequestBody RequestShoppingCartDto dto) {
        log.info("Consumo postAddProduct de API de Shopping Cart");
        shoppingCartService.addProductToCart(dto);
        return ResponseEntity.accepted().build();
    }

    @ApiOperation(value = "Consulta los productos en el carrito de compras", notes = "El servicio consulta si existe carrito de compra con estado disponible y los productos agregados")
    @GetMapping
    public ResponseEntity<ResponseShoppingCartDto> getShoppingCart(@RequestParam(required = true) Integer idCustomer) {
        log.info("Consumo getShoppingCart de API de Shopping Cart");
        return ResponseEntity.ok(shoppingCartService.getShoppingCart(idCustomer));
    }

    @ApiOperation(value = "elimina los producto del carrito de compras", notes = "El servicio vacia el carrito de compras")
    @DeleteMapping
    public ResponseEntity<?> deleteProductShoppingCart(@RequestParam(required = true) Integer idCustomer) {
        log.info("Consumo deleteProductShoppingCart de API de Shopping Cart");
        shoppingCartService.cancelShoppingCart(idCustomer);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    @ApiOperation(value = "Realiza la compra", notes = "El servicio actualiza el stock de los productos y cambia el estado del carrito de compras")
    @GetMapping("/{idCustomer}")
    public ResponseEntity<ResponseShoppingCartDto> getToBuy(@PathVariable("idCustomer") Integer idCustomer) {
        log.info("Consumo getToBuy de API de Shopping Cart");
        return ResponseEntity.ok(shoppingCartService.toBuy(idCustomer));
    }

}