# Parking Management App

This is a Java Swing application that allows users to manage parking spaces in a grid layout using a graphical interface.

## Features

- Real-time clock display (hours, minutes, seconds)
- Keyboard interaction to add/remove spots
- Voice feedback using FreeTTS
- Save/load parking matrix to/from file

## Technologies

- Java 11+
- Swing (GUI)
- FreeTTS (text-to-speech)
- NetBeans IDE

## How to Run

1. Open the project in NetBeans
2. Make sure `freetts.jar` is added to the classpath
3. Run `ParkingJFrame` class

## Notes

- Only rows 0–7 and columns 0–9 are accepted.
- Voice synthesis requires FreeTTS properly installed.
