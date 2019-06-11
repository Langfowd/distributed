package study.rpc;

import study.rpc.po.RpcRq;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class ClientHandler implements InvocationHandler {
    private String host;
    private int port;

    public ClientHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRq rpcRq = new RpcRq();
        rpcRq.setClassName(method.getDeclaringClass().getName());
        rpcRq.setMethodName(method.getName());
        rpcRq.setParams(args);
        return doClient(rpcRq);
    }

    private Object doClient(RpcRq rpcRq) {
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            Socket socket = new Socket(host,port);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRq);
            objectOutputStream.flush();

            objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
