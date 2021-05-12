package my.workshop.mockito;

import org.springframework.stereotype.Service;

@Service
public interface MyService {

    int times2(int value);

    String generate1(String value);

    String generate2(String value);

}
