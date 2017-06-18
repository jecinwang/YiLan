package com.wzhx.yilan.common.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class FileUtil
{
    private static final String TAG = "FileUtil";

    public static final int BUFFER_SIZE = 4 * 1024;

    public static boolean existSDCard()
    {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static String getCacheDirPath(Context context)
    {
        return context.getCacheDir().getAbsolutePath() + "/";
    }

    public static String parseFilename(String url)
    {
        try
        {
            String[] tmp = url.split("/");
            String filename = tmp[tmp.length - 1];
            if (isLocalUri(url))
                return filename;
            else
                return URLDecoder.decode(filename, "utf-8");
        }
        catch (UnsupportedEncodingException e)
        {
            LogUtils.error("UnsupportedEncodingException", e);
            return "";
        }
    }

    public static String replaceExtension(String filename, String extension)
    {
        StringBuffer sb = new StringBuffer(filename.substring(0, filename.lastIndexOf(".")));
        sb.append(".");
        sb.append(extension);
        return sb.toString();
    }

    public static String parseFileDir(String filepath)
    {
        int index = filepath.lastIndexOf('/');
        return filepath.substring(0, index + 1);
    }

    public static String getFrontname(String filename)
    {
        return filename.split("\\.")[0];
    }

    public static String getExtension(String filename)
    {
        String[] tmp = filename.split("\\.");
        return "." + tmp[tmp.length - 1];
    }

    public static File copyFile(String oldPath, String newPath) throws IOException
    {
        InputStream inStream = new FileInputStream(oldPath);
        return copyFile(inStream, newPath);
    }

    public static File copyFile(InputStream inStream, String target) throws IOException
    {
        FileOutputStream fs = new FileOutputStream(target);
        byte[] buffer = new byte[BUFFER_SIZE];
        int byteread;
        while ((byteread = inStream.read(buffer)) != -1)
        {
            fs.write(buffer, 0, byteread);
        }
        inStream.close();
        fs.close();
        return new File(target);
    }

    public static void deleteAllFiles(File file)
    {
        if (file != null && file.exists())
        {
            if (file.isFile())
            {
                file.delete();
            }
            else if (file.isDirectory())
            {
                File files[] = file.listFiles();
                for (File subfile : files)
                    deleteAllFiles(subfile);
            }
        }
    }

    public static boolean checkFolder(String folder)
    {
        File file = new File(folder);
        if (!file.exists())
        {
            return file.mkdirs();
        }
        else if (!file.isDirectory())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public static final String LOCAL_URI_PREFIX = "file://";

    public static boolean isLocalUri(String uri)
    {
        return uri.startsWith(LOCAL_URI_PREFIX) || uri.startsWith("/");
    }

    public static File getFileFromUrl(String url, String target) throws IOException
    {
        InputStream inputStream = (InputStream) new URL(url).getContent();
        return FileUtil.copyFile(inputStream, target);
    }

    public static String removeLocalPrefix(String uri)
    {
        return uri.replace(LOCAL_URI_PREFIX, "");
    }

    /**
     * 获取目录或文件大小 <功能详细描述>
     * 
     * @param file
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static double getDirSize(File file)
    {
        // 判断文件是否存在
        if (file.exists())
        {
            // 如果是目录则递归计算其内容的总大小
            if (file.isDirectory())
            {
                File[] children = file.listFiles();
                double size = 0;
                for (File f : children)
                    size += getDirSize(f);
                return size;
            }
            else
            {// 如果是文件则直接返回其大小,以“兆”为单位
                double size = (double) file.length() / 1024 / 1024;
                return size;
            }
        }
        else
        {
            return 0.0;
        }
    }

    public static boolean saveFile(String path, String data)
    {
        if (TextUtils.isEmpty(path) || data == null)
        {
            return false;
        }
        File f = new File(path);
        if (f.exists() && !f.delete())
        {
            return false;
        }

        try
        {
            if (!f.createNewFile())
            {
                return false;
            }
        }
        catch (Exception e)
        {
        }

        boolean ok = false;
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(f);
            fos.write(data.getBytes("UTF-8"));
            fos.flush();
            ok = true;
        }
        catch (Exception e)
        {

        }
        finally
        {
            try
            {
                if (fos != null)
                {
                    fos.close();
                }
            }
            catch (Exception e)
            {
                // ignore;
            }
        }
        return ok;
    }

    /**
     * file->String
     * @param file
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getStringFromFile(File file)
    {
        if (file == null || !file.exists() || !file.canRead())
        {
            return null;
        }

        FileInputStream fis = null;
        BufferedInputStream bufferedInput = null;
        ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
        try
        {
            fis = new FileInputStream(file);

            byte[] buffer = new byte[512];

            bufferedInput = new BufferedInputStream(fis);

            int bytesRead = 0;

            while ((bytesRead = bufferedInput.read(buffer)) != -1)
            {

                byteArrayInputStream.write(buffer, 0, bytesRead);
            }
            String content = byteArrayInputStream.toString();
            return content;
        }
        catch (Exception e)
        {
            LogUtils.error("open cache file " + e);
        }
        finally
        {
            byteArrayInputStream = null;
            if (fis != null)
            {
                try
                {
                    fis.close();
                    if (bufferedInput != null)
                    {
                        bufferedInput.close();
                    }
                }
                catch (Exception e)
                {
                    LogUtils.error("close cache file " + e);
                }
            }
        }
        return null;
    }
}
