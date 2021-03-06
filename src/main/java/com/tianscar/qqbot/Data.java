package com.tianscar.qqbot;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tianscar.qqbot.image.ImageProcessor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;
import java.util.Scanner;

public class Data {

	private Data(){}

	public static final File home = System.getProperty("java.runtime.name").equals("Android Runtime") ? new File("/storage/emulated/0/") : new File(System.getProperty("user.home"));
	public static final File dataDir = new File(home, "tianscar-qqbot");

	public static Scanner console;
	public static File propFile;
	public static Properties prop;

	public static void init() {

		boolean succeed = true;
		if (!dataDir.exists()) {
			succeed = dataDir.mkdirs();
		}
		else if (dataDir.isFile()) succeed = false;
		if (!succeed) {
			System.err.println("Unable to create data directory: \n" + dataDir.getAbsolutePath());
			System.exit(1);
		}

		console = new Scanner(System.in);

		propFile = new File(dataDir, "robot.properties");
		prop = new Properties();
		loadToProp(prop, propFile);

		initProp("port","设置 MiraiConsoleLoader 的 IP 和端口 (例: 0.0.0.0:8888): ","未设置");
		initProp("qq","设置已登陆在MiralConsoleLoader上的QQ机器人账号: ", "未设置");
		initProp("mode","设置机器人的日志等级(QUIET, NORMAL, NONE): ", "未设置");
		initProp("miraiApiHttpVerifyKey","设置 mirai-api-http 的连接验证密钥 (请确保您在安装此插件后已修改 mirai-api-http 的配置文件): ",
				"未设置");

	}

	public static void initProp(String value, String RequireMessage, String ErrorMessage) {
		if (prop.get(value) == null) {
			System.out.print(RequireMessage);
			String v;
			if ((v = console.next()) == null) {
				System.err.println("Failed to initialize property: \n" + ErrorMessage);
				System.exit(1);
			}
			prop.put(value,v);
			refreshProp();
		}
	}

	public static void refreshProp() {
		try (FileOutputStream out = new FileOutputStream(propFile, false); FileInputStream in = new FileInputStream(propFile)) {
			prop.store(out,"Robot Properties");
			prop.clear();
			prop.load(in);
		} catch (IOException ignored) {
		}
	}

	public static boolean putProp(String key, String value) {
		prop.put(key, value);
		return prop.get(key).equals(value);
	}

	public static String getProp(String key) {
		String value = null;
		if (prop.get(key) != null) {
			value = (String) prop.get(key);
		}
		return value;
	}

	public static JsonArray getPropArray(String key) {
		String json = Data.getProp(key);
		JsonArray jsonArray;
		if (json == null) {
			jsonArray = new JsonArray();
		} else {
			jsonArray = (JsonArray) JsonParser.parseString(json);
		}
		return jsonArray;
	}

	public static void propArrayAppendNumber(String key, Number value) {
		JsonArray jsonArray = getPropArray(key);
		jsonArray.add(value);
		Data.putProp(key, jsonArray.toString());
	}

	public static Number propArrayRemoveNumber(String key, Number value) {
		JsonArray jsonArray = getPropArray(key);
		for (JsonElement element : jsonArray) {
			if (element.getAsNumber().toString().equals(value.toString())) {
				jsonArray.remove(element);
				Data.putProp(key, jsonArray.toString());
				return value;
			}
		}
		return null;
	}

	public static boolean propArrayContainsNumber(String key, Number value) {
		JsonArray jsonArray = getPropArray(key);
		for (JsonElement element : jsonArray) {
			if (element.getAsNumber().toString().equals(value.toString())) {
				return true;
			}
		}
		return false;
	}

	public static byte[] getFileBytes(String path) {
		try {
			return Files.readAllBytes(Paths.get(path));
		} catch (IOException ignored) {
			return null;
		}
	}

	public static byte[] getResourceBytes(String path) {
		if (path.startsWith("/")) path = path.substring(1);
		try {
			return Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(path).toURI()));
		} catch (IOException | URISyntaxException ignored) {
			return null;
		}
	}

	public static String getFileBase64(String path) {
		return Base64.getEncoder().encodeToString(getFileBytes(path));
	}

	public static String getByteArrayBase64(byte[] array) {
		return Base64.getEncoder().encodeToString(array);
	}

    public static FileOutputStream openFileOutputStream(String path, boolean append) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path, append);
        } catch (FileNotFoundException ignored) {
		}
        return out;
    }

	public static Properties loadProp(File file) {
		Properties prop = new Properties();
		loadToProp(prop, file);
		return prop;
	}

	public static boolean loadToProp(Properties prop, File file) {
		try (FileInputStream stream = new FileInputStream(propFile)) {
			prop.load(stream);
		} catch (IOException ignored) {
			return false;
		}
		return true;
	}

	public static String processImage(ImageProcessor processor) throws IOException {
		BufferedImage image = processor.process();
		if (image == null) return null;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
	    ImageIO.write(image, "png", stream);
		stream.close();
	    return getByteArrayBase64(stream.toByteArray());
	}

}
