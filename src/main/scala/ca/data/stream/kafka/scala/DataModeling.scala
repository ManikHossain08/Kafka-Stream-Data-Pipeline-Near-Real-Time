package ca.data.stream.kafka.scala

case class Trip(tripId: String, serviceId: String, routeId: Int, tripHeadSign: String, wheelChairAccessible: Int)

object Trip{
  def apply(line: String): Trip = {
    val fields: Array[String] = line.split(",", -1)
    Trip(fields(2), fields(1), fields(0).toInt, fields(3), fields(6).toInt)
  }
}

case class EnrichedTrip(tripId: String,
                        serviceId: String,
                        routeId: Int,
                        tripHeadSign: String,
                        date: Option[String],
                        exceptionType: Option[Int],
                        routeLongName: Option[String],
                        routeColor: Option[String],
                        wheelChairAccessible: Boolean)

object EnrichedTrip {
  def apply(trip: Trip): EnrichedTrip = {
    EnrichedTrip(
      trip.tripId,
      trip.serviceId,
      trip.routeId,
      trip.tripHeadSign,
      None, None, None, None,
      trip.wheelChairAccessible == 1)
  }

  def toCsv(enrichedTrip: EnrichedTrip): String = {
    s"${enrichedTrip.tripId},${enrichedTrip.serviceId},${enrichedTrip.routeId}," +
      s"${enrichedTrip.tripHeadSign},,,,,${enrichedTrip.wheelChairAccessible}"
  }
}
