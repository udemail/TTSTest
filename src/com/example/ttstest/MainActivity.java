package com.example.ttstest;

import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



/*public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}*/
 //import java.util.Locale; 
  
 import android.app.Activity; 
 import android.os.Bundle; 
 import android.speech.tts.TextToSpeech; 
 import android.text.Editable; 
 import android.text.TextWatcher; 
 import android.view.View; 
 import android.widget.Button; 
 import android.widget.CheckBox; 
 import android.widget.EditText; 
import android.widget.Toast; 
  
  
 public class MainActivity extends Activity  
 { 
     private EditText mEditText = null; 
     private Button readButton = null; 
     private Button saveButton = null; 
     private CheckBox mCheckBox = null; 
     private TextToSpeech mTextToSpeech = null; 
      
     /** Called when the activity is first created. */ 
     @Override 
     public void onCreate(Bundle savedInstanceState)  
     { 
         super.onCreate(savedInstanceState); 
         setContentView(R.layout.activity_main); 
          
         mEditText = (EditText)this.findViewById(R.id.edittext); 
         readButton = (Button)this.findViewById(R.id.rbutton); 
         saveButton = (Button)this.findViewById(R.id.sbutton); 
         mCheckBox = (CheckBox)this.findViewById(R.id.checkbox); 
         //ʵ������ʼ��TTS���� 
         mTextToSpeech = new TextToSpeech(this,new TextToSpeech.OnInitListener() 
         { 
  
             @Override 
             public void onInit(int status)  
             { 
                 // TODO Auto-generated method stub 
                 if(status == TextToSpeech.SUCCESS) 
                 { 
                     //�����ʶ�����  
                     int supported = mTextToSpeech.setLanguage(Locale.US); 
                     if((supported != TextToSpeech.LANG_AVAILABLE)&&(supported != TextToSpeech.LANG_COUNTRY_AVAILABLE)) 
                     { 
                         displayToast("��֧�ֵ�ǰ���ԣ�"); 
                     } 
                 } 
             } 
              
         });      
         //�ʶ���ť���� 
         readButton.setOnClickListener(new View.OnClickListener()  
         { 
              
             @Override 
             public void onClick(View v)  
             { 
                 // TODO Auto-generated method stub 
                 //�ʶ�EditText������� 
                 mTextToSpeech.speak(mEditText.getText().toString(), TextToSpeech.QUEUE_FLUSH, null); 
             } 
         }); 
         //���水ť���� 
         saveButton.setOnClickListener(new View.OnClickListener()  
         { 
              
             @Override 
             public void onClick(View v)  
             { 
                 // TODO Auto-generated method stub 
                  
                 //��EditText������ݱ���Ϊ�����ļ� 
                 int r = mTextToSpeech.synthesizeToFile(mEditText.getText().toString(), null, "/sdcard/speak.wav"); 
                 if(r == TextToSpeech.SUCCESS) 
                     displayToast("����ɹ���");                 
             } 
         }); 
         //EditText���ݱ仯���� 
         mEditText.addTextChangedListener(mTextWatcher); 
          
     } 
      
      
     private TextWatcher mTextWatcher = new TextWatcher() 
     { 
  
         @Override 
         public void afterTextChanged(Editable s)  
         { 
             // TODO Auto-generated method stub 
             //����Ǳ�д�߶� 
             if(mCheckBox.isChecked()&&(s.length()!=0)) 
             { 
                //���EditText���������� 
                 String t = s.toString();         
                 mTextToSpeech.speak(t.substring(s.length()-1), TextToSpeech.QUEUE_FLUSH, null); 
             } 
         } 
  
         @Override 
         public void beforeTextChanged(CharSequence s, int start, int count, 
                 int after)  
         { 
             // TODO Auto-generated method stub 
              
         } 
  
         @Override 
         public void onTextChanged(CharSequence s, int start, int before, 
                 int count)  
         { 
             // TODO Auto-generated method stub 
              
         } 
     };  
        
     //��ʾToast���� 
     private void displayToast(String s) 
     { 
         Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show(); 
     } 
      
      
     @Override 
     public void onDestroy() 
     { 
	      super.onDestroy(); 
          
         if(mTextToSpeech != null) 
             mTextToSpeech.shutdown();//�ر�TTS 
     } 
      
 } 

