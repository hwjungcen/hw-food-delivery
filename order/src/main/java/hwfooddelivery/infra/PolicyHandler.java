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
    OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PaymentPaid'"
    )
    public void wheneverPaymentPaid_Paid(@Payload PaymentPaid paymentPaid) {
        PaymentPaid event = paymentPaid;
        System.out.println("\n\n##### listener Paid : " + paymentPaid + "\n\n");

        // Sample Logic //
        Order.paid(event);
    }
}
