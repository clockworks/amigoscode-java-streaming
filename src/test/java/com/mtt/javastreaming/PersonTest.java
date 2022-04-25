package com.mtt.javastreaming;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class PersonTest {

    @Test public void shouldListImperativeApproach() {

        List<Person> people = getPeople();

        List<Person> females = new ArrayList<>();
        for (Person person : people) {
            if (person.getGender().equals(Gender.FEMALE)) {
                females.add(person);
            }
        }

        females.forEach(System.out::println);

        assertTrue(true);
    }

    @Test public void shouldListDeclarativeApproach() {
        List<Person> people = getPeople();

        List<Person> females = people.stream()
                        .filter(person -> person.getGender().equals(Gender.FEMALE))
                        .collect(Collectors.toList());

        females.forEach(System.out::println);
        assertTrue(true);
    }

    @Test public void shouldSortedListDeclarativeApproach() {
        List<Person> people = getPeople();

        List<Person> females = people.stream()
                .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
                .collect(Collectors.toList());

        females.forEach(System.out::println);
        assertTrue(true);
    }

    @Test public void otherDeclarativeUsages() {
        List<Person> people = getPeople();

        // All match
        boolean allMatch = people.stream().allMatch(person -> person.getAge() > 8);
        System.out.println("allmatch: " + allMatch);

        // Any match
        boolean anyMatch = people.stream().anyMatch(person -> person.getAge() > 121);
        System.out.println("anymatch: " + anyMatch);

        // None match
        boolean noneMatch = people.stream().noneMatch(person -> person.getName().equals("Antonio"));
        System.out.println("nonematch: " + noneMatch);

        // Max
        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent( System.out::println);

        // Min
        people.stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent( System.out::println);

        // Group
        Map<Gender, List<Person>> groupByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));


        groupByGender.forEach((gender, people1) -> {
            System.out.println("Group: " + gender);
            people1.forEach(System.out::println);
            System.out.println();
        });

        Optional<String> oldestFemaleAge = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);

        oldestFemaleAge.ifPresent( System.out::println);
    }



    private static List<Person> getPeople() {
        return List.of(
                new Person("Antonio", 20, Gender.MALE),
                new Person("Alina Smith", 33, Gender.FEMALE),
                new Person("Helen White", 57, Gender.FEMALE),
                new Person("Alex Boz", 14, Gender.MALE),
                new Person("Jamie Goa", 99, Gender.MALE),
                new Person("Anna Cook", 7, Gender.FEMALE),
                new Person("Zelda Brown", 120, Gender.FEMALE)
        );
    }
}
