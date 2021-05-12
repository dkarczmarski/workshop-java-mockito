package my.workshop.mockito.internal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyCore {

    public String generate1(String value) {
        log.info("generate1() " + value);

        return "#" + value.toUpperCase() + "#";
    }

    public String generate2(String value) {
        log.info("generate2() " + value);

        if (value != null && value.length() > 3) {
            throw new RuntimeException("value " + value);
        }

        return "#" + value + "#";
    }

}
