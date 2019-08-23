package test.poc.demostore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.poc.demostore.management.DiscountManagement;
import test.poc.demostore.model.Discount;

import java.util.List;

@RestController
@RequestMapping("/discount")
public class DiscountController {


    @Autowired
    DiscountManagement discountManagement;


    @RequestMapping(value = "/create", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<String> createDiscount() {
        discountManagement.createDiscount();

        return new ResponseEntity<>("{\"message\": \"Filled database with default data.\"}", HttpStatus.CREATED) ;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<List<Discount>> listDiscount() {

        return new ResponseEntity<>(discountManagement.listDiscounts(), HttpStatus.ACCEPTED) ;

    }


}
