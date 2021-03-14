package com.example.empire;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.empire.network.model.CivilizationNetworkModel;
import com.example.empire.network.services.APIClient;
import com.example.empire.network.services.CivilizationAPIService;
import com.example.empire.repository.CivilizationRepo;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CivilizationFragment extends Fragment {

    private final CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_civilization, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*
        *  TEMPORARY CODE
        * */
        /**
         * This is code is added just for checking if the API call is working
         */

        CivilizationRepo repo = new CivilizationRepo();

        disposable.add(
                //apiService.fetchCivilizationsDetails()
                repo.getCivilizationDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<CivilizationNetworkModel>>(){

                    @Override
                    public void onSuccess(List<CivilizationNetworkModel> value) {
                        if (value != null)
                        Log.d("onSuccess", value.toString());
                        else
                            Log.d("onSuccess", "null");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("onSuccess", e.toString());
                    }
                })
        );
        /*
         *  TEMPORARY CODE
         * */
        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);*/
            }
        });
    }
}