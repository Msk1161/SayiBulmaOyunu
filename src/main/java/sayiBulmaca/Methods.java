package sayiBulmaca;

import java.io.*;
import java.util.*;

public class Methods extends Objects{
    Scanner scan=new Scanner(System.in);
    List<String> listUretSayi =new ArrayList<>();

    String dosyaYolu = "src/main/java/sayiBulmaca/file.txt"; // Okunacak ve güncellenecek dosyanın yolu

    public void starting() throws IOException {
        dosyaOkuma();

        System.out.println("========== SAYI BULMA OYUNU ==========");
        System.out.println("||                                  ||");
        System.out.println("||  Sayı bulma oyununa hoşgeldiniz. ||");
        System.out.println("||     Şu an ki seviyeniz : "+getLevel()+".      ||");
        System.out.println("||                                  ||");
        System.out.println("============ made by msk =============");
        System.out.println("");
        bekletme(10);
    }
    public void aciklamaOncesi() throws IOException {
        System.out.println("Oyunun nasıl oynandığını öğrenmek istermisiniz. ");
        System.out.print("Oyunla ilgili açıklamayı görmek için 'E', oyuna başlamak için 'H' yazınız.>> ");
        String cevap = scan.next().toUpperCase().substring(0, 1);
        if (cevap.equals("E")) {
            aciklama();
            bekletme(40);
            seviyeDevam();
        } else {
            seviyeDevam();
        }
    }
    private void seviyeDevam() throws IOException {
        if(getLevel()==1){
            sayiUret(getLevel());
            System.out.println("===>>> OYUN BASLIYOR <<<====");
            bekletme(15);
            sayiKontrol();
        }else {
            System.out.print(getLevel() + ". seviyeden devam etmek icin  'D', 1. seviyeden baslamak icin 'Y' yaziniz. >>");
            String stChar = scan.next().toUpperCase().substring(0, 1);
            if(stChar.equals("D")){
                setLevel(getLevel());
                sayiUret(getLevel());
                System.out.println("===>>> OYUN BASLIYOR <<<====");
                bekletme(15);
                sayiKontrol();

            }else{
                setLevel(1);
                dosyaYazma();
                System.out.println("SEVIYE 1");
                sayiUret(getLevel());
                System.out.println("===>>> OYUN BASLIYOR <<<====");
                bekletme(15);
                sayiKontrol();

            }
        }

    }
    int kacKeredeBuldun;
    private void sayiKontrol() throws IOException {
        List<String> listGirilenSayi = new ArrayList<>();
        int arti;
        kacKeredeBuldun=0;
        int toplamBulunan;

        do {
            arti = 0;
            toplamBulunan = 0;
            listGirilenSayi.clear();
            System.out.print("Lutfen " + getLevel() + " basamakli, rakamlari birbirinden farkli bir sayi giriniz :");
            String strGirilenSayi = scan.next();

            for (int i = 0; i < strGirilenSayi.length(); i++) {
                listGirilenSayi.add(strGirilenSayi.substring(i, i + 1));
            }

            if (strGirilenSayi.replaceAll("[^0-9]", "").length() < getLevel()) {
                System.out.println("Girilen " + strGirilenSayi + " degeri uygun degildir.");
            } else if (strGirilenSayi.length() != getLevel()) {
                System.out.println("Girilen " + strGirilenSayi + " degeri uygun degildir.");
            } else if (listGirilenSayi.stream().distinct().count() < getLevel()) {
                System.out.println("Girilen " + strGirilenSayi + " degeri uygun degildir.");
            } else {
                for (int i = 0; i < getLevel() ; i++) {
                    for (int j = 0; j < getLevel() ; j++) {
                        if (listGirilenSayi.get(i).equals(listUretSayi.get(j))) {
                            toplamBulunan++;
                        }
                    }
                }
                    for (int i = 0; i <getLevel() ; i++) {
                        if (listGirilenSayi.get(i).equals(listUretSayi.get(i))) {
                            arti++;
                        }
                    }
                    kacKeredeBuldun++;

                    if(arti==getLevel()){
                        //System.out.println("Sayiniz : " + listGirilenSayi);
                        System.out.println("+ "+arti +"  Tebrikler, " + kacKeredeBuldun + ". denemede buldunuz.");
                        System.out.println("Tutulan Sayi : " + listUretSayi);
                    }else{
                        //System.out.println("Sayiniz : " + listGirilenSayi);
                        System.out.println("+ " + arti + ", - "+(toplamBulunan-arti));
                    }
                }

        } while (arti < getLevel());

        setLevel(getLevel()+1);
        if(getLevel()==10){
            System.out.println("Tebrikler. 9 seviyeli oyunu bitirdiniz.");
            puanHesapla();
            setLevel(1);
            dosyaYazma();
            System.out.println("Oyun seviyesi 1 olarak güncellendi.");
        }else {
            System.out.println("Tebrikler " + getLevel() + ". seviyeye gectiniz. ");
            puanHesapla();
            dosyaYazma();
        }

            System.out.println(getLevel() + ". seviyede devam etmek icin 'D', cikmak icin 'Q' yaziniz : ");
            String devamEt = scan.next().toUpperCase().substring(0, 1);
            if (devamEt.equals("D")) {

                starting();
                sayiUret(getLevel());
                System.out.println("===>>> OYUN BASLIYOR <<<====");
                bekletme(15);
                sayiKontrol();

            } else {
                System.out.println("Seviyeniz kaydedildi. istediginiz zaman aynı seviyeden devam edebilirsiniz.");
            }

    }
    private void sayiUret(int a) {
        Random rnd=new Random();
        int low=1;
        for (int i = 1; i <a ; i++) {
            low=low*10;
        }
        int high=low*10-1;

        do {
            int sayi = rnd.nextInt(high - low) + low;
            String strUretSayi=Integer.toString(sayi);
            for (int i = 0; i <a ; i++) {
                listUretSayi.add(strUretSayi.substring(i,i+1));
            }
            if(listUretSayi.stream().distinct().count()==a){
                break;
            }else listUretSayi.clear();
        }while (listUretSayi.isEmpty());
    }
    public void bekletme(int noktaSayisi){
        int beklemeSuresi = 500; // Her noktanın ardışık olarak gösterilme süresi (milisaniye cinsinden)

        for (int i = 0; i < noktaSayisi; i++) {
            System.out.print("* ");
            try {
                Thread.sleep(beklemeSuresi);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
    private void aciklama(){
        System.out.println(">>===================== SAYI BULMA OYUNU NASIL OYNANIR =====================>>");
        System.out.println("|| Bilgisayar random olarak rakamları birbirinden farklı sayı üretir.       ||");
        System.out.println("|| Kullanıcı bu sayıyı bulmak için rakamları birbirinden farklı sayı girer. ||");
        System.out.println("|| Bilgisayar bu iki sayının rakamları arasında karşılaştırma yapar,        ||");
        System.out.println("|| bilgisayar '+' (artı) ve '-' (eksi) degerlerle geri doner.               ||");
        System.out.println("|| '+' (artı) değer <--> yeri doğru bilenen sayi adedini,                   ||");
        System.out.println("|| '-' (eksi) değer <--> ise yeri yanlış bilinen sayi adedini gösterir.     ||");
        System.out.println("|| Kullanıcı basamak adedince '+' değer bulana kadar oyuna devam eder.      ||");
        System.out.println("|| Oyun bitirildiğinde yeni bir seviyeye geçer ve bu seviye kaydedilir.     ||");
        System.out.println("|| ======================================================================== ||");
    }
private void puanHesapla() throws IOException {
        double yeniPuan=getPuan();
        double levelPuan= getLevel()*100;
        levelPuan=levelPuan-(kacKeredeBuldun*10);
        yeniPuan=yeniPuan+levelPuan;
        setPuan(yeniPuan);
    System.out.println(getLevel()+". seviye puanınız : "+levelPuan);
    System.out.println("Toplam puanınız : "+yeniPuan);
}
private void dosyaOkuma() throws IOException {
// Dosyayı okuma işlemi
        String satir="";
    try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {

        while ((satir = br.readLine()) != null) {
            setLevel(Integer.parseInt(satir));
        }
    } catch (IOException e) {
        System.out.println("Dosya okuma hatası: " + e.getMessage());
    }
}
    private void dosyaYazma() throws IOException {
// Dosyayı yazma işlemi
        String yeniIcerik = "";
        yeniIcerik=String.valueOf(getLevel());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dosyaYolu, false))) {
            bw.write(yeniIcerik); // Yeni içeriği dosyaya yazma
            bekletme(10);
            System.out.println("Seviye güncelleme işlemi tamamlandı.");
        } catch (IOException e) {
            System.out.println("Dosya güncelleme hatası: " + e.getMessage());
        }    }

}
