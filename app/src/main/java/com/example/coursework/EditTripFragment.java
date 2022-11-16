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
    binding = FragmentEditTripBinding.inflate(inflater, container, false);
    database = RoomHelper.initDatabase(getContext());

    int id = getArguments().getInt("id");
    TripEntities trip = database.tripDAO().getTripByID(id);

    binding.txtBudget.setText(trip.getBudget());
    binding.txtDescription.setText(trip.getDescription());
    binding.txtName.setText(trip.getNameOfTrip());
    binding.txtDestination.setText(trip.getDestination());
    binding.txtsStartDate.setText(trip.getStartDate());
    binding.txtEndDate.setText(trip.getEndDate());
    binding.txtTransport.setText(trip.getTransport());
    binding.radioGroup.check(
      trip.getRiskAssessment() == 1 ? binding.yes.getId() : binding.no.getId()
    );

    binding.btnAdd.setOnClickListener(view -> {
      trip.setBudget(binding.txtBudget.getText().toString());
      trip.setNameOfTrip(binding.txtName.getText().toString());
      trip.setDescription(binding.txtDescription.getText().toString());
      trip.setDestination(binding.txtDestination.getText().toString());
      trip.setTransport(binding.txtTransport.getText().toString());
      trip.setStartDate(binding.txtsStartDate.getText().toString());
      trip.setEndDate(binding.txtEndDate.getText().toString());

      trip.setRiskAssessment(isRisk);
      database.tripDAO().update(trip);
      Navigation
        .findNavController(getView())
        .navigate(R.id.action_editTripFragment2_to_mainFragment);
      Toast.makeText(getContext(), "Edit successfully!", Toast.LENGTH_LONG);
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
      Navigation.findNavController(getView()).navigate(R.id.action_editTripFragment2_to_expensesFragment);
    });
    return binding.getRoot();
  }
}
