/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */


package net.srussell.framework.assessment;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import junit.framework.TestCase;

/**
 * @version 1.0
 * @author Scott Russell
 * @since 1.0
 *
 * <br><br>
 * <center>
 * <a href="http://www.srussell.net"> Copyright &copy; Scott M. Russell, 2010-2016</a>
 * </center>
 */
public class EmailDeliveryTest extends TestCase
{

    /*
     * ---===>>> THESE VARIABLES MUST BE CONFIGURED FOR THIS TO WORK <<<===---
     */
    /**
     * some public variables to facilitate other JUnit tests sending email assessments
     */
    public static final String VALID_SMTP_SERVER = "?";
    public static final String TO_ADDRESS = "?";
    public static final String FROM_ADDRESS = "?";
    public static final String PASSWORD = "?";
    public static final String PORT = "?";
    
    // private test variables
    private static final String REPLY_TO_ADDRESS = "?";
    private static final String VALID_ADDRESS = "?";
    
    // ---===>>> end of required configuration <<<===---
    
    private static final String JUNIT_ASSESSMENT_TEXT = "JUnit assessment!";
    private static final String INVALID_ADDRESS = "not an address";
    private static final String JUNIT_PREFIX = "[JUnit] ";
    private EmailDelivery emailDelegate;



    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        emailDelegate = new EmailDelivery( TO_ADDRESS, JUNIT_ASSESSMENT_TEXT, FROM_ADDRESS, VALID_SMTP_SERVER, PORT, PASSWORD );
    }



    /**
     * @throws MessagingException - exception thrown by Messaging classes
     * @throws AddressException - thrown when there is a malformed address
     */
    public void testBuildMessage() throws AddressException, MessagingException
    {
        final String expectedSubject = JUNIT_PREFIX + JUNIT_ASSESSMENT_TEXT;
        Properties properties = System.getProperties();
        Session session = Session.getInstance( properties, null );

        String msgBody = "test buildMessage()";
        emailDelegate.setReplyToAddress( REPLY_TO_ADDRESS );
        Assessment thisAssessment = new Assessment( msgBody );
        thisAssessment.setPrefix( JUNIT_PREFIX );
        MimeMessage msg = emailDelegate.buildMessage( thisAssessment, session );

        assertEquals( "unexpected 'to' address", TO_ADDRESS, msg.getRecipients( RecipientType.TO )[0].toString() );
        assertEquals( "unexpected 'reply to' address", REPLY_TO_ADDRESS, ( (InternetAddress)msg.getReplyTo()[0] ).getAddress() );
        assertEquals( "unexpected 'from' address", FROM_ADDRESS, ( (InternetAddress)msg.getFrom()[0] ).getAddress() );
        assertEquals( "unexpected 'subject'", expectedSubject, msg.getSubject() );
    }



    public void testValidateSmtpAddress()
    {
        assertEquals( "valid replacement address not returned", EmailDelivery.validateSmtpAddress( INVALID_ADDRESS, VALID_ADDRESS ), VALID_ADDRESS );
        assertEquals( "valid address not returned", EmailDelivery.validateSmtpAddress( VALID_ADDRESS, null ), VALID_ADDRESS );
        assertEquals( "null not returned for two invalid addresses", EmailDelivery.validateSmtpAddress( INVALID_ADDRESS, INVALID_ADDRESS ), null );
    }



    public void testGetFromAddress()
    {
        assertEquals( "setup value not returned", emailDelegate.getFromAddress(), FROM_ADDRESS );

        emailDelegate.setFromAddress( INVALID_ADDRESS );
        assertEquals( "changed value not returned", emailDelegate.getFromAddress(), null );

        emailDelegate.setFromAddress( FROM_ADDRESS );
        assertEquals( "valid value not returned", emailDelegate.getFromAddress(), FROM_ADDRESS );

        emailDelegate.setFromAddress( null );
        assertEquals( "changed value not found", emailDelegate.getFromAddress(), null );
    }



    public void testGetToAddress()
    {
        assertEquals( "setup value not returned", emailDelegate.getToAddress(), TO_ADDRESS );

        emailDelegate.setToAddress( INVALID_ADDRESS );
        assertEquals( "changed value not returned", emailDelegate.getToAddress(), null );

        emailDelegate.setToAddress( TO_ADDRESS );
        assertEquals( "valid value not returned", emailDelegate.getToAddress(), TO_ADDRESS );

        emailDelegate.setToAddress( null );
        assertEquals( "changed value not returned", emailDelegate.getToAddress(), null );
    }



    /**
     * Test method for {@link net.srussell.framework.assessment.EmailDelivery#getReplyToAddress()}.
     */
    public void testGetReplyToAddress()
    {
        assertEquals( "setup value not returned", emailDelegate.getReplyToAddress(), null );

        emailDelegate.setReplyToAddress( INVALID_ADDRESS );
        assertEquals( "changed value not returned", emailDelegate.getReplyToAddress(), null );

        emailDelegate.setReplyToAddress( REPLY_TO_ADDRESS );
        assertEquals( "valid value not returned", emailDelegate.getReplyToAddress(), REPLY_TO_ADDRESS );
    }



    public void testSimpleConstructor()
    {
        EmailDelivery ed = new EmailDelivery( TO_ADDRESS );

        assertEquals( "to address incorrect", TO_ADDRESS, ed.getToAddress() );
    }



    /**
     * This is not a JUnit test per se but allows for manual verification of sending an assessment via an email delegate 
     *
     */
    public void testEmailAssessment()
    {
        String toAddress = TO_ADDRESS;

        Assessment br = new Assessment( "simple assessment message via email" );
        br.setPrefix( "[JUnit] " );
        emailDelegate.setToAddress( toAddress );
        br.setDeliverDelegate( emailDelegate );
        br.deliver();

        br = new Assessment( "more specifications assessment message via email" );
        br.setPrefix( "[JUnit2] " );
        emailDelegate.setToAddress( toAddress );
        emailDelegate.setReplyToAddress( REPLY_TO_ADDRESS );
        br.setDeliverDelegate( emailDelegate );
        br.deliver();
    }

}
