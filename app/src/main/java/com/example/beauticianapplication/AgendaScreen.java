package com.example.beauticianapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.beauticianapplication.Utils.dateToEpoch;
import static com.example.beauticianapplication.Utils.servicesParse;
import static com.example.beauticianapplication.Utils.userIDJsonInputForAli;

public class AgendaScreen extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Intent registerIntent = new Intent(AgendaScreen.this, MainActivity.class);
        startActivity(registerIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_agenda);

        myAgendaFill();
    }

    private void myAgendaFill() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    PostApi caller = new PostApi();
                    DataHolder dataHolder = DataHolder.getInstance();

                    String response = caller.post("https://fbclo6slpk.execute-api.eu-central-1.amazonaws.com/default/myagenda", userIDJsonInputForAli());
                    JSONObject jsonObj = new JSONObject(response);
                    response = (String) jsonObj.get("appointments");
                    ArrayList<ArrayList<String>> rs = servicesParse(response);
                    dataHolder.setAppointments(rs);

                    SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());

                    CompactCalendarView compactCalendarView = findViewById(R.id.compactcalendar_view);
                    compactCalendarView.setUseThreeLetterAbbreviation(true);
                    compactCalendarView.setCurrentDate(new Date());
                    TextView monthYearTextView = findViewById(R.id.myAgendaMonthYearTextView);

                    TextView chosenDayTextView = findViewById(R.id.myAgendaChosenDate);
                    chosenDayTextView.setText("No chosen date");

                    monthYearTextView.setText(dateFormatMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

                    int i = 0;
                    for(ArrayList<String> event: rs){
                        Long a = dateToEpoch(event.get(0), event.get(1), event.get(2));
                        Event ev1 = new Event(Color.GREEN, a, "" + i);
                        i++;
                        compactCalendarView.addEvent(ev1);
                    }

                    compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
                        @Override
                        public void onDayClick(Date dateClicked) {
                            ArrayList<ArrayList<String>> appointments = DataHolder.getInstance().getAppointments();
                            List<Event> events = compactCalendarView.getEvents(dateClicked);
                            Context context = getApplicationContext();

                            TextView chosenDayTextView = findViewById(R.id.myAgendaChosenDate);
                            chosenDayTextView.setText("Chosen Date: " + new SimpleDateFormat("dd/MM/yyyy").format(dateClicked));

                            ListView yourListView = (ListView) findViewById(R.id.myAgendaListView);

                            ArrayList<AppointmentsData> holyMoly = new ArrayList<>();

                            if(events.size() != 0){
                                for(Event event: events){
                                    ArrayList<String>  chosenEvent = appointments.get(Integer.parseInt((String) event.getData()));
                                    holyMoly.add(new AppointmentsData(chosenEvent.get(6) + " " + chosenEvent.get(7), chosenEvent.get(8),chosenEvent.get(9),chosenEvent.get(3) + ":" + chosenEvent.get(4)));
                                    System.out.println("Starting time: " + chosenEvent.get(3) + ":" + chosenEvent.get(4) + ", to who: " + chosenEvent.get(6) + " " + chosenEvent.get(7) + ", Phone number: " + chosenEvent.get(8) + ", Note: " + chosenEvent.get(9));
                                }
                            }else{
                                Toast.makeText(context, "No event", Toast.LENGTH_SHORT).show();
                            }
                            ListAdapter customAdapter = new ListAdapter(AgendaScreen.this, R.layout.agenda_grid_layout, holyMoly);
                            yourListView.setAdapter(customAdapter);
                        }

                        @Override
                        public void onMonthScroll(Date firstDayOfNewMonth) {
                            monthYearTextView.setText(dateFormatMonth.format(firstDayOfNewMonth));
                        }
                    });



                } catch (IOException | JSONException | ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}