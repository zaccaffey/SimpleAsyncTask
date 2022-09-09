package com.example.simpleasynctask;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, ArrayList<User>> {

    private WeakReference<TextView> mTextView;
    ProgressBar progressBar;

    SimpleAsyncTask(TextView tv, ProgressBar progressBar)
    {
        mTextView = new WeakReference<>(tv);
        this.progressBar = progressBar;
    }

    @Override
    protected ArrayList<User> doInBackground(Void... voids) {

        for (int i = 0; i < 100; ++i) {
            try {
                Thread.sleep(50);
                publishProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        User user = new User("Zac", 22);
        User user2 = new User("Bob", 25);
        User user3 = new User("Jake", 19);
        User user4 = new User("Cait", 23);
        ArrayList userList = new ArrayList<User>(Arrays.asList(user, user2, user3, user4));

        return userList;
        /*Random random = new Random();
        int n = random.nextInt(11);
        int s = n * 200;
        *//*
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*//*


        return "Awake at last after loading for " + s + " milliseconds!";*/
    }

    @Override
    // String returned from doInBackground is passed here
    protected void onPostExecute(ArrayList<User> user)
    {
        String names = "";
        for (int i = 0; i < user.size(); ++i) {
            names += user.get(i).getName() + "\n";
        }
        mTextView.get().setText(names);

        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }
}
