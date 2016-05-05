/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */
package net.srussell.framework.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.srussell.framework.utility.string.vcs.RevisionNumberI;

/**
 * This class manages logging
 */
public class LogMgr
{

    /**
     * vcs revision number of this version of LogMgr 
     */
    protected static String revisionNumber = "$Rev: 65 $";

    /**
     * the wrapped java.util.logging.Logger object
     */
    private Logger wrappedLogger = null;

    /**
     * the class name that instantiated this logger
     */
    private String className = null;



    /**
     * constructor for when logging a classes revision number as well as it's name
     * 
     * @param className String - class name for this logger instance
     * @param revision RevisionNumberI - instance of some vcs revision number
     */
    public LogMgr( String className, RevisionNumberI revision )
    {
        super();
        init( className, revision );
    }



    /**
     * base constructor 
     * 
     * @param className String - class name for this logger instance
     */
    public LogMgr( String className )
    {
        super();
        init( className, null );
    }



    /**
     * initialize the wrapped logger, remove any existing handler(s), and set the handler for this logger 
     * 
     * @param className String - class name on behalf whom we're logging
     * @param revision RevisionNumberI - an optional revision string for the class being logged.  
     */
    private void init( String className, RevisionNumberI revision )
    {
        Logger newLogger = Logger.getLogger( className );

        Formatter newFormatter = ( revision != null ? new RevisionFormatter( revision ) : new StandardFormatter() );

        setSpecificHandler( newLogger, newFormatter );

        this.setWrappedLogger( newLogger );
        this.setClassName( className );
    }



    /**
     * Set a specific handler (console) in a new Logger, removing any inherited handlers and
     * set the specified formatter.
     *
     * @param newLogger Logger - a new JUL logger 
     * @param newFormatter Formatter - the desired formatter for the new handler
     */
    private void setSpecificHandler( Logger newLogger, Formatter newFormatter )
    {
        Handler[] handlers = newLogger.getHandlers();
        for ( int i = 0; i < handlers.length; i++ )
        {
            newLogger.removeHandler( handlers[i] );
        }

        Handler handler = new ConsoleHandler();
        handler.setFormatter( newFormatter );
        handler.setLevel( Level.FINEST );

        newLogger.addHandler( handler );
        newLogger.setUseParentHandlers( false );
    }



    /** 
     * Write a warn message to the with stack trace
     * 
     * @param methodName String - the name of the method making the call
     * @param message String - the message to write to the log
     * @param exception Throwable - causing exception
     */
    public void warn( String methodName, String message, Throwable exception )
    {
        logMessage( Level.WARNING, methodName, message, exception );
    }



    /** 
     * Write a warn message to the
     * 
     * @param methodName String - the name of the method making the call
     * @param message String - the message to write to the log
     */
    public void warn( String methodName, String message )
    {
        logMessage( Level.WARNING, methodName, message, null );
    }



    /** 
     * Write an error logging entry
     * 
     * @param methodName String - the name of the method making the call
     * @param message String - the message to write to the log
     */
    public void error( String methodName, String message )
    {
        logMessage( Level.SEVERE, methodName, message, null );
    }



    /** 
     * Write an error logging entry with stack trace
     * 
     * @param methodName String - the name of the method making the call
     * @param message String - the message to write to the log
     * @param exception Throwable - an exception
     */
    public void error( String methodName, String message, Throwable exception )
    {
        logMessage( Level.SEVERE, methodName, message, exception );
    }



    /** 
     * Write an info message entry
     * 
     * @param methodName String - the name of the method making the call
     * @param message String - the message to write to the log
     */
    public void info( String methodName, String message )
    {
        logMessage( Level.INFO, methodName, message, null );
    }



    /** 
     * Write an info message entry with stack trace
     * 
     * @param methodName String - the name of the method making the call
     * @param message String - the message to write to the log
     * @param exception Throwable - causing exception
     */
    public void info( String methodName, String message, Throwable exception )
    {
        logMessage( Level.INFO, methodName, message, exception );
    }



    /** 
     * Write a debug logging entry
     * 
     * @param methodName String - the name of the method making the call
     * @param message String - the message to write to the log
     */
    public void debug( String methodName, String message )
    {
        logMessage( Level.FINE, methodName, message, null );
    }



    /** 
     * Write a debug logging entry with an array of String to build the message with (if debug logging in enabled)
     * 
     * @param methodName String - the name of the method making the call
     * @param message String - the message to write to the log
     */
    public void debug( String methodName, String... message )
    {
        if ( this.isDebugEnabled() )
        {
            StringBuffer builtUpMessage = new StringBuffer();

            for ( int i = 0; i < message.length; i++ )
            {
                builtUpMessage.append( message[i] );
            }
            logMessage( Level.FINE, methodName, builtUpMessage.toString(), null );
        }
    }



    /** 
     * Write a debug logging entry with stack trace
     * 
     * @param methodName String - the name of the method making the call
     * @param message String - the message to write to the log
     * @param exception Throwable - causing exception
     */
    public void debug( String methodName, String message, Throwable exception )
    {
        logMessage( Level.FINE, methodName, message, exception );
    }



    /** 
     * convenience wrapper for method entry
     * 
     * @param methodName String - the name of the method making the call
     */
    public void entry( String methodName )
    {
        debug( methodName, "entering..." );
    }



    /** 
     * convenience wrapper for method exit
     * 
     * @param methodName String - the name of the method making the call
     */
    public void exit( String methodName )
    {
        debug( methodName, "...exiting" );
    }



    /**
     * call JUL logger to actually log the message. The class name is obtained from the data passed into the constructor.
     * 
     * @param level Level - the verified level of the message to be logged
     * @param methodName String - the method name containing this log message. 
     * @param message String - the log message
     */
    private void logMessage( Level level, String methodName, String message, Throwable exception )
    {
        if ( getWrappedLogger().isLoggable( level ) )
        {
            StringBuffer msg = new StringBuffer( message );
            if ( exception != null )
            {
                msg = new StringBuffer( message + System.getProperty( "line.separator" ) + getStackTrace( exception ) );
            }
            getWrappedLogger().logp( level, getClassName(), methodName, msg.toString() );
        }
    }



    /**
     * @return String - the class name of the instantiated logger
     */
    private String getClassName()
    {
        return ( className != null ? className : "" );
    }



    /**
     * set the className property
     * 
     * @param className String - class name for this logger instance
     */
    private void setClassName( String className )
    {
        this.className = className;
    }



    /**
     * convenience method for knowing if we're at warn level
     * 
     * @return boolean - indicator of current setting
     */
    public boolean isWarnEnabled()
    {
        return getWrappedLogger().isLoggable( Level.WARNING );
    }



    /**
     * convenience method for knowing if we're at error level
     * 
     * @return boolean - indicator of current setting
     */
    public boolean isErrorEnabled()
    {
        return getWrappedLogger().isLoggable( Level.SEVERE );
    }



    /**
     * convenience method for knowing if we're at debug level
     * 
     * @return boolean - indicator of current setting
     */
    public boolean isInfoEnabled()
    {
        return getWrappedLogger().isLoggable( Level.INFO ) || getWrappedLogger().isLoggable( Level.FINE );
    }



    /**
     * convenience method for knowing if we're at debug level
     * 
     * @return boolean - indicator of current setting
     */
    public boolean isDebugEnabled()
    {
        return getWrappedLogger().isLoggable( Level.FINEST );
    }



    /**
     * get the stack trace for the specified exception
     *
     * @return String - The stack trace for this exception
     */
    private String getStackTrace( Throwable exception )
    {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter( stringWriter, true );
        exception.printStackTrace( printWriter );

        return stringWriter.toString();
    }



    /**
     * Returns the wrappedLogger
     *
     * @return Logger - the wrappedLogger property.
     */
    protected Logger getWrappedLogger()
    {

        return this.wrappedLogger;
    }



    /**
     * Sets the wrappedLogger
     *
     * @param wrappedLogger Logger - new value for the wrappedLogger property.
     */
    protected void setWrappedLogger( Logger wrappedLogger )
    {

        this.wrappedLogger = wrappedLogger;
    }

}
