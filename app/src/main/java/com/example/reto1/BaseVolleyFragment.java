package com.example.reto1;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

public class BaseVolleyFragment extends Fragment {

    private VolleyS volley;
    protected RequestQueue fRequestQueue;

    @Override
    public void onCreate(Bundle instancia) {
        super.onCreate(instancia);
        volley = VolleyS.getInstance(getActivity().getApplicationContext());
        fRequestQueue = volley.getRequestQueue();
    }

    public void addToQueue(Request request) {
        if (request != null) {
            request.setTag(this);
            if (fRequestQueue == null) fRequestQueue = volley.getRequestQueue();
            request.setRetryPolicy(new DefaultRetryPolicy(60000,3,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            onPreStartConnection(); //se implementa a continuación
            fRequestQueue.add(request);
        }
    }

    public void onPreStartConnection() {
        getActivity().setProgressBarIndeterminateVisibility(true);
    }

    public void onConnectionFinished() {
        getActivity().setProgressBarIndeterminateVisibility(false);
    }

    public void onConnectionFailed(String error) {
        getActivity().setProgressBarIndeterminateVisibility(false);
        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
    }



}
