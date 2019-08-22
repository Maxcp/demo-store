package triggerise.org.demostore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import triggerise.org.demostore.management.DiscountManagement;
import triggerise.org.demostore.model.Discount;

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
