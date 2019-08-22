package triggerise.org.demostore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import triggerise.org.demostore.management.CheckoutManagement;
import triggerise.org.demostore.management.ProductManagement;
import triggerise.org.demostore.model.Checkout;
import triggerise.org.demostore.model.Product;
import triggerise.org.demostore.model.request.CheckoutRequest;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductManagement productManagement;

    @RequestMapping(value = "/create", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<String> createProduct() throws Exception {

        LOGGER.info("Creating Product");
        productManagement.createProduct();

        return new ResponseEntity<>("{\"message\": \"Filled database with default data.\"}", HttpStatus.CREATED) ;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ResponseEntity<List<Product>> listProducts() throws Exception {

        LOGGER.info("Listing Product");

        return new ResponseEntity<>(productManagement.listProducts() , HttpStatus.ACCEPTED);
    }

}
