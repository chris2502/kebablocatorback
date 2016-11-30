package org.kebablocator.controller;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kebablocator.KebabLocatorBackApplication;
import org.kebablocator.model.Kebab;
import org.kebablocator.model.KebabDao;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ebongue on 20/11/16.
 */
@ActiveProfiles("integration")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = KebabLocatorBackApplication.class, loader = SpringBootContextLoader.class)
public class KebabControllerTest {

    @Autowired
    private KebabController kebabController;
    private Kebab kebab;

    @Autowired
    private KebabDao kebabDao;
    private static final int id  = 301;




    @Test(expected = Exception.class)
    public void should_save_kebab(){
        Kebab kebab = new Kebab(48.862364, 2.349856, "Nabab Kebab", "72 rue rambuteau", "Paris");
        //kebab.setId(id);
        try {
            long nb = kebabDao.count();
            nb = nb +1;
            kebabController.addKebabs(kebab);
            assertThat(kebabController.getAllKebab(), Matchers.hasSize((int)nb) );

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
