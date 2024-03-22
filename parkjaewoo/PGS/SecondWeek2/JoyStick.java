package PGS.SecondWeek2;

public class JoyStick {
    public static void main(String[] args) {
        System.out.println(Solution.solution("JEROEN"));
        System.out.println(Solution.solution("JAN"));
    }
}

class Solution { // 정답
    public static int solution(String name) {
        int answer = 0;
        int length = name.length();

        int index;
        int move = length - 1;

        for(int i = 0; i < name.length(); i++){
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);

            index = i + 1;
            while(index < length && name.charAt(index) == 'A'){
                index++;
            }
            move = Math.min(move, i * 2 + length - index);
            move = Math.min(move, (length - index) * 2 + i);
        }
        return answer + move;
    }
}

//class Solution { 함정에 빠져버린 코드
//    public static int solution(String name) {
//        int answer = 0;
//
//        for (char e : name.toCharArray()) {
//            if (e < 'N') {
//                answer += (e - 'A');
//            } else {
//                answer += ('Z' - e + 1);
//            }
//        }
//        answer += name.length() - 1;
//        if (name.length() > 1 && name.charAt(1) == 'A') {
//            answer--;
//        }
//
//        return answer;
//    }
//}