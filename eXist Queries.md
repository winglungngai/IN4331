### Get Movie with Title
```
let $ms:=doc("/movies/movies_alone.xml")/movies/movie
let $as:=doc("/movies/artists_alone.xml")/artists/artist

let $title:='Heat'

for $movies in $ms[contains(title, $title)]
    return $movies
```

### Get Movie with Genre
```
let $ms:=doc("/movies/movies_alone.xml")/movies/movie
let $as:=doc("/movies/artists_alone.xml")/artists/artist

let $genre:='Crime'

for $movies in $ms[genre=$genre]
    return $movies
```

### Get Movie with Director Name
```
let $ms:=doc("/movies/movies_alone.xml")/movies/movie
let $as:=doc("/movies/artists_alone.xml")/artists/artist

let $directorName:='Clint Eastwood'

let $directorId:=$as[contains(concat(first_name, ' ', last_name), $directorName)]/@id
return $ms[director/@id=$directorId]
```


### Get Movie with Actor Name
```
let $ms:=doc("/movies/movies_alone.xml")/movies/movie
let $as:=doc("/movies/artists_alone.xml")/artists/artist

let $actorName:='Clint Eastwood'

let $actorId:=$as[contains(concat(first_name, ' ', last_name), $actorName)]/@id
return $ms[actor/@id=$actorId]
```

### Get Movie within certian time interval
```
let $ms:=doc("/movies/movies_alone.xml")/movies/movie
let $as:=doc("/movies/artists_alone.xml")/artists/artist

let $earliest:=1999
let $latest:=2020

return $ms[year>=$earliest and year<=$latest]
```

### Get Movie with keyword in summary
```
let $ms:=doc("/movies/movies_alone.xml")/movies/movie
let $as:=doc("/movies/artists_alone.xml")/artists/artist

let $keyword:='love'

return $ms[contains(summary, $keyword)]
```


