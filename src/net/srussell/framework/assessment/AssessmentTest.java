/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.assessment;

import java.security.InvalidParameterException;
import java.util.Date;

import junit.framework.TestCase;
import net.srussell.framework.logging.LogMgr;

/**
 * @version 1.0
 * @author Scott Russell
 * @since 1.0
 *
 * <br><br>
 * <center>
 * <a href="http://www.srussell.net"> Copyright &copy; Scott M. Russell, 2010</a>
 * </center>
 */
public class AssessmentTest extends TestCase implements AssessmentDeliverDelegate
{
    // standard logging 
    private static LogMgr logger = new LogMgr( "net.srussell.framework.assessment.AssessmentTest" );

    /*
     * test constants
     */
    private static final String ASSESSMENT_PREFIX = "[assessment] ";
    private static final String JUNIT_ASSESSMENT_TEXT = "this message from JUnit test";

    Assessment ass = null;
    String builtMsg = null;



    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        ass = new Assessment( JUNIT_ASSESSMENT_TEXT );
        builtMsg = null;
    }



    public void testToString() throws Exception
    {
        assertEquals( "toString() doesn't match", JUNIT_ASSESSMENT_TEXT, ass.toString() );
    }



    public void testBasicMsg() throws Exception
    {
        assertNotNull( "message should exist when created with time stamp", ass.getAssessmentText() );

        ass = new Assessment( JUNIT_ASSESSMENT_TEXT, Assessment.NO_TIME_STAMP );
        assertNotNull( "message should exist when created without time stamp", ass.getAssessmentText() );

        ass.deliver(); // not for JUnit testing (no way to verify stdout but useful for code coverage testing
    }



    public void testDefaultPrefix() throws Exception
    {
        ass = new Assessment( JUNIT_ASSESSMENT_TEXT, Assessment.NO_TIME_STAMP );
        assertTrue( "prefix not correctly applied to assessment[" + ass.buildAsseessment() + "]", ass.buildAsseessment().startsWith( ASSESSMENT_PREFIX ) );
    }



    public void testBuildNoTimeStamp() throws Exception
    {
        ass = new Assessment( JUNIT_ASSESSMENT_TEXT, Assessment.NO_TIME_STAMP );
        assertEquals( "assessment not assembled correctly", ASSESSMENT_PREFIX + JUNIT_ASSESSMENT_TEXT, ass.buildAsseessment() );
    }



    public void testBuildWithTimeStamp() throws Exception
    {
        Date assessmentDate = ass.getNow();
        assertEquals( "assessment not assembled correctly", assessmentDate + ": " + ASSESSMENT_PREFIX + JUNIT_ASSESSMENT_TEXT, ass.buildAsseessment() );
    }



    public void testBuildWithDelegate() throws Exception
    {
        ass = new Assessment( JUNIT_ASSESSMENT_TEXT, this );
        ass.deliver();
        assertNotNull( "no built msg", this.builtMsg );

        this.builtMsg = null;
        ass = new Assessment( JUNIT_ASSESSMENT_TEXT, this, false );
        ass.deliver();
        assertNotNull( "no built msg", this.builtMsg );

    }



    public void testBadAssessmentText() throws Exception
    {
        try
        {
            ass = new Assessment( null );
            fail( "should have thrown an exception" );
        }
        catch( InvalidParameterException ipe )
        {
            // we're OK with this}
        }
    }



    public void testBadDelegate() throws Exception
    {
        try
        {
            ass = new Assessment( "delegate exception", null );
            fail( "should have thrown an exception" );
        }
        catch( InvalidParameterException ipe )
        {
            // we're OK with this}
        }
    }



    public void testSettingTimeStamp() throws Exception
    {
        Date assessmentDate = ass.getNow();
        String assessmentWithDate = assessmentDate + ": " + ASSESSMENT_PREFIX + JUNIT_ASSESSMENT_TEXT;
        String assessmentNoDate = ASSESSMENT_PREFIX + JUNIT_ASSESSMENT_TEXT;

        assertEquals( "assessment not assembled correctly", assessmentWithDate, ass.buildAsseessment() );

        ass.noTimeStamp();
        assertEquals( "assessment not assembled correctly", assessmentNoDate, ass.buildAsseessment() );

        ass.useTimeStamp();
        assertEquals( "assessment not assembled correctly", assessmentWithDate, ass.buildAsseessment() );
    }



    /* (non-Javadoc)
     * @see net.srussell.framework.assessment.AssessmentDeliverDelegate#delegatedDeliver(net.srussell.framework.assessment.Assessment)
     */
    @Override
    public void delegatedDeliver( Assessment assessment )
    {
        logger.entry( "delegatedDeliver" );

        this.builtMsg = assessment.buildAsseessment();
        logger.debug( "delegatedDeliver", "JUnit delegatedDeliver called for[" + this.builtMsg + "]" );

        logger.exit( "delegatedDeliver" );
    }
}
