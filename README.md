# Dinoco Motors Application

This was my final for CISC-191 at San Diego Miramar College. I was tasked with
creating an application that used pulled data from a local network MySQL 
database. The application was designed using Swing.

## Dependencies

- Java
- MySql

## How to build/run the program

- Check out the 'devel' script

### Future Additions

- Fix the implementation of the GUIManager class. I meant for it to init
all frames within the class. I don't know why I didn't implement it in that way
initially.

- I might do something with a condition that decides whether or not I download
the contents of the database into memory. I originally did this to practice
loading the contents back and forth from my own computer to the device that the
server was running on but it might be cool to do some kind of CPU Usage check
so I know how to offload CPU usage to the user's machine in case the server is
overloaded

- I might change the organization of the Data. Right now the program is very
Object Oriented. I know that I can make better use of caching by making all the
data local in one object.

- If I do decide to make all the data local in one object, I want to mess with
the idea of adding padding within the object. Filling the object with garbage
values between the fields that actually mean something. Balancing some security
but still utilizing the cache

- I will make this README look more professional at some point?.. maybe

## Author

- Angelo Laberinto          Fall 2024

### LICENSE

- Probably MIT?... I will do this eventually. 

