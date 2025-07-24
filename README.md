
# 🎬 Cinema Seat Booking System

A simple **console-based Java application** to simulate a cinema ticket booking system. It allows users to:

- View available seats
- Book seats with different pricing by row
- Cancel previously booked tickets using a unique seat ID

---

## 📌 Features

- ✅ Book multiple tickets with name and seat choice
- ✅ View full seat layout (8x8 matrix)
- ✅ Cancel ticket using **name and seat ID**
- ✅ Dynamic pricing:
  - Rows 1–3 → ₹25
  - Rows 4–5 → ₹50
  - Rows 6–8 → ₹100

---

## 🧠 System Design

| Component | Responsibility |
|----------|----------------|
| `Display` | Shows the seat matrix (0 = free, 1 = booked) |
| `Person`  | Stores user name and seat ID |
| `Counter` | Handles booking, cancellation, and validation |
| `Cinema`  | Main driver class with menu loop |

---

## 🖥️ Seat Matrix

Each seat is represented by a `0` (available) or `1` (booked).  
The user selects row and column (1 to 8), and is assigned a seat ID:  
```java
seatID = row * 10 + col;
```
For example:
- Seat (2, 3) → ID `23`
- Seat (6, 5) → ID `65`

This ID is needed to **cancel a booking**.

---

## 🚀 How to Run

### 1. **Compile**
```bash
javac Cinema.java
```

### 2. **Run**
```bash
java Cinema
```

Ensure `Cinema.java` contains all classes (or split into multiple files if needed).

---

## 📷 Sample Output

```
------ CINEMA BOOKING SYSTEM ------
1 - Book Ticket
2 - Cancel Ticket
3 - Display Seats
0 - Exit
Enter choice: 1
Enter number of tickets to book:
1
Enter name: John
... seat grid displayed ...
Enter row and column (1-8): 2 3
Booking successful!
Name: John
Seat ID: 23
Amount: ₹25
```

---

## ⚠️ Notes

- This is a **console-based simulation**. No file/database persistence is implemented.
- Input validation is handled for out-of-range rows/columns and double bookings.
- Useful for beginners learning object-oriented Java.


---

## ✅ To Do (Optional Enhancements)

- [ ] Save bookings to file (CSV or JSON)
- [ ] Load previous bookings at startup
- [ ] Add user login system
- [ ] GUI with Swing or JavaFX

---

## 👨‍💻 Author

Built by **k.Deepak Varma**  
Feel free to use, modify, or extend this program.
