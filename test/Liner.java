import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author nowto
 *
 */
public class Liner {
	public static void main(String[] args) throws IOException {
		Liner liner = new Liner();
		int srcJavaLine  = liner.tongji(new File("src"), ".java");
		int srcXmlLine = liner.tongji(new File("src"), ".xml");
		int configXmlLine = liner.tongji(new File("config"), ".xml");
		
		int testJavaLine = liner.tongji(new File("test"), ".java");
		int jspLine = liner.tongji(new File("WebRoot"), ".jsp");
		
		System.out.println("源码包java行数：" + srcJavaLine);
		System.out.println(" 测试包Java行数：" + testJavaLine);
		System.out.println("java代码总行数：" + (srcJavaLine + testJavaLine));
		System.out.println();
		System.out.println("源码包Xml行数：" + srcXmlLine);
		System.out.println(" 配置包Xml行数：" + configXmlLine);
		System.out.println("xml代码总行数: " + (srcXmlLine + configXmlLine));
		System.out.println();
		
		System.out.println("jsp行数：" + jspLine);
		
		System.out.println();
		System.out.println("总行数： " + (srcJavaLine + srcXmlLine + configXmlLine + testJavaLine + jspLine));
	}
	private int tongji(File file, String suffix) throws IOException{
		int totalLine = 0;
		if (file.isFile()) {
			if (file.getName().endsWith(suffix)) {
				System.out.println("文件： " + file.getName());
				totalLine = totalLine + getLine(file);
			}
		}else if(file.isDirectory()) {
			System.out.println("目录： " + file.getName());
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				totalLine = totalLine + tongji(files[i], suffix);
			}
		}
		return totalLine;
	}
	private int getLine(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		int line = 0;
		String str = null;
		while ((str = br.readLine()) != null) {
			if (!str.matches("^\\s*$")) {
				line++;
			}
		}
		return line;
		
	}
}
