import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class PriorityQueueTest {
    static Stream<Arguments> streamProvider() {

        int[][][] test_cases = new int[5][2][4];

        Random rand = new Random();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < test_cases[i][0].length; j++) {
                test_cases[i][0][j] = rand.nextInt();
                test_cases[i][1][j] = test_cases[i][0][j];
            }

            Arrays.sort(test_cases[i][1]);

        }

        return Stream.of(
                Arguments.of(test_cases[0][0], test_cases[0][1]),
                Arguments.of(test_cases[1][0], test_cases[1][1]),
                Arguments.of(test_cases[2][0], test_cases[2][1]),
                Arguments.of(test_cases[3][0], test_cases[3][1]),
                Arguments.of(test_cases[4][0], test_cases[4][1])
        );
    }

    @ParameterizedTest(name = "#{index} - Test with Argument={0},{1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array) {

        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        int index = 0;
        Integer s;
        int[] result = new int[random_array.length];

        for (int j : random_array) test.add(j);

        for (int i = 0; i < result.length; i++)
            result[i] = test.poll();

        assertArrayEquals(correct_array, result);
    }

    @Test
    public void whenExceptionThrown_thenOfferEIsNull() {
        Exception exception = assertThrows(NullPointerException.class,()->{
            PriorityQueue<Integer> test = new PriorityQueue<Integer>();
            test.add(null);
        });
    }

    @Test
    public void whenExceptionThrown_thenInitialCapacityNotGreaterThanOne() {
        Exception exception = assertThrows(IllegalArgumentException.class,()->{
            PriorityQueue<Integer> test = new PriorityQueue<Integer>(0);
        });
    }

    @Test
    public void whenExceptionThrown_thenNoElementCanRemove() {
        Exception exception = assertThrows(NoSuchElementException.class,()->{
            PriorityQueue<Integer> test = new PriorityQueue<Integer>();
            test.remove();
        });
    }
}
