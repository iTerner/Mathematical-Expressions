import java.util.Map;
import java.util.TreeMap;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-05-17
 */
public class ExpressionsTest {
    /**
     * the function create an expression and do some actions.
     *
     * @throws Exception if during the evaluation we pass a math role or doesn't give value to variable.
     * @param args none
     */
    public static void main(String[] args) throws Exception {
        //Create the expression (2x) + (sin(4y)) + (e^x)
        Expression e = new Plus(new Plus(new Mult(new Num(2), new Var("x")),
                new Sin(new Mult(new Num(4), new Var("y")))),
                new Pow(new Var("e"), new Var("x")));
        //print the expression
        System.out.println(e.toString());
        //Print the value of the expression with (x=2,y=0.25,e=2.71)
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);
        try {
            System.out.println(e.evaluate(assignment));
        } catch (Exception e1) {
            System.out.println(e1.toString());
        }
        //Print the differentiated expression according to x
        Expression de = e.differentiate("x");
        System.out.println(de.toString());
        //Print the value of the differentiated expression according to x with the assignment above.
        System.out.println(de.evaluate(assignment));
        //Print the simplified differentiated expression.
        System.out.println(de.simplify().toString());
    }
}
