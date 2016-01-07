# Wikipedia Feature Article
Java Code for getting Wikipedia featured article

Requirements:

* Java
* Jsoup Java Library
* A system to run the code !!!

It is basically scrapping, use at your own risk ....

What to do:


Get JSOUP java library and then download `Wikipedia.java` file, in your java code just call

`Wikipedia.getWikiFeatureArticle(ts)` where `ts` is the timestamp (in milliseconds) of the day article is featured.
and get result in json string, for eg

`{"title":"Upper and Lower Table Rock","url":"https://en.wikipedia.org/wiki/Upper_and_Lower_Table_Rock"}`
