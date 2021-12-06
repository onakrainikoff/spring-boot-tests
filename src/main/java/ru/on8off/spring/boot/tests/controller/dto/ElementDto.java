package ru.on8off.spring.boot.tests.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.on8off.spring.boot.tests.repository.entity.Element;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElementDto {
    private Integer id;
    private String name;

    public static ElementDto toDto(Element element){
        if(element == null) {
            return null;
        }
        var elementDto = new ElementDto();
        elementDto.setId(element.getId());
        elementDto.setName(element.getName());
        return elementDto;
    }

    public static Element fromDto(ElementDto elementDto){
        var element = new Element();
        element.setId(elementDto.getId());
        element.setName(elementDto.getName());
        return element;
    }
}
