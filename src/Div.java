import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-05-17
 */
public class Div extends BinaryExpression implements Expression {
    /**
     * Constructor for Div function.
     *
     * @param left  is the left expression of the Pow function.
     * @param right is the left expression of the Pow function.
     */
    public Div(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * The function returns a string who represented the Div.
     *
     * @return the function returns a string who represented the variable.
     */
    public String toString() {
        return "(" + getExpLeft().toString() + " / " + getExpRight().toString() + ")";
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression left = getExpLeft().assign(var, expression);
        Expression right = getExpRight().assign(var, expression);
        return new Div(left, right);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double right = getExpRight().evaluate(assignment);
        if (right == 0) {
            throw new Exception("divided by 0");
        }
        return getExpLeft().evaluate(assignment) / right;
    }

    @Override
    public double evaluate() throws Exception {
        Map<String, Double> assignment = new TreeMap<String, Double>();
        double right = getExpRight().evaluate(assignment);
        if (right == 0) {
            throw new Exception("divided by 0");
        }
        return getExpLeft().evaluate(assignment) / right;
    }

    @Override
    public Expression differentiate(String var) {
        Expression tempLeft = new Mult(getExpLeft().differentiate(var), getExpRight());
        Expression tempRight = new Mult(getExpLeft(), getExpRight().differentiate(var));
        Expression newLeft = new Minus(tempLeft, tempRight);
        Expression newRight = new Pow(getExpRight(), new Num(2));
        return new Div(newLeft, newRight);
    }

    @Override
    public Expression simplify() {
        //get the simplify of the left and right expressions
        Expression right = getExpRight().simplify();
        Expression left = getExpLeft().simplify();
        //check if you can simplify more
        Num one = new Num(1);
        //check if the expression is X / 1
        if (one.isEqual(right)) {
            return left;
        }
        //create a temp binary expression
        Div temp = new Div(left, right);
        if (temp.isEquals()) {
            return one;
        }
        try {
            //check if the expression in a number
            double value = evaluate();
            return new Num(value);
        } catch (Exception e) {
            return new Div(left, right);
        }
    }
}
