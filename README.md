# 📌 GitHub User Activity CLI (Java)

A simple **Command Line Interface (CLI)** application built using **Java** that fetches and displays recent public activity of any GitHub user using the **GitHub REST API**.

This tool allows users to view real-time GitHub activity directly from the terminal.

---

## 🚀 Features

* 🔎 Fetch public activity of any GitHub user
* 📦 Displays recent events (Push, Create, Issues, Watch, etc.)
* 🌐 Uses GitHub REST API
* ⚡ Built using Java `HttpClient`
* 📄 JSON parsing using Jackson / Gson
* ❌ Handles invalid usernames gracefully
* 🧾 Clean and readable CLI output

---

## 🛠️ Technologies Used

* **Java 11+**
* Java `HttpClient`
* GitHub REST API
* Jackson / Gson (for JSON parsing)
* Java Streams API

---

## 🌐 API Used

The application uses the official GitHub API:

```
https://api.github.com/users/{username}/events
```

This endpoint returns the latest public events performed by the user.

---

## 📂 Project Structure

```
org.example
│
├── Main.java
├── GitHubService.java
└── models/
```

---

## ⚙️ How It Works

1. User enters a GitHub username.
2. Application sends a GET request to GitHub API.
3. Parses the JSON response.
4. Displays formatted activity in the terminal.

---

## ▶️ How to Run

### 1️⃣ Compile

```bash
javac org/example/Main.java
```

### 2️⃣ Run

```bash
java org.example.Main
```

---

## 📌 Example Usage

```
Enter GitHub username: torvalds

Recent Activity:

- Pushed 3 commits to linux repository
- Opened a new issue in subsystem repo
- Starred a repository
```

---

## 📋 Supported Event Types

Some common GitHub events handled:

* PushEvent
* CreateEvent
* IssuesEvent
* WatchEvent
* ForkEvent
* PullRequestEvent

---

## ❗ Error Handling

* Displays message if user does not exist
* Handles API rate limiting errors
* Handles network errors gracefully

---

## 📈 Possible Improvements

* Add authentication using GitHub Personal Access Token
* Add pagination support
* Display more detailed event data
* Add colored CLI output
* Package as executable JAR
* Convert to Spring Boot REST service
* Add caching for repeated users

---

## 📜 License

This project is open-source and free to use for educational purposes.
