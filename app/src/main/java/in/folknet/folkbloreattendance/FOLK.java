package in.folknet.folkbloreattendance;import android.app.ProgressDialog;import android.content.Intent;import android.content.pm.PackageManager;import android.os.Bundle;import android.support.v4.app.ActivityCompat;import android.support.v4.content.ContextCompat;import android.support.v7.app.AppCompatActivity;import android.view.View;import android.widget.AdapterView;import android.widget.Button;import android.widget.EditText;import android.widget.RadioButton;import android.widget.RadioGroup;import android.widget.Spinner;import android.widget.TextView;import android.widget.Toast;import com.google.firebase.database.DataSnapshot;import com.google.firebase.database.DatabaseError;import com.google.firebase.database.DatabaseReference;import com.google.firebase.database.FirebaseDatabase;import com.google.firebase.database.ValueEventListener;import java.util.Calendar;public class FOLK extends AppCompatActivity {    Button scanbtn;    String co,year_fb;    TextView result;    public static final int REQUEST_CODE = 100;    public static final int PERMISSION_REQUEST = 200;    private RadioGroup radioSexGroup;    private RadioButton radioSexButton;    int flag = 0;    Button btn;    EditText ename,eid, jp;    String val,fin;    ProgressDialog pd;    String id,name,fg, mjp, sarea, fl, mob;    Spinner spinner_area;    String selectedItemText;    RadioGroup rg;    TextView txt;    int i;    String ws;    RadioButton rb;    DatabaseReference database,mid,mfg,mcount,mChack,mfl, mmob;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_folk);//        if (isOnline())//        {//            pd.hide();            jp = (EditText)findViewById(R.id.etjp);            btn = (Button) findViewById(R.id.btn);        spinner_area = (Spinner) findViewById(R.id.spinner_area);        rg = (RadioGroup)findViewById(R.id.radioGroup);            eid = (EditText)findViewById(R.id.et);            if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, PERMISSION_REQUEST);            }        spinner_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {            @Override            public void onItemSelected(AdapterView<?> parent, View view, int position, long lid) {                selectedItemText = spinner_area.getItemAtPosition(position).toString();                // Notify the selected item text                Toast.makeText                        (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)                        .show();            }            @Override            public void onNothingSelected(AdapterView<?> parent) {            }        });            btn.setOnClickListener(new View.OnClickListener() {                @Override                public void onClick(View v)                {                    id = eid.getText().toString();                    mjp = jp.getText().toString();                    if ((id.length() < 11 && id.length() > 6) || (id.length() < 6)){                        Toast.makeText(getApplicationContext(), "Enter Full FOLK ID!", Toast.LENGTH_SHORT).show();                    }else if(mjp.isEmpty()){                        Toast.makeText(getApplicationContext(), "You have not entered Japa Rounds!", Toast.LENGTH_SHORT).show();                    }else if(selectedItemText.equals("Not Selected")){                        Toast.makeText(getApplicationContext(), "You have not selected any Area near to your Residence!", Toast.LENGTH_SHORT).show();                    }                    else                    {                        int selectedId = 0;                        selectedId = rg.getCheckedRadioButtonId();                        rb = (RadioButton) findViewById(selectedId);                        if (selectedId == -1)                            Toast.makeText(getApplicationContext(),"Select Workshop!", Toast.LENGTH_SHORT).show();                        else{                            ws = (String) rb.getText();                            pd = new ProgressDialog(FOLK.this);                            pd.setTitle("Please Wait...");                            pd.setMessage("Make Sure Your Internet Connection Is On.");                            pd.show();                            pd.setCancelable(false);                            val = "";                            mChack = FirebaseDatabase.getInstance().getReference().child("A");                            database = FirebaseDatabase.getInstance().getReference().child("FOLK")                                    .child(id).child("name");                            mid = FirebaseDatabase.getInstance().getReference().child("FOLK")                                    .child(id).child("id");                            mfg = FirebaseDatabase.getInstance().getReference().child("FOLK")                                    .child(id).child("fg");                            mfl = FirebaseDatabase.getInstance().getReference().child("FOLK")                                    .child(id).child("fl");                            mmob = FirebaseDatabase.getInstance().getReference().child("FOLK")                                    .child(id).child("mob");                            mcount = FirebaseDatabase.getInstance().getReference().child("Counter").child("CountE");                            ValueEventListener postListener = new ValueEventListener() {                                @Override                                public void onDataChange(DataSnapshot dataSnapshot) {                                    // Get Post object and use the values to update the UI                                    id = dataSnapshot.getValue(String.class);                                }                                @Override                                public void onCancelled(DatabaseError databaseError) {}                            };                            mid.addValueEventListener(postListener);                            ValueEventListener postListener6 = new ValueEventListener() {                                @Override                                public void onDataChange(DataSnapshot dataSnapshot) {                                    // Get Post object and use the values to update the UI                                    fl = dataSnapshot.getValue(String.class);                                }                                @Override                                public void onCancelled(DatabaseError databaseError) {}                            };                            mfl.addValueEventListener(postListener6);                            ValueEventListener postListener7 = new ValueEventListener() {                                @Override                                public void onDataChange(DataSnapshot dataSnapshot) {                                    // Get Post object and use the values to update the UI                                    mob = dataSnapshot.getValue(String.class);                                }                                @Override                                public void onCancelled(DatabaseError databaseError) {}                            };                            mmob.addValueEventListener(postListener7);                            ValueEventListener postListener4 = new ValueEventListener() {                                @Override                                public void onDataChange(DataSnapshot dataSnapshot) {                                    // Get Post object and use the values to update the UI                                    i = dataSnapshot.getValue(int.class);                                }                                @Override                                public void onCancelled(DatabaseError databaseError) {}                            };                            mcount.addValueEventListener(postListener4);//                        ValueEventListener postListener5 = new ValueEventListener() {//                            @Override//                            public void onDataChange(DataSnapshot dataSnapshot) {//                                // Get Post object and use the values to update the UI////                                if (dataSnapshot.hasChild(id)) {//                                    flag = 0;//                                }//                                else//                                    flag = 1;////                            }//                            @Override//                            public void onCancelled(DatabaseError databaseError) {}//                        };//                        mChack.addValueEventListener(postListener5);                            ValueEventListener postListener2 = new ValueEventListener() {                                @Override                                public void onDataChange(DataSnapshot dataSnapshot) {                                    // Get Post object and use the values to update the UI                                    name = dataSnapshot.getValue(String.class);                                }                                @Override                                public void onCancelled(DatabaseError databaseError) {}                            };                            database.addValueEventListener(postListener2);                            ValueEventListener postListener1 = new ValueEventListener() {                                @Override                                public void onDataChange(DataSnapshot dataSnapshot) {                                    // Get Post object and use the values to update the UI                                    fg = dataSnapshot.getValue(String.class);//                        if (flag == 0){//                            Toast.makeText(getApplicationContext(), "Coupon is already taken!", Toast.LENGTH_SHORT).show();//                            pd.hide();//////                        }                                    if (fg == null || id == null || name == null) {                                        Toast.makeText(getApplicationContext(), "Try Again!", Toast.LENGTH_SHORT).show();//                            eid.setText(id);//                            Intent intent = getIntent();//                            finish();//                            startActivity(intent);//                            fg=null;//                            id=null;//                            name=null;                                        pd.hide();                                    } else{                                        Calendar calendar = Calendar.getInstance();                                        int hours = calendar.get(Calendar.HOUR_OF_DAY);                                        int minutes = calendar.get(Calendar.MINUTE);                                        int date = calendar.get(Calendar.DAY_OF_MONTH);                                        int month = calendar.get(Calendar.MONTH);                                        int year = calendar.get(Calendar.YEAR);                                        month = month +1;                                        String time = date + "/" + month + "/" + year + "|"+hours+":"+minutes;                                        String c = color();                                        String WOS = null;                                        switch (ws){                                            case "SELF EMPOWERMENT" :                                                ws = "SELF EMP.";                                                break;                                            case "HAPPINESS WORKSHOP" :                                                ws = "HAPPINESS WS";                                                break;                                            case "SPECIAL SESSION":                                                ws = "SP. SESSION";                                                break;                                            case "SERVICE":                                                ws = "SERVICE";                                                break;                                            default:                                        }                                        int d = year(id);                                        if (d == 17){                                            year_fb = "2";                                        }                                        else if(d == 18){                                            year_fb = "3";                                        }                                        else{                                            year_fb = "1";                                        }                                        Toast.makeText(getApplicationContext(),  year_fb, Toast.LENGTH_SHORT).show();                                        String Venuee = venue(fg);                                        fin = "    FOLK BANGALORE\n **********************\nName : " + name + "\nFOLK ID : " + id + "\nFOLK Guide : " + fg + "\nTIME : "+date+"/"+ month+"|"+ hours + ":" + minutes+ "\nFOLK Level : "+fl + "\nMeeting : "+Venuee + "\nCOUPON COLOR : "+c+"\n **********************\n\n\n\n.";                                        Intent intent = new Intent(FOLK.this, MainActivity.class);                                        intent.putExtra("message", fin);                                        intent.putExtra("name",name);                                        intent.putExtra("mjp",mjp);                                        intent.putExtra("fg",fg);                                        intent.putExtra("fl",fl);                                        intent.putExtra("mob",mob);                                        intent.putExtra("time",time);                                        intent.putExtra("fid",id);                                        intent.putExtra("ws",ws);                                        intent.putExtra("counter",i);                                        intent.putExtra("year_fb",year_fb);                                        intent.putExtra("selected_area", selectedItemText);                                        String a,b;                                        a = time();                                        b = sort(fg);                                        intent.putExtra("timeNumber",a);                                        intent.putExtra("fgNumber",b);                                        pd.hide();                                        eid.setText("");                                        jp.setText("");                                        startActivity(intent);                                    }                                }                                @Override                                public void onCancelled(DatabaseError databaseError) {}                            };                            mfg.addValueEventListener(postListener1);//////                name = ename.getText().toString();////                if (TextUtils.isEmpty(id)){//                    Toast.makeText(getApplicationContext(), "Enter FOLK ID!", Toast.LENGTH_SHORT).show();//                }////////                else if (TextUtils.isEmpty(name)){//                    Toast.makeText(getApplicationContext(), "Enter Name!", Toast.LENGTH_SHORT).show();////                }//                else {//                        int selectedId = 0;//                        selectedId = rg.getCheckedRadioButtonId();//                        rb = (RadioButton) findViewById(selectedId);//                        if (selectedId == -1)//                            Toast.makeText(getApplicationContext(), "Select FOLK Guide!", Toast.LENGTH_SHORT).show();//                        else {//                            Calendar calendar = Calendar.getInstance();//                            int hours = calendar.get(Calendar.HOUR_OF_DAY);//                            int minutes = calendar.get(Calendar.MINUTE);//                            int seconds = calendar.get(Calendar.SECOND);////                            if (hours == 16 && minutes <= 45){//                                Toast.makeText(getApplicationContext(), "GREEN", Toast.LENGTH_SHORT).show();////                            }//                            else if ((hours == 16 && minutes <= 59)||(hours == 17 && minutes == 00)){//                                Toast.makeText(getApplicationContext(), "PINK", Toast.LENGTH_SHORT).show();////                            }//                            else if ((hours == 17 && minutes <= 15)){//                                Toast.makeText(getApplicationContext(), "WHITE", Toast.LENGTH_SHORT).show();////                            }//                            else if (hours < 16 || hours > 17){//                                Toast.makeText(getApplicationContext(), "TRY AFTER 4:00 PM!", Toast.LENGTH_SHORT).show();////                            }//                            else if (hours == 17 && minutes >15){//                                Toast.makeText(getApplicationContext(), "SORRY, YOU'VE EXCEEDED TIME LIMIT!", Toast.LENGTH_SHORT).show();////                            }//                            else {}//                        }////                    }                        }                    }                }            });//        }//        else//        {//            pd.hide();//            Intent intent = new Intent(FOLK.this, InternetConnection.class);//            startActivity(intent);////            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();//        }    }        String color(){        String col = null;        Calendar calendar = Calendar.getInstance();                            int hours = calendar.get(Calendar.HOUR_OF_DAY);                            int minutes = calendar.get(Calendar.MINUTE);                            int seconds = calendar.get(Calendar.SECOND);                            if ((hours == 15) || (hours == 16 && minutes <= 40)){                                col = "GREEN";                            }                            else if ((hours == 16 && minutes > 40) && (hours == 16 && minutes <= 50)){                                col = "PINK";                            }                            else if ((hours == 16 && minutes > 50) && (hours == 16 && minutes <= 59)){                                col = "YELLOW";                            }                            else {                                col = "WHITE";                            }        return col;    }    String time(){        String co = null;        Calendar calendar = Calendar.getInstance();        int hours = calendar.get(Calendar.HOUR_OF_DAY);        int minutes = calendar.get(Calendar.MINUTE);        int seconds = calendar.get(Calendar.SECOND);        if ((hours == 15) || (hours == 16 && minutes <= 40)){            co = "1";        }        else if ((hours == 16 && minutes > 40) && (hours == 16 && minutes <= 50)){            co = "2";        }        else if ((hours == 16 && minutes > 50) && (hours == 16 && minutes <= 59)){            co = "3";        }        else {            co = "4";        }        return co;    }    String sort(String fguide){        String Num;        String temp = null;        switch(fguide){            case "ANBD" :                Num = "1";                break;            case "ATSD" :                Num = "20";                break;            case "CTCD" :                Num = "2";                break;            case "HMCD" :                Num = "3";                break;            case "INPD" :                Num = "4";                break;            case "JVMD" :                Num = "5";                break;            case "KKSD" :                Num = "6";                break;            case "KMPD" :                Num = "7";                break;            case "LMND" :                Num = "8";                break;            case "RMRD" :                Num = "9";                break;            case "SRID" :                Num = "10";                break;            case "SRRD" :                Num = "11";                break;            case "VGND" :                Num = "12";                break;            case "VJGD" :                Num = "13";                break;            case "VNTD" :                Num = "14";                break;            case "KRMD" :                Num = "16";                break;                case "KBRD" :                Num = "9";                break;            case "HVGD" :                Num = "17";                break;            default:                Num = "15";                break;        }        return Num;    }    int year(String id){        char a = id.charAt(2);        char b = id.charAt(3);        String temp = String.valueOf(a);        String temp2 = String.valueOf(b);        temp = temp.concat(temp2);        int result = Integer.parseInt(temp);//        Toast.makeText(getApplicationContext(), result , Toast.LENGTH_SHORT).show();        return result;    }    String venue(String fguide){        String Num;        switch(fguide){            case "ANBD" :                Num = "Gita Counter Cabin";                break;            case "ATSD" :                Num = "2 floor above Radha Krishna Temple";                break;            case "CTCD" :                Num = "14 Mtrs East";                break;            case "HMCD" :                Num = "ENGLISH LH";                break;            case "INPD" :                Num = "Samskara Hall";                break;            case "JVMD" :                Num = "FOLK Seminar Hall";                break;            case "KKSD" :                Num = "MTH";                break;            case "KMPD" :                Num = "ELH";                break;            case "LMND" :                Num = "MVT";                break;            case "RMRD" :                Num = "14 Mtrs West";                break;            case "SRID" :                Num = "SP Office";                break;            case "SRRD" :                Num = "3Mr. Cabin";                break;            case "VGND" :                Num = "14m SW";                break;            case "VJGD" :                Num = "AV Hall";                break;            case "VNTD" :                Num = "14 Mtrs North";                break;            case "KRMD" :                Num = "In His CABIN";                break;            case "HVGD" :                Num = "3 Mtrs Board Room";                break;            case "KBRD" :                Num = "";                break;            default:                Num = "";                break;        }        return Num;    }//    public boolean isOnline() {//        pd = new ProgressDialog(FOLK.this);//        pd.setTitle("Please Wait...");//        pd.setMessage("Make Sure Your Internet Connection Is On.");//        pd.show();//        pd.setCancelable(false);//        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);//        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();////        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){//            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();//            return false;//        }//        return true;//    }}