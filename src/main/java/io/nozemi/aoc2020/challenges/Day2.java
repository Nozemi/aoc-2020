package io.nozemi.aoc2020.challenges;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day2 {

    private final Logger logger = Logger.getLogger(Day2.class.getName());

    private static int validPasswords;

    private final Pattern pattern = Pattern.compile("(\\d*)-(\\d*) (\\w): (.*)");

    public List<String> getInput() throws IOException {
        Path file = Path.of("./inputs/day2.txt");
        return Arrays.stream(Files.readString(file)
                .split("\r\n"))
                .collect(Collectors.toList());
    }

    public void challenge1() throws IOException {
        validPasswords = 0;

        List<String> passwordPolicies = getInput();

        passwordPolicies.forEach(passwordPolicy -> {
            Password password = parsePasswordPolicy(passwordPolicy).orElse(null);

            if(password == null) {
                return;
            }

            int policyOccurrences = countChar(password.password, password.policy);
            if(policyOccurrences >= password.min && policyOccurrences <= password.max) {
                validPasswords++;
            }
        });

        logger.info("Valid passwords: " + validPasswords);
    }

    public void challenge2() throws IOException {
        validPasswords = 0;

        List<String> passwordPolicies = getInput();

        passwordPolicies.forEach(passwordPolicy -> {
            Password password = parsePasswordPolicy(passwordPolicy).orElse(null);

            if(password == null
            || password.password.length() < password.max) {
                return;
            }

            if((password.password.charAt(password.min - 1) == password.policy
            || password.password.charAt(password.max - 1) == password.policy)
            && !(password.password.charAt(password.min - 1) == password.policy
            && password.password.charAt(password.max - 1) == password.policy)) {
                validPasswords++;
            }
        });

        logger.info("Valid passwords: " + validPasswords);
    }

    private Optional<Password> parsePasswordPolicy(String passwordPolicy) {
        Matcher matcher = pattern.matcher(passwordPolicy);

        if(!matcher.matches()) {
            return Optional.empty();
        }

        Password password = new Password();
        password.min = Integer.parseInt(matcher.group(1));
        password.max = Integer.parseInt(matcher.group(2));
        password.policy = matcher.group(3).toCharArray()[0];
        password.password = matcher.group(4);

        return Optional.of(password);
    }

    private int countChar(String string, char c) {
        int count = 0;

        for(int i = 0; i < string.length(); i++) {
            if(string.charAt(i) == c) count++;
        }

        return count;
    }

    class Password {
        public int min;
        public int max;
        public char policy;
        public String password;
    }
}
