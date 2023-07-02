package co.com.worldoffice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.worldoffice.entities.DetailPurchaseEntity;

public interface DetailPurchaseRepository extends JpaRepository<DetailPurchaseEntity, Long> {

    @Query(value = "SELECT * FROM DETAILPURCHASE dp WHERE dp.IDCART = ?1 ", nativeQuery = true)
    List<DetailPurchaseEntity> findByIdCart(long idCart);

}