package co.devhack.musicapp.helpers;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by krlosf on 5/12/17.
 */

public class ThreadExecutor<T> {
    public ThreadExecutor() {

    }

    public void execute(final Task<T> task) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<T> future = executor.submit(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return task.execute();
            }
        });

        try {
            T result = future.get();
            if(result != null && future.isDone()) {
                task.finish(null, result);
            } else {
                task.finish(null, null);
            }
        } catch (InterruptedException | ExecutionException e) {
            task.finish(e, null);
        }
    }

    public interface Task<T> {
        //Que se va a ejecutar en segundo plano
        T execute() throws Exception;
        //Que se va a hacer cuando termine la ejecucion en segundo plano
        void finish(Exception error, T result);
    }
}
