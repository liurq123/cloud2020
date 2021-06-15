package com.dkf.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LbImpl implements LoadBalanced {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    // 循环
    public final Integer crlceNum(){
        int current;
        int newindex;
        do {
            current = atomicInteger.get();
            newindex = current > 2147483647 ? 0 : current + 1;
        }
        while (!atomicInteger.compareAndSet(current,newindex));
        System.out.println("访问（请求）index次数： "+newindex+"次");
        return newindex;

    }
    @Override
    public ServiceInstance getService(List<ServiceInstance> instanceList) {
        Integer index = crlceNum() % instanceList.size();
        return instanceList.get(index);
    }
}
