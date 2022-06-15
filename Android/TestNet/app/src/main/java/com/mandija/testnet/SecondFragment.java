package com.mandija.testnet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.mandija.testnet.databinding.FragmentSecondBinding;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.net.ssl.HandshakeCompletedEvent;

public class SecondFragment extends Fragment {

    private static final int DOWNLOAD_COMPLETED = 0;
    private static final int CHANGE_IMG = 1;
    private FragmentSecondBinding binding;

    private ArrayList<Bitmap> immagini;
    private Random random = new Random();
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull android.os.Message msg) {
            super.handleMessage(msg);
            Log.d("PPLNET", "handleMessage() called with: msg = [" + msg + "] TH: " + Thread.currentThread().getName());
            if(msg.what == DOWNLOAD_COMPLETED) {
                Bitmap new_img = immagini.get(immagini.size()-1);
                binding.ivMain.setImageBitmap(new_img);
                if (immagini.size() == 1) {
                    handler.sendMessageDelayed(handler.obtainMessage(CHANGE_IMG), 7000);
                }
            }
            if (msg.what == CHANGE_IMG) {
                int rnd_id = random.nextInt(immagini.size()-1);
                binding.ivMain.setImageBitmap(immagini.get(rnd_id));
                handler.sendMessageDelayed(handler.obtainMessage(CHANGE_IMG), 7000);
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        immagini = new ArrayList<Bitmap>();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        binding.btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeImage();
            }
        });
    }

    private Executor executor = Executors.newSingleThreadExecutor();

    private void changeImage() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://picsum.photos/500");
                    Bitmap img = BitmapFactory.decodeStream(new BufferedInputStream(url.openStream()));
                    immagini.add(img);
                    Message imgMessage = handler.obtainMessage();
                    imgMessage.what = DOWNLOAD_COMPLETED;
                    handler.sendMessage(imgMessage);

                    /*getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.ivMain.setImageBitmap(img);
                        }
                    });*/
                    /*binding.ivMain.post(new Runnable() {
                        @Override
                        public void run() {
                            binding.ivMain.setImageBitmap(img);
                        }
                    });*/


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}