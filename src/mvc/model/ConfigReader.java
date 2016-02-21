package mvc.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigReader {
	
	@SuppressWarnings({ "finally" })
	public static String[] getInsallDbScript(String sqlfile_path) throws IOException {
		String line = "";
		String tmp = "";
		String tmp_del = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(sqlfile_path));
			try {
				String tmpLine = null;
				while ((tmpLine = br.readLine()) != null) {
					tmp = tmpLine.substring(tmpLine.length() - 1);
					tmp_del = tmpLine.substring(tmpLine.length() - 2,tmpLine.length() - 1);
					if ((tmp_del == "//")||(tmp == ";"))
						line = line + tmpLine;
					else
						line = line + tmpLine + " ";
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Parse sql script line error " + e);
			} finally {
				br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Parse sql script file error " + e);
		} finally {
			return line.split("//");
		}
	}
}
