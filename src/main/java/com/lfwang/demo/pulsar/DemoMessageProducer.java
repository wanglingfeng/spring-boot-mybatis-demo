package com.lfwang.demo.pulsar;

import com.ypshengxian.pulsar.springboot.annotation.PulsarConsumer;
import com.ypshengxian.pulsar.springboot.producer.PulsarMessageProducer;
import org.apache.pulsar.common.naming.TopicDomain;
import org.springframework.stereotype.Component;

/**
 * @author wanglingfeng
 * @date Created in 2021/3/2
 */
@PulsarConsumer(domain = TopicDomain.persistent,
        tenant = "${spring.pulsar.tenant}",
        namespace ="${spring.pulsar.namespace}",
        topic = "${spring.pulsar.consumer.topic.dispatch-coupon}",
        subscriptionSuffix = "${spring.pulsar.consumer.subscription-suffix}")
@Component
public class DemoMessageProducer extends PulsarMessageProducer<DemoMessage> {
}
