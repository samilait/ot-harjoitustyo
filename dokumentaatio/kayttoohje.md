# Käyttöohje

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla
```
java -jar IcehockeyStats-1.1.jar
```

Ohjelma edellyttää, että sovelluksen kanssa samassa kansiossa on tiedostot: home.txt ja away.txt.
Nämä tiedostot pitävät sisällään joukkueen nimen ja pelaajalistat.

Kun ohjelma käynnistetään näkyviin tulee käynnistyksen yhteydessä ladatut pelaajalistat ja joukkueiden nimet (järjestyksessä Koti / Vieras)

<img src="https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje-kokoonpanot.png" width="400">

Välilehdestä "Tilastointi" pääsee tilastointinäkymään

<img src="https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje-tilastointi.png" width="400">

Painamalla nappulaa "Aloita ottelu" aktivoituu nappula "Aloita erä". Aseta eränpituus minuutteina ennen erän aloitusta. Painamalla nappulaa "Aloita erä" erän numero muuttuu ja kellon käynnistys/pysäytys nappula aktivoituvat. Käynnistä erä painamalla kellon käynnistä painiketta. Pelikatkon ajaksi pysäytä kello.

Jos tulee maali, niin paina "Lisää.." nappulaa joko koti tai vierasjoukkueen maali-ikkunassa. Näkymä vaihtuu maalinsyöttönäkymään

<img src="https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje-maali.png" width="400">

Sovellus täyttää automaattisesti maalin syntyajan. Käyttäjä voi joko antaa tekstikenttään pelaajan ja syöttäjien numeron tai vaihtoehtoisesti valita pelaajat alasvetovalikoista. Kun tiedot on oikein painetaan "Tallenna nappulaa", joka täydentää tilastointinäkymässä olevan maalitaulukon.

Jos tulee rangaistus, niin paina "Lisää.." nappulaa joko koti tai vierasjoukkueen rangaistus-ikkunassa. Näkymä vaihtuu rangaistuksensyöttönäkymään

<img src="https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje-rangaistus.png" width="400">

Sovellus täyttää automaattisesti rangaistuksen alkamisajan. Käyttäjä syöttää pelaajan numeron tai vaihtoehtoisesti valitsee sen alasvetovalikosta. Lisäksi annetaan rangaistuksen kesto (min) ja valitaan rangaistuksen syy alasvetovalikosta. Kun tiedot on oikein painetaan "Tallenna nappulaa", joka täydentää tilastointinäkymässä olevan rangaistustaulukon.

<img src="https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje-tilastointi2.png" width="400">

Ottelun päätyttyä painetaan "Lopeta ottelu" nappulaa. Tämä toimenpide tallentaa ottelun tapahtumat (maalit ja rangaistukset) ja mahdollisesti tallentamattomat joukkue- ja pelaajatiedot tietokantaan. Tietokannan avulla on mahdollista myöhemmin tarkastella pelaaja- ja joukkuekohtaisia tilastoja.
 