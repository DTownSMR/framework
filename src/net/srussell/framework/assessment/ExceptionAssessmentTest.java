/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.assessment;

import junit.framework.TestCase;
import net.srussell.framework.logging.LogMgr;

/**
 */
public class ExceptionAssessmentTest extends TestCase
{
    // standard logging 
    private static LogMgr logger = new LogMgr( "net.srussell.framework.assessment.ExceptionAssessmentTest" );

    private Throwable testException = null;

    private ExceptionAssessment testAssessment = null;



    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        try
        {
            try
            {
                try
                {
                    try
                    {
                        testAssessment.getAssessmentText(); // not assigned yet, will cause null pointer exception
                    }
                    catch( Throwable t )
                    {
                        throw new RuntimeException( "outer layer1", t );
                    }
                }
                catch( Throwable t )
                {
                    throw new RuntimeException( "outer layer2", t );
                }
            }
            catch( Throwable t )
            {
                throw new RuntimeException( "outer layer3", t );
            }
        }
        catch( Throwable t )
        {
            testException = t;
        }

        testAssessment = new ExceptionAssessment( "JUnit test", testException );
    }



    /**
     * Test method for {@link net.srussell.framework.assessment.ExceptionAssessment#buildAsseessment()}.
     */
    public void testBuildAsseessment()
    {
        // assessment starts @ 28 to bypass date/time stamp prefix
        assertTrue( "unexpected assessment", testAssessment.buildAsseessment().substring( 28 ).startsWith( ": [assessment] JUnit test\n\nexception chain:\nouter layer3\nouter layer2\nouter layer1\n...ROOT CAUSE...\njava.lang.NullPointerException" ) );
    }



    /**
     * Test method for {@link net.srussell.framework.assessment.ExceptionAssessment#getCause(java.lang.Throwable)}.
     */
    public void testGetCause()
    {
        assertTrue( "unexpected cause", testAssessment.getCause( testException ).startsWith( "outer layer3\nouter layer2\nouter layer1\n...ROOT CAUSE...\njava.lang.NullPointerException" ) );
    }

}
