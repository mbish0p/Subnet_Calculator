package KalkulatorPodsieci;

/*
1. Skrypt przyjmuje jako argument adres IP (hosta lub sieci) wraz z maską w formacie:
a.b.c.d/maska
2. Jeśli argument nie został podany to skrypt pobiera adres komputera, na którym jest
uruchomiony
3. Skrypt sprawdza, czy wprowadzony adres jest poprawnym adresem IP. Jeśli nie,
wyświetla komunikat o błędzie.
4. Skrypt oblicza następujące dane:
i. Adres sieci
ii. Klasę sieci
iii. Czy adres należy do puli adresów publicznych czy prywatnych
iv. Maska sieci w formacie dziesiętnym (np. 255.255.255.0) i binarnym
v. Adres broadcast (dziesiętnie i binarnie)
vi. Pierwszy adres hosta (dziesiętnie i binarnie)
vii. Ostatni adres hosta (dziesiętnie i binarnie)
viii. Maksymalna ilość hostów, która może być przypisana do danej podsieci
5. Obliczone wartości są wyświetlane na ekranie oraz zapisywane do pliku tekstowego
6. Adresy w systemie dwójkowym są prezentowane tak, aby każdy oktet był
przedstawiony przy pomocy 8 znaków.
7. Jeżeli podany adres jest adresem hosta to skrypt pyta, czy wykonać polecenie ping
dla podanego adresu. Jeśli użytkownik wpisze Y to skrypt wykonuje polecenie ping
oraz prezentuje jego wyniki.
 */

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

public class Kalkulator {

    int firstOctet;
    int secondOctet;
    int thirdOctet;
    int fourthOctet;
    int mask;
    String ipAdress;
    char[]maskBinary;
    String maskBinaryS;

    Kalkulator(String ip) {

        String tmp = ip;
        String[] tmpSplit = tmp.split("/");
        ipAdress = tmpSplit[0];



      String[] split = ip.split("\\.");
      firstOctet = Integer.parseInt(split[0]);
      secondOctet = Integer.parseInt(split[1]);
      thirdOctet = Integer.parseInt(split[2]);
      String[] secondSplit = split[3].split("/");
      fourthOctet = Integer.parseInt(secondSplit[0]);
      mask = Integer.parseInt(secondSplit[1]);

        int value = 0xffffffff << (32 - mask);
        byte[] bytes = new byte[]{ (byte) (value >>> 24), (byte) (value >> 16 & 0xff), (byte) (value >> 8 & 0xff), (byte) (value & 0xff)};
        try {
            char tmpmask[] = stringToCharArray(InetAddress.getByAddress(bytes).toString());
            maskBinary =tmpmask;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
    Kalkulator() {

        try {
            InetAddress myIp = InetAddress.getLocalHost();
            String name = myIp.getHostAddress();
            ipAdress =name;
            System.out.println(name);

            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(myIp);
            int netPrefix = networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();
            mask =netPrefix;

            String[] split = name.split("\\.");
            firstOctet = Integer.parseInt(split[0]);
            secondOctet = Integer.parseInt(split[1]);
            thirdOctet = Integer.parseInt(split[2]);
            fourthOctet = Integer.parseInt(split[3]);

            int value = 0xffffffff << (32 - mask);
            byte[] bytes = new byte[]{ (byte) (value >>> 24), (byte) (value >> 16 & 0xff), (byte) (value >> 8 & 0xff), (byte) (value & 0xff)};
            try {
                char tmpmask[] = stringToCharArray(InetAddress.getByAddress(bytes).toString());
                maskBinary =tmpmask;
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    void Print () {
        System.out.println(firstOctet);
        System.out.println(secondOctet);
        System.out.println(thirdOctet);
        System.out.println(fourthOctet);
        System.out.println(mask);

    }
    public String networkAddressDec(){

        String tmp = networkAddress();

        String firstOctets = tmp.substring(0,8);
        String secondOctets = tmp.substring(8,16);
        String thirdOctets = tmp.substring(16,24);
        String fourthOctets = tmp.substring(24,32);

        int firstOctet = Integer.parseInt(firstOctets,2);
        int esecondOctet = Integer.parseInt(secondOctets,2);
        int thirdOctet = Integer.parseInt(thirdOctets,2);
        int fourthOctet = Integer.parseInt(fourthOctets,2);

        String networkAddress = firstOctet + "." + esecondOctet + "." + thirdOctet + "." + fourthOctet;

        return networkAddress;
    }
    String networkAddress() {
        char[] tmp2 = new char[32];

        // ---------------mask-------------------
        try {
            int value = 0xffffffff << (32 - mask);
            byte[] bytes = new byte[]{ (byte) (value >>> 24), (byte) (value >> 16 & 0xff), (byte) (value >> 8 & 0xff), (byte) (value & 0xff)};
            char tmp[] = stringToCharArray(InetAddress.getByAddress(bytes).toString());


            for(int i=0;i<tmp.length;i++)
            {
                //System.out.print(tmp[i]);
                tmp2[i] = tmp[i];
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        //------------- ip --------------------
       /* long result = 0;

       String[] ipAddressInArray = ipAdress.split("\\.");
        for(int i=3 ; i>=0 ;i--) {

           long tmpIp = Long.parseLong(ipAddressInArray[3 - i]);

           result |= tmpIp << (i * 8);
        }
        String resultString =Long.toBinaryString(result);
        int len = resultString.length();
        if (len < 32) {
            for(int i=0;i<32-len;i++)
                resultString= "0" + resultString;
        }

        //System.out.println();
       // System.out.println(resultString);


        String maskString = new String(tmp2);

        long maskLong = Long.parseLong(maskString,2);


        Long networkAdess = maskLong & result;
        String value = Long.toBinaryString(networkAdess);
        len = value.length();
        if (len < 32) {
            for(int i=0;i<32-len;i++)
                value= "0" + value;
        }

       // System.out.println(value);
        return value; */

        String firstBinaryOctet = convertToBinary(firstOctet);
        String secondBinaryOctet = convertToBinary(secondOctet);
        String thirdBinaryOctet = convertToBinary(thirdOctet);
        String fourthBinaryOctet = convertToBinary(fourthOctet);

        String ipAddress = firstBinaryOctet + secondBinaryOctet + thirdBinaryOctet + fourthBinaryOctet;
        maskDec();
        String binaryMask = maskBinaryS;
        String networkAddress = new String();

        //System.out.println(ipAddress);
       // System.out.println(binaryMask);

        for(int i=0;i<32;i++){

            if((ipAddress.charAt(i) == '1' && binaryMask.charAt(i)== '1'))
                networkAddress = networkAddress + "1" ;
            else
                networkAddress = networkAddress + "0" ;
        }

        return networkAddress;
    }
    public String networkClass(){

        if(firstOctet<127 || firstOctet == 10) return "Class A";
        else if( firstOctet == 172 || firstOctet<191) return "Class B";
        else if(firstOctet == 192 || firstOctet<223) return "Class C";
        else if(firstOctet<239) return "Class D";
        else return "Class E";
    }
    public String privateOrPublic(){

        if(firstOctet == 10) return "Private Adress";
        else if (firstOctet == 172 && secondOctet < 31 && secondOctet>16) return "Private Adress";
        else if (firstOctet == 192 && secondOctet == 168) return "Private Adress";

        return "Public Adress";
    }
    public String brodcastAdress() {

        StringBuilder tmpString = new StringBuilder();
        for(int i=0 ; i<32;i++)
        {
            if(maskBinary[i] =='1')
                tmpString.append('0');
            else tmpString.append('1');

        }
        String firstOctets = tmpString.substring(0,8);
        String secondOctets = tmpString.substring(8,16);
        String thirdOctets = tmpString.substring(16,24);
        String fourthOctets = tmpString.substring(24,32);

        String tmp = networkAddress();
        String firstOctetsNetwork = tmp.substring(0,8);
        String secondOctetsNetwork = tmp.substring(8,16);
        String thirdOctetsNetwork = tmp.substring(16,24);
        String fourthOctetsNetwork = tmp.substring(24,32);


        int maskfirstOctet = Integer.parseInt(firstOctets,2);
        int maskesecondOctet = Integer.parseInt(secondOctets,2);
        int maskthirdOctet = Integer.parseInt(thirdOctets,2);
        int maskfourthOctet = Integer.parseInt(fourthOctets,2);

        int networkfirstOctet = Integer.parseInt(firstOctetsNetwork,2);
        int networksecondOctet = Integer.parseInt(secondOctetsNetwork,2);
        int networkthirdOctet = Integer.parseInt(thirdOctetsNetwork,2);
        int networkfourthOctet = Integer.parseInt(fourthOctetsNetwork,2);

        int brodcastFirstOctet = maskfirstOctet + networkfirstOctet;
        int brodcastSecondOctet = maskesecondOctet + networksecondOctet;
        int brodcastThirdOctet = maskthirdOctet + networkthirdOctet;
        int brodcastFourthOctet = maskfourthOctet + networkfourthOctet;


        String brodcastAdress = brodcastFirstOctet + "." + brodcastSecondOctet + "." + brodcastThirdOctet + "." + brodcastFourthOctet;

        return brodcastAdress;
    }

    public String brodcastAdressbinary(){

        StringBuilder tmpString = new StringBuilder();
        for(int i=0 ; i<32;i++)
        {
            if(maskBinary[i] =='1')
                tmpString.append('0');
            else tmpString.append('1');

        }
        String firstOctets = tmpString.substring(0,8);
        String secondOctets = tmpString.substring(8,16);
        String thirdOctets = tmpString.substring(16,24);
        String fourthOctets = tmpString.substring(24,32);

        String tmp = networkAddress();
        String firstOctetsNetwork = tmp.substring(0,8);
        String secondOctetsNetwork = tmp.substring(8,16);
        String thirdOctetsNetwork = tmp.substring(16,24);
        String fourthOctetsNetwork = tmp.substring(24,32);


        int maskfirstOctet = Integer.parseInt(firstOctets,2);
        int maskesecondOctet = Integer.parseInt(secondOctets,2);
        int maskthirdOctet = Integer.parseInt(thirdOctets,2);
        int maskfourthOctet = Integer.parseInt(fourthOctets,2);

        int networkfirstOctet = Integer.parseInt(firstOctetsNetwork,2);
        int networksecondOctet = Integer.parseInt(secondOctetsNetwork,2);
        int networkthirdOctet = Integer.parseInt(thirdOctetsNetwork,2);
        int networkfourthOctet = Integer.parseInt(fourthOctetsNetwork,2);

        int brodcastFirstOctet = maskfirstOctet + networkfirstOctet;
        int brodcastSecondOctet = maskesecondOctet + networksecondOctet;
        int brodcastThirdOctet = maskthirdOctet + networkthirdOctet;
        int brodcastFourthOctet = maskfourthOctet + networkfourthOctet;


        String firstBinaryOctet = convertToBinary(brodcastFirstOctet);
        String secondBinaryOctet = convertToBinary(brodcastSecondOctet);
        String thirdBinaryOctet = convertToBinary(brodcastThirdOctet);
        String fourthBinaryOctet = convertToBinary(brodcastFourthOctet);


        return firstBinaryOctet+secondBinaryOctet+thirdBinaryOctet+fourthBinaryOctet;

    }
    public String addressFirsHostDec(){

        String networkAddress = networkAddress();

        String firstOctetsNetwork = networkAddress.substring(0,8);
        String secondOctetsNetwork = networkAddress.substring(8,16);
        String thirdOctetsNetwork = networkAddress.substring(16,24);
        String fourthOctetsNetwork = networkAddress.substring(24,32);

        int networkfirstOctet = Integer.parseInt(firstOctetsNetwork,2);
        int networksecondOctet = Integer.parseInt(secondOctetsNetwork,2);
        int networkthirdOctet = Integer.parseInt(thirdOctetsNetwork,2);
        int networkfourthOctet = Integer.parseInt(fourthOctetsNetwork,2) + 1;

        String firstHostAddress = networkfirstOctet + "." + networksecondOctet + "." + networkthirdOctet + "." + networkfourthOctet;

        return firstHostAddress;
    }

    public String addressFirsHostBin(){

        String networkAddress = networkAddress();

        String firstOctetsNetwork = networkAddress.substring(0,8);
        String secondOctetsNetwork = networkAddress.substring(8,16);
        String thirdOctetsNetwork = networkAddress.substring(16,24);
        String fourthOctetsNetwork = networkAddress.substring(24,32);

        int networkfirstOctet = Integer.parseInt(firstOctetsNetwork,2);
        int networksecondOctet = Integer.parseInt(secondOctetsNetwork,2);
        int networkthirdOctet = Integer.parseInt(thirdOctetsNetwork,2);
        int networkfourthOctet = Integer.parseInt(fourthOctetsNetwork,2) + 1;

        String firstBinaryOctet = convertToBinary(networkfirstOctet);
        String secondBinaryOctet = convertToBinary(networksecondOctet);
        String thirdBinaryOctet = convertToBinary(networkthirdOctet);
        String fourthBinaryOctet = convertToBinary(networkfourthOctet);

        return firstBinaryOctet+secondBinaryOctet+thirdBinaryOctet+fourthBinaryOctet;

    }
    public String lastHostAdressDec(){

        StringBuilder tmpString = new StringBuilder();
        for(int i=0 ; i<32;i++)
        {
            if(maskBinary[i] =='1')
                tmpString.append('0');
            else tmpString.append('1');

        }
        String firstOctets = tmpString.substring(0,8);
        String secondOctets = tmpString.substring(8,16);
        String thirdOctets = tmpString.substring(16,24);
        String fourthOctets = tmpString.substring(24,32);

        String tmp = networkAddress();
        String firstOctetsNetwork = tmp.substring(0,8);
        String secondOctetsNetwork = tmp.substring(8,16);
        String thirdOctetsNetwork = tmp.substring(16,24);
        String fourthOctetsNetwork = tmp.substring(24,32);


        int maskfirstOctet = Integer.parseInt(firstOctets,2);
        int maskesecondOctet = Integer.parseInt(secondOctets,2);
        int maskthirdOctet = Integer.parseInt(thirdOctets,2);
        int maskfourthOctet = Integer.parseInt(fourthOctets,2);

        int networkfirstOctet = Integer.parseInt(firstOctetsNetwork,2);
        int networksecondOctet = Integer.parseInt(secondOctetsNetwork,2);
        int networkthirdOctet = Integer.parseInt(thirdOctetsNetwork,2);
        int networkfourthOctet = Integer.parseInt(fourthOctetsNetwork,2);

        int brodcastFirstOctet = maskfirstOctet + networkfirstOctet;
        int brodcastSecondOctet = maskesecondOctet + networksecondOctet;
        int brodcastThirdOctet = maskthirdOctet + networkthirdOctet;
        int brodcastFourthOctet = maskfourthOctet + networkfourthOctet - 1;

        String brodcastAdress = brodcastFirstOctet + "." + brodcastSecondOctet + "." + brodcastThirdOctet + "." + brodcastFourthOctet;

        return brodcastAdress;
    }
    public String lastHostAdressBin(){

        StringBuilder tmpString = new StringBuilder();
        for(int i=0 ; i<32;i++)
        {
            if(maskBinary[i] =='1')
                tmpString.append('0');
            else tmpString.append('1');

        }
        String firstOctets = tmpString.substring(0,8);
        String secondOctets = tmpString.substring(8,16);
        String thirdOctets = tmpString.substring(16,24);
        String fourthOctets = tmpString.substring(24,32);

        String tmp = networkAddress();
        String firstOctetsNetwork = tmp.substring(0,8);
        String secondOctetsNetwork = tmp.substring(8,16);
        String thirdOctetsNetwork = tmp.substring(16,24);
        String fourthOctetsNetwork = tmp.substring(24,32);


        int maskfirstOctet = Integer.parseInt(firstOctets,2);
        int maskesecondOctet = Integer.parseInt(secondOctets,2);
        int maskthirdOctet = Integer.parseInt(thirdOctets,2);
        int maskfourthOctet = Integer.parseInt(fourthOctets,2);

        int networkfirstOctet = Integer.parseInt(firstOctetsNetwork,2);
        int networksecondOctet = Integer.parseInt(secondOctetsNetwork,2);
        int networkthirdOctet = Integer.parseInt(thirdOctetsNetwork,2);
        int networkfourthOctet = Integer.parseInt(fourthOctetsNetwork,2);

        int brodcastFirstOctet = maskfirstOctet + networkfirstOctet;
        int brodcastSecondOctet = maskesecondOctet + networksecondOctet;
        int brodcastThirdOctet = maskthirdOctet + networkthirdOctet;
        int brodcastFourthOctet = maskfourthOctet + networkfourthOctet - 1;


        String firstBinaryOctet = convertToBinary(brodcastFirstOctet);
        String secondBinaryOctet = convertToBinary(brodcastSecondOctet);
        String thirdBinaryOctet = convertToBinary(brodcastThirdOctet);
        String fourthBinaryOctet = convertToBinary(brodcastFourthOctet);


        return firstBinaryOctet+secondBinaryOctet+thirdBinaryOctet+fourthBinaryOctet;

    }
    public int maxCountOfHosts(){

        double hosts = Math.pow(2,32-mask) -2;

        return (int)hosts;
    }
    public String maskDec(){

        String maskBinaryString = new String();
        for(int i=0;i<32;i++){
            maskBinaryString += maskBinary[i];
        }
        maskBinaryS = maskBinaryString;
        String firstOctets = maskBinaryString.substring(0,8);
        String secondOctets = maskBinaryString.substring(8,16);
        String thirdOctets = maskBinaryString.substring(16,24);
        String fourthOctets = maskBinaryString.substring(24,32);

        int maskfirstOctet = Integer.parseInt(firstOctets,2);
        int maskesecondOctet = Integer.parseInt(secondOctets,2);
        int maskthirdOctet = Integer.parseInt(thirdOctets,2);
        int maskfourthOctet = Integer.parseInt(fourthOctets,2);


        return maskfirstOctet + "." + maskesecondOctet + "." + maskthirdOctet + "." + maskfourthOctet;
    }




    static String convertToBinary(int no){

        String binary = new String();

        while (no > 0){
            int result = no%2;
            binary += Integer.toString(result);
            no = no/2;
        }
        int length = binary.length();
        if(length<8){

            for(int j=0;j<8-length;j++)
                binary = "0" + binary;
        }

        return binary;
    }







    public char[] stringToCharArray(String string){


        string =string.substring(string.indexOf("/")+1);

        String[] octetArray = string.split("\\.");
        StringBuilder stringBuilder = new StringBuilder();
        for (String x : octetArray){
            int octet = Integer.parseInt(x);
            String binaryOctetString = Integer.toBinaryString(octet);

            while(binaryOctetString.length() < 8) {
                binaryOctetString = "0"+ binaryOctetString;
            }
            stringBuilder.append(binaryOctetString);
        }
        return stringBuilder.toString().toCharArray();
    }


}
