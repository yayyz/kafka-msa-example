package com.spartacoding.msa.order;

public enum OrderTopic {
    CREATED("order-created"),
    COMPLETED("order-completed");

    private final String topic;

    OrderTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }
}
