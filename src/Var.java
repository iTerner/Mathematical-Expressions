import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-05-17
 */
public class Var implements Expression {
    private String v;

    /**
     * Constructor for var.
     *
     * @param variable is the variable.
     */
    public Var(String variable) {
        this.v = variable;
    }

    /**
     * The function returns the variable.
     *
     * @return the function returns the variable.
     */
    public String getVar() {
        return v;
    }

    /**
     * The function returns a string who represented the variable.
     *
     * @return the function returns a string who represented the variable.
     */
    public String toString() {
        return this.v;
    }

    @Override
    public double evaluate() throws Exception {
        throw new Exception("variable doesn't get any value");
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        //check the edge cases
        if (assignment == null) {
            throw new NullPointerException();
        }
        if (assignment.get(this.v) == null) {
            throw new Exception("variable doesn't exist");
        }
        return assignment.get(this.v);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        //check the edge cases
        if (expression == null) {
            return null;
        }
        if (var.equals(this.v)) {
            return expression;
        }
        return this;
    }

    @Override
    public List<String> getVariables() {
        List<String> l = new LinkedList<String>();
        l.add(this.v);
        return l;
    }

    @Override
    public Expression differentiate(String var) {
        if (this.v.equals(var)) {
            return new Num(1);
        }
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
