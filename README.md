# Multithreaded Library Management System

## Overview

Este sistema de gestión de biblioteca simula múltiples usuarios (patrons) que interactúan con una biblioteca de forma concurrente. Cada usuario puede tomar prestado un libro y devolverlo tras un periodo aleatorio, utilizando multithreading en Java para simular la concurrencia real.

## Arquitectura

El sistema se compone de las siguientes clases principales:

- Book: Representa un libro con atributos como título, autor, número de copias disponibles e ISBN.
- Library: Administra los libros, permitiendo el registro, préstamo y devolución. Protege los recursos compartidos con sincronización.
- Patron: Representa un usuario de la biblioteca. Cada instancia corre en su propio hilo (Runnable) y simula el préstamo y la devolución.
- Main: Inicia la biblioteca, registra libros y lanza múltiples hilos de usuarios.

## Métodos Clave

### Library
- `registerBook(title, author, numberOfCopies, isbn)`  
  Registra un nuevo libro. Usa synchronized para evitar registros simultáneos duplicados.

- `borrowBook(title, patronName)`  
  Intenta prestar un libro. Usa sincronización interna del objeto Book para garantizar la atomicidad al modificar el número de copias.

- `returnBook(title, patronName)`  
  Devuelve un libro prestado. También sincronizado para evitar inconsistencias.

- `getRandomBookTitle()`  
  Devuelve un título aleatorio de los disponibles.

### Book
- `synchronized borrow()`  
  Reduce el número de copias disponibles si es mayor a 0.

- `synchronized returnBook()`  
  Incrementa el número de copias disponibles.

### Patron implements Runnable
- Simula la acción de tomar prestado y devolver un libro aleatorio después de un tiempo.
- Cada instancia corre en su propio hilo.

## Cómo ejecutar el sistema

1. Asegúrate de tener instalado Java 11+.
2. Compila todos los archivos:

```bash
javac Main.java model/*.java
```
2. Corre el código:
```bash
java Main
```