import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-05-17
 */
public class Cos extends UnaryExpression implements Expression {
    /**
     * Constructor for cos.
     *
     * @param expression is the expression inside the sin function.
     */
    public Cos(Expression expression) {
        super(expression);
    }

    /**
     * The function returns a string who represent the function.
     *
     * @return the function returns a string who represent the function.
     */
    public String toString() {
        return "cos(" + getExp().toString() + ")";
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(getExp().assign(var, expression));
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.cos(Math.toRadians(getExp().evaluate(assignment)));
    }

    @Override
    public double evaluate() throws Exception {
        Map<String, Double> assignment = new TreeMap<String, Double>();
        return Math.cos(Math.toRadians(getExp().evaluate(assignment)));
    }

    @Override
    public Expression differentiate(String var) {
        return new Neg(new Mult(getExp().differentiate(var), new Sin(getExp())));
    }

    @Override
    public Expression simplify() {
        Expression exp = getExp().simplify();
        try {
            //check if the expression is a number
            double value = evaluate();
            return new Num(value);
        } catch (Exception e) {
            return new Cos(exp);
        }
    }
}
