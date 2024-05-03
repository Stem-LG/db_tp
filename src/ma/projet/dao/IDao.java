package ma.projet.dao;

import java.util.List;

import ma.projet.beans.client;

public interface IDao {
    boolean create(client c);

    boolean delete(client c);

    boolean update(client c);

    client findById(int id);

    List<client> findAll();
}
