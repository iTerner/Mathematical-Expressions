import java.util.LinkedList;
import java.util.List;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-05-17
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression expression;

    /**
     * Constructor for UnaryExpression.
     *
     * @param expression is the expression inside the Unary Expression.
     */
    public UnaryExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * The function returns the expression.
     *
     * @return the function returns the expression.
     */
    protected Expression getExp() {
        return expression;
    }


    /**
     * The function returns a list of the variables in the UnaryExpression.
     *
     * @return the function returns a list of the variables in the expression.
     */
    public List<String> getVariables() {
        //returns the list of variables from the expression inside the unary function
        List<String> temp = this.expression.getVariables();
        List<String> list = new LinkedList<String>();
        //remove all the duplicate variables from the left list and put the on the list
        for (String s : temp) {
            if (!list.contains(s)) {
                list.add(s);
            }
        }
        //return the variables list
        return list;
    }
}
