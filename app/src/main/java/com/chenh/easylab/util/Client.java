package com.chenh.easylab.util;

import android.widget.ListView;

import com.chenh.easylab.vo.DeskVO;
import com.chenh.easylab.vo.LabVO;
import com.chenh.easylab.vo.UserVO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenh on 2016/6/10.
 */
public class Client {
    public static final String IP_ADDR = "115.159.220.246";//服务器地址  这里要改成服务器的ip
    public static final int PORT = 12345;//服务器端口号
    private Socket socket;
    private static Client client;
    public static Client getInstance(){
        if (client==null)
            client=new Client();
        return client;
    }

    private Client(){
        socket = null;
        //创建一个流套接字并将其连接到指定主机上的指定端口号
        try {
            socket = new Socket(IP_ADDR, PORT);
            Listener listener=new Listener();
            Thread t=new Thread(listener);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送数据
     * @param js 数据包
     * @return 是否成功发送
     */
    public boolean sendRequest(JSONObject js){
        String jsonString = "";
        jsonString = js.toString();
        byte[] jsonByte = jsonString.getBytes();
        DataOutputStream outputStream = null;
        try {
            outputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("发的数据长度为:"+jsonByte.length);
            outputStream.write(jsonByte);
            outputStream.flush();
            System.out.println("传输数据完毕");
            socket.shutdownOutput();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void receiveData(JSONObject js){
        boolean success= false;
        ServerBackData serverBackData=new ServerBackData();
        try {
            success = (((String) js.get("isSuccess")).equals("success"));
            if (success){
                serverBackData.setResultState(true);
                int rp=Integer.parseInt(js.getString("rp"));
                switch (rp){
                    case 0://返回类型为booleean
                        break;
                    case 1://返回类型为user
                        LocalUser.writeInstance(js);
                        break;
                    case 2://返回类型为Appointment

                        break;
                    case 3:

                        break;
                    case 21://响应是否成功
                        serverBackData.setMessage(js.getString("message"));
                        break;
                    case 2101://电流表示数
                        LocalAmperemeters.writeInstance(js);
                        break;
                    case 3101://电源状态
                        LocalPowers.writeInstance(js);
                        break;
                    case 41003://返回当前所有实验室
                        LocalLabs.writeInstance(js);
                        break;
                    case 51003://返回一张desk
                        LocalDesks.writeInstance(js);
                        break;

                }
            }else {
                serverBackData.setResultState(false);
                int rp=Integer.parseInt(js.getString("rp"));
                switch (rp){
                    case 21://响应是否成功
                        serverBackData.setMessage(js.getString("message"));
                        break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param
     * @return
     */
    public static ServerBackData register(JSONObject json){
        ServerBackData serverBackData=new ServerBackData();
        while (true) {
            Socket socket = null;
            try {
                //创建一个流套接字并将其连接到指定主机上的指定端口号
                socket = new Socket(IP_ADDR, PORT);
                System.out.println("连接已经建立");
                //向服务器端发送数据
               /* Map<String, String> map = new HashMap<String, String>();

                for (int i=0;i<data.length;i++){
                    map.put(data[i][0],data[i][1]);
                }
                //将json转化为String类型
                JSONObject json = new JSONObject(map);*/
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
                        case 1://返回类型为user
                            UserVO userVO=new UserVO(js);
                            serverBackData.setObject(userVO);
                            break;
                        case 2://返回类型为Appointment

                            break;
                        case 3:

                            break;
                        case 21://响应是否成功
                            serverBackData.setMessage(js.getString("message"));
                            break;
                        case 2101://电流表示数
                            serverBackData.setObject(new Integer(js.getInt("value")));
                            break;
                        case 3101://电源状态
                            serverBackData.setObject(new Boolean(js.getBoolean("state")));
                            break;

                        case 41003://返回当前所有实验室-
                            ArrayList<LabVO> labs=new ArrayList<>();
                            int num=js.getInt("num");
                            for (int i=0;i<num;i++){
                                labs.add(new LabVO(js.getJSONObject("item"+i)));
                            }
                            serverBackData.setObject(labs);
                            break;
                        case 51003://返回一张desk
                            DeskVO desk=new DeskVO(js);
                            serverBackData.setObject(desk);
                            break;

                    }
                }else {
                    serverBackData.setResultState(false);
                    int rp=Integer.parseInt(js.getString("rp"));
                    switch (rp){
                        case 21://响应是否成功
                            serverBackData.setMessage(js.getString("message"));
                            break;

                    }
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

    /**
     * 监听服务器端是否有消息返回。如果有、就启动receiveData方法解析数据
     */
    class Listener implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    DataInputStream inputStream = null;
                    String strInputstream ="";
                    inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    strInputstream=inputStream.readUTF();
                    JSONObject js = new JSONObject(strInputstream);
                    receiveData(js);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
