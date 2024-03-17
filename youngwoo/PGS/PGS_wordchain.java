import java.util.HashSet;

public class PGS_wordchain {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];      
        //탈락자 정보를 담는 int 배열. 첫 번째 칸은 탈락자 번호를 저장.두 번째 칸은 탈락 순서를 저장.
        HashSet<String> usedWords = new HashSet<>();
        //HashSet은 중복된 값을 허용X, 빠른 검색 속도를 제공

        String prevWord = "";
        for (int i = 0; i < words.length; i++) {
            String currWord = words[i];

            // 탈락 조건 검사
            /*
            prevWord가 비어있지 않고, prevWord의 마지막 글자가 currWord의 첫 번째 글자와 일치하지 않으면 탈락
            usedWords에 currWord가 존재하면 탈락 (이미 사용된 단어)
            currWord의 길이가 1글자 미만이면 탈락
            탈락 조건에 해당하는 경우 answer 배열에 탈락자 정보를 저장하고 for 문을 종료.
            */
            if (!prevWord.isEmpty() && prevWord.charAt(prevWord.length() - 1) != currWord.charAt(0)) {
                answer[0] = (i % n) + 1;
                answer[1] = i / n + 1;
                break;
            }
            if (usedWords.contains(currWord)) {
                answer[0] = (i % n) + 1;
                answer[1] = i / n + 1;
                break;
            }
            if (currWord.length() < 2) {
                answer[0] = (i % n) + 1;
                answer[1] = i / n + 1;
                break;
            }

            // 다음 단어 선정
            prevWord = currWord;
            usedWords.add(currWord);
        }

        // 탈락자가 발생하지 않은 경우
        /*
        탈락 조건에 해당하지 않으면 currWord를 prevWord에 저장하고 다음 단어의 기준으로 삼음.
        둘 다 0이면 탈락자가 발생하지 않는 것. answer 배열에 [0, 0]을 저장.
        */
        if (answer[0] == 0 && answer[1] == 0) {
            answer[0] = 0;
            answer[1] = 0;
        }

        return answer;
    }
}