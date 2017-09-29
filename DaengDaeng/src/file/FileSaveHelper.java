package file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class FileSaveHelper {
	private static Random random = new Random();//int형 난수
	public static String save(String directory,InputStream is)throws IOException{
		long currentTime = System.currentTimeMillis();
		int randomValue=random.nextInt(50);//0-49
		String fileName=Long.toString(currentTime)+"_"+Integer.toString(randomValue);//파일 이름 생성
		File file = new File(directory,fileName);
		FileOutputStream os = null;
		try{
			os = new FileOutputStream(file);
			byte[]data = new byte[8096];
			int len=-1;
			while((len=is.read(data))!=-1){//inputstream으로 읽어온것을 data배열에 저장해서
				os.write(data, 0, len);//outputstream으로 원하는 경로(file객체에 담겨있는 경로)에 쓴다.
			}
		} finally {
			if (os != null)
				try {
					os.close();
				} catch (IOException e) {
				}
		}
		return file.getAbsolutePath();//파일의 경로 반환 -> 다운로드 받을 때 필요
	}

}
