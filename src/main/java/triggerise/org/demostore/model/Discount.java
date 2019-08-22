package triggerise.org.demostore.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class Discount {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;
    private DiscountType discountType;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer rule;
    private Integer value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getRule() {
        return rule;
    }

    public void setRule(Integer rule) {
        this.rule = rule;
    }


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public BigDecimal apply(Integer quantity){
        BigDecimal discountApplied = BigDecimal.ZERO;

        if (quantity >= rule) {
            switch (discountType) {
                case N_PAY_X:
                    discountApplied = applyNPayX(quantity);
                    break;
                case VALUE_BULK:
                    discountApplied = applyValueBulk(quantity);
                    break;
                case DEFAULT:
                    discountApplied = applyDefault(quantity);
                    break;
            }
        }
        return discountApplied;
    }

    private BigDecimal applyNPayX(Integer quantity){
            Integer quantityPackDiscounted = quantity / rule;
            Integer numberDiscounted = rule - value;

            int productMultiplier = quantityPackDiscounted * numberDiscounted;

            return product.getPrice().multiply(new BigDecimal(productMultiplier));


    }

    private BigDecimal applyValueBulk(Integer quantity){
        if (quantity >= rule) {
            return new BigDecimal(value).multiply(new BigDecimal(quantity));
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal applyDefault(Integer quantity){
        BigDecimal percentage = new BigDecimal(value).divide(new BigDecimal(100),2, RoundingMode.DOWN);
        return product.getPrice().multiply(percentage).multiply(new BigDecimal(quantity));
    }
}
