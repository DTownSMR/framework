/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */


package net.srussell.framework.assessment;

import java.security.InvalidParameterException;
import java.util.Date;

import net.srussell.framework.logging.LogMgr;
import net.srussell.framework.utility.string.vcs.subversion.SvnRevisionNumber;

/**
 * An Assessment encapsulates a short detailed account of an incident that happens in the system and provides a means to control it's delivery. 
 * The encapsulation provides for consistent formatting and standard, application wide, way(s) of delivery. The <code>deliver()</code> method is 
 * used to control exactly when the assessment is delivered.
 * <br><br>
 * A default means of delivery is provided via the standard delivery object that writes to stdout. 
 * <br><br>
 * The default delivery can be overridden by an alternate delivery 
 * object specified in a <code>setDeliverDelegate()</code> method call. See <code>EmailDelivery</code> as an example implementation of how to 
 * override delivery.  
 * <br><br>
 *
 * @see #setDeliverDelegate(AssessmentDeliverDelegate)
 * @see EmailDelivery
 * @version 1.0
 * @author Scott Russell
 * @since 1.0
 * 
 *
 * <br><br>
 * <center>
 * <a href="http://www.srussell.net"> Copyright &copy; Scott M. Russell, 2010</a>
 * </center>
 */
public class Assessment
{
    // last revision
    private final static SvnRevisionNumber revisionNumber = new SvnRevisionNumber( "$LastChangedRevision: 50 $" );
    // standard logging 
    private static LogMgr logger = new LogMgr( "net.srussell.framework.assessment.Assessment", revisionNumber );

    /**
     * the text of the assessment
     */
    protected StringBuffer assessmentText = null;

    /**
     * prefix associated with this assessment
     */
    protected String prefix = "[assessment] ";

    /**
     * true/false indicator if a time stamp should  be used. The default is YES
     */
    protected boolean isTimeStampDesired = true;
    public static final boolean USE_TIME_STAMP = true;
    public static final boolean NO_TIME_STAMP = false;

    /**
     * Date object to hold date/time stamp for this assessment
     */
    protected Date now = null;

    /**
     * this assessments delivery delegate. The default is DefaultAssessmentDelivery
     */
    protected AssessmentDeliverDelegate delegate = new DefaultAssessmentDelivery();



    /**
     * hide the default constructor so no extending classes can create one because it doesn't make sense to create a non-specified assessment object
     */
    private Assessment()
    {
        initialize();
    }



    /**
     * base constructor that accepts the text info of the assessment
     * 
     * @param msg String - the text data of the  assessment  
     */
    public Assessment( String msg )
    {
        super();
        setAssessmentText( msg );
        initialize();
    }



    /**
     * constructor that accepts the assessment text and an alternate delivery delegate object
     * 
     * @param msg 
     * @param delegate
     */
    public Assessment( String msg, AssessmentDeliverDelegate delegate )
    {
        super();
        this.setAssessmentText( msg );
        this.setDeliverDelegate( delegate );
        this.initialize();
    }



    public Assessment( String msg, boolean useTimeStamp )
    {
        super();
        this.setAssessmentText( msg );
        this.setIsTimeStampDesired( useTimeStamp );
        this.initialize();
    }



    public Assessment( String msg, AssessmentDeliverDelegate delegate, boolean useTimeStamp )
    {
        super();
        this.setAssessmentText( msg );
        this.setDeliverDelegate( delegate );
        this.setIsTimeStampDesired( useTimeStamp );
        this.initialize();
    }



    /**
     * The initialize method is designed to be called from a constructor. Controls the flow of the instantiation of an Assessment object.
     */
    protected void initialize()
    {
        logger.entry( "initialize" );

        if ( this.isTimeStampDesired() )
        {
            logger.debug( "initialize", "time stamps are desired...calling setNow(null)..." );
            setNow( null ); // grab the current date/time as a default
        }

        logger.debug( "initialize", "calling extension_init()..." );
        extension_init();

        logger.exit( "initialize" );
    }



    /**
     * This method is empty in this base class but allows extending classes an easy method to plug into the initialize process without having to
     * be concerned with all portions of the initialize process.
     */
    protected void extension_init()
    {
    }



    /**
     * provide a logical toString() method
     */
    @Override
    public String toString()
    {
        return ( this.getAssessmentText().toString() );
    }



    /**
     * The deliver function is used to present the assessment information. In the base class a default delivery delegate is used. 
     * 
     * examples of alternate assessment deliveries are: standard java logging sub-system, mail message, a text file, a database, etc.

     * @since 1.0
     */
    public void deliver()
    {
        this.getDelegate().delegatedDeliver( this );

        return;
    }



    /**
     * This method builds the assessment data string and prefixes the text with the date/time string.  
     * 
     * @return String - the built up assessment text
     */
    public String buildAsseessment()
    {
        logger.entry( "buildAsseessment" );

        StringBuffer theAssessmentText = new StringBuffer( 1024 );

        if ( this.isTimeStampDesired() )
        {
            Date myNow = this.getNow();
            logger.debug( "buildAsseessment", "using date[" + myNow.toString() + "]" );
            theAssessmentText.append( myNow.toString() + ": " );
        }
        theAssessmentText.append( selectMsg() );

        logger.exit( "buildAsseessment" );
        return theAssessmentText.toString();
    }



    /**
     * This method selects and formulates text to use as the assessment. Can be overridden by an extending class to provide special handling of the 
     * assessment text. 
     * 
     * @return String - the assessment text
     */
    public String selectMsg()
    {
        StringBuffer oMessage = new StringBuffer( this.getAssessmentText() );

        String prefix = this.getPrefix();
        if ( prefix != null && prefix.length() > 0 )
        {
            oMessage.insert( 0, prefix );
        }

        return ( oMessage.toString() );
    }



    /**
     * Returns the assessmentText
     *
     * @return StringBuffer - the assessmentText property.
     */
    protected StringBuffer getAssessmentText()
    {
        return assessmentText;
    }



    /**
     * convenience wrapper for setting the assessment text from a String object
     */
    protected void setAssessmentText( String msg )
    {
        if ( msg == null || msg.length() == 0 )
        {
            throw new InvalidParameterException( "invalid assessment text[" + msg + "]" );
        }

        this.setAssessmentText( new StringBuffer( msg ) );
    }



    /**
     * Sets the assessmentText. Must not be null or an empty String.
     *
     * @param assessmentText StringBuffer - new value for the assessmentText property.
     * @throws InvalidParameterException
     */
    protected void setAssessmentText( StringBuffer assessmentText )
    {
        this.assessmentText = assessmentText;
    }



    /**
     * Returns the now. 
     * 
     * <br/><br/><b>Note:</b> Made protected for unit testing
     *
     * @return Date - the now property.
     */
    protected Date getNow()
    {
        return now;
    }



    /**
     * Sets the now Date object. If now is null, the current date is used.
     *
     * @param newNow Date - new value for the now property.
     */
    protected void setNow( Date newNow )
    {
        logger.entry( "setNow" );
        if ( now == null )
        {
            logger.debug( "setNow", "called with null date...using this moment" );
            this.now = new Date();
        }
        else
        {
            logger.debug( "setNow", "called with date...using supplied date" );
            this.now = newNow;
        }
        logger.debug( "setNow", "now is[" + this.now.toString() + "]" );
        logger.exit( "setNow" );
    }



    /**
     * Returns the delegate
     *
     * @return AssessmentDeliverDelegate - the delegate property.
     */
    protected AssessmentDeliverDelegate getDelegate()
    {
        return delegate;
    }



    /**
     * public method for setting the delivery delegate
     * 
     * @param delegate AssessmentDeliverDelegate - new delivery delegate
     */
    public void setDeliverDelegate( AssessmentDeliverDelegate delegate )
    {
        if ( delegate == null )
        {
            throw new InvalidParameterException( "attempted to use an invalid AssessmentDeliverDelegate" );
        }

        this.delegate = delegate;
    }



    /**
     * Returns the useTimeStamp
     *
     * @return boolean - the useTimeStamp property.
     */
    protected boolean isTimeStampDesired()
    {
        return isTimeStampDesired;
    }



    /**
     * setter for the isTimeStampDesired property
     * 
     * 
     * @param newState boolean - new value for isTimeStampDesired
     */
    protected void setIsTimeStampDesired( boolean newState )
    {
        this.isTimeStampDesired = newState;
    }



    /**
     * convenience public setter for the use of a time stamp
     */
    public void useTimeStamp()
    {
        this.setIsTimeStampDesired( true );
    }



    /**
     * convenience public setter for no use of a time stamp
     */
    public void noTimeStamp()
    {
        this.setIsTimeStampDesired( false );
    }



    /**
     * Returns the prefix
     *
     * @return String - the prefix property.
     */
    public String getPrefix()
    {
        return this.prefix;
    }



    /**
     * Sets the prefix
     *
     * @param prefix String - new value for the prefix property.
     */
    public void setPrefix( String prefix )
    {
        this.prefix = prefix;
    }
}
