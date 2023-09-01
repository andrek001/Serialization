package ru.netology;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress obj1 = new GameProgress(100, 10, 1, 10.4);
        GameProgress obj2 = new GameProgress(200, 30, 3, 15.0);
        GameProgress obj3 = new GameProgress(300, 50, 5, 20.1);
        saveGame("D:\\Games\\savegames\\1.dat", obj1);
        saveGame("D:\\Games\\savegames\\2.dat", obj2);
        saveGame("D:\\Games\\savegames\\3.dat", obj3);
        List<String> files = Arrays.asList("D:\\Games\\savegames\\1.dat", "D:\\Games\\savegames\\2.dat", "D:\\Games\\savegames\\3.dat");
        zipFiles("D:\\Games\\savegames\\saves.zip", files);


    }
    private static void saveGame (String address, GameProgress obj) {
        try (FileOutputStream fos  = new FileOutputStream(address);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    private static void zipFiles(String archivePath, List<String> filesPath) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(archivePath))) {
            for (int i = 0; i < filesPath.size(); i++) {
                try (FileInputStream fis = new FileInputStream(filesPath.get(i))) {
                    File file = new File (filesPath.get(i));
                    ZipEntry entry = new ZipEntry(file.getName());
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}