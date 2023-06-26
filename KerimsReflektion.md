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