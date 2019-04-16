# Icehockey Stats

Sovellus on tarkoitettu jääkiekko-ottelun tapahtumien kirjaamiseen. Sovellukseen voi ladata koti- ja vierasjoukkueiden kokoonpanot. Pelin aikana sovelluksessa on tehokasta peliaikaa mittaava kello. Pelin aikana voidaan kirjata maalit, rangaistukset ja maalivahtien torjunnat sekä vaihdot. Tapahtumat tallennetaan tietokantaan ja lisäksi ottelusta voidaan muodostaa lopuksi ottelupöytäkirja.

## Dokumentaatio

- [Vaatimusmäärittely](https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

- [Tuntikirjanpito](https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

## Komentorivitoiminnot

### Testaus
Testit suoritetaan komennolla: mvn test
Testikattavuusraportti luodaan komennolla: mvn jacoco:report
Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto *target/site/jacoco/index.html*