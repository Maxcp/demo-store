package test.poc.demostore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.poc.demostore.exception.DemoStoreException;
import test.poc.demostore.model.Product;
import test.poc.demostore.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void add(Product product){
        productRepository.save(product);
    }

    public Product findByName(String name) { return productRepository.findByName(name);}

    public void delete(long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProducts() {
        return ( List<Product> ) productRepository.findAll();
    }

    public Product getProductById(long id) throws DemoStoreException {
        Optional<Product> optionalCheckout = productRepository.findById(id);
        return optionalCheckout.orElseThrow(() -> new DemoStoreException("Couldn't find a product with id: " + id));
    }
}
