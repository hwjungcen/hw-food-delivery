package hwfooddelivery.domain;

import hwfooddelivery.domain.*;
import hwfooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class PaymentPaid extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String status;

    public PaymentPaid(Payment aggregate) {
        super(aggregate);
    }

    public PaymentPaid() {
        super();
    }
}
