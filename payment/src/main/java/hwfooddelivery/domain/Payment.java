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
    }

    @PreUpdate
    public void onPreUpdate() {
    }

    public static PaymentRepository repository() {
        PaymentRepository paymentRepository = PaymentApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return paymentRepository;
    }

    public static void ordered(OrderPlaced orderPlaced) {
        Payment payment = new Payment();
        payment.setOrderId(orderPlaced.getId());
        payment.setStatus("WAIT");
        repository().save(payment);
    }

    public void pay() {
        setStatus("PAID");
        PaymentPaid paymentPaid = new PaymentPaid(this);
        paymentPaid.publishAfterCommit();
    }
}