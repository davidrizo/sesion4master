package es.ua.dlsi.master.sesion4.persistence.xstream;

import es.ua.dlsi.master.sesion4.Sesion4Exception;
import es.ua.dlsi.master.sesion4.persistence.User;
import es.ua.dlsi.master.sesion4.persistence.Users;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class XStreamUsersDAOTest {
    @Test
    public void saveLoadUsers() throws Sesion4Exception {
        Users users = new Users();
        users.addUser(new User("David Rizo"));
        users.addUser(new User("Santi Meliá"));

        XStreamUsersDAO xStreamUsersDAO = new XStreamUsersDAO(new File("/tmp/a.xml")); //TODO TEMP
        xStreamUsersDAO.save(users);

        Users loadUsers = xStreamUsersDAO.getUsers();
        assertEquals("Loaded users", users.getUsers().size(), loadUsers.getUsers().size());
        for (int i=0; i<users.getUsers().size(); i++) {
            assertEquals("User #" + i, users.getUsers().get(i), loadUsers.getUsers().get(i));
        }
    }
}