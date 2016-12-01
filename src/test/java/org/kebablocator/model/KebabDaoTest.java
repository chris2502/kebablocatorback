package org.kebablocator.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kebablocator.KebabLocatorBackApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ebongue on 11/11/16.
 */

@ActiveProfiles("integration")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = KebabLocatorBackApplication.class, loader = SpringBootContextLoader.class)
public class KebabDaoTest {

    @Autowired
    private KebabDao kebabDao;
    @Autowired
    private Kebab kebab;
    private static int id;


    @Before
    public void setInit(){
        kebab = new Kebab(48.862364, 2.349856, "Nabab Kebab", "72 rue rambuteau", "75001", "Paris");
        try {
            kebabDao.save(kebab);
            id= kebab.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test()
    public void should_return_no_kebab(){
        int id = -1;

        assertNull(kebabDao.findById(id));
    }

    @Test
    public void should_valid_if_this_kebab_in_database(){
        assertTrue(kebabDao.exists(id));
    }

    @Test
    public void should_return_all_kebab(){
        List<Kebab> kebabList = kebabDao.findAll();
        assertTrue(kebabDao.findAll() instanceof List);
        Iterator i = kebabList.iterator();
        while(i.hasNext()){
            assertTrue(i.next() instanceof Kebab);
        }

    }

    @After
    public void cleanUp(){
        try {
            if(kebabDao.findById(id) != null ) {
                kebabDao.delete(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
