package com.example.demad.a2msjokes;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.demad.myapplication.backend.myApi.MyApi;
import com.example.liband2msjokes.libandActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

class EndpointAsyncTask extends AsyncTask<JokesFragment, Void, String> {
    private static MyApi myApiService = null;
    JokesFragment jokesFragment = new JokesFragment();
    private Context context;

    @Override
    protected String doInBackground(JokesFragment... params) {
        jokesFragment = params[0];
        context = jokesFragment.getActivity();
        if (myApiService == null) {  // Only do this once
           /* MyApi.Builder builder = new
                    MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // ­ 10.0.2.2 is localhost's IP address in Android emulator
                    // ­ turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/spi/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?>
                                                       abstractGoogleClientRequest) {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });*/
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport()
                    , new AndroidJsonFactory(), null)
                    .setRootUrl("https://jokesinno.appspot.com/_ah/spi/");
            // end options for devappserver
            myApiService = builder.build();
        }
        try {
            return myApiService.tellJokes().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(context, libandActivity.class);
        intent.putExtra(libandActivity.KEY_JOKES, result);
        context.startActivity(intent);
        jokesFragment.loadedJoke = result;
        jokesFragment.displayJokeActivity();
    }
}
