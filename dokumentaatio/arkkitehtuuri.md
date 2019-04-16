# Arkkitehtuurikuvaus

## Rakenne

Ohjelman koodin pakkausrakenne on seuraava

![Pakkauskuvaus](https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkauskuvaus.png)

Pakkaus _icehockeystats_ pit�� sis�ll��n k�ytt�liittym�n kontrollitoiminnot. Pakkaus _icehockeystats.domain_ pit�� sis�ll��n sovelluslogiikan ja keskeiset luokat ja pakkaus _icehockeystats.dao_ sis�lt�� tiedon luku ja tallennus toiminnot.

## K�ytt�liittym�

## Sovelluslogiikka

## Tietojen pysyv�istallennus

### P��toiminnallisuudet

#### Sovelluksen k�ynnistys

Kun sovellus k�ynnistet��n, niin koti- ja vierasjoukkueiden pelaajat (Nimi, pelinumero, pelipaikka, kent�llinen) luetaan tekstitiedostosta k�ytt�liittym�n tableView -n�kymiin. K�ytt�j� voi tarvittaessa muokata pelaajaluetteloita.

![Sekvenssikaavio](https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/OT-Sekvenssi1.png)
