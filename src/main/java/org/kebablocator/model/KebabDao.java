package org.kebablocator.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fchoudhry on 04/10/16.
 */
@Repository
public interface KebabDao extends CrudRepository<Kebab, Integer> {


    Kebab findById(int id);

    Kebab findByNom(String nom);

    List<Kebab> findByVille(String ville);

    List<Kebab> findByIdIn(List<Long> ids);

    List<Kebab> findAll();

    void deleteById(int id);
}
