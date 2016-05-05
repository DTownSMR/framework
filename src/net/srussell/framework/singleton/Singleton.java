/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.singleton;

/**
 * Interface to the Singleton pattern. Any POJO that implements this interface can use the singletonwrapper template code to 
 * create a wrapper class that turns it into a singleton object. 
 * <br><br>
 * The <code>Singleton</code> can be utilized as: <code>{type}Wrapper.instance().{someMethod}()</code>. 
 * <br><br>
 * <b>Note:</b>If special instantiation logic must be used to create the base POJO class, a factory method can be specified and 
 * set in the wrapper class (<code>setFactory(SingletonFactoryFunctor)</code>). The functor passed to the setFactory method must 
 * implement the SingletonFactoryFunctor interface.
 * <br><br>
 * to implement:
 * <ul>
 * <li>create a new class that is desired to be a singleton and implement the <code>Singleton</code> interface in that class</li>
 * <li>create a new wrapper class with the same root name</li>
 * <li>replace the contents of the wrapper class with the "singleton" template</li>
 * <li>replace the highlighted name in the template code with the name of the singleton object</li>
 * <li><i>optionally, the wrapper could implement convenience methods to a public interface for the singleton type allowing simplified usage:</i><br><code>{type}Wrapper.{someMethod}()</code></li>
 * </ul>
 * @see net.srussell.framework.singleton.SingletonFactoryFunctor
 * @see net.srussell.framework.singleton.SingletonWrapper
 */
public interface Singleton
{

}

/** this is the Eclipse Java Editor Template code for the singleton wrapper. 


//
// A static container for a single instance of ${enclosing_type}
//
final public class ${enclosing_type}Wrapper
{

    //
    // A reference to a possibly alternate factory.
    //
    static private SingletonFactoryFunctor factory = null;

    //
    // A reference to the current instance.
    //
    static private ${enclosing_type} instance = null;



    //
    // This is the default factory method.
    // It is called to create a new ${enclosing_type} when
    // a new instance is needed and _factory is null.
    //
    static private ${enclosing_type} makeInstance()
    {
        return new ${enclosing_type}();
    }



    //
    // This is the accessor for the ${enclosing_type}.
    //
    static public synchronized ${enclosing_type} instance()
    {
        if ( null == instance )
        {
            instance = ( null == factory ) ? makeInstance() : (${enclosing_type})factory.makeInstance();
        }
        return instance;
    }



    //
    // Sets the factory method used to create new instances.
    // You can set the factory method to null to use the default method.
    // @param factory SingletonFactoryFunctor - The ${enclosing_type} factory
    //
    static public synchronized void setFactory( SingletonFactoryFunctor newFactory )
    {
        factory = newFactory;
    }



    //
    // Sets the current ${enclosing_type} instance.
    // You can set this to null to force a new instance to be created the
    // next time instance() is called.
    // @param instance ${enclosing_type} - The ${enclosing_type} instance to use.
    //
    static public synchronized void setInstance( ${enclosing_type} newInstance )
    {
        instance = newInstance;
    }

} 

**/
