package KalkulatorPodsieci;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class File {

    private Kalkulator calculator;

    public File() {
        calculator = new Kalkulator("12.21.31.0/24");
    }

    public void writeFile(){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Dane"));

            bufferedWriter.write("ADRES SIECI:");
            bufferedWriter.newLine();
            bufferedWriter.write(calculator.networkAddress());
            bufferedWriter.newLine();
            bufferedWriter.write("KLASA SIECI:");
            bufferedWriter.newLine();
            bufferedWriter.write(calculator.networkClass());
            bufferedWriter.newLine();
            bufferedWriter.write("CZY ADRES JEST PRYWATNY CZY PUBLICZNY?:");
            bufferedWriter.newLine();
            bufferedWriter.write(calculator.privateOrPublic());
            bufferedWriter.newLine();
            bufferedWriter.write("MASKA SIECI W FORMACIE DZIESIĘTNYM I DWÓJKOWYM:");
            bufferedWriter.newLine();
            bufferedWriter.write(calculator.maskDec());
            bufferedWriter.newLine();
            bufferedWriter.write(calculator.maskBinaryS);
            bufferedWriter.newLine();
            bufferedWriter.write("ADRES BROADCAST W FORMACIE DZIESIETNYM I DWÓJKOWYM:");
            bufferedWriter.newLine();
            bufferedWriter.write(calculator.brodcastAdress());
            bufferedWriter.newLine();
            bufferedWriter.write(calculator.brodcastAdressbinary());
            bufferedWriter.newLine();
            bufferedWriter.write("ADRES PIERWSZEGO HOSTA W FORMACIE DZIĘSIETNYM I DWÓJKOWYM:");
            bufferedWriter.newLine();
            bufferedWriter.write(calculator.addressFirsHostBin());
            bufferedWriter.newLine();
            bufferedWriter.write(calculator.addressFirsHostDec());
            bufferedWriter.newLine();
            bufferedWriter.write("ADRES OSTATNIEGO HOSTA W FORMACIE DZIĘSIETNYM I DWÓJKOWYM:");
            bufferedWriter.newLine();
            bufferedWriter.write(calculator.lastHostAdressBin());
            bufferedWriter.newLine();
            bufferedWriter.write(calculator.lastHostAdressDec());
            bufferedWriter.newLine();
            bufferedWriter.write("MAKSYMALNA ILOŚĆ HOSTÓW:");
            bufferedWriter.newLine();
            bufferedWriter.write(calculator.maxCountOfHosts());

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

