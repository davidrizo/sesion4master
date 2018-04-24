package es.ua.dlsi.master.sesion4;

import es.ua.dlsi.master.sesion4.persistence.IUsersDAO;
import es.ua.dlsi.master.sesion4.persistence.Users;
import es.ua.dlsi.master.sesion4.persistence.xstream.XStreamUsersDAO;

import javax.inject.Inject;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @autor drizo
 */
public class Model {
    Users users;

    IUsersDAO usersDAO;

    @Inject
    public Model(IUsersDAO usersDAO) {
        this.usersDAO = usersDAO;
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Using DAO {0}", usersDAO);
        users = usersDAO.getUsers();
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Loaded {0} users in model", users.getUsers().size());
    }

    public Users getUsers() {
        return users;
    }
}
