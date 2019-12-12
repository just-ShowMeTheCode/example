package com.demo;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/217:26
 */
@Slf4j
public class ZipfileNoBuffer {
    public static String ZIP_FILE_NAME = "C:\\Users\\fumj\\Desktop\\傅乐瑶三周岁\\zip\\ff.zip";
    public static void main(String[] args) throws IOException {
        String fileName = "C:\\Users\\fumj\\Desktop\\傅乐瑶三周岁\\XX (2).JPG";
        File file = new File(fileName);
//        zipFileWithNoBuffer(file,ZIP_FILE_NAME);
//        zipFileWithBuffer(file,ZIP_FILE_NAME);
        zipFileWithChannel(file,ZIP_FILE_NAME);
    }

    private static void zipFileWithNoBuffer(File file, String zipFile) throws IOException {
        Long start = System.currentTimeMillis();
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(
                zipFile
        ));
        String relativePath = file.getName();
        ZipEntry entry = new ZipEntry(relativePath);
        zipOutputStream.putNextEntry(entry);
        InputStream inputStream = new FileInputStream(file);
        int length = 0;
        while ((length = inputStream.read()) != -1){
            System.out.println(length);
            zipOutputStream.write(length);
        }
        zipOutputStream.flush();
        zipOutputStream.closeEntry();
        inputStream.close();
        log.info("zipFileWithNoBuffer cost time {}",(System.currentTimeMillis() - start));
    }

    private static void zipFileWithBuffer(File file, String zipFile) throws IOException {
        Long start = System.currentTimeMillis();
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(
                zipFile
        ));
        BufferedOutputStream outputStream = new BufferedOutputStream(zipOutputStream);
        String relativePath = file.getName();
        ZipEntry entry = new ZipEntry(relativePath);
        zipOutputStream.putNextEntry(entry);
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        int length = 0;
        while ((length = inputStream.read()) != -1){
            outputStream.write(length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        log.info("zipFileWithBuffer cost time {}",(System.currentTimeMillis() - start));
    }


    private static void zipFileWithChannel(File file, String zipFile) throws IOException {
        Long start = System.currentTimeMillis();
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(
                zipFile
        ));
        WritableByteChannel writableByteChannel = Channels.newChannel(zipOutputStream);
        String relativePath = file.getName();
        ZipEntry entry = new ZipEntry(relativePath);
        zipOutputStream.putNextEntry(entry);
        FileChannel fileChannel = new FileInputStream(file).getChannel();
        fileChannel.transferTo(0,file.getTotalSpace(),writableByteChannel);
        fileChannel.close();
        writableByteChannel.close();
        log.info("zipFileWithChannel cost time {}",(System.currentTimeMillis() - start));
    }
}
