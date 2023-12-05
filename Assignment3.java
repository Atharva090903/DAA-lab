import java.util.Arrays;
import java.util.Comparator;

public class Assignment3 {

    static class Item{
        int profit;
        int weight;

        Item(int profit,int weight){
            this.profit = profit;
            this.weight = weight;
        }
    }
    

    static class sortByRatio implements Comparator<Item>{
        public int compare(Item a,Item b){
            double ratioA = (double) a.profit / (double) a.weight;
            double ratioB = (double) b.profit / (double) b.weight;

            if(ratioA < ratioB){
                return 1;
            }
            else{
                return -1;
            }
        }
    }

    static double maxProfit(Item[] arr,int maxWeight){
        Arrays.sort(arr,new sortByRatio());

        int curr_capacity = maxWeight;
        double totalProfit = 0;

        for(Item i:arr){
            if(i.weight <= curr_capacity){
                totalProfit += i.profit;
                curr_capacity -= i.weight;
            }
            else{
                double fraction = (double) curr_capacity / (double) i.weight;
                totalProfit += i.profit * fraction;
                break;
            }
        }

        return totalProfit;
    }
    public static void main(String[] args) {
        // Item[] arr = {new Item(100,20),
        //     new Item(60,10),
        //     new Item(120, 30) };
        
        Item[] arr = {new Item(10,2),
                new Item(5,3),
                new Item(15, 5),
                new Item(7, 7),
                new Item(6, 1),
                new Item(18, 4),
                new Item(3, 1)};

        // int maxWeight = 50;
        int maxWeight = 15;

        double maxProfit = maxProfit(arr, maxWeight);
        System.out.println("The max Profit is " + maxProfit);
    }
}