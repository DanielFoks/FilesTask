import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class UniteFiles {
    private final Path file1;
    private final Path file2;
    private final Path resultFile;

    public UniteFiles(Path file1, Path file2, Path resultFile) {
        this.file1 = file1;
        this.file2 = file2;
        this.resultFile = resultFile;
    }

    public void makeResultFile() throws IOException {

        try (BufferedReader bufferedReader1 = Files.newBufferedReader(file1);
             BufferedReader bufferedReader2 = Files.newBufferedReader(file2);
             BufferedWriter bufferedWrite = Files.newBufferedWriter(resultFile)){


            String line1 = bufferedReader1.readLine();
            String line2 = bufferedReader2.readLine();

            while (line1!=null && line2!=null){
                bufferedWrite.write(line1);
                bufferedWrite.newLine();
                bufferedWrite.write(line2);
                bufferedWrite.newLine();

                line1=bufferedReader1.readLine();
                line2=bufferedReader2.readLine();
            }

            if (line1==null && line2!=null){
                do{
                    bufferedWrite.write(line2);
                    bufferedWrite.newLine();
                }
                while ((line2=bufferedReader2.readLine())!=null);
            }
            if (line2==null && line1!=null){
                    do{
                        bufferedWrite.write(line1);
                        bufferedWrite.newLine();
                    }
                    while ((line1=bufferedReader1.readLine())!=null);
                }
        }

    }


    public static void main(String[] args) {
        UniteFiles uniteFiles = new UniteFiles(Paths.get(args[0]),Paths.get(args[1]),Paths.get(args[2]));
        try {
            uniteFiles.makeResultFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
