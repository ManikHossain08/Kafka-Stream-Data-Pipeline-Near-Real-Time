package ca.data.stream.kafka.scala

import java.time.Duration
import org.apache.kafka.clients.producer.ProducerRecord

/* *
 * IMPORTANT NOTE:
 * According to the requirements you said to use kafka command (using kafka Console) to produce kafka message.
 * Therefore, I produce the the kafka messages of the trips.txt file using the below kafka command:
 *
 * "kafka-console-producer --broker-list localhost:9092 --topic trip
 *        < /Users/manikhossain/IdeaProjects/kafka-playground/data/trips.txt"
 *
 * Then this driver program started to consume the messages from trip kafka topic.
 *
 */

object KafkaMain extends App with KafkaConsumerConfig with KafkaProducerConfig {

  while (true) {
    val polledRecords = consumer.poll(Duration.ofSeconds(1))
    if (!polledRecords.isEmpty) {
      val recordIterator = polledRecords.iterator()
      while (recordIterator.hasNext) {
        val consumedTrip = recordIterator.next().value()
        if (!consumedTrip.contains("route_id"))
          kafkaMessageProducer(consumedTrip)
      }
    }
    Thread.sleep(1000)
  }

  def kafkaMessageProducer(consumedMSG: String): Unit = {
    val trip = Trip(consumedMSG)
    val enrichedTrip = EnrichedTrip(trip)
    val csvEnrichedTrip = EnrichedTrip.toCsv(enrichedTrip)
    producer.send(new ProducerRecord[String, String](producerTopicName,
      s"${enrichedTrip.routeId.toString}${enrichedTrip.serviceId}", csvEnrichedTrip)
    )
  }
}