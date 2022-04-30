package com.example.application.views.list;

import com.example.application.Floor;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.List;

@CssImport("custom-styles.css")
@PageTitle("АИС Haulmont")
@Route("")
public class MainView extends VerticalLayout {

  private final HorizontalLayout menu = new HorizontalLayout();
  private final H1 choose = new H1("CHOOSE");
  private final H1 the = new H1("THE");
  private final H1 floor = new H1("FLOOR");

  private final H1 room = new H1("ROOM");

  private final Button addRoomButton = new Button("ADD");

  private final List<Floor> floors = List.of(  new Floor(1), new Floor(2), new Floor(3));

  public MainView() {
    configureClasses();

    floors.get(0).getButton().addClickListener(buttonClickEvent -> {
      removeAllFloors();
      showRooms(1);
    });

    floors.get(1).getButton().addClickListener(buttonClickEvent -> {
      removeAllFloors();
      showRooms(2);
    });

    floors.get(2).getButton().addClickListener(buttonClickEvent -> {
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
    addRoomButton.setClassName("add-button");
    menu.add(choose, the, floor);
    menu.add(floors.get(0).getButton(),floors.get(1).getButton() , floors.get(2).getButton());
  }

  private void removeAllFloors() {
    floors.forEach(aFloor -> aFloor.getButton().setClassName("big-button-stub"));
    floor.setClassName("floor-stub");

  }

  private void showRooms(int floor) {
    menu.remove(floors.get(0).getButton());
    menu.remove(floors.get(1).getButton());
    menu.remove(floors.get(2).getButton());
    menu.add(room);
    menu.add(addRoomButton);
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