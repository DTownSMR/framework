/**
 * (c) Copyright Scott M. Russell 2016
 */
package net.srussell.framework.exception.handler;
/*
 * Created by: Scott Russell on Jul 28, 2016 3:58:39 PM for GIT-dtownsmr-framework::net.srussell.framework.exception.handler
 */

import junit.framework.TestCase;
import net.srussell.framework.exception.ApplicationException;

public class HandlersTest extends TestCase implements ExceptionHandler
{
    Handlers handlers = null;
    
    Boolean isHandled;

    protected void setUp() throws Exception
    {
        super.setUp();
        
        handlers = new Handlers();
        isHandled = false;
    }



    public void testHandle()
    {
        handlers.setHandler( ApplicationException.class, this );
        handlers.handle( new ApplicationException("YIKES!") );
        assertTrue("wasn't handled",isHandled);
    }


    /* 
     * the default handler should kick in and turn it into a ApplicationException
     */
    public void testBadHandle()
    {
        handlers.setHandler( ApplicationException.class, this );
        try
        {
            handlers.handle( new Exception("YIKES!") );
            fail("bad handling");
        }
        catch(ApplicationException ae)
        {
            assertEquals("wrong exception", "YIKES!", ae.getMessage());   // expected this
            return;
        }
        fail("bad handle");
    }



    @Override
    public void handle( Throwable t )
    {
        isHandled = true;        
    }

}

