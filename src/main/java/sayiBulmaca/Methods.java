package sayiBulmaca;

import java.io.*;
import java.util.*;

public class Methods extends Objects{
    Scanner scan=new Scanner(System.in);
    List<String> listUretSayi =new ArrayList<>();
    int kacKeredeBuldun;
    String dosyaYolu = "src/main/java/sayiBulmaca/file.txt"; // Okunacak ve güncellenecek dosyanın yolu
    String puanYolu = "src/main/java/sayiBulmaca/puan.txt"; // Okunacak ve güncellenecek dosyanın yolu

    public void starting() throws IOException {
        dosyaOkuma();
        puanOkuma();
        System.out.println("========== SAYI BULMA OYUNU ==========");
        System.out.println("||                                  ||");
        System.out.println("||  Sayı bulma oyununa hoşgeldiniz. ||");
        System.out.println("||      Oyun seviyeniz : "+getLevel()+".         ||");
        System.out.println("||      Oyun Puaniniz  : "+getPuan()+"      ||");
        System.out.println("||                                  ||");
        System.out.println("===========> made by msk <============");
        System.out.println("");
        System.out.println("===>>> OYUN YÜKLENİYOR <<<====");
        bekletme(10);
    }
    public void aciklamaOncesi() throws IOException {
        System.out.println("Oyunun nasıl oynandığını öğrenmek istermisiniz? ");
        System.out.print("Oyun ile ilgili açıklamayı görmek için 'E', oyuna başlamak için herhangi bir karakter yazınız.>> ");
        String cevap = scan.next().toUpperCase().substring(0, 1);
        if (cevap.equals("E")) {
            aciklama();
            bekletme(30);
            seviyeDevam();
        } else {
            seviyeDevam();
        }
    }
    private void seviyeDevam() throws IOException {
        if(getLevel()==1){
            sayiUret(getLevel());
            setPuan(0); // 1.
            System.out.println("===>>> OYUN BASLIYOR <<<====");
            bekletme(15);
            sayiKontrol();
        }else {
            System.out.print(getLevel() + ". seviyeden devam etmek icin  'D', 1. seviyeden başlamak için herhangi bir karakter yazınız. >>");
            String stChar = scan.next().toUpperCase().substring(0, 1);
            if(stChar.equals("D")){
                System.out.println(getLevel()+". SEVİYE YÜKLENİYOR");
                setLevel(getLevel());
                setPuan(getPuan()); // 2
                sayiUret(getLevel());
                System.out.println("===>>> OYUN BAŞLIYOR <<<====");
                bekletme(15);
                sayiKontrol();

            }else{
                System.out.println("1. SEVİYE YÜKLENİYOR");
                setLevel(1);
                setPuan(0);
                puanYazma(); //3
                dosyaYazma();
                sayiUret(getLevel());
                System.out.println("===>>> OYUN BAŞLIYOR <<<====");
                bekletme(15);
                sayiKontrol();

            }
        }

    }

    private void sayiKontrol() throws IOException {
        List<String> listGirilenSayi = new ArrayList<>();
        int arti;
        kacKeredeBuldun=0;
        int toplamBulunan;

        do {
            arti = 0;
            toplamBulunan = 0;
            listGirilenSayi.clear();
            System.out.print("Lutfen " + getLevel() + " basamaklı, rakamları birbirinden farklı bir sayı giriniz :");
            String strGirilenSayi = scan.next();

            for (int i = 0; i < strGirilenSayi.length(); i++) {
                listGirilenSayi.add(strGirilenSayi.substring(i, i + 1));
            }

            if (strGirilenSayi.replaceAll("[^0-9]", "").length() < getLevel()) {
                System.out.println("Girilen " + strGirilenSayi + " değeri uygun değildir.");
            } else if (strGirilenSayi.length() != getLevel()) {
                System.out.println("Girilen " + strGirilenSayi + " değeri uygun değildir.");
            } else if (listGirilenSayi.stream().distinct().count() < getLevel()) {
                System.out.println("Girilen " + strGirilenSayi + " değeri uygun değildir.");
            }else if(strGirilenSayi.substring(0,1).equals("0")){
                System.out.println("Girilen " + strGirilenSayi + " değeri uygun değildir.");
            }else {
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
                        System.out.println("+ "+arti +" <-> Tebrikler, " + kacKeredeBuldun + ". denemede buldunuz.");
                        String urettilenSayiYazdir="";
                        for (int i = 0; i <listUretSayi.size() ; i++) {
                            urettilenSayiYazdir=urettilenSayiYazdir + listUretSayi.get(i);
                        }
                        System.out.print("Tutulan Sayi : " + urettilenSayiYazdir);
                    }else{
                        //System.out.println("Sayiniz : " + listGirilenSayi);
                        System.out.println("+ " + arti + ", - "+(toplamBulunan-arti));
                    }
                }

        } while (arti < getLevel());
        levelDegerlendirme();
        /*
        puanHesapla();
        puanYazma();
        setLevel(getLevel()+1);
        if(getLevel()==10){
            System.out.println("Tebrikler. 9 seviyeli oyunu bitirdiniz.");
            setLevel(1);
            dosyaYazma();
            System.out.println("Oyun seviyesi 1 olarak güncellendi.");
        }else {
            System.out.println("Tebrikler " + getLevel() + ". seviyeye geçtiniz. ");
            dosyaYazma();
        }

            System.out.println(getLevel() + ". seviyeden devam etmek icin 'D', çıkmak için herhangi bir katakter yazınız : ");
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
*/
    }
    private void levelDegerlendirme() throws IOException {
        puanHesapla();
        puanYazma();
        setLevel(getLevel()+1);
        if(getLevel()==10){
            System.out.println("Tebrikler. 9 seviyeli oyunu bitirdiniz.");
            setLevel(1);
            dosyaYazma();
            System.out.println("Oyun seviyesi 1 olarak güncellendi.");
        }else {
            System.out.println("Tebrikler " + getLevel() + ". seviyeye geçtiniz. ");
            dosyaYazma();
        }

        System.out.println(getLevel() + ". seviyeden devam etmek icin 'D', çıkmak için herhangi bir katakter yazınız : ");
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
        System.out.println("|| =============================> made by msk <============================ ||");
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
        }
    }
    private void puanYazma() throws IOException {
// Dosyayı yazma işlemi
        String yeniPuan = "";
        yeniPuan=String.valueOf(getPuan());
        try (BufferedWriter bp = new BufferedWriter(new FileWriter(puanYolu, false))) {
            bp.write(yeniPuan); // Yeni içeriği dosyaya yazma
            bekletme(10);
            System.out.println("Puan güncelleme işlemi tamamlandı.");
        } catch (IOException e) {
            System.out.println("Dosya güncelleme hatası: " + e.getMessage());
        }
    }
    private void puanOkuma() throws IOException {
// Dosyayı okuma işlemi
        String puan="";
        try (BufferedReader bp = new BufferedReader(new FileReader(puanYolu))) {

            while ((puan = bp.readLine()) != null) {
                setPuan(Double.parseDouble(puan));
            }
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }
    }

}
