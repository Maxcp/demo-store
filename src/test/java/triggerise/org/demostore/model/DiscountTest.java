package triggerise.org.demostore.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DiscountTest {

    @Test
    public void apply() {
        Discount discount1 = new Discount();
        Discount discount2 = new Discount();
        Discount discount3 = new Discount();
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();

        product1.setCode("P001");
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setPrice(new BigDecimal(20L));

        product2.setCode("P002");
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setPrice(new BigDecimal(10L));

        product3.setCode("P003");
        product3.setId(3L);
        product3.setName("Product 3");
        product3.setPrice(new BigDecimal(5L));

        discount1.setId(1L);
        discount1.setCode("D1");
        discount1.setRule(3);
        discount1.setValue(2);
        discount1.setDiscountType(DiscountType.N_PAY_X);
        discount1.setProduct(product1);

        discount2.setId(1L);
        discount2.setCode("D2");
        discount2.setRule(2);
        discount2.setValue(1);
        discount2.setDiscountType(DiscountType.VALUE_BULK);
        discount2.setProduct(product2);

        discount3.setId(1L);
        discount3.setCode("D1");
        discount3.setRule(3);
        discount3.setValue(10);
        discount3.setDiscountType(DiscountType.DEFAULT);
        discount3.setProduct(product3);


        System.out.println(discount1.apply(1));
        System.out.println(discount1.apply(3));
        System.out.println(discount1.apply(9));
        System.out.println(discount1.apply(11));

        System.out.println("=============================DISCOUNT T1====================================");

        System.out.println(discount2.apply(1));
        System.out.println(discount2.apply(2));
        System.out.println(discount2.apply(4));
        System.out.println(discount2.apply(5));

        System.out.println("=============================DISCOUNT T2====================================");


        System.out.println(discount3.apply(1));
        System.out.println(discount3.apply(3));
        System.out.println(discount3.apply(9));
        System.out.println(discount3.apply(11));

        System.out.println("=============================DISCOUNT T3====================================");

    }
}
