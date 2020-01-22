package pk.lkarten;

//import java.util.ArrayList;
//import java.util.ListIterator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Lernkartei implements Serializable {


    private HashSet<Lernkarte> karten;
    private static final long serialVersionUID = -3549468280101226180L;

//    public HashSet<Lernkarte> getKarten() {
//        return karten;
//    }

    public Lernkartei() {
        this.karten = new HashSet<>();
    }

    public void hinzufuegen(Lernkarte karte) throws UngueltigeKarteException {
        karte.validiere();
        karten.add(karte);
    }

    public void druckeAlleKarten() throws IOException {
        List<Lernkarte> sortList = new ArrayList<>(karten);
        Collections.sort(sortList, new SortiereLernkarte());
        for (Lernkarte k : sortList) {
            k.druckeKarte();
        }
    }

    public int gibAnzahlKarten() {
        for (int i = 0; i < karten.size(); i++) {
            karten.size();
        }
        return karten.size();
    }

    public HashSet<Lernkarte> gibKartenZuKategorie(String kategorie) {
        HashSet<Lernkarte> arrayListKategorie = new HashSet<>();
        for (Lernkarte lernkarte : karten) {
            if (lernkarte.getKategorie().equals(kategorie)) {
                arrayListKategorie.add(lernkarte);
            }
        }
        return arrayListKategorie;
    }

    public ArrayList<Lernkarte> erzeugeDeck(int anzahlKarten) {
        ArrayList<Lernkarte> deck = new ArrayList<>();
        Random random = new Random();
        while (anzahlKarten != 0) {
            int zufaellig = random.nextInt(karten.size());
            int zaehler = 0;
            for (Lernkarte k : karten) {
                if (zaehler == zufaellig)
                    deck.add(k);
            }
            anzahlKarten--;
        }
        return deck;
    }

    public void exportiereEintraegeAlsCsv(File datei) throws IOException {
        String csv = "";
        for (Lernkarte lernkarten : karten) {
            csv += lernkarten.exportiereAlsCsv();
        }
        try (FileOutputStream fos = new FileOutputStream(datei)) {
            fos.write(csv.getBytes());
            fos.flush();
        }
    }

    public void exportiereEintraegeAlsCsvNio(File datei) {
        StringBuilder csv = new StringBuilder();
        for (Lernkarte lernkarten : karten) {
            csv.append(lernkarten.exportiereAlsCsv());
        }
        String path = "/home/chris/pk1-praktikum/" + datei + ".csv";
        Path filePath = Paths.get(path);
        try {
            Files.writeString(filePath, csv.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void speichern() {
        ArrayList erg = new ArrayList<>();
        File file = new File("serialisierung.ser");
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(karten);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void laden() {
        File file = new File("serialisierung.ser");
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            karten = (HashSet<Lernkarte>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Iterator<Lernkarte> getIterator() {
        Iterator iterator = karten.iterator();
//        while(iterator.hasNext()) {
//            iterator.next();
//        }
        return iterator;
    }
}
