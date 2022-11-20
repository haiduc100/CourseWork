package com.example.coursework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.coursework.databinding.FragmentCreateTripBinding;
import com.example.coursework.databinding.FragmentMainBinding;

public class CreateTripFragment extends Fragment {

  private FragmentCreateTripBinding binding;
  private Database database;
  private int isRisk = 0;

  @Override
  public View onCreateView(
    LayoutInflater inflater,
    ViewGroup container,
    Bundle savedInstanceState
  ) {
    requireActivity().setTitle("Create Trip");
    binding = FragmentCreateTripBinding.inflate(inflater, container, false);
    database = RoomHelper.initDatabase(getContext());
    binding.btnAdd.setOnClickListener(view -> {
      String name = binding.txtName.getText().toString();
      String description = binding.txtDescription.getText().toString();
      String budget = binding.txtBudget.getText().toString();
      String destination = binding.txtDestination.getText().toString();
      String endDate = binding.txtEndDate.getText().toString();
      String startDate = binding.txtsStartDate.getText().toString();
      String transport = binding.txtTransport.getText().toString();
      if(name.length()==0){
        binding.txtName.requestFocus();
        binding.txtName.setError("Name can not be null");
      }else if(destination.length()==0){
        binding.txtDestination.requestFocus();
        binding.txtDestination.setError("Destination can not be null");
      }
      else if(startDate.length()==0){
        binding.txtsStartDate.requestFocus();
        binding.txtsStartDate.setError("Start Date can not be null");
      }
      else if(endDate.length()==0){
        binding.txtEndDate.requestFocus();
        binding.txtEndDate.setError("End Date can not be null");
      }
      else if(budget.length()==0){
        binding.txtBudget.requestFocus();
        binding.txtBudget.setError("Budget can not be null");
      }
      else if(description.length()==0){
        binding.txtDescription.requestFocus();
        binding.txtDescription.setError("Description can not be null");
      }
      else if(transport.length()==0){
        binding.txtTransport.requestFocus();
        binding.txtTransport.setError("Transport can not be null");
      }
      else {
        TripEntities trip = new TripEntities(
                name,
                destination,
                startDate,
                endDate,
                isRisk,
                transport,
                description,
                budget
        );

        database.tripDAO().insert(trip);
        Navigation
                .findNavController(getView())
                .navigate(R.id.action_createTripFragment_to_mainFragment);

        Toast
                .makeText(getContext(), "Create trip successfully", Toast.LENGTH_LONG)
                .show();
      }

    });
    binding.radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
      if (i == R.id.yes) {
        isRisk = 1;
      } else {
        isRisk = 0;
      }
    });

    return binding.getRoot();
  }
}
