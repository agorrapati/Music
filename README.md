# Music
#Main Activity
Prompts for the input of the artist name for searching the apple music store API

#Music Search
Gets the input from the main activity and parses the input string encoding the input for any white spaces and converts it to URL format so that the search may continue. 
Passes the URL to JSONHandler where the JSON parsing of the data happens.

#JSONHandler
Makes an HTTPCOnnection call to the Apple API and parses the downloaded json string fetches the output string and converts the data into a ArrayList and passes the data onto the MUsicSearch.java class where the data is displayed in a ListView
