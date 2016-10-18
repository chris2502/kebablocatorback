package org.kebablocator.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fchoudhry on 04/10/16.
 */
@Transactional
public interface KebabDao extends CrudRepository<Kebab, Long> {

    public Kebab findById(int id);

    public Kebab findByNom(String nom);

    List<Kebab> findByIdIn(List<Integer> ids);

    List<Kebab> findAll();
}
