package com.chenh.easylab.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chenh.easylab.R;
import com.chenh.easylab.util.Client;
import com.chenh.easylab.util.LocalBoolean;
import com.chenh.easylab.util.LocalLabs;
import com.chenh.easylab.vo.LabVO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by chenh on 2016/7/4.
 */
public class AddLabDialog extends DialogFragment {

    private EditText mInput;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_lab_add_lab,null);
        mInput= (EditText) v.findViewById(R.id.lab_name_input);


        return new AlertDialog.Builder(getActivity()).setView(v).setTitle("添加实验室").setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String input=mInput.getText().toString();
                if (input==null){
                    Toast.makeText(getActivity(),"输入无效、新建失败",Toast.LENGTH_SHORT);
                    return;
                }
                LabVO labVO=new LabVO(input);

                LocalBoolean.getInstance().clear();
                JSONObject jsonObject=labVO.toJsonObject();
                try {
                    jsonObject.put("op","40001");
                    boolean success = Client.getInstance().sendRequest(jsonObject);

                     if (!success){
                         Toast.makeText(getActivity(),"网络无法连接，请检查网络后重试",Toast.LENGTH_SHORT);
                         return;
                     }else {
                         for (int i=0;i<30;i++){
                             if(LocalBoolean.getInstance().getSuccess()!=null){
                                 break;
                             }
                             Thread.sleep(100);
                         }
                         if (LocalBoolean.getInstance().getSuccess()==null){
                             Toast.makeText(getActivity(),"服务器无响应，请稍后重试",Toast.LENGTH_SHORT);
                             return;
                         }else {
                             if (LocalBoolean.getInstance().getSuccess())
                                 LocalLabs.getInstance().getLabs().add(labVO);
                             else
                                 Toast.makeText(getActivity(),"已经存在该名的实验室，请重试",Toast.LENGTH_SHORT);
                         }
                     }


                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }) .create();

    }

    public static AddLabDialog newInstance(){
        AddLabDialog addLabDialog=new AddLabDialog();

        return addLabDialog;
    }
}
