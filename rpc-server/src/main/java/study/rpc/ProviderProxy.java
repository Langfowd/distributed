package study.rpc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ProviderProxy implements ApplicationContextAware,InitializingBean {

    private int port;

    private Map<String,Object> map = new HashMap<String, Object>();

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public ProviderProxy(int port) {
        this.port = port;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(RpcServer.class);
        if (beans != null) {
            for (Map.Entry<String, Object> entry : beans.entrySet()) {
                Object value = entry.getValue();
                RpcServer rpcServer = value.getClass().getAnnotation(RpcServer.class);
                String className = rpcServer.value().getName();
                map.put(className,value);
            }
        }
    }

    public void afterPropertiesSet() throws Exception {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new SocketComsuption(socket,map));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
