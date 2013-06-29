package publication;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class VersionController {

	public static void main(String[] args) throws IOException {
		FileUtils.writeStringToFile(new File("OutputVersion.txt"), String.valueOf(0));
	}

	public static Integer IncrementVersion() {
		try {
			
			File file = new File("OutputVersion.txt");
			String content = FileUtils.readFileToString(file);
			Integer versionNo = Integer.parseInt(content);
			versionNo++;
			FileUtils.writeStringToFile(new File("OutputVersion.txt"), String.valueOf(versionNo));
			return versionNo;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Integer GetIncrementedVersionNumber()
	{
		return IncrementVersion();
	}
}
