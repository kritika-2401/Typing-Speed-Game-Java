# Typing Speed Game

A Java Swing–based desktop application that helps users improve their typing speed and accuracy through interactive typing tests with real-time feedback and background music.

---

## Overview

Typing Speed Game is a Java Swing desktop application that allows users to practice typing while measuring their typing performance.

The application provides multiple test durations and text difficulty levels, highlights typing mistakes in real time, calculates typing speed (WPM) and accuracy, plays background music during the test, and displays the user's performance level at the end.

---

## Screenshots

### Start Screen

<img width="683" height="491" alt="image" src="https://github.com/user-attachments/assets/e04ca251-0d91-47e5-8c09-e0b6eb870bf4" />


### Typing Test

<img width="884" height="640" alt="image" src="https://github.com/user-attachments/assets/47349b9f-ef4a-4306-aebb-8afa8e3dc85f" />


### Result Screen

<img width="888" height="649" alt="image" src="https://github.com/user-attachments/assets/a1ae4349-f631-467f-b4c8-e140dfa92f20" />


---

## Features

- 1 Minute and 2 Minute typing modes
- Easy and Medium typing passages
- Real-time character highlighting (Correct/Wrong)
- Countdown timer
- Words Per Minute (WPM) calculation
- Accuracy calculation
- Performance levels
  - 🐢 Turtle
  - 🦊 Fox
  - 🦖 T-Rex
  - 👑 Legend
- Background music during the typing test
- Modern Java Swing GUI with gradient design
- Restart (Try Again) option

---

## How It Works

1. Select the typing duration (1 or 2 minutes).
2. Select the text difficulty (Easy or Medium).
3. Click **START BATTLE**.
4. Type the displayed passage.
5. The application highlights correct and incorrect characters in real time.
6. When the timer ends, the application displays:
   - Typing Speed (WPM)
   - Accuracy
   - Performance Level

---

## Technologies Used

- Java
- Java Swing
- AWT
- Java Sound API
- javax.swing.Timer

---

## How to Run

Compile:

```bash
javac TypingProject.java
```

Run:

```bash
java TypingProject
```

> Ensure the `sounds` folder remains in the project directory so the background music can be loaded correctly.

---

## Future Improvements

- High score system
- User profiles
- Additional difficulty levels
- More typing passages
- Dark mode
- Leaderboard
- Custom music selection

---

## Author

**Kritika Sharma**

M.Sc. Bioinformatics & Data Science
