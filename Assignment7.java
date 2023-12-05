import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Assignment7 {

    static List<List<Integer>> subsetSum(int[] nums,int target){
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums,target,0,new ArrayList<>(),result);
        return result;
    }

    static void backtrack(int[] nums,int target,int start,List<Integer> solution,List<List<Integer>> result){
        if(target == 0){
            result.add(new ArrayList<>(solution));
            return;
        }

        for(int i=start;i<nums.length;i++){
            if(i > start && nums[i] == nums[i-1]){
                continue;
            }

            if(nums[i] > target){
                break;
            }

            solution.add(nums[i]);
            backtrack(nums, target-nums[i], i+1, solution, result);
            solution.remove(solution.size() - 1);
        }
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        int target = 8;

        List<List<Integer>> result = subsetSum(nums, target);

        for(List<Integer> subset: result){
            System.out.println(subset);
        }
    }
}