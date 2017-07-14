import java.util.*;
import java.io.*;
import java.nio.file.*;

public class desafio1 {
	public static void copyDirectory(File folder, File destination){
		String destName = destination.getPath();  
		String originName = folder.getPath();
		File[] listOfFiles = folder.listFiles(); //Pega a lista de arquivos presentes no diretório de origem
		
		for(int i = 0; i < listOfFiles.length; i++){
			System.out.println("Copying " + originName + "/" + listOfFiles[i].getName() + " to " + destName + "/" + listOfFiles[i].getName());
			final File newFile = new File(destName + "/" + listOfFiles[i].getName()); //Cria objeto File pro arquivo a ser criado no destino
			try{
				Files.copy(listOfFiles[i].toPath(), newFile.toPath()); //Tenta copiar cada arquivo da origem para o destino
			}
			catch (IOException ex){
				System.out.println(listOfFiles[i].getName() + " already exists"); //Se já houver arquivo com mesmo nome, avisa e não copia
			}
			if(listOfFiles[i].isDirectory()){
				copyDirectory(listOfFiles[i], newFile); //Se esse File é na verdade um diretório, chama a função recursivamente para ele
														//e seu diretório correspondente criado no destino
			}
		}
	}
		
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String originName, destName;
		
		originName = sc.next(); //Lê o path de origem
		destName = sc.next(); //Lê o path destino
		
		//Assumindo que os paths dados são válidos. Caso contrário, bastaria checar se existem e jogar uma exceção caso não
		
		final File folder = new File(originName); //Cria objetos File com os paths dados
		final File destination = new File(destName);
		
		copyDirectory(folder, destination); //Função recursiva chamada com os paths origem e destino
		
	}

}
