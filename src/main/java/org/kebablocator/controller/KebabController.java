package org.kebablocator.controller;


import org.kebablocator.model.Kebab;
import org.kebablocator.model.KebabDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by fchoudhry on 04/10/16.
 */
@RestController
@RequestMapping("/kebab")
public class KebabController {

    /*@RequestMapping("/kebab")
    @ResponseBody
    public Kebab getById(int id) {
        Kebab kebab = null;

        try {
            kebab = kebabDao.findById(id);
        } catch (Exception e) {
            //return "Kebab Not Found";
        }
        return kebab;
    }*/

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

    @Autowired
    private KebabDao kebabDao;
}
