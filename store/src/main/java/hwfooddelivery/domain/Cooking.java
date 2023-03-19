package hwfooddelivery.domain;

import hwfooddelivery.StoreApplication;
import hwfooddelivery.domain.CookingAccepted;
import hwfooddelivery.domain.CookingFinished;
import hwfooddelivery.domain.CookingRejected;
import hwfooddelivery.domain.CookingStarted;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Cooking_table")
@Data
public class Cooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Long storeId;

    private Long foodId;

    private String customerId;

    private String status;

    private String address;

    @PostPersist
    public void onPostPersist() {
        CookingAccepted cookingAccepted = new CookingAccepted(this);
        cookingAccepted.publishAfterCommit();

        CookingRejected cookingRejected = new CookingRejected(this);
        cookingRejected.publishAfterCommit();

        CookingStarted cookingStarted = new CookingStarted(this);
        cookingStarted.publishAfterCommit();

        CookingFinished cookingFinished = new CookingFinished(this);
        cookingFinished.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {}

    public static CookingRepository repository() {
        CookingRepository cookingRepository = StoreApplication.applicationContext.getBean(
            CookingRepository.class
        );
        return cookingRepository;
    }

    public void accept() {}

    public void start() {}

    public void finish() {}

    public void reject() {}

    public static void add(OrderPaid orderPaid) {
        /** Example 1:  new item 
        Cooking cooking = new Cooking();
        repository().save(cooking);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderPaid.get???()).ifPresent(cooking->{
            
            cooking // do something
            repository().save(cooking);


         });
        */

    }

    public static void cancel(OrderCanceled orderCanceled) {
        /** Example 1:  new item 
        Cooking cooking = new Cooking();
        repository().save(cooking);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCanceled.get???()).ifPresent(cooking->{
            
            cooking // do something
            repository().save(cooking);


         });
        */

    }
}
