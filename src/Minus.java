import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-05-17
 */
public class Minus extends BinaryExpression implements Expression {
    /**
     * Constructor for Minus function.
     *
     * @param left  is the left expression of the Pow function.
     * @param right is the left expression of the Pow function.
     */
    public Minus(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * The function returns a string who represented the Minus.
     *
     * @return the function returns a string who represented the variable.
     */
    public String toString() {
        return "(" + getExpLeft().toString() + " - " + getExpRight().toString() + ")";
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression left = getExpLeft().assign(var, expression);
        Expression right = getExpRight().assign(var, expression);
        return new Minus(left, right);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return getExpLeft().evaluate(assignment) - getExpRight().evaluate(assignment);
    }

    @Override
    public double evaluate() throws Exception {
        Map<String, Double> assignment = new TreeMap<String, Double>();
        return getExpLeft().evaluate(assignment) - getExpRight().evaluate(assignment);
    }

    @Override
    public Expression differentiate(String var) {
        return new Minus(getExpLeft().differentiate(var), getExpRight().differentiate(var));
    }

    @Override
    public Expression simplify() {
        //get the simplify of the left and right expressions
        Expression right = getExpRight().simplify();
        Expression left = getExpLeft().simplify();
        //check if you can simplify more
        Num zero = new Num(0);
        //check if the left expression equals to zero
        if (zero.isEqual(left)) {
            return new Neg(right);
        }
        //check if the right expression equals to zero
        if (zero.isEqual(right)) {
            return left;
        }
        //check if the expression is X - X
        Minus temp = new Minus(left, right);
        if (temp.isEquals()) {
            return new Num(0);
        }
        try {
            //check if the expression is a number
            double value = evaluate();
            return new Num(value);
        } catch (Exception e) {
            return new Minus(left, right);
        }

    }
}
