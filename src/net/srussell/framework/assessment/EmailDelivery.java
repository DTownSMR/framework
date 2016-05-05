/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */


package net.srussell.framework.assessment;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;

import net.srussell.framework.logging.LogMgr;

/**
 * EmailDelivery delegate provides email delivery for an assessment.
 * 
 * 
 * <br><br>
 * <pre>
 *     <b>Example:</b>
 *     Assessment ass = new Assessment( "sample assessment, delivered via email" );
 *     EmailDelivery delegate = new EmailDelivery( "smtp_recipient@host.domain",
 *                                                 "an e-mail subject",
 *                                                 "smtp_originator@host.domain" );
 *     ass.setDeliverDelegate( delegate );
 *     ass.deliver();
 * </pre>
 *
 * @see AssessmentDeliverDelegate
 * @see Assessment
 * @version 1.0
 * @author Scott Russell
 * @since 1.0
 *
 * 
 * <center>
 * <a href="http://www.srussell.net"> Copyright &copy; Scott M. Russell, 2010</a>
 * </center>
 */

public class EmailDelivery implements AssessmentDeliverDelegate, Serializable
{
    /**
     */
    protected static final String DEFAULT_SMTP_HOST = "localhost";

    /**
     */
    protected static final String DEFAULT_SUBJECT = "an assessment";

    /**
     * serializable ID
     */
    private static final long serialVersionUID = -4592042368342235598L;

    // standard SPE logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.report.EmailDelivery" );



    /**
     * this method allows specification of recipient, originator, subject and smtphost
     * @param toAddress email recipient
     * @param subject email subject
     * @param fromAddress email originator
     * @param smtpHost mailer
     * @param password
     * @since 0.0.1
     */
    public EmailDelivery( String toAddress, String subject, String fromAddress, String smtpHost, String port, String password )
    {
        this.setToAddress( toAddress );
        this.setSubject( subject );
        this.setFromAddress( fromAddress );
        this.setSmtpHost( smtpHost );
        this.setPassword( password );
        this.smtpPort = port;
    }



    /**
     * this method allows specification of recipient, originator, subject and smtphost
     * @param toAddress email recipient
     * @param subject email subject
     * @param fromAddress email originator
     * @param smtpHost mailer
     * @param password
     * @since 0.0.1
     */
    public EmailDelivery( String toAddress, String subject, String fromAddress, String smtpHost, String password )
    {
        this.toAddress = toAddress;
        this.subject = subject;
        this.fromAddress = fromAddress;
        this.smtpHost = smtpHost;
        this.password = password;
    }



    /**
     * this method allows specification of recipient, originator, subject and smtphost
     * @param toAddress email recipient
     * @param subject email subject
     * @param fromAddress email originator
     * @param smtpHost mailer
     * @since 0.0.1
     */
    public EmailDelivery( String toAddress, String subject, String fromAddress, String smtpHost )
    {
        this.toAddress = toAddress;
        this.subject = subject;
        this.fromAddress = fromAddress;
        this.smtpHost = smtpHost;
    }



    /**
     * this method allows specification of recipient, originator, and subject
     * @param toAddress email recipient
     * @param subject email subject
     * @param fromAddress email originator
     * @since 0.0.1
     */
    public EmailDelivery( String toAddress, String subject, String fromAddress )
    {
        this.toAddress = toAddress;
        this.subject = subject;
        this.fromAddress = fromAddress;
    }



    /**
     * this method allows specification of recipient, and subject
     * @param toAddress email recipient
     * @param subject email subject
     * @since 0.0.1
     */
    public EmailDelivery( String toAddress, String subject )
    {
        this.toAddress = toAddress;
        this.subject = subject;
    }



    /**
     * minimal constructor that supplies default values for from, subject, and smtp server.
     * <br/>
     * it uses the to address as the from address and "localhost" as the SMTP server
     * @param toAddress String - email recipient
     * @since 1.0
     */
    public EmailDelivery( String toAddress )
    {
        this( toAddress, toAddress, DEFAULT_SUBJECT, DEFAULT_SMTP_HOST );
    }



    /**
     * The delegatedDeliver method is called from the Assessment.deliver() method.
     * @see Assessment#deliver
     */
    public void delegatedDeliver( Assessment assessment )
    {
        logger.entry( "delegatedDeliver" );

        logger.debug( "delegatedDeliver", "delegatedDelivered on delegate:" + this.toString() );

        Session session = null;
        Properties properties = System.getProperties();

        if ( this.getSmtpHost() == null )
        {
            logger.debug( "delegatedDeliver", "no SMTP host set...trying to obtain Session from InitialContext" );
            try
            {
                InitialContext ic = new InitialContext();
                session = (Session)ic.lookup( "java:comp/env/smtpHostName" );
            }
            catch( Throwable t )
            {
                logger.error( "delegatedDeliver", "Unexpected exception:", t );
            }
        }
        else
        {
            logger.debug( "delegatedDeliver", "SMTP host set...stashing smtp host in system properties and trying to obtain a Session instance " );
            properties.put( "mail.smtp.host", this.getSmtpHost() );

            Authenticator auth = null;
            if ( this.getPassword() != null )
            {
                auth = new Authenticator( this.getFromAddress(), this.getPassword() );

                properties.setProperty( "mail.smtp.submitter", auth.getPasswordAuthentication().getUserName() );
                properties.setProperty( "mail.smtp.auth", "true" );
                properties.setProperty( "mail.smtp.port", smtpPort );
            }

            session = Session.getInstance( properties, auth );
        }

        try
        {
            MimeMessage msg = buildMessage( assessment, session );
            Transport.send( msg );
        }
        catch( MessagingException e )
        {
            if ( logger.isErrorEnabled() )
            {
                logger.error( "delegatedDeliver", "error in sending e-mail message \n" + e.toString() + "\n" + "was trying to deliver assessment:\n" + assessment.toString() );
            }
        }

        logger.exit( "delegatedDeliver" );
    }



    /**
     * build a valid MimeMessage object from a Report & Session objects 
     * 
     * @param assessment Assessment - the assessment object for this EmailDelivery
     * @param session Session - mail session object
     * @return MimeMessage - the built MimeMessage
     * @throws MessagingException 
     * @throws AddressException 
     */
    protected MimeMessage buildMessage( Assessment assessment, Session session ) throws AddressException, MessagingException
    {
        logger.entry( "buildMessage" );

        MimeMessage msg = new MimeMessage( session );

        msg.setFrom( new InternetAddress( this.getFromAddress() ) );
        msg.setRecipient( Message.RecipientType.TO, new InternetAddress( this.getToAddress() ) );
        if ( this.getReplyToAddress() != null )
        {
            InternetAddress[] anAddressAsArray =
            { new InternetAddress( this.getReplyToAddress() ) };
            msg.setReplyTo( anAddressAsArray );
        }
        msg.setSubject( assessment.getPrefix() + subject );
        msg.setText( assessment.buildAsseessment() );

        logger.debug( "buildMessage", "msg built[" + msg.toString() + "]" );

        logger.exit( "buildMessage" );
        return msg;
    }



    /**
     * 
     * validate a SMTP address by trying to construct an InternetAddress object from it. If an AddressException occurs, use the replacement address. The replacement address is also
     * validated. If both are invalid, null is returned.
     * 
     * @param inAddress String - address to validate
     * @param replacementIfInvalidAddress String - address to use if the inAddress is invalid. (can be null)
     * @return String - the SMTP address to be used. null if both input address are invalid.
     */
    static public String validateSmtpAddress( String inAddress, String replacementIfInvalidAddress )
    {
        if ( inAddress == null )
        {
            if ( replacementIfInvalidAddress != null )
            {
                return EmailDelivery.validateSmtpAddress( replacementIfInvalidAddress, null );
            }
            return null;
        }

        try
        {
            InternetAddress validationAddr = new InternetAddress( inAddress );
            validationAddr.getType(); // just remove the unreferenced compiler warning
        }
        catch( AddressException e )
        {
            if ( replacementIfInvalidAddress != null )
            {
                return EmailDelivery.validateSmtpAddress( replacementIfInvalidAddress, null );
            }
            return null;
        }

        return inAddress;
    }



    public String getFromAddress()
    {
        return EmailDelivery.validateSmtpAddress( fromAddress, null );
    }



    public void setFromAddress( String fromAddress )
    {
        this.fromAddress = fromAddress;
    }



    public String getToAddress()
    {
        return EmailDelivery.validateSmtpAddress( toAddress, null );
    }



    public void setToAddress( String toAddress )
    {
        this.toAddress = toAddress;
    }



    public String getSmtpHost()
    {
        return smtpHost;
    }



    public void setSmtpHost( String smtpHost )
    {
        this.smtpHost = smtpHost;
    }



    public String getSubject()
    {
        return subject;
    }



    public void setSubject( String subject )
    {
        this.subject = subject;
    }



    public String getReplyToAddress()
    {

        return EmailDelivery.validateSmtpAddress( replyToAddress, null );
    }



    public void setReplyToAddress( String ioReplyToAddress )
    {

        replyToAddress = ioReplyToAddress;
    }



    protected String getPassword()
    {
        return password;
    }



    protected void setPassword( String newPassword )
    {
        password = newPassword;
    }

    /**
     * defaults to "not_specified"
     */
    private String fromAddress = "not_specified";

    /**
     * no default smtpHost, will use whatever is configured
     */
    private String smtpHost = null;

    /**
     * the subject of the email. defaults to an empty string
     */
    private String subject = "";

    /**
     * must be specified
     */
    private String toAddress = null;

    /**
     * defaults to nothing. if still null @ time of Assessment, field is NOT set
     */
    private String replyToAddress = null;

    /**
     * password for sending email. defaults to null (re: NOT specified)
     */
    private String password = null;

    private String smtpPort = "25";



    @Override
    public String toString()
    {
        StringBuffer me = new StringBuffer();

        me.append( "\n" );
        me.append( "to address[" + this.getToAddress() + "]\n" );
        me.append( "reply-to address[" + this.getReplyToAddress() + "]\n" );
        me.append( "from address[" + this.getFromAddress() + "]\n" );
        me.append( "subject[" + this.getSubject() + "]\n" );
        me.append( "smtp server[" + this.getSmtpHost() + "]\n" );
        me.append( "password[" + this.getPassword() + "]\n" );

        return me.toString();
    }

    /**
     * inner-class for authentication
     */
    private class Authenticator extends javax.mail.Authenticator
    {
        private final PasswordAuthentication authentication;



        public Authenticator( String username, String password )
        {
            authentication = new PasswordAuthentication( username, password );
        }



        @Override
        protected PasswordAuthentication getPasswordAuthentication()
        {
            return authentication;
        }
    }

}
