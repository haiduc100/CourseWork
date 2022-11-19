package com.example.coursework;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class MainViewModel extends ViewModel {

  // TODO: Implement the ViewModel\
  MutableLiveData<List<TripEntities>> listTrip = new MutableLiveData<List<TripEntities>>();
  MutableLiveData<List<ExpensesEntities>> listExpense = new MutableLiveData<List<ExpensesEntities>>();

  Database database;

  public MutableLiveData<List<TripEntities>> getAllTrip() {
    List<TripEntities> trips = database.tripDAO().getAllTrip();
    listTrip.setValue(trips);
    return listTrip;
  }

  public MutableLiveData<List<ExpensesEntities>> getAllExpenses() {
    List<ExpensesEntities> expenses = database.expensesDAO().getAllExpenses();
    listExpense.setValue(expenses);
    return listExpense;
  }
  public MutableLiveData<List<ExpensesEntities>> getExpensesOfTrip(int id) {
    List<ExpensesEntities> expenses = database.expensesDAO().getTripById(id);
    listExpense.setValue(expenses);
    return listExpense;
  }


  public void setDatabase(Database database) {
    this.database = database;
  }
}
