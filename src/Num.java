import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-05-17
 */
public class Num implements Expression {
    private double num;

    /**
     * Constructor for Num.
     *
     * @param number is the value of the number.
     */
    public Num(double number) {
        this.num = number;
    }

    /**
     * The function returns the num.
     *
     * @return the function returns the num.
     */
    public double getNum() {
        return this.num;
    }

    /**
     * The function returns a string who represented the number.
     *
     * @return the function returns a string who represented the number.
     */
    public String toString() {
        return String.valueOf(this.num);
    }

    @Override
    public double evaluate() throws Exception {
        return this.num;
    }


    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment == null) {
            throw new NullPointerException();
        }
        return this.num;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }


    @Override
    public List<String> getVariables() {
        return new LinkedList<String>();
    }

    @Override
    public Expression differentiate(String var) {
        return new Num(0);
    }


    @Override
    public Expression simplify() {
        return this;
    }

    /**
     * The function checks if the expression is equals to the number.
     * @param e is the expression we want to check if he is equals to the number.
     * @return the function returns true if the expression equals to the number, otherwise false.
     */
    public boolean isEqual(Expression e) {
        //check the edge case
        if (e == null) {
            return false;
        }
        return e.toString().equals(this.toString());
    }
}
