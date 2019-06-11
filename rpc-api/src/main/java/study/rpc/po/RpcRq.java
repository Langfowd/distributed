package study.rpc.po;

import java.io.Serializable;

public class RpcRq implements Serializable {

    private static final long serialVersionUID = -8084414226649383364L;
    private String methodName;
    private String className;
    private Object[] params;

    /**
     * Gets the value of methodName.
     *
     * @return the value of methodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Sets the methodName.
     *
     * @param methodName methodName
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * Gets the value of className.
     *
     * @return the value of className
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets the className.
     *
     * @param className className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Gets the value of params.
     *
     * @return the value of params
     */
    public Object[] getParams() {
        return params;
    }

    /**
     * Sets the params.
     *
     * @param params params
     */
    public void setParams(Object[] params) {
        this.params = params;
    }
}
