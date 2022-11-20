package com.example.coursework;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.coursework.databinding.FragmentCreateExpensesBinding;

public class CreateExpensesFragment extends Fragment {
    private FragmentCreateExpensesBinding binding;
    private Database database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        requireActivity().setTitle("Create Expense");
        binding = FragmentCreateExpensesBinding.inflate(inflater,container,false);
        database = RoomHelper.initDatabase(getContext());

        int id = getArguments().getInt("id");
        TripEntities tripEntities = database.tripDAO().getTripByID(id);

        binding.btnAExpense.setOnClickListener(view ->
        {

            String amount = binding.txtAmount.getText().toString();
            String date = binding.txtDate.getText().toString();
            String type= binding.txtType.getText().toString();
            String desc = binding.txtDesc.getText().toString();
            if(type.length()==0){
                binding.txtType.requestFocus();
                binding.txtType.setError("Type can not be null");
            }else if(amount.length()==0){
                binding.txtAmount.requestFocus();
                binding.txtAmount.setError("Amount can not be null");
            }else if(date.length()==0){
                binding.txtDate.requestFocus();
                binding.txtDate.setError("Date can not be null");
            }else if(desc.length()==0){
                binding.txtDesc.requestFocus();
                binding.txtDesc.setError("Description can not be null");
            }else{
                ExpensesEntities expenses = new ExpensesEntities(tripEntities, type,amount,date,desc);
                expenses.setTripEntities(tripEntities);

                database.expensesDAO().insert(expenses);
                requireActivity().onBackPressed();
            }
        });

        return binding.getRoot();
    }
}