package hwfooddelivery.domain;

import hwfooddelivery.domain.*;
import hwfooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class CancelRequested extends AbstractEvent {

    private Long id;
    private Long storeId;
    private Long foodId;
    private String customerId;
    private String address;
    private String status;

    public CancelRequested(Order aggregate) {
        super(aggregate);
    }

    public CancelRequested() {
        super();
    }
}
