package study.rpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "study.rpc")
public class SpringConfig{

    @Bean
    public ProviderProxy providerProxy() {
        return new ProviderProxy(8080);
    }
}
