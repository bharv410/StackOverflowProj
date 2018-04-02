Here I have built an Android application that fetches users from StackOverflow API endpoint.

If you have an active internet connection then it will always display the newest data from StackOverflow.
If you DO NOT have an active internet connection, then it will display whatever data was shown the last time that you opened the app.
If you DO NOT have an active internet connection and you never opened the app, then the app will display an error dialog and ask you to connect.

I used Retrofit and OkHTTP libraries for HTTP Networking.
I used Gson library as a converter so that I could parse the json directly into  the objects.
I used CardView to make the list items and avatars look more appealing.
I used Picasso library for image loading.
I used ConstraintLayout to avoid a nested view hierarchy
