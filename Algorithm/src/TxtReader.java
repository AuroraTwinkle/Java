import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

class TxtReader {
    private Logger logger;
    TxtReader(){
        logger=Logger.getLogger("aurora.logger",null);
    }
    ArrayList<String> read(String path){
        ArrayList<String> resListStr=new ArrayList<>();
        try (FileReader reader = new FileReader(path);
             BufferedReader br = new BufferedReader(reader))
        {
            String line;
            while ((line = br.readLine()) != null) {
               resListStr=new ArrayList<>(Arrays.asList(line.split(",\\s+")));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return resListStr;
    }

    <T> void write(String path,ArrayList<T> arrayList){
        try{
            File writer=new File(path);
            if(!writer.exists()){
                logger.log(Level.INFO,"创建文件成功");

            }
            logger.log(Level.INFO,writer.getCanonicalPath());
            logger.log(Level.INFO,writer.getName());
            logger.log(Level.INFO,writer.getPath());
            logger.log(Level.INFO,writer.getParent());
            File file1=new File("E:\\Java\\Algorithm");
            File file=new File(file1,"data1.txt");
            file.createNewFile();
            logger.log(Level.INFO,file.getParent());
            System.out.println(writer.lastModified());
            System.out.println(writer.length());
            //System.out.println(writer.list().toString());
            System.out.println(Arrays.toString(file1.list()));
            try( FileWriter fileWriter=new FileWriter(writer);
                 BufferedWriter out=new BufferedWriter(fileWriter)) {
                StringBuilder stringBuilder=new StringBuilder();
                String tmp=null;
                for (T t:arrayList
                     ) {
                    tmp=String.valueOf(t)+",\t";
                    stringBuilder.append(tmp);
                }
                out.write(stringBuilder.toString());
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
