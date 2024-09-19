# kafka-msa 예제코드 

>💡 **Kafka를 사용하여, 간단한 이벤트 중심 아키텍처를 구현해봅니다! 
주문이 생성될 때, 결제가 이루어지고, 알림이 발송되는 흐름을 이벤트 기반으로 개발 해주세요!**

<img width="638" alt="스크린샷 2024-09-13 오전 2 30 16 (1)" src="https://github.com/user-attachments/assets/818abd5b-71a4-4ac8-82e4-a705492e0119">

## kafka-practice 구조

**기술스택**
- Spring-Kafka
- Kafka, Kafka-UI, Zookeeper, Zipkin
- Spring Cloud-Discovery Client, Server, Gateway
- Spring JPA
- H2

  
**패키지 구조**  

```java
order
├── domain
│   ├── Order.java
│   ├── OrderRepository.java
│   └── OrderStatus.java
│
├── application
│   └── OrderApplicationService.java
│
├── dto
│		├── OrderCreateDto.java	
│   └── OrderResponseDto.java
│
├── events  // 필요한 Event 객체를 생성 해주세요 
│
├── infrastructure
│   ├── messaging
│   │   └── OrderConsumer.java
│   │
│   ├── persistence
│       └── JpaOrderRepository.java
│
└── presentation
│   └── OrderController.java
│
└── EventSerializer.java
```
