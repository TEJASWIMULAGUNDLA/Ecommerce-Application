package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.kafka.KafkaConstant;

@Configuration
public class KafkaConsumerConfig {

	@Bean
	 ConsumerFactory<String , Object> consumerFactory(){
		Map<String, Object> configProp = new HashMap<>();
		configProp.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,KafkaConstant.HOST);
		configProp.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		configProp.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(configProp, new org.apache.kafka.common.serialization.StringDeserializer(), new JsonDeserializer<>());
	}
	@Bean
	 ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListnerFactory(){
		ConcurrentKafkaListenerContainerFactory<String,Object> factory =
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}