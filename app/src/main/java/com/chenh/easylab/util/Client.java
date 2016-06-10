package com.chenh.easylab.util;

import com.chenh.easylab.vo.UserVO;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenh on 2016/6/10.
 */
public class Client {
    public static final String IP_ADDR = "115.159.220.246";//服务器地址  这里要改成服务器的ip
    public static final int PORT = 12345;//服务器端口号

    /**
     *
     * @param data 一个key与value对应的n*2的数组
     * @param opNum 操作识别码
     * @return
     */
    public static ServerBackData register(String[][] data){
        ServerBackData serverBackData=new ServerBackData();
        while (true) {
            Socket socket = null;
            try {
                //创建一个流套接字并将其连接到指定主机上的指定端口号
                socket = new Socket(IP_ADDR, PORT);
                System.out.println("连接已经建立");
                //向服务器端发送数据
                Map<String, String> map = new HashMap<String, String>();

                for (int i=0;i<data.length;i++){
                    map.put(data[i][0],data[i][1]);
                }
                //将json转化为String类型
                JSONObject json = new JSONObject(map);
                String jsonString = "";
                jsonString = json.toString();
                //将String转化为byte[]
                //byte[] jsonByte = new byte[jsonString.length()+1];
                byte[] jsonByte = jsonString.getBytes();
                DataOutputStream outputStream = null;
                outputStream = new DataOutputStream(socket.getOutputStream());
                System.out.println("发的数据长度为:"+jsonByte.length);
                outputStream.write(jsonByte);
                outputStream.flush();
                System.out.println("传输数据完毕");
                socket.shutdownOutput();

                //读取服务器端数据
                DataInputStream inputStream = null;
                String strInputstream ="";
                inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                strInputstream=inputStream.readUTF();
                JSONObject js = new JSONObject(strInputstream);

                boolean success=(((String) js.get("isSuccess")).equals("success"));
                if (success){
                    serverBackData.setResultState(true);
                    int rp=Integer.parseInt(js.getString("rp"));
                    switch (rp){
                        case 0://返回类型为booleean
                            break;
                        case 1:
                            String username=js.getString("username");
                            String password=js.getString("password");
                            String name=js.getString("name");
                            String sid=js.getString("sid");
                            int identify=Integer.parseInt(js.getString("identify"));
                            UserVO userVO=new UserVO(username,password,sid,name,identify);
                            serverBackData.setObject(userVO);
                            break;
                        case 2:
                            break;
                    }
                }else {
                    serverBackData.setResultState(false);
                }

                // 如接收到 "OK" 则断开连接
                if (js != null) {
                    System.out.println("客户端将关闭连接");
                    Thread.sleep(500);
                    break;
                }

            } catch (Exception e) {
                System.out.println("客户端异常:" + e.getMessage());
                e.printStackTrace();
                break;
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        socket = null;
                        System.out.println("客户端 finally 异常:" + e.getMessage());
                    }
                }
            }
        }
        return serverBackData;
    }
}
