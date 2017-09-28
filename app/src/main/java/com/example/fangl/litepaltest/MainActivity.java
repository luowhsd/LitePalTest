package com.example.fangl.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.fangl.litepaltest.bean.Book;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button create;
    private Button add;
    private Button delete;
    private Button updata;
    private Button query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create = (Button)findViewById(R.id.create);
        add = (Button)findViewById(R.id.add);
        delete = (Button)findViewById(R.id.delete);
        updata = (Button)findViewById(R.id.updata);
        query = (Button)findViewById(R.id.query);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setAuthor("luowh");
                book.setName("www");
                book.setPages(111);
                book.setPress("njklj");
                book.setPrice(105.5);
                book.save();
                book.setPrice(500.0);
                book.save();
            }
        });
        updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
             /*   book.setPrice(444);
                book.setPress("author");
                book.updateAll("price=? and pages=?","500.0","111");*/
                book.setToDefault("pages");
                book.updateAll();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book.class,"price<?","200");
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//             List<Book> books = DataSupport.findAll(Book.class);
                List<Book> books = DataSupport.select("name","author").where("pages>?","0").find(Book.class);
                for(Book book:books){

                       Log.d("db", book.getPress()+"");

                }
            }
        });
    }
}
