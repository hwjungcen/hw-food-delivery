package hwfooddelivery.domain;

import hwfooddelivery.domain.*;
import hwfooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class CookingRejected extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long storeId;
    private Long foodId;
    private String customerId;
    private String status;
    private String address;

    public CookingRejected(Cooking aggregate) {
        super(aggregate);
    }

    public CookingRejected() {
        super();
    }
}
