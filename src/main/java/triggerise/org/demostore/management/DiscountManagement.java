package triggerise.org.demostore.management;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import triggerise.org.demostore.model.Discount;
import triggerise.org.demostore.model.DiscountType;
import triggerise.org.demostore.service.DiscountService;
import triggerise.org.demostore.service.ProductService;

import java.util.List;

@Component
public class DiscountManagement {

    private Logger logger = LoggerFactory.getLogger(DiscountManagement.class);

    @Autowired
    DiscountService discountService;

    @Autowired
    ProductService productService;


    /**
     *  List all the Discounts
     * @return
     */
    public List<Discount> listDiscounts(){
        logger.info("Listing Discounts");
        return discountService.getDiscounts();
    }


    public void createDiscount(){

        logger.info("creating Discounts");

        if (discountService.getDiscounts().isEmpty()) {
            Discount discount1 = new Discount();

            discount1.setProduct( productService.findByName("TICKET"));
            discount1.setDiscountType(DiscountType.N_PAY_X);
            discount1.setRule(2);
            discount1.setValue(1);
            discountService.add(discount1);

            Discount discount2 = new Discount();

            discount2.setProduct( productService.findByName("HOODIE"));
            discount2.setDiscountType(DiscountType.VALUE_BULK);
            discount2.setRule(3);
            discount2.setValue(1);

            discountService.add(discount2);
        }


    }
}
