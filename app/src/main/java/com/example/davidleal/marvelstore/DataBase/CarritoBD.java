package com.example.davidleal.marvelstore.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.davidleal.marvelstore.Modelos.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CarritoBD extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Carrito.db";
    public static final String Producto_TABLE_NAME = "Producto";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_Nombre = "nombre";
    public static final String CONTACTS_COLUMN_Precio= "precio";
    public static final String CONTACTS_COLUMN_PrecioImpuesto = "precioimpuesto";
    public static final String CONTACTS_COLUMN_Categoria = "categoria";
    public static final String CONTACTS_COLUMN_Imagen = "imagen";
    public static final String CONTACTS_COLUMN_Cantidad = "cantidad";


    private HashMap hp;


    public CarritoBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table Producto " +
                        "(id string primary key, nombre text,precio text,imagen text, categoria text,descripcion text, precioimpuesto text, cantidad int)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
// TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Carrito");
        onCreate(db);
    }

    public boolean insertProducto (Producto producto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", producto.getId());
        contentValues.put("nombre", producto.getNombre());
        contentValues.put("precio", producto.getPrecio()+"");
        contentValues.put("imagen", producto.getImagen());
        contentValues.put("categoria", producto.getCategoria());
        contentValues.put("descripcion", producto.getDescripcion());
        contentValues.put("precioimpuesto", producto.getPrecioImpuesto()+"");
        contentValues.put("cantidad", producto.getCantidad());



        db.insert("Producto", null, contentValues);
        return true;
    }

    public Cursor getData(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Producto where id ="+"'"+id+"'", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, Producto_TABLE_NAME);
        return numRows;
    }

    public boolean updateProducto (Producto producto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", producto.getNombre());
        contentValues.put("id", producto.getId());
        contentValues.put("precio", producto.getPrecio()+"");
        contentValues.put("imagen", producto.getImagen());
        contentValues.put("categoria", producto.getCategoria());
        contentValues.put("descripcion", producto.getDescripcion());
        contentValues.put("precioimpuesto", producto.getPrecioImpuesto()+"");
        contentValues.put("cantidad",producto.getCantidad());

        db.update("Producto", contentValues, "id = ? ", new String[] { producto.getId() } );
        return true;
    }

    public Integer deleteProducto (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Producto",
                "id = ? ",
                new String[] { (id) });
    }

    public void deleteAllProducto () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ Producto_TABLE_NAME);
    }

    public List<Producto> getAllProductos() {
        List<Producto> array_list = new ArrayList();
        Producto producto = new Producto();


        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + Producto_TABLE_NAME;
        try {
            Cursor res = db.rawQuery(query, null);
            res.moveToFirst();
            while (!res.isAfterLast()) {
                producto = new Producto();
                producto.setId(res.getString(0));
                producto.setNombre(res.getString(1));
                producto.setImagen(res.getString(3));
                producto.setDescripcion(res.getString(5));
                producto.setCategoria(res.getString(4));
                producto.setPrecioImpuesto(Double.parseDouble(res.getString(6)));
                producto.setPrecio(Double.parseDouble(res.getString(2)));
                producto.setCantidad(res.getInt(7));

                array_list.add(producto);
                res.moveToNext();
            }
        }
        catch(SQLiteException e) { }
        return array_list;

    }
}
