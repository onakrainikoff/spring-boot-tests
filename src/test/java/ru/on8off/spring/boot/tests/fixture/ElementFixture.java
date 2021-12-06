package ru.on8off.spring.boot.tests.fixture;

import ru.on8off.spring.boot.tests.controller.dto.ElementDto;

import java.util.List;
import java.util.Random;

public class ElementFixture {
    private static final Random random = new Random();
    private final static List<String> names = List.of("Zoom", "WinRar", "Idea", "Office 365");

    public static ElementDto generateElement(){
        var elementDto = new ElementDto();
        elementDto.setName(getRandomValue(names));
        return elementDto;
    }

    private static <T> T getRandomValue(List<T> source) {
        return source.get(
                random.nextInt(source.size())
        );
    }
}
