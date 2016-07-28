/**
 * (c) Copyright Scott M. Russell 2016
 */
package net.srussell.framework.exception.handler;
/*
 * Created by: Scott Russell on Jul 28, 2016 1:53:22 PM for GIT-dtownsmr-framework::net.srussell.framework.exception.handler
 */

import junit.framework.TestCase;
import net.srussell.framework.exception.ApplicationException;

public class DefaultHandlerTest extends TestCase
{
    private DefaultHandler dh = new DefaultHandler();

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }



    /**
     * Test method for DefaultHandler#handle(ApplicationException).
     */
    public void testDefaultHandlerOfApplicationException()
    {
        ApplicationException ae = new ApplicationException("my exception");
        try
        {
            dh.handle( ae );
        }
        catch(Throwable t)
        {
            assertEquals( "unexpected exception", ae, t );
        }
    }

    /**
     * Test method for DefaultHandler#handle(Throwable).
     */
    public void testDefaultHandlerOfNonApplicationException()
    {
        Exception e = new Exception("my non-application exception");
        try
        {
            dh.handle( e );
        }
        catch(Throwable t)
        {
            assertTrue( "unexpected exception", t instanceof ApplicationException );
        }
    }

    
}

