# Arkkitehtuurikuvaus

## Rakenne

Ohjelman koodin pakkausrakenne on seuraava

![Pakkauskuvaus](https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkauskuvaus.png)

Pakkaus _icehockeystats_ pit�� sis�ll��n k�ytt�liittym�n kontrollitoiminnot. Pakkaus _icehockeystats.domain_ pit�� sis�ll��n sovelluslogiikan ja keskeiset luokat ja pakkaus _icehockeystats.dao_ sis�lt�� tiedon luku ja tallennus toiminnot.

## K�ytt�liittym�

K�ytt�liittym� pit�� sis�ll��n nelj� tab -v�lilehte�:

- Kokoonpanot: koti ja vierasjoukkue
- Tilastointi: Keskeinen ottelutapahtumien seuranta ja kontrollointi
- Maali: Maalien sy�tt�n�kym�
- Rangaistus: Rangaistuksien sy�tt�n�kym�

K�ytt�liittym�n avulla voidaan lis�t� ottelutapahtumia ja liitt�� ne oikeisiin luokkiin (Match, Team, Player)

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat: Match, Team, Player, Goal, Penalty, Clock.
Match luokka sis�lt�� Team (home ja away) oliot. Lis�ksi Match-luokassa on lista-oliot ottelutapahtumille Goal ja Penalty. Team -luokka pit�� sis�ll��n lista-olion Player luokasta. Ja Player luokka sis�lt�� pelaajakohtaiset tiedot (number, firstName, lastName, position, line) ja lis�ksi lista-olioina pelaajaan liittyv�t ottelutapahtumat Goal ja Penalty.

## Tietojen pysyv�istallennus

Sovelluksen k�ynnistyess� ladataan joukkueiden pelaajalistat csv -tiedostosta.

Otteluun liittyv�t joukkueet ja pelaajat tallennetaan tietokantaan. Lis�ksi tietokantaan tallennetaan ottelun tapahtumat (maalit, rangaistukset). Tietokannassa on seuraavat taulut: Match, Team, Player, Goals, Penalties, PenaltyCodes. Pysyv�istallennuksessa k�ytet��n DAO -mallia. Tosin nykyisen sovelluksen ratkaisu tulisi olla enemm�n luokkakohtainen, koska MatchDao tallentaa kaikkiin luokkiin liittyv�n tiedon.

### P��toiminnallisuudet

#### Sovelluksen k�ynnistys

Kun sovellus k�ynnistet��n, niin koti- ja vierasjoukkueiden pelaajat (Nimi, pelinumero, pelipaikka, kent�llinen) luetaan tekstitiedostosta k�ytt�liittym�n tableView -n�kymiin. K�ytt�j� voi tarvittaessa muokata pelaajaluetteloita.

![Sekvenssikaavio](https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/OT-Sekvenssi1.png)

#### Ottelun k�ynnistys

Kun ottelu k�ynnistet��n, niin tehokas peliaika alkaa juoksemaan k�ytt�j�n painaessa "K�ynnist�" nappulaa. Kellon k�ydess� Clock oliota p�ivitet��n sekunnin v�lein

#### Maalin lis��minen

Maalin lis��misess� k�ytt�j� valitsee maalintekij�t ja sy�tt�j�t ja n�m� tiedot tallennetaan sek� joukkueen, ett� pelaajien maali ja sy�tt�saldoihin.

#### Rangaistuksen lis��minen

Rangaistuksen lis��misess� k�ytt�j� valitsee rikkeen tehneen pelaajan, rangaistuksen pituuden ja syyn. N�m� tiedot tallennetaan sek� joukkueen, ett� pelaajan rangaistussaldoihin my�hemp�� pysyv�istallennusta varten.

#### Tiedon tallennus

Ottelukohtaiset, joukkuekohtaiset ja pelaajakohtaiset tiedot tallennetaan tietokantaan tapahtumalajeittain (maalit, rangaistukset)

## Ohjelman rakenteeseen j��neet heikkoudet

K�ytt�liittym�n toiminnallisuuttta voisi eriytt�� enemm�n. Nyt FXMLController pit�� sis�ll��n eritt�in paljon toiminnallisuutta, joka voisi olla erillisess� sovelluslogiikka kokonaisuudessa.

Tietokannan rakennetta voisi viel� mietti� uudelleen.





