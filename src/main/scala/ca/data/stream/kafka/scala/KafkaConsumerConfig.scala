package ca.data.stream.kafka.scala

import org.apache.kafka.clients.consumer.ConsumerConfig._
import java.util.Properties
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import scala.collection.JavaConverters._

trait KafkaConsumerConfig {
  val consumerProperties = new Properties()
  consumerProperties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  consumerProperties.setProperty(GROUP_ID_CONFIG, "group-enriched-trips")
  consumerProperties.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
  consumerProperties.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
  consumerProperties.setProperty(AUTO_OFFSET_RESET_CONFIG, "latest")
  consumerProperties.setProperty(ENABLE_AUTO_COMMIT_CONFIG, "true")

  val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](consumerProperties)
  consumer.subscribe(List("trip").asJava)
}
