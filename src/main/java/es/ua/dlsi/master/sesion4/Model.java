package es.ua.dlsi.master.sesion4;

import es.ua.dlsi.master.sesion4.persistence.IUsersDAO;
import es.ua.dlsi.master.sesion4.persistence.Users;
import es.ua.dlsi.master.sesion4.persistence.xstream.XStreamUsersDAO;

import java.io.File;

/**
 * @autor drizo
 */
public class Model {
    Users users;

    public Model() {
        IUsersDAO usersDAO = new XStreamUsersDAO(this.getClass().getResource("/xmldata/users.xml").getFile());
        users = usersDAO.getUsers();
    }

    public Users getUsers() {
        return users;
    }
}
