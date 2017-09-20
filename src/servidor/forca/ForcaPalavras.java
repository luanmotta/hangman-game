package servidor.forca;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

public class ForcaPalavras {
	ArrayList<PalavraForca> palavras = new ArrayList<PalavraForca>();

	public ForcaPalavras() {
		String fileName = getClass().getResource("ListaPalavras.txt").getPath().replaceFirst("/", "");

		//read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.forEach(line -> {
				String[] parts = line.split("\\|");
				palavras.add(new PalavraForca(parts[0], parts[1]));
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PalavraForca getPalavra() {
		Random generator = new Random();
		return palavras.get(generator.nextInt(palavras.size()));
	}
	
	

}
