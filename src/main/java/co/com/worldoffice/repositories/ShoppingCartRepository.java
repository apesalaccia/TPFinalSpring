package co.com.worldoffice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.worldoffice.entities.ShoppingCartEntity;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {

    @Query(value = "SELECT * FROM SHOPPINGCART sc WHERE sc.CURTOMER = ?1 AND sc.STATUS = 'Disponible'", nativeQuery = true)
    ShoppingCartEntity findByCustomer(long idCustomer);

}