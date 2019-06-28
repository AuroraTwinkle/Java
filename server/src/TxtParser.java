import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtParser {
    //三个参数，分别为要查找的关键字，查询码，查询方式
    String searchFromFile(String keys, int optCode, int style) {
        String pathname = "student.txt";//打开文件
        List<String> resultList=new ArrayList<String>();
        resultList.add("false");
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            if(style==1){
                while ((line = br.readLine()) != null) {
                    String[] strArr=line.split("\\s+");
                    if(keys.equals(strArr[optCode])){
                        resultList.set(0,"true");
                        resultList.add(String.join(" " , strArr));
                    }
                }
            }
            else if(style==0){
                while ((line = br.readLine()) != null) {
                    String[] strArr=line.split("\\s+");
                    if(strArr[optCode].contains(keys)){
                        resultList.set(0,"true");
                        resultList.add(String.join(" " , strArr));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.join("," , resultList);
    }

}
