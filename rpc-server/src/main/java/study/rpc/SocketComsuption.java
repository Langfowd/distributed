package study.rpc;

import study.rpc.po.RpcRq;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

public class SocketComsuption implements Runnable{
    Socket socket;

    Map<String,Object> map;

    public SocketComsuption(Socket socket, Map<String,Object> map) {
        this.socket = socket;
        this.map = map;
    }

    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRq o = (RpcRq) objectInputStream.readObject();
            String className = o.getClassName();
            if (!map.containsKey(className)) {
                throw new RuntimeException("没有该服务");
            }
            Object[]  args = o.getParams();
            Class<?>[] types = getParamsClass(args);
            Object result;
            Class<?> clazz = Class.forName(className);
            if (types == null) {
                Method method = clazz.getMethod(o.getMethodName());
                result = method.invoke(map.get(className));
            } else {
                Method method = clazz.getMethod(o.getMethodName(), types);
                result = method.invoke(map.get(className), args);
            }
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
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

    private Class<?>[] getParamsClass(Object[] params) {
        if (params == null) {
            return null;
        }
        Class<?>[] classes = new Class[params.length];
        for (int i = 0, size = params.length; i < size; i++) {
            classes[i] = params[i].getClass();
        }
        return classes;
    }
}
