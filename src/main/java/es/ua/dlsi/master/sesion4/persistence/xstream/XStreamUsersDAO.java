package es.ua.dlsi.master.sesion4.persistence.xstream;

import com.thoughtworks.xstream.XStream;
import es.ua.dlsi.master.sesion4.Sesion4Exception;
import es.ua.dlsi.master.sesion4.persistence.IUsersDAO;
import es.ua.dlsi.master.sesion4.persistence.User;
import es.ua.dlsi.master.sesion4.persistence.Users;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @autor drizo
 */
public class XStreamUsersDAO implements IUsersDAO {
    private final File file;
    private XStream xStream;

    public XStreamUsersDAO(File file) {
        this.file = file;
        xStream = new XStream();
        xStream.alias("users", Users.class);
        xStream.alias("user", User.class);
    }

    public XStreamUsersDAO(String filename) {
        this(new File(filename));
    }


    @Override
    public Users getUsers() {
        return (Users) xStream.fromXML(file);
    }

    public void save(Users users) throws Sesion4Exception  {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            xStream.toXML(users, fos);
        } catch (FileNotFoundException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot save users to xml file " +file.getAbsoluteFile(), e);
            throw new Sesion4Exception(e);
        }
    }
}
