package com.example.application.views.list;

import com.example.application.Floors;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.EnumSet;

@CssImport("custom-styles.css")
@PageTitle("АИС Haulmont")
@Route("")
public class MainView extends VerticalLayout {

  private final HorizontalLayout menu = new HorizontalLayout();
  private final H1 choose = new H1("CHOOSE");
  private final H1 the = new H1("THE");
  private final H1 floor = new H1("FLOOR");

  private final H1 room = new H1("ROOM");

  public MainView() {
    configureClasses();

    Floors.FIRST.getButton().addClickListener(buttonClickEvent -> {
      removeAllFloors();
      showRooms(1);
    });

    Floors.SECOND.getButton().addClickListener(buttonClickEvent -> {
      removeAllFloors();
      showRooms(2);
    });

    Floors.THIRD.getButton().addClickListener(buttonClickEvent -> {
      removeAllFloors();
      showRooms(3);
    });

    add(menu);
    setSizeFull();
    setAlignItems(Alignment.STRETCH);
    setClassName("main");
  }

  private void configureClasses() {
    menu.setClassName("menu");
    choose.setClassName("choose");
    the.setClassName("the");
    floor.setClassName("floor");
    room.setClassName("room");
    menu.add(choose, the, floor);
    menu.add(Floors.FIRST.getButton(),Floors.SECOND.getButton() , Floors.THIRD.getButton());
  }

  private void removeAllFloors() {
    EnumSet<Floors> floors = EnumSet.of(Floors.FIRST, Floors.SECOND, Floors.THIRD);
    floors.forEach(floor -> floor.getButton().setClassName("big-button-stub"));
    floor.setClassName("floor-stub");

  }

  private void showRooms(int floor) {
    menu.add(room);
//    switch (floor) {
//      case 1 -> {
//
//      }
//      case 2 -> {
//
//      }
//      case 3 -> {
//
//      }
//    }
  }

}