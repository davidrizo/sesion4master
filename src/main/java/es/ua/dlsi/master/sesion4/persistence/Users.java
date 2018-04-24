package es.ua.dlsi.master.sesion4.persistence;

import java.util.LinkedList;
import java.util.List;

/**
 * @autor drizo
 */
public class Users {
    List<User> users;

    public Users() {
        users = new LinkedList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


}
