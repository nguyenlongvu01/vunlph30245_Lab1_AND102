package com.vunlph30245.lab1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ToDoDAO toDoDAO;
    private AdapterSanpham adapterSanpham;
    private ArrayList<SanphamModel> listSanphams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText edtTitle = findViewById(R.id.edt_title);
        EditText edtContent = findViewById(R.id.edt_content);
        EditText edtDate = findViewById(R.id.edt_date);
        EditText edtType = findViewById(R.id.edt_type);

        Button btnAdd = findViewById(R.id.btn_add);
        Button btnUpdate = findViewById(R.id.btn_update);
        Button btnDelete = findViewById(R.id.btn_delete);

        RecyclerView rcvSanpham = findViewById(R.id.rcv_sanpham);

        toDoDAO = new ToDoDAO(this);
        listSanphams = toDoDAO.getListToDo();
        adapterSanpham = new AdapterSanpham(listSanphams);
        rcvSanpham.setAdapter(adapterSanpham);
        rcvSanpham.setLayoutManager(new LinearLayoutManager(this));


        btnAdd.setOnClickListener(view -> {
            String title = edtTitle.getText().toString();
            String content = edtContent.getText().toString();
            String date = edtDate.getText().toString();
            String type = edtType.getText().toString();

            SanphamModel newTask = new SanphamModel(title, content, date, type);
            toDoDAO.addToDo(newTask);
            listSanphams.add(newTask);
            adapterSanpham.notifyDataSetChanged();
            Toast.makeText(this, "Công việc đã được thêm", Toast.LENGTH_SHORT).show();
        });


        btnUpdate.setOnClickListener(view -> {
            String title = edtTitle.getText().toString();
            String content = edtContent.getText().toString();
            String date = edtDate.getText().toString();
            String type = edtType.getText().toString();

            SanphamModel updatedTask = new SanphamModel(title, content, date, type);
            toDoDAO.updateToDo(updatedTask);

            for (int i = 0; i < listSanphams.size(); i++) {
                if (listSanphams.get(i).getTitle().equals(title)) {
                    listSanphams.set(i, updatedTask);
                    break;
                }
            }
            adapterSanpham.notifyDataSetChanged();
            Toast.makeText(this, "Sua thanh cong", Toast.LENGTH_SHORT).show();
        });


        btnDelete.setOnClickListener(view -> {
            String title = edtTitle.getText().toString();
            toDoDAO.deleteToDo(title);

            listSanphams.removeIf(task -> task.getTitle().equals(title));
            adapterSanpham.notifyDataSetChanged();
            Toast.makeText(this, "Da xoa", Toast.LENGTH_SHORT).show();
        });
    }
}
