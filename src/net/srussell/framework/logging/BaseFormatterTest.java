/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.logging;

import java.util.logging.Level;
import java.util.logging.LogRecord;

import junit.framework.TestCase;

/**
 * abstract base class for formatter test classes that creates a valid LogRecord to be used in formatter testing
 */
public abstract class BaseFormatterTest extends TestCase
{

    protected static final String TEST_MESSAGE = "test message";
    protected static final String A_CLASS_NAME = "net.srussell.a.class.name";
    protected static final String A_METHOD_NAME = "aMethodName";

    protected LogRecord lr = null;



    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        lr = new LogRecord( Level.FINE, TEST_MESSAGE );
        lr.setSourceClassName( A_CLASS_NAME );
        lr.setSourceMethodName( A_METHOD_NAME );
    }

}
