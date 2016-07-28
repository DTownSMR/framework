/**
 * (c) Copyright  Scott M. Russell.  2010-2016
 *     All rights reserved.
 */

package net.srussell.framework.assessment;

import junit.framework.TestCase;

/**
 */
public class ExceptionAssessmentEmailTest extends TestCase
{
    private ExceptionAssessment testAssessment = null;
    private Throwable testException = null;


    private static final String JUNIT_ASSESSMENT_TEXT = "this message from JUnit test";

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

        testAssessment = new ExceptionAssessment( JUNIT_ASSESSMENT_TEXT, testException );
    }

    
    /**
     * not a JUnit test per se. Send exception assessment via email for visual inspection
     */
    public void testEmailSendOfExcetpionAssessment()
    {
        testAssessment.setPrefix( "[JUnit] " );

        EmailDelivery emailDelegate = new EmailDelivery( EmailDeliveryTest.TO_ADDRESS, "email delivery of ExceptionAssessment", EmailDeliveryTest.FROM_ADDRESS, EmailDeliveryTest.VALID_SMTP_SERVER, EmailDeliveryTest.PORT, EmailDeliveryTest.PASSWORD );
        testAssessment.noTimeStamp();
        testAssessment.setDeliverDelegate( emailDelegate );
        testAssessment.deliver();

    }
}
