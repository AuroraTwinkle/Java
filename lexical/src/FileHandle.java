import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandle {
    private BufferedOutputStream bufferedOutputStream;
    public void openFile(String path) throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream(path, true);
        this.bufferedOutputStream=new BufferedOutputStream(fileOutputStream);
    }
    public void writeFile(String destStr) throws IOException {
        bufferedOutputStream.write(destStr.getBytes());
        bufferedOutputStream.write("\r\n".getBytes());
        bufferedOutputStream.flush();
    }

    public void closeFile() throws IOException {
        this.bufferedOutputStream.close();
    }
}
