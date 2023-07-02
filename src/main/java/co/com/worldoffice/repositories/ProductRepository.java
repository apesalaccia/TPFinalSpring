package co.com.worldoffice.repositories;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.worldoffice.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    public List<ProductEntity> getData(HashMap<String, Object> conditions);
}