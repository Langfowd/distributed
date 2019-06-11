package study.rpc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class Client
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        RpcProxy rpcProxy = context.getBean(RpcProxy.class);
        HelloService service = rpcProxy.getInstance(HelloService.class, "localhost", 8080);
        System.out.println(service.sayHello("xiaoluo"));
    }
}
