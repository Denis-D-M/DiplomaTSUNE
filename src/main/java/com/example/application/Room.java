package com.example.application;

import java.util.List;

public class Room {
  private final int num;
  private final List<Table> tables;

  public Room(int num, List<Table> tables) {
    this.num = num;
    this.tables = tables;
  }

  public int getNum() {
    return num;
  }

  public List<Table> getTables() {
    return tables;
  }
}
