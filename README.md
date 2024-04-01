# TheMeteorLandingApp
Android Mobile Development: Kotlin/Java Programming 

How does the application work:
The application starts on the Login Activity where the user can enter their login credentials if they are a registered user.  If not, the user can enter their email and password and register. Once they have been authenticated as a registered user, they can then log in. 

The user will be shown the Main/Search Activity where they can enter a year from 1840 – 2013 and then click the ‘Search’ button to view the meteor landings in that year. A new Results activity is opened to list the meteorite landings from the year selected. 

The user can click on one of the meteorite landings and it will take them to the page where they’ll see the information about the specific meteorite and they can either view the meteorite landing location on Google Maps by clicking on the location button or they can add it as one of their favourite meteorites to the Firestore database. 

To view all the favourite meteorites in the database, the user can click on the navigation and choose the Favourites page. The list of favourite meteorites is retrieved from the Firestore database and displayed in a list on the screen.

The use of MVVM:
●	ViewModel for the API Format
●	Mutablelivedata for the dataset retrieved
●	The data set is passed to the Results Activity to create the Recycler View

The use of Data Binding:
●	Throughout the project, but especially on the Results activity layout to handle variable changes in the UI

The use of Coroutines:
●	Used for the Web server, placed it in the resultsViewModel so that we can access the function such as onButtonGo() in the recyclerAdapter
●	Utilize the coroutine scope to call the dataset of meteor landings using the .async as it returns the data.

The use of Firebase Authentication:
●	Sign-in method, to authenticate users 
●	Register method, for users that wants access to the application

The use of Firestore Database:
●	Storing favourite meteorites and retrieving them from Firestore database to display on the Favourites Activity


# ![Login](https://github.com/j-arandia/TheMeteorLandingApp/assets/105087979/16844df9-728f-4b1f-ae04-240f6e0e982d) ![register](https://github.com/j-arandia/TheMeteorLandingApp/assets/105087979/1494ce36-f3de-4bd6-a563-30b3517d23a7)
# ![main](https://github.com/j-arandia/TheMeteorLandingApp/assets/105087979/aac4d11c-d847-47fa-b4be-c952f3ee76e9) ![results](https://github.com/j-arandia/TheMeteorLandingApp/assets/105087979/7aa09eb3-251a-434f-a74e-d9d09ee411ab)
# ![details](https://github.com/j-arandia/TheMeteorLandingApp/assets/105087979/271efd08-f771-4887-8d62-b5c49b3457b1) ![favourites](https://github.com/j-arandia/TheMeteorLandingApp/assets/105087979/572379b5-89a9-41d7-a05f-6ea86115d14a)
# ![location map](https://github.com/j-arandia/TheMeteorLandingApp/assets/105087979/159b1479-edf3-46fa-b32a-b463143e7758) ![firestore](https://github.com/j-arandia/TheMeteorLandingApp/assets/105087979/ef9872a8-b2b7-4e9c-a2d6-21da520e19e1)
