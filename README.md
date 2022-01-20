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
            "hashtags": "ArrayList<String>,
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
    "dati su tweet e utenti": [
        {
            "tweet": {
            "date": "",
                "hashtags": [
                   ],
                "id": 
                "retweets": "",
                "likes": ""
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

Stesso formato dei [dati](#3) con solo tweet scritti nel giorno indicato.

<a name=5></a>
### 5. Daily_Filter

Stesso formato dei [dati](#3) con solo tweet provenienti dal luogo indicato.

**NOTA:** *In questo caso per essere visualizzato il tweet deve contenere nel luogo la parola inserita. Questo significa che se cerco **Italia** non vedrò tweet con posizione **Milano, Lombardia**!*

<a name=6></a>
### 6. PublicMetrics_Filter

Tra default e caso specifico non cambia nulla se non i giorni indicati:
```json
{
    "Total tweets downloaded": 5.0
    "Tweets written on 2021-12-15": 5.0,
    "Tweets written on 2021-12-14": 0.0,
    "Tweets written on 2021-12-13": 0.0,
    "Percentage": 100.0
}
```

<a name=7></a>
### 7. Hashtag_Stats

Default:
```json
{
    "Total tweets downloaded": 5.0
    "Tweets with no location": 2.0,
    "Tweets with unprocessable location": 0.0
    "Tweets written in Italy": 3.0,
    "Percentage of tweets with no location": 40.0,
    "Percentage of tweets with unprocessable location": 0.0,
    "Percentage of tweets written in Italy": 60.0
}
```

**NOTA:** *Si ha NO LOCATION se nè il tweet nè l'utente hanno fornito informazioni sulla posizione, mentre UNPROCESSABLE LOCATION comprende i tweet in cui la posizione non corrisponde a "Italia", "Italy" oppure nomi di comuni e regioni in **lingua italiana**.*

Specifico:
```json
{
    "Total tweets downloaded": 5.0,
    "Tweets written in Italia": 3.0,
    "Percentage": 60.0
}
```
**NOTA:** *vedi [sopra](#5)*

<a name=8></a>
### 8. Hashtag_Stats

Default:
```json
{
    "Total tweets downloaded": 5.0
    "Min hashtags per tweet": 2.0,
    "Max hashtags per tweet": 4.0,
    "Average hashtags per tweet": 3.0
}
```

**NOTA:** *Per problemi di Twitter può accadere che il valore minimo degli hashtag sia 0 anche se impossibile visto che i tweet sono stati ricercati in base ad almeno 1 hashtag. Purtroppo questo non dipende da noi!*

Specifico:
```json
{
    "Total tweets downloaded": 5.0,
    "Tweets containing #univpm": 0.0,
    "Percentage": 0.0
}
```

**NOTA:** *la parola da ricercare va inserita senza # in questo caso!*





