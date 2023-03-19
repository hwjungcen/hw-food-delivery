package hwfooddelivery.domain;

import hwfooddelivery.RiderApplication;
import hwfooddelivery.domain.DeliveryFinished;
import hwfooddelivery.domain.DeliveryStarted;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Delivery_table")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private String address;

    private String status;

    @PostPersist
    public void onPostPersist() {
    }

    @PreUpdate
    public void onPreUpdate() {}

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = RiderApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public void pick() {
        setStatus("STARTED");
        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();
    }

    public void confirm() {
        setStatus("FINISHED");
        DeliveryFinished deliveryFinished = new DeliveryFinished(this);
        deliveryFinished.publishAfterCommit();
    }

    public static void addDelivery(CookingFinished cookingFinished) {
        Delivery delivery = new Delivery();
        delivery.setOrderId(cookingFinished.getOrderId());
        delivery.setAddress(cookingFinished.getAddress());
        delivery.setStatus("ADDED");
        repository().save(delivery);
    }
}
