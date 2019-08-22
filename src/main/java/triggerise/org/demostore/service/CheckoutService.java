package triggerise.org.demostore.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import triggerise.org.demostore.exception.DemoStoreException;
import triggerise.org.demostore.model.Checkout;
import triggerise.org.demostore.repository.CheckoutRepository;

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
