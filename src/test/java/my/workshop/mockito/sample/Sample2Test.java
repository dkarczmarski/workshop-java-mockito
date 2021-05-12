package my.workshop.mockito.sample;

import my.workshop.mockito.MyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class Sample2Test {

    @Mock
    MyService myService;

    @Test
    void test1() {
        System.out.println(myService.generate1("xxx-xxx"));
        assertEquals(null, myService.generate1("xxx-xxx"));

        Mockito.when(myService.generate1(any())).thenReturn("yyy");
        System.out.println(myService.generate1("xxx-xxx"));
        assertEquals("yyy", myService.generate1("xxx-xxx"));
    }

}
