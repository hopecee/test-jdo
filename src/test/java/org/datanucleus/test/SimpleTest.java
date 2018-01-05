package org.datanucleus.test;

import java.util.*;
import org.junit.*;
import javax.jdo.*;

import static org.junit.Assert.*;
import mydomain.model.*;
import org.datanucleus.util.NucleusLogger;

public class SimpleTest
{
    @Test
    public void testSimple()
    {
        NucleusLogger.GENERAL.info(">> test START");
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("MyTest");

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try
        {
            tx.begin();

            // [INSERT code here to persist object required for testing]
            Product prod1 = new Product();
            prod1.setName("Apple");
            Product prod2 = new Product();
            prod2.setName("Appleman");
            Product prod3 = new Product();
            prod3.setName("OrangeApple");
            Product prod4 = new Product();
            prod4.setName("Mango");
            Product prod5 = new Product();
            prod5.setName("Grape");
            Product prod6 = new Product();
            prod6.setName("Pear");

            pm.makePersistent(prod1);
            pm.makePersistent(prod2);
            pm.makePersistent(prod3);
            pm.makePersistent(prod4);
            pm.makePersistent(prod5);
            pm.makePersistent(prod6);
            
            tx.commit();
        }
        catch (Throwable thr)
        {
            NucleusLogger.GENERAL.error(">> Exception in test", thr);
            fail("Failed test : " + thr.getMessage());
        }
        finally 
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }

        
       PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        JDOPersistenceManager jdopm = (JDOPersistenceManager) pm;
        QProduct cand = QProduct.candidate();
        JDOQLTypedQuery<Product> tq = jdopm.newJDOQLTypedQuery(Product.class);
        
        value ="Apple";
        List<Product> prods = null;
        
        try
        {
            tx.begin();

            //prods = tq.filter(cand.eq(value)).executeList(); // works.
            //prods = tq.filter(cand.equalsIgnoreCase(value)).executeList(); // works.
            //prods = tq.filter(cand.startsWith(value)).executeList(); // works.
            prods = tq.filter(cand.ne(value)).executeList(); // does not work.
            // no way to search product name that contains "Apple".
            
            NucleusLogger.GENERAL.info(">> Product List: " + prods);
            
            
        catch (Throwable thr)
        {
            NucleusLogger.GENERAL.error(">> Exception in test", thr);
            fail("Failed test : " + thr.getMessage());
        }
        finally 
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }


        
        
        pmf.close();
        
        
        
        
        
        NucleusLogger.GENERAL.info(">> test END");
    }
}
