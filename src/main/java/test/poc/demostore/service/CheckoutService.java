package test.poc.demostore.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.poc.demostore.exception.DemoStoreException;
import test.poc.demostore.model.Checkout;
import test.poc.demostore.repository.CheckoutRepository;

import java.util.List;
import java.util.Optional;

@Component
public class CheckoutService {

    @Autowired
    CheckoutRepository checkoutRepository;

    public void add(Checkout checkout){
        checkoutRepository.save(checkout);
    }

    public void delete(long id) {
        checkoutRepository.deleteById(id);
    }

    public List<Checkout> getCheckouts() {
        return ( List<Checkout> ) checkoutRepository.findAll();
    }

    public Checkout getCheckoutById(long id) throws DemoStoreException {
        Optional<Checkout> optionalDog = checkoutRepository.findById(id);
        return optionalDog.orElseThrow(() -> new DemoStoreException("Couldn't find a checkout with id: " + id));
    }
}
