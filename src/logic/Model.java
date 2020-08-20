package logic;

import java.io.*;
import java.util.*;

public class Model implements Serializable {

    public static final Model instance = new Model();

    public final Map<Integer, User> map;

    public Model() {
        map = new HashMap<>();

        map.put(1, new User("Sergey", "Frolov", 45555) );
        map.put(2, new User("Nikolay", "Bulankin", 45555));
        map.put(3, new User("Dmitriy", "Kurnik", 45555));
        map.put(4, new User("Nikita", "Petrov", 45555));
    }

    public static Model getInstance() {
        return instance;
    }

    public void add(int id, User user) {
        map.put(id, user);
    }

    public Map<Integer, User> getList() {
        return map;
    }

    public void doDelete(int id) {
        map.remove(id);
    }

    public void doPut(int id, String name, String surname,double salary) {
        map.get(id).setName(name);
        map.get(id).setSurname(surname);
        map.get(id).setSalary(salary);
    }

}
