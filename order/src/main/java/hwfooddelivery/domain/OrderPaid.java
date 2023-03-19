package hwfooddelivery.domain;

import hwfooddelivery.domain.*;
import hwfooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderPaid extends AbstractEvent {

    private Long id;
    private Long storeId;
    private Long foodId;
    private String customerId;
    private String address;
    private String status;

    public OrderPaid(Order aggregate) {
        super(aggregate);
    }

    public OrderPaid() {
        super();
    }
}
