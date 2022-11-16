package com.example.coursework;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TRIPS")
public class TripEntities {

  @PrimaryKey(autoGenerate = true)
  int TripId;

  String NameOfTrip;
  String Destination;
  String StartDate;
  String EndDate;
  int RiskAssessment;
  String Transport;
  String Description;
  String Budget;

  public TripEntities(
    int tripId,
    String nameOfTrip,
    String destination,
    String startDate,
    String endDate,
    int riskAssessment,
    String transport,
    String description,
    String budget
  ) {
    TripId = tripId;
    NameOfTrip = nameOfTrip;
    Destination = destination;
    StartDate = startDate;
    EndDate = endDate;
    RiskAssessment = riskAssessment;
    Transport = transport;
    Description = description;
    Budget = budget;
  }

  public int getTripId() {
    return TripId;
  }

  public void setTripId(int tripId) {
    TripId = tripId;
  }

  public String getNameOfTrip() {
    return NameOfTrip;
  }

  public void setNameOfTrip(String nameOfTrip) {
    NameOfTrip = nameOfTrip;
  }

  public String getDestination() {
    return Destination;
  }

  public void setDestination(String destination) {
    Destination = destination;
  }

  public String getStartDate() {
    return StartDate;
  }

  public void setStartDate(String startDate) {
    StartDate = startDate;
  }

  public String getEndDate() {
    return EndDate;
  }

  public void setEndDate(String endDate) {
    EndDate = endDate;
  }

  public int getRiskAssessment() {
    return RiskAssessment;
  }

  public void setRiskAssessment(int riskAssessment) {
    RiskAssessment = riskAssessment;
  }

  public String getTransport() {
    return Transport;
  }

  public void setTransport(String transport) {
    Transport = transport;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String description) {
    Description = description;
  }

  public String getBudget() {
    return Budget;
  }

  public void setBudget(String budget) {
    Budget = budget;
  }

  public TripEntities(
    String nameOfTrip,
    String destination,
    String startDate,
    String endDate,
    int riskAssessment,
    String transport,
    String description,
    String budget
  ) {
    NameOfTrip = nameOfTrip;
    Destination = destination;
    StartDate = startDate;
    EndDate = endDate;
    RiskAssessment = riskAssessment;
    Transport = transport;
    Description = description;
    Budget = budget;
  }

  public TripEntities() {}
}
