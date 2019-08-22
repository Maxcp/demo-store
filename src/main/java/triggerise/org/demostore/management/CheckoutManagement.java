package triggerise.org.demostore.management;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import triggerise.org.demostore.exception.DemoStoreException;
import triggerise.org.demostore.model.Checkout;
import triggerise.org.demostore.model.Discount;
import triggerise.org.demostore.model.ProductItem;
import triggerise.org.demostore.model.request.CheckoutRequest;
import triggerise.org.demostore.model.request.Item;
import triggerise.org.demostore.service.DiscountService;
import triggerise.org.demostore.service.ProductService;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class CheckoutManagement {

    private Logger logger = LoggerFactory.getLogger(CheckoutManagement.class);

    @Autowired
    DiscountService discountService;

    @Autowired
    ProductService productService;


    public Checkout doCheckout(CheckoutRequest checkoutRequest) throws DemoStoreException {
        logger.info("Initializing checkout");

        checkoutRequest.setItems(aggregateItemsByProductId(checkoutRequest.getItems()));

        Checkout checkout = new Checkout();
        checkout.setProductItems(setProductItems(checkoutRequest));
        checkout.setDiscount(applyDiscounts(checkoutRequest));

        logger.info("Checkout finished");
        return checkout;

    }


    private BigDecimal applyDiscounts(CheckoutRequest checkoutRequest) {
        logger.info("Applying discounts");
        List<Discount> discounts = discountService.getDiscounts();

        AtomicReference<BigDecimal> discountPool = new AtomicReference<>(new BigDecimal(0L));

        discounts.forEach( d -> {
                  discountPool.updateAndGet(v -> v.add (Objects.requireNonNull(applyDiscounts(d , checkoutRequest.getItems()))));
        });

        discountPool.get();
        logger.info("Discounts applied");


        return discountPool.get();
    }

    private BigDecimal applyDiscounts(Discount discount, List<Item> items) {
        AtomicReference<BigDecimal> totalDiscount = new AtomicReference<>(new BigDecimal(0L));

        items.forEach( item -> {
            if(item.getProductId().equals(discount.getProduct().getId())) {
                totalDiscount.updateAndGet(v -> v.add(discount.apply(item.getQuantity())));
            }
        });

        return totalDiscount.get();

    }

    /**
     * Fetch the productItem while validating if it exists
     *
     * @param checkoutRequest
     * @return List of ProductItem
     * @throws DemoStoreException
     */
    private List<ProductItem> setProductItems(CheckoutRequest checkoutRequest) throws DemoStoreException {

        List<ProductItem> productItems = new ArrayList<>();

        for (Item item : checkoutRequest.getItems()) {
            ProductItem productItem = new ProductItem();
            productItem.setProduct(productService.getProductById(item.getProductId()));
            productItem.setQuantity(item.getQuantity());
            productItems.add(productItem);
        }
        return productItems;
    }

    /**
     *
     * If there are duplicated products in the list it will merge.
     * This method must be called before apply the discount.
     *
     * @param items
     * @return List of Item
     */
    private List<Item> aggregateItemsByProductId(List<Item> items) {
        Map<Long,Item> productList = new HashMap<>();

        for (Item item : items ){
            if (!productList.containsKey(item.getProductId())){
                productList.put(item.getProductId(),item);
            } else {
                productList.get(item.getProductId()).setQuantity( productList.get(item.getProductId()).getQuantity() + item.getQuantity() );
            }
        }

        return new ArrayList<>(productList.values());
    }

}
