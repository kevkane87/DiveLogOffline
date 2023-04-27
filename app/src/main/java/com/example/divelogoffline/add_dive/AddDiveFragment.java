package com.example.divelogoffline.add_dive;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.divelogoffline.Dive;
import com.example.divelogoffline.R;
import com.example.divelogoffline.databinding.FragmentAddDiveBinding;

public class AddDiveFragment  extends Fragment {

    public AddDiveFragment() {
        super(R.layout.fragment_add_dive);
    }


    private FragmentAddDiveBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddDiveBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Activity activity = requireActivity();
        AddDiveViewModelFactory factory = new AddDiveViewModelFactory(activity.getApplication());
        AddDiveViewModel viewModel = new ViewModelProvider(this, factory).get(AddDiveViewModel.class);

        binding.saveButton.setOnClickListener(v -> {
            if (isDataMissing()) {
                Toast.makeText(getContext(), "missing input data", Toast.LENGTH_SHORT).show();
            } else {

                Dive dive = new Dive();
                dive.date = binding.dateInput.getText().toString();
                dive.diveTitle = binding.diveNameInput.getText().toString();
                dive.diveSite = binding.diveSiteInput.getText().toString();
                dive.bottomTime = Integer.parseInt(binding.bottomTimeInput.getText().toString());
                dive.maxDepth = Integer.parseInt(binding.maxDepthInput.getText().toString());

                viewModel.insert(dive);

        }
    }
        );

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    private boolean isDataMissing(){
        return (binding.diveNameInput.getText().toString().isEmpty()
                || binding.diveSiteInput.getText().toString().isEmpty()
                || binding.bottomTimeInput.getText().toString().isEmpty()
                || binding.dateInput.getText().toString().isEmpty()
                || binding.maxDepthInput.getText().toString().isEmpty());
    }


}
