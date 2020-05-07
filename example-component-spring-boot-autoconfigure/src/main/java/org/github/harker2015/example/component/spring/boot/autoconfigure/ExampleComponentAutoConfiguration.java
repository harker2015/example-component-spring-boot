package org.github.harker2015.example.component.spring.boot.autoconfigure;

import org.github.harker2015.example.component.core.LocalCache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

@Configuration
@ConditionalOnClass({LocalCache.class})
public class ExampleComponentAutoConfiguration {
    @Bean
//    @ConditionalOnMissingBean(name = "localCache")
    public LocalCache localCache(){
        return new LocalCache();
    }
}
