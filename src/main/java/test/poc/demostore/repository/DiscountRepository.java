package test.poc.demostore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.poc.demostore.model.Discount;

@Repository
public interface DiscountRepository extends CrudRepository<Discount, Long> {

}
