package com.dkf.murule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiuRongQuan
 */
@Configuration
public class MyselfRule {

    @Bean
    public IRule myRule(){
//        return new RandomRule();
        return new RetryRule();
    }
}
