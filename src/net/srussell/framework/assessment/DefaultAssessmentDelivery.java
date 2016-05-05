/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.assessment;

/**
 * provides the default delivery for an assessment to stdout
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
public class DefaultAssessmentDelivery implements AssessmentDeliverDelegate
{

    /* (non-Javadoc)
     * @see net.srussell.framework.assessment.AssessmentDeliverDelegate#delegatedDeliver(net.srussell.framework.assessment.Assessment)
     */
    @Override
    public void delegatedDeliver( Assessment assessment )
    {
        System.out.println( assessment.buildAsseessment() );
    }
}
