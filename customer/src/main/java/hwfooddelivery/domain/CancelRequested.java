package hwfooddelivery.domain;

import hwfooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.Data;

@Data
public class CancelRequested extends AbstractEvent {

    private Long id;
    private Long storeId;
    private Long foodId;
    private String customerId;
    private String address;
    private String status;
}
