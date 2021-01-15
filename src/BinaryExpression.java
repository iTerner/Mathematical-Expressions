import java.util.LinkedList;
import java.util.List;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-05-17
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression expLeft;
    private Expression expRight;

    /**
     * Constructor for BinaryExpression.
     *
     * @param left  is the left expression of the BinaryExpression.
     * @param right is the left expression of the BinaryExpression.
     */

    public BinaryExpression(Expression left, Expression right) {
        this.expRight = right;
        this.expLeft = left;
    }

    /**
     * The function returns the right expression.
     *
     * @return the function returns the right expression.
     */
    protected Expression getExpRight() {
        return expRight;
    }

    /**
     * The function returns the left expression.
     *
     * @return the function returns the left expression.
     */
    protected Expression getExpLeft() {
        return expLeft;
    }

    /**
     * The function returns a list of the variables in the BinaryExpression.
     *
     * @return the function returns a list of the variables in the expression.
     */
    public List<String> getVariables() {
        //get the variables from the left and the right Expressions
        List<String> listLeft = this.expLeft.getVariables();
        List<String> listRight = this.expRight.getVariables();
        for (String s : listRight) {
            //check if the variables in the right variable list isn't inside the left variable list
            if (!listLeft.contains(s)) {
                //add the variable to the left Expression list
                listLeft.add(s);
            }
        }
        List<String> list = new LinkedList<String>();
        //remove all the duplicate variables from the left list and put the on the list
        for (String s : listLeft) {
            if (!list.contains(s)) {
                list.add(s);
            }
        }
        //return the variable list
        return list;
    }

    /**
     * The function checks if the left expression equals to the right expression.
     *
     * @return the function returns true if the left expression equals to the right expression, otherwise false.
     */
    public boolean isEquals() {
        return getExpLeft().toString().equals(getExpRight().toString());
    }
}
