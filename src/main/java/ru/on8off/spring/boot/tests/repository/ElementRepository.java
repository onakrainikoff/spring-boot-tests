package ru.on8off.spring.boot.tests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.on8off.spring.boot.tests.repository.entity.Element;


public interface ElementRepository extends JpaRepository<Element, Integer>, JpaSpecificationExecutor<Element> {
}
