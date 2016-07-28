/**
 * (c) Copyright Scott M. Russell 2016
 */
package net.srussell.framework.exception.handler;
/*
 * Created by: Scott Russell on Jul 28, 2016 1:35:32 PM for GIT-dtownsmr-framework::net.srussell.framework.exception.handler
 */
/**
 * 
 * This interface describes the basic API for handling exceptions...the handle method.
 * 
 *
 *    @author Scott Russell 
 * <br><br>
 * <center>
 * <a href="http://www.srussell.net"> Copyright &copy; Scott M. Russell, 2016</a>
 * </center>
 */

public interface ExceptionHandler
{
    /**
     * 
     * call the configured handler for this exception type.
     * 
     * @param t Throwable - an exception
     */
    public void handle(Throwable t);
}

