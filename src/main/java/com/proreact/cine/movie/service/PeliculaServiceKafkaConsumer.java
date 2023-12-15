package com.proreact.cine.movie.service;

import com.proreact.cine.movie.config.kafka.KafkaConfig;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class PeliculaServiceKafkaConsumer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public String obtenerPeliculaKafka(String topico){
        ConsumerRecord<String, String> peliculaRecibida;
        KafkaConfig kafkaConfig = new KafkaConfig();
        kafkaTemplate.setConsumerFactory(kafkaConfig.consumerFactory());
        peliculaRecibida = kafkaTemplate.receive(topico, 0, 0);
        return Objects.requireNonNull(peliculaRecibida.value());
    }
}
