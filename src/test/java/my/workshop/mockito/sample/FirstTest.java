package my.workshop.mockito.sample;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FirstTest {

    @Test
    public void test1() {
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

        mockedList.add(999, "aaaaa1");

        // stubbing appears before the actual execution
        when(mockedList.get(0)).thenReturn("first");

        // the following prints "first"
        System.out.println(mockedList.get(0));
        assertEquals("first", mockedList.get(0));

        // the following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));
        assertEquals(null, mockedList.get(999));
    }

    @Test
    public void test2() {
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = spy(LinkedList.class);

        mockedList.add("A1");
        mockedList.add("A2");

        // stubbing appears before the actual execution
        when(mockedList.get(0)).thenReturn("first");

        // the following prints "first"
        System.out.println(mockedList.get(0));
        assertEquals("first", mockedList.get(0));

        // the following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(1));
        assertEquals("A2", mockedList.get(1));
    }

    @Test
    void test3() {
        LinkedList mockedList = mock(LinkedList.class);

        when(mockedList.get(0))
                .thenReturn("A")
                .thenReturn("B");

        assertEquals("A", mockedList.get(0));
        assertEquals("B", mockedList.get(0));
        assertEquals("B", mockedList.get(0));

        assertEquals(null, mockedList.get(22));
    }

    @Test
    void test4() {
        LinkedList mockedList = mock(LinkedList.class);

        when(mockedList.get(anyInt()))
                .thenReturn("A");

        assertEquals("A", mockedList.get(0));
        assertEquals("A", mockedList.get(22));
    }

    @Test
    void test5() {
        LinkedList mockedList = mock(LinkedList.class);

        when(mockedList.get(0))
                .thenReturn("A")
                .thenReturn("B")
                .thenThrow(new RuntimeException("123"));

        assertEquals("A", mockedList.get(0));
        assertEquals("B", mockedList.get(0));
        assertThrows(RuntimeException.class, () -> mockedList.get(0));
    }
}
