package second.week2;

class Solution {
    public int solution(String name) {
        int cnt = 0;

        int length = name.length();
        int movement = length - 1;
        for(int i = 0; i < length; i++){
            cnt += calculateLetter(name.charAt(i));

            int index = i + 1;
            // 연속된 A의 길이를 구함.
            while(index < length && name.charAt(index) == 'A'){
                index += 1;
            }

            // 순서대로 가는 경우와 반대로 가는 경우 고려
            movement = Math.min(movement, i * 2 + length - index);
            // 처음부터 반대로 가는 경우를 고려 (정답 참조)
            movement = Math.min(movement, (length - index) * 2 + i);
        }
        return cnt + movement;
    }


    private int calculateLetter(char alphabet){
        int num = alphabet - 'A';
        if(num <= 13){
            return num;
        } else {
            return 25 - num + 1;
        }
    }
}
