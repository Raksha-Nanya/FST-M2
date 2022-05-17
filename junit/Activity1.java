package org.Activity;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class Activity1 {
    static ArrayList<String> list;

    @BeforeEach
    public void setUp() {
     list = new ArrayList<String>();
     list.add("Super");
     list.add("natural");
     }

     @Test
     @Order(1)
    public void insertTest(){
        assertEquals(2,list.size(),"Wrong Size");
        list.add("Casting Dean");
        assertEquals(3, list.size(),"Wrong Size");
        assertEquals("Super",list.get(0),"Wrong Name");
         assertEquals("natural",list.get(1),"Wrong Name");
         assertEquals("Casting Dean",list.get(2),"Wrong Name");
     }

    @Test
    @Order(2)
    public void replaceTest() {
        // Replace new element
        list.set(1, "charlie");

        // Assert size of list
        assertEquals(2, list.size(), "Wrong size");
        // Assert individual elements
        assertEquals("Super", list.get(0), "Wrong element");
        assertEquals("charlie", list.get(1), "Wrong element");
    }
}


