/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.logging;

/**
 */
public class StandardFormatterTest extends BaseFormatterTest
{

    /**
     */
    private static final String EXPECTED_FORMAT = "[FINE] TID[1] - " + A_CLASS_NAME + "." + A_METHOD_NAME + ": " + TEST_MESSAGE + System.getProperty( "line.separator" );

    StandardFormatter sf = new StandardFormatter();



    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }



    /**
     * Test method for {@link net.srussell.framework.logging.StandardFormatter#format(java.util.logging.LogRecord)}.
     */
    public void testFormatLogRecord()
    {
        assertEquals( "unexpected format", EXPECTED_FORMAT, sf.format( lr ) );
    }



    /**
     * Test method for {@link net.srussell.framework.logging.StandardFormatter#getDisplayClassName(java.util.logging.LogRecord)}.
     */
    public void testGetDisplayClassName()
    {
        assertEquals( "unexpected class name", A_CLASS_NAME, sf.getDisplayClassName( lr ).trim() );
    }
}
