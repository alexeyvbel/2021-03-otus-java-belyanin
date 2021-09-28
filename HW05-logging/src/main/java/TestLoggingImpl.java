

public class TestLoggingImpl implements TestLogging {
    @Override
    public void calculation(int param1) {
        System.out.println("calculation(int param1)");
    }

    @Log
    @Override
    public void calculation(int param1, int param2) {
        System.out.println("calculation(int param1, int param2)");
    }

    @Log
    @Override
    public void calculation(int param1, int param2, String param3) {
        System.out.println("calculation(int param1, int param2, String param3)");
    }


}
