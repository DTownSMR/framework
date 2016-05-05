/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.singleton;

/**
 * These are the public methods created by the singletonwrapper template. 
 * <br><br>
 * <b>Note:</b> that these methods will actually be created as <b><code>static</code></b> &amp; <b><code>synchronized</code></b> but 
 * the Java interface doesn't allow that to be specified here. 
 * <br><br>
 * <b><i>This interface exists solely for the purpose of detailing the methods that the wrapper template creates and is for JavaDoc 
 * purposes only.</i></b>
 * @see net.srussell.framework.singleton.Singleton
 * @see net.srussell.framework.singleton.SingletonFactoryFunctor
 */
public interface SingletonWrapper
{
    public Singleton instance();



    public void setFactory( SingletonFactoryFunctor newFactory );



    public void setInstance( Singleton newInstance );
}
