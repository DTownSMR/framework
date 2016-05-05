/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */


package net.srussell.framework.exception;

import net.srussell.framework.assessment.Assessment;

/**
 * Basic application exception. Application exceptions are based on RuntimeException to eliminate the need to clutter method signatures
 * with complex throws clauses. 
 * <br><br>
 * Localized non-runtime exceptions should only be used when it's expected that the call <b><i>can AND SHOULD</i></b>  handle the exception immediately.
 * If there's a possibility that the caller won't/can't handle an exception, then an exception based off of this base class should be
 * used to allow application exceptions to percolate to the highest application level.
 * 
 * <br><br>
 * <center>
 * <a href="http://www.srussell.net"> Copyright &copy; Scott M. Russell, 2010</a>
 * </center>
 */
public class ApplicationException extends RuntimeException
{
    private static final long serialVersionUID = -8812631247709787069L;

    private Assessment exceptionAssessment = null;



    /**
     * base constructor, just a message
     * 
     * @param msg String - the message relating to the exception
     */
    public ApplicationException( String msg )
    {
        super( msg );
    }



    /**
     * constructor for an exception with a message and an associated assessment
     * 
     * @param msg String - the message associated with the exception
     * @param assessment Assessment - an Assessment object detailing the situation surrounding this exception
     */
    public ApplicationException( String msg, Assessment assessment )
    {
        super( msg );
        this.setAssessment( assessment );
    }



    /**
     * constructor for an exception with a message and an exception which caused this exception
     * 
     * @param message String - the message associated with the exception
     * @param cause Throwable - an exception that caused this exception
     */
    public ApplicationException( String message, Throwable cause )
    {
        super( message, cause );
    }



    /**
     * constructor for an exception with a message and an exception which caused this exception
     * 
     * @param message String - the message associated with the exception
     * @param cause Throwable - an exception that caused this exception
     * @param assessment Assessment - an Assessment object detailing the situation surrounding this exception
     */
    public ApplicationException( String message, Throwable cause, Assessment assessment )
    {
        super( message, cause );
        this.setAssessment( assessment );
    }



    private void setAssessment( Assessment assessment )
    {
        exceptionAssessment = assessment;
    }



    public Assessment getMyAssessment()
    {
        return exceptionAssessment;
    }



    /**
     * delivers an Assessment associated with this exception if it exists
     */
    public void deliverAssessment()
    {
        Assessment exceptionAssessment = this.getMyAssessment();
        if ( exceptionAssessment != null )
        {
            exceptionAssessment.deliver();
        }
    }

}
