package com.example.reto1;

public class SneakersLista {

        private String descripcion;
        private String titulo;

        private byte[] imagen;  // Agrega este campo para la imagen en formato BLOB


        public SneakersLista(String descripcion, String titulo, byte[] img) {
            this.descripcion = descripcion;
            this.titulo = titulo;
            this.imagen = img;
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
            return  titulo + " " + descripcion;
        }

}
