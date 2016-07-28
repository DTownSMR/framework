/**
 * (c) Copyright Scott M. Russell 2016
 */
package net.srussell.framework.exception.handler;
/*
 * Created by: Scott Russell on Jul 28, 2016 5:04:40 PM for GIT-dtownsmr-framework::net.srussell.framework.exception.handler
 */

import junit.framework.TestCase;
import net.srussell.framework.exception.ApplicationException;
import net.srussell.framework.singleton.Singleton;
import net.srussell.framework.singleton.SingletonFactoryFunctor;

public class HandlersWrapperTest extends TestCase implements ExceptionHandler, SingletonFactoryFunctor
{
    
    Boolean isHandled;
    Boolean madeFactory;

    protected void setUp() throws Exception
    {
        super.setUp();
        
        isHandled = false;
        madeFactory = false;
    }



    public void testInstance()
    {
        HandlersWrapper.instance().setHandler( ApplicationException.class, this );
        HandlersWrapper.instance().handle( new ApplicationException("YIKES!") );
        assertTrue("wasn't handled",isHandled);
    }



    public void testSetInstance()
    {
        ApplicationException ae = new ApplicationException("YIKES!");
        HandlersWrapper.instance().setHandler( ApplicationException.class, this );
        HandlersWrapper.instance().handle( ae );
        assertTrue("wasn't handled",isHandled);
        
        isHandled = false;
        HandlersWrapper.setInstance( null );  // force a new instance so previously set handler will not be there
        
        try
        {
            HandlersWrapper.instance().handle( ae );
            fail("handler was still set");
        }
        catch(ApplicationException ae2)
        {
            
            assertEquals("wrong exception", "YIKES!", ae.getMessage()); // expected to land here   
            return; 
        }
    }

    public void testSetFactory()
    {
        ApplicationException ae = new ApplicationException("YIKES!");
        HandlersWrapper.setFactory( this );  // allow me to make the instance        
        HandlersWrapper.instance().setHandler( ApplicationException.class, this );
        HandlersWrapper.instance().handle( ae );
        assertTrue("wasn't handled", isHandled);
        assertTrue("not MY factory", madeFactory);        
    }



    @Override
    public void handle( Throwable t )
    {
        isHandled = true;        
    }



    @Override
    public Singleton makeInstance()
    {
        madeFactory = true;
        return new Handlers();
    }

}

