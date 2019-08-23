package test.poc.demostore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.poc.demostore.model.Checkout;

@Repository
public interface CheckoutRepository extends CrudRepository<Checkout, Long> {
    
}
