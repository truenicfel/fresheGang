# Code für 2. Pratkikumsaufgabe Software-Architektur Sommer 2017 

Autoren: Nico Daßler, Philipp Konopac

Anwendung: https://shareit-schnitzel.herokuapp.com/

## Spezifikation der Rest API zum Anlegen von Exemplaren

Die URIs sind gekürzt. Komplette URI ist zum Beispiel: _/shareit/media/books/copies/1234_

### Bücher

| URI-Template | Methode | Body | Ergebnis/Fehler |
|--------------|---------|------|-----------------|
|/media/books/copies/isbn|GET|---|Alle Kopien der spezifizierten ISBN als JSON-Representation.|
|/media/books/copies|GET|---|Alle Kopien aller Bücher als JSON-Repräsentation.|
|/media/books/copies|POST|Copy (Book)|Neues Exemplar angelegt + Erfolgsmeldung. Mögliche Fehler:Autor/Titel/ISBN fehlt. Ungültige Buchspezifikation. ISBN existiert noch nicht (füge zunächst das Buch hinzu).|
|/media/books/copies/isbn|PUT|Copy (Book)|Exemplar verändert + Erfolgsmeldung. Mögliche Fehler: Exemplar nicht gefunden (ISBN existiert nicht). ISBN soll modifiziert werden (URI ISBN weicht von Body ISBN ab). Autor/Titel(/ISBN) fehlen.|

### Discs

| URI-Template | Methode | Body | Ergebnis/Fehler |
|--------------|---------|------|-----------------|
|/media/discs/copies/barcode|GET|---|Alle Kopien des spezifizierten Barcodes als JSON-Representation.|
|/media/discs/copies|GET|---|Alle Kopien aller Discs als JSON-Repräsentation.|
|/media/discs/copies|POST|Copy (discs)|Neues Exemplar angelegt + Erfolgsmeldung. Mögliche Fehler: Autor/Titel/Barcode fehlt. Ungültige Discspezifikation. Barcode existiert noch nicht (füge zunächst die Disc hinzu).|
|/media/discs/copies/barcode|PUT|Copy (discs)|Exemplar verändert + Erfolgsmeldung. Mögliche Fehler: Exemplar nicht gefunden (Barcode existiert nicht). Barcode soll modifiziert werden (URI Barcode weicht von Body Barcode ab). Autor/Titel(/Barcode) fehlen.|
