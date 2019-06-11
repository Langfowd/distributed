package study.rpc.po;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 5481116351350592619L;
    private String name;
    private int age;

    /**
     * Gets the value of name.
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the value of age.
     *
     * @return the value of age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age.
     *
     * @param age age
     */
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
