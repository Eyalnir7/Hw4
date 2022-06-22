import java.sql.Array;
import java.util.ArrayDeque;

public class LevelMostOccurrences {

    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num){
        ArrayDeque<BinNode<Integer>> q1 = new ArrayDeque<BinNode<Integer>>();
        ArrayDeque<BinNode<Integer>> q2 = new ArrayDeque<BinNode<Integer>>();
        ArrayDeque<BinNode<Integer>> temp;
        int maxLevel = -1;
        int maxCounter = 0;
        int currentLevel = 0, currentCounter;
        q2.add(node);
        while (!q2.isEmpty()){
            currentCounter = 0;
            temp = q1;
            q1 = q2;
            q2 = temp;
            while (!q1.isEmpty()) {
                BinNode<Integer> currentNode = q1.pop();
                if(currentNode.getData() == num)
                    currentCounter++;
                if (currentNode.getRight() != null)
                    q2.add(currentNode.getRight());
                if (currentNode.getLeft() != null)
                    q2.add(currentNode.getLeft());
            }
            if(currentCounter > maxCounter)
            {
                maxLevel = currentLevel;
                maxCounter = currentCounter;
            }
            currentLevel++;
        }
        return maxLevel;
    }



}
