/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.singleton;

/**
 * the interface that allows the override of the creation of a Singleton
 */
public interface SingletonFactoryFunctor
{
    /**
     * @return Singleton - an instance of a Singleton
     */
    public Singleton makeInstance();
}
