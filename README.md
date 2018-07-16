# KafkaPubSubs
A simple framework for scalable Streaming Processing with Apache  Kafka

## Overview

KafkaPubSub is a simple framework that provides a platform to build scalable streaming processing application over Apache Kafka.

It abstracts message producers behind a RESTful API. That is, the framework provides REST APIs to create topics, list topics and configure topics. 

As the main API, it allows sending messages over a REST API. 
```sh
curl -d '{"key":"MessageKey", "value":"Message"}' -X POST http://host:port/v1/{topic_name}
```

```yaml
# Kafka PubSubs Configuration
---
streams:
    - 
        id: stream1
        source: topic_in  
        sink: topic_transformed 
        topology: com.sithroo.sample.DataTransformStream 
    - 
        id: stream2
        sourceTopic: topic_transformed
        topology: com.sithroo.sample.DataLakeStream
        properties:
            sateStoreName: eventStateStore
            interval: 10000
    - 
        id: stream3
        sourceTopic: topic_transformed
        topology: com.sithroo.sample.DataAggStream
        properties:
            sateStoreName: aggStateStore
            interval: 20000
```  

The framework provides a configurable way to define multiple Stream topologies. The application implemented with the framework is deployable on Kubernetes to achieve scalability and availability. The framework handles the consistency of the state store for individual stream topology over multiple instances of the application on cluster environment.
