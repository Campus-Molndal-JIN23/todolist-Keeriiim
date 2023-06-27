# Kerim kozo

## Projektet


### Beskrivning av projektet
Projektet är en applikation som skapar users & todos associerade till dem. Både users och todos kan ändras genom CRUD och
sparas slutligen i en databas. 

## Planering
Projektet planerades genom att skriva ned tankar och ideer. Därefter påbörjades ett försök
att koda det som var tänkt. Efter kodningen skrevs en ny planering baserad på vad jag hade åstadkommit. 
Detta upprepades tills projektet var klart.

### Lösningsförslag innan uppgiften påbörjas
Min tanke var att kunna skapa en app som skapar fungerande users & todos. Dessa skulle sedan sparas ned i listor
och föras över i min databas. Jag hade ett klart och tydligt mål att alla ändringar ska ske utanför databasen. Databas ska alltså endast
ta emot "slut" produkten. 

#### Hur du tänker försöka lösa uppgiften.(exempelvis)
Tanken var tidigt att ha en klass som hanterar users, en klass som hanterar todos, en klass hanterar CRUD, en klass som hanterar databasen
samt en klass som hanterar menyvalen för användaren. 

### Jira/Trello/Github Project och projekthantering enligt Scrum/Kanban
Jag använde mig utav notepad där jag innan varje kodning brainstormade mina ideer. Därefter kodade jag och sedan
samlade mina tankar i notepaden. Detta gjordes inför varje kodning.

### Vad som varit svårt & besvärligt att få till
Det absolut svåraste i detta projekt var att skriva tester till databasen. Jag fick komplett förlita mig på
chat GPT och försöka förstå varför den skrev koden som den skrev. Över lag var hela projektet kämpigt. Det var väldigt utmanande 
att få programmet till att betee sig som det var tänkt. 

### Beskriv lite olika lösningar du gjort
Det har varit varienda lösningar av olika saker under projektet. Ett exempel är att jag har två listor i min todoApp klass
där tanken var att allt som ska läggas till, uppdateras eller tas bort hamnar i dessa listor. Därefter först det som finns i listorna in i databasen.
Jag gjorde även en smart lösning där jag importerar befintlig data från databasen in i dessa listor(ifall den existerar) som jag sedan hanterar. Detta för att göra programmet mer dynamiskt.
Det fanns även ett alternativ att spara datan på en fil och läsa in den vid programstart men det kändes onödigt komplext.

## Reflektion & Slutsatser
Jag är supernöjd hur slutresultatet blev. Jag åstadkom precis det jag hade i åtankarna kring att ha programmet dynamiskt. 
Jag tycker även om min lösning att ha en app klass som hanterar crud. Det blir i min mening lättare att felsöka när det finns flera klasser än att baka in allt i en enda klass.
Jag vet dock att programet  går definitivt att förbättra. Det finns ett flertal tester som jag inte har använt mig utav och därav
vet jag att programmet är kraschbart. Det optimala hade varit att ha flera tester som testar olika delar av programmet.

### Vad har du lärt dig & framtida möjligheter
Jag har definitivt övat på att skriva ett komplett program och tillämpa tester. Detta är något jag kommer ta med mig till framtida projekt.
Viktigaste förändringen för mig är att jag är mer medveten om att jag bör försöka krascha mitt test ifall jag vill försäkra mig om en stabil produkt.
Tidigare när jag inte kände till tester så var mina tankar mer fokuserade på att skriva en "perfekt kod" från början. Nu med denna kunskap så ser jag hur
omöjligt det är att skriva en perfekt kod från början. Det är bättre att skriva en kod som fungerar och sedan förbättra den med hjälp av tester.

## VG-reflektion

### Motivering till lösningar
Jag valde mina lösningar baserat på det jag ansåg var mest effektivt. T.ex ansåg jag det vara onödig komplexitet att spara datan på en fil och läsa in den vid programstart.
Det är mycket enklare och bättre lösning att läsa kopiera in informationen från databasen till tomma listor och sedan hantera dem.
Jag valde också att ha en flera klasser samt en klass som behandlar listorna enligt CRUD. Detta för att göra programmet lättare att felsöka när det blir fel.

### Förbättringsförslag
Det finns en hel del möjlighet till att förbättra min kod. Till en början behöver jag göra fler tester för att säkerställa att programmet blir stabilare.
Jag behöver även se över min kod i helhet, både testerna samt själva programmet. Det finns flera exempel där jag hade kunnat förtydliga namn på variablar samt skriva bättre kod.
Jag ser även förbättringsmöjligheter i databasklassen gällande kodstruktur och att lägga till t.ex en autokorrigering av autoincrement.

### Ej implementerade lösningar
- En idee var att skapa en lista för users, en lista för todos, och en lista för tasks för varje todo.
- En annan idee var att skriva över informationen från databasen på en fil och läsa av filen vid programstart.

### UI/UX design
Jag har inte lagt någon vikt på designen. Jag har fokuserat på att få programmet att fungera som det är tänkt.
Det som hade kunnat läggas till rent backend-mässigt vore isåfall att ha flera select alternativ för att visa specifik information
från databasen. För övrigt hade man kunnat skapa en grafisk design genom javafx för att göra programmet mer tilltalande.

