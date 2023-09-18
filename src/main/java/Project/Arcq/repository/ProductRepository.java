package Project.Arcq.repository;

import Project.Arcq.data.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

    @Query("""
   SELECT p
   FROM Product p
   JOIN p.brand b
   WHERE b.id = :brandId
   AND p.productId = :productId
   AND :appDate BETWEEN p.startDate AND p.endDate
   """)
    List<Product> findByBrandIdProductIdAndAppDate(Long brandId, Long productId, LocalDateTime appDate);
}
