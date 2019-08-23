package test.poc.demostore.management;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.poc.demostore.model.Product;
import test.poc.demostore.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductManagement {

    private Logger logger = LoggerFactory.getLogger(ProductManagement.class);

    @Autowired
    ProductService productService;


    /**
     * List all products
     *
     * @return
     */
    public List<Product> listProducts() {
        logger.info("Listing products");
        return productService.getProducts();
    }

    public void createProduct() {
        logger.info("creating Products");
        if (productService.getProducts().isEmpty()) {

            Product p1 = new Product();

            p1.setName("TICKET");
            p1.setCode("TR0001");
            p1.setPrice(new BigDecimal(5));

            productService.add(p1);


            Product p2 = new Product();

            p2.setName("HOODIE");
            p2.setCode("TR0002");
            p2.setPrice(new BigDecimal(20));

            productService.add(p2);

            Product p3 = new Product();

            p3.setName("HAT");
            p3.setCode("TR0003");
            p3.setPrice(new BigDecimal(7.5));

            productService.add(p3);

        }
    }
}
