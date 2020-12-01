package io.nozemi.aoc2020.challenges;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Day1 {

    private final Logger logger = Logger.getLogger(Day1.class.getName());

    public List<Integer> getInput() throws IOException {
        Path file = Path.of("./inputs/day1.txt");
        return Arrays.stream(Files.readString(file)
                .split("\r\n"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public void challenge1() throws IOException {
        List<Integer> numbers = getInput();

        for(int i = 0; i < numbers.size(); i++) {
            for (int y = 0; y < numbers.size(); y++) {
                if(numbers.get(i) + numbers.get(y) == 2020) {
                    logger.info("Two Numbers: " + numbers.get(i) + "+" + numbers.get(y) + "=" + 2020);
                    logger.info("Answer: " + numbers.get(i) * numbers.get(y));
                    return;
                }
            }
        }
    }

    public void challenge2() throws IOException {
        List<Integer> numbers = getInput();

        for(int i = 0; i < numbers.size(); i++) {
            for (int y = 0; y < numbers.size(); y++) {
                for (int z = 0; z < numbers.size(); z++) {
                    if(numbers.get(i) + numbers.get(y) + numbers.get(z) == 2020) {
                        logger.info("Three Numbers: " + numbers.get(i) + "+" + numbers.get(y) + "+" + numbers.get(z) + "=" + 2020);
                        logger.info("Answer: " + numbers.get(i) * numbers.get(y) * numbers.get(z));
                        return;
                    }
                }
            }
        }
    }
}
