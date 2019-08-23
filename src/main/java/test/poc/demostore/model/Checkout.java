package test.poc.demostore.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Entity
public class Checkout {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<ProductItem> productItems;
    private BigDecimal discount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }

    public BigDecimal getTotalWithoutDiscount() {
        AtomicReference<BigDecimal> total = new AtomicReference<>(new BigDecimal(0L));
        if (!productItems.isEmpty()) {
            productItems.forEach(pi ->
                    total.updateAndGet(v -> v.add(pi.getProduct().getPrice().multiply(new BigDecimal(pi.getQuantity()))))
            );
        }

        return total.get();
    }

    public BigDecimal getTotal(){
        return getTotalWithoutDiscount().subtract(getDiscount());
    }


    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
