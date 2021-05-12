package my.workshop.mockito.sample;

import my.workshop.mockito.internal.MyCore;
import my.workshop.mockito.internal.MyServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class Sample1Test {

    @Test
    void test1() {
        MyCore core = new MyCore();
        MyServiceImpl srv = new MyServiceImpl(core);

        System.out.println(srv.times2(111));
        assertEquals(222, srv.times2(111));
    }

    @Test
    void test2() {
        MyCore core = new MyCore();
        core = spy(core);
        MyServiceImpl srv = new MyServiceImpl(core);

        System.out.println(srv.generate1("555"));
        assertEquals("#555#", srv.generate1("555"));

        System.out.println(srv.generate1("556"));
        assertEquals("#556#", srv.generate1("556"));

        System.out.println("---");
//        Mockito.doReturn("YYY").when(core).generate1("777");
        Mockito.when(core.generate1("777")).thenReturn("YYY");
        System.out.println("---");

        System.out.println(srv.generate1("777"));
        assertEquals("YYY", srv.generate1("777"));
    }

    @Test
    void test3() {
        MyCore core = new MyCore();
        MyCore coreSpy = spy(core);
        MyServiceImpl srv1 = new MyServiceImpl(coreSpy);

        assertThrows(RuntimeException.class, () -> {
            core.generate2("xxx_xxx");
        });

        assertThrows(RuntimeException.class, () -> {
            coreSpy.generate2("xxx_xxx");
        });

        assertThrows(RuntimeException.class, () -> {
            Mockito.when(coreSpy.generate2("xxx_xxx")).thenReturn("yyy");
        });

        Mockito.doReturn("yyy").when(coreSpy).generate2("xxx_xxx");

        System.out.println("srv1 " + srv1.generate2("xxx_xxx"));
        assertEquals("yyy", srv1.generate2("xxx_xxx"));

        MyCore coreMock = mock(MyCore.class);
        MyServiceImpl srv2 = new MyServiceImpl(coreMock);

        assertDoesNotThrow(() -> {
            coreMock.generate2("xxx_xxx");
        });

        assertDoesNotThrow(() -> {
            Mockito.when(coreMock.generate2("xxx_xxx")).thenReturn("yyy");
        });

        System.out.println("srv2 " + srv2.generate2("xxx_xxx"));
        assertEquals("yyy", srv2.generate2("xxx_xxx"));
    }
}
