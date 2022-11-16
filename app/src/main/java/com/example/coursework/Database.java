package com.example.coursework;

import androidx.room.RoomDatabase;

@androidx.room.Database(
  entities = { TripEntities.class, ExpensesEntities.class },
  version = 1
)
public abstract class Database extends RoomDatabase {

  public abstract ExpensesDAO expensesDAO();

  public abstract TripDAO tripDAO();
}
