package my.workshop.mockito.sample;

import my.workshop.mockito.internal.MyCore;
import my.workshop.mockito.internal.MyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class Sample3Test {

    @Mock
    MyCore myCore;

    @InjectMocks
    MyServiceImpl myService;

    @Test
    void test1() {
        assertEquals(null, myService.generate1("aaa"));

        Mockito.when(myCore.generate1("aaa")).thenReturn("XXX");
        assertEquals("XXX", myService.generate1("aaa"));
    }

    @Test
    void test2() {
        // Given
        given(myCore.generate1("aaa")).willReturn("XXX");

        // When
        String result = myService.generate1("aaa");

        // Then
        assertThat(result, is("XXX"));
    }

    @Test
    void test3() {

        myService.create1("qqq");

        verify(myCore).verify1("qqq");
        verify(myCore).create1("qqq");

        Mockito.verify(myCore, Mockito.times(0)).create1("xxx");

    }

    @Test
    void test4() {
        // Given
        given(myCore.verify1("qqq_qqq")).willReturn(true);

        // When
        myService.create1("qqq_qqq");

        // Then
        verify(myCore).verify1("qqq_qqq");
        Mockito.verify(myCore, Mockito.times(0)).create1("qqq_qqq");
    }
}
