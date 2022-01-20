<p align = "center">



<h1 align="center">TwitterHashtagAnalytics

Object Oriented Programming Project 2021/2022

### Applicazione Java di tipo WEB-REST che sfrutta il framework SpringBoot e l'API di Twitter per offrire all'utente la possibilità di effettuare ricerche per hashtag, filtraggio per hashtag e metriche pubbliche, e statistiche sui tweet giornalieri.

<\div>

#### :warning: NOTA IMPORTANTE: nelle specifiche era richiesto il filtraggio per numero di reply ma tale parametro, non essendo presente sull'API-proxy fornita, è stato sostituito ,in accordo con il docente, dal numero di followers dell'utente che ha effettusto il post.
<\p>

## **Indice** :bookmark_tabs:
* [Introduzione](#intro)
* [Come iniziare](#config)
* [Rotte](#rotte)
* [Chiamate API](#calls)
* [Esempio di esecuzione](#ex)
* [Eccezioni](#eccez)
* [Test](#test)
* [Documentazione](#doc)
* [Autori e commenti](#autor)

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
 [7](#7) |` GET ` | `/posts/stats/daily` | *output: JSONObject che contiene numero massimo, numero minimo e media di like, retweet e followers dei tweet postati in un giorno*
 [8](#8) |` GET ` | `/posts/stats/hashtag` | *output: JSONObject che contiene numero massimo, numero minimo e media degli hashtag per tweet*
 
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

inserire immagine

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
                "hashtags": [],
                "created_at": 
                "id": "",
                "retweets": ""
                "likes":
            },
            "user": {
                "followers": ""
                "name": "",
                "id":
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
            "created_at": ""
            "id":
            "hashtags": [],
            ]
        }
```

<a name=5></a>
### 5. Daily_Filter
 ```json
 {
     "Lista filtrata di tweet pubblicati il giorno cercato": [
         {
              "Numero di followers":
              "Nome dell'utente che ha postato il tweet": ""
              "Id":
              "Data di creazione": ""
              "hashtag": []
         }
     ]
 }

<a name=6></a>
### 6. PublicMetrics_Filter



<a name=7></a>
### 7. Hashtag_Stats
(https://user-images.githubusercontent.com/95432231/150432194-d06d23b2-ab1a-46a9-858f-93d6770e253d.png)






