package test.poc.demostore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.poc.demostore.model.Discount;
import test.poc.demostore.exception.DemoStoreException;
import test.poc.demostore.repository.DiscountRepository;

import java.util.List;
import java.util.Optional;

@Component
public class DiscountService {

    @Autowired
    DiscountRepository discountRepository;

    public void add(Discount discount){
        discountRepository.save(discount);
    }

    public void delete(long id) {
        discountRepository.deleteById(id);
    }

    public List<Discount> getDiscounts() {
        return ( List<Discount> ) discountRepository.findAll();
    }

    public Discount getDiscountById(long id) throws DemoStoreException {
        Optional<Discount> optionalDiscount = discountRepository.findById(id);
        return optionalDiscount.orElseThrow(() -> new DemoStoreException("Couldn't find a checkout with id: " + id));
    }
}
