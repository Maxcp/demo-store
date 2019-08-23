package test.poc.demostore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.poc.demostore.model.Checkout;
import test.poc.demostore.model.request.CheckoutRequest;
import test.poc.demostore.management.CheckoutManagement;


@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutController.class);

    @Autowired
    CheckoutManagement checkoutManagement;

    @RequestMapping(value = "/checkout", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<String> checkout(@RequestBody CheckoutRequest checkoutRequest) throws Exception {


        Checkout checkout = checkoutManagement.doCheckout(checkoutRequest);

        return new ResponseEntity<>(checkout.getTotal().toString(), HttpStatus.CREATED) ;
    }

}
