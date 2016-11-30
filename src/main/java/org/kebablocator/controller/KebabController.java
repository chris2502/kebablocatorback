package org.kebablocator.controller;


import org.kebablocator.model.Kebab;
import org.kebablocator.model.KebabDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by fchoudhry on 04/10/16.
 */
@RestController
@RequestMapping("/kebab")
public class KebabController {

    @RequestMapping(value = "/{id}"
            , method= RequestMethod.GET)
    public Kebab getKebabById(@PathVariable("id") int id) throws Exception {
        return kebabDao.findById(id);
    }

    @RequestMapping(value = "/"
            , method= RequestMethod.GET)
    public List<Kebab> getAllKebab() throws Exception {
        return kebabDao.findAll();
    }

    @RequestMapping(value = "/ville/{ville}"
            , method= RequestMethod.GET)
    @CrossOrigin()
    public List<Kebab> getKebabByVille(@PathVariable("ville") String ville) throws Exception {
        return kebabDao.findByVille(ville);
    }


    @RequestMapping(value = "/addKebab"
            , method= RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Kebab addKebabs(@RequestBody Kebab kebab) throws Exception {
          return kebabDao.save(kebab);
    }

    @RequestMapping(value = "/addKebabs"
            , method= RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addKebabs(@RequestBody List<Kebab> kebabs) throws Exception {
        for(Kebab kebab : kebabs){
            kebabDao.save(kebab);
        }
    }

    @RequestMapping(value = "/delete/{id}"
            , method= RequestMethod.GET)
    public void deleteKebabById(@PathVariable("id") int id) throws Exception {
        kebabDao.delete(id);
    }

    @Autowired
    private KebabDao kebabDao;
}
