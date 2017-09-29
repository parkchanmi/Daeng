package file;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
public class FileRemoveHelper {
	public static void remove(String filePath)throws IOException{
		
			 File f = new File(filePath); // 파일 객체생성
			 if( f.exists()) 
				 f.delete(); // 파일이 존재하면 파일을 삭제한다.

		
	}
}
