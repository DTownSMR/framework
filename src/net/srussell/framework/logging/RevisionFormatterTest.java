/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.logging;

import net.srussell.framework.utility.string.vcs.subversion.SvnRevisionNumber;

/**
 */
public class RevisionFormatterTest extends BaseFormatterTest
{

    private static final String EXPECTED_CLASS_NAME = A_CLASS_NAME + "[12]";
    private String testRevision = "$Rev: 12 $";
    private RevisionFormatter rf = new RevisionFormatter( new SvnRevisionNumber( testRevision ) );



    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }



    /**
     * Test method for {@link net.srussell.framework.logging.RevisionFormatter#getDisplayClassName(java.util.logging.LogRecord)}.
     */
    public void testGetDisplayClassName()
    {
        assertEquals( "unexpected class name", EXPECTED_CLASS_NAME, rf.getDisplayClassName( lr ) );
    }
}
