import java.util.List;
import java.util.Map;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-05-17
 */
public interface Expression {
    /**
     * <p>
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     * </p>
     *
     * @param assignment is the values for the variables in the expression.
     * @return the function returns the value of the expression.
     * @throws Exception if during the evaluation we pass a math role or doesn't give value to variable.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A  convenience method. Like the `evaluate(assignment)` method above but uses an empty assignment.
     *
     * @return the function returns the value of the expression.
     * @throws Exception if during the evaluation we pass a math role or doesn't give value to variable.
     */
    double evaluate() throws Exception;

    /**
     * The function returns a list of the variables in the expression.
     *
     * @return the function returns a list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * The function returns a string who represented the Expression.
     *
     * @return the function returns a string who represented the variable.
     */
    String toString();

    /**
     * <p>
     * The function returns a new expression in which all occurrences of the variable var are replaced with the provided
     * expression (Does not modify the current expression).
     * </p>
     *
     * @param var        is the variable we want to modify.
     * @param expression is what we want to put instead of the variable var.
     * @return the function returns the new expression.
     */
    Expression assign(String var, Expression expression);

    /**
     * <p>
     * The function returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     * </p>
     *
     * @param var is the variable we want to find the differentiate.
     * @return the function returns the differentiate relative to 'var'.
     */
    Expression differentiate(String var);

    /**
     * The function returned a simplified version of the current expression.
     *
     * @return the function returns a simplify version of the expression.
     */
    Expression simplify();
}
