package com.example.beauticianapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import static com.example.beauticianapplication.Utils.addServiceLocationJson;
import static com.example.beauticianapplication.Utils.allServicesParse;
import static com.example.beauticianapplication.Utils.cityParser;
import static com.example.beauticianapplication.Utils.deleteServiceLocationJson;
import static com.example.beauticianapplication.Utils.districtParser;
import static com.example.beauticianapplication.Utils.getCityListJson;
import static com.example.beauticianapplication.Utils.getDistrictListJson;
import static com.example.beauticianapplication.Utils.parseMyHour;
import static com.example.beauticianapplication.Utils.parseMyJson;
import static com.example.beauticianapplication.Utils.parseMyJsonList;
import static com.example.beauticianapplication.Utils.removeFirstLast;
import static com.example.beauticianapplication.Utils.serviceAddJson;
import static com.example.beauticianapplication.Utils.serviceDeleteJson;
import static com.example.beauticianapplication.Utils.servicesParse;
import static com.example.beauticianapplication.Utils.userIDJsonInput;
import static com.example.beauticianapplication.Utils.userIDJsonInputForAli;
import static com.example.beauticianapplication.Utils.userServiceHourEditJson;

public class MainActivity extends AppCompatActivity {

    CardView myCareerPathButton;
    CardView myHRManagerButton;
    CardView myIncomingAppointmentsButton;
    CardView myPastAppointmentsButton;
    CardView myInboxButton;
    CardView myAgendaButton;
    CardView myFinancialSituationButton;
    CardView myServicesButton;
    CardView myServiceLocationButton;
    CardView myServiceHoursButton;

    LinearLayout dashboardLayout;
    RelativeLayout chosenMenu;

    ImageView userProfileImageView;
    TextView userNameEditText;


    CardView serviceHourEditButton;

    CardView servicesDeleteButton;
    CardView servicesAddServiceButton;

    @Override
    public void onBackPressed() {
        if(dashboardLayout.getVisibility() == View.GONE){
            chosenMenu.setVisibility(View.GONE);
            dashboardLayout.setVisibility(View.VISIBLE);
            backButtonCount = 0;
        }else {
            if (backButtonCount >= 1) {
                DataHolder.deleteDataHolder();
                Intent registerIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(registerIntent);
            } else {
                Toast.makeText(this, "Press the back button once again to Log Out", Toast.LENGTH_SHORT).show();
                backButtonCount++;
            }
        }
    }

    int backButtonCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataHolder dataHolder = DataHolder.getInstance();


        userNameEditText = findViewById(R.id.userFullNameTextView);
        userProfileImageView = findViewById(R.id.userAvatarImageView);

        myCareerPathButton = findViewById(R.id.myCareerPathCardView);
        myHRManagerButton = findViewById(R.id.myHRManagerCardView);
        myIncomingAppointmentsButton = findViewById(R.id.myIncomingAppointmentsCardView);
        myPastAppointmentsButton = findViewById(R.id.myPastAppointmentsCardView);
        myInboxButton = findViewById(R.id.myInboxCardView);
        myAgendaButton = findViewById(R.id.myAgendaCardView);
        myFinancialSituationButton = findViewById(R.id.myFinancialSituationCardView);
        myServicesButton = findViewById(R.id.myServicesCardView);
        myServiceLocationButton = findViewById(R.id.myServiceLocationCardView);
        myServiceHoursButton = findViewById(R.id.myServiceHoursCardView);

        dashboardLayout = findViewById(R.id.dashboard);


        userNameEditText.setText(dataHolder.getUserFullName());
        Picasso.get().load(dataHolder.getPpLink()).into(userProfileImageView);

        serviceHourEditButton = findViewById(R.id.myServiceHoursEditButton);
        servicesDeleteButton = findViewById(R.id.serviceDeleteButton);
        servicesAddServiceButton = findViewById(R.id.addServiceButton);

        DatePicker datePicker =findViewById(R.id.addServiceDatePicker);
        LocalDate currentDate = LocalDate.now();
        datePicker.setMinDate(System.currentTimeMillis() - 1000);

        myCareerPathButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                chosenMenu = findViewById(R.id.myCareerPathLayout);

                dashboardLayout.setVisibility(View.GONE);
                chosenMenu.setVisibility(View.VISIBLE);

                myCareerPathFill();
            }


        });

        myHRManagerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, ChatScreen.class);
                startActivity(registerIntent);
            }
        });

        myIncomingAppointmentsButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                chosenMenu = findViewById(R.id.myIncomingAppointmentsLayout);

                dashboardLayout.setVisibility(View.GONE);
                chosenMenu.setVisibility(View.VISIBLE);

                myIncomingAppointmentsFill();
            }
        });

        myPastAppointmentsButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                chosenMenu = findViewById(R.id.myPastAppointmentsLayout);

                dashboardLayout.setVisibility(View.GONE);
                chosenMenu.setVisibility(View.VISIBLE);

                myPastAppointmentsFill();
            }
        });

        myInboxButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                chosenMenu = findViewById(R.id.myInboxLayout);

                dashboardLayout.setVisibility(View.GONE);
                chosenMenu.setVisibility(View.VISIBLE);

                myInboxFill();


            }
        });

        myAgendaButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, AgendaScreen.class);
                startActivity(registerIntent);
            }
        });

        myFinancialSituationButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                chosenMenu = findViewById(R.id.myFinancialSituationLayout);

                dashboardLayout.setVisibility(View.GONE);
                chosenMenu.setVisibility(View.VISIBLE);

                myFinancialSituationFill();


            }
        });

        myServicesButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                chosenMenu = findViewById(R.id.myServicesLayout);

                dashboardLayout.setVisibility(View.GONE);
                chosenMenu.setVisibility(View.VISIBLE);

                myServicesFill();

            }
        });

        myServiceLocationButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                chosenMenu = findViewById(R.id.myServiceLocationLayout);

                dashboardLayout.setVisibility(View.GONE);
                chosenMenu.setVisibility(View.VISIBLE);

                myServiceLocationFill();


            }
        });

        myServiceHoursButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                chosenMenu = findViewById(R.id.myServiceHoursLayout);

                dashboardLayout.setVisibility(View.GONE);
                chosenMenu.setVisibility(View.VISIBLE);

                myServiceHoursFill();
            }


        });

        Spinner dateSpinner = findViewById(R.id.pastAppointmentsSpinner);
        dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                TextView locationTextView = findViewById(R.id.pastAppointmentsLocationTextView);
                TextView location2TextView = findViewById(R.id.pastAppointmentsLocation2TextView);
                TextView buildingNoTextView = findViewById(R.id.pastAppointmentsBuildingNoTextView);
                TextView doorNoTextView = findViewById(R.id.pastAppointmentsDoorNoTextView);

                ArrayList<ArrayList<String>> pastAppointmentsHolder = DataHolder.getInstance().getPastAppointments();

                locationTextView.setText(pastAppointmentsHolder.get(position).get(1) + " " + pastAppointmentsHolder.get(position).get(4));
                location2TextView.setText(pastAppointmentsHolder.get(position).get(3) + " / " + pastAppointmentsHolder.get(position).get(2));
                buildingNoTextView.setText(pastAppointmentsHolder.get(position).get(5));
                doorNoTextView.setText(pastAppointmentsHolder.get(position).get(6));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        Spinner incomingDateSpinner = findViewById(R.id.incomingAppointmentsSpinner);
        incomingDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                TextView locationTextView = findViewById(R.id.incomingAppointmentsLocationTextView);
                TextView location2TextView = findViewById(R.id.incomingAppointmentsLocation2TextView);
                TextView buildingNoTextView = findViewById(R.id.incomingAppointmentsBuildingNoTextView);
                TextView doorNoTextView = findViewById(R.id.incomingAppointmentsDoorNoTextView);

                ArrayList<ArrayList<String>> incomingAppointmentsHolder = DataHolder.getInstance().getIncomingAppointments();

                locationTextView.setText(incomingAppointmentsHolder.get(position).get(1) + " " + incomingAppointmentsHolder.get(position).get(4));
                location2TextView.setText(incomingAppointmentsHolder.get(position).get(3) + " / " + incomingAppointmentsHolder.get(position).get(2));
                buildingNoTextView.setText(incomingAppointmentsHolder.get(position).get(5));
                doorNoTextView.setText(incomingAppointmentsHolder.get(position).get(6));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        serviceHourEditButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    EditText startHourEditText = findViewById(R.id.serviceStartHour);
                    EditText startMinuteEditText = findViewById(R.id.serviceStartMinute);
                    EditText endHourEditText = findViewById(R.id.serviceEndHour);
                    EditText endMinuteEditText = findViewById(R.id.serviceEndMinute);

                    {
                        int startHour = Integer.valueOf(startHourEditText.getText().toString());
                        int startMinute = Integer.valueOf(startMinuteEditText.getText().toString());
                        int endHour = Integer.valueOf(endHourEditText.getText().toString());
                        int endMinute = Integer.valueOf(endMinuteEditText.getText().toString());

                        if (startHour > 23 || endHour > 23 || startMinute > 59 || endMinute > 59 || startHour > endHour || (startHour == endHour && startMinute > endMinute)) {
                            Exception e = new Exception("error");
                            throw e;
                        }
                    }

                    ArrayList<ArrayList<String>> updatedVersion = dataHolder.getServiceSchedule();

                    Spinner serviceHourDaySpinner = findViewById(R.id.serviceHourDaySpinner);
                    int selectedDay = serviceHourDaySpinner.getSelectedItemPosition();

                    String startHour = startHourEditText.getText().toString();
                    String startMinute = startMinuteEditText.getText().toString();
                    String endHour = endHourEditText.getText().toString();
                    String endMinute = endMinuteEditText.getText().toString();

                    if(startHour.contentEquals("0")) startHour = "00";
                    if(startMinute.contentEquals("0")) startMinute = "00";
                    if(endHour.contentEquals("0")) endHour = "00";
                    if(endMinute.contentEquals("0")) endMinute = "00";

                    ArrayList<String> updatedDay = updatedVersion.get(selectedDay);
                    updatedDay.set(1, startHour);
                    updatedDay.set(2, startMinute);
                    updatedDay.set(3, endHour);
                    updatedDay.set(4, endMinute);

                    updatedVersion.set(selectedDay, updatedDay);

                    PostApi caller = new PostApi();
                    String response = caller.post("https://j15r9zo8a0.execute-api.eu-central-1.amazonaws.com/test/calisma_araligi_update", userServiceHourEditJson(updatedDay.get(0), startHour, startMinute, endHour, endMinute ));

                }catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Something is wrong try again with valid values", Toast.LENGTH_SHORT).show();
                }


            }
        });

        servicesDeleteButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    Spinner myServicesSpinner = findViewById(R.id.myServicesSpinner);
                    String serviceName = myServicesSpinner.getSelectedItem().toString();
                    DataHolder dataHolder = DataHolder.getInstance();
                    ArrayList<ArrayList<String>> serviceList = dataHolder.getServices();
                    ArrayList<ArrayList<String>> newServiceList = new ArrayList<>();
                    ArrayList<String> adapterServiceList = new ArrayList<>();

                    TableLayout servicesTable = (TableLayout)findViewById(R.id.myServicesTableLayout);
                    servicesTable.removeAllViews();
                    servicesTable.setStretchAllColumns(true);
                    servicesTable.bringToFront();
                    int counter = 1;

                    for(int i = 0; i < serviceList.size(); i++){
                        if(!serviceList.get(i).get(0).contentEquals(serviceName)){
                            newServiceList.add(serviceList.get(i));
                            adapterServiceList.add(serviceList.get(i).get(0));

                            TableRow tr =  new TableRow(MainActivity.this);
                            TextView c1 = new TextView(MainActivity.this);
                            c1.setTextColor(Color.WHITE);
                            c1.setTextSize(18);
                            c1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            c1.setText(String.valueOf(counter++));
                            c1.setTypeface(Typeface.SANS_SERIF);
                            c1.setWidth(90);
                            TextView c2 = new TextView(MainActivity.this);
                            c2.setTextColor(Color.WHITE);
                            c2.setTextSize(18);
                            c2.setTypeface(Typeface.SANS_SERIF);
                            c2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            c2.setText(serviceList.get(i).get(0));
                            c2.setWidth(170);
                            TextView c3 = new TextView(MainActivity.this);
                            c3.setTextColor(Color.WHITE);
                            c3.setText(serviceList.get(i).get(1));
                            c3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            c3.setTextSize(18);
                            c3.setTypeface(Typeface.SANS_SERIF);
                            c3.setWidth(80);
                            tr.addView(c1);
                            tr.addView(c2);
                            tr.addView(c3);
                            servicesTable.addView(tr);
                        }else{
                            PostApi caller = new PostApi();

                            String response = caller.post("https://j15r9zo8a0.execute-api.eu-central-1.amazonaws.com/test/beuaty_service_update", serviceDeleteJson(serviceList.get(i).get(0)));
                        }


                    }

                    dataHolder.setServices(newServiceList);

                    ArrayAdapter<String> adapter =
                            new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_item, adapterServiceList);
                    adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                    myServicesSpinner.setAdapter(adapter);

                    myServicesSpinner.setSelection(0);

                }catch(Exception e){
                    Toast.makeText(MainActivity.this, "Something is wrong try again with valid values", Toast.LENGTH_SHORT).show();
                }
            }
        });

        servicesAddServiceButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    DataHolder dataHolder = DataHolder.getInstance();

                    Spinner addServiceSpinner = findViewById(R.id.addServiceSpinner);
                    String newServiceName = addServiceSpinner.getSelectedItem().toString();

                    if(newServiceName == null || newServiceName.length() <= 1){
                        Toast.makeText(MainActivity.this, "Something is weird, sorry about that", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    ArrayList<ArrayList<String>> serviceList = dataHolder.getServices();
                    ArrayList<String> adapterServiceList = new ArrayList<>();
                    boolean doesAlreadyThere = false;

                    TableLayout servicesTable = (TableLayout)findViewById(R.id.myServicesTableLayout);
                    servicesTable.removeAllViews();
                    servicesTable.setStretchAllColumns(true);
                    servicesTable.bringToFront();

                    for(int i = 0; i < serviceList.size(); i++){
                        adapterServiceList.add(serviceList.get(i).get(0));
                        if(serviceList.get(i).get(0).contentEquals(newServiceName)){
                            doesAlreadyThere = true;
                        }

                        TableRow tr =  new TableRow(MainActivity.this);
                        TextView c1 = new TextView(MainActivity.this);
                        c1.setTextColor(Color.WHITE);
                        c1.setTextSize(18);
                        c1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        c1.setText(String.valueOf(i+1));
                        c1.setTypeface(Typeface.SANS_SERIF);
                        c1.setWidth(90);
                        TextView c2 = new TextView(MainActivity.this);
                        c2.setTextColor(Color.WHITE);
                        c2.setTextSize(18);
                        c2.setTypeface(Typeface.SANS_SERIF);
                        c2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        c2.setText(serviceList.get(i).get(0));
                        c2.setWidth(170);
                        TextView c3 = new TextView(MainActivity.this);
                        c3.setTextColor(Color.WHITE);
                        c3.setText(serviceList.get(i).get(1));
                        c3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        c3.setTextSize(18);
                        c3.setTypeface(Typeface.SANS_SERIF);
                        c3.setWidth(80);
                        tr.addView(c1);
                        tr.addView(c2);
                        tr.addView(c3);
                        servicesTable.addView(tr);
                    }

                    if(doesAlreadyThere == false){
                        adapterServiceList.add(newServiceName);
                        ArrayList<String> newOne = new ArrayList<>();
                        newOne.add(newServiceName);
                        String newServiceCost = "NA";

                        ArrayList<ArrayList<String>> allServiceList = dataHolder.getAllServices();
                        for(ArrayList<String> a: allServiceList){
                            if(a.get(0).contentEquals(newServiceName)){
                                newServiceCost = a.get(1);
                                break;
                            }

                        }
                        newOne.add(newServiceCost);

                        serviceList.add(newOne);

                        TableRow tr =  new TableRow(MainActivity.this);
                        TextView c1 = new TextView(MainActivity.this);
                        c1.setTextColor(Color.WHITE);
                        c1.setTextSize(18);
                        c1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        c1.setText(String.valueOf(serviceList.size()));
                        c1.setTypeface(Typeface.SANS_SERIF);
                        c1.setWidth(90);
                        TextView c2 = new TextView(MainActivity.this);
                        c2.setTextColor(Color.WHITE);
                        c2.setTextSize(18);
                        c2.setTypeface(Typeface.SANS_SERIF);
                        c2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        c2.setText(newServiceName);
                        c2.setWidth(170);
                        TextView c3 = new TextView(MainActivity.this);
                        c3.setTextColor(Color.WHITE);
                        c3.setText(newServiceCost);
                        c3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        c3.setTextSize(18);
                        c3.setTypeface(Typeface.SANS_SERIF);
                        c3.setWidth(80);
                        tr.addView(c1);
                        tr.addView(c2);
                        tr.addView(c3);
                        servicesTable.addView(tr);

                        PostApi caller = new PostApi();
                        String response = caller.post("https://j15r9zo8a0.execute-api.eu-central-1.amazonaws.com/test/beuaty_service_update", serviceAddJson(newServiceName));



                    }else{
                        Exception exception = new Exception("You can't add something that is already there");
                        throw exception;
                    }

                    dataHolder.setServices(serviceList);

                    Spinner myServicesSpinner = findViewById(R.id.myServicesSpinner);
                    ArrayAdapter<String> adapter =
                            new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_item, adapterServiceList);
                    adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                    myServicesSpinner.setAdapter(adapter);

                    myServicesSpinner.setSelection(0, false);
                    addServiceSpinner.setSelection(0, false);


                }catch(Exception e){
                    if(e.getMessage().contentEquals("You can't add something that is already there")){
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Something is wrong try again with valid values", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Spinner serviceHourDaySpinner = findViewById(R.id.serviceHourDaySpinner);

        serviceHourDaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                EditText startHourEditText = findViewById(R.id.serviceStartHour);
                EditText startMinuteEditText = findViewById(R.id.serviceStartMinute);
                EditText endHourEditText = findViewById(R.id.serviceEndHour);
                EditText endMinuteEditText = findViewById(R.id.serviceEndMinute);

                startHourEditText.setText(DataHolder.getInstance().getServiceSchedule().get(position).get(1));
                startMinuteEditText.setText(DataHolder.getInstance().getServiceSchedule().get(position).get(2));
                endHourEditText.setText(DataHolder.getInstance().getServiceSchedule().get(position).get(3));
                endMinuteEditText.setText(DataHolder.getInstance().getServiceSchedule().get(position).get(4));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        Spinner citySpinner = findViewById(R.id.citySpinner);

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                try {
                    City selectedCity = (City )citySpinner.getSelectedItem();

                    if(selectedCity.getDistrictList() == null){
                        PostApi caller = new PostApi();
                        String response2 = caller.post("https://ldy1qgz5vk.execute-api.eu-central-1.amazonaws.com/default/addservicelocation", getDistrictListJson(selectedCity.getKey()));
                        JSONObject jsonObj2 = new JSONObject(response2);
                        response2 = (String) jsonObj2.get("service_locations");
                        ArrayList<District> districtList = districtParser(response2);
                        Collections.sort(districtList);
                        selectedCity.setDistrictList(districtList);
                    }

                    Spinner districtSpinner = findViewById(R.id.districtSpinner);
                    ArrayAdapter<District> adapter2 =
                            new ArrayAdapter<District>(getApplicationContext(),  android.R.layout.simple_spinner_item, selectedCity.getDistrictList());
                    adapter2.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                    districtSpinner.setAdapter(adapter2);

                    districtSpinner.setSelected(false);
                    districtSpinner.setSelected(true);
                    districtSpinner.setSelection(0, false);

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        Spinner districtSpinner = findViewById(R.id.districtSpinner);

        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                City selectedCity = (City )citySpinner.getSelectedItem();
                District selectedDistrict = (District )districtSpinner.getSelectedItem();
                boolean exist = false;

                for(ArrayList<String> a: dataHolder.getServiceLocation()){
                    if(a.get(1).contentEquals(selectedCity.toString()) && a.get(2).contentEquals(selectedDistrict.toString())){
                        exist = true;
                    }
                }

                TextView buttonLabel = findViewById(R.id.serviceLocationAddDeleteButtonLabel);
                TextView infoMessage = findViewById(R.id.addLocationInfoTextView);

                if(exist == true){
                    buttonLabel.setText("Delete Location");
                    datePicker.setVisibility(View.GONE);
                    infoMessage.setVisibility(View.GONE);
                }else{
                    buttonLabel.setText("Add Location");
                    datePicker.setVisibility(View.VISIBLE);
                    infoMessage.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        CardView serviceLocationAddDeleteButton = findViewById(R.id.serviceLocationAddDeleteButton);

        serviceLocationAddDeleteButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                PostApi caller = new PostApi();

                Spinner citySpinner = findViewById(R.id.citySpinner);
                Spinner districtSpinner = findViewById(R.id.districtSpinner);

                City selectedCity = (City )citySpinner.getSelectedItem();
                District selectedDistrict = (District )districtSpinner.getSelectedItem();

                TextView buttonLabel = findViewById(R.id.serviceLocationAddDeleteButtonLabel);

                if(buttonLabel.getText().toString().contentEquals("Delete Location")) {
                    try {
                        String response = caller.post("https://ldy1qgz5vk.execute-api.eu-central-1.amazonaws.com/default/addservicelocation", deleteServiceLocationJson(selectedCity.getId(), selectedDistrict.getId()));
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }else{
                    try {

                        int day = datePicker.getDayOfMonth();
                        int month = datePicker.getMonth() + 1;
                        int year = datePicker.getYear();
                        String response = caller.post("https://ldy1qgz5vk.execute-api.eu-central-1.amazonaws.com/default/addservicelocation", addServiceLocationJson(selectedCity.getId(), selectedDistrict.getId(), day, month, year));
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                myServiceLocationFill();

            }
        });

    }

    private void myServiceHoursFill() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    DataHolder dataHolder = DataHolder.getInstance();
                    PostApi caller = new PostApi();
                    String response = caller.post("https://j15r9zo8a0.execute-api.eu-central-1.amazonaws.com/test/calimsa_araligi", userIDJsonInput());
                    ArrayList<ArrayList<String>> rs = parseMyHour(response);

                    dataHolder.setServiceSchedule(rs);

                    EditText startHourEditText = findViewById(R.id.serviceStartHour);
                    EditText startMinuteEditText = findViewById(R.id.serviceStartMinute);
                    EditText endHourEditText = findViewById(R.id.serviceEndHour);
                    EditText endMinuteEditText = findViewById(R.id.serviceEndMinute);

                    Spinner daySelectSpinner = findViewById(R.id.serviceHourDaySpinner);

                    ArrayList<String> dayList = new ArrayList<>();

                    for(ArrayList<String> a: rs){
                        dayList.add(a.get(0));
                    }

                    ArrayAdapter<String> adapter =
                            new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_item, dayList);
                    adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                    daySelectSpinner.setAdapter(adapter);

                    daySelectSpinner.setSelection(0);

                    startHourEditText.setText(rs.get(daySelectSpinner.getSelectedItemPosition()).get(1));
                    startMinuteEditText.setText(rs.get(daySelectSpinner.getSelectedItemPosition()).get(2));
                    endHourEditText.setText(rs.get(daySelectSpinner.getSelectedItemPosition()).get(3));
                    endMinuteEditText.setText(rs.get(daySelectSpinner.getSelectedItemPosition()).get(4));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void myInboxFill() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    PostApi caller = new PostApi();
                    DataHolder dataHolder = DataHolder.getInstance();

                    String response = caller.post("https://55k8mk3fw6.execute-api.eu-central-1.amazonaws.com/default/myinbox", userIDJsonInputForAli());
                    JSONObject jsonObj = new JSONObject(response);
                    response = (String) jsonObj.get("body");
                    response = removeFirstLast(response);

                    dataHolder.setPhoneNumber(response);

                    TextView myInboxMessageTextView = findViewById(R.id.myInboxMessage);

                    myInboxMessageTextView.setText("New notifications will be directed\n to your mobile: \n" + dataHolder.getPhoneNumber() + "");

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void myServiceLocationFill(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    PostApi caller = new PostApi();
                    DataHolder dataHolder = DataHolder.getInstance();

                    String response = caller.post("https://nubpd621wa.execute-api.eu-central-1.amazonaws.com/default/myservicelocations", userIDJsonInputForAli());
                    JSONObject jsonObj = new JSONObject(response);
                    response = (String) jsonObj.get("service_locations");
                    ArrayList<ArrayList<String>> rs = servicesParse(response);

                    dataHolder.setServiceLocation(rs);

                    TableLayout serviceLocationTable = (TableLayout)findViewById(R.id.myServiceLocationTableLayout);
                    serviceLocationTable.removeAllViews();
                    serviceLocationTable.setStretchAllColumns(true);
                    serviceLocationTable.bringToFront();
                    for(int i = 0; i < rs.size(); i++){
                        TableRow tr =  new TableRow(MainActivity.this);
                        TextView c1 = new TextView(MainActivity.this);
                        c1.setTextColor(Color.WHITE);
                        c1.setTextSize(18);
                        c1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        c1.setText(rs.get(i).get(0));
                        c1.setTypeface(Typeface.SANS_SERIF);
                        TextView c2 = new TextView(MainActivity.this);
                        c2.setTextColor(Color.WHITE);
                        c2.setTextSize(18);
                        c2.setTypeface(Typeface.SANS_SERIF);
                        c2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        c2.setText(rs.get(i).get(1));
                        TextView c3 = new TextView(MainActivity.this);
                        c3.setTextColor(Color.WHITE);
                        c3.setText(rs.get(i).get(2));
                        c3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        c3.setTextSize(18);
                        c3.setTypeface(Typeface.SANS_SERIF);
                        tr.addView(c1);
                        tr.addView(c2);
                        tr.addView(c3);
                        serviceLocationTable.addView(tr);
                    }

                    Spinner citySpinner = findViewById(R.id.citySpinner);

                    if(dataHolder.getCities() == null){
                        String response2 = caller.post("https://ldy1qgz5vk.execute-api.eu-central-1.amazonaws.com/default/addservicelocation", getCityListJson());
                        JSONObject jsonObj2 = new JSONObject(response2);
                        response2 = (String) jsonObj2.get("service_locations");
                        ArrayList<City> cityList = cityParser(response2);

                        Collections.sort(cityList);
                        dataHolder.setCities(cityList);


                    }
                    ArrayAdapter<City> adapter2 =
                            new ArrayAdapter<City>(getApplicationContext(),  android.R.layout.simple_spinner_item, dataHolder.getCities());
                    adapter2.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter2);

                    citySpinner.setSelected(false);
                    citySpinner.setSelection(0, false);


                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void myServicesFill() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    PostApi caller = new PostApi();
                    DataHolder dataHolder = DataHolder.getInstance();

                    String response = caller.post("https://5per00nlf1.execute-api.eu-central-1.amazonaws.com/default/myservices", userIDJsonInputForAli());
                    JSONObject jsonObj = new JSONObject(response);
                    response = (String) jsonObj.get("services");
                    ArrayList<ArrayList<String>> rs = servicesParse(response);

                    dataHolder.setServices(rs);

                    ArrayList<String> servicesList = new ArrayList<String>();

                    TableLayout servicesTable = (TableLayout)findViewById(R.id.myServicesTableLayout);
                    servicesTable.removeAllViews();
                    servicesTable.setStretchAllColumns(true);
                    servicesTable.bringToFront();
                    for(int i = 0; i < rs.size(); i++){
                        TableRow tr =  new TableRow(MainActivity.this);
                        TextView c1 = new TextView(MainActivity.this);
                        c1.setTextColor(Color.WHITE);
                        c1.setTextSize(18);
                        c1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        c1.setText(String.valueOf(i+1));
                        c1.setTypeface(Typeface.SANS_SERIF);
                        c1.setWidth(90);
                        TextView c2 = new TextView(MainActivity.this);
                        c2.setTextColor(Color.WHITE);
                        c2.setTextSize(18);
                        c2.setTypeface(Typeface.SANS_SERIF);
                        c2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        c2.setText(rs.get(i).get(0));
                        c2.setWidth(170);
                        TextView c3 = new TextView(MainActivity.this);
                        c3.setTextColor(Color.WHITE);
                        c3.setText(rs.get(i).get(1));
                        c3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        c3.setTextSize(18);
                        c3.setTypeface(Typeface.SANS_SERIF);
                        c3.setWidth(80);
                        tr.addView(c1);
                        tr.addView(c2);
                        tr.addView(c3);
                        servicesTable.addView(tr);
                        servicesList.add(rs.get(i).get(0));
                    }

                    Spinner myServicesSpinner = findViewById(R.id.myServicesSpinner);


                    ArrayAdapter<String> adapter =
                            new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_item, servicesList);
                    adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                    myServicesSpinner.setAdapter(adapter);

                    myServicesSpinner.setSelection(0);

                    {
                        String response2 = caller.whenGetRequest_thenCorrect("https://j15r9zo8a0.execute-api.eu-central-1.amazonaws.com/test/getserviceTable");
                        ArrayList<ArrayList<String>> rs2 = allServicesParse(response2);
                        dataHolder.setAllServices(rs2);

                        Spinner addServiceSpinner = findViewById(R.id.addServiceSpinner);

                        ArrayList<String> allServiceList = new ArrayList<String>();

                        for(ArrayList<String> a: rs2){
                            allServiceList.add(a.get(0));
                        }

                        ArrayAdapter<String> adapter2 =
                                new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_item, allServiceList);
                        adapter2.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                        addServiceSpinner.setAdapter(adapter2);

                        addServiceSpinner.setSelection(0);

                    }



                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void myIncomingAppointmentsFill() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    PostApi caller = new PostApi();
                    DataHolder dataHolder = DataHolder.getInstance();

                    String response = caller.post("https://j15r9zo8a0.execute-api.eu-central-1.amazonaws.com/test/incoming_appointments", userIDJsonInput());
                    ArrayList<ArrayList<String>> rs = parseMyJsonList(response);

                    dataHolder.setIncomingAppointments(rs);

                    ArrayList<String> incomingDateList = new ArrayList<String>();

                    for(ArrayList<String> a: dataHolder.getIncomingAppointments()){
                        incomingDateList.add(a.get(0));
                    }

                    Spinner dateSpinner = findViewById(R.id.incomingAppointmentsSpinner);

                    ArrayAdapter<String> adapter =
                            new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_item, incomingDateList);
                    adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                    dateSpinner.setAdapter(adapter);

                    dateSpinner.setSelection(0);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void myPastAppointmentsFill() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    PostApi caller = new PostApi();
                    DataHolder dataHolder = DataHolder.getInstance();

                    String response = caller.post("https://j15r9zo8a0.execute-api.eu-central-1.amazonaws.com/test/past_appointments", userIDJsonInput());
                    ArrayList<ArrayList<String>> rs = parseMyJsonList(response);

                    dataHolder.setPastAppointments(rs);

                    ArrayList<String> pastDateList = new ArrayList<String>();

                    for(ArrayList<String> a: dataHolder.getPastAppointments()){
                        pastDateList.add(a.get(0));
                    }

                    Spinner dateSpinner = findViewById(R.id.pastAppointmentsSpinner);

                    ArrayAdapter<String> adapter =
                            new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_item, pastDateList);
                    adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                    dateSpinner.setAdapter(adapter);

                    dateSpinner.setSelection(0);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void myFinancialSituationFill() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    PostApi caller = new PostApi();
                    DataHolder dataHolder = DataHolder.getInstance();

                    String response = caller.post("https://j15r9zo8a0.execute-api.eu-central-1.amazonaws.com/test/my_financial_situation", userIDJsonInput());
                    ArrayList<String> rs = parseMyJson(response);

                    dataHolder.setTotalEarnings(Integer.valueOf(rs.get(0)));
                    dataHolder.setMonthlyEarnings(Integer.valueOf(rs.get(1)));
                    dataHolder.setAnnualEarnings(Integer.valueOf(rs.get(2)));

                    TextView totalEarningsTextView = findViewById(R.id.totalEarningsTextView);
                    TextView  monthlyEarningsTextView = findViewById(R.id.monthlyEarningsTextView);
                    TextView annualEarningsTextView = findViewById(R.id.annualEarningsTextView);

                    totalEarningsTextView.setText(String.valueOf(dataHolder.getTotalEarnings()) + " TL");
                    monthlyEarningsTextView.setText(String.valueOf(dataHolder.getMonthlyEarnings()) + " TL");
                    annualEarningsTextView.setText(String.valueOf(dataHolder.getAnnualEarnings()) + " TL");


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void myCareerPathFill() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    PostApi caller = new PostApi();
                    DataHolder dataHolder = DataHolder.getInstance();

                    String response = caller.post("https://j15r9zo8a0.execute-api.eu-central-1.amazonaws.com/test/career_path", userIDJsonInput());
                    ArrayList<String> rs = parseMyJson(response);

                    dataHolder.setTotalJobDone(Integer.valueOf(rs.get(0)));
                    dataHolder.setMonthJobDone(Integer.valueOf(rs.get(1)));
                    dataHolder.setLevel(Integer.valueOf(rs.get(2)));

                    TextView totalJobDoneTextView = findViewById(R.id.totalJobDoneTextView);
                    TextView monthDoneThisMonthTextView = findViewById(R.id.monthDoneThisMonthTextView);
                    TextView levelTextView = findViewById(R.id.levelTextView);
                    ImageView levelImageView = findViewById(R.id.levelImageView);

                    totalJobDoneTextView.setText(String.valueOf(dataHolder.getTotalJobDone()));
                    monthDoneThisMonthTextView.setText(String.valueOf(dataHolder.getMonthJobDone()));
                    levelTextView.setText(String.valueOf(dataHolder.getLevel()));

                    if(levelTextView.getText().equals("1")){
                        levelImageView.setImageResource(R.drawable.level1_icon);
                    }else if(levelTextView.getText().equals("2")){
                        levelImageView.setImageResource(R.drawable.level2_icon);
                    }else{
                        levelImageView.setImageResource(R.drawable.level3_icon);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}