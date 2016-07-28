/**
 * (c) Copyright Scott M. Russell 2016
 */
package net.srussell.framework.exception.handler;
/*
 * Created by: Scott Russell on Jul 28, 2016 3:01:32 PM for GIT-dtownsmr-framework::net.srussell.framework.exception.handler
 */

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import net.srussell.framework.singleton.Singleton;

/**
 *  Handles is the business logic of handling exceptions. It provides a means to manage a collection of exception handlers via
 *  {@link #setHandler(Class, ExceptionHandler)}. That collection is then used by the {@link #handle(Throwable)} method to determine
 *  which handler to call based on the exception class. A default handler is provided and can be overridden by the {@link #setDefaultHandler(ExceptionHandler)}
 *  method.
 *
 *  @see net.srussell.framework.exception.handler.DefaultHandler
 *
 *    @author Scott Russell
 * <br><br>
 * <center>
 * <a href="http://www.srussell.net"> Copyright &copy; Scott M. Russell, 2016</a>
 * </center>
 */
public class Handlers implements Singleton, ExceptionHandler
{
    private ConcurrentHashMap<Class<?>, ExceptionHandler> handlers = new ConcurrentHashMap<Class<?>, ExceptionHandler>();

    private ExceptionHandler dh = new DefaultHandler();
    
    /**
     * 
     * Adds the passed in exception handler to the collection of handlers. It will replace an existing entry if one exists.
     * 
     * @param exceptionClass Class&lt;?&gt; - the class that this exception handler handles
     * @param thisHandler ExceptionHandler - the handler itself
     */
    public void setHandler( Class<?> exceptionClass, ExceptionHandler thisHandler )
    {
        handlers.put( exceptionClass, thisHandler );
    };
    

    @Override
    public void handle( Throwable t )
    {
        for( Iterator<Entry<Class<?>,ExceptionHandler>> i = handlers.entrySet().iterator() ; i.hasNext() ; )
        {
            Entry<Class<?>,ExceptionHandler> thisEntry = i.next();
            if( thisEntry.getKey() == t.getClass() )
            {
                thisEntry.getValue().handle( t ); 
                return;
            }
        }
        dh.handle( t );
    }


    /**
     * 
     * Enables an application to override the default exception handler. The default handler simply turns an exception
     * into an ApplicationException if it isn't already one, which is a runtime exception, and throws it.
     * 
     * @param eh ExceptionHandler - new default exception handler
     */
    public void setDefaultHandler( ExceptionHandler eh )
    {
        dh = eh;
    }
}

