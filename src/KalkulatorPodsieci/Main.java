package KalkulatorPodsieci;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {


        if(args[0] != null) {
            Kalkulator a2 = new Kalkulator();
            String ip = args[0];
            Kalkulator a1 = new Kalkulator(ip);
            System.out.println(a1.ipAdress);
            String networkAddress = a1.networkAddress();
            String networkAddressDecymal = a1.networkAddressDec();
            System.out.println("Kalkulator (ip podane) ");
            System.out.println();
            System.out.println("1. Adres sieci dwójkowo - " + networkAddress);
            System.out.println("2. Adres sieci dziesiętnie - " + networkAddressDecymal);
            System.out.println("3. Klasa sieci - " + a1.networkClass());
            System.out.println("4. Sieć publiczna lub prywatna - " + a1.privateOrPublic());
            String maskDecymal = a1.maskDec();
            System.out.println("5. Maska sieci w systemie dziesiętnym - " + maskDecymal);
            System.out.println("6. Maska sieci w systemie binarnym - " + a1.maskBinaryS);
            String brodcastDecymal = a1.brodcastAdress();
            String brodcastBinarty = a1.brodcastAdressbinary();
            System.out.println("7. Adres brodcast w systemie dziesietnym - " + brodcastDecymal);
            System.out.println("8. Adres brodcast w systemie binarnym - " + brodcastBinarty);
            String firstHostDecymal = a1.addressFirsHostDec();
            String firstHostBinary = a1.addressFirsHostBin();
            String lastHostDecymal = a1.lastHostAdressDec();
            String lastHostBinary = a1.lastHostAdressBin();
            System.out.println("9. Adres pierwszego hosta w systemie dziesietnym - " + firstHostDecymal);
            System.out.println("10. Adres pierwszego hosta w systemie binarnym - " + firstHostBinary);
            System.out.println("11. Adres ostatniego hosta w systemie dziesietnym - " + lastHostDecymal);
            System.out.println("12. Adres ostatniego hosta w systemie binarnym - " + lastHostBinary);
            int maxHosts = a1.maxCountOfHosts();
            System.out.println("13. Maksymalna liczba hostów - " + maxHosts);

            File file = new File();
            file.writeFile();

            System.out.println("Ping");

            System.out.println("WYKONAĆ POLECENIE PING? WPISZ Y JEŚLI TAK");

            Scanner scanner = new Scanner(System.in);

            String answer = scanner.nextLine();

            if(answer.equals("Y")){
                Ping ping = new Ping();
                System.out.println(ping.sendPing(a2.ipAdress));
            }

        }
        else {
            Kalkulator a1 = new Kalkulator();
            String networkAddress = a1.networkAddress();
            String networkAddressDecymal = a1.networkAddressDec();
            System.out.println("Kalkulator (ip podane) ");
            System.out.println();
            System.out.println("1. Adres sieci dwójkowo - " + networkAddress);
            System.out.println("2. Adres sieci dziesiętnie - " + networkAddressDecymal);
            System.out.println("3. Klasa sieci - " + a1.networkClass());
            System.out.println("4. Sieć publiczna lub prywatna - " + a1.privateOrPublic());
            String maskDecymal = a1.maskDec();
            System.out.println("5. Maska sieci w systemie dziesiętnym - " + maskDecymal);
            System.out.println("6. Maska sieci w systemie binarnym - " + a1.maskBinaryS);
            String brodcastDecymal = a1.brodcastAdress();
            String brodcastBinarty = a1.brodcastAdressbinary();
            System.out.println("7. Adres brodcast w systemie dziesietnym - " + brodcastDecymal);
            System.out.println("8. Adres brodcast w systemie binarnym - " + brodcastBinarty);
            String firstHostDecymal = a1.addressFirsHostDec();
            String firstHostBinary = a1.addressFirsHostBin();
            String lastHostDecymal = a1.lastHostAdressDec();
            String lastHostBinary = a1.lastHostAdressBin();
            System.out.println("9. Adres pierwszego hosta w systemie dziesietnym - " + firstHostDecymal);
            System.out.println("10. Adres pierwszego hosta w systemie binarnym - " + firstHostBinary);
            System.out.println("11. Adres ostatniego hosta w systemie dziesietnym - " + lastHostDecymal);
            System.out.println("12. Adres ostatniego hosta w systemie binarnym - " + lastHostBinary);
            int maxHosts = a1.maxCountOfHosts();
            System.out.println("13. Maksymalna liczba hostów - " + maxHosts);

            File file = new File();
            file.writeFile();

            System.out.println("Ping");

            System.out.println("WYKONAĆ POLECENIE PING? WPISZ Y JEŚLI TAK");

            Scanner scanner = new Scanner(System.in);

            String answer = scanner.nextLine();

            if(answer.equals("Y")){
                Ping ping = new Ping();
                System.out.println(ping.sendPing(a1.ipAdress));
            }
        }




    }
}
