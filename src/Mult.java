import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-05-17
 */
public class Mult extends BinaryExpression implements Expression {
    /**
     * Constructor for Mult function.
     *
     * @param left  is the left expression of the Pow function.
     * @param right is the left expression of the Pow function.
     */
    public Mult(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * The function returns a string who represented the Mutl.
     *
     * @return the function returns a string who represented the variable.
     */
    public String toString() {
        return "(" + getExpLeft().toString() + " * " + getExpRight().toString() + ")";
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression left = getExpLeft().assign(var, expression);
        Expression right = getExpRight().assign(var, expression);
        return new Mult(left, right);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return getExpLeft().evaluate(assignment) * getExpRight().evaluate(assignment);
    }

    @Override
    public double evaluate() throws Exception {
        Map<String, Double> assignment = new TreeMap<String, Double>();
        return getExpLeft().evaluate(assignment) * getExpRight().evaluate(assignment);
    }

    @Override
    public Expression differentiate(String var) {
        Expression newLeft = new Mult(getExpLeft().differentiate(var), getExpRight());
        Expression newRight = new Mult(getExpLeft(), getExpRight().differentiate(var));
        return new Plus(newLeft, newRight);
    }

    @Override
    public Expression simplify() {
        //get the simplify of the left and right expressions
        Expression right = getExpRight().simplify();
        Expression left = getExpLeft().simplify();
        //check if you can simplify more
        Num zero = new Num(0), one = new Num(1);
        //check if the left expression equals to zero
        if (zero.isEqual(left)) {
            return zero;
        }
        //check if the right expression equals to zero
        if (zero.isEqual(right)) {
            return zero;
        }
        //check if the left expression equals to one
        if (one.isEqual(left)) {
            return right;
        }
        //check if the right expression equals to one
        if (one.isEqual(right)) {
            return left;
        }
        try {
            //check if the expression is a number
            double value = evaluate();
            return new Num(value);
        } catch (Exception e) {
            return new Mult(left, right);
        }
    }
}
