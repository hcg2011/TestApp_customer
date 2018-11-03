package com.prize;

import android.app.Application;
import android.content.res.AssetManager;
import android.util.Base64;

import com.prize.utils.Log_sdk;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/18 12:51
 */

public class RDCpplict {
    public static Application application;
    public static String logTime = null;

    public RDCpplict() {
    }

    public static void init(Application applications) {
        if (logTime == null) {
            logTime = System.currentTimeMillis() + "";
        }
        if (application == null) {
            application = applications;
            RDCpplict appliction = new RDCpplict();
            File files = new File(application.getFilesDir() + "/plugs/");
            if (files.exists()) {
                File[] var3 = files.listFiles();
                int var4 = var3.length;

                for (int var5 = 0; var5 < var4; ++var5) {
                    File file = var3[var5];
                    if (file.isFile() && file.getName().contains(".jar") || file.getName().contains(".lt")) {
                        Log_sdk.i(RDCpplict.class, "加载:" + file.getName());
                        String target = file.getName().contains(".jar") ? ".jar" : ".lt";
                        appliction.saveJar(file.getName().replace(target, ""));
                    }
                }
            } else {
                files.mkdirs();
                AssetManager assetManager = application.getResources().getAssets();
                try {
                    String[] lts = assetManager.list("");
                    String[] var12 = lts;
                    int var13 = lts.length;

                    for (int var14 = 0; var14 < var13; ++var14) {
                        String lt = var12[var14];
                        if (lt.contains(".lt")) {
                            appliction.saveJar(lt.replace(".lt", ""));
                        }
                    }
                } catch (IOException var9) {
                    var9.printStackTrace();
                }
            }
        }
    }

    private void saveJar(String fileName) {
        File path;
        if (fileName.contains("prof")) {
            path = new File(fileName);
            path.delete();
        } else {
            path = new File(application.getFilesDir() + "/plugs");
            if (!path.exists()) {
                path.mkdirs();
            }

            File lt = new File(application.getFilesDir() + "/plugs/" + fileName + ".lt");
            File jar = new File(application.getFilesDir() + "/plugs/" + fileName + ".jar");

            try {
                if (!lt.exists() && !jar.exists()) {
                    AssetManager assetManager = application.getResources().getAssets();
                    InputStream inputStream = assetManager.open(fileName + ".lt");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder buffer = new StringBuilder();

                    String tmp;
                    while ((tmp = reader.readLine()) != null) {
                        buffer.append(tmp);
                    }

                    reader.close();
                    inputStream.close();
                    this.decode(buffer.toString(), fileName);
                    this.getData(fileName);
                } else if (lt.exists() && !jar.exists()) {
                    BufferedReader reader = new BufferedReader(new FileReader(lt));
                    StringBuilder buffer = new StringBuilder();

                    String tmp;
                    while ((tmp = reader.readLine()) != null) {
                        buffer.append(tmp);
                    }

                    reader.close();
                    this.decode(buffer.toString(), fileName);
                    this.getData(fileName);
                } else {
                    if (lt.exists() && jar.exists()) {
                        jar.delete();
                        this.saveJar(fileName);
                        return;
                    }

                    this.getData(fileName);
                }

                if (lt.exists()) {
                    lt.delete();
                }
            } catch (Exception var10) {
                var10.printStackTrace();
            }

        }
    }

    private void getData(String jarName) {
        File path = new File(application.getFilesDir() + "/plugs");
        if (!path.exists()) {
            path.mkdirs();
        }

        File jar = new File(application.getFilesDir() + "/plugs/" + jarName + ".jar");
        if (!jar.exists()) {
            Log_sdk.i(this, jar.getAbsolutePath());
            Log_sdk.i(this, "" + jar.exists());
            this.saveJar(jarName);
        } else {
            try {
                Object[] t1 = this.getDexElements(application.getClassLoader());
                DexClassLoader loader = new DexClassLoader(jar.getAbsolutePath(), application.getFilesDir() + "/plugs/", (String) null, application.getClassLoader());
                Object[] t2 = this.getDexElements(loader);
                this.saveLibPath(t1, t2);
                this.removeLib(loader);
                this.getDexElements(application.getClassLoader());
            } catch (Exception var7) {
                var7.printStackTrace();
            }

        }
    }

    private void saveLibPath(Object[] t1, Object[] t2) throws Exception {
        Field[] fields = BaseDexClassLoader.class.getDeclaredFields();
        Field pathListField = null;
        Field[] var5 = fields;
        int var6 = fields.length;

        for (int var7 = 0; var7 < var6; ++var7) {
            Field field = var5[var7];
            if (field.getName().equals("pathList")) {
                pathListField = field;
            }
        }

        if (pathListField != null) {
            pathListField.setAccessible(true);
            Object pathList = pathListField.get(application.getClassLoader());
            Field dexElements = pathList.getClass().getDeclaredField("dexElements");
            dexElements.setAccessible(true);
            Object[] obj = Arrays.copyOf(t1, t1.length + t2.length);
            int i = t1.length;

            for (int j = 0; i < t1.length + t2.length; ++j) {
                Array.set(obj, i, t2[j]);
                ++i;
            }

            dexElements.set(pathList, obj);
        }
    }

    private void removeLib(ClassLoader loader) {
        try {
            Field[] fields = BaseDexClassLoader.class.getDeclaredFields();
            Field pathListField = null;
            Field[] var4 = fields;
            int var5 = fields.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                Field field = var4[var6];
                if (field.getName().equals("pathList")) {
                    pathListField = field;
                }
            }

            if (pathListField == null) {
                return;
            }

            pathListField.setAccessible(true);
            Object pathList = pathListField.get(loader);
            Field dexElements = pathList.getClass().getDeclaredField("dexElements");
            dexElements.setAccessible(true);
            dexElements.set(pathList, (Object) null);
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }

    private Object[] getDexElements(ClassLoader loader) {
        Log_sdk.i(this, "===============start============");

        try {
            Field[] fields = BaseDexClassLoader.class.getDeclaredFields();
            Field pathListField = null;
            Field[] var4 = fields;
            int var5 = fields.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                Field field = var4[var6];
                Log_sdk.i(this, field.getName());
                if (field.getName().equals("pathList")) {
                    pathListField = field;
                }
            }

            if (pathListField == null) {
                return null;
            } else {
                pathListField.setAccessible(true);
                Object pathList = pathListField.get(loader);
                Field dexElementsField = pathList.getClass().getDeclaredField("dexElements");
                dexElementsField.setAccessible(true);
                Object[] dexElements = (Object[]) ((Object[]) dexElementsField.get(pathList));
                Log_sdk.i(this, "dexElements length = " + dexElements.length);
                Log_sdk.i(this, "===============end============");
                return dexElements;
            }
        } catch (Exception var8) {
            var8.printStackTrace();
            return null;
        }
    }

    private void decode(String str, String fileName) {
        try {
            File jar = new File(application.getFilesDir() + "/plugs/" + fileName + ".jar");
            if (jar.exists()) {
                jar.delete();
            }

            byte[] b = Base64.decode(str, 0);
            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(jar));
            outputStream.write(b);
            outputStream.flush();
            outputStream.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }
}

