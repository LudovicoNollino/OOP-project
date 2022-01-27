<p align = "center">



<h1 align="center">TwitterHashtagAnalytics

Object Oriented Programming Project 2021/2022

### Applicazione Java di tipo WEB-REST che sfrutta il framework SpringBoot e l'API di Twitter per offrire all'utente la possibilità di effettuare ricerche per hashtag, filtraggio per hashtag e metriche pubbliche, e statistiche sui tweet giornalieri.


#### :warning: NOTA IMPORTANTE: nelle specifiche era richiesto il filtraggio per numero di reply ma tale parametro, non essendo presente sull'API-proxy fornita, è stato sostituito ,in accordo con il docente, dal numero di followers dell'utente che ha effettusto il post.


## **Indice** :bookmark_tabs:
* [Introduzione](#intro)
* [Come iniziare](#config)
* [Rotte](#rotte)
* [Chiamate API](#calls)
* [Eccezioni](#eccez)
* [Test](#test)
* [Documentazione](#doc)
* [Autori](#autor)

<a name="intro"></a>
## Introduzione :mega:

 L' Applicazione Java prodotta consente la ricerca dei tweet passando come parametro di input uno o più hashtags, la lingua e il numero dei tweet che si vuole visualizzare. I tweet restituiti contengono almeno uno degli hashtag inseriti, ciò vuol dire che in un post possono essere presenti anche altri hashtag non richiesti dall'utente.
 I risultati trovati, inoltre, possono filtrati ed analizzati.
* **FILTRI** 
  * *[GIORNO](#4):* vengono restituiti i tweet scritti in uno specifico giorno.
  * *[HASHTAG](#5):* vengono restituiti i tweet che contengono degli hashtag specifici.
  * *[METRICHE PUBLICHE](#6):* vengono restituiti i tweet con un numero like, retweet followers maggiore di quello inserito.

* **STATISTICHE** 
  * *[POST_GIORNALIERI](#7): vengono restituite le statistiche sulle metriche pubbliche dei tweet postati in un determinato giorno* 
  * *[HASHTAG](#8): vengono restituite le statistiche su un determinato hashtag* 
  
  <a name="config"></a>
## Come iniziare :bulb:
 * Clonare in locale la repository corrente da cmd con il seguente comando: git clone https: //github.com/LudovicoNollino/OOP-Project,
 * Avviare il progetto da un IDE o da cmd come SpringBoot Application.
 * Una volta in esecuzione, utilizzare un client server (Postman) per usufruire dell'applicazione ed effettuare le GET requests all'indirizzo http://localhost:8080/
 
 <a name="rotte"></a>
## Rotte :ship:

 N° |Tipo | Rotta | Descrizione
 ----- | ------------ | -------------------- | ----------------------
 [1](#1) |` GET ` | `/posts/get_tweet` | *prende in input uno o più hashtag, ricerca e salva i dati*
 [2](#2) |` GET ` | `/posts/metadati` | *output: JSONObject che contiene i metadati, ovvero le informazioni di ogni tipo di dato visualizzato*
 [3](#3) |` GET ` | `/posts/data` | *output: JSONObject che contiene i dati dei post precedentemente ricercati e salvati*
 [4](#4) |` GET ` | `/posts/filter/daily` | *output: JSONObject che contiene i tweet postati nel giorno inserito*
 [5](#5) |` GET ` | `/posts/filter/hashtag` | *output: JSONObject che contiene i tweet filtrati con l'hashtag inserito dall'utente*
 [6](#6) |` GET ` | `/posts/filter/public-metrics` | *output: JSONObject che contiene i tweet con un numero di like, retweet e followers superiore a quello inserito*
 [7](#7) |` GET ` | `/posts/stats/daily` | *output: HASHMAP che contiene numero massimo, numero minimo e media di like, retweet e followers dei tweet postati in un giorno*
 [8](#8) |` GET ` | `/posts/stats/hashtag` | *output: HASHMAP che contiene numero massimo, numero minimo e media degli hashtag per tweet*
 
 <a name="param"></a>
## Chiamate API :telephone_receiver:

#### :memo: Cosa passare in input:

N° | Parametri | Tipo 
----- | ------------ | -------------------- 
[1](#1) | `hashtag, count, lang` | *String, int, String*
[4](#4) | `date` | *String*
[5](#5) | `hashtag` | *String* 
[6](#6) | `likes, retweets, followers` | *int, int, int* 
[7](#7) | `date` | *String* 
[8](#8) | `hashtag` | *String* 

#### :page_with_curl: Cosa viene retituito:

<a name=1></a>
### 1. get_tweet

![Screenshot (11)](https://user-images.githubusercontent.com/95432231/150433081-3b794f8a-d582-4aee-8355-f50ed9b22b3c.png)

### 2. metadati

```json
{
    "Metadati": {
        "tweet": {
            "date": "String",
            "hashtags": "ArrayList<String>",
            "id": "long",
            "retweets": "int",
            "likes": "int"
        },
        "user": {
            "followers": "long",
            "name": "String",
            "id": "long"
        }
    }
}
```

<a name=3></a>
### 3. tweet_data
Dati relativi ad esempio precedente:
```json
{
    "Dati su tweet e utenti trovati": [
        {
            "tweet": {
                "hashtags": [
                    "music",
                    "blues"],
                "created_at": "2022-01-20" 
                "id": 1484300157827047429,
                "retweets": 0
                "likes": "0"
            },
            "user": {
                "followers": "3275"
                "name": "WNMC Radio Playlist",
                "id": 259312208
            }
        }
    ]
}
```

<a name=4></a>
### 4. Hashtag_Filter
 ```json
{
    "Lista filtrata di tweet che contengono l'hashtag cercato": [
        {
            "created_at": "2022-01-20"
            "id":1484301772172972032
            "hashtags": ["raye",
                "tiktok",
                "awards",
                "britawards",
                "music"],
          
        }
    ]
 }
```

<a name=5></a>
### 5. Daily_Filter
 ```json
 {
     "Lista filtrata di tweet pubblicati il giorno cercato": [
         {
              "Numero di followers":42
              "Nome dell'utente che ha postato il tweet": "Diana Sawicka"
              "Id":1484301772172972032
              "Data di creazione": "2022-01-20"
              "hashtag": ["raye",
                "tiktok",
                "awards",
                "britawards",
                "music"]
         }
     ]
 }
 ```

<a name=6></a>
### 6. PublicMetrics_Filter
 ```json
 {
     "Lista filtrata secondo i parametri inseriti": [
        {
            "Numero di likes": 0
            "Numero di followers": 3275
            "Nome dell'utente che ha postato il tweet": "WNMC Radio Playlist"
            "Numero di retweets":0
            "Id del tweet filtrato":1484300157827047429
        }
      ]
 }
 ```

<a name=7></a>
### 7. Hashtag_Stats
 ```json
 {
    "Media degli hashtag per tweet": 1.2,
    "Totale dei tweet contenenti l'hashtag #music": 4.0,
    "Minimo numero di hastag contenuto in un tweet": 1.0,
    "Massimo numero di hashtag contenuto in un tweet": 11.0
}
 ```

 <a name=8></a>
### 8. Daily_Tweet_Stats
 ```json
 {
    "Massimo numero di followers tra gli utenti dei tweet analizzati": 0.0,
    "Massimo numero di likes dei tweet analizzati": 0.0,
    "Media dei likes dei tweet analizzati": 0.1,
    "Media dei retweets dei tweet analizzati": 0.1,
    "Massimo numero di retweets dei tweet analizzati": 0.0,
    "Minimo numero di likes dei tweet analizzati": 1.0,
    "Minimo numero di followers tra gli utenti dei tweet analizzati": 1.0,
    "Totale dei tweet analizzati nella data scelta": 10.0,
    "Media dei followers": 0.1,
    "Minimo numero di retweets dei tweet analizzati": 1.0
}
 ```
 
 <a name="eccez"></a>
## Eccezioni :x:
 Sono state gestite eccezioni standard di Java.
 
 <a name="test"></a>
## Test :question:
 Sfruttando il framework JUnit sono stati effettuati un test sul metodo di costruzione dell'URL per effettuare la ricerca e un test sull' eccezione InternalServerError.
 
 <a name="doc"></a>
## Documentazione :abc:
 Il progetto è stato documentato con Javadoc.
 
 ## Autori e commenti :busts_in_silhouette:

Progetto realizzato da
 
Simone Recchia: Service, Stats.
 
Ludovico Nollino: Filter, Model.
 
Lavoro congiunto su Controller, Test e ExceptionHandler.
 
#### NOTA IMPORTANTE: negli ultimi giorni di lavoro è stato svolto tutto sulla stessa macchina in quanto ci sono state problematiche legate ai funzionamenti di GitHub. Nella fattispecie, non venivano effettuate correttamente le push e le pull. 
 
 
 




