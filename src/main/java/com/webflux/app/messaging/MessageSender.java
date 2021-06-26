package com.webflux.app.messaging;



import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderRecord;

@Component
public class MessageSender {
     @Autowired
      private MessageProducer producer;
      
      private String topic="junction";
     
     public void SendMessage(String message) {
    	producer.getSender().send(Mono.just(producer.getMessageCount())
                .map(i -> SenderRecord.create(new ProducerRecord<>(topic, i, message), i)))
      .doOnError(e -> e.printStackTrace())
      .subscribe(r -> {
          RecordMetadata metadata = r.recordMetadata();
              System.out.printf("Message %d sent successfully, topic-partition=%s-%d offset=%d \n",
              r.correlationMetadata(),
              metadata.topic(),
              metadata.partition(),
              metadata.offset());
          
            });
    	 
     }
}
