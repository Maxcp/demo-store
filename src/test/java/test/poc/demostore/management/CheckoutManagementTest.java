package test.poc.demostore.management;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import test.poc.demostore.model.Discount;
import test.poc.demostore.model.DiscountType;
import test.poc.demostore.model.Product;
import test.poc.demostore.model.request.CheckoutRequest;
import test.poc.demostore.model.request.Item;
import test.poc.demostore.exception.DemoStoreException;
import test.poc.demostore.model.Checkout;
import test.poc.demostore.service.DiscountService;
import test.poc.demostore.service.ProductService;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutManagementTest {

    @InjectMocks
    CheckoutManagement checkoutManagement = new CheckoutManagement();

    @Mock
    DiscountService  discountService;

    @Mock
    ProductService productService;

    @Test
    public void doCheckout() throws DemoStoreException {

        Product p1 = new Product();

        p1.setId(1L);
        p1.setName("TICKET");
        p1.setCode("TR0001");
        p1.setPrice(new BigDecimal(5));

        Product p2 = new Product();

        p2.setId(2L);
        p2.setName("HOODIE");
        p2.setCode("TR0002");
        p2.setPrice(new BigDecimal(20));
        Product p3 = new Product();

        p3.setId(3L);
        p3.setName("HAT");
        p3.setCode("TR0003");
        p3.setPrice(new BigDecimal(7.5));


        Discount discount1 = new Discount();

        discount1.setProduct( p1);
        discount1.setDiscountType(DiscountType.N_PAY_X);
        discount1.setRule(2);
        discount1.setValue(1);

        Discount discount2 = new Discount();

        discount2.setProduct(p2);
        discount2.setDiscountType(DiscountType.VALUE_BULK);
        discount2.setRule(3);
        discount2.setValue(1);

        List<Discount> discounts = new ArrayList<>();
        discounts.add(discount1);
        discounts.add(discount2);

        List<Product> products = new ArrayList<>();
        products.add(p1);
        products.add(p2);
        products.add(p3);


        Mockito.when(discountService.getDiscounts()).thenReturn(discounts);

        Mockito.when(productService.getProductById(p1.getId())).thenReturn(p1);
        Mockito.when(productService.getProductById(p2.getId())).thenReturn(p2);
        Mockito.when(productService.getProductById(p3.getId())).thenReturn(p3);

        Mockito.lenient().when(productService.getProducts()).thenReturn(products);

        CheckoutRequest checkoutRequest = new CheckoutRequest();
        List<Item> items = new ArrayList<>();
        Item i1 = new Item();
        i1.setQuantity(3);
        i1.setProductId(p1.getId());
        Item i2 = new Item();
        i2.setQuantity(3);
        i2.setProductId(p2.getId());
        Item i3 = new Item();
        i3.setQuantity(1);
        i3.setProductId(p3.getId());

        items.add(i1);
        items.add(i2);
        items.add(i3);
        checkoutRequest.setItems(items);
        Checkout checkout = checkoutManagement.doCheckout(checkoutRequest);

        assertEquals(checkout.getDiscount(), new BigDecimal(8));
        assertEquals(checkout.getTotal(), new BigDecimal(74.5));
        assertEquals(checkout.getTotalWithoutDiscount(), new BigDecimal(82.5));


    }
}
