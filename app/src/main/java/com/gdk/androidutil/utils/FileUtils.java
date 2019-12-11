package com.gdk.androidutil.utils;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import com.gdk.androidutil.commen.MyApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * 文件工具类
 *
 * @author gdk
 */
public class FileUtils {

    public static String getResourceFromAssets(String filename) {
        AssetManager assetManager = MyApplication.getInstance().getAssets();
        InputStream ins = null;
        try {
            ins = assetManager.open(filename);
            return readTextFile(ins);
        } catch (Exception e) {
        }
        return null;
    }

    public static InputStream getResourceImageFromAssets(String imageName) {
        AssetManager assetManager = MyApplication.getInstance().getAssets();
        InputStream ins = null;
        try {
            ins = assetManager.open(imageName);
            return ins;
        } catch (Exception e) {
        }
        return null;
    }

    public static String getSDApplictionDir() {
        boolean hasSd = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (!hasSd) {
            return null;
        }
        String appPath = Environment.getExternalStorageDirectory() + "/ebaozhang";
        File dir = new File(appPath);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                return null;
            } else {
                return appPath + "/";
            }
        } else {
            return appPath + "/";
        }
    }

    public static String readTextFile(InputStream inputStream) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];

        int len;

        try {

            while ((len = inputStream.read(buf)) != -1) {

                outputStream.write(buf, 0, len);

            }


        } catch (IOException e) {

        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
            }
        }

        return outputStream.toString();

    }

    public static String getStringFromFile(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] datas = StreamUtil.getBytesFromStream(fis);

            return new String(datas, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void saveBitmapToFile(Bitmap mBitmap, String path) {
        if (mBitmap == null) {
            return;
        }
        File f = new File(path);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean writeDataToFile(String data, File file) {
        FileOutputStream fos = null;
        try {
            byte[] datas = data.getBytes();
            fos = new FileOutputStream(file);
            fos.write(datas);
            fos.flush();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * Deletes a directory recursively.
     *
     * @param directory directory to delete
     * @throws IOException in case deletion is unsuccessful
     */
    public static void deleteDirectory(File directory) throws IOException {
        if (!directory.exists()) {
            return;
        }
        if (!isSymlink(directory)) {
            cleanDirectory(directory);
        }
        if (!directory.delete()) {
            String message =
                    "Unable to delete directory " + directory + ".";
            throw new IOException(message);
        }
    }

    /**
     * Cleans a directory without deleting it.
     *
     * @param directory directory to clean
     * @throws IOException in case cleaning is unsuccessful
     */
    public static void cleanDirectory(File directory) throws IOException {
        if (!directory.exists()) {
            String message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        }

        if (!directory.isDirectory()) {
            String message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        }
        File[] files = directory.listFiles();
        if (files == null) {  // null if security restricted
            throw new IOException("Failed to list contents of " + directory);
        }

        IOException exception = null;
        for (File file : files) {
            try {
                forceDelete(file);
            } catch (IOException ioe) {
                exception = ioe;
            }
        }

        if (null != exception) {
            throw exception;
        }
    }

    /**
     * Determines whether the specified file is a Symbolic Link rather than an actual file.
     * <p>
     * Will not return true if there is a Symbolic Link anywhere in the path,
     * only if the specific file is.
     * <p>
     * <b>Note:</b> the current implementation always returns {@code false} if the system
     * is detected as Windows using {@link 'FilenameUtils#isSystemWindows()}
     *
     * @param file the file to check
     * @return true if the file is a Symbolic Link
     * @throws IOException if an IO error occurs while checking the file
     * @since 2.0
     */
    public static boolean isSymlink(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("File must not be null");
        }
        if (isSystemWindows()) {
            return false;
        }
        File fileInCanonicalDir = null;
        if (file.getParent() == null) {
            fileInCanonicalDir = file;
        } else {
            File canonicalDir = file.getParentFile().getCanonicalFile();
            fileInCanonicalDir = new File(canonicalDir, file.getName());
        }

        if (fileInCanonicalDir.getCanonicalFile().equals(fileInCanonicalDir.getAbsoluteFile())) {
            return false;
        } else {
            return true;
        }
    }

    private static final char WINDOWS_SEPARATOR = '\\';
    private static final char SYSTEM_SEPARATOR = File.separatorChar;

    static boolean isSystemWindows() {
        return SYSTEM_SEPARATOR == WINDOWS_SEPARATOR;
    }

    //-----------------------------------------------------------------------

    /**
     * Deletes a file. If file is a directory, delete it and all sub-directories.
     * <p>
     * The difference between File.delete() and this method are:
     * <ul>
     * <li>A directory to be deleted does not have to be empty.</li>
     * <li>You get exceptions when a file or directory cannot be deleted.
     * (java.io.File methods returns a boolean)</li>
     * </ul>
     *
     * @param file file or directory to delete, must not be {@code null}
     * @throws NullPointerException  if the directory is {@code null}
     * @throws FileNotFoundException if the file was not found
     * @throws IOException           in case deletion is unsuccessful
     */
    public static void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
        } else {
            boolean filePresent = file.exists();
            if (!file.delete()) {
                if (!filePresent) {
                    throw new FileNotFoundException("File does not exist: " + file);
                }
                String message =
                        "Unable to delete file: " + file;
                throw new IOException(message);
            }
        }
    }

    public static boolean createDir(String path) {
        File f;
        f = new File(path);
        if (f.exists() && f.isDirectory())
            return true;

        return f.mkdirs();
    }

    /**
     * 将源文件拷贝到目标目录下
     *
     * @param srcPath
     * @param toDir
     */
    public static void copyFile(String srcPath, String toDir) {
        if (!TextUtils.isEmpty(srcPath) && !TextUtils.isEmpty(toDir)) {
            try {
                File srcFile = new File(Uri.parse(srcPath).getPath());
                File to = new File(toDir + File.separator + srcFile.getName());
                if (srcFile.canRead()) {
                    to.getParentFile().mkdirs();
                    byte[] buffer = new byte[1024];
                    InputStream fis = new FileInputStream(srcFile);
                    OutputStream fos = new FileOutputStream(to);
                    int b = fis.read(buffer);
                    while (b != -1) {
                        fos.write(buffer, 0, b);
                        b = fis.read(buffer);
                    }
                    fis.close();
                    fos.flush();
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将源文件拷贝到目标目录下
     *
     * @param srcPath
     * @param toDir
     */
    public static void copyImgFile(String srcPath, String toDir) {
        if (!TextUtils.isEmpty(srcPath) && !TextUtils.isEmpty(toDir)) {
            File srcFile = new File(Uri.parse(srcPath).getPath());
            String fileName = srcFile.getName();
            int index = fileName.lastIndexOf('.');
            String ext = fileName.substring(index);
            try {
                Bitmap bitmap = null;
                try {
                    bitmap = BitmapFactory.decodeFile(Uri.parse(srcPath).getPath());
                    if (bitmap != null) {
                        bitmap = Zoombitmap.imageZoom(bitmap, ext);
                    }
                } catch (Exception e) {

                }
                if (bitmap == null) {
                    return;
                }
                File to = new File(toDir + File.separator + fileName);
                OutputStream outStream = new FileOutputStream(to);
                if (ext.compareToIgnoreCase(".png") == 0) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                } else {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                }
                outStream.flush();
                outStream.close();
                bitmap.recycle();
                bitmap = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static File storeImage(Bitmap bmp, String fileName) throws IOException {
        int index = fileName.lastIndexOf('.');
        String name = fileName.substring(0, index);
        String ext = fileName.substring(index);
        File file = File.createTempFile("tmp_" + name, ext);
        OutputStream outStream = new FileOutputStream(file);
        if (ext.compareToIgnoreCase(".png") == 0) {
            bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
        } else {
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        }
        outStream.flush();
        outStream.close();
        return file;
    }

    /**
     * 将源目录中的文件拷贝到目标目录下
     *
     * @param fromDir
     * @param toDir
     */
    public static void copyAllFiles(String fromDir, String toDir) {
        if (!TextUtils.isEmpty(fromDir) && !TextUtils.isEmpty(toDir)) {
            try {
                File fromDirFile = new File(fromDir);
                File[] filelist = fromDirFile.listFiles();
                if (filelist != null) {
                    for (File f : filelist) {
                        File to = new File(toDir + File.separator + f.getName());
                        if (f.isDirectory()) {
                            to.mkdirs();
                            copyAllFiles(f.getAbsolutePath(), to.getAbsolutePath());
                        } else {
                            if (f.canRead()) {
                                to.getParentFile().mkdirs();
                                byte[] buffer = new byte[1024];
                                InputStream fis = new FileInputStream(f);
                                OutputStream fos = new FileOutputStream(to);
                                int b = fis.read(buffer);
                                while (b != -1) {
                                    fos.write(buffer, 0, b);
                                    b = fis.read(buffer);
                                }
                                fis.close();
                                fos.flush();
                                fos.close();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取sdcard绝对路径
     *
     * @return sdcard可用返回绝对路径，否则返回null
     */
    public static String getSdCardPath() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();

        }
        return null;
    }

    public static String getTempPath() {
        String tempDir = getSdCardPath() + "/ebaoTemp/";
        return tempDir;
    }

    public static File writeByteArrayToPath(String path, byte[] input) {
        FileOutputStream fos = null;
        try {
            File f = new File(path);

            if (!f.exists()) {
                f.createNewFile();
            }

            if (!f.isFile()) {
                return null;
            } else {
                fos = new FileOutputStream(f);
                fos.write(input);
                fos.flush();
                fos.close();
                return f;
            }
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (null != fos)
                    fos.close();
            } catch (IOException e) {
                //do nothing
            }
        }
    }
}
