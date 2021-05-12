package my.workshop.mockito.internal;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.workshop.mockito.MyService;

@AllArgsConstructor
@Slf4j
public class MyServiceImpl implements MyService {

    private final MyCore myCore;

    @Override
    public int times2(int value) {
        return 2 * value;
    }

    @Override
    public String generate1(String value) {
        log.info("generate1() " + value);
        return myCore.generate1(value);
    }

    @Override
    public String generate2(String value) {
        log.info("generate2() " + value);
        return myCore.generate2(value);
    }

    @Override
    public String create1(String value) {
        if (!myCore.verify1(value)) {
            myCore.create1(value);
        }
        return value;
    }

}
