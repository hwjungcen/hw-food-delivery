package hwfooddelivery.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import hwfooddelivery.config.kafka.KafkaProcessor;
import hwfooddelivery.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CookingFinished'"
    )
    public void wheneverCookingFinished_AddDelivery(
        @Payload CookingFinished cookingFinished
    ) {
        CookingFinished event = cookingFinished;
        System.out.println(
            "\n\n##### listener AddDelivery : " + cookingFinished + "\n\n"
        );

        // Sample Logic //
        Delivery.addDelivery(event);
    }
}
