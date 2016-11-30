package org.kebablocator.controller;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kebablocator.KebabLocatorBackApplication;
import org.kebablocator.model.Kebab;
import org.kebablocator.model.KebabDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertThat;

/**
 * Created by ebongue on 30/11/16.
 */

@ActiveProfiles("integration")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = KebabLocatorBackApplication.class, loader = SpringBootContextLoader.class)
public class KebabControllerDelete {


    @Autowired
    private KebabController kebabController;
    @Autowired
    private Kebab kebab;

    @Autowired
    private KebabDao kebabDao;
    private static final int id  = 400;


    @Before
    public void setInit(){
        kebab = new Kebab(48.862364, 2.349856, "Nabab Kebab", "72 rue rambuteau", "75001", "Paris");
        kebab.setId(id);
        try {
            kebabController.addKebabs(kebab);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_delete_kebab(){
        long nb = kebabDao.count();
        nb = nb -1;
        try {
            kebabController.deleteKebabById(id);
            assertThat(kebabController.getAllKebab(), Matchers.hasSize((int) nb) );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
