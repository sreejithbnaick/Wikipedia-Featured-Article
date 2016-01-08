# Wikipedia Feature Article
Java Code for getting Wikipedia featured article

Requirements:

* Java
* Jsoup Java Library
* A system to run the code !!!

This code uses scrapping, use at your own risk ....

What to do:


Get JSOUP java library `jsoup-1.8.3.jar` and then download `Wikipedia.java` file, in your java code just call

`Wikipedia.getWikiFeatureArticle(ts)` where `ts` is the timestamp (in milliseconds) of the day article is featured.
and get result in json string, for eg

    {
    "featured" : Bruce Kingsbury,
    "url" : https://en.wikipedia.org/wiki/Bruce_Kingsbury,
    "title" : Bruce Kingsbury,
    "extract" : Bruce Steel Kingsbury, VC (8 January 1918 â€“ 29 August 1942) was an Australian soldier of the Second World War. Serving initially in the Middle East, he later gained renown for his actions during the Battle of Isurava, one of many battles forming the Kokoda Track Campaign in New Guinea. His bravery during the battle was recognised with the Victoria Cross, the highest decoration for gallantry \"in the face of the enemy\" that can be awarded to members of the British and Commonwealth armed forces. The first serviceman to receive the VC for actions in Australian territory, Kingsbury was a member of the 2/14th Infantry Battalion.\nOn 29 August 1942, during the Battle of Isurava, Kingsbury was one of the few survivors of a platoon that had been overrun by the Japanese.,
    "thumbnail" : -{
        "source" : https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/BruceSKingsbury.jpg/225px-BruceSKingsbury.jpg,
        "width" : 225,
        "height" : 320
    }

Sample: http://www.codesmell.in/wikifeature?ts=1452277904000 , try changing `ts` value, it might be tooo old.
        http://www.codesmell.in/wikifeature , using time in server.
