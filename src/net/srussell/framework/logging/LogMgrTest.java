/**
 * (c) Copyright  Scott M. Russell.  2016
 *     All rights reserved.
 */
package net.srussell.framework.logging;
/*
 * Created by: Scott Russell on May 5, 2016 12:30:07 PM for framework-for-git::net.srussell.framework.logging
 */

import junit.framework.TestCase;
import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.logging.Level;

public class LogMgrTest extends TestCase implements Filter
{
    private static final String LOG_MESSAGE = "a log message";
    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.logging.LogMgrTest" );

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        Filter newFilter = this;
        logger.getWrappedLogger().setFilter( newFilter );
    }

    @Override
    public boolean isLoggable( LogRecord record )
    {
        return false; // make sure we don't actually log anything.
    }

    public void testWarnStringStringThrowable()
    {
        logger.warn( "StringStringThrowable", LOG_MESSAGE, null );
    }



    public void testWarnStringString()
    {
        logger.warn( "StringString", LOG_MESSAGE );
    }



    public void testErrorStringString()
    {
        logger.error( "StringString", LOG_MESSAGE );
    }



    public void testErrorStringStringThrowable()
    {
        logger.error( "StringStringThrowable", LOG_MESSAGE, null );
    }



    public void testInfoStringString()
    {
        logger.info( "StringString", LOG_MESSAGE );
    }



    public void testInfoStringStringThrowable()
    {
        logger.info( "StringStringThrowable", LOG_MESSAGE, null );
    }



    public void testDebugStringString()
    {
        logger.debug( "StringString", LOG_MESSAGE );
    }



    public void testDebugStringStringArray()
    {
        logger.debug( "StringStringArray", LOG_MESSAGE, null );
    }



    public void testDebugStringStringThrowable()
    {
        logger.getWrappedLogger().setLevel( Level.FINEST );
        logger.debug( "StringStringThrowable", LOG_MESSAGE, new RuntimeException("testing 1-2-3") );
    }



    public void testEntry()
    {
        logger.entry( "log entry" );
    }



    public void testExit()
    {
        logger.exit( "log exit" );
    }



    public void testIsWarnEnabled()
    {
        logger.getWrappedLogger().setLevel( Level.WARNING );
        assertTrue("warn is NOT enabled", logger.isWarnEnabled());
        logger.getWrappedLogger().setLevel( Level.OFF );
        assertFalse("warn is enabled", logger.isWarnEnabled());
    }



    public void testIsErrorEnabled()
    {
        logger.getWrappedLogger().setLevel( Level.SEVERE );
        assertTrue("error is NOT enabled", logger.isErrorEnabled());
        logger.getWrappedLogger().setLevel( Level.OFF );
        assertFalse("error is enabled", logger.isErrorEnabled());
    }



    public void testIsInfoEnabled()
    {
        logger.getWrappedLogger().setLevel( Level.FINE ); // info level works with ALL, FINE* 
        assertTrue("info is NOT enabled", logger.isInfoEnabled());
        logger.getWrappedLogger().setLevel( Level.OFF );
        assertFalse("info is enabled", logger.isInfoEnabled());
    }



    public void testIsDebugEnabled()
    {
        logger.getWrappedLogger().setLevel( Level.FINEST );
        assertTrue("debug is NOT enabled", logger.isDebugEnabled());
        logger.getWrappedLogger().setLevel( Level.OFF );
        assertFalse("debug is enabled", logger.isDebugEnabled());
    }

}

