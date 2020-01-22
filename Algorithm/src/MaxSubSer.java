import java.util.ArrayList;
import java.util.Random;

public class MaxSubSer{

    private static  <T extends Number> ArrayList<T> maxSubSer(ArrayList<T> arrayList){
        ArrayList<T> resArray=new ArrayList<>();
        double maxSubSum=0;
        int low=0,high=0;
        for(int i=0;i<arrayList.size();i++){
            double thisSum =0.0;
            for(int j=i;j<arrayList.size();j++){
                thisSum+=arrayList.get(j).doubleValue();
                if(thisSum>maxSubSum){
                    maxSubSum=thisSum;
                    low=i;
                    high=j;
                    //System.out.println("最大子序列和是："+maxSubSum);
                }
            }
        }
        for(int i=low;i<=high;i++){
            resArray.add(arrayList.get(i));
        }
        return resArray;
    }

    private static void randomNum(){
        Random random=new Random();
        TxtReader txtReader=new TxtReader();
        ArrayList<Integer> arr=new ArrayList<>();

        for(int i=0;i<100;i++){
            arr.add(100-2*random.nextInt(100));
        }
        //System.out.println(arr);
        txtReader.write("data.txt",arr);
    }

    public static void main(String[] args){
        TxtReader txtReader=new TxtReader();
        randomNum();
        ArrayList<String> arrayList=txtReader.read("data.txt");
        ArrayList<Integer> dataList=new ArrayList<>();
        for (String str:arrayList
             ) {
            dataList.add(Integer.parseInt(str));
        }
        ArrayList<Integer> resList=maxSubSer(dataList);
        System.out.println(resList);
        System.out.println(resList.stream().mapToInt(Integer::intValue).sum());

    }
}
