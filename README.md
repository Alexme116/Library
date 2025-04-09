# Multithreaded Library Management System

## Overview

Este sistema de gestión de biblioteca simula múltiples usuarios (patrons) que interactúan con una biblioteca de forma concurrente. Cada usuario puede tomar prestado un libro y devolverlo tras un periodo aleatorio, utilizando multithreading en Java para simular la concurrencia real. Además, implementa una lógica de préstamo y devolución que respeta la sincronización para evitar condiciones de carrera y garantizar que los recursos compartidos (libros) sean accesibles de forma segura en un entorno multihilo.

## Arquitectura

El sistema se compone de las siguientes clases principales:

- **Book**: Representa un libro con atributos como título, autor, número de copias disponibles e ISBN.
- **Library**: Administra los libros, permitiendo el registro, préstamo y devolución. Protege los recursos compartidos con sincronización.
- **Patron**: Representa un usuario de la biblioteca. Cada instancia corre en su propio hilo (Runnable) y simula el préstamo y la devolución de libros.
- **Main**: Inicia la biblioteca, registra libros y lanza múltiples hilos de usuarios.

## Métodos Clave

### Library
- `registerBook(title, author, numberOfCopies, isbn)`  
  Registra un nuevo libro en la biblioteca. Usa `synchronized` para evitar registros simultáneos duplicados.
  
- `borrowBook(title, patronName)`  
  Intenta prestar un libro. Utiliza sincronización para evitar que varios patronos tomen el mismo libro de forma concurrente. La función de préstamo también asegura que no se preste un libro si no hay copias disponibles.

- `returnBook(title, patronName)`  
  Devuelve un libro prestado. Usa sincronización para evitar inconsistencias en la cantidad de copias disponibles.

- `getRandomBookTitle()`  
  Devuelve un título aleatorio de los libros disponibles en la biblioteca.

### Book
- `synchronized borrow()`  
  Intenta reducir el número de copias disponibles si es mayor a 0, asegurando que solo un hilo pueda modificar el número de copias a la vez.

- `synchronized returnBook()`  
  Incrementa el número de copias disponibles cuando un libro es devuelto.

### Patron implements Runnable
- Simula la acción de tomar prestado y devolver un libro aleatorio después de un tiempo aleatorio. La clase `Patron` implementa `Runnable`, por lo que cada instancia corre en su propio hilo.
- Cada patrono intentará tomar un libro de forma aleatoria, lo "leerá" durante un tiempo aleatorio y luego lo devolverá.
  
### Main
- Registra los libros en la biblioteca.
- Crea múltiples instancias de la clase `Patron`, cada una representando a un usuario de la biblioteca.
- Lanza los hilos para simular las interacciones concurrentes con la biblioteca.

## Cómo ejecutar el sistema
1. Clona el repositorio.
2. Asegúrate de tener instalado lo necesario.
```bash
mvn clean install
```
3. Compila todos los archivos:
```bash
mvn clean compile
```
4. Corre el código:
```bash
mvn exec:java -Dexec.mainClass="src.main.java.Main"
```
5. Corre los tests:
```bash
mvn test
```