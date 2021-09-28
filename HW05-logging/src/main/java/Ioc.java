import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.stream.Collectors;

public class Ioc {

    private Ioc() {
    }

    static TestLogging createMyClass() throws ClassNotFoundException {
        InvocationHandler handler = new Ioc.DemoInvocationHandler(new TestLoggingImpl());
        return (TestLogging) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLogging.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLogging myClass;
        Set<Method> allMethods;

        DemoInvocationHandler(TestLogging myClass) throws ClassNotFoundException {
            this.myClass = myClass;
            allMethods = Arrays.stream(myClass.getClass().getDeclaredMethods())
                    .filter(m -> m.isAnnotationPresent(Log.class))
                    .map(m-> {
                        try {
                            return TestLogging.class.getDeclaredMethod(m.getName(),m.getParameterTypes());
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                        return m;
                    })
                    .collect(Collectors.toSet());
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if (allMethods.contains(method)) {
                System.out.println("executed method: " + method.getName()+" param:"+ Arrays.toString(args));
            }
            return method.invoke(myClass, args);
        }

    }
}
