package study.rpc;

import study.rpc.po.User;

public interface HelloService {
    String sayHello(String name);

    String saveUser(User user);

    void say(String name);
}
