import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<Void, Void, String> {

    private MyAsyncTaskListener listener;

    public MyAsyncTask(MyAsyncTaskListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        // Perform background task
        return "Background task complete.";
    }

    @Override
    protected void onPostExecute(String result) {
        // Call listener method when task is complete
        listener.onTaskComplete(result);
    }

    public interface MyAsyncTaskListener {
        void onTaskComplete(String result);
    }
}
