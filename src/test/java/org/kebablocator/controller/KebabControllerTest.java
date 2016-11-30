package org.kebablocator.controller;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kebablocator.KebabLocatorBackApplication;
import org.kebablocator.model.Kebab;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by ebongue on 20/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = KebabLocatorBackApplication.class, loader = SpringBootContextLoader.class)
public class KebabControllerTest {

    @InjectMocks
    private KebabController kebabController;


    @Test
    public void should_save_kebab(){
        final Kebab kebab = Mockito.mock(Kebab.class, Mockito.RETURNS_DEEP_STUBS);
        kebab.setAdresse("72 rue rambuteau");
        kebab.setLatitude(48.862364);
        kebab.setLongitude(2.349856);
        kebab.setNom("Nabab Kebab");
        kebab.setVille("Paris");
        try {
            kebabController.addKebabs(kebab);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void cleanUp(){

    }
}
