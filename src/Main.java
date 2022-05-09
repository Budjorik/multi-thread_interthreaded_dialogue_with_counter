import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        final int SIZE_THREAD_POOL = 4;
        final int MAX_MESSAGES = 3;
        Callable<Integer>[] myCallablesArray = createMyCallablesArray(SIZE_THREAD_POOL,
                "поток", MAX_MESSAGES);
        final ExecutorService threadPool = Executors.newFixedThreadPool(SIZE_THREAD_POOL);
        Future<Integer> totalCountMessages = null;
        for (int i = 0 ; i < myCallablesArray.length ; i++) {
            totalCountMessages = threadPool.submit(myCallablesArray[i]);
        }
        final Integer resultOfTotalCountMessages = totalCountMessages.get();
        System.out.println("Общее количество выведенных сообщений = " + resultOfTotalCountMessages);
        threadPool.shutdown();
    }

    public static Callable<Integer>[] createMyCallablesArray(int sizeArray,
                                                             String myCallablePreName,
                                                             int maxMessages) {
        Callable<Integer>[] newMyCallablesArray = new MyCallable[sizeArray];
        for (int i = 0 ; i < newMyCallablesArray.length ; i++) {
            String name = myCallablePreName + " " + (i + 1);
            Callable<Integer> myCallable = new MyCallable(name, maxMessages);
            newMyCallablesArray[i] = myCallable;
        }
        System.out.println("Потоки успешно созданы!");
        return newMyCallablesArray;
    }

}
