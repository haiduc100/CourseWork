package com.example.coursework;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface TripDAO {
  @Insert
  void insert(TripEntities... TRIPS);

  @Update
  void update(TripEntities... TRIPS);

  @Delete
  void delete(TripEntities... TRIPS);

  @Query("SELECT * FROM TRIPS")
  List<TripEntities> getAllTrip();

  @Query("SELECT * FROM TRIPS WHERE TripId =:id")
  TripEntities getTripByID(int id);

  @Query("DELETE  FROM TRIPS WHERE TripId =:id")
  void deleteTripByID(int id);

  @Query("DELETE FROM TRIPS ")
  void deleteTrips();
}
