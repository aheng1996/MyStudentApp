package com.example.mystudentapp.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * FieldSamplingSystemStaff
 *
 * @author sjw
 * @date 2018/8/8.
 */
public class FileUtils {
    public static final String path = "/sdcard/bs/";
    public static final String videoPath = "/sdcard/bs/video/";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public static File getFile(String name) {
        File tempFile = new File(path, name);
        try {
            if (!tempFile.exists()) {
                tempFile.getParentFile().mkdirs();
                tempFile.createNewFile();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempFile;
    }
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        }
        else if (ContentResolver.SCHEME_FILE.equalsIgnoreCase(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
    public static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    public static File getVideoFile(String name) {
        File tempFile = new File(videoPath, name);
        try {
            if (!tempFile.exists()) {
                tempFile.getParentFile().mkdirs();
                tempFile.createNewFile();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempFile;
    }

    public static String getPicName() {
        return simpleDateFormat.format(new Date()) + ".jpg";
    }

    public static String getName() {
        return simpleDateFormat.format(new Date());
    }

    public static String getImgPath(String photoName) {
        return path + File.separator + photoName;
    }

    public static String getVideoPath(String photoName) {
        return videoPath + File.separator + photoName;
    }


    public static Uri fileUri(String filePath) {
        return new Uri.Builder().scheme("file").path(filePath).build();
    }

    public static int getResourceId(@NonNull Context context, String fileName) {
        return context.getResources().getIdentifier(fileName, "drawable", context.getPackageName());
    }

    public static Uri resourceUri(@NonNull Context context, int resId) {
        return Uri.parse("res://"
                + context.getPackageName() + "/"
                + resId);
    }

    public static String readAssets(@NonNull Context context, String fileName) {
        try {
            AssetManager assetManager = context.getAssets();
            return readInStream(assetManager.open(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String readInStream(@NonNull InputStream inStream) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(inStream));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 向手机写文件
     *
     * @param buffer
     * @param folder
     * @param fileName
     * @return
     */
    public static boolean writeFile(@NonNull byte[] buffer, @NonNull String folder,
                                    String fileName) {
        boolean writeSucc = false;
        FileOutputStream out = null;
        try {
            File fileDir = new File(folder.substring(0,
                    folder.lastIndexOf(File.separator)));
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            File file = new File(folder + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new FileOutputStream(file);
            out.write(buffer);
            writeSucc = true;
        } catch (Exception e) {
            e.printStackTrace();
            writeSucc = false;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return writeSucc;
    }

    public static String readFile(@NonNull String folder, String fileName) {
        File fileDir = new File(folder.substring(0,
                folder.lastIndexOf(File.separator)));
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File file = new File(folder + fileName);
        if (!file.exists()) {
            return "";
        }

        try {
            return readInStream(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 根据文件绝对路径获取文件名
     *
     * @param filePath
     * @return
     */
//    public static String getFileName(@NonNull String filePath) {
//        if (StringUtils.isEmpty(filePath))
//            return "";
//        return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
//    }

    /**
     * 根据文件的绝对路径获取文件名但不包含扩展名
     *
     * @param filePath
     * @return
     */
//    public static String getFileNameNoFormat(@NonNull String filePath) {
//        if (StringUtils.isEmpty(filePath)) {
//            return "";
//        }
//        int point = filePath.lastIndexOf('.');
//        return filePath.substring(filePath.lastIndexOf(File.separator) + 1,
//                point);
//    }

    /**
     * 获取文件扩展名
     *
     * @param fileName
     * @return
     */
//    public static String getFileFormat(@NonNull String fileName) {
//        if (StringUtils.isEmpty(fileName))
//            return "";
//
//        int point = fileName.lastIndexOf('.');
//        return fileName.substring(point + 1);
//    }

    /**
     * 获取文件大小
     *
     * @param filePath
     * @return
     */
    public static long getFileSize(@NonNull String filePath) {
        long size = 0;

        File file = new File(filePath);
        if (file != null && file.exists()) {
            size = file.length();
        }
        return size;
    }

    /**
     * 获取文件大小
     *
     * @param size 字节
     * @return
     */
    public static String getFileSize(long size) {
        if (size <= 0)
            return "0";
        java.text.DecimalFormat df = new java.text.DecimalFormat("##.##");
        float temp = (float) size / 1024;
        if (temp >= 1024) {
            return df.format(temp / 1024) + "M";
        } else {
            return df.format(temp) + "K";
        }
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return B/KB/MB/GB
     */
    @NonNull
    public static String formatFileSize(long fileS) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 获取目录文件大小
     *
     * @param dir
     * @return
     */
    public static long getDirSize(@Nullable File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }

    /**
     * 检查路径是否存在
     *
     * @param path
     * @return
     */
    public static boolean checkFilePathExists(@NonNull String path) {
        return new File(path).exists();
    }

    /**
     * 计算SD卡的剩余空间
     *
     * @return 返回-1，说明没有安装sd卡
     */
    @SuppressWarnings("deprecation")
    public static long getFreeDiskSpace() {
        String status = Environment.getExternalStorageState();
        long freeSpace = 0;
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            try {
                File path = Environment.getExternalStorageDirectory();
                StatFs stat = new StatFs(path.getPath());
                long blockSize = stat.getBlockSize();
                long availableBlocks = stat.getAvailableBlocks();
                freeSpace = availableBlocks * blockSize / 1024;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return -1;
        }
        return (freeSpace);
    }

    /**
     * 检查是否安装SD卡
     *
     * @return
     */
    public static boolean checkSaveLocationExists() {
        String sDCardStatus = Environment.getExternalStorageState();
        boolean status;
        if (sDCardStatus.equals(Environment.MEDIA_MOUNTED)) {
            status = true;
        } else
            status = false;
        return status;
    }

    /**
     * 删除空目录
     * <p>
     * 返回 0代表成功 ,1 代表没有删除权限, 2代表不是空目录,3 代表未知错误
     *
     * @return
     */
    public static int deleteBlankPath(@NonNull String path) {
        File f = new File(path);
        if (!f.canWrite()) {
            return 1;
        }
        if (f.list() != null && f.list().length > 0) {
            return 2;
        }
        if (f.delete()) {
            return 0;
        }
        return 3;
    }

    /**
     * 重命名
     *
     * @param oldName
     * @param newName
     * @return
     */
    public static boolean reNamePath(@NonNull String oldName, @NonNull String newName) {
        File f = new File(oldName);
        return f.renameTo(new File(newName));
    }

    /**
     * 删除文件
     *
     * @param filePath
     */
    public static boolean deleteFileWithPath(@NonNull String filePath) {
        SecurityManager checker = new SecurityManager();
        File f = new File(filePath);
        checker.checkDelete(filePath);
        if (f.isFile()) {
            Log.i("DM DeleteFile", filePath);
            f.delete();
            return true;
        }
        return false;
    }

    /**
     * 清空一个文件夹
     *
     * @param filePath
     */
    public static void clearFilePath(@NonNull String filePath) {
        List<File> files = listPathFiles(filePath);
        if (files == null) return;
        for (File f : files) {
            if (f.isDirectory()) {
                clearFilePath(f.getAbsolutePath());
            }
            f.delete();
        }
    }

    //删除文件或文件夹
    public static void deleteFilePath(@NonNull String filePath) {
        clearFilePath(filePath);
        SecurityManager checker = new SecurityManager();
        File f = new File(filePath);
        if (f.isFile()) {
            f.delete();
        } else {
            clearFilePath(f.getAbsolutePath());
            f.delete();
        }
    }

    /**
     * 获取SD卡的根目录
     *
     * @return
     */
    public static String getSDRoot() {

        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 获取一个文件夹下的所有文件
     *
     * @param root
     * @return
     */
    public static List<File> listPathFiles(@NonNull String root) {
        List<File> allDir = new ArrayList<File>();
        SecurityManager checker = new SecurityManager();
        File path = new File(root);
        checker.checkRead(root);
        File[] files = path.listFiles();
        if (files == null) return null;
        for (File f : files) {
            allDir.add(f);
        }
        return allDir;
    }

    public enum PathStatus {
        SUCCESS, EXITS, ERROR
    }

    /**
     * 创建目录
     *
     * @param newPath
     */
    @NonNull
    public static PathStatus createFilePath(@NonNull String newPath) {
        File path = new File(newPath);
        if (path.exists()) {
            return PathStatus.EXITS;
        }
        if (path.mkdir()) {
            return PathStatus.SUCCESS;
        } else {
            return PathStatus.ERROR;
        }
    }

}
