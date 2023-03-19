package hwfooddelivery.domain;

import hwfooddelivery.OrderApplication;
import hwfooddelivery.domain.CancelRequested;
import hwfooddelivery.domain.OrderPaid;
import hwfooddelivery.domain.OrderPlaced;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long storeId;

    private Long foodId;

    private String customerId;

    private String address;

    private String status;

    @PostPersist
    public void onPostPersist() {
        setStatus("ORDERED");
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {}

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    public void cancel() {
        CancelRequested cancelRequested = new CancelRequested(this);
        cancelRequested.publishAfterCommit();
    }

    public static void paid(PaymentPaid paymentPaid) {
        repository().findById(paymentPaid.getOrderId()).ifPresent(order->{
            order.setStatus("PAID");
            repository().save(order);

            OrderPaid orderPaid = new OrderPaid(order);
            orderPaid.publishAfterCommit();
         });
    }
}
