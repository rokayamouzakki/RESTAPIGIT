# RESTAPIGIT
Coding Challenge: REST microservice that list the languages used by the 100 trending public repos on GitHub.

this Backend application is used to list the languages used by the 100 trending public repos on GitHub. And for every language, you need to calculate theese attributes  : * Number of repos using this language.  * The list of repos using the language


So for that, I create a java spring boot application, which consume the response of calling the endpoint Github repositories "https://api.github.com/search/repositories?q=created:>{date}&sort=stars&order=desc".

The application fetch trending repositories using RestTemplate and parse each item in object of repositories, then the application calculate for each language used in this list the number of repositories, at the end the application return list of languages using, number of repos using this language and the list of repos using the language. To be consumed by a frontend application

So for that, the frontend application should call the request mapping "api/repos/languages" with the date in the requestBody:

RequestMapping : api/repos/languages
Method : GET
Produces: APPLICATION_JSON
RequestBody: String "date"


Thanks for reading :) :) 
