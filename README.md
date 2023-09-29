# Country-University Explorer App

## Overview

This application allows users to explore information about different countries and universities. It follows modern Android app development practices and architectural patterns, making it a great example of how to build a robust and user-friendly Android app.

## Features


- **University Information in Different Countries**: It displays a list of all the countries along with their flags in a horizontal recycler view. When a country is selected, the relevant university data is fetched. By default, India is selected as the default country. However, when the user changes to a different country, the app fetches and displays the relevant university data.
- **Offline-First Approach**: The app employs an offline-first approach, ensuring that users can access data quickly, even when offline.
- **Data Staleness Logic**: The UniversityRepository uses data staleness logic to determine whether to fetch data from a remote server or use data from the local database. 

## Architecture

The app follows the Model-View-ViewModel (MVVM) architectural pattern, separating the UI logic from the business logic. It also utilizes the following technologies and practices:

- **Single Activity**: The app employs a single-activity architecture with multiple fragments.
- **Navigation Component**: It utilizes the Navigation Component to manage navigation between fragments.
- **Dependency Injection**: The app uses Hilt for dependency injection, making it easy to manage and provide dependencies.
- **RecyclerViews and Adapters**: RecyclerViews are used to display lists of countries and universities efficiently. Custom adapters are implemented to handle data binding.
- **Glide**: Glide is used for loading and caching images, making the app efficient when displaying country flags.
- **Room Database**: Room is used as a local database to store and retrieve data efficiently.
- **Retrofit**: Retrofit is used for making network requests to fetch data from a remote server.

## Usage

The app is straightforward to use:

1. **Country Selection**: When you open the app, you'll see a list of countries along with their flags in a horizontal recycler view. By default, India is selected. Swipe to select a different country.
2. **University Information**: Below the country list, you'll find a vertical recycler view displaying universities in the selected country.
3. **Data Refresh**: The app employs an offline-first approach. If the data is already present in the local database, it is immediately displayed to the user. If the data is stale (based on predefined criteria), the app fetches fresh data from the server in the background.
4. **Web Links**: If you click on the links provided for universities' web pages, they will open in your device's default web browser.

## Building the App

To build the app, follow these steps:

1. Clone the repository: `git clone https://github.com/your-username/Country-University-Explorer-App.git`
2. Open the project in Android Studio.
3. Build and run the app on an Android emulator or a physical device.

## Contribution

Contributions are welcome! If you'd like to enhance the app or fix any issues, please create a pull request.

## License

This app is open-source and available under the [MIT License](LICENSE.md). You are free to use and modify it as per your needs.

---

Happy exploring with the Country-University Explorer App! If you have any questions or need further assistance, please feel free to reach out.

For more information on Android app development, visit [developer.android.com](https://developer.android.com/).

![App Screenshot](app_screenshot.png)

---

*Disclaimer: This README template is for demonstration purposes only. Please customize it according to your app's specific details and requirements.*
