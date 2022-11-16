package com.example.coursework;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface ExpensesDAO {
  @Insert
  void insert(ExpensesEntities... EXPENSES);

  @Update
  void update(ExpensesEntities... EXPENSES);

  @Delete
  void delete(ExpensesEntities... EXPENSES);

  @Query("SELECT * FROM EXPENSES")
  List<ExpensesEntities> getAllExpenses();

  @Query("SELECT * FROM EXPENSES WHERE id =:id")
  List<ExpensesEntities> getExpensesByID(int id);

  @Query("DELETE  FROM EXPENSES WHERE id =:id")
  void deleteExpensesByID(int id);
}
