package com.example.coursework;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "EXPENSES")
public class ExpensesEntities {

  @PrimaryKey(autoGenerate = true)
  @NonNull
  private int id;

  String Type;
  String Amount;
  String Time;
  String Note;

  public ExpensesEntities() {}

  public ExpensesEntities(
    TripEntities trip,
    String type,
    String amount,
    String time,
    String note
  ) {
    this.tripEntities = trip;
    this.Type = type;
    this.Amount = amount;
    this.Time = time;
    this.Note = note;
  }
  public ExpensesEntities(
          String type,
          String amount,
          String time,
          String note
  ) {
    this.Type = type;
    this.Amount = amount;
    this.Time = time;
    this.Note = note;
  }

  @Embedded
  private TripEntities tripEntities;

  public TripEntities getTripEntities() {
    return tripEntities;
  }

  public void setTripEntities(TripEntities tripEntities) {
    this.tripEntities = tripEntities;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return Type;
  }

  public void setType(String type) {
    Type = type;
  }

  public String getAmount() {
    return Amount;
  }

  public void setAmount(String amount) {
    Amount = amount;
  }

  public String getTime() {
    return Time;
  }

  public void setTime(String time) {
    Time = time;
  }

  public String getNote() {
    return Note;
  }

  public void setNote(String note) {
    Note = note;
  }
}
