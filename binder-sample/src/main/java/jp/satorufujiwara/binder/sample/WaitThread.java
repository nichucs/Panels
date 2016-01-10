package jp.satorufujiwara.binder.sample;

import android.os.AsyncTask;

/**
 * Created by nizamcs on 13/12/15.
 */
public class WaitThread extends AsyncTask<Integer,Integer,Void> {
    @Override
    protected Void doInBackground(Integer... params) {
        synchronized (this){
            try {
                this.wait(params!=null && params.length >0 ? params[0] : 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
