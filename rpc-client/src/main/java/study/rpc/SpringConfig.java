package study.rpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public RpcProxy rpcProxy() {
        return new RpcProxy();
    }
}
