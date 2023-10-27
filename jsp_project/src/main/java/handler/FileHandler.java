package handler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileHandler {

	private static final Logger log = LoggerFactory.getLogger(FileHandler.class);
	
	public int deleteFile(String imageFileName, String savePath) {
		
		boolean isDel =true;
		log.info("imageFileName> "+imageFileName);
		
		File fileDir = new File(savePath);
		File removeFile = new File(fileDir+fileDir.separator+imageFileName);
		File removethFile = new File(fileDir+File.separator+"_th_"+imageFileName);
		
		if(removeFile.exists()||removethFile.exists()) {
			isDel = removeFile.delete();
			log.info(">>del : "+(isDel?"OK":"FAIL"));
			if (isDel) {
				isDel = removethFile.delete();
				log.info(">>ThFile del : "+(isDel? "OK":"FAIL"));
			}
		}
		log.info("remove File OK");
		
		
		return isDel? 1 : 0;
	}
}
