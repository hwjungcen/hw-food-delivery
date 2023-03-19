package hwfooddelivery.domain;

import hwfooddelivery.PaymentApplication;
import hwfooddelivery.domain.PaymentPaid;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Payment_table")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private String status;

    @PostPersist
    public void onPostPersist() {
        PaymentPaid paymentPaid = new PaymentPaid(this);
        paymentPaid.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {}

    public static PaymentRepository repository() {
        PaymentRepository paymentRepository = PaymentApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return paymentRepository;
    }

    public static void ordered(OrderPlaced orderPlaced) {
        /** Example 1:  new item 
        Payment payment = new Payment();
        repository().save(payment);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(payment->{
            
            payment // do something
            repository().save(payment);


         });
        */

    }
}
