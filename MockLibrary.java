import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// Define a simple Calculator interface
interface Calculator {
    int add(int a, int b);
}

// Create a class that implements InvocationHandler to handle method invocations
class CalculatorProxy implements InvocationHandler {
    private Object realCalculator; // The real calculator object

    // Constructor to set the real calculator
    public CalculatorProxy(Object realCalculator) {
        this.realCalculator = realCalculator;
    }

    // Intercept method invocations and add custom behavior
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoking the method: " + method.getName());
        Object result = method.invoke(realCalculator, args); // Invoke the method on the real calculator
        System.out.println("After invoking the method: " + method.getName());
        return result;
    }
}

public class MockLibrary {
    public static void main(String[] args) {
        // Create a real calculator instance
        Calculator realCalculator = new Calculator() {
            @Override
            public int add(int a, int b) {
                return a + b;
            }
        };

        // Create a dynamic proxy for the Calculator interface
        Calculator calculatorProxy = (Calculator) Proxy.newProxyInstance(
                Calculator.class.getClassLoader(),
                new Class[]{Calculator.class},
                new CalculatorProxy(realCalculator)
        );

        // Use the dynamic proxy to perform calculations
        int result = calculatorProxy.add(5, 3);
        System.out.println("Result: " + result);
    }
}
