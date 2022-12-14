package com.example.coursework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.coursework.databinding.FragmentEditTripBinding;

public class EditTripFragment extends Fragment {

  private FragmentEditTripBinding binding;
  private Database database;
  private int isRisk;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
    LayoutInflater inflater,
    ViewGroup container,
    Bundle savedInstanceState
  ) {
    requireActivity().setTitle("Edit Trip");
    binding = FragmentEditTripBinding.inflate(inflater, container, false);
    database = RoomHelper.initDatabase(getContext());

    int id = getArguments().getInt("id");
    TripEntities trip = database.tripDAO().getTripByID(id);

    binding.txtBudget.setText(trip.getBudget());
    binding.txtDescription.setText(trip.getDescription());
    binding.txtName.setText(trip.getNameOfTrip());
    binding.txtDestination.setText(trip.getDestination());
    binding.txtStartDate.setText(trip.getStartDate());
    binding.txtEndDate.setText(trip.getEndDate());
    binding.txtTransport.setText(trip.getTransport());
    binding.radioGroup.check(
      trip.getRiskAssessment() == 1 ? binding.yes.getId() : binding.no.getId()
    );

    binding.btnAdd.setOnClickListener(view -> {
      String name = binding.txtName.getText().toString();
      String description = binding.txtDescription.getText().toString();
      String budget = binding.txtBudget.getText().toString();
      String destination = binding.txtDestination.getText().toString();
      String endDate = binding.txtEndDate.getText().toString();
      String startDate = binding.txtStartDate.getText().toString();
      String transport = binding.txtTransport.getText().toString();

      if(name.length()==0){
        binding.txtName.requestFocus();
        binding.txtName.setError("Name can not be null");
      }else if(destination.length()==0){
        binding.txtDestination.requestFocus();
        binding.txtDestination.setError("Destination can not be null");
      }
      else if(startDate.length()==0){
        binding.txtStartDate.requestFocus();
        binding.txtStartDate.setError("Start Date can not be null");
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
      }else {
        trip.setBudget(budget);
        trip.setNameOfTrip(name);
        trip.setDescription(description);
        trip.setDestination(destination);
        trip.setTransport(transport);
        trip.setStartDate(startDate);
        trip.setEndDate(endDate);
        trip.setRiskAssessment(isRisk);
        database.tripDAO().update(trip);
        Navigation
                .findNavController(getView())
                .navigate(R.id.action_editTripFragment2_to_mainFragment);
        Toast.makeText(getContext(), "Edit successfully!", Toast.LENGTH_LONG);
      }

    });
    binding.radioGroup.setOnCheckedChangeListener(
      new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
          if (i == R.id.yes) {
            isRisk = 1;
          } else {
            isRisk = 0;
          }
        }
      }
    );
    binding.btnExpenses.setOnClickListener(view -> {
      Bundle bundle = new Bundle();
      bundle.putInt("id", id);
      Navigation.findNavController(getView()).navigate(R.id.action_editTripFragment2_to_expensesFragment,bundle);
    });


    binding.btnDeleteTrip.setOnClickListener(view -> {
      database.tripDAO().deleteTripByID(id);
      requireActivity().onBackPressed();
    });
    return binding.getRoot();
  }
}
