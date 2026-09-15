package tester;

import static org.junit.Assert.*;
import org.junit.Test;
import student.StudentArrayDeque;

import edu.princeton.cs.introcs.StdRandom;

public class TestArrayDequeEC {

    @Test
    public void randomizedTest() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();

        String message = "";

        while (true) {
            int operationNum = StdRandom.uniform(4);
            if (operationNum == 0) {
                int value = StdRandom.uniform(100);

                student.addFirst(value);
                solution.addFirst(value);
                message += "addFirst" + "(" + value + ")\n";
            } else if (operationNum == 1) {
                int value = StdRandom.uniform(100);
                student.addLast(value);
                solution.addLast(value);
                message += "addLast" + "(" + value + ")\n";
            } else if (operationNum == 2) {
                if (solution.isEmpty()) {
                    continue;
                }
                Integer studentResult = student.removeFirst();
                Integer solutionResult = solution.removeFirst();
                message += "removeFirst()\n";
                assertEquals(message, solutionResult, studentResult);
            } else if (operationNum == 3) {
                if (solution.isEmpty()) {
                    continue;
                }
                Integer studentResult = student.removeLast();
                Integer solutionResult = solution.removeLast();
                message += "removeLast()\n";
                assertEquals(message, solutionResult, studentResult);
            }
        }
    }

}
