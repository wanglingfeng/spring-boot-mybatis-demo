package com.lfwang.demo.pulsar;

import com.ypshengxian.pulsar.springboot.annotation.PulsarConsumer;
import com.ypshengxian.pulsar.springboot.consumer.PulsarMessageConsumer;
import org.apache.pulsar.common.naming.TopicDomain;
import org.springframework.stereotype.Service;

/**
 * @author wanglingfeng
 * @date Created in 2021/3/1
 */
@PulsarConsumer(domain = TopicDomain.persistent,
        tenant = "${spring.pulsar.tenant}",
        namespace ="${spring.pulsar.namespace}",
        topic = "${spring.pulsar.consumer.topic.dispatch-coupon}",
        subscriptionSuffix = "${spring.pulsar.consumer.subscription-suffix}")
@Service
public class DemoMessageConsumer extends PulsarMessageConsumer<DemoMessage> {

    @Override
    public void handleMessage(DemoMessage demoMessage) {
        System.out.println("1");
    }
}
