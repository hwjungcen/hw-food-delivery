package hwfooddelivery.domain;

import hwfooddelivery.OrderApplication;
import hwfooddelivery.domain.OrderCanceled;
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
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();

        OrderCanceled orderCanceled = new OrderCanceled(this);
        orderCanceled.publishAfterCommit();

        OrderPaid orderPaid = new OrderPaid(this);
        orderPaid.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {}

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    public void cancel() {}

    public static void paid(PaymentPaid paymentPaid) {
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process
        
        repository().findById(paymentPaid.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);


         });
        */

    }
}
