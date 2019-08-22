package triggerise.org.demostore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import triggerise.org.demostore.model.Checkout;

@Repository
public interface CheckoutRepository extends CrudRepository<Checkout, Long> {
    
}
