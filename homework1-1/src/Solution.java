import java.util.HashSet;
import java.util.Set;

// https://school.programmers.co.kr/learn/courses/30/lessons/68644
public class Solution {
    public int[] solution(int[] numbers) {
        Set<Integer> S = new HashSet<>();

        for(int i=0; i<numbers.length-1; i++)
            for(int j=i+1; j<numbers.length; j++)
                S.add(numbers[i] + numbers[j]);

        int[] answer = S.stream().sorted().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}
