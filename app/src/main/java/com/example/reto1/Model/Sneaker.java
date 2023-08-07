package com.example.reto1.Model;

import com.example.reto1.R;

public class Sneaker {

    private int id;

    private String name;

    private String description;

    private int idDrawable;



    public Sneaker(int id,String name, String description, int idDrawable){

        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

        public static Sneaker[] ITEMS = {

            new Sneaker(1, "converse1", "Converse Rubber Shoe Company fue creado por Marquis Mills Converse en 1908 en Malden Massachusetts. En 1917, la compañía creo el primer calzado llamado All Star compuesto únicamente de caucho y lona.",R.drawable.converse1),
            new Sneaker(2, "converse2", "Converse Rubber Shoe Company fue creado por Marquis Mills Converse en 1908 en Malden Massachusetts. En 1917, la compañía creo el primer calzado llamado All Star compuesto únicamente de caucho y lona.", R.drawable.converse2),
            new Sneaker(3, "fila", "FILA es una de as marcas de sportswear más antiguas y grandes del mundo. Nació en 1911 en Biella, Italia, creada por los hermanos Fila. La marca inició sus andaduras fabricando ropa interior adaptada a las exigencias del clima alpino de Biella.", R.drawable.fila),
            new Sneaker(4, "fly", "marca de identidad como una estrategia de marketing en la que se crea un nuevo nombre para los tenis fly",R.drawable.fly),
            new Sneaker(5, "new balance", "New Balance Athletics, Inc. más conocida simplemente como New Balance (NB), es un fabricante estadounidense de piezas de arco con sede en Boston (Estados Unidos). La fundación data del 1906 con el nombre New Balance Arch Support Company", R.drawable.newbalance),
            new Sneaker(6, "nike", "(del griego: Νίκη, Niké, victoria;\u200B NYSE: NKE) es una empresa multinacional estadounidense dedicada al diseño, desarrollo, fabricación y comercialización de equipamiento deportivo: balones, calzado, ropa, equipo, accesorios y otros artículos deportivos.",R.drawable.nike),
            new Sneaker(7, "puma", "PUMA es la tercera compañía de indumentaria deportiva más grande e importante del mundo. PUMA, es una multinacional europea que diseña y fabrica calzado, indumentaria y accesorios deportivos y casuales, cuya sede principal está en Herzogenaurach, Bavaria.",R.drawable.puma),
            new Sneaker(8, "reebok", "Reebok International Limited, anteriormente Reebok, es una empresa americana de ascedenscia inglesa subsidiaria del grupo Authentic Brands Group (ABG), y especializado en zapatillas, ropa, y accesorios deportivos.", R.drawable.reebok),


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
