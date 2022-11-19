package com.example.coursework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.coursework.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

  private MainViewModel MainViewModel;
  private FragmentMainBinding binding;
  private Database database;
  private TripAdapter adapter;

  public static MainFragment newInstance() {
    return new MainFragment();
  }

  @Override
  public View onCreateView(
    @NonNull LayoutInflater inflater,
    @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState
  ) {
    binding = FragmentMainBinding.inflate(inflater, container, false);
    MainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    database = RoomHelper.initDatabase(getContext());
    MainViewModel.setDatabase(database);
      binding.simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String s) {
              return false;
          }

          @Override
          public boolean onQueryTextChange(String s) {
              adapter.getFilter().filter(s);
              return false;
          }
      });
    MainViewModel
      .getAllTrip()
      .observe(
        getViewLifecycleOwner(),
        tripEntities -> {
          adapter = new TripAdapter(tripEntities);

          adapter.setListener(
            (
              (v, post) -> {
                Bundle bundle = new Bundle();
                int tripId = tripEntities.get(post).getTripId();
                bundle.putInt("id", tripId);
                Navigation
                  .findNavController(getView())
                  .navigate(
                    R.id.action_mainFragment_to_editTripFragment2,
                    bundle
                  );
              }
            )
          );
          binding.recyclerview.setAdapter(adapter);
          binding.recyclerview.setLayoutManager(
            new LinearLayoutManager(getActivity())
          );
          binding.recyclerview.addItemDecoration(
            new DividerItemDecoration(
              getContext(),
              DividerItemDecoration.HORIZONTAL
            )
          );
        }
      );
    RecyclerView rv = binding.recyclerview;
    rv.setHasFixedSize(true);
    rv.addItemDecoration(
      new DividerItemDecoration(
        getContext(),
        (new LinearLayoutManager(getContext()).getOrientation())
      )
    );
    binding.fabAddTrip.setOnClickListener(view -> {
      Navigation
        .findNavController(getView())
        .navigate(R.id.action_mainFragment_to_createTripFragment);
    });
    binding.fabDeleteAll.setOnClickListener(view -> {
        database.tripDAO().deleteTrips();
        Toast.makeText(getContext(),"Delete all trip successfully",Toast.LENGTH_LONG).show();
        Navigation.findNavController(getView()).navigate(R.id.mainFragment);
    });
    return binding.getRoot();
  }

}
