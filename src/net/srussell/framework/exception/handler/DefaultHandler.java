/**
 * (c) Copyright Scott M. Russell 2016
 */
package net.srussell.framework.exception.handler;
/*
 * Created by: Scott Russell on Jul 28, 2016 1:44:24 PM for GIT-dtownsmr-framework::net.srussell.framework.exception.handler
 */

import net.srussell.framework.exception.ApplicationException;
/**
 * 
 * The default handler simply throws the exception as an ApplicationException. In essence turning all exceptions 
 * into a RuntimeException instance of ApplicationException.
 *
 *    @author Scott Russell
 * <br><br>
 * <center>
 * <a href="http://www.srussell.net"> Copyright &copy; Scott M. Russell, 2016</a>
 * </center>
 */
public class DefaultHandler implements ExceptionHandler
{

    @Override
    public void handle( Throwable e ) 
    {
        if(e instanceof ApplicationException)
        {
            throw (ApplicationException)e;
        }
        else
        {
            throw new ApplicationException(e.getMessage(), e);
        }

    }

}

