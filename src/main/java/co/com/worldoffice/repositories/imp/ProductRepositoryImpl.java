package co.com.worldoffice.repositories.imp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import co.com.worldoffice.entities.ProductEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProductRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ProductEntity> getData(HashMap<String, Object> conditions) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> query = cb.createQuery(ProductEntity.class);
        Root<ProductEntity> root = query.from(ProductEntity.class);

        List<Predicate> predicates = new ArrayList<>();
        conditions.forEach((field, value) -> {
            switch (field) {
                case "name":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;

                case "trademark":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "price":
                    predicates.add(cb.lessThan(root.get(field), (Integer) value));
                    break;
                default:
                    break;
            }

        });

        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(query).getResultList();

    }

}