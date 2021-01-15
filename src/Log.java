import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-05-17
 */
public class Log extends BinaryExpression implements Expression {
    /**
     * Constructor for Log function.
     *
     * @param left  is the left expression of the Pow function.
     * @param right is the left expression of the Pow function.
     */
    public Log(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * The function returns a string who represented the Log.
     *
     * @return the function returns a string who represented the variable.
     */
    public String toString() {
        return "log(" + getExpLeft().toString() + ", " + getExpRight().toString() + ")";
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression left = getExpLeft().assign(var, expression);
        Expression right = getExpRight().assign(var, expression);
        return new Log(left, right);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double right = getExpRight().evaluate(assignment);
        double left = getExpLeft().evaluate(assignment);
        //check the edge case
        if (right <= 0 || left <= 0) {
            throw new Exception("negative values cannot be inside the log function");
        }
        if (left == 1) {
            throw new Exception("One cannot be the base of the logarithm");
        }
        return (Math.log10(right)) / (Math.log10(left));
    }

    @Override
    public double evaluate() throws Exception {
        Map<String, Double> assignment = new TreeMap<String, Double>();
        double right = getExpRight().evaluate(assignment);
        double left = getExpLeft().evaluate(assignment);
        //check the edge case
        if (right <= 0 || left <= 0) {
            throw new Exception("negative values cannot be inside the log function");
        }
        if (left == 1) {
            throw new Exception("One cannot be the base of the logarithm");
        }
        return (Math.log10(right)) / (Math.log10(left));
    }

    @Override
    public Expression differentiate(String var) {
        Expression e1 = new Log(new Var("e"), getExpLeft());
        Expression newRight = new Pow(e1, new Num(2));
        Expression e = new Mult(e1, getExpRight().differentiate(var));
        Expression e2 = new Log(new Var("e"), getExpRight());
        Expression ee = new Mult(getExpLeft().differentiate(var), e2);
        Expression newLeft = new Minus(new Div(e, getExpRight()), new Div(ee, getExpLeft()));
        return new Div(newLeft, newRight);
    }

    @Override
    public Expression simplify() {
        //get the simplify of the left and right expressions
        Expression right = getExpRight().simplify();
        Expression left = getExpLeft().simplify();
        //check if you can simplify more
        //create a temp Binary Expression
        Log temp = new Log(left, right);
        //check if the expression is log(x, x)
        if (temp.isEquals()) {
            return new Num(1);
        }
        try {
            //check if the Expression is a number
            double value = evaluate();
            return new Num(value);
        } catch (Exception e) {
            return new Log(left, right);
        }

    }
}
