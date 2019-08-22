package triggerise.org.demostore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import triggerise.org.demostore.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findByName(String name);
    
}
