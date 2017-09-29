package file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
public class FileDownloadHelper {
	public static void copy(String filePath, OutputStream os)throws IOException{
		FileInputStream is = null;
		try{
			is = new FileInputStream(filePath);//경로에있는 파일을 읽어오기 위해 InputStream연결
			byte[] data = new byte[8096];
			int len=-1;
			while((len=is.read(data))!=-1){//해당 경로의 파일을 읽어와서
				os.write(data,0,len);//OutputStream으로 write.
			}
		}finally{
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
				}
		}
	}

}
