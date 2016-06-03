package org.reefminder.microservice.auth.mongo.helpers;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.springframework.security.crypto.keygen.KeyGenerators.string;

public class TestDataGenerator {

    private static final Random randomGenerator = new Random();

    public static Supplier<String> generateString() {
        return () -> string().generateKey();
    }

    public static Supplier<Integer> generateInteger() {
        return () -> randomGenerator.nextInt();
    }

    public static Supplier<String> generateDelimitedIntegerList() {
        Set integerSet = new HashSet<Integer>();
        for(int i = 0; i <= randomGenerator.nextInt(); i++) {
            integerSet.add(randomGenerator.nextInt());
        }
        return () ->
            (String) integerSet.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    public static Supplier<String> generateDelimitedStringList() {
        Set stringSet = new HashSet<Integer>();
        for(int i = 0; i <= randomGenerator.nextInt(); i++) {
            stringSet.add(string().generateKey());
        }
        return () -> (String) stringSet.stream().collect(Collectors.joining(","));
    }

    public static Supplier<Set<String>> generateDelimitedStringSet() {
        Set stringSet = new HashSet<Integer>();
        for(int i = 0; i <= randomGenerator.nextInt(); i++) {
            stringSet.add(string().generateKey());
        }
        return () -> stringSet;
    }


}
