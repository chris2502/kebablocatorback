package org.kebablocator.controller;

import org.hamcrest.Matchers;
import org.junit.After;
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

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ebongue on 20/11/16.
 */
@ActiveProfiles("integration")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = KebabLocatorBackApplication.class, loader = SpringBootContextLoader.class)
public class KebabControllerTest {

    @Autowired
    private KebabController kebabController;
    @Autowired
    private Kebab kebab;

    @Autowired
    private KebabDao kebabDao;
    private static int id;



    @Before
    public void setInit(){
        kebab = new Kebab(48.862364, 2.349856, "Nabab Kebab", "72 rue rambuteau", "75001", "Paris");
        try {
            kebabController.addKebabs(kebab);
            id= kebab.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Test
    public void should_return_kebab(){
        try {
            Kebab keb = kebabController.getKebabById(id);

            assertTrue(keb instanceof  Kebab);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_return_no_kebab(){
        try {
            assertNull(kebabController.getKebabById(10000000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_kebab_by_ville(){
        try {
            List<Kebab> kebList = kebabController.getKebabByVille("Paris");
            for(Kebab keb : kebList) {
                assertThat(keb.getVille(), Matchers.is("Paris"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void should_return_all_kebab(){
        try {
            long nb = kebabDao.count();
            assertThat(kebabController.getAllKebab(), Matchers.hasSize((int)nb));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_delete_kebab(){
        long nb = kebabDao.count();
        nb = nb - 1;
        try {
            kebabController.deleteKebabById(id);
            assertThat(kebabController.getAllKebab(), Matchers.hasSize((int) nb) );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void should_add_kebab(){
        try {
            long nb = kebabDao.count();
            kebabController.addKebabs(kebab);
            assertThat(kebabController.getAllKebab(), Matchers.hasSize((int) nb));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void cleanUp(){
        try {
            kebabController.deleteKebabById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
