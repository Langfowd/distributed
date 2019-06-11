package study.rpc.provider;

import org.springframework.stereotype.Service;
import study.rpc.HelloService;
import study.rpc.RpcServer;
import study.rpc.po.User;

@RpcServer(value = HelloService.class)
public class HelloServiceImpl implements HelloService {

    public String sayHello(String name) {
        System.out.println("from name ==>  "+name);
        return "hello ,"+name;
    }

    public String saveUser(User user) {
        System.out.println(user.toString());
        return "SUCCESS";
    }

    public void say(String name) {
        System.out.println(name);
    }
}
