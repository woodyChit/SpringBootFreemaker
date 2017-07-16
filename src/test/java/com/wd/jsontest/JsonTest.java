package com.wd.jsontest;

import com.wd.entity.json.City;
import com.wd.entity.json.Town;
import com.wd.util.JsonUtil;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by woody on 2017/7/16.
 */
public class JsonTest {


    public void testSerializer(){
        Town t1 = new Town(10,"tomt","verygood");
        Town t2 = new Town(12,"jerry","not so bad");
        Town t3 = new Town(11,"ket","unknown words");
        City city = new City();
        city.setId(1);
        city.setName("双利");
        city.setStartDate(new Date());
        city.setTowns(Arrays.asList(t1,t2,t3));
        Map<String,Town> townMap = new HashMap<>();
        townMap.put("柠檬只想",t1);
        townMap.put("弱智乡",t3);
        city.setTitleMap(townMap);
        String json = JsonUtil.convertToJson(city);
        System.out.println("length>>>>>"+json.length());
        File f = new File("F:/json.txt");

        try (OutputStream outputStream = new FileOutputStream(f)) {
            outputStream.write(json.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(json);
    }

    public void  deserializ(){
        File f = new File("F:/json.txt");
        try (InputStream inputStream = new FileInputStream(f)) {
            StringBuilder sb = new StringBuilder();
            byte[] bytes = new byte[1024];
            int r=-1;
            while (( r =inputStream.read(bytes,0,1024))!=-1){
                sb.append(new String(bytes,0,r));
            }
            System.out.println(sb.toString());
            System.out.println("length>>>>>"+sb.toString().length());

            City c = JsonUtil.convertToObject(sb.toString(),City.class);
            System.out.println(c);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
