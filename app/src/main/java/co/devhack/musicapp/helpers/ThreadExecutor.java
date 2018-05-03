package co.devhack.musicapp.helpers;

import android.os.AsyncTask;

/**
 * Created by krlosf on 5/12/17.
 */

public class ThreadExecutor<T> extends AsyncTask<Void, Void, Object> {
    private Task<T> task;

    public ThreadExecutor() {

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Lo que quiero hacer antes de entrar al hilo secundario
    }

    @Override
    protected Object doInBackground(Void... objects) {
        try {
            //Aqui en el hilo secundario (fuera del MainThread)
            //AQUI NO SE PUEDE LLAMAR A NINGUN METODO QUE INTERACTUE CON LA INTERFACE DE USUARIO
            return task.execute();
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        if(result instanceof Throwable) {
            task.finish((Exception) result, null);
        } else {
            task.finish(null, (T) result);
        }
    }

    public void execute(Task<T> task) {
        this.task = task;
        super.execute();
    }

    public interface Task<T> {
        //Que se va a ejecutar en segundo plano
        T execute() throws Exception;
        //Que se va a hacer cuando termine la ejecucion en segundo plano
        void finish(Exception error, T result);
    }
}
