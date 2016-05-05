/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */


package net.srussell.framework.assessment;

/**
 * This interface provides the method signatures needed to implement a delivery delegate for an Assessment object. A delivery delegate is used to
 * provide an alternate means of delivering an assessment.
 * 
 * @version 1.0
 * @author Scott Russell
 * @since 1.0
 *
 * <br><br>
 * <center>
 * <a href="http://www.srussell.net"> Copyright &copy; Scott M. Russell, 2010</a>
 * </center>
 */

public interface AssessmentDeliverDelegate
{

    public void delegatedDeliver( Assessment assessment );

}
