package com.example.reto1;

public class SneakersLista {

        private int ID;

        private String descripcion;
        private String titulo;

        private byte[] imagen;  // Agrega este campo para la imagen en formato BLOB


        public SneakersLista(int id,String descripcion, String titulo, String s, byte[] img) {
            this.ID= id;
            this.descripcion = descripcion;
            this.titulo = titulo;
            this.imagen = img;
        }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    // Métodos getter y setter para la descripción y el precio


        public byte[] getImagen() {

            return imagen;
        }

        public void setImagen(byte[] imagen) {
            this.imagen = imagen;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }


        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        @Override
        public String toString() {
            return  ID+ " " +titulo + " " + descripcion;
        }

}
