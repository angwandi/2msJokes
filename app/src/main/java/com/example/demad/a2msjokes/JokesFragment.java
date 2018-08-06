package com.example.demad.a2msjokes;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.liband2msjokes.libandActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokesFragment extends Fragment {
    ProgressBar progressBar = null;
    Button button;
    public boolean testFlag = false;
    public String loadedJoke = null;
    private InterstitialAd interstitialAd;

    public JokesFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MobileAds.initialize(getContext(),
                "ca-app-pub-3940256099942544~3347511713");
        interstitialAd = new InterstitialAd(Objects.requireNonNull(getContext()));
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                progressBar.setVisibility(View.VISIBLE);
                tellJoke();
                showInterstitialAd();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                showInterstitialAd();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        });
        showInterstitialAd();
        View root = inflater.inflate(R.layout.jokes_fragment, container, false);
        AdView adview = root.findViewById(R.id.adv);
        button = root.findViewById(R.id.button);
        progressBar = root.findViewById(R.id.progressbar);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    tellJoke();
                }
            }
        });
        progressBar.setVisibility(View.GONE);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adview.loadAd(adRequest);
        return root;
    }

    private void tellJoke() {
        new EndpointAsyncTask().execute(this);
    }

    public void displayJokeActivity() {
        if (!testFlag) {
            Context context = getActivity();
            Intent intent = new Intent(context, libandActivity.class);
            assert context != null;
            intent.putExtra(context.getString(R.string.envelopeJokes), loadedJoke);
            context.startActivity(intent);
        }
    }

    private void showInterstitialAd() {
        interstitialAd.loadAd(new AdRequest.Builder().build());
    }
}
