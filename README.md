This project demonstrates how to create an Android application that uses an Activity and a ViewModel programmatically without
relying on XML layouts or Compose. The main activity dynamically adds a Recyclerview, all done in Kotlin code.

Features

    Dynamic UI: No XML layouts are used. The entire UI is created programmatically.
    Simplified Structure: The project focuses on the essentials of dynamic UI creation in Android.

Files

    MainActivity.kt: The entry point of the application. It programmatically creates the UI and a Recyclerview.
    BeneficiaryAdapter.kt: A simple adapter to display content in the recyclerview.

Getting Started

Prerequisites

    Android Studio: The latest version.
    Kotlin: Ensure that Kotlin is set up in your Android project.

Installation

    Clone the repository:

    bash

    git clone https://github.com/your-repo/dynamic-fragment-android.git

    Open the project in Android Studio:
        Import the project into Android Studio by selecting the build.gradle file.

    Build the project:
        Let Android Studio sync and build the project. Ensure all dependencies are resolved.

Running the App

    Connect an Android device or use an emulator.
    Run the app from Android Studio:
        Click on the green play button or use the shortcut Shift + F10.
    Interact with the UI:
        The app will display a a list of beneficiaries.

Customization

You can customize the UI by modifying the Activity and BeneficiaryAdapter.kt file. For example, you can add more views, change the layout, increase/decrease text size.