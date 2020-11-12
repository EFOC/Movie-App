# Movie-App

Created a Movie App for users to search movies and save their favorite movies to their profiles.

Some of the 

* 100% Kotlin
* One activity, multiple fragments for the screens
* Jetpack library
* Firebase Database to save User's info
* Firebase Authentication let User log-in using their phone number, email, or Gmail

### Log-On
User is able to log on the app using 3 methods, phone number, Gmail, or personal email.(Example uses auto log-in)
Once the User is authenticated, they will have the extra options to sign-out, save a movie, and view the movies saved in the "Your List" section

![](https://github.com/EFOC/Movie-App/blob/master/app/libs/gifs/log_on.gif)

### Log-Off
When the User logs-off, the app shows the anonymous version where saving movies and viewing your list is disabled

![](https://github.com/EFOC/Movie-App/blob/master/app/libs/gifs/sign_off.gif)

### Saving Movie
The User can save a movie by tapping the save button beside the movie, the info will be stored in the database to retrieve when they want to see it

![](https://github.com/EFOC/Movie-App/blob/master/app/libs/gifs/saving_movie.gif)

### Searching Movie
Type in a string in the search bar and click the search button to get a list of related movies

![](https://github.com/EFOC/Movie-App/blob/master/app/libs/gifs/searching_movie.gif)

### Deleting Movies
Swiping left deletes movies from your list (deletes them from Firebase database)

![](https://github.com/EFOC/Movie-App/blob/master/app/libs/gifs/deleting_movie.gif)
