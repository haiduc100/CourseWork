package com.example.coursework;

import android.content.Context;
import androidx.room.Room;

public class RoomHelper {

  public static final String DATABASE_NAME = "DATABASE";

  public static Database initDatabase(Context context) {
    return Room
      .databaseBuilder(context, Database.class, DATABASE_NAME)
      .allowMainThreadQueries()
      .build();
  }
}
