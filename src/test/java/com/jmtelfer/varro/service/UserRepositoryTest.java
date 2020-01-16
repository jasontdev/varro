/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmtelfer.varro.service;

import com.jmtelfer.varro.entity.UserCredentials;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Jason Telfer <jason@jasont.dev>
 */

@RunWith(Arquillian.class)
public class UserRepositoryTest {
    
    @Inject
    private UserRepository userRepository;
    
    @Deployment
    public static Archive<?> createDeploymentPackage() {
        return ShrinkWrap.create(WebArchive.class, "UserPersistenceTest.war")
                .addClass(UserCredentials.class)
                .addClass(UserRepository.class)
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence-test.xml"), "persistence.xml");
    }
    
    @Test
    public void injectionTest() {
        Assert.assertNotNull(userRepository);
    } 
}
