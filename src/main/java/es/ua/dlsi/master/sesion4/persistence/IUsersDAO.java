package es.ua.dlsi.master.sesion4.persistence;

import es.ua.dlsi.master.sesion4.Sesion4Exception;

import java.util.List;

/**
 * @autor drizo
 */
public interface IUsersDAO {
    Users getUsers();
    void save(Users users) throws Sesion4Exception;

}
