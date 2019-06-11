package study.rpc;

import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

public class RpcProxy {

    public <T> T getInstance(Class<T> tClass,String host,int port) {
        return (T) Proxy.newProxyInstance(tClass.getClassLoader(),new Class<?>[]{tClass},new ClientHandler(host,port));
    }
}
