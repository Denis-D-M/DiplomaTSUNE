package com.example.application;

import java.util.List;

public class Table {

  private int num;

  private boolean isFree;

  private String bookedBy;

  private List<String> equip;

  public Table(int num, boolean isFree, String bookedBy, List<String> equip) {
    this.num = num;
    this.isFree = isFree;
    this.bookedBy = bookedBy;
    this.equip = equip;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public boolean isFree() {
    return isFree;
  }

  public void setFree(boolean free) {
    isFree = free;
  }

  public String getBookedBy() {
    return bookedBy;
  }

  public void setBookedBy(String bookedBy) {
    this.bookedBy = bookedBy;
  }

  public List<String> getEquip() {
    return equip;
  }

  public void setEquip(List<String> equip) {
    this.equip = equip;
  }
}
