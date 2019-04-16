# Arkkitehtuurikuvaus

## Rakenne

Ohjelman koodin pakkausrakenne on seuraava

![Pakkauskuvaus](https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkauskuvaus.png)

Pakkaus _icehockeystats_ pitää sisällään käyttöliittymän kontrollitoiminnot. Pakkaus _icehockeystats.domain_ pitää sisällään sovelluslogiikan ja keskeiset luokat ja pakkaus _icehockeystats.dao_ sisältää tiedon luku ja tallennus toiminnot.

## Käyttöliittymä

## Sovelluslogiikka

## Tietojen pysyväistallennus

### Päätoiminnallisuudet

#### Sovelluksen käynnistys

Kun sovellus käynnistetään, niin koti- ja vierasjoukkueiden pelaajat (Nimi, pelinumero, pelipaikka, kentällinen) luetaan tekstitiedostosta käyttöliittymän tableView -näkymiin. Käyttäjä voi tarvittaessa muokata pelaajaluetteloita.

![Sekvenssikaavio](https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/OT-Sekvenssi1.png)
