
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        TestLogging testLogging = Ioc.createMyClass();
        testLogging.calculation(1);
        testLogging.calculation(10, 89);
        testLogging.calculation(7,8,"11");
    }
}
