# K�ytt�ohje

## Ohjelman k�ynnist�minen

Ohjelma k�ynnistet��n komennolla
```
java -jar IcehockeyStats-1.1.jar
```

Ohjelma edellytt��, ett� sovelluksen kanssa samassa kansiossa on tiedostot: home.txt ja away.txt.
N�m� tiedostot pit�v�t sis�ll��n joukkueen nimen ja pelaajalistat.

Kun ohjelma k�ynnistet��n n�kyviin tulee k�ynnistyksen yhteydess� ladatut pelaajalistat ja joukkueiden nimet (j�rjestyksess� Koti / Vieras)

<img src="https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje-kokoonpanot.png" width="400">

V�lilehdest� "Tilastointi" p��see tilastointin�kym��n

<img src="https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje-tilastointi.png" width="400">

Painamalla nappulaa "Aloita ottelu" aktivoituu nappula "Aloita er�". Aseta er�npituus minuutteina ennen er�n aloitusta. Painamalla nappulaa "Aloita er�" er�n numero muuttuu ja kellon k�ynnistys/pys�ytys nappula aktivoituvat. K�ynnist� er� painamalla kellon k�ynnist� painiketta. Pelikatkon ajaksi pys�yt� kello.

Jos tulee maali, niin paina "Lis��.." nappulaa joko koti tai vierasjoukkueen maali-ikkunassa. N�kym� vaihtuu maalinsy�tt�n�kym��n

<img src="https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje-maali.png" width="400">

Sovellus t�ytt�� automaattisesti maalin syntyajan. K�ytt�j� voi joko antaa tekstikentt��n pelaajan ja sy�tt�jien numeron tai vaihtoehtoisesti valita pelaajat alasvetovalikoista. Kun tiedot on oikein painetaan "Tallenna nappulaa", joka t�ydent�� tilastointin�kym�ss� olevan maalitaulukon.

Jos tulee rangaistus, niin paina "Lis��.." nappulaa joko koti tai vierasjoukkueen rangaistus-ikkunassa. N�kym� vaihtuu rangaistuksensy�tt�n�kym��n

<img src="https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje-rangaistus.png" width="400">

Sovellus t�ytt�� automaattisesti rangaistuksen alkamisajan. K�ytt�j� sy�tt�� pelaajan numeron tai vaihtoehtoisesti valitsee sen alasvetovalikosta. Lis�ksi annetaan rangaistuksen kesto (min) ja valitaan rangaistuksen syy alasvetovalikosta. Kun tiedot on oikein painetaan "Tallenna nappulaa", joka t�ydent�� tilastointin�kym�ss� olevan rangaistustaulukon.

<img src="https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje-tilastointi2.png" width="400">

Ottelun p��tytty� painetaan "Lopeta ottelu" nappulaa. T�m� toimenpide tallentaa ottelun tapahtumat (maalit ja rangaistukset) ja mahdollisesti tallentamattomat joukkue- ja pelaajatiedot tietokantaan. Tietokannan avulla on mahdollista my�hemmin tarkastella pelaaja- ja joukkuekohtaisia tilastoja.
 