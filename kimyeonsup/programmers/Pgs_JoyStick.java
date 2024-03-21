package pgs;

public class Pgs_JoyStick {
    public int solution(String name) {
        int answer = 0;
        int move = name.length() - 1;
        char[] alphabets = name.toCharArray();

        for (int pos = 0; pos < alphabets.length; pos++) {
            answer += getMinChangeCount('A', alphabets[pos]);
            if (isNextA(pos, alphabets)) {
                int end = getLastAPos(alphabets, pos + 1);
                move = getMinMoveCount(move, pos, alphabets, end);
            }
        }
        return answer + move;
    }

    private int getMinMoveCount(int move, int pos, char[] alphabets, int end) {
        // 왼쪽으로 갔다가 되돌아가는 값
        int leftMove = Math.min(move, pos * 2 + (alphabets.length - end));
        // 오른쪽으로 갔다가 되돌아가는 값
        int rightMove = Math.min(move, pos + (alphabets.length - end) * 2);
        return Math.min(leftMove, rightMove);
    }

    private boolean isNextA(int pos, char[] alphabets) {
        return pos < alphabets.length - 1 && alphabets[pos + 1] == 'A';
    }

    private int getMinChangeCount(char alphabet, char target) {
        return Math.min(target - alphabet, (91 - 'A') - (target - alphabet));
    }

    private int getLastAPos(char[] alphabets, int index) {
        int move = index;
        while (move < alphabets.length && alphabets[move] == 'A') {
            move++;
        }
        return move;
    }
}
