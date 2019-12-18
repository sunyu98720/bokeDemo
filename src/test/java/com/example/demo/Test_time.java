package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.sign.SignUtils;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.*;
import okhttp3.RequestBody;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * Author
 *
 * @param
 * @return
 * @time
 */

public class Test_time {

    ////    private ApplicationContext applicationContext = SpringContextHolder.getApplicationContext();
//    ApplicationContext context = new FileSystemXmlApplicationContext("E:/Spring/applicationContext.xml");
//    private StudentMapper studentMapper = applicationContext.getBean(StudentMapper.class);
    @Test
    public void time() {
        String a = new String("test");
//        a = "test";
        String b = "test";
        System.out.println(a == b);
        System.out.println(a.equals(b));

//        System.out.println(studentMapper);
        SignUtils signUtils = new SignUtils();
        Map<String, String> map = new TreeMap<String, String>();
        map.put("Timestamp", signUtils.getTimestamp());
        map.put("Str", signUtils.getNonceStr(16));
        map.put("token", "ec2b05b0-005e-4928-9721-f45b5e4c785b");
        String str = "U2FsdGVkX1/0roH2HRyRRpnurCzyQTSRmcF4F05eUWE=";
        String str1 = signUtils.createSign(map, str);
        System.out.println(str1);
        String encodeStr = DigestUtils.md5DigestAsHex(str1.getBytes());
        System.out.println(encodeStr);
    }

    @Test
    public void arraylistTest() throws NoSuchFieldException, IllegalAccessException {
        List<String> testarray = new ArrayList<>();
        List<String> testarray1 = new LinkedList<>();

        testarray.add("a");
        testarray.add("a1");
        testarray.add("a2");
        testarray.add("a3");
        testarray.add("a4");
        testarray.add("a5");
        testarray.add("a6");
        testarray.add("a7");
        testarray.add("a8");
//        testarray.add("a9");
//        testarray.add("a10");
//        testarray.add("a11");
//        testarray.add("a12");
//        testarray.add("a13");
//        testarray.add("a13");

        Field f = ArrayList.class.getDeclaredField("elementData");
        f.setAccessible(true);
        Object[] elementData = (Object[]) f.get(testarray);
        System.out.println(elementData.length);
        System.out.println(testarray.size());


    }

    @Test
    public void hashaaa(){
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        Random random = new Random();
        StringBuilder randomStr = new StringBuilder();
        for (int i = 0; i < 36; i++) {
            int numbers = random.nextInt(62);
            randomStr.append(str.charAt(numbers));
        }
        System.out.println(randomStr.toString());
    }

    @Test
    public void test(){
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("account", "admin10");
        jsonObject.put("password", "123");

        RequestBody body = RequestBody.create(mediaType, jsonObject.toString());
        Request request = new Request.Builder()
                .url("http://localhost:8086/login")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println(string);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    interface A{
        public void a();
    }
    interface B{
        public void b();
    }

    @Test
    public void test111(){
        ClassLoader classLoader = this.getClass().getClassLoader();
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("asdasda");
                return null;
            }
        };
        Object obj = Proxy.newProxyInstance(classLoader, new Class[]{A.class,B.class},invocationHandler);
        A a = (A) obj;
        B b = (B) obj;

        a.a();
        b.b();
    }
}