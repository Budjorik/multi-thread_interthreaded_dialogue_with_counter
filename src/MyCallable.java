import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    static int totalMessages;
    String name;
    int maxMessages;

    MyCallable(String name, int maxMessages) {
        this.name = name;
        this.maxMessages = maxMessages;
    }

    @Override
    public Integer call() throws Exception {
        int currentMessage = 0;
        do {
            currentMessage++;
            Thread.sleep(2500);
            System.out.println("Я " + this.name + ". Всем привет!");
        } while (currentMessage < this.maxMessages);
        totalMessages += currentMessage;
        return totalMessages;
    }

}
