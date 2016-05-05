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
