package com.example.contact.service;

import com.example.contact.domain.Block;
import com.example.contact.domain.Person;
import com.example.contact.repository.BlockRepository;
import com.example.contact.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BlockRepository blockRepository;

    @Test
    void getPeopleExcludeBlocks() {
        givenPeople();

        List<Person> result = personService.getPeopleExcludeBlocks();

        result.forEach(System.out::println);
    }

    @Test
    void getPeopleByName() {
        givenPeople();

        List<Person> result = personService.getPeopleByName("martin");

        result.forEach(System.out::println);
    }

    @Test
    void cascadeTest() {
        givenPeople();

        List<Person> result = personRepository.findAll();
        result.forEach(System.out::println);

        Person person = result.get(3);
        person.getBlock().setStartDate(LocalDate.now());
        person.getBlock().setEndDate(LocalDate.now());

        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);

//        personRepository.delete(person);
//        personRepository.findAll().forEach(System.out::println);
//        blockRepository.findAll().forEach(System.out::println);

        person.setBlock(null);
        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);
        blockRepository.findAll().forEach(System.out::println);
    }

    @Test
    void getPerson() {
        givenPeople();

        Person person = personService.getPerson(3L);

        System.out.println(person);
    }

    private void givenPeople() {
        givenPerson("martin", 10, "A");
        givenPerson("david", 9, "B");
        givenBlockPerson("dennis",7,"O");
        givenBlockPerson("martin", 11, "AB");
    }

    private void givenPerson(String name, int age, String bloodType) {
        personRepository.save(new Person(name, age, bloodType));
    }

    private void givenBlockPerson(String name, int age, String bloodType) {
        Person blockPerson = new Person(name, age, bloodType);
        blockPerson.setBlock(new Block(name));

        personRepository.save(blockPerson);
    }
}