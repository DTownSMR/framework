/**
 * (c) Copyright Scott M. Russell 2016
 */
package net.srussell.framework.exception.handler;
/*
 * Created by: Scott Russell on Jul 28, 2016 3:02:39 PM for GIT-dtownsmr-framework::net.srussell.framework.exception.handler
 */

import net.srussell.framework.singleton.SingletonFactoryFunctor;

/**
 * A static container for a single instance of Handlers
 * <br><br>
 * <center>
 * <a href="http://www.srussell.net"> Copyright &copy; Scott M. Russell, 2016</a>
 * </center>
 */
final public class HandlersWrapper
{

    /**
     * A reference to a possibly alternate factory.
     */

    static private SingletonFactoryFunctor factory  = null;

    /**
     * A reference to the current instance.
     */
    static private Handlers                instance = null;



    /**
     * This is the default factory method.
     * It is called to create a new Handlers when
     * a new instance is needed and _factory is null.
     */
    static private Handlers makeInstance()
    {
        return new Handlers();
    }



    /**
     * This is the accessor for the Handlers.
     * 
     * @return Handlers - the singleton instance of Handlers
     */
    static public synchronized Handlers instance()
    {
        if( null == instance )
        {
            instance = ( null == factory ) ? makeInstance() : (Handlers)factory.makeInstance();
        }
        return instance;
    }



    /**
     * Sets the factory method used to create new instances.
     * You can set the factory method to null to use the default method.
     * 
     * @param newFactory SingletonFactoryFunctor - The Handlers factory
     */
    static public synchronized void setFactory( SingletonFactoryFunctor newFactory )
    {
        factory = newFactory;
    }



    /**
     * Sets the current Handlers instance.
     * You can set this to null to force a new instance to be created the
     * next time instance() is called.
     * 
     * @param newInstance Handlers - The Handlers instance to use.
     */
    static public synchronized void setInstance( Handlers newInstance )
    {
        instance = newInstance;
    }

}
