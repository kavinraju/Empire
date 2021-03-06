package com.example.empire.ui.civilization;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.empire.R;
import com.example.empire.databinding.FragmentCivilizationBinding;
import com.example.empire.ui.civilization.model.CivilizationModel;
import com.example.empire.utils.State;
import com.example.empire.utils.Utility;

import java.util.List;

public class CivilizationFragment extends Fragment {

    private static final String LOG = CivilizationFragment.class.getSimpleName();

    private FragmentCivilizationBinding binding;
    private CivilizationViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_civilization, container, false
        );

        // Set the Lifecycle Owner of the DataBinding object
        binding.setLifecycleOwner(this);

        // Initialize the ViewModel

        viewModel = new ViewModelProvider(this,
                new CivilizationViewModelFactory(getActivity().getApplication()))
                .get(CivilizationViewModel.class);

        // call to fetch teh civilization details
        viewModel.fetchCivilizationDetails(Utility.isNetworkAvailable(getContext()));

        // Set up network retry button OnClickListener
        binding.btnRetry.setOnClickListener(v -> {
            viewModel.fetchCivilizationDetails(Utility.isNetworkAvailable(getContext()));
        });

        // Observe for the civilization details data
        viewModel.civilizationDetails().observe(getViewLifecycleOwner(), new Observer<State>() {
            @Override
            public void onChanged(State state) {
                // Call the helper method to process based on the State of the response
                processCivilizationDetailsResponse(state);
            }
        });

        return binding.getRoot();
    }

    /**
     * Helper method to show the user that there's no network available
     */
    private void setNetworkErrorUI() {
        binding.progressBarCivilization.setVisibility(View.GONE);
        binding.recyclerViewCivilization.setVisibility(View.GONE);
        binding.tvError.setText(R.string.network_error);
        binding.llNetworkError.setVisibility(View.VISIBLE);
    }

    /**
     * Helper method to set the UI based on the State of the response for the Civilization details fetch
     *
     * @param state
     */
    private void processCivilizationDetailsResponse(State state) {

        Log.d(LOG, String.valueOf(state.status));

        switch (state.status) {
            case LOADING:
                binding.progressBarCivilization.setVisibility(View.VISIBLE);
                binding.recyclerViewCivilization.setVisibility(View.GONE);
                binding.llNetworkError.setVisibility(View.GONE);
                break;
            case SUCCESS:
                binding.progressBarCivilization.setVisibility(View.GONE);
                binding.recyclerViewCivilization.setVisibility(View.VISIBLE);
                binding.llNetworkError.setVisibility(View.GONE);
                setUpCivilizationAdapter((List<CivilizationModel>) state.data);
                break;
            case FAILED:
                binding.progressBarCivilization.setVisibility(View.GONE);
                binding.recyclerViewCivilization.setVisibility(View.GONE);
                binding.tvError.setText(R.string.error);
                binding.llNetworkError.setVisibility(View.VISIBLE);
                Log.d("processResponse", String.valueOf(state.error));
                break;
        }
    }

    /**
     * Helper method to set the CivilizationAdapter
     *
     * @param civilizationModelList
     */
    private void setUpCivilizationAdapter(List<CivilizationModel> civilizationModelList) {
        Log.d(LOG, "Size of data is " + civilizationModelList.size());

        // Show Network Error UI only if network isn't available and also the data isn't available.
        if (!Utility.isNetworkAvailable(getContext()) && civilizationModelList.size() == 0) {
            setNetworkErrorUI();
            return;
        }

        CivilizationAdapter adapter = new CivilizationAdapter();
        binding.recyclerViewCivilization.setAdapter(adapter);
        adapter.submitList(civilizationModelList);
    }
}