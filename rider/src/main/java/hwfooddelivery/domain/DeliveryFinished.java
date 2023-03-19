package hwfooddelivery.domain;

import hwfooddelivery.domain.*;
import hwfooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class DeliveryFinished extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String address;
    private String status;

    public DeliveryFinished(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryFinished() {
        super();
    }
}
