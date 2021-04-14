# Kafka-Stream-DataPipeline-Near-Real-Time
Stream data into pipeline in near-real-time using Kafka

## Objective
The objective of this assignment is to be able to have data in a platform to run streaming data pipeline.
In this assignment, we learn how to
  • produce the content of a CSV file to a Kafka topic,
  • consume messages from a Kafka topic
  
## Data set
The data set is STM GTFS data of Montreal, Canada.
## Problem statement
We get the information of STM every day and need to run an ETL pipeline to enrich data for reporting
and analysis purpose in real-time. Data is split in two
  1. A set of tables that build dimension (batch style)
  2. Trips that needed to be enriched for analysis and reporting (streaming)
  In order to be able to run streaming analysis with a platform such as Spark Streaming, we need to have
  the records in a streaming platform such as Kafka.
  
 ## Project Requiments
 <img width="975" alt="image" src="https://user-images.githubusercontent.com/45977153/114740946-51146880-9d18-11eb-8503-0df57450c82c.png">
