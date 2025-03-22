# ✨ Android Challenge

This is an Android application that fetches and displays articles from a mock API. The project demonstrates skills in **UI design, API integration, cache management, and modern Android development practices**.

---

## 🌟 Features

- ✔ **Article List & Details** – Fetch articles from an API and display them in a modern UI.
- ✔ **API Integration** – Uses `Retrofit` and `OkHttp` for networking.
- ✔ **Image Loading** – Uses `Glide` for optimized image rendering.
- ✔ **View Binding** – Reduces boilerplate code for better readability.
- ✔ **Grid/List View Toggle** – Users can switch between Grid and List view, with preferences saved using `SharedPreferences`.
- ✔ **Offline Handling** – Detects network connectivity and informs users when offline.
- ✔ **Dependency Injection** – Uses `Dagger` for efficient dependency management.
- ✔ **RxJava for Asynchronous Calls** – Efficiently handles API requests.

---

## 🔧 Tech Stack

- **Programming Language:** Java
- **Networking:** [Retrofit](https://square.github.io/retrofit/), [OkHttp](https://square.github.io/okhttp/)
- **Dependency Injection:** [Dagger](https://github.com/google/dagger)
- **Reactive Programming:** [RxJava](https://github.com/ReactiveX/RxJava), [RxAndroid](https://github.com/ReactiveX/RxAndroid)
- **Image Loading:** [Glide](https://bumptech.github.io/glide/)
- **State Management:** ViewModel + LiveData
- **Persistence:** SharedPreferences
- **Connectivity Monitoring:** `ConnectivityManager`

---

## 🌈 UI Design

The UI follows a **modern and clean design**, following the provided Figma layout:
[👉 Figma Design](https://www.figma.com/design/O5PRl1FsvKXLfHVPELGZ6d/Development-Challenge)

- **Article List Screen** – Displays articles in **List or Grid view**.
- **Article Detail Screen** – Shows full article content with images.

---

## ⚙️ Project Setup

### 1. Prerequisites
- **Android Studio** (Latest Version)
- **Java 8+**
- **Gradle 8+**

### 2. Clone the Repository
```sh
git clone https://github.com/atiqulalam257/AndroidChallenge.git
cd AndroidChallenge
```

### 3. Open in Android Studio
- Open **Android Studio**
- Click **"Open an Existing Project"**
- Select the `AndroidChallenge` folder

### 4. Build & Run the App
- Connect an **Android device** or start an **emulator**
- Click **Run ▶️** in Android Studio

---

## 🌐 API Details
The app fetches articles from:
[👉 Mock API](https://mocki.io/v1/9b040bf5-62aa-4ba6-b3f2-f7a1e146097a)

**Example API Response:**
```json
[
  {
    "id": 1,
    "title": "Introduction to Jetpack Compose",
    "imageUrl": "https://example.com/article1.jpg",
    "publishedAt": "2025-03-06T20:00:00Z",
    "content": "Jetpack Compose is the modern UI toolkit for Android..."
  }
]
```

---

## 📂 Project Structure
The project follows **MVVM (Model-View-ViewModel) architecture** for clean separation of concerns.

```
📆 AndroidChallenge
 ┣ 📚 app
 ┃ ┣ 📂 data
 ┃ ┃ ┣ 📂 api          # Retrofit API service
 ┃ ┃ ┣ 📂 model        # Data classes
 ┃ ┣ 📂 di             # Dependency injection (Dagger)
 ┃ ┣ 📂 ui
 ┃ ┃ ┣ 📂 articles     # Article List Screen
 ┃ ┃ ┣ 📂 article      # Article Detail Screen
 ┃ ┣ 📂 utils          # Helpers (Network, Glide, SharedPreferences)
 ┣ 📃 build.gradle
 ┣ 📃 AndroidManifest.xml
 ┣ 📃 README.md
```

---

## 🔗 Useful Libraries & Resources
- **Retrofit:** [Documentation](https://square.github.io/retrofit/)
- **OkHttp:** [Documentation](https://square.github.io/okhttp/)
- **Hilt:** [Documentation](https://developer.android.com/training/dependency-injection/hilt-android)
- **RxJava:** [Documentation](https://github.com/ReactiveX/RxJava)
- **Glide:** [Documentation](https://bumptech.github.io/glide/)

---

## 📏 Contribution Guidelines
Want to contribute? Follow these steps:
1. **Fork the repository**
2. **Create a new branch** (`feature-new-ui`)
3. **Commit your changes** (`git commit -m "Added new UI design"`)
4. **Push to GitHub** (`git push origin feature-new-ui`)
5. **Open a Pull Request** 🚀

---

## 📝 License
This project is **open-source** and available under the **MIT License**.

---

Let me know if you need any modifications! 🚀😊
