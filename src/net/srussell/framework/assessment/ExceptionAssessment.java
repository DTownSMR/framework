/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.assessment;

import java.security.InvalidParameterException;

import net.srussell.framework.logging.LogMgr;

/**
 */
public class ExceptionAssessment extends Assessment
{

    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.assessment.ExceptionAssessment" );

    private Throwable theException = null;



    public ExceptionAssessment( String msg, Throwable t )
    {
        super( msg );

        this.setTheException( t );

    }



    /* (non-Javadoc)
     * @see net.srussell.framework.assessment.Assessment#buildAsseessment()
     */
    @Override
    public String buildAsseessment()
    {
        logger.entry( "buildAsseessment" );

        StringBuffer assessment = new StringBuffer( super.buildAsseessment() );

        assessment.append( "\n\n" );

        assessment.append( "exception chain:\n" );
        assessment.append( getCause( this.getTheException() ) );

        logger.exit( "buildAsseessment" );
        return assessment.toString();

    }



    /**
     * follow the chain of nested exception capturing the messages of each exception and the final root cause stack trace
     *
     * @param anException Throwable - an exception
     * @return String - data from nested exceptions
     */
    protected String getCause( Throwable anException )
    {
        logger.entry( "getCause" );

        StringBuffer cause = new StringBuffer();
        Throwable rootCause = anException;

        while ( rootCause.getCause() != null )
        {
            cause.append( this.getDisplayMessage( rootCause ) + "\n" );
            rootCause = rootCause.getCause();
        }

        cause.append( "...ROOT CAUSE...\n" );
        cause.append( this.getDisplayMessage( rootCause ) + "\n" );
        StackTraceElement[] stackTrace = rootCause.getStackTrace();
        logger.debug( "getCause", "found [" + stackTrace.length + "] elements in stack trace" );
        for ( int i = 0; i < stackTrace.length; i++ )
        {
            cause.append( stackTrace[i].getClassName() + "." + stackTrace[i].getMethodName() + "(" + stackTrace[i].getFileName() + ":" + stackTrace[i].getLineNumber() + ")\n" );
        }

        logger.exit( "getCause" );
        return cause.toString();

    }



    /**
     * return the Throwable's message if it exists, otherwise return the class name
     *
     * @param t Throwable - an exception
     * @return String - display message
     */
    protected String getDisplayMessage( Throwable t )
    {
        if ( t.getMessage() != null )
        {
            return t.getMessage();
        }

        return t.getClass().getCanonicalName();
    }



    /**
     * Returns the theException
     *
     * @return Throwable - the theException property.
     */
    protected Throwable getTheException()
    {

        return this.theException;
    }



    /**
     * Sets the theException
     *
     * @param theException Throwable - new value for the theException property.
     */
    protected void setTheException( Throwable theException )
    {
        if ( theException == null )
        {
            throw new InvalidParameterException( "illogical to have an assessment for a null exception" );
        }
        this.theException = theException;
    }
}
