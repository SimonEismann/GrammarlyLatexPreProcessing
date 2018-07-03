package latexPreprocessing;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Processor {
	
	public static void main(String[] args) throws IOException {
		String text = readFile("C:\\Users\\Simon\\Desktop\\main.txt");
		text = removeComments(text);
		text = removeAnythingBeforeBeginDocument(text);
		text = removeLatexCommands(text);
		System.out.println(text);
	}
	
	public static String readFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, Charset.defaultCharset());
	}
	
	public static String removeComments(String text) {
		return text.replaceAll("(?s)(?<!\\\\)%.*?\\n", "");
	}
	
	public static String removeAnythingBeforeBeginDocument(String text) {
		return text.replaceAll("(?s).*\\\\begin\\{document\\}", "");
	}
	
	public static String removeLatexCommands(String text) {
		String[] commands = new String[] {
				"begin",
				"end",
				"author",
				"address",
				"sep",
				"linenumbers",
				"item",
				"ref",
				"cite",
				"bibliography",
				"centering",
				"includegraphics",
				"caption",
				"label",
				"title",
				"paragraph",
				"section",
				"section\\*",
				"input",
				"forget",
				"footnote",
				"url"
		};
		for (String command : commands)
			text = text.replaceAll("(?s)\\\\" + command + "((\\[.*?\\])*(\\{.*?\\})*)*", "");
		return text;
	}
}