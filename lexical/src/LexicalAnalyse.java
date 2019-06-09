import java.io.*;
import java.util.HashMap;
public class LexicalAnalyse {
    private BufferedOutputStream bufferedOutputStream;
    private static HashMap<String,Integer> categoryCodeList= new HashMap<>(){
        {
            put("begin",1);
            put("if",4);
            put("end",2);
            put("integer",3);
            put("then",5);
            put("else",6);
            put("function",7);
            put("read",8);
            put("write",9);
            put("=",12);
            put("<>",13);
            put("<=",14);
            put("<",15);
            put(">=",16);
            put(">",17);
            put("-",18);
            put("*",19);
            put(":=",20);
            put("(",21);
            put(")",22);
            put(";",23);
        }
    };

    //判断是否是保留字
    private boolean isKey(String str)
    {
        return categoryCodeList.containsKey(str);
    }
    //判断是否是字母
    private boolean isLetter(char letter)
    {
        return (letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z');
    }
    //判断是否是数字
    private boolean isDigit(char digit)
    {
        return digit >= '0' && digit <= '9';
    }
    //词法分析
    private void analyze(char[] chars) throws IOException {
        FileHandle fileHandle=new FileHandle();
        FileHandle fileHandleError=new FileHandle();
        fileHandleError.openFile("E:\\java\\lexical\\error.err");
        fileHandle.openFile("E:\\java\\lexical\\result.dyd");
        int lineSize=1;
        for(int i = 0;i< chars.length;i++) {
            char ch = chars[i];
            StringBuilder arr = new StringBuilder();
            if(ch == ' '|| ch == '\t'|| ch == '\r'){}
            if(ch=='\n'){
                lineSize++;
                fileHandle.writeFile("EOLN\t"+24);
                System.out.println("EOLN\t"+24);
            }
            else if(ch=='<'||ch=='>'||ch==':'){
                if (ch == ':') {
                    if(chars[++i]!='='){
                        fileHandleError.writeFile("LINE:"+lineSize+"\t2");

                    }
                    i--;
                }
                arr.append(ch);
                ch =chars[++i];
                if(ch=='>'||ch=='='||ch=='<'){
                    arr.append(ch);
                    if(categoryCodeList.get(arr.toString())!=null){
                        fileHandle.writeFile(arr+"\t"+categoryCodeList.get(arr.toString()).toString());
                        System.out.println(arr+"\t"+categoryCodeList.get(arr.toString()).toString());
                    }
                }
                else{
                    i--;
                }
            }
            else if(isLetter(ch)){

                while(isLetter(ch)||isDigit(ch))
                {
                    arr.append(ch);
                    ch = chars[++i];
                }
                //回退一个字符
                i--;
                if(isKey(arr.toString())){
                    //关键字
                    fileHandle.writeFile(arr+"\t"+categoryCodeList.get(arr.toString()).toString());
                    System.out.println(arr+"\t"+categoryCodeList.get(arr.toString()).toString());
                }
                else{
                    //标识符
                    fileHandle.writeFile(arr+"\t10");
                    System.out.println(arr+"\t10");
                }
            }
            else if(isDigit(ch))
            {
                while(isDigit(ch)||(ch == '.'&&isDigit(chars[++i])))
                {
                    if(ch == '.') i--;
                    arr.append(ch);
                    ch = chars[++i];
                }
                //属于无符号常数
                fileHandle.writeFile(arr+"\t11");
                System.out.println(arr+"\t11");
            }

            else{
                if(categoryCodeList.get(String.valueOf(ch))!=null){
                    fileHandle.writeFile(ch+"\t"+categoryCodeList.get(String.valueOf(ch)).toString());
                    System.out.println(ch+"\t"+categoryCodeList.get(String.valueOf(ch)).toString());
                }
            }
        }
        fileHandle.writeFile("EOF\t"+25);
        fileHandle.closeFile();
        fileHandleError.closeFile();
        System.out.println("EOF\t"+25);

    }
    public static void main(String[] args) throws Exception {
        File file = new File("E:\\java\\lexical\\data.txt");//定义一个file对象，用来初始化FileReader
        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
        int length = (int) file.length();
        char buf[] = new char[length+1];
        reader.read(buf);
        reader.close();
        LexicalAnalyse lexicalAnalyse=new LexicalAnalyse();
        lexicalAnalyse.analyze(buf);
    }
}
