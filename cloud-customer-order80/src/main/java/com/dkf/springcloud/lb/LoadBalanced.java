package com.dkf.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalanced {
    ServiceInstance getService(List<ServiceInstance> instanceList);
}
