import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        final int SIZE_THREAD_POOL = 5;
        final int MAX_MESSAGES = 4;
        List<Callable<Integer>> newMyCallablesCollection = createMyCallablesCollection(SIZE_THREAD_POOL,
                "поток", MAX_MESSAGES);
        final ExecutorService threadPool = Executors.newFixedThreadPool(SIZE_THREAD_POOL);
        List<Future<Integer>> totalCountMessages = threadPool.invokeAll(newMyCallablesCollection);
        Integer resultOfTotalCountMessages = toGetResult(totalCountMessages);
        System.out.println("Общее количество выведенных сообщений = " + resultOfTotalCountMessages);
        threadPool.shutdown();
    }

    public static List<Callable<Integer>> createMyCallablesCollection(int sizeCollection,
                                                             String myCallablePreName,
                                                             int maxMessages) {
        List<Callable<Integer>> newMyCallablesCollection = new ArrayList<>();
        for (int i = 0; i < sizeCollection; i++) {
            String name = myCallablePreName + " " + (i + 1);
            MyCallable myCallable = new MyCallable(name, maxMessages);
            newMyCallablesCollection.add(myCallable);
        }
        System.out.println("Потоки успешно созданы!");
        return newMyCallablesCollection;
    }

    public static Integer toGetResult(List<Future<Integer>> totalCountMessages)
            throws ExecutionException, InterruptedException {
        Integer result = 0;
        for (int i = 0 ; i < totalCountMessages.size() ; i++) {
            result = Math.max(result, totalCountMessages.get(i).get());
        }
        return result;
    }

}
