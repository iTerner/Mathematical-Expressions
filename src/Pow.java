import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-05-17
 */
public class Pow extends BinaryExpression implements Expression {
    /**
     * Constructor for Pow function.
     *
     * @param left  is the left expression of the Pow function.
     * @param right is the left expression of the Pow function.
     */
    public Pow(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * The function returns a string who represented the Pow.
     *
     * @return the function returns a string who represented the variable.
     */
    public String toString() {
        return "(" + getExpLeft().toString() + "^" + getExpRight().toString() + ")";
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression left = getExpLeft().assign(var, expression);
        Expression right = getExpRight().assign(var, expression);
        return new Pow(left, right);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double left = getExpLeft().evaluate(assignment);
        double right = getExpRight().evaluate(assignment);
        if (left == 0 && right == 0) {
            throw new Exception("0^0 isn't defined");
        } else if (right < 0 && left < 0) {
            throw new Exception("can't do square of negative number");
        }
        return Math.pow(left, right);
    }

    @Override
    public double evaluate() throws Exception {
        Map<String, Double> assignment = new TreeMap<String, Double>();
        double left = getExpLeft().evaluate(assignment);
        double right = getExpRight().evaluate(assignment);
        if (left == 0 && right == 0) {
            throw new Exception("0^0 isn't defined");
        } else if (right < 0 && left < 0) {
            throw new Exception("can't do square of negative number");
        }
        return Math.pow(left, right);
    }

    @Override
    public Expression differentiate(String var) {
        Expression newLeft = new Pow(getExpLeft(), getExpRight());
        Expression e1 = new Mult(getExpLeft().differentiate(var), new Div(getExpRight(), getExpLeft()));
        Expression e2 = new Mult(getExpRight().differentiate(var), new Log(new Var("e"), getExpLeft()));
        Expression newRight = new Plus(e1, e2);
        return new Mult(newLeft, newRight);
    }

    @Override
    public Expression simplify() {
        //simplify the left and the right expressions
        Expression left = getExpLeft().simplify();
        Expression right = getExpRight().simplify();
        try {
            //check if the Expression is a number
            double value = evaluate();
            return new Num(value);
        } catch (Exception e) {
            return new Pow(left, right);
        }
    }
}
