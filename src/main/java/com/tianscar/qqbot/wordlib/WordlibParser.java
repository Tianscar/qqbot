package com.tianscar.qqbot.wordlib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class WordlibParser {

    public static LinkedHashMap<String, String> parse(InputStream stream, String robotName) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        LinkedHashMap<String, String> wordlib = new LinkedHashMap<>();
        LinkedList<String> tmpLines = new LinkedList<>();
        try {
            while (reader.ready()) {
                String line = reader.readLine().replaceAll("%robotname%", robotName);
                tmpLines.add(line);
                if (line.equals("")) {
                    int index = tmpLines.indexOf(line);
                    if (index < 2) {
                        tmpLines.clear();
                        continue;
                    }
                    String key = tmpLines.get(index-2);
                    String value = tmpLines.get(index-1).replaceAll("嘻嘻", "唔唔").replaceAll("呵呵", "哈哈");
                    if (key.contains("[\"face\"")) {
                        tmpLines.clear();
                        continue;
                    }
                    wordlib.put(key, value);
                    tmpLines.clear();
                }
            }
            reader.close();
            stream.close();
            return wordlib;
        }
        catch (IOException ignored) {
        }
        return null;
    }

}
