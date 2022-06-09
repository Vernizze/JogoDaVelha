package com.uninter.types;

//Classe utilizada para que métodos da Aplicação possam retornar dois valores
public class Tupla<X, Y> {
    public final X x;
    public final Y y;

    public Tupla(X x, Y y) { 
      this.x = x; 
      this.y = y; 
    } 
  } 