package com.example.reto1.Model;

import com.example.reto1.R;

public class Sneaker {

    private int id;

    private String name;

    private int idDrawable;

    public Sneaker(int id,String name, int idDrawable){

        this.id = id;
        this.name = name;
        this.idDrawable = idDrawable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

        public static Sneaker[] ITEMS = {

            new Sneaker(1, "converse1", R.drawable.converse1),
            new Sneaker(2, "converse2", R.drawable.converse2),
            new Sneaker(3, "fila", R.drawable.fila),
            new Sneaker(4, "fly", R.drawable.fly),
            new Sneaker(5, "new balance", R.drawable.newbalance),
            new Sneaker(6, "nike", R.drawable.nike),
            new Sneaker(7, "puma", R.drawable.puma),
            new Sneaker(8, "reebok", R.drawable.reebok),


        };

        public static Sneaker getItem(int id){

            for(Sneaker sneakerActual: ITEMS){

                if (sneakerActual.getId() == id){
                    return sneakerActual;
                }
            }
            return null;
        }


}
