/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.utility.collection;

import net.srussell.framework.singleton.SingletonFactoryFunctor;

/*
 * Created by: scott on Oct 16, 2013 6:02:13 PM for framework::net.srussell.framework.utility.collection
 */

/**
 * A static container for a singleton instance of LruCache
 */
final public class LruCacheWrapper
{
    /**
     * A reference to a possibly alternate factory.
     */
    static private SingletonFactoryFunctor factory = null;

    /**
     * A reference to the current instance.
     */
    static private LruCache instance = null;



    /**
     * This is the default factory method.
     * It is called to create a new LruCache when
     * a new instance is needed and _factory is null.
     */
    static private LruCache makeInstance()
    {
        return new LruCache( 0 ); // better not use this! ;-)
    }



    /**
     * This is the accessor for the LruCache.
     * @return LruCache - the instance of the cache
     */
    static public synchronized LruCache instance()
    {
        if ( null == instance )
        {
            instance = ( null == factory ) ? makeInstance() : (LruCache)factory.makeInstance();
        }
        return instance;
    }



    /**
     * Sets the factory method used to create new instances.
     * You can set the factory method to null to use the default method.
     * @param newFactory SingletonFactoryFunctor - The LruCache factory
     */
    static public synchronized void setFactory( SingletonFactoryFunctor newFactory )
    {
        factory = newFactory;
    }



    /**
     * Sets the current LruCache instance.
     * You can set this to null to force a new instance to be created the
     * next time instance() is called.
     * @param newInstance LruCache - The LruCache instance to use.
     */
    static public synchronized void setInstance( LruCache newInstance )
    {
        instance = newInstance;
    }

}
