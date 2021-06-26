package com.webflux.app.messaging;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

@Component
public class MessageProducer {
  
	private KafkaSender<Integer,String> sender;
    private Integer messageCount=0;
	{
		 Map<String, Object> props = new HashMap<>();
	        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	        props.put(ProducerConfig.CLIENT_ID_CONFIG, "sample-producer");
	        props.put(ProducerConfig.ACKS_CONFIG, "all");
	        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
	        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	        SenderOptions<Integer, String> senderOptions = SenderOptions.create(props);

	        sender = KafkaSender.create(senderOptions);
	        System.out.println(sender);
	}
	
	public KafkaSender<Integer, String> getSender(){
		 return sender;
	}
	
	public Integer getMessageCount() {
		messageCount++;
		return messageCount;
	}
}
