package sjjd.doc.line.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
    //静态方法：三个参数：文件的二进制，文件路径，文件名
    //通过该方法将在指定目录下添加指定文件
    public static void fileupload(byte[] file, String filePath, String fileName) throws IOException {
        if (fileName == null || fileName.isEmpty()) {
            return;
        }
        //目标目录
        File targetfile = new File(filePath);
        if (targetfile.exists()) {
            targetfile.mkdirs();
        }

        //二进制流写入
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    //递归删除指定路径下的所有文件
    public static void deleteFile(File file) {
        if (file.isFile() || file.list().length == 0) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteFile(f);//递归删除每一个文件
                f.delete();//删除该文件夹
            }
        }
    }


}
