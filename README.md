# Icehockey Stats

Sovellus on tarkoitettu jääkiekko-ottelun tapahtumien kirjaamiseen. Sovellukseen voi ladata koti- ja vierasjoukkueiden kokoonpanot. Pelin aikana sovelluksessa on tehokasta peliaikaa mittaava kello. Pelin aikana voidaan kirjata maalit, rangaistukset ja maalivahtien torjunnat sekä vaihdot. Tapahtumat tallennetaan tietokantaan ja lisäksi ottelusta voidaan muodostaa lopuksi ottelupöytäkirja.

## Dokumentaatio

- [Käyttöohje](https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

- [Vaatimusmäärittely](https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

- [Arkkitehtuurikuvaus](https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

- [Tuntikirjanpito](https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

- [Viikko 5](https://github.com/samilait/ot-harjoitustyo/releases/tag/viikko5) 
- [Viikko 6](https://github.com/samilait/ot-harjoitustyo/releases/tag/viikko6) 
- [Viikko 7](https://github.com/samilait/ot-harjoitustyo/releases/tag/viikko7) 

## Komentorivitoiminnot

### Testaus
Testit suoritetaan komennolla: mvn test

Testikattavuusraportti luodaan komennolla: mvn jacoco:report

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto *target/site/jacoco/index.html*